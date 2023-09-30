public class PruebaFecha{
  public static void main(String [] args){
    //Probando los constructores
    Fecha c1,c2,c3,c4;
    c1 = new Fecha();
    c2 = new Fecha(12,5,1986);
    c3 = new Fecha(5,"julio",2009);
    c4 = new Fecha(2008);
    System.out.println(c1);
    System.out.println(c2);
    System.out.println(c3);
    System.out.println(c4);
    //System.out.println("Cambiando los datos de la fecha c4");
    //c4.setFecha(34,12,2005);
    //c4.setFecha(3,12,2005);
    //System.out.println("Consultando la fecha c4");
    //System.out.println(c4);
    //System.out.println("Obteniendo las partes de la fecha c3: " + c3);
    //System.out.println("El día es: " + c3.getDia());
    //System.out.println("El mes es: " + c3.getMes());
    //System.out.println("El año es: " + c3.getAnio());
    //System.out.println("Cambiando el día, mes y año de la fecha c2: " + c2);
    //c2.setDia(3);
    //c2.setMes(7);
    //c2.setAnio(1999);
    //System.out.println("La fecha modificada es: " + c2);
    //System.out.println("¿La fecha c1 es igual a la fecha c2? " + c1.equals(c2));
    //System.out.println("¿La fecha c2 es igual a la fecha c3? " + c2.anterior(c3));
    //System.out.println("Cambiando los valores de una fecha desde el teclado:");
    //c1.leerFecha();
    //System.out.println("La fecha c1 con nuevos valores es: " + c1);
  }
}