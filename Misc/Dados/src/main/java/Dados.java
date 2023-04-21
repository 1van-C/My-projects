/**
 * Simulacion de un juego de dados con las especificaciones dadas.
 * @author Ivan C.
 * @version Octubre, 2020.
 */

import java.util.Scanner;

public class Dados {
    
    public static void main(String[] args){
        // Se definen los puntajes de los jugadores.
        int player1 = 0;
        int player2 = 0;
        int house = 0;
        // Declarar las variables pertinentes al nombre de los jugadores.
        String name;
        Scanner input = new Scanner(System.in);
        // Guardar los nombres de los jugadores.
        System.out.println("Bienvenidos al casino. Ingresa el nombre del jugador 1: ");
        String inp1 = input.nextLine();
        System.out.println("Ahora el nombre del jugador 2: ");
        String inp2 = input.nextLine();
        // Se numeran los diez tiros.
        for(int i = 0; i<10; i++){
            // Tiros individuales de cada jugador.
            for(int j=0; j<2; j++){
                // Calculo de los tiros aleatorios.
                int random_int1 = (int)(Math.random()*6 + 1);
                int random_int2 = (int)(Math.random()*6 + 1);
                // Asignacion de puntaje para el primer jugador.
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
                    System.out.println("El tiro #" + (i+1) + " del jugador " + name + " fue " + random_int1 + " y "
                            + random_int2 + ".");
                }
                // Asignacion del puntaje para el segundo jugador.
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
                    System.out.println("El tiro #" + (i+1) + " del jugador " + name + " fue " + random_int1 + " y "
                            + random_int2 + ".");
                }
            }
        }
    // Aviso de los puntajes totales.
    System.out.println("A continuacion, los puntajes finales.");
    System.out.println(inp1 + ": " + player1 + ".");
    System.out.println(inp2 + ": " + player2 + ".");
    System.out.println("Computadora: " + house + ".");
    //Se determina el resultado del juego y se anuncia el ganador (si lo hay).
    if (player1 > player2 && player1 > house){
        System.out.println("El ganador es: " + inp1.toUpperCase() + ".");
    }
    else if (player2 > player1 && player2 > house){
        System.out.println("El ganador es: " + inp2.toUpperCase() + ".");
    }
    else if (house > player1 && house > player2){
        System.out.println("El ganador es la COMPUTADORA.");
    }
    else if (player1 == player2 && player2 == house){
        System.out.println("EMPATE.");
    }
    else if (player1 > player2 && player1 == house){
        System.out.println("EMPATE.");
    }
    else if (player2 > player1 && player2 == house){
        System.out.println("EMPATE.");
    }
    }
}