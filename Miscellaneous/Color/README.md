# Color

## Descripción

Es un programa para catalogar el color aparente de una camiseta roja cuando se mueve a cierta velocidad. Esta determina la longitud de onda, lo que genera una ilusión de un distinto color.

##	Prerrequisitos

Se necesita tener Python (versión 3.9 en adelante). Las paqueterías utilizadas vienen por defecto en la instalación inicial del JDK.

##	Ejecución

El primer paso es descargar la carpeta en un directorio elegido. Luego, en la terminal (Windows), se utilizará el comando
    cd path="directorio\de\la\carpeta".

Es crucial asegurarse que el directorio contiene el archivo Color.py. El siguiente y último paso es ejecutar el programa con el comando
    Python Color.py
El resultado final será la palabra vermelho impresa en la terminal. Esto sucede por la implementación requerida por Beecrowd. Ver las notas adicionales.

##	Funcionamiento 

El primer paso fue definir la función principal a utilizar. Se denominó color y lleva como argumento la velocidad (entera) que mantiene el portador de la camiseta roja. Esta velocidad es ingresada a la fórmula para calcular la longitud de onda aparente observada por el observador, acorde al efecto Doppler. Luego, el valor de dicha longitud es catalogado según el intervalo donde pertenezca; hay siete en total y cada uno tiene su propio color. Si la longitud no se encuentra en ninguno, se dirá que la camiseta es invisible. Esto incluye que sea menor a 400 nm, mayor a 700, que comprenda valores complejos o haya indeterminaciones. El color resultante es regresado por la función como cadena. Ver las notas adicionales para mayor información de los posibles colores. Después, se implementó un bucle while que corre el programa siempre y cuando haya una línea nueva en la entrada. Cada una de estas líneas contiene una velocidad de entrada que será utilizada para calcular los datos ya mencionados y tener un veredicto adecuado, que será una impresión del color correspondiente.

##	Notas adicionales

El juez en línea requería –por alguna razón fuera de las instrucciones- que se imprimiera el color rojo (vermelho en portugués) cuando no hubiera líneas de entrada que analizar. También decidió necesario que se tomen en cuenta valores complejos (raíz de un número negativo) e indeterminaciones (raíz de una división sobre cero) y se les asigne la invisibilidad (invisivel en portugués).
