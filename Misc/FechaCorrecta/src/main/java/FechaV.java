/**
 *Ejercicio 4 de la tarea: determinar si una fecha ingresada es correcta.
 * @author IvanC
 * @version Marzo 2020
 */
import java.util.Scanner;
import static java.lang.System.*;

public class FechaCorrecta {
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        int d, m;
        long a;
        System.out.println("Se verificará si cualquier fecha de formato "
                + "DD/MM/AAAA ingresada es válida.");
        System.out.println("Escribe el día: ");
        d = lector.nextInt();
        System.out.println("Escribe el mes: ");
        m = lector.nextInt();
        System.out.println("Escribe el año: ");
        a = lector.nextLong();
        if (d < 1 || m < 1 || a < 1){
            System.out.println("La fecha es inválida, pedazo de alcornoque."
                    + " ¿No sabes que no hay dí­as, meses ni años negativos?");
        }
        else if(m > 12){
            System.out.println("La fecha es inválida, sólo hay doce meses. Esfúmate.");
        }
        else if(d > 31){
            System.out.println("La fecha es inválida, no hay meses con más de 31 dí­as. Tonto.");
        }
        else{
            if (d > 28){
                if(m == 2){
                    System.out.println("La fecha es inválida, febrero tiene 28"
                            + " dí­as. Vuelve a la primaria.");
                }
                else{
                    if(d > 30){
                        if(m == 4 || m == 6 || m == 9 || m == 11){
                            System.out.println("La fecha es inválida, ese mes sólo"
                                    + " tiene 30 días. No quiero verte jamás.");
                        }
                        else{
                            System.out.println("La fecha es válida. Bien hecho.");
                        }                    
                    }
                }
            }
            else{
                System.out.println("La fecha es válida. Bien hecho.");
            }
        }
    }    
}