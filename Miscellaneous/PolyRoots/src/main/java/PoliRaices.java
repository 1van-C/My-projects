import java.util.Scanner;
import static java.lang.System.*;
import static java.lang.Math.*;

public class PoliRaices {
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        double a, b, c;
        double raiz1, raiz2;
        System.out.println("The roots of a polynomial Ax^2 + Bx + C will be computed.");
        System.out.println("Write the A term:");
        a = lector.nextDouble();
        System.out.println("Write the B term:");
        b = lector.nextDouble();
        System.out.println("Write the C term:");
        c = lector.nextDouble();
        double det = Math.pow(b,2)-4*a*c;

        // Real roots.
        if(det > 0){
            raiz1 = (-b + Math.sqrt(det))/(2*a);
            raiz2 = (-b - Math.sqrt(det))/(2*a);
            System.out.print("The roots are: " + raiz1 + " y " + raiz2 + ".");
        }

        // Matching roots.
        else if(det == 0){
            raiz1 = raiz2 = -b/(2*a);
            System.out.print("The roots are: " + raiz1 + ".");
        }

        // Complex roots.
        else {
            double real = -b/(2*a);
            double imag = Math.sqrt(-det)/(2*a);
            System.out.print("The roots are: " + real + " + " + imag + "i y " +
                real + " - " + imag + "i .");
        }
    }    
}
