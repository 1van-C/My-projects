/**
  * Clase para definir un Artículo
  * @author Gerardo Avilés Rosas
  * @version Noviembre 2016
  */

public class Articulo{
 private String titulo;
 private String hechoPor;
 private int anio;
 private String pais;

 /**
   * Constructor por omisión de la clase Artículo
   */
 public Articulo(){}

 /**
   * Constructor que recibe parámetros
   * @param t El título del artículo
   * @param h El nombre de la persona que lo elaboró
   * @param a El anio en que se hizo el artículo
   * @param c EL país de origen del artículo
   */
 public Articulo(String t,String h,int a, String c){
  this.titulo = t;
  hechoPor = h;
  setAnio(a);
  pais = c;
 }

 /**
   * Método para establecer el anio del Artículo
   * @param a El anio del Artículo
   */
 public void setAnio(int a){
   anio = a < 0 ? 2020 : a;
 }

 /**
   * Método para obtener el anio del Artículo
   * @return int El anio del Artículo
   */
 public int getAnio(){
  return anio;
 }

 /**
   * Método para establecer el título del Artículo
   * @param t El título del Artículo
   */
 public void setTitulo(String t){
  titulo = t;
 }

 /**
   * Método para obtener el título del Artículo
   * @return String El título del Artículo
   */
 public String getTitulo(){
  return titulo;
 }

 /**
   * Método para establecer el nombre de quién elaboró el Artículo
   * @param h El nombre de la persona
   */
 public void setHechoPor(String h){
  hechoPor = h;
 }

 /**
   * Método para obtener el nombre de quién elaboró el Artículo
   * @return String el nombre de la persona
   */
 public String getHechoPor(){
  return hechoPor;
 }
 
 /**
   * Método para establecer el páis de origen del artículo
   * @param p El País
   */
 public void setPais(String c){
  pais = c;
 }

 /**
   * Método para obtener el País de origen del articulo
   * @return String el nombre del País
   */
 public String getPais(){
  return pais;
 }

 /**
   * Método para imprimir un Artículo como una cadena de caracteres
   * @return String El Articulo en formato de cadena de caracter
   */
 public String toString(){
  String articulo = "";
  articulo = "Titulo: \"" + titulo + "\"\nHecho por: " + 
              hechoPor + "\nAnio: " + anio + "\nPais: " + pais;
  return articulo;
 }
}