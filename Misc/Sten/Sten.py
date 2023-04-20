import numpy as np
from PIL import Image


def Cod(src, mens, dest, llave):
    """ Función para codificar un mensaje en una imagen.

    Args:
        src (string): nombre de la imagen a codificar.
        mens (string): mensaje a incluir.
        dest (string): nombre a usar para la imagen cofidicada.
        llave (string): clave a utilizar para la codificación.

    Raises:
        Exception: excepción utilizada para asegurarse que el usuario referencíe una imagen con formato png.
        Exception: excepción para denotar que la imagen es insuficiente para el mensaje ingresado.
    """

    if(src.partition(".")[2] != "png"):
        raise Exception("La imagen a codificar debe tener formato png.")

    img = Image.open(src, 'r')
    width, height = img.size
    array = np.array(list(img.getdata()))

    if img.mode == 'RGB':
        n = 3
    elif img.mode == 'RGBA':
        n = 4
    pix_tot = array.size//n

    llave = llave + "$0$sCHp"

    mens += llave

    b_mens = ''.join([format(ord(i), "08b") for i in mens])
    pix_req = len(b_mens)
    
    if (pix_req > pix_tot):
        raise Exception("Incompatibilidad: se necesita una imagen con más pixeles o un mensaje con menos caracteres.")

    else:
        index = 0
        for i in range(pix_tot):
            for j in range(0, 3):
                if index < pix_req:
                    array[i][j] = int(bin(array[i][j])[2:9] + b_mens[index], 2)
                    index += 1

        array = array.reshape(height, width, n)
        img_cod = Image.fromarray(array.astype('uint8'), img.mode)
        img_cod.save(dest)
        print("Mensaje codificado.")


def Decod(src, llave, dest):
    """ Función para decodificar mensajes ocultos en imágenes png.

    Args:
        src (string): nombre de la imagen a decodificar.
        llave (string): clave a utilizar para la decodificación.
        dest (string): nombre del archivo de texto (.txt) donde se guardará el mensaje decriptado.

    Raises:
        Exception: excepción utilizada para asegurarse que el usuario referencíe una imagen con formato png.

    Returns:
        string: Mensaje decriptado.
        None: No regresa nada cuando no encuentra un mensaje.
    """


    if(src.partition(".")[2] != "png"):
        raise Exception("La imagen a decodificar debe tener formato png.")

    if(dest.partition(".")[2] != "txt"):
        raise Exception("El archivo destino debe tener formato txt.")

    img = Image.open(src, 'r')
    array = np.array(list(img.getdata()))

    if img.mode == 'RGB':
        n = 3
    elif img.mode == 'RGBA':
        n = 4
    pix_tot = array.size//n

    bits_oc = ""
    for i in range(pix_tot):
        for j in range(0, 3):
            bits_oc += (bin(array[i][j])[2:][-1])

    bits_oc = [bits_oc[i:i+8] for i in range(0, len(bits_oc), 8)]

    llave = llave + "$0$sCHp"

    mens = ""
    for i in range(len(bits_oc)):
        if mens[-len(llave):] == llave:
            break
        else:
            mens += chr(int(bits_oc[i], 2))

    if llave in mens:
        print("El mensaje oculto se guardó en el archivo.")
        with open(dest, 'w', encoding='utf-8') as f:
            f.write(mens[:-len(llave)])
            f.close
            return mens[:-len(llave)]
        
        
    else:
        print("No se encontró un mensaje en la imagen.")
        return


def Stego():
    """ Función main para correr el programa y conseguir las variables del usuario.
    """

    opt = input("Bienvenido. Ingresa h si quieres encriptar o u si quieres decifrar.\n")

    while opt not in ["h", "u"]:
        opt = input("Opción inválida. Ingresa h si quieres encriptar o u si quieres decifrar.\n")

    if opt == 'h':
        src = input("Ingresa el nombre de la imagen a utilizar con su formato (.png).\n")
        mens = input("Ingresa el mensaje a encriptar.\n")
        dest = input("Ingresa el nombre de la imagen resultante con su formato (png).\n")
        key = input("Ingresa la llave a usar para el encriptado. Asegúrate que no se encuentre en el mensaje a encriptar.\n")
        Cod(src, mens, dest, key)

    elif opt == 'u':
        src = input("Ingresa el nombre de la imagen a utilizar con su formato (.png).\n")
        key = input("Ingresa la llave a usar para el decriptado.\n")
        dest = input("Ingresa el nombre del archivo en el que se guardará el texto con su formato (.txt).\n")
        Decod(src, key, dest)


Stego()