Carlos Iván Canto Varela

# Alliteration

## Descripción

Es un programa que detecta y cuenta las aliteraciones por línea de una entrada de cadena inicial.

## Prerrequisitos

Se necesita tener Java (versión 14 en adelante) con el JDK de OpenJDK. Las paqueterías utilizadas vienen por defecto en la instalación inicial del JDK.

## Ejecución
El primer paso es descargar la carpeta en un directorio elegido. Luego, en la terminal (Windows), se utilizará el comando
    cd path="directorio\de\la\carpeta"
Es crucial asegurarse que el directorio contiene el archivo Alliteration.java. El siguiente y último paso es ejecutar el programa con el comando
    java Alliteration.java
El resultado final no se visualizará pues fue construido con la implementación de Beecrowd en mente.

## Funcionamiento

El procedimiento fue construir un lector BufferedReader de la clase homónima para tratar los datos con el formato de entrada de Beecrowd. Se implementó un bucle while que corre el programa siempre y cuando haya una línea nueva en la entrada. Esta línea es entonces convertida a mayúsculas y separada por espacios; cada palabra es introducida como entrada a una lista. Una vez aquí, se comparan los primeros caracteres de dos en dos palabras inmediatas. Si hay match, el contador de aliteraciones incrementa en uno y el programa procede a comparar las primeras letras de la palabra inicial con las palabras subsecuentes hasta que deje de haber match. Una vez aquí, el iterador i es marcado en una posición adelante de la última palabra en la aliteración y el proceso se repite hasta acabar con la lista (línea). Entonces se imprime la cuenta de aliteraciones y se sigue con la siguente línea hasta acabar con la entrada proporcionada por Beecrowd.
