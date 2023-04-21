/**
 * Clase para modelar un disco a partir de otra clase
 * @author Gerardo Avilés Rosas
 * @version Noviembre de 2016
 * @see ArticuloGI
 */
public class VideoJuego extends ArticuloGI{
   private String plataforma;
   private int edicion;
   private int numeroj;
   

   /**
     * Constructor que recibe parámetros
     * @param t El título del Video Juego
     * @param n El nombre de la empresa que lo desarrolló
     * @param a El anio en que se lanzó el Video Juego
     * @param c El país de origen del Video Juego
     * @param g El género del Video Juego
     * @param i El idioma del Video Juego
     * @param p Plataforma del Video Juego
     * @param noj El número de jugadores del Video Juego
     */
   public VideoJuego(String t,String n,int a,String c,String g,String i,String p,int e,int noj){
      super(t,n,a,c,g,i);
      plataforma = p;
      setEdicion(e);
      setNumeroJ(noj);
   }
   
   /**
   * Método para obtener la plataforma 
   * @return String la plataforma del Video Juego
   */
   
   public String getPlataforma(){
      return plataforma;
   }
   
    /**
   * Método para obtener la edición
   * @return int la edición del Video Juego
   */
   
   public int getEdicion(){
      return edicion;
   }
   
    /**
   * Método para obtener el número de jugadores
   * @return int el númerod de jugadores del Video Juego
   */
   
   public int getNumeroJugadores(){
      return numeroj;
   }

   /**
   * Método para establecer la plataforma
   * @param p la plataforma
   */
   public void setPlataforma(String p){
      plataforma = p;
   }
   
    /**
   * Método para establecer la edición
   * @param e La edición del Video Juego
   */
   public void setEdicion(int e){
      edicion = e < 0 ? 0 : e;
   }
   
 /**
   * Método para establecer el número de jugadores
   * @param nj El número de jugadores del Video Juego
   */
   public void setNumeroJ(int nj){
      numeroj = nj < 0 ? 0 : nj;
   }
   
    /**
   * Método para imprimir un Video Juego como una cadena de caracteres
   * @return String El Video Juego en formato de cadena de caracter
   */
 public String toString(){
  if(edicion == 0)
   return super.toString() + "\nPlataforma: " + plataforma + 
          "\nEdición no disponible" + "\nNúmero de jugadores: " + numeroj;
  else
   return super.toString() + "\nPlataforma: " + plataforma +
          "\nEdición: " + edicion + "\nNúmero de jugadores: " + numeroj;
 }

}