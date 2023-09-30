import java.util.Scanner;
import static java.lang.System.*;
import static java.lang.Math.*;

public class PoliRaices {
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        double a, b, c;
        double raiz1, raiz2;
        System.out.println("Se calcularán las raíces de un polinomio de forma"
            + " Ax^2 + Bx + C.");
        System.out.println("Escribe el término A:");
        a = lector.nextDouble();
        System.out.println("Escribe el término B:");
        b = lector.nextDouble();
        System.out.println("Escribe el término C:");
        c = lector.nextDouble();
        double det = Math.pow(b,2)-4*a*c;

        // Raíces reales
        if(det > 0){
            raiz1 = (-b + Math.sqrt(det))/(2*a);
            raiz2 = (-b - Math.sqrt(det))/(2*a);
            System.out.print("Tus raíces son: " + raiz1 + " y " + raiz2 + ".");
        }

        // Raíces iguales
        else if(det == 0){
            raiz1 = raiz2 = -b/(2*a);
            System.out.print("Tu raíz es: " + raiz1 + ".");
        }

        // Raíces complejas
        else {
            double real = -b/(2*a);
            double imag = Math.sqrt(-det)/(2*a);
            System.out.print("Tus raíces son: " + real + " + " + imag + "i y " +
                real + " - " + imag + "i .");
        }
    }    
}
