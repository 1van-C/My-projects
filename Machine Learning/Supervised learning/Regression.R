#------------------------------------------------------------------------------#
#                     Aprendizaje estadístico automatizado                     #
#                     Tarea-examen 3. Predicción: Versión A                    #
#                       1. Predicción en el caso continuo                      #
#                      Carlos Iván Canto Varela 315649888                      #
#------------------------------------------------------------------------------#


# Preparamiento del environment.

rm(list = ls(all.names = TRUE))
gc()

set.seed(666)

library(faraway)
library(ggplot2)
library(dplyr)
library(broom)
library(caret)
library(MASS)
library(glmnet)

# Preprocesamiento de los datos.

data(fat)

# Eliminación de variables a ignorar.

fat = subset(fat, select = -c(siri, density, free))

# Identificación de outliers mediante gráficas de dispersión.

ggplot(data = fat, mapping = aes(x = weight, y = brozek)) + 
  geom_point(color = '#880808') + 
  labs(title = 'Peso vs. porcentaje de grasa corporal',
       x = 'Peso (libras)',
       y = 'Porcentaje (%)') +
  theme_bw()

ggplot(data = fat, mapping = aes(x = height, y = brozek)) + 
  geom_point(color = '#880808') +
  labs(title = 'Altura vs. porcentaje de grasa corporal',
       x = 'Altura (pulgadas)',
       y = 'Porcentaje (%)') +
  theme_bw()

fat = fat[fat$weight <= 350, ] # Las personas de más de 350 libras son raras.

fat = fat[fat$height >= 30, ] # ¿Quién mide menos de 30 pulgadas?, ¿un bebé?,
                              # ¿un enano?

fat = fat[fat$brozek != 0, ] # Tampoco queremos gente sin grasa corporal.
                             # Alienígenas.

# Partición estratificada en cuartiles para el método de repeated holdout.

partition = createDataPartition(fat$brozek, p = 0.8, groups = 4, list = FALSE,
                                times = 50)





# i) RLM -----------------------------------------------------------------------


  # Modelo simple: sólo efectos principales.

simple_lm = lm(brozek ~ ., fat)
summary(simple_lm) # Regla.

    # Poder predictivo por repeated holdout method con B = 50.

modRHM = function(x, IndTrain, Dat, forme){
  train = IndTrain[,x]
  test = (-train)
  modtr = lm(forme, Dat[train,])
  predte = predict(modtr, Dat[test,])
  MSE = mean((Dat$brozek[test]-predte)^2)
  return(MSE)
}

MSE_simple_lm = sapply(1:50, modRHM, IndTrain = partition, Dat = fat,
                       forme = as.formula('brozek ~ .'))
(MSE_simple_lm = mean(MSE_simple_lm)) # 17.19318

  # Modelo comprehensivo: efectos principales, interacciones y cuadrados.

sqrs = paste0('I(', names(fat)[-1], '^2)') # Valores al cuadrado
# Fórmula inclusiva absoluta.
frml = as.formula(paste('brozek ~ . ^2 +', paste(sqrs, collapse = '+')))

comprehensive_lm = lm(frml, data = fat)
summary(comprehensive_lm) # Regla.

    # Poder predictivo por repeated holdout method con B = 50.

MSE_comprehensive_lm = sapply(1:50, modRHM, IndTrain = partition, Dat = fat,
                              forme = frml)
(MSE_comprehensive_lm = mean(MSE_comprehensive_lm)) # 113.9358





# ii) Selección de variables por BIC (stepwise) --------------------------------


  # Selección bidireccional con el modelo simple como base y el modelo
  # comprehensivo como límite superior con penalización BIC.

step_lm = stepAIC(simple_lm, scope = list(upper = frml, lower = ~ 1),
                  trace = FALSE, direction = 'both', k = log(nrow(fat)))
summary(step_lm) # Regla final.

  # Poder predictivo por repeated holdout method con B = 50.

mod_step_RHM = function(x, IndTrain, Dat, forme, upform){
  train = IndTrain[, x]
  test = (-train)
  assign('DatosAux', Dat[train,], envir = .GlobalEnv)
  modAux = lm(forme, data = DatosAux)
  penAux = log(dim(DatosAux)[1])
  modtr = stepAIC(modAux, scope = list(upper = upform, lower = ~ 1),
                  trace = FALSE, direction = 'both', k = penAux)
  predte = predict(modtr, Dat[test,])
  MSE = mean((Dat$brozek[test] - predte)^2)
  return(MSE)
}

MSE_step_lm = NA
for(i in 1:50){
  MSE_step_lm[i] = mod_step_RHM(i, IndTrain = partition, Dat = fat,
                                forme = as.formula('brozek ~ .'), upform = frml)
}
(MSE_step_lm = mean(MSE_step_lm)) # 18.14616





# iii) Selección de variables por lasso ----------------------------------------


  # Modelo simple.

    # Matriz para el método.

XMat1 = model.matrix(brozek ~ ., data = fat)[, -1]
Y = fat[, 'brozek']

    # Lasso con tuneo 5-CV.

lasso_fit_lm1 = cv.glmnet(XMat1, Y, nfolds = 5, type.measure = 'mse', gamma = 0,
                          relax = FALSE, family = gaussian('identity'),
                          nlambda = 50)
#lasso_fit_lm1$lambda.min
coef(lasso_fit_lm1, s = 'lambda.min') # Regla final.

    # Poder predictivo por repeated holdout method con B = 50.

mod_lasso_RHM = function(x, IndTrain, Dat, forme){
  train = IndTrain[,x]
  test = (-train)
  Xmod4ttotal = model.matrix(forme, data = Dat)[,-1]
  Xmod4t = Xmod4ttotal[train, ]
  Ymod4t = Dat[train, 'brozek'] 
  mod4t.lasso.tun = cv.glmnet(Xmod4t, Ymod4t, nfolds = 5, type.measure = 'mse',
                              gamma = 0, relax = FALSE,
                              family = gaussian('identity'), nlambda = 50)
  predte = predict(mod4t.lasso.tun, newx = Xmod4ttotal[test, ],
                   type = 'response', s = 'lambda.min')
  MSE = mean((Dat$brozek[test]-predte)^2)
  return(MSE)
}

MSE_lasso_simple_lm = sapply(1:50, mod_lasso_RHM, IndTrain = partition, Dat = fat,
                       forme = as.formula('brozek ~ .'))
(MSE_lasso_simple_lm = mean(MSE_lasso_simple_lm)) # 17.1

  # Modelo comprehensivo.

XMat2 = model.matrix(frml, data = fat)[, -1]

    # Lasso con tuneo 5-CV.

lasso_fit_lm2 = cv.glmnet(XMat2, Y, nfolds = 5, type.measure = 'mse',
                          gamma = 0, relax = FALSE,
                          family = gaussian('identity'), nlambda = 50)
#lasso_fit_lm1$lambda.min
coef(lasso_fit_lm2, s = "lambda.min") # Regla final.
summary(lm(brozek ~ abdom + age:thigh + age:ankle + age:biceps + height:neck
           + height:wrist + neck:wrist, data = fat))
coef(lm(brozek ~ abdom + age:thigh + age:ankle + age:biceps + height:neck
        + height:wrist + neck:wrist, data = fat))

    # Poder predictivo.

MSE_lasso_comprehensive_lm = sapply(1:50, mod_lasso_RHM, IndTrain = partition, Dat = fat,
                       forme = frml)
(MSE_lasso_comprehensive_lm = mean(MSE_lasso_comprehensive_lm)) # 17.0413





# iv) Selección de variables por pasos (BIC) para GLM gamma con liga identidad -


  # Modelos auxiliares para la selección de variables.

aux_gamma_fit1 = glm(brozek ~ ., data = fat, family = Gamma(link = 'identity'))
aux_gamma_fit2 = glm(frml, data = fat,   family = Gamma(link = 'identity'))

  # Selección por BIC.

gamma_fit = stepAIC(aux_gamma_fit1,
                    scope = list(upper = aux_gamma_fit2, lower = ~ 1),
                    trace = FALSE, direction = 'both', k = log(nrow(fat)))
summary(gamma_fit) # Regla final.

    # Poder predictivo por repeated holdout method con B = 50.

mod_gamma_RHM = function(x, IndTrain, Dat){
  train = IndTrain[, x]
  test = (- train)
  assign('DatosAux', Dat[train, ], envir = .GlobalEnv)
  modAuxN = glm(brozek ~ ., data = DatosAux, family = Gamma(link = 'identity'))
  modAuxF = glm(frml, data = DatosAux, family = Gamma(link = 'identity'))
  penAux = log(dim(DatosAux)[1])
  modtr = stepAIC(modAuxN, scope = list(upper = modAuxF, lower = ~ 1),
                  trace = FALSE, direction = 'both', k = penAux)
  predte = predict(modtr, Dat[test, ])
  MSE = mean((Dat$brozek[test] - predte)^2)
  return(MSE)
}

MSE_gamma_glm = NA
for(i in 1:50){
  MSE_gamma_glm[i] = mod_gamma_RHM(i, IndTrain = partition, Dat = fat)
}
# Marca error por devianza infinita o algo. Igual encuentra algunos modelos
# viables.
(MSE_gamma_glm = mean(MSE_gamma_glm)) # 18.6737





# v) Resultados ----------------------------------------------------------------


# Visualización de resultados.

ResGlob = as.data.frame(rbind(MSE_simple_lm,
                              MSE_comprehensive_lm,
                              MSE_step_lm,
                              MSE_lasso_simple_lm,
                              MSE_lasso_comprehensive_lm,
                              MSE_gamma_glm))
names(ResGlob) = c('MSE')

ResGlob

# Pinpoint de los extremos.

max = 0
min = 200

for (i in 1:nrow(ResGlob)){
  if(ResGlob[i, 1] > max){
    max = ResGlob[i, 1]
  }
  if(ResGlob[i, 1] < min){
    min = ResGlob[i, 1]
  }
}

min # RLM con selección lasso a partir del comprehensivo.
max # RLM comprehensivo.
