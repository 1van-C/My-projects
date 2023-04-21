Carlos Iván Canto Varela 								      315649888

Password

* Descripción

 Es un programa para validar contraseñas. Se asegura que estas lleven al menos una letra mayúscula, una minúscula y un número; además debe tener entre 6 a 32 caracteres y no llevar ningún carácter especial (puntuación, acentos o espacios).

* Prerrequisitos

Se necesita tener Java (versión 14 en adelante) con el JDK de OpenJDK. Las paqueterías utilizadas vienen por defecto en la instalación inicial del JDK.

* Ejecución

El primer paso es descargar la carpeta en un directorio elegido. Luego, en la terminal (Windows), se utilizará el comando
cd path="directorio\de\la\carpeta".

Es crucial asegurarse que el directorio contiene el archivo Password.java. El siguiente y último paso es ejecutar el programa con el comando

java Password.java

El resultado final no se visualizará pues fue construido con la implementación de Beecrowd en mente.

* Funcionamiento 

Primero se construye un método para comparar dos cadenas mediante expresiones regulares (regex). La cadena modelo se construyó para tener una letra mayúscula, una minúscula y un decimal. Además, se filtró de modo que sólo acepte este tipo de caracteres. Este método se denominó validatePassword.

Después, se construyó un lector BufferedReader de la clase homónima para tratar los datos con el formato de entrada de Beecrowd. Se implementó un bucle while que corre el programa siempre y cuando haya una línea nueva en la entrada.

Cada una de estas líneas es comparada con el método definido anteriormente y, mediante un if, si la línea está en el formato deseado se imprime la frase “Senha valida.” De otra forma, se imprime “Senha invalida.” Esto se hace para cada línea ingresada por Beecrowd.

* Notas adicionales

El programa utilizado en Beecrowd lleva como nombre de clase “Main” por los requisitos de Beecrowd. Sin embargo, para su escritura y prueba en el sistema de origen, fue llamada “Password”.
