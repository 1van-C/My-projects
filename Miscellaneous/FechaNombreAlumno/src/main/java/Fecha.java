import java.util.Scanner;
/**
 * Clase para modelar una <b>Fecha</b> con el formato <b>d/nombre_mes/anio</b>
 * <br>Se exploran distintas formas de métodos constructores, el método equals y toString.
 * <br>Se utilizan algunos métodos privados
 * @author Gerardo Avilés Rosas
 * @version Septiembre de 2016
 */

public class Fecha{
  private String mes; //Nombre del mes
  private int dia; //Número del día
  private int anio; //Número de 4 dígitos
  
  /**
   * Constructor por omisión de la <b>Fecha</b>
   */
  public Fecha(){
    dia = 15;
    mes = "septiembre";
    anio = 2016;
  }
  
  /**
   * Constructor por parámetros para la <b>Fecha</b>. Recibe el número del mes.
   * @param dia El día en que ocurrió la <b>Fecha</b>.
   * @param mesInt El número mes en que ocurrió la <b>Fecha</b>.
   * @param anio El año en que ocurrió la <b>Fecha</b>.
   */
  public Fecha(int dia,int mesInt,int anio){
    setFecha(dia,mesInt,anio);
  }
  /**
   * Constructor por parámetros para la <b>Fecha</b>. Recibe el nombre de mes.
   * @param dia El día en que ocurrió la <b>Fecha</b>.
   * @param mes El nombre del mes en que ocurrió la <b>Fecha</b>.
   * @param anio El año en que ocurrió la <b>Fecha</b>.
   */
  public Fecha(int dia,String mes,int anio){
    setFecha(dia,mes,anio);
  }
  
  /**
   * Constructor por parámetros para la <b>Fecha</b>. Recibe solo el año.
   * el día y el mes estan preestablecidos.
   * @param anio El año en que ocurrió la <b>Fecha</b>.
   */ 
  public Fecha(int anio){
    setFecha(20,6,anio);
  }
  
  /**
   * Método que permite modificar una fecha a partir del número de mes, día y año.
   * @param dia El día en que ocurrió la <b>Fecha</b>.
   * @param mesInt El número del mes en que ocurrió la <b>Fecha</b>.
   * @param anio El año en que ocurrió la <b>Fecha</b>.
   */
  public void setFecha(int dia,int mesInt,int anio){
    if(fechaOK(dia,mesInt,anio)){ //Si es una fecha correcta
      this.dia = dia;
      this.mes = mesString(mesInt);
      this.anio = anio; //Validar que sea un año de 4 dígitos
    }
    else{
      System.out.println("Error fatal. Los datos no corresponden con una Fecha.");
      System.exit(0); //Provoca que el programa termine
    }
  }
  
  /**
   * Método que permite modificar una fecha a partir del nombre del mes, día y año.
   * @param dia El día en que ocurrió la <b>Fecha</b>.
   * @param mes El nombre del mes en que ocurrió la <b>Fecha</b>.
   * @param anio El año en que ocurrió la <b>Fecha</b>.
   */
  public void setFecha(int dia,String mes,int anio){
    if(fechaOK(dia,mes,anio)){
      this.dia = dia;
      this.mes = mes;
      this.anio = anio; //Validar que sea un año de 4 dígitos
    }
    else{
      System.out.println("Error fatal. Los datos no corresponden con una fecha correcta");
      System.exit(0); //Provoca que el programa termine
    }
  }
  
  /**
   * Método que permite modificar el año de una fecha, el día y el mes están preestablecidos.
   * @param anio El año en que ocurrió la <b>Fecha</b>.
   */
  public void setFecha(int anio){
    setFecha(28,11,anio);
  }
  
  /**
   * Método que permite cambiar el año de cualquier fecha.
   * <br> Se valida que el año sea de 4 dígitos.
   * @param anio El año en que ocurrió la <b>Fecha</b>.
   */
  public void setAnio(int anio){
    if ((anio < 1000) || (anio > 9999)){
      System.out.println("Error fatal. No es un año de 4 dígitos");
      System.exit(0); //Provoca que el programa termine
    }
    else //El año es válido
      this.anio = anio;
    }
  
  /**
   * Método que permite cambiar el mes de cualquier fecha.
   * <br> Recibe el número del mes.
   * @param mesInt El año en que ocurrió la <b>Fecha</b>.
   */
    public void setMes(int mesInt){
      if((mesInt <= 0) || (mesInt > 12)){ //No es un mes válido.
        System.out.println("Error fatal.No es mes válido.");
        System.exit(0); //Provoca que el programa termine
      }
      else
        mes = mesString(mesInt); //Obtiene el nombre del mes
    }
    
    /**
     * Método que permite cambiar el mes de cualquier fecha.
     * <br> Recibe el número del mes.
     * @param dia El año en que ocurrió la <b>Fecha</b>.
     */
    public void setDia(int dia){
      if((dia <= 0) || (dia > 31)){ //No es un día válido
        System.out.println("Error fatal. No es un día válido");
        System.exit(0); //Provoca que el programa termine
      }
      else
        this.dia = dia;
    }
    
    /**
     * Método para obtener el número de mes de una <b>Fecha</b>.
     * @return int El número de mes de la <b>Fecha</b>.
     */
    public int getMes(){
      if (mes.equals("enero"))
        return 1;
      else if (mes.equalsIgnoreCase("febrero"))
        return 2;
      else if (mes.equalsIgnoreCase("marzo"))
        return 3;
      else if (mes.equalsIgnoreCase("abril"))
        return 4;
      else if (mes.equalsIgnoreCase("mayo"))
        return 5;
      else if (mes.equalsIgnoreCase("junio"))
        return 6;
      else if (mes.equalsIgnoreCase("julio"))
        return 7;
      else if (mes.equalsIgnoreCase("agosto"))
        return 8;
      else if (mes.equalsIgnoreCase("septiembre"))
        return 9;
      else if (mes.equalsIgnoreCase("octubre"))
        return 10;
      else if (mes.equals("noviembre"))
        return 11;
      else if (mes.equals("diciembre"))
        return 12;
      else{
        System.out.println("Error fatal.El nombre del mes no es válido");
        System.exit(0); //Provoca que el programa termine
        return 0; //Necesario para que el compilador este feliz.
      }
    }
    
    /**
     * Método para obtener el día de una <b>Fecha</b>.
     * @return int El número de día de la <b>Fecha</b>.
     */
    public int getDia( ){
      return dia;
    }
    
    /**
     * Método para obtener el año de una <b>Fecha</b>.
     * @return int El número de año de la <b>Fecha</b>.
     */
    public int getAnio( ){
      return anio;
    }
    
    /**
     * Método para imprimir una <b>Fecha</b> como una cadena de caracteres.
     * @return String Le fecha con formato dd/nombre_mes/aaaa
     */
    public String toString(){
      return dia + "/" + mes + "/" + anio;
    }
    
    /**
     * Método para determinar si dos Fechas dadas son iguales
     * @param otraFecha Una <b>Fecha</b> contra la cual comparar.
     * @return boolean <b>True</b> si las fechas son iguales, <b>false</b> en caso contrario.
     */
    public boolean equals(Fecha otraFecha){
      if (otraFecha == null) //No se especificó la Fecha
        return false;
      else
        return ((mes.equals(otraFecha.mes)) && (dia == otraFecha.dia) && (anio == otraFecha.anio));
    }
    
    /**
     * Método para determinar una <b>Fecha</b> es anterior a otra.
     * @param otraFecha Una <b>Fecha</b> contra la cual comparar.
     * @return boolean <b>True</b> si las fechas son iguales, <b>false</b> en caso contrario.
     */
    public boolean anterior(Fecha otraFecha){
      return ((anio < otraFecha.anio) ||
              (anio == otraFecha.anio && getMes() < otraFecha.getMes()) ||
              (anio == otraFecha.anio && mes.equals(otraFecha.mes) && dia < otraFecha.dia));
    }
    
    /**
     * Método para leer una <b>Fecha</b> por medio del teclado.
     */
    public void leerFecha(){
      boolean otraVez = true;
      Scanner lector = new Scanner(System.in);
      String mesInput;
      int anioInput,diaInput;
      do{
        System.out.println("Ingresa dia nombre_mes anio.");
        System.out.println("Da un ENTER entre cada valor");
        diaInput = lector.nextInt( );
        mesInput = lector.next( );
        anioInput = lector.nextInt( );
        if(fechaOK(diaInput,mesInput,anioInput)){
          setFecha(diaInput,mesInput,anioInput);
          otraVez = false;
        }
        else
          System.out.println("Fecha ilegal. Ingresa de nuevo los datos.");
      }while (otraVez);
    }
    
    /*
     * Método para determinar si una fecha es correcta. Todos los datos son enteros.
     * Este método es privado.
     */
    private boolean fechaOK(int diaInt,int mesInt,int anioInt){
      return ((mesInt >= 1) && (mesInt <= 12) &&
              (diaInt >= 1) && (diaInt <= 31) &&
              (anioInt >= 1000) && (anioInt <= 9999));
    }
    
    /*
     * Método para determinar si una fecha es correcta.
     * Este método es privado.
     */
    private boolean fechaOK(int diaInt,String mesString,int anioInt){
      return (mesOK(mesString) &&
              (diaInt >= 1) && (diaInt <= 31) &&
              (anioInt >= 1000) && (anioInt <= 9999));
    }
    
    /*
     * Método para saber si el nombre del mes es correcto
     */
    private boolean mesOK(String mes){
      return (mes.equals("enero") || mes.equals("febrero") ||
              mes.equals("marzo") || mes.equals("abril") ||
              mes.equals("mayo") || mes.equals("junio") ||
              mes.equals("julio") || mes.equals("agosto") ||
              mes.equals("septiembre") || mes.equals("octubre") ||
              mes.equals("noviembre") || mes.equals("diciembre"));
    }
    
    /*
     * Método para convertir a partir de un número de mes el nombre del mes
     */
    private String mesString(int mesInt){
      switch (mesInt){
        case 1:
          return "enero";
        case 2:
          return "febrero";
        case 3:
          return "marzo";
        case 4:
          return "abril";
        case 5:
          return "mayo";
        case 6:
          return "junio";
        case 7:
          return "julio";
        case 8:
          return "agosto";
        case 9:
          return "septiembre";
        case 10:
          return "octubre";
        case 11:
          return "noviembre";
        case 12:
          return "diciembre";
        default:
          System.out.println("Error fatal. No es un número de mes válido");
          System.exit(0); //Provoca que el programa termine
            return "Error"; //Necesario para que el compilador este feliz.
      }
    }
}