/**
 * Simulation of a game of dice with certain specifications.
 * @author Ivan C.
 * @version Octubre, 2020.
 */

import java.util.Scanner;

public class Dados {
    
    public static void main(String[] args){
        // Definition of the players' scores.
        int player1 = 0;
        int player2 = 0;
        int house = 0;
        // Variable declaration for the players' names.
        String name;
        Scanner input = new Scanner(System.in);
        // Saves the names of the players.
        System.out.println("Welcome to the casino. Write the name for player 1: ");
        String inp1 = input.nextLine();
        System.out.println("Now the name for player 2: ");
        String inp2 = input.nextLine();
        // The ten throws are enumerated.
        for(int i = 0; i<10; i++){
            // Individual throws for each player.
            for(int j=0; j<2; j++){
                // Calculation of the random throws.
                int random_int1 = (int)(Math.random()*6 + 1);
                int random_int2 = (int)(Math.random()*6 + 1);
                // Score assignation for the first player.
                if (j==0){
                    name = inp1;
                    int sum = random_int1 + random_int2;
                    switch(sum) {
                        case 2:
                          player1 -= 1;
                          break;
                        case 3:
                          player1 -= 1;
                          break;
                        case 7:
                          player1 += 2;
                          break;
                        case 11:
                          player1 += 2;
                          break;
                        case 12:
                          house += 1;
                        break;
                    }
                    System.out.println("The throw #" + (i+1) + " of the player " + name + " was " + random_int1 + " and "
                            + random_int2 + ".");
                }
                // Score assignation for the second player.
                else{
                    name = inp2;
                    int sum = random_int1 + random_int2;
                    switch(sum){
                        case 2:
                          player2 -= 1;
                          break;
                        case 3:
                          player2 -= 1;
                          break;
                        case 7:
                          player2 += 2;
                          break;
                        case 11:
                          player2 += 2;
                          break;
                        case 12:
                          house += 1;
                        break;
                    }
                    System.out.println("The throw #" + (i+1) + " of the player " + name + " was " + random_int1 + " and "
                            + random_int2 + ".");
                }
            }
        }
    // Total scores.
    System.out.println("Next up, the final scores.");
    System.out.println(inp1 + ": " + player1 + ".");
    System.out.println(inp2 + ": " + player2 + ".");
    System.out.println("House: " + house + ".");
    // The result of the game is determined and the winner (if there is one) is announced.
    if (player1 > player2 && player1 > house){
        System.out.println("The winner is: " + inp1.toUpperCase() + ".");
    }
    else if (player2 > player1 && player2 > house){
        System.out.println("The winner is: " + inp2.toUpperCase() + ".");
    }
    else if (house > player1 && house > player2){
        System.out.println("The HOUSE wins.");
    }
    else if (player1 == player2 && player2 == house){
        System.out.println("It's a tie.");
    }
    else if (player1 > player2 && player1 == house){
        System.out.println("It's a tie.");
    }
    else if (player2 > player1 && player2 == house){
        System.out.println("It's a tie.");
    }
    }
}