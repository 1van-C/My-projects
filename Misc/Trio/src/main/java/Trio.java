/**
 * Ejercicio tres.
 * @author Johnny Sins
 * @version Abril 2020
 */

 /*
a) El objetivo de dicha línea es invocar otro constructor desde la misma clase. Es una forma de "conectar"
constructores para hacer consistente al objeto constructor mediante constructores. A continuación se anexa el
programa incluido en las instrucciones.
*/

public class Trio{
  private int x;
  private int y;
  private int z;
  
  public Trio(){
    this(0,0,0);
  }
  
  public Trio(int x,int y,int z){
    this.x = x;
    this.y = y;
    this.z = z;
  }
  /*
  Hasta aquí está lo proporcionado en el ejercicio.
  */
  
  /**
  * b) Constructor por parámetros (las dos primeras coordenadas) con valor z de 50 predeterminado.
  * @param ehcs La nueva coordenada x.
  * @param why La nueva coordenada y.
  */
  
  public Trio(int ehcs, int why){
      this.x = ehcs;
      this.y = why;
      this.z = 50;
  }
  
  /**
  * c) Método comparador de la tercer coordenada entre tríos. Si son iguales, se regresa true, de otro modo regresará
  * false.
  * @param three El trio con coordenada z a comparar.
  * @return boolean Valor lógico de la comparación para las terceras coordenadas.
  */
  public boolean isthisz(Trio three){
      return z == three.z;
  }

  /**
  * d) Método comparador: mayor y menor entre tríos.
  * @param third El trío a comparar.
  * @return String Cadena pertinente a coordenadas mayores, menores o iguales.
  */
  
  public String size(Trio third){
      if (x > third.x && y > third.y && z > third.z){
          return "Mayor";
      }
      if (third.x > x && third.y > y && third.z > z){
          return "Menor";
      }
      if (third.x == x && third.y == y && third.z == z){
          return "Iguales";
      }
      else{
          return "No son comparables para fines de este método.";
      }
  }
  
  /**
  * e) Método comparador de tríos. Si son iguales, se regresa true, de otro modo regresará false.
  * @param triad El trío a comparar en tanto a su igualdad.
  * @return boolean El valor lógico derivado de la comparación entre ambos tríos.
  */
  
  public boolean same(Trio triad){
      return x == triad.x && y == triad.y && z == triad.z;
  }

  
   /**
   * f) Método para obtener la representación en cadena de caracteres de un trío.
   * @return String Cadena representadora del trío.
   */

  public String toString(){
    return "Trío con coordenada x: " + x + ", coordenada y: " + y + " y coordenada z: " + z + " .";
  }
  
  /**
  * g) Método que "desplazará" las coordenadas por cierto valor distinto de cero.
  * @param step El número para 'desplazar' al trío.
  * @return Trio El nuevo trío desplazado.
  */
  
  public Trio disp(int step){
      Trio ti = new Trio(this.x+step, this.y+step, this.z+step);
      return ti;
  }
  
/**
* h) Método modificador que multiplica las coordenadas de un trío por las de otro.
* @param trinity El trío por el cual multiplicar.
* @return Trio El nuevo trío multiplicado.
*/
  
  public Trio prod(Trio trinity){
      Trio tx = new Trio(this.x*trinity.x, this.y*trinity.y, this.z*trinity.z);
      return tx;
  }
  
}