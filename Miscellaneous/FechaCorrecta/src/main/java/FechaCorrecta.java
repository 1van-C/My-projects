import java.util.Scanner;
import static java.lang.System.*;

public class FechaCorrecta {
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        int d, m;
        long a;
        System.out.println("The validity of a date with format DD/MM/YYYY will be checked.");
        System.out.println("Write the day: ");
        d = lector.nextInt();
        System.out.println("Write the month: ");
        m = lector.nextInt();
        System.out.println("Write the year: ");
        a = lector.nextLong();
        if (d < 1 || m < 1 || a < 1){
            System.out.println("The date is invalid. There are no negative days, months or years.");
        }
        else if(m > 12){
            System.out.println("The date is invalid. There are only 12 months.");
        }
        else if(d > 31){
            System.out.println("The date is invalid. No month has more than 31 days.");
        }
        else{
            if (d > 28){
                if(m == 2){
                    System.out.println("The date is invalid. February only has 28 days.");
                }
                else{
                    if(d > 30){
                        if(m == 4 || m == 6 || m == 9 || m == 11){
                            System.out.println("The date is invalid. Said month only has 30 days.");
                        }
                        else{
                            System.out.println("Date validated.");
                        }                    
                    }
                }
            }
            else{
                System.out.println("Date validates.");
            }
        }
    }    
}
