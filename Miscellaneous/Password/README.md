# Password

## Description

Program to validate passwords. It makes sure that they contain, at least, an upwer and lower case letter and a number. It also must be between 6 to 32 characters and not have a special character (punctuation marks, accents or spaces).

## Prerrequisites

Java (version 14 and onwards) is needed, with OpenJDK's JDK. The used libraries come by default in the initial installation of the JDK.

## Execution

The first step is to download the floder in a chosen directory. Then, at the Windows terminal, use the command
    cd path="directory\of\the\folder".
It is imperative to make sure that the directory contains the file Time.java. The next and last step is to execute it with the command
    java Time.java
The final result won't appear since it was built for the Beecrowd reader.

## Operation

First, a method is built to compare two string using ReGex. The model string was built to have an upper and lower case letter and a decimal. It is also built so it only accepts this type of characters. The method was named validatePassword.
Then, a BufferedReader reader was constructed to deal with Beecrowd's input format. A while loop was also implemented to run the program as long as the is a new line on the input. Each one of these lines is compared with the method and, if the line has the required format, the phrase "Senha valida." is printed. Otherwise, it prints "Senha invalida.". This is done for each line in the input.

#
