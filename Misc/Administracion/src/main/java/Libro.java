/**
 * Clase que permite modelar un Libro a partir de un Artículo
 * @author Gerardo Avilés Rosas
 * @version Noviembre de 2016
 * @see Articulo
 */
public class Libro extends Articulo{
 private String editorial;
 private String tema;

 /**
   * Constructor que recibe parámetros
   * @param t El título del Libro
   * @param n El nombre de la persona que lo escribió
   * @param a El anio en que se hizo el Libro
   * @param c El país de origen del Libro
   * @param e La editorial del Libro
   * @param k El tema del Libro
   */
 public Libro(String t,String n,int a,String c,String e, String k){
  super(t,n,a,c);
  editorial = e;
  tema = k;
 }

 /**
   * Método para cambiar la editorial del Libro
   * @param e La editorial del Libro
   */
 public void setEditorial(String e){
  editorial = e;
 }
 
 /**
   * Método para cambiar el tema del Libro
   * @param k El tema del Libro
   */
 public void setTema(String k){
  tema = k;
 }

 /**
   * Método para obtener la editorial del Libro
   * @return String la editorial del Libro
   */
 public String getEditorial(){
  return editorial;
 }
 
 /**
   * Método para obtener el tema del Libro
   * @return String el tema del Libro
   */
 public String getTema(){
  return tema;
 }
 
 /**
   * Método para imprimir un Libro como una cadena de caracteres
   * @return String El Libro en formato de cadena de caracter
   */
 public String toString(){
  return super.toString().replaceAll("Hecho","Escrito") +
         "\nEditorial:" + editorial + "\nTema: " + tema;
 }
}