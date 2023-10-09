import numpy as np
from PIL import Image


def Cod(src, mens, dest, llave):
    """ Function to codify a message inside an image.

    Args:
        src (string): Name of the image to use.
        mens (string): Message to include.
        dest (string): Name to use for the codified image.
        llave (string): Key to use for the process.

    Raises:
        Exception: Exception to make sure that the user references a png image.
        Exception: Exception to let the user know that the image is too small for the intended message.
    """

    if(src.partition(".")[2] != "png"):
        raise Exception("You must use a png image.")

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
        raise Exception("Incompatibility error. You need an image with more pixels or a message with less characters.")

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
        print("Message codified.")


def Decod(src, llave, dest):
    """ Function to decodify messages hidden in png images.

    Args:
        src (string): Name of the image to use.
        llave (string): Key to use for the process.
        dest (string): Name of the txt file where the decrypted message will be stored.

    Raises:
        Exception: Exception to make sure that the user references a png image.

    Returns:
        string: Decrypted message.
        None: Returns nothing if it can't find a message.
    """


    if(src.partition(".")[2] != "png"):
        raise Exception("It must be a png image.")

    if(dest.partition(".")[2] != "txt"):
        raise Exception("You must use a txt file.")

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
        print("The hidden message was stored in the txt file.")
        with open(dest, 'w', encoding='utf-8') as f:
            f.write(mens[:-len(llave)])
            f.close
            return mens[:-len(llave)]
        
        
    else:
        print("A message wasn't found in the image.")
        return


def Stego():
    """ Main function to run the program and get the user's variables.
    """

    opt = input("Welcome. Write [h] if you would like to encrypt or [u] if you would like to decrypt a message.\n")

    while opt not in ["h", "u"]:
        opt = input("Invalid option. Write [h] if you would like to encrypt or [u] if you would like to decrypt.\n")

    if opt == 'h':
        src = input("Write the name of the image to use with it's format (.png).\n")
        mens = input("Write the message to encrypt.\n")
        dest = input("Write the name of the resulting image with it's format (png).\n")
        key = input("Write the key to use for the encryption. Make sure it is not present in the message to encrypt.\n")
        Cod(src, mens, dest, key)

    elif opt == 'u':
        src = input("Write the name of the image to use with it's format (.png).\n")
        key = input("Write the key to use for the encryption. Make sure it is not present in the message to encrypt.\n")
        dest = input("Write the name of the file where the text will be stored with it's format (.txt).\n")
        Decod(src, key, dest)


Stego()