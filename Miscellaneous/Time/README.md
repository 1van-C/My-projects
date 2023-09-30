# Time

## Descripción

Programa para calcular la duración de un juego con dos horas como entrada.

## Prerrequisitos

Se necesita tener Java (versión 14 en adelante) con el JDK de OpenJDK. Las paqueterías utilizadas vienen por defecto en la instalación inicial del JDK.

## Ejecución

El primer paso es descargar la carpeta en un directorio elegido. Luego, en la terminal (Windows), se utilizará el comando
    cd path="directorio\de\la\carpeta".
Es crucial asegurarse que el directorio contiene el archivo Time.java. El siguiente y último paso es ejecutar el programa con el comando
    java Time.java
El resultado final no se visualizará pues fue construido con la implementación de Beecrowd en mente.

# Funcionamiento 

Se construyó un lector BufferedReader de la clase homónima para tratar los datos con el formato de entrada de Beecrowd; también se implementó un bucle while que corre el programa siempre y cuando haya una línea nueva en la entrada Ya que la entrada es en forma de una cadena con cuatro números (dos para la hora y dos para los minutos), se toma cada número, se convierte a clase entero y se ingresa en una lista. Luego, una serie de comparaciones se hacen sobre las horas iniciales y finales (primera y tercera entrada en la lista) para determinar la operación a realizar. Después de comparar las horas, se comparan los minutos y, según el caso, se les asigna un valor a las variables hours y minutes. Estas, mediante una operación a definir por las comparaciones ya mencionadas, contienen el valor final de las horas y minutos que llevó el juego en cuestión. Finalmente, las variables anteriores son convertidas a cadenas y se imprime "O JOGO DUROU " + hours + " HORA(S) E " + minutes + " MINUTO(S)" según los requisitos de Beecrowd.
