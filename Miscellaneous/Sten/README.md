# Sten
# LSB Stenography

## Description

Program that performs basic stenography. It can hide a message in an image or reveal a hidden message in an image using a key, as required by the least significant bit (LSB) methodology.

## Prerrequisites

Python (version 3.9 and onwards) is needed. It also requires the Pillow library and Numpy.

## Execution

The first step is to download the floder in a chosen directory. Then, at the Windows terminal, use the command
    cd path = "directory\of\the\folder".
It is imperative to make sure that the directory contains the file Color.py. The next and last step is to execute it with the command
    python Sten.py
The options to choose from will be printed on the terminal, so the user will have to input the required information in the adequate format.

## Operation

The image to use must be in the same file as the program, so when the user inputs it's name in the image, the program will be able to open it.

When the option to hide a message is selected, the program will open the image, read it and transform it into an array; the dimension of the inputs will depend on whether the png can handle RGBA or just RGB values. Then, the program will open the image and add the chosen key and an extra key for improved stenographic security. This chain will be turned into binary and it will calculate the required pixels to save the message. If the inputed image does not have enough, an exception will be raised to handle such error. Otherwise, an iterator will midify the last bit in each pixel to store the message until every character is covered, including both keys. The final array is used to create and save the image with the hidden message in the same folder.

The process is simmilar when the option to show a message is selected. First, the pixels are processed and stored into an array, then the dimension (RGB or RGBA) is used to calculate the total pixels. Each one of them has the least significant big extracted and these are stored in groups of eight. Then they are converted into ASCII characters and the key, along with the security key, is searched for inside the resulting string. Once found, the program takes the characters ti the left of it and saves them in a txt file inside the folder used.

The program was based on https://medium.com/swlh/lsb-image-steganography-using-python-2bbbee2c69a2

#
