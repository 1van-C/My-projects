# Color

## Description

Program that catalogues the apparent color of a red shirt when it moves at a certain velocity. This will determine it's wave longitude, which generates an illusion of a different color.

## Prerrequisites

Python (version 3.9 and onwards) is needed. The used libraries come by default in the initial installation of the JDK.

## Execution

The first step is to download the floder in a chosen directory. Then, at the Windows terminal, use the command
    cd path="directory\of\the\folder".
It is imperative to make sure that the directory contains the file Color.py. The next and last step is to execute it with the command
    Python Color.py
The word "vermelho" will be printed at the terminal. This is required by the Beecrowd implementation — see the additional notes.

## Operation

The function color is the main function; it's only input is the speed (integer) that the wearer of the red shirt mantains. This speed is fed into the formula to calculate the apparent wavelength that is observed, according to the Doppler effect. Then, the value of such length is classified in one of the seven intervals, each for a different color. If the length does not belong in any, the shirt will be catalogued as invisible. This includes values lower than 400 nm, higher that 700 nm, complex values of indeterminations. The resulting color is returned by the function as a string. See the additional notes for more details on the possible colors.
A while loop was also implemented to run the program as long as the is a new line on the input. Each one of these lines contains a speed input to calculate with the process and receive an appropriate verdict on the color.

## Additional notes

The colors are displayed in Portuguese.
The online judge —for some unspecified reason— required the red color (vermelho) when there where no input lines to analyze. It also deemed it necessary to take complex values (imaginary numbers) and indeterminations (divisions over zero) into account, by assigning invisibility to them (invisivel).

#
