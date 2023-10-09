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

#
