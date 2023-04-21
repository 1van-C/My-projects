/**
  * Clase para definir el idioma de un Artículo
  * @author Gerardo Avilés Rosas
  * @version Noviembre 2016
  * @see Articulo
  */

public class ArticuloGI extends Articulo{
 private String genero;
 private String idioma;

 /**
   * Constructor que recibe parámetros
   * @param t El título del Artículo
   * @param h El nombre de la persona que lo elaboró
   * @param a El anio en que se hizo el Artículo
   * @param c El país de origen del Artículo
   * @param g El género
   * @param i El idioma
   */
 public ArticuloGI(String t,String h,int a,String c,String g,String i){
  super(t,h,a,c);
  genero = g;
  idioma = i;
 }

 /**
   * Método para establecer el género del Artículo
   * @param g El género del Artículo
   */
 public void setGenero(String g){
   genero = g;
 }

 /**
   * Método para obtener el género del Artículo
   * @return String El género del Artículo
   */
 public String getGenero(){
  return genero;
 }

 /**
   * Método para establecer el idioma del Artículo
   * @param i El idioma del Artículo
   */
 public void setIdioma(String i){
  idioma = i;
 }

 /**
   * Método para obtener el idioma del Artículo
   * @return String El idioma del Artículo
   */
 public String getIdioma(){
  return idioma;
 }

 /**
   * Método para imprimir un Artículo como una cadena de caracteres
   * @return String El Artículo en formato de cadena de caracter
   */
 public String toString(){
  String articulo = "";
  articulo = super.toString() + "\nGenero: " + genero +
            "\nIdioma: " + idioma;
  return articulo;
 }
}