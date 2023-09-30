import java.util.Scanner;
import static java.lang.System.*;

public class FechaNombreAlumno{
  public static void main(String [] args){
    Scanner lector = new Scanner(in);
    //Se construyen las dos fechas por parámetros.
    Fecha f1,f2;
    f1 = new Fecha(11,9,2001);
    f2 = new Fecha(30,4,1945);
    int d,m,y;
    String month;
    
    //Recordatorio de las fechas intactas.
    System.out.println("La fecha uno inicial es " + f1);
    System.out.println("La fecha dos inicial es " + f2);

    //Método modificador para cambiar la fecha uno.
    System.out.println("Se establecerá una nueva fecha uno. Ingrese el día como entero: ");
    d = lector.nextInt();
    System.out.println("Ingrese el mes como entero: ");
    m = lector.nextInt();
    System.out.println("Ingrese el año como entero: ");
    y = lector.nextInt();
    f1.setFecha(d,m,y);
    System.out.println("La nueva fecha uno es " + f1);
    
    //Otro método modificador para cambiar la fecha dos.
    System.out.println("Se establecerá una nueva fecha dos. Ingrese el día como entero: ");
    d = lector.nextInt();
    System.out.println("Ingrese el mes como cadena: ");
    month = lector.next();
    System.out.println("Ingrese el año como entero: ");
    y = lector.nextInt();
    f2.setFecha(d,month,y);
    System.out.println("La nueva fecha dos es " + f2);
    
    //Se ve si las fechas son la misma.
    System.out.println("¿Serán la misma fecha? " + f1.equals(f2));
    
    //Se verifica si la fecha dos es anterior a la uno.
    System.out.println("¿La fecha dos pasó antes que la fecha uno? " + f2.anterior(f1));
    
    //Métodos de modificación para editar individualmente día, mes y año.
        //Día en fecha uno.
    System.out.println("Se establecerá un nuevo día en la fecha uno. Ingrese el día como entero: ");
    d = lector.nextInt();
    f1.setDia(d);
        //Mes en fecha dos.
    System.out.println("Se establecerá un nuevo mes en la fecha dos. Ingrese el mes como entero: ");
    m = lector.nextInt();
    f1.setMes(m);
        //Año en fecha uno.
    System.out.println("Se establecerá un nuevo año en la fecha uno. Ingrese el año como entero: ");
    y = lector.nextInt();
    f1.setAnio(y);
    
    //Métodos de acceso para conocer sus componentes (día, mes y año).
        //Componentes de fecha uno.
    System.out.println("El día en la fecha uno es " + f1.getDia());
    System.out.println("El mes en la fecha uno es " + f1.getMes());
    System.out.println("El año en la fecha uno es " + f1.getAnio());
        //Componentes de fecha dos.
    System.out.println("El día en la fecha dos es " + f2.getDia());
    System.out.println("El mes en la fecha dos es " + f2.getMes());
    System.out.println("El año en la fecha dos es " + f2.getAnio());
    
    //Cambiar fecha uno por el último método modificador.
    System.out.println("Último cambio en la fecha uno. Escoja sabiamente.");
    f1.leerFecha();
    System.out.println("La fecha uno queda como: " + f1);
  }
}
