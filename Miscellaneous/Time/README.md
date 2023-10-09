# Time

## Description

Program to compute the duration of a game given two times.

## Prerrequisites

Java (version 14 and onwards) is needed, with OpenJDK's JDK. The used libraries come by default in the initial installation of the JDK.

## Execution

The first step is to download the floder in a chosen directory. Then, at the Windows terminal, use the command
    cd path="directory\of\the\folder".
It is imperative to make sure that the directory contains the file Time.java. The next and last step is to execute it with the command
    java Time.java
The final result won't appear since it was built for the Beecrowd reader.

## Operation

A BufferedReader reader was constructed to deal with Beecrowd's input format. A while loop was also implemented to run the program as long as the is a new line on the input.
Since the entry is a string with four numbers (two for the time and two for the minutes), each number is converted to an integer and stored in a list. Then, a series of comparisons are done with the initial and final times to determine the operation to realize. After comparing hours, the minutes follow and, according to each case, a value is assigned to the hours and minutes variables. Using a defined algorithm, these contain the final value of the hours and minutes that the game in question lasted.
Finally, the previous variables are converted to strings and it prints the output: "O JOGO DUROU " + hours + " HORA(S) E " + minutes + " MINUTO(S)", as the Beecrowd requirements state.

#
