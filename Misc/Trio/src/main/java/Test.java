/**
 *
 * @author calei
 */

public class Test {
    public static void main(String [] args){
        Trio t1, t2, t3;
        t1 = new Trio(1,2);
        t2 = new Trio(1, 2, 50);
        System.out.println("Trio uno inicial: " + t1);
        System.out.println("Trio dos inicial: " + t2);
        
        System.out.println ("¿Mismas z? " + t1.isthisz(t2));
        
        System.out.println ("¿Tamaño? " + t1.size(t2));
        
        System.out.println ("¿Mismo trio? " + t1.same(t2));
        
        System.out.println ("Desplazar el trio uno por 7: " + t1.desp(7));
        
        System.out.println ("Multiplicar los dos trios: " + t1.prod(t2));
        
    }
}

