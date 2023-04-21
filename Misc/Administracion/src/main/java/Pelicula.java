/**
 * Clase para modelar una Película a partir de otra clase
 * @author Gerardo Avilés Rosas
 * @version Noviembre de 2016
 * @see ArticuloGI
 */
public class Pelicula extends ArticuloGI{
 private String actriz;
 private double duracion;
 private String director;
 private String subtitulos;

 /**
   * Constructor que recibe parámetros
   * @param t El título de la Película
   * @param n El nombre del actor principal
   * @param a El anio en que se estrenó
   * @param c El país de origen de la Película
   * @param g El género de la Película
   * @param i El idioma de la Película
   * @param d La duración en minutos de la Película
   * @param ac El nombre de la actriz principal
   * @param nd El nombre del director
   * @param s El idioma de los subtítulos
   */
 public Pelicula(String t,String n,int a,String c,String g,String i,double d,String ac,String nd,String s){
  super(t,n,a,c,g,i);
  actriz = ac;
  setDuracion(d);
  director = nd;
  subtitulos = s;
 }

 /**
  * Método para establecer el nombre de la actriz
  * @param a el nombre de la actriz
  */
 public void setActriz(String a){
  actriz = a;
 }

 /**
  * Método para obtener el nombre de la actriz
  * @return String el nombre de la actriz
  */
 public String getActriz(){
  return actriz;
 }
 
 /**
  * Método para establecer el nombre del director
  * @param nd el nombre del director
  */
 public void setDirector(String nd){
  director = nd;
 }
 
 /**
  * Método para obtener el nombre del director
  * @return String el nombre del director
  */
 public String getDirector(){
  return director;
 }
 
  /**
  * Método para establecer el idioma de los subtítulos
  * @param s El idioma de los subtítulos
  */
 public void setSubtitulos(String s){
  subtitulos = s;
 }
 
 /**
  * Método para obtener el idioma de los subtítulos
  * @return String el idioma de los subtítulos
  */
 public String getSubtitulos(){
  return subtitulos;
 }
  
 /**
   * Método para establecer la duración en minutos
   * @param n la duracion en minutos
   */
 public void setDuracion(double n){
  duracion = n < 0 ? 0 : n;
 }

 /**
   * Método para obtener la duración en minutos 
   * @return double la duracon en minutos
   */
 public double getDuracion(){
  return duracion;
 }

 /**
   * Método para imprimir una Película como una cadena de caracteres
   * @return String La Película en formato de cadena de caracter
   */
 public String toString(){
  if(duracion == 0)
   return super.toString().replaceAll("Hecho por","Actor principal:") + 
          "\nActriz principal: " + actriz + "\nDuracion: No disponible" +
          "\nDirector: "+ director  + "\nSubtitulos: " + 
          subtitulos;
  else
   return super.toString().replaceAll("Hecho por","Actor principal:") +
          "\nActriz principal: " + actriz + "\nDuracion: " + duracion +
          "\nDirector: "+ director + "\nSubtitulos: " + 
          subtitulos;
 }
}