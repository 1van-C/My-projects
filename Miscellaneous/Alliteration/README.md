# Alliteration

## Description

A program that detects and counts the alliterations by each line of an initial string input.

## Prerrequisites

Java is needed (version 14 or higher) with the OpenJDK JDK. The packages used come by default in the initial installing of JDK.

## Execution

The first step is to download the folder in a directory of choice. Then, in the Windows command prompt, use the command
    cd path="directorio\de\la\carpeta"
It is crucial to make sure that the directory has the file Alliteration.java.
The next and last step is to execute the program with the command
    java Alliteration.java
The final result wont be visible, as it was constructed for the Beecrowd review system.

## Operation

A BufferedReader reader was constructed to deal with the data that Beecrowd inputs. A while loop runs the program as long as there is a new line in the received input. This line is capitalized and separated by spaces; each word is introduced into a list. Here, the first two characters in each pair of immediate words is matched. If it succeeds, the alliteration counter goes up by one and the program tries matching the initial word's first letters with the following words until the matching stops. Then, the iterator i is marked at one position ahead of the last word in the alliteration and the process is repeated until the entire list (line) is done. Then, the count of alliterations is printed and it goes on with the next line until the entire input is processed.


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
