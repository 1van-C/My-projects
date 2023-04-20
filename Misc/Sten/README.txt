Carlos Iván Canto Varela 								      315649888

Esteganografía por LSB

* Descripción

Es un programa que realiza esteganografía básica. Puede ocultar un mensaje en una imagen de o revelar un mensaje oculto en una imagen de acuerdo a una clave con la metodología del bit menos significativo.

* Prerrequisitos

Se necesita tener Python (se utilizó la versión 3.9). Además se requiere haber instalado la paquetería Pillow en el ambiente a utilizar y tener Numpy.

* Ejecución

El primer paso es descargar la carpeta en un directorio elegido. Luego, en la terminal (Windows), se utilizará el comando
cd path="directorio\de\la\carpeta".

Es crucial asegurarse que el directorio contiene el archivo Color.py. El siguiente y último paso es ejecutar el programa con el comando

python Sten.py

Las opciones a utilizar se verán impresas en la terminal, por lo que el usuario deberá ingresar la información requerida en el formato adecuado.

* Funcionamiento 

Se requiere que la imagen a utilizar se encuentre en la misma carpeta que el programa. Así, cuando el usuario ingrese su nombre de la imagen el programa la podrá abrir.

   Cuando se elige la opción de ocultar un mensaje, el programa abrirá la imagen y procederá a leerla y transformarla en un arreglo; la dimensión de las entradas dependerá si el png soporta los valores RGBA o sólo RGB. Luego, se toma el mensaje a ocultar y se le añade la llave elegida y una llave extra para mayor seguridad estenográfica. Esta cadena se convierte entonces a binario y se calculan los pixeles requeridos para guardar el mensaje. Si la imagen ingresada no tiene los suficientes se alzará una excepción para manejar este error. De otra manera, un iterador midificará el último bit de cada pixel para guardar el mensaje hasta que no falte ningún caracter, incluidas ambas llaves. El arreglo final es entonces utilizado para crear y guardar la imagen con el mensaje oculto en la misma carpeta del programa.
   
   El proceso es similar cuando se elige la opción de develar un mensaje. Primero se leen y guardan los pixeles de la imagen elegida en un arreglo, se toma su dimensión (RGB o RGBA) y se calculan los pixeles totales. A cada pixel se le extrae el bit menos significativo y estos se guardan en grupos de ocho. Luego son convertidos en caracteres ASCII y se busca la llave (junto con la llave de seguridad del programa) dentro de la cadena resultante. Una vez encontrada, el programa toma los caracteres a la izquierda de ella y los guarda en un documento de texto (txt) dentro de la carpeta del programa.

* Notas adicionales

Mi equipo es de uno y estoy atiborrado de tareas y trabajos, perdón por no hacerle una interfaz gráfica. Ténganme piedad. :(






Trabajo con base en https://medium.com/swlh/lsb-image-steganography-using-python-2bbbee2c69a2