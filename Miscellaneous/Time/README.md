# Time

## Description

Program to compute the duration of a game given two times.

## Prerrequisites

Java (version 14 and onwards) is needed, with OpenJDK's JDK. The used libraries come by default in the initial installation of the JDK.

## Execution

The first step is to download the floder in a chosen directory. Then, at the Windows terminal, use the command
    cd path="directory\of\the\folder".
It is imperative to make sure that the directory contains the file Time.java. The next and last step is to execute it with the command
    java Time.java
The final result wont appear since it was built for the Beecrowd reader.

## Operation

TBD




# Funcionamiento 

Se construyó un lector BufferedReader de la clase homónima para tratar los datos con el formato de entrada de Beecrowd; también se implementó un bucle while que corre el programa siempre y cuando haya una línea nueva en la entrada Ya que la entrada es en forma de una cadena con cuatro números (dos para la hora y dos para los minutos), se toma cada número, se convierte a clase entero y se ingresa en una lista. Luego, una serie de comparaciones se hacen sobre las horas iniciales y finales (primera y tercera entrada en la lista) para determinar la operación a realizar. Después de comparar las horas, se comparan los minutos y, según el caso, se les asigna un valor a las variables hours y minutes. Estas, mediante una operación a definir por las comparaciones ya mencionadas, contienen el valor final de las horas y minutos que llevó el juego en cuestión. Finalmente, las variables anteriores son convertidas a cadenas y se imprime "O JOGO DUROU " + hours + " HORA(S) E " + minutes + " MINUTO(S)" según los requisitos de Beecrowd.
