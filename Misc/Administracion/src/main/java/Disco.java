/**
 * Clase para modelar un disco a partir de otra clase
 * @author Gerardo Avilés Rosas
 * @version Noviembre de 2016
 * @see ArticuloGI
 */
public class Disco extends ArticuloGI{
 private int numero;
 private double calificacion;

 /**
   * Constructor que recibe parámetros
   * @param t El título del Disco
   * @param n El nombre de la persona que lo interpreta
   * @param a El anio en que se hizo lanzó el Disco
   * @param c El país de origen del Disco
   * @param g El género del Disco
   * @param i El idioma del Disco
   * @param no El número de canciones del Disco
   * @param r La calificación del Disco
   */
 public Disco(String t,String n,int a,String c,String g,String i,int no,double r){
  super(t,n,a,c,g,i);
  setNumero(no);
  setCalificacion(r);
 }

 /**
   * Método para establecer el número de canciones
   * @param n El número de canciones
   */
 public void setNumero(int n){
  numero = n < 0 ? 0 : n;
 }
 
 /**
   * Método para establecer la calificación del disco
   * @param r La calificación
   */
 public void setCalificacion(double r){
  calificacion = r < 0 ? 0 : r;
 }
 
 /**
   * Método para obtener el número de canciones 
   * @return int El número de canciones del disco
   */
 public int getNumero(){
  return numero;
 }
  
 /**
   * Método para obtener la calificación del Dico 
   * @return double La calificación del Disco
   */
 public double getCalificacion(){
  return calificacion;
 }

 /**
   * Método para imprimir un Disco como una cadena de caracteres
   * @return String El Disco en formato de cadena de caracter
   */
 public String toString(){
  if(numero == 0)
   return super.toString().replaceAll("Hecho","Cantado") + 
          "\nNo disponible"  + 
          "\nCalificación: " + calificacion;
  else
   return super.toString().replaceAll("Hecho","Cantado") +
          "\nCanciones: " + numero + 
          "\nCalificación: " + calificacion;
 }
}