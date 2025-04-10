#------------------------------------------------------------------------------#
#                     Aprendizaje estadístico automatizado                     #
#                     Tarea-examen 3. Predicción: Versión A                    #
#                         2. Clasificación supervisada                         #
#                      Carlos Iván Canto Varela 315649888                      #
#------------------------------------------------------------------------------#


# Preparamiento del environment.

rm(list = ls(all.names = TRUE))
gc()

set.seed(666)

library(mlbench)
library(ggplot2)
library(dplyr)
library(caret)
library(tibble)
library(corrplot)
library(factoextra)
library(metrica)
library(MASS)
library(glmnet)
library(e1071)
library(class)
library(randomForest)

# Preprocesamiento de los datos.

data(PimaIndiansDiabetes2)
Data = PimaIndiansDiabetes2

# Eliminación de variables con datos incompletos.

Data = na.omit(Data)

# Partición por 5-CV.

partition = createFolds(Data$diabetes, k = 5, list = FALSE, returnTrain = FALSE)





# i) Visualizaciones de variables predictoras ----------------------------------


for (i in 1:(ncol(Data) - 1)){
  print(Data %>% ggplot(aes(x = diabetes, y = pregnant, fill = diabetes,
                            colour = diabetes)) +
          geom_boxplot() +
          scale_fill_manual(values = c('#880808','#280137')) +
          scale_colour_manual(values = c('pos' = 'white', 'neg' = 'black')) +
          geom_boxplot(color = 'black', fill = NA, fatten = 0) +
          labs(
            title = paste('Visualización para', colnames(Data)[i]),
            x = 'Diabetes',
            y = colnames(Data)[i]))
}





# ii) Componentes principales --------------------------------------------------


  # Dataframe auxiliar.

aux_Data = Data %>% dplyr::select(-c('diabetes'))
aux_Data %>% cor(method = 'pearson') %>% round(digits = 3)

  # Componentes principales escalados.

prin_comp = prcomp(scale(aux_Data, scale = TRUE), scale = FALSE)

    # Variabilidad explicada.

fviz_screeplot(prin_comp, addlabels = TRUE)

    # Influencia sobre los componentes por variable.

cbind(prin_comp$x[,1:4], scale(aux_Data, scale = TRUE)) %>% cor() %>% 
      round(3) %>% data.frame() %>% dplyr::select(c('PC1', 'PC2', 'PC3', 'PC4')) %>% 
      slice(6:n()) %>% rownames_to_column(var = 'Covariable') 

fviz_contrib(prin_comp, choice = 'var', axes = 1)
fviz_contrib(prin_comp, choice = 'var', axes = 2)
fviz_contrib(prin_comp, choice = 'var', axes = 3)

    # Diagramas de dispersión.

fviz_pca_biplot(prin_comp, habillage = Data$diabetes, label = 'var',
                title = 'Diagrama de dispersión entre los CP 1 y 2'
                ) + theme_bw()

fviz_pca_biplot(prin_comp, axes = c(1,3), habillage = Data$diabetes,
                label = 'var',
                title = 'Diagrama de dispersión entre los CP 1 y 3'
                ) + theme_bw()

fviz_pca_biplot(prin_comp, axes = c(2,3), habillage = Data$diabetes,
                label = 'var',
                title = 'Diagrama de dispersión entre los CP 2 y 3'
                ) + theme_bw()





# iii) MLG binario liga logit --------------------------------------------------


  # Modelo simple: sólo efectos principales.

simple_fit = glm(diabetes ~ ., data = Data, family = binomial(link = 'logit'))
summary(simple_fit) # Regla.

    # Poder predictivo por 5-CV.

modRHM = function(x, IndTest, Dat, forme){
  test = which(IndTest == x)
  train = (-test)
  modtr = glm(forme, Dat[train, ], family = binomial(link = 'logit'))
  preda = predict(modtr, newdata = Dat[test, ], type = 'response')
  predb = ifelse(preda >= 0.5, levels(Dat$diabetes)[2], levels(Dat$diabetes)[1])
  resPod = metrics_summary(obs = Dat[test, 'diabetes'], pred = predb,
                           metrics_list = c('accuracy', 'recall',
                                            'specificity'),
                           type = 'classification')
  return(resPod[, 2])
}

TCC_simple_fit = sapply(1:5, modRHM, IndTest = partition, Dat = Data,
                        forme = as.formula('diabetes ~ .'))
(TCC_simple_fit = rowMeans(TCC_simple_fit)) # 0.7757 0.5692 0.8784


  # Modelo comprehensivo: efectos principales, interacciones y cuadrados.

sqrs = paste0('I(', names(aux_Data)[-1], '^2)') # Valores al cuadrado
# Fórmula inclusiva absoluta.
frml = as.formula(paste('diabetes ~ . ^2 +', paste(sqrs, collapse = '+')))

comprehensive_fit = glm(frml, data = Data, family = binomial(link = 'logit'))
summary(comprehensive_fit) # Regla.

    # Poder predictivo por 5-CV.

TCC_comprehensive_fit = sapply(1:5, modRHM, IndTest = partition, Dat = Data,
                               forme = frml)
(TCC_comprehensive_fit = rowMeans(TCC_comprehensive_fit)) # 0.7323 0.5538 0.8211



  

# iv) Selección de variables por BIC (stepwise) --------------------------------


  # Selección bidireccional con el modelo simple como base y el modelo
  # comprehensivo como límite superior con penalización BIC.

step_fit = stepAIC(simple_fit, scope =list(upper = frml, lower = ~1),
                   trace = FALSE, direction = 'both', k = log(nrow(Data)))
summary(step_fit) # Regla final.

    # Poder predictivo por 5-CV.

mod_step_RHM = function(x, IndTest, Dat){
  test = which(IndTest == x)
  train = (-test)
  assign('auxx_Data', Dat[train,], envir = .GlobalEnv)
  modAux = glm(diabetes ~ ., data = auxx_Data,
               family = binomial(link = 'logit'))
  penAux = log(nrow(auxx_Data))
  modtr = stepAIC(modAux, scope = list(upper = frml, lower = ~ 1),
                  trace = FALSE, direction = 'both', k = penAux)
  preda = predict(modtr, newdata = Dat[test,], type = 'response')
  predb = ifelse(preda >= 0.5, levels( Dat$diabetes)[2],
                 levels( Dat$diabetes)[1])
  resPod = metrics_summary(obs = Dat[test, 'diabetes'], pred = predb,
                           metrics_list = c('accuracy', 'recall',
                                            'specificity'),
                           type = 'classification')
  return(resPod[, 2])
}

TCC_step_fit = matrix(NA, ncol = 5, nrow = 3)
for(i in 1:5){
  TCC_step_fit[, i] = mod_step_RHM(i, IndTest = partition, Dat = Data)
}
(TCC_step_fit = rowMeans(TCC_step_fit)) # 0.7833 0.6154 0.8668





# v) MLG binomial con liga probit y selección lasso tuneada con 5-CV -----------


  # Matriz auxiliar con cuadrados e interacción.

XMat = model.matrix(frml, data = Data)[, -1]
Y = Data[, 'diabetes'] 

  # Lasso con tuneo.

lasso_fit = cv.glmnet(XMat, Y, nfolds = 5, type.measure = 'class', gamma = 0,
                      relax = FALSE, family = binomial(link = 'probit'),
                      nlambda = 50)
#lasso_fit$lambda.min
coef(lasso_fit, s = "lambda.min") # Regla final.

    # Poder predictivo

mod_lasso_RHM=function(x, IndTest, Dat){
  test = which(IndTest == x)
  train = (-test)
  Xmod4ttotal = model.matrix(frml, data = Dat)[,-1]
  Xmod4t = Xmod4ttotal[train, ]
  Ymod4t = Dat[train, 'diabetes'] 
  mod4t.lasso.tun = cv.glmnet(Xmod4t, Ymod4t, nfolds = 5,
                              type.measure = 'class', gamma = 0, relax = FALSE,
                              family = binomial(link = 'probit'), nlambda = 50)
  preda = predict(mod4t.lasso.tun, newx = Xmod4ttotal[test, ],
                  type = 'response', s = 'lambda.min')
  predb = ifelse(preda >= 0.5, levels(Dat$diabetes)[2], levels(Dat$diabetes)[1])
  resPod = metrics_summary(obs = Dat[test, 'diabetes'], pred = predb,
                           metrics_list = c('accuracy', 'recall',
                                            'specificity'),
                           type = 'classification')
  return(resPod[,2])
}

TCC_lasso_fit = sapply(1:5, mod_lasso_RHM, IndTest = partition, Dat = Data)
(TCC_lasso_fit = rowMeans(TCC_lasso_fit)) # 0.7935 0.5615 0.9087





# vi) Naive classifier, LDA, QDA y K-NN ----------------------------------------


  # Naive classifier.

naive_fit = naiveBayes(diabetes ~ ., Data)

    # Poder predictivo por 5-CV.

mod_naive_RHM = function(x, IndTest, Dat){
  test = which(IndTest == x)
  train = (-test)
  modt <-  naiveBayes(diabetes ~ ., Dat[train,])
  predb = predict(modt, Dat[test,])
  resPod = metrics_summary(obs = Dat[test, 'diabetes'], pred = predb,
                           metrics_list = c('accuracy', 'recall',
                                            'specificity'),
                           type = 'classification')
  return(resPod[,2])
}

TCC_naive = sapply(1:5, mod_naive_RHM, IndTest = partition, Dat = Data)
(TCC_naive = rowMeans(TCC_naive)) # 0.7578 0.6231 0.8248


  # LDA.

lda_fit = lda(diabetes ~ ., Data)

    # Poder predictivo por 5-CV.

mod_wda_RHM = function(x, IndTest, Dat, w){
  test = which(IndTest == x)
  train = (-test)
  if (w == 1){
    mod5t = lda(diabetes ~ ., Dat[train,])
  }
  else {
    mod5t = qda(diabetes ~ ., Dat[train,])
  }
  mod5pt = predict(mod5t, Dat[test,])
  predb = mod5pt$class
  resPod = metrics_summary(obs = Dat[test, 'diabetes'], pred = predb,
                           metrics_list = c('accuracy', 'recall',
                                            'specificity'),
                           type = 'classification')
  return(resPod[,2])
}

TCC_lda = sapply(1:5, mod_wda_RHM, IndTest = partition, Dat = Data, w = 1)
(TCC_lda = rowMeans(TCC_lda)) # 0.7757 0.5615 0.8821


  # QDA.

qda_fit = qda(diabetes ~ ., Data)

    # Poder predictivo por 5-CV.

TCC_qda = sapply(1:5, mod_wda_RHM, IndTest = partition, Dat = Data, w = 2)
(TCC_qda = rowMeans(TCC_qda)) # 0.7783 0.6308 0.8515


  # K-NN con tuneo.

XMatt = model.matrix(diabetes ~ ., data = Data)[, -1]
knn.cross = tune.knn(x = XMatt, y = Y, k = 1:20,
                     tunecontrol = tune.control(sampling = 'cross'), cross = 5)

    # Predicciones.

knn_fit = knn(train = XMatt, test = XMatt, Y,
              k = knn.cross$best.parameters[[1]], use.all = TRUE)

    # Poder predictivo por 5-CV.

mod_knn_RHM = function(x, IndTest, Dat){
  test = which(IndTest == x)
  train = (-test)
  Xmod8ttotal = model.matrix(diabetes~ ., data = Dat)[,-1]
  Xmod8t = Xmod8ttotal[train, ]
  Xmod8test = Xmod8ttotal[test, ]
  Ymod8t = Dat[train, 'diabetes'] 
  knn.crosst = tune.knn(x = Xmod8t, y = Ymod8t, k = 1:20,
                        tunecontrol = tune.control(sampling = 'cross'),
                        cross = 5)
  predb = knn(train = Xmod8t, test = Xmod8test, Ymod8t,
              k = knn.crosst$best.parameters[[1]], use.all = TRUE)
  resPod = metrics_summary(obs = Dat[test, 'diabetes'], pred = predb,
                           metrics_list = c('accuracy', 'recall',
                                            'specificity'),
                           type = 'classification')
  return(resPod[,2])
}

TCC_knn = sapply(1:5, mod_knn_RHM, IndTest = partition, Dat = Data)
(TCC_knn = rowMeans(TCC_knn)) # 0.7733 0.5462 0.8858


  # Random forest de 200 árboles con mtry tuneado por 5-CV.

net_mtry = seq(1, 8, 2)
rfort = tune.randomForest(diabetes ~ ., data = Data, importance = F,
                          mtry = net_mtry, ntree = 200,
                          tunecontrol = tune.control(sampling = 'cross',
                                                     cross = 5))

random_forest_fit = randomForest(diabetes ~ ., data = Data,
                                 mtry = rfort$best.parameters[[1]],
                                 importance = F, ntree = 200)

print(random_forest_fit)
round(importance(random_forest_fit), 2)

    # Poder predictivo por 5-CV.

mod_random_forest_RHM = function(x, IndTest, Dat){
  test = which(IndTest == x)
  train = (-test)
  tunRFt5CV = tune.randomForest(diabetes ~ ., data = Dat[train, ],
                                importance = F, mtry = net_mtry, ntree = 200,
                                tunecontrol = tune.control(sampling = 'cross',
                                                           cross = 5))
  RFt = randomForest(diabetes ~ ., data = Dat[train, ],
                     mtry = tunRFt5CV$best.parameters[[1]], importance = F,
                   ntree = 200)
  predb = predict(RFt, newdata = Dat[test, ], type = 'class')
  resPod = metrics_summary(obs = Dat[test, 'diabetes'], pred = predb,
                           metrics_list = c('accuracy', 'recall',
                                            'specificity'),
                           type = 'classification')
  return(resPod[,2])
}

TCC_random_forest = sapply(1:5, mod_random_forest_RHM, IndTest = partition,
                           Dat = Data)
(TCC_random_forest = rowMeans(TCC_random_forest)) # 0.7629 0.5769 0.8554





# viii) Resultados -------------------------------------------------------------


  # Visualización de resultados.

ResGlob = as.data.frame(rbind(TCC_simple_fit,
                            TCC_comprehensive_fit,
                            TCC_step_fit,
                            TCC_lasso_fit,
                            TCC_naive,
                            TCC_lda,
                            TCC_qda,  
                            TCC_knn,
                            TCC_random_forest))
names(ResGlob) = c('accuracy', 'recall', 'specificity')

ResGlob

  # Pinpoint de los extremos.

max = 0
min = 1
for (i in 1:nrow(ResGlob)){
  if(rowMeans(ResGlob[i, ]) > max){
    max = rowMeans(ResGlob[i, ])
  }
  if(rowMeans(ResGlob[i, ]) < min){
    min = rowMeans(ResGlob[i, ])
  }
}

min # MLG con interacciones y cuadrados
max # MLG seleccionado por pasos con criterio BIC, inicio en efectos principales
    # y límites inferior de sólo el intercepto y superior el comprehensivo.
