#_____________________________________________________________________#
#                                                                     #
#                       Carlos Ivan Canto Varela                      #      
#                        Matematicas aplicadas                        #
#                      Facultad de Ciencias, UNAM                     #
#                                                                     #
#                 Pronostico de variaciones en el PIB                 #
#           usando redes neuronales y riesgos estadisticos            #
#                                                                     #
#_____________________________________________________________________#


#_____________________________________________________________________#
#                                                                     #
#   En este codigo se realiza el calculo de los riesgos estadisticos  #
#          como atributos del modelo principal de prediccion.         #
#                                                                     #
#_____________________________________________________________________#


#----------------------------------------------------------------------
# Preparacion del espacio de trabajo.

{
rm(list = ls())
graphics.off()
start_timer = proc.time()

library(beepr)             # Alarmas.
library(doParallel)        # Computo paralelo.
library(foreach)           # Ciclos paralelizados.
library(perry)             # Particiones.
library(parallel)          # Computo paralelo.
library(quantreg)          # Regresion cuantilica.
library(readxl)            # Lectura de Excel.
library(sn)                # Distribucion t sesgada.
library(tidyverse)         # Operaciones con dataframes.

setwd('~\\UNAM\\Thesis\\Implementation')


#----------------------------------------------------------------------
# Funciones accesorias.

# Medicion de tiempo transcurrido.
  time_taken = function(seconds){
    hours = floor(seconds/(60^2))
    remainder = seconds - (hours*60^2)
    minutes = floor(remainder/(60))
    remainder = remainder - (minutes*60)
    seconds = floor(remainder)
    interval = paste('Time taken: ', hours, ' hours, ', minutes,
                     ' minutes, ', seconds, ' seconds.')
    return(interval)
  }

# Notificacion de termino.
  done = function(){
    beep(7)
    Sys.sleep(0.2)
    beep(9)
  }


#----------------------------------------------------------------------
# Preprocesamiento de datos.

database = read_xlsx(paste0(getwd(), '/Data.xlsx'),
                      sheet = 'Data',
                      col_types = c('date', rep('numeric', 23)),
                      col_names = TRUE
                     ) %>% data.frame() %>% select(1, 2, 3)

# Rezago de un periodo para atributos.
database = database %>%
  dplyr::select(date, GDPv, NFCI) %>%
  mutate(GDPv_L = lag(GDPv, 1), NFCI_L = lag(NFCI, 1)) %>%
  slice(-c(1:1))
#database = database[(1:(nrow(database) - 3)), ]


#----------------------------------------------------------------------
# Funcion de optimizacion parametrica.

optimize_it = function(initial_values, target_y, match_quants){
  
  # Auxiliares.
  temp_sums = list()
  temp_pars = list()
  
  # Malla para grados de libertad.
  for(j in 1:30){
    # Funcion de perdida.
    sq_sum = function(b){
      result = qst(match_quants, xi = b[1], omega = b[2],
                   alpha = b[3], nu = j) - target_y
      return(sum(result^2))
    }
    # Resto de parametros.
    parameters = optim(par = initial_values, fn = sq_sum,
                               method = 'L-BFGS-B',
                               lower = c(-20, 1e-10, -30),
                               upper = c(20, 50, 30))
    
    temp_sums = append(temp_sums, parameters$value)
    temp_pars = append(temp_pars, list(parameters$par))
  }
  
  min_pos = which.min(temp_sums)
  parameters = c(temp_pars[[min_pos]], min_pos)
  
  return(parameters)
  }


#----------------------------------------------------------------------
# Funcion preliminar.

prep_it = function(train_db, full_db, qreg.quants, match_quants,
                   divisor, q25, q75){
  
  # Regresion cuantilica ----------------------------------------------

  # Incondicional.
  qreg.i = rq(formula('GDPv ~ 1'), data = train_db,
              tau = qreg.quants)
  # Condicional.
  qreg.c = rq(formula('GDPv ~ GDPv_L + NFCI_L'),
              data = train_db, tau = qreg.quants)
  
  # Estimaciones condicionales de la instancia - - - - - - - - - - - - 
  
  qreg.names = as.character(full_db$date)
  qreg.c.preds =
    t(predict(qreg.c,
              newdata = full_db[, c('GDPv_L', 'NFCI_L')])
    ) %>% as_tibble(.name_repair = 'unique')
  for(i in 1:length(qreg.names)){
    names(qreg.c.preds)[i] = qreg.names[i]
  }
  qreg.c.aux_quants = c()
  for (i in 1:length(qreg.c$tau)){
    qreg.c.aux_quants = c(qreg.c.aux_quants,
                          rep(qreg.c$tau[i], ncol(qreg.c.preds)))
  }
  qreg.c.preds = pivot_longer(qreg.c.preds, everything(),
                              names_to = 'date',
                              values_to = 'values') %>%
    mutate(date = as.Date(date))
  qreg.c.preds = mutate(qreg.c.preds, quantile = qreg.c.aux_quants)
  
  # Distribucion empirica incondicional -------------------------------
  
  # Cuantiles estimados.
  pred_vals.i = qreg.i$fitted.values[1, ]
  # Valores iniciales de los parametros distribucionales.
  initial_values.i = c(pred_vals.i[q75],
                     (pred_vals.i[q75] - pred_vals.i[q25])/divisor,
                     1e-10)
  target_y.i = pred_vals.i[ceiling(match_quants*length(qreg.quants))]
  
  skewt.i.params.est = optimize_it(initial_values.i, target_y.i,
                                   match_quants)

  return(list(skewt.i.params.est, qreg.c.preds))
}


#----------------------------------------------------------------------
# Funcion principal.

risk_it = function(i, skewt.i.params.est, qreg.c.preds, q5, q25,
                   q75, q95, divisor, match_quants, intervalues){
  
  
  # Distribucion t sesgada condicional --------------------------------
  
  pred_vals = filter(qreg.c.preds, date == i)
  initial_values.c = c(pred_vals$values[q75],
                       (pred_vals$values[q75] -
                          pred_vals$values[q25])/divisor, 1e-10)
  target_y.c = pred_vals$values[c(q5, q25, q75, q95)]
  
  skewt.c.params.est = optimize_it(initial_values.c, target_y.c,
                                   match_quants)
  
  # Ajuste distribucional realizado.
  intervalues.vals = qst(intervalues, dp = skewt.c.params.est)
  
  # Entropias ---------------------------------------------------------
  
  # Mediana condicional.
  skewt.c.med = qst(0.5, dp = skewt.c.params.est)
  # Integrando.
  entrpy.integrand = function(z){
    (log(sn::dst(z, dp = skewt.c.params.est))
     - log(sn::dst(z, dp = skewt.i.params.est)))*
      sn::dst(z, dp = skewt.c.params.est)
  }
  
  # Entropia de baja.
  entrpy.down.val =
    tryCatch(expr = {integrate(entrpy.integrand, lower = -20,
                               upper = skewt.c.med,
                               stop.on.error = FALSE)$value
    },
    error = function(e){
      for(l in floor(skewt.c.med):-20){
        if(sn::dst(l - 1, dp = skewt.c.params.est) == 0){
          break
        }
      }
      message(paste0('Domain shortened for left entropy (',
                     l, '); period ', i, '.'))
      return(integrate(entrpy.integrand, lower = l,
                       upper = skewt.c.med,
                       stop.on.error = FALSE)$value)
    })
  
  # Entropia de alza.
  entrpy.up.val =
    tryCatch(expr = {integrate(entrpy.integrand,
                               lower = skewt.c.med, upper = 20,
                               stop.on.error = FALSE)$value
    },
    error = function(e){
      for(l in ceiling(skewt.c.med):20){
        if(sn::dst(l + 1, dp = skewt.c.params.est) == 0){
          break
        }
      }
      message(paste0('Domain shortened for right entropy (', l,
                     '); periodo ', i, '.'))
      return(integrate(entrpy.integrand, lower = skewt.c.med,
                       upper = l, stop.on.error = FALSE)$value)
    })
  
  # Deficit y levantamiento esperados al 5% ---------------------------
  
  shortfall.val = (1/0.05)*sum(0.01*qst(seq(0.01, 0.05, 0.01),
                                        dp = skewt.c.params.est))
  longrise.val = (1/0.05)*sum(0.01*qst(seq(1 - 0.05, 0.99, 0.01),
                                       dp = skewt.c.params.est))
  
  return(c(list(i, entrpy.down.val, entrpy.up.val, shortfall.val,
           longrise.val), skewt.c.params.est, intervalues.vals))
}


#----------------------------------------------------------------------
# Proceso completo.

# Particion 5CVB para estimacion del poder predictivo.
partition = cvFolds(nrow(database), K = 5, type = 'consecutive')
# Percentiles a estimar con la regresion.
qreg.quants = seq(0.05, 0.95, by = 0.05)
# Auxiliares.
divisor = qnorm(0.75) - qnorm(0.25)
q95 = ceiling(0.95*length(qreg.quants))
q75 = ceiling(0.75*length(qreg.quants))
q25 = ceiling(0.25*length(qreg.quants))
q5  = ceiling(0.05*length(qreg.quants))
# Cuantiles de correspondencia entre regresion y distribucion.
match_quants = c(0.05, 0.25, 0.75, 0.95)
# Intervalos distribucionales para ilustraciones.
intervalues = c(0.010, 0.025, 0.050, 0.160, 0.250, 0.500, 0.750,
                0.840, 0.950, 0.975, 0.990)

# Travesia por las particiones.
for(K in 1:5){
  
  start_timer_2 = proc.time()
  
  # Ciclo externo -----------------------------------------------------
  partition.database = database[which(partition$which != K,
                                      arr.ind = TRUE), ]
  partition.atts.rows = nrow(partition.database)
  
  preparations = prep_it(partition.database, database, qreg.quants,
                         match_quants, divisor, q25, q75)
  
  skewt.i.params.est = preparations[[1]]
  qreg.c.preds = preparations[[2]]
  
  # Computo en paralelo - - - - - -  - - - - - - - - - - - - - - - - - 
  
  cl = makeCluster(detectCores())
  registerDoParallel(cl)
  
  results = foreach(i = as.character(database$date),
                    .combine = 'rbind',
                    .packages = c('sn', 'tidyverse')
  ) %dopar% {
    risk_it(i, skewt.i.params.est, qreg.c.preds,
            q5, q25, q75, q95, divisor,
            match_quants, intervalues)
  }
  
  stopCluster(cl)
  
  # Guardado de resultados.
  results = data.frame(results)
  colnames(results) = c('date', 'entrpy.down', 'entrpy.up',
                        'shortfall', 'longrise', 'xi', 'omega',
                        'alpha', 'nu', paste0('I', intervalues))
  write.csv(results, file = paste0(getwd(), '/Risks/risks_',
                                   K - 1, '.csv'),
            na = '', row.names = FALSE)
  
  # Ciclo interno -----------------------------------------------------
  
  partition.nested = cvFolds(partition.atts.rows,
                             K = 5, type = 'consecutive')
  
  for(k in 1:5){

    start_timer_3 = proc.time()
    partition.nested.database =
      partition.database[which(partition.nested$which != k,
                               arr.ind = TRUE), ]
    
    preparations = prep_it(partition.nested.database,
                           partition.database, qreg.quants,
                           match_quants, divisor, q25, q75)
    
    skewt.i.params.est = preparations[[1]]
    qreg.c.preds = preparations[[2]]

    cl = makeCluster(detectCores())
    registerDoParallel(cl)
    
    results = foreach(i = as.character(partition.database$date[1:3]),
                      .combine = 'rbind',
                      .packages = c('sn', 'tidyverse')
                      ) %dopar% {
                        risk_it(i, skewt.i.params.est, qreg.c.preds,
                                q5, q25, q75, q95, divisor,
                                match_quants, intervalues)
                      }
    
    stopCluster(cl)
    
    results = data.frame(results)
    colnames(results) = c('date', 'entrpy.down', 'entrpy.up',
                          'shortfall', 'longrise', 'xi', 'omega',
                          'alpha', 'nu', paste0('I', intervalues))
    write.csv(results, file = paste0(getwd(), '/Risks/risks_',
                                     K - 1, '_', k - 1, '.csv'),
              na = '', row.names = FALSE)
    
    # Indicadores de progreso.
    print(paste0('Subfold ', (K - 1)*5 + k, ' out of 25 completed.'))
    print(time_taken(proc.time()[3] - start_timer_3[3])) # ~ 50m
  }
  
  print(paste0('Fold ', K, ' out of 5 completed.'))
  print(time_taken(proc.time()[3] - start_timer_2[3])) # ~ 5h
}

print('All done.')
print(time_taken(proc.time()[3] - start_timer[3])) # ~ 26h
done()
rm(list = ls())
}
