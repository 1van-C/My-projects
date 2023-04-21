import java.util.Scanner;
import static java.lang.System.*;
//Bibliotecas para lectura/escritura de Archivos en Java
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
//Bibliotecas en caso de que haya problemas con la lectura/escritura de Archivos
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Clase que se encarga de modelar todas las opciones de administración de Artículos
 * @author Gerardo Avilés Rosas
 * @version Noviembre de 2016
 */
public class Administracion{
   private Articulo articulo[];
   private int na;
   private Scanner lector;
   private Scanner lector1;
  
  /**
   * Constructor por omisión
   * <br>Construye un arreglo con capacidad de 20 Artículos
   */
   public Administracion(){
      this(20);
   }
  
  /**
   * Constructor que recibe parámetros
   * @param t El número de Artículos que se van a almacenar
   */
   public Administracion(int t){
      articulo = new Articulo[(t < 0)? 20 : t];
      na = 0; //inicialmente no hay articulos
      lector = new Scanner(in);
      lector1 = new Scanner(in).useDelimiter("\n");
   }
  
  /**
   * Método para saber si el arreglo de Artículos esta vacío
   * @return boolean true sí está vacío, false en caso contrario
   */
   public boolean estaVacio(){
      return na == 0;
   }
  
  /**
   * Método para saber si el arreglo de Artículos esta lleno
   * @return boolean true sí está lleno, false en otro caso
   */
   public boolean estaLleno(){
      return na == articulo.length;
   }
  
  /**
   * Método para agregar un Artículo
   * @param a El artículo que se va a agregar
   */
   public void agregar(Articulo a){
      if(!estaLleno()){ //Verifica si hay espacio
         articulo[na++] = a;
      }
      else{
  Articulo temp[] = new Articulo[na++];
  for(int i = 0;i < na;i++) // Copiamos los elementos desde el arreglo original al nuevo.
     temp[i] = articulo[i];
  temp[na++] = a; //Añadir el nuevo articulo.
  articulo = temp; //Actualizar el arreglo original al sustituirlo por el nuevo.
      }
   }

  /**
   * Método para eliminar el primer Artículo dado el título
   * @param titulo El título del Artículo
   */
   public void eliminarPrimero(String titulo){
      int i;
      boolean borro = false;
      if(!estaVacio()){ //Si hay Articulos almacenados
         for(i = 0;i < na;i++) //Buscamos el Articulo
            if(titulo.equals(articulo[i].getTitulo())){ //Lo encontró
               if(i == (na - 1)) //El Artículo está al final
                  na--;
               else //El Artículo no es el último elemento
                  articulo[i] = articulo[--na]; //Deja inaccesible al último valor
               borro = true;
               break; //Ya no seguimos buscando
            }
         if(i == na && !borro) //En caso de que no exista el Articulo
            out.println("El Articulo con titulo \"" + titulo + "\" no existe!\n");
      }
      else
         out.println("No hay articulos almacenados\n");
   }
    
  /**
   * Método para eliminar todos Artículos dado el título
   * @param titulo El título del Artículo
   */
   public void eliminarTodos(String titulo){
      int i;
      boolean borro = false;
      if(!estaVacio()){ //Si hay Articulos almacenados
         do{
            for(i = 0;i < na;i++) //Buscamos el Articulo
               if(titulo.equals(articulo[i].getTitulo())){ //Lo encontro
                  if(i == (na - 1)) //El Artículo está al final
                     na--;
                  else //El Artículo no es el último elemento
                     articulo[i] = articulo[--na]; //Deja inaccesible al último valor
                  borro = true;
                  break;
               }
         }while(contiene(titulo));
         if(!borro) //En caso de que ningún Artículo exista
            out.println("No existe ningun Articulo con el titulo \"" + titulo + "\"\n");
      }       
      else
         out.println("No hay articulos almacenados\n");
   }
  
   private boolean contiene(String titulo){
      boolean respuesta = false;
      if(!estaVacio()) //Si hay Articulos almacenados
         for(int i = 0;i < na;i++) //Buscamos el Articulo
            if(titulo.equals(articulo[i].getTitulo())){ //Lo encontro
               respuesta = true;
               break;
            }
      return respuesta;
   }
    
    /**
     * Método para eliminar Película dado el título
     * @param titulo El título de la Película a eliminar
     */
   public void eliminarPelicula(String titulo){
      int i;
      boolean borro = false;
      if(!estaVacio()){ //Si hay Articulos
         for(i = 0;i < na;i++) //Lo buscamos
            if(articulo[i] instanceof Pelicula &&
             articulo[i].getTitulo().equals(titulo)){ //Si es pelicula y el titulo coincide
               if(i == (na - 1)) //La Película está al final
                  na--;
               else //La película no es el último elemento
                  articulo[i] = articulo[--na]; //Deja inaccesible al último valor
               borro = true;
               break; //No seguimos buscando
            }
         if(i == na && !borro) // EN caso de que no exista la pelicula
            out.println("La Pelicula + \"" + titulo + "\" no existe!\n");
      }
      else
         out.println("No hay articulos almacenados\n");
   }
    
    /**
     * Método para eliminar un Libro dado el título
     * @param titulo El título del Libro a eliminar
     */
   public void eliminarLibro(String titulo){
      int i;
      boolean borro = false;
      if(!estaVacio()){ //Si hay Articulos
         for(i = 0;i < na;i++) //Lo buscamos
            if(articulo[i] instanceof Libro &&
             articulo[i].getTitulo().equals(titulo)){ //Si es libro y el titulo coincide
               if(i == (na - 1)) //El Libro está al final
                  na--;
               else //El Libro no es el último elemento
                  articulo[i] = articulo[--na]; //Deja inaccesible al último valor
               borro = true;
               break; //No seguimos buscando
            }
         if(i == na && !borro) // EN caso de que no exista el libro
            out.println("El libro + \"" + titulo + "\" no existe!\n");
      }
      else
         out.println("No hay articulos almacenados\n");
   }
    
     /**
     * Método para eliminar un Disco dado el título
     * @param titulo El título del Disco a eliminar
     */
   public void eliminarDisco(String titulo){
      int i;
      boolean borro = false;
      if(!estaVacio()){ //Si hay Articulos
         for(i = 0;i < na;i++) //Lo buscamos
            if(articulo[i] instanceof Disco &&
             articulo[i].getTitulo().equals(titulo)){ //Si es Disco y el titulo coincide
               if(i == (na - 1)) //El Disco está al final
                  na--;
               else //El Disco no es el último elemento
                  articulo[i] = articulo[--na]; //Deja inaccesible al último valor
               borro = true;
               break; //No seguimos buscando
            }
         if(i == na && !borro) // EN caso de que no exista el disco
            out.println("El disco + \"" + titulo + "\" no existe!\n");
      }
      else
         out.println("No hay articulos almacenados\n");
   }
    
    //Método para revisar si hay libros en el arreglo
   private boolean contieneL(){
      boolean respuesta = false;
      if(!estaVacio()) //Si hay Articulos almacenados
         for(int i = 0;i < na;i++) //Buscamos el Articulo
            if(articulo[i] instanceof Libro){ //Lo encontro
               respuesta = true;
               break;
            }
      return respuesta;
   }
  
   
    /**
    * Método para eliminar todos los Libros
    */
   public void eliminarLibros(){
      int i;
      boolean borro = false;
      if(!estaVacio()){ //Si hay Articulos almacenados
         do{
            for(i = 0;i < na;i++) //Buscamos el Articulo
               if(articulo[i] instanceof Libro){ //Lo encontro
                  if(i == (na - 1)) //El Artículo está al final
                     na--;
                  else //El Artículo no es el último elemento
                     articulo[i] = articulo[--na]; //Deja inaccesible al último valor
                  borro = true;
                  break;
               }
         }while(contieneL());
         if(!borro) //En caso de que ningún Artículo exista
            out.println("No existe ningun Libro almacenado\n" );
      }       
      else
         out.println("No hay articulos almacenados\n");
   }
   
   //Método para revisar si hay Discos en el arreglo
   private boolean contieneD(){
      boolean respuesta = false;
      if(!estaVacio()) //Si hay Articulos almacenados
         for(int i = 0;i < na;i++) //Buscamos el Articulo
            if(articulo[i] instanceof Disco){ //Lo encontro
               respuesta = true;
               break;
            }
      return respuesta;
   }
  
   
    /**
    * Método para eliminar todos los Discos
    */
   public void eliminarDiscos(){
      int i;
      boolean borro = false;
      if(!estaVacio()){ //Si hay Articulos almacenados
         do{
            for(i = 0;i < na;i++) //Buscamos el Articulo
               if(articulo[i] instanceof Disco){ //Lo encontro
                  if(i == (na - 1)) //El Artículo está al final
                     na--;
                  else //El Artículo no es el último elemento
                     articulo[i] = articulo[--na]; //Deja inaccesible al último valor
                  borro = true;
                  break;
               }
         }while(contieneD());
         if(!borro) //En caso de que ningún Artículo exista
            out.println("No existe ningun Disco almacenado\n" );
      }       
      else
         out.println("No hay articulos almacenados\n");
   }

   //Método para revisar si hay Películas en el arreglo
   private boolean contieneP(){
      boolean respuesta = false;
      if(!estaVacio()) //Si hay Articulos almacenados
         for(int i = 0;i < na;i++) //Buscamos el Articulo
            if(articulo[i] instanceof Pelicula){ //Lo encontro
               respuesta = true;
               break;
            }
      return respuesta;
   }
  
   
    /**
    * Método para eliminar todos los Películas
    */
   public void eliminarPeliculas(){
      int i;
      boolean borro = false;
      if(!estaVacio()){ //Si hay Articulos almacenados
         do{
            for(i = 0;i < na;i++) //Buscamos el Articulo
               if(articulo[i] instanceof Pelicula){ //Lo encontro
                  if(i == (na - 1)) //El Artículo está al final
                     na--;
                  else //El Artículo no es el último elemento
                     articulo[i] = articulo[--na]; //Deja inaccesible al último valor
                  borro = true;
                  break;
               }
         }while(contieneP());
         if(!borro) //En caso de que ningún Artículo exista
            out.println("No existe ninguna Película almacenado\n" );
      }       
      else
         out.println("No hay articulos almacenados\n");
   }

   //Método para revisar si hay Video Juegos en el arreglo
   private boolean contieneVJ(){
      boolean respuesta = false;
      if(!estaVacio()) //Si hay Articulos almacenados
         for(int i = 0;i < na;i++) //Buscamos el Articulo
            if(articulo[i] instanceof VideoJuego){ //Lo encontro
               respuesta = true;
               break;
            }
      return respuesta;
   }
  
   
    /**
    * Método para eliminar todos los Video Juegos
    */
   public void eliminarVideoJuegos(){
      int i;
      boolean borro = false;
      if(!estaVacio()){ //Si hay Articulos almacenados
         do{
            for(i = 0;i < na;i++) //Buscamos el Articulo
               if(articulo[i] instanceof VideoJuego){ //Lo encontro
                  if(i == (na - 1)) //El Artículo está al final
                     na--;
                  else //El Artículo no es el último elemento
                     articulo[i] = articulo[--na]; //Deja inaccesible al último valor
                  borro = true;
                  break;
               }
         }while(contieneVJ());
         if(!borro) //En caso de que ningún Artículo exista
            out.println("No existe ningun Video Juego almacenado\n" );
      }       
      else
         out.println("No hay articulos almacenados\n");
   }
   
   /**
   * Método para eliminar un libro dado el autor
   * @param al El autor del libro
   */
   public void eliminarLibroPorAutor(String al){
      int i;
      boolean borro = false;
      if(!estaVacio()){ //Si hay Articulos
         for(i = 0;i < na;i++) //Lo buscamos
            if(articulo[i] instanceof Libro &&
             articulo[i].getHechoPor().equals(al)){ //Si es libro y el autor coincide
               if(i == (na - 1)) //El Libro está al final
                  na--;
               else //El Libro no es el último elemento
                  articulo[i] = articulo[--na]; //Deja inaccesible al último valor
               borro = true;
               break; //No seguimos buscando
            }
         if(i == na && !borro) // En caso de que no se encuentre el autor
            out.println("El autor + \"" + al + "\" no se encontró!\n");
      }
      else
         out.println("No hay articulos almacenados\n");
   }
   
    /**
   * Método para eliminar una pelicula dado el director
   * @param d El director de la película
   */
   public void eliminarPeliPorDirector(String d){
      int i;
      boolean borro = false;
      if(!estaVacio()){ //Si hay Articulos
         for(i = 0;i < na;i++) //Lo buscamos
            if(articulo[i] instanceof Pelicula &&
             ((Pelicula)articulo[i]).getDirector().equals(d)){ //Si es pelicula y el director coincide
               if(i == (na - 1)) //El Didco está al final
                  na--;
               else //El Disco no es el último elemento
                  articulo[i] = articulo[--na]; //Deja inaccesible al último valor
               borro = true;
               break; //No seguimos buscando
            }
         if(i == na && !borro) // EN caso de que no se encuentre el director
            out.println("El Director + \"" + d + "\" no se encontró!\n");
      }
      else
         out.println("No hay articulos almacenados\n");
   }
   
   
   /**
   * Método para eliminar un Disco dado el interprete
   * @param n El interprete del disco
   */
   public void eliminarDiscoPorInterprete(String n){
      int i;
      boolean borro = false;
      if(!estaVacio()){ //Si hay Articulos
         for(i = 0;i < na;i++) //Lo buscamos
            if(articulo[i] instanceof Disco &&
             articulo[i].getHechoPor().equals(n)){ //Si es disco y el interprete coincide
               if(i == (na - 1)) //El Disco está al final
                  na--;
               else //El Disco no es el último elemento
                  articulo[i] = articulo[--na]; //Deja inaccesible al último valor
               borro = true;
               break; //No seguimos buscando
            }
         if(i == na && !borro) // En cado de que el interprete no se encuentre
            out.println("El interprete + \"" + n + "\" no se encontró!\n");
      }
      else
         out.println("No hay articulos almacenados\n");
   }
   
   /**
   * Método para eliminar el primer Artículo dado el País de origen
   * @param pais El País
   */
   public void eliminarPrimeroPorPais(String pais){
      int i;
      boolean borro = false;
      if(!estaVacio()){ //Si hay Articulos almacenados
         for(i = 0;i < na;i++) //Buscamos el Articulo
            if(pais.equals(articulo[i].getPais())){ //Lo encontró
               if(i == (na - 1)) //El Artículo está al final
                  na--;
               else //El Artículo no es el último elemento
                  articulo[i] = articulo[--na]; //Deja inaccesible al último valor
               borro = true;
               break; //Ya no seguimos buscando
            }
         if(i == na && !borro) //En caso de que no exista el Articulo
            out.println("El Articulo con País de origen \"" + pais + "\" no existe!\n");
      }
      else
         out.println("No hay articulos almacenados\n");
   }
   
    /**
   * Método para eliminar todos Artículos dado el País de origen
   * @param pais El País
   */
   public void eliminarTodosPorPais(String pais){
      int i;
      boolean borro = false;
      if(!estaVacio()){ //Si hay Articulos almacenados
         do{
            for(i = 0;i < na;i++) //Buscamos el Articulo
               if(pais.equals(articulo[i].getPais())){ //Lo encontro
                  if(i == (na - 1)) //El Artículo está al final
                     na--;
                  else //El Artículo no es el último elemento
                     articulo[i] = articulo[--na]; //Deja inaccesible al último valor
                  borro = true;
                  break;
               }
         }while(contieneP(pais));
         if(!borro) //En caso de que ningún Artículo exista
            out.println("No existe ningun Articulo con  \"" + pais + "\" como País de origen\n");
      }       
      else
         out.println("No hay articulos almacenados\n");
   }
  
   private boolean contieneP(String pais){
      boolean respuesta = false;
      if(!estaVacio()) //Si hay Articulos almacenados
         for(int i = 0;i < na;i++) //Buscamos el Articulo
            if(pais.equals(articulo[i].getPais())){ //Lo encontro
               respuesta = true;
               break;
            }
      return respuesta;
   }
   
    /**
     * Método para eliminar un Video Juego dado el título
     * @param titulo El título del Video Juego a eliminar
     */
   public void eliminarVideoJuego(String titulo){
      int i;
      boolean borro = false;
      if(!estaVacio()){ //Si hay Articulos
         for(i = 0;i < na;i++) //Lo buscamos
            if(articulo[i] instanceof VideoJuego &&
             articulo[i].getTitulo().equals(titulo)){ //Si es Video Juego y el titulo coincide
               if(i == (na - 1)) //El Video Juego está al final
                  na--;
               else //El Video Juego no es el último elemento
                  articulo[i] = articulo[--na]; //Deja inaccesible al último valor
               borro = true;
               break; //No seguimos buscando
            }
         if(i == na && !borro) // EN caso de que no exista el disco
            out.println("El video juego + \"" + titulo + "\" no existe!\n");
      }
      else
         out.println("No hay articulos almacenados\n");
   }
   
   /**
   * Método para eliminar un Video Juego por le empresa que lo creo
   * @param n La empresa que creo el juego
   */
   public void eliminarVideoJuegoPorEmpresa(String n){
      int i;
      boolean borro = false;
      if(!estaVacio()){ //Si hay Articulos
         for(i = 0;i < na;i++) //Lo buscamos
            if(articulo[i] instanceof VideoJuego &&
             articulo[i].getHechoPor().equals(n)){ //Si es video juego y la emprea coincide
               if(i == (na - 1)) //El video juego está al final
                  na--;
               else //El video juego no es el último elemento
                  articulo[i] = articulo[--na]; //Deja inaccesible al último valor
               borro = true;
               break; //No seguimos buscando
            }
         if(i == na && !borro) // En cado de que la empresa no se encuentra
            out.println("La empresa + \"" + n + "\" no se encontró!\n");
      }
      else
         out.println("No hay articulos almacenados\n");
   }

   
   /**
     * Método para buscar un Artículo dado el título
     * @param titulo El título del Articulo a buscar
     * @return String Los Artículos que coinciden con la búsqueda
     */
   public String buscar(String titulo){
      int i;
      String lib = "";
      String dis = "";
      String peli = "";
      String gam = "";
      if(!estaVacio()){ //Si hay Articulos
         for(i = 0;i < na;i++) //Lo buscamos
            if(articulo[i].getTitulo().equals(titulo)){ //Si titulo coincide
               if(articulo[i] instanceof Pelicula) //Es pelicula
                  peli += ((Pelicula)articulo[i]).toString() + "\n********************\n";
               if(articulo[i] instanceof Libro) //Es libro
                  lib += ((Libro)articulo[i]).toString() + "\n********************\n";
               if(articulo[i] instanceof Disco) //Es disco
                  dis += ((Disco)articulo[i]).toString() + "\n********************\n";
        if(articulo[i] instanceof VideoJuego) //Es videojuego
                  gam += ((VideoJuego)articulo[i]).toString() + "\n********************\n";
            }
         if(lib.equals(""))//No se encontraron libros
            lib = "No hay libros que mostrar!\n********************\n";
         if(peli.equals(""))//No se encontraron peliculas
            peli = "No hay peliculas que mostrar!\n********************\n";
         if(dis.equals(""))//No se encontraron discos
            dis = "No hay discos que mostrar!\n********************\n";
  if(gam.equals(""))//No se encontraron videojuegos
            gam = "No hay videojuegos que mostrar!\n********************\n";
         return "LIBROS:\n" + lib + "\nDISCOS:\n" + dis + "\nPELICULAS:\n" + peli + "\nVIDEOJUEGOS:\n" + gam;
      }
      else
         return "No hay articulos almacenados";
   }
    
    /**
     * Método para buscar un Artículo dado el autor|intérprete|actor principal|empresa desarrolladora.
     * @param autor El nombre del Artículo a buscar
     * @return String Los Artículos que coinciden con la búsqueda
     */
   public String buscarAutor(String autor){
      int i;
      String lib = "";
      String dis = "";
      String peli = "";
      String gam = "";
      if(!estaVacio()){ //Si hay Articulos
         for(i = 0;i < na;i++) //Lo buscamos
            if(articulo[i].getHechoPor().equals(autor)){ //Si autor coincide
               if(articulo[i] instanceof Pelicula) //Es pelicula
                  peli += ((Pelicula)articulo[i]).toString() + "\n********************\n";
               if(articulo[i] instanceof Libro) //Es libro
                  lib += ((Libro)articulo[i]).toString() + "\n********************\n";
               if(articulo[i] instanceof Disco) //Es disco
                  dis += ((Disco)articulo[i]).toString() + "\n********************\n";
        if(articulo[i] instanceof VideoJuego) //Es videojuego
                  gam += ((VideoJuego)articulo[i]).toString() + "\n********************\n";
            }
         if(lib.equals(""))//No se encontraron libros
            lib = "No hay libros que mostrar!\n********************\n";
         if(peli.equals(""))//No se encontraron peliculas
            peli = "No hay peliculas que mostrar!\n********************\n";
         if(dis.equals(""))//No se encontraron discos
            dis = "No hay discos que mostrar!\n********************\n";
  if(gam.equals(""))//No se encontraron videojuegos
            gam = "No hay discos que mostrar!\n********************\n";
         return "LIBROS:\n" + lib + "\nDISCOS:\n" + dis + "\nPELICULAS:\n" + peli + "\nVIDEOJUEGOS:\n" + gam;
      }
      else
         return "No hay articulos almacenados";
   }

    /**
     * Método para buscar un Artículo dado el país de origen.
     * @param country El nombre del Artículo a buscar
     * @return String Los Artículos que coinciden con la búsqueda
     */
   public String buscarPais(String country){
      int i;
      String lib = "";
      String dis = "";
      String peli = "";
      String gam = "";
      if(!estaVacio()){ //Si hay Articulos
         for(i = 0;i < na;i++) //Lo buscamos
            if(articulo[i].getPais().equals(country)){ //Si el pais coincide
               if(articulo[i] instanceof Pelicula) //Es pelicula
                  peli += ((Pelicula)articulo[i]).toString() + "\n********************\n";
               if(articulo[i] instanceof Libro) //Es libro
                  lib += ((Libro)articulo[i]).toString() + "\n********************\n";
               if(articulo[i] instanceof Disco) //Es disco
                  dis += ((Disco)articulo[i]).toString() + "\n********************\n";
        if(articulo[i] instanceof VideoJuego) //Es videojuego
                  gam += ((VideoJuego)articulo[i]).toString() + "\n********************\n";
            }
         if(lib.equals(""))//No se encontraron libros
            lib = "No hay libros que mostrar!\n********************\n";
         if(peli.equals(""))//No se encontraron peliculas
            peli = "No hay peliculas que mostrar!\n********************\n";
         if(dis.equals(""))//No se encontraron discos
            dis = "No hay discos que mostrar!\n********************\n";
  if(gam.equals(""))//No se encontraron videojuegos
            gam = "No hay discos que mostrar!\n********************\n";
         return "LIBROS:\n" + lib + "\nDISCOS:\n" + dis + "\nPELICULAS:\n" + peli + "\nVIDEOJUEGOS:\n" + gam;
      }
      else
         return "No hay articulos almacenados";
   }

    /**
     * Método para buscar un Artículo dado el anio de creación.
     * @param year El nombre del Artículo a buscar
     * @return String Los Artículos que coinciden con la búsqueda
     */
   public String buscarAnio(int year){
      int i;
      String lib = "";
      String dis = "";
      String peli = "";
      String gam = "";
      if(!estaVacio()){ //Si hay Articulos
         for(i = 0;i < na;i++) //Lo buscamos
            if(articulo[i].getAnio()==(year)){ //Si el anio coincide
               if(articulo[i] instanceof Pelicula) //Es pelicula
                  peli += ((Pelicula)articulo[i]).toString() + "\n********************\n";
               if(articulo[i] instanceof Libro) //Es libro
                  lib += ((Libro)articulo[i]).toString() + "\n********************\n";
               if(articulo[i] instanceof Disco) //Es disco
                  dis += ((Disco)articulo[i]).toString() + "\n********************\n";
        if(articulo[i] instanceof VideoJuego) //Es videojuego
                  gam += ((VideoJuego)articulo[i]).toString() + "\n********************\n";
            }
         if(lib.equals(""))//No se encontraron libros
            lib = "No hay libros que mostrar!\n********************\n";
         if(peli.equals(""))//No se encontraron peliculas
            peli = "No hay peliculas que mostrar!\n********************\n";
         if(dis.equals(""))//No se encontraron discos
            dis = "No hay discos que mostrar!\n********************\n";
  if(gam.equals(""))//No se encontraron videojuegos
            gam = "No hay discos que mostrar!\n********************\n";
         return "LIBROS:\n" + lib + "\nDISCOS:\n" + dis + "\nPELICULAS:\n" + peli + "\nVIDEOJUEGOS:\n" + gam;
      }
      else
         return "No hay articulos almacenados";
   }
    
   /**
     * Método para buscar un Libro dada la editorial
     * @param edit La editorial del Libro a buscar
     * @return String Los Libros que coinciden con la búsqueda
     */
   public String buscarEditorial(String edit){
      int i;
      String lib = "";
      if(!estaVacio()){ //Si hay Articulos
         for(i = 0;i < na;i++) //Lo buscamos
            if(articulo[i] instanceof Libro &&
             ((Libro)articulo[i]).getEditorial().equals(edit)) //Si la editorial coincide
               lib += ((Libro)articulo[i]).toString() + "\n********************\n";
         if(lib.equals(""))//No se encontraron libros
            lib = "No hay libros que mostrar!\n********************\n";
         return "LIBROS:\n" + lib;
      }
      else
         return "No hay articulos almacenados";
   }
    
   /**
     * Método para buscar un Disco, Película o Videojuego dado el idioma
     * @param id El idioma del Artículo a buscar
     * @return String Los Artículos que coinciden con la búsqueda
     */
   public String buscarIdioma(String id){
      int i;
      String dis = "";
      String peli = "";
      String gam = "";
      if(!estaVacio()){ //Si hay Articulos
         for(i = 0;i < na;i++){ //Lo buscamos
            if(articulo[i] instanceof Disco &&
             ((Disco)articulo[i]).getIdioma().equals(id)) //Si el idioma coincide
               dis += ((Disco)articulo[i]).toString() + "\n********************\n";
            if(articulo[i] instanceof Pelicula &&
             ((Pelicula)articulo[i]).getIdioma().equals(id)) //Si el idioma coincide
               peli += ((Pelicula)articulo[i]).toString() + "\n********************\n";
     if(articulo[i] instanceof VideoJuego &&
             ((VideoJuego)articulo[i]).getIdioma().equals(id)) //Si el idioma coincide
               gam += ((VideoJuego)articulo[i]).toString() + "\n********************\n";
         }
         if(peli.equals(""))//No se encontraron peliculas
            peli = "No hay peliculas que mostrar!\n********************\n";
         if(dis.equals(""))//No se encontraron discos
            dis = "No hay discos que mostrar!\n********************\n";
  if(gam.equals(""))//No se encontraron videojuegos
            gam = "No hay videojuegos que mostrar!\n********************\n";
         return "DISCOS:\n" + dis + "\nPELICULAS:\n" + peli + "\nVIDEOJUEGOS:\n" + gam;
      }
      else
         return "No hay articulos almacenados";
   }
    
    /**
     * Método para buscar un Disco, Película o Videojuego dado el género
     * @param gen El género del Artículo a buscar
     * @return String Los Artículos que coinciden con la búsqueda
     */
   public String buscarGenero(String gen){
      int i;
      String dis = "";
      String peli = "";
      String gam = "";
      if(!estaVacio()){ //Si hay Articulos
         for(i = 0;i < na;i++){ //Lo buscamos
            if(articulo[i] instanceof Disco &&
             ((Disco)articulo[i]).getGenero().equals(gen)) //Si el genero coincide
               dis += ((Disco)articulo[i]).toString() + "\n********************\n";
            if(articulo[i] instanceof Pelicula &&
             ((Pelicula)articulo[i]).getGenero().equals(gen)) //Si el genero coincide
               peli += ((Pelicula)articulo[i]).toString() + "\n********************\n";
     if(articulo[i] instanceof VideoJuego &&
             ((VideoJuego)articulo[i]).getGenero().equals(gen)) //Si el genero coincide
               gam += ((VideoJuego)articulo[i]).toString() + "\n********************\n";
         }
         if(peli.equals(""))//No se encontraron peliculas
            peli = "No hay peliculas que mostrar!\n********************\n";
         if(dis.equals(""))//No se encontraron discos
            dis = "No hay discos que mostrar!\n********************\n";
  if(gam.equals(""))//No se encontraron videojuegos
            gam = "No hay videojuegos que mostrar!\n********************\n";
         return "DISCOS:\n" + dis + "\nPELICULAS:\n" + peli + "\nVIDEOJUEGOS:\n" + gam;
      }
      else
         return "No hay articulos almacenados";
   }
    
    /**
     * Método para buscar por Actriz
     * @param act La actriz de la Película a buscar
     * @return String Las Películas que coinciden con la búsqueda
     */
   public String buscarActriz(String act){
      int i;
      String peli = "";
      if(!estaVacio()){ //Si hay Articulos
         for(i = 0;i < na;i++) //Lo buscamos
            if(articulo[i] instanceof Pelicula &&
             ((Pelicula)articulo[i]).getActriz().equals(act)) //Si la actriz coincide
               peli += ((Pelicula)articulo[i]).toString() + "\n********************\n";
         if(peli.equals(""))//No se encontraron peliculas
            peli = "No hay peliculas que mostrar!\n********************\n";
         return "PELICULAS:\n" + peli;
      }
      else
         return "No hay articulos almacenados";
   }

    /**
     * Método para buscar por Tema
     * @param theme El tema del Libro a buscar
     * @return String Los Libros que coinciden con la búsqueda
     */
   public String buscarTema(String theme){
      int i;
      String lib = "";
      if(!estaVacio()){ //Si hay Articulos
         for(i = 0;i < na;i++) //Lo buscamos
            if(articulo[i] instanceof Libro &&
             ((Libro)articulo[i]).getTema().equals(theme)) //Si el tema coincide
               lib += ((Libro)articulo[i]).toString() + "\n********************\n";
         if(lib.equals(""))//No se encontraron libros
            lib = "No hay libros que mostrar!\n********************\n";
         return "LIBROS:\n" + lib;
      }
      else
         return "No hay articulos almacenados";
   }

    /**
     * Método para buscar por Calificación
     * @param score La Calificación del Disco a buscar
     * @return String Los Discos que coinciden con la búsqueda
     */
   public String buscarCalificacion(double score){
      int i;
      String dis = "";
      if(!estaVacio()){ //Si hay Articulos
         for(i = 0;i < na;i++) //Lo buscamos
            if(articulo[i] instanceof Disco &&
             ((Disco)articulo[i]).getCalificacion() == score) //Si la calificación coincide
               dis += ((Disco)articulo[i]).toString() + "\n********************\n";
         if(dis.equals(""))//No se encontraron discos
            dis = "No hay discos que mostrar!\n********************\n";
         return "DISCOS:\n" + dis;
      }
      else
         return "No hay articulos almacenados";
   }

    /**
     * Método para buscar por Director
     * @param tarant El Director de la Película a buscar
     * @return String Las Películas que coinciden con la búsqueda
     */
   public String buscarDirector(String tarant){
      int i;
      String peli = "";
      if(!estaVacio()){ //Si hay Articulos
         for(i = 0;i < na;i++) //Lo buscamos
            if(articulo[i] instanceof Pelicula &&
             ((Pelicula)articulo[i]).getDirector().equals(tarant)) //Si el director coincide
               peli += ((Pelicula)articulo[i]).toString() + "\n********************\n";
         if(peli.equals(""))//No se encontraron peliculas
            peli = "No hay peliculas que mostrar!\n********************\n";
         return "PELICULAS:\n" + peli;
      }
      else
         return "No hay articulos almacenados";
   }

    /**
     * Método para buscar por Empresa
     * @param company La empresa del Videojuego a buscar
     * @return String Los Videojuegos que coinciden con la búsqueda
     */
   public String buscarEmpresa(String company){
      int i;
      String gam = "";
      if(!estaVacio()){ //Si hay Articulos
         for(i = 0;i < na;i++) //Lo buscamos
            if(articulo[i] instanceof VideoJuego &&
             ((VideoJuego)articulo[i]).getHechoPor().equals(company)) //Si la empresa coincide
               gam += ((VideoJuego)articulo[i]).toString() + "\n********************\n";
         if(gam.equals(""))//No se encontraron videojuegos
            gam = "No hay videojuegos que mostrar!\n********************\n";
         return "VIDEOJUEGOS:\n" + gam;
      }
      else
         return "No hay articulos almacenados";
   }

    /**
     * Método para buscar por Plataforma
     * @param platform La plataforma del Videojuego a buscar
     * @return String Los Videojuegos que coinciden con la búsqueda
     */
   public String buscarPLataforma(String platform){
      int i;
      String gam = "";
      if(!estaVacio()){ //Si hay Articulos
         for(i = 0;i < na;i++) //Lo buscamos
            if(articulo[i] instanceof VideoJuego &&
             ((VideoJuego)articulo[i]).getPlataforma().equals(platform)) //Si la plataforma coincide
               gam += ((VideoJuego)articulo[i]).toString() + "\n********************\n";
         if(gam.equals(""))//No se encontraron videojuegos
            gam = "No hay videojuegos que mostrar!\n********************\n";
         return "VIDEOJUEGOS:\n" + gam;
      }
      else
         return "No hay articulos almacenados";
   }

    /**
     * Método para buscar por Número de jugadores
     * @param players Los jugadores del Videojuego a buscar
     * @return String Los Videojuegos que coinciden con la búsqueda
     */
   public String buscarJugadores(int players){
      int i;
      String gam = "";
      if(!estaVacio()){ //Si hay Articulos
         for(i = 0;i < na;i++) //Lo buscamos
            if(articulo[i] instanceof VideoJuego &&
             ((VideoJuego)articulo[i]).getNumeroJugadores() == players) //Si los jugadores coinciden
               gam += ((VideoJuego)articulo[i]).toString() + "\n********************\n";
         if(gam.equals(""))//No se encontraron videojuegos
            gam = "No hay videojuegos que mostrar!\n********************\n";
         return "VIDEOJUEGOS:\n" + gam;
      }
      else
         return "No hay articulos almacenados";
   }
    
    /**
     * Método para mostrar toda la información almacenada
     * @return String Todos los Artículos almacenados
     */
   public String toString(){
       
      int i;
      String lib = "";
      String dis = "";
      String peli = "";
      if(!estaVacio()){ //Si hay Articulos
           //Primero vamos a acomodar las cosas y despues vamos a ver si es pelicula, libro,...
           Articulo[] copia = new Articulo[na]; //vamos a crear una copia
            for (int l = 0; l < na; l++){ 
                copia[l] = articulo[l]; //Aqui rellenamos los datos
            }
            //Ahora sí se van a acomodar las cosas
            for(int g= 0;g < na;g++)
                for(int j = g + 1;j < na;j++)
                    if(copia[g].getTitulo().compareTo(copia[j].getTitulo()) > 0){ //Intercambiamos
                        Articulo temp = copia[g];
                        copia[g] = copia[j];
                        copia[j] = temp;
            }
         //Una vez que estan acomodadas las cosas (en copia) vemos a que tipo de obras pertenecen
         for(i = 0;i < na;i++){ //Los recorremos todos
        
         if(copia[i] instanceof Pelicula) //Si es pelicula, entonces agregamos ese elemnto a una cadena
               peli += ((Pelicula)copia[i]).toString() + "\n********************\n";
         else if(copia[i] instanceof Libro) //Es libro
               lib += ((Libro)copia[i]).toString() + "\n********************\n";
         else if(copia[i] instanceof Disco) //Es disco
               dis += ((Disco)copia[i]).toString() + "\n********************\n";
         if(lib.equals(""))//No se encontraron libros
            lib = "No hay libros que mostrar!\n********************\n";
         if(peli.equals(""))//No se encontraron peliculas
            peli = "No hay peliculas que mostrar!\n********************\n";
         if(dis.equals(""))//No se encontraron discos
            dis = "No hay discos que mostrar!\n********************\n";

            }
         return "LIBROS:\n" + lib + "\nDISCOS:\n" + dis + "\nPELICULAS:\n" + peli;
            }
      else
         return "No hay articulos almacenados";   
     
   }
  
    /**
     * Método para mostrar unicamente los libros ordenados
     * @return String Todos los libors almacenados acomodados ascendentemente
     */
   
     public String LibrosAsc(){
      //Primero hay qque saber cuantos libros hay
      String libros="";
    
      int contadorLi;
      contadorLi=0;
      for (int u=0;u<na;u++){
        if(articulo[u] instanceof Libro)
        contadorLi = contadorLi + 1;}
        
     if(contadorLi==0)   //si no hay libros no hay nada que mostrar 
        return "No hay libros";
     else{ //si hay libros entonces creamos un nuevo arreglo
        
      Articulo[] Sololibros = new Articulo[contadorLi]; //en este arreglo meteremos los libros
      
      for(int i =0;i<na;i++){
         if(articulo[i] instanceof Libro){
             for (int o=0;o<contadorLi;o++){
                 Sololibros[o]=articulo[i];
                }
        }
        //una vez que se llene el arreglo ya no se pueden meter mas cosas 
    }  
    
    //ahora si podemos acomodar las cosas
      Articulo[] copiaLi = new Articulo[contadorLi]; //vamos a crear una copia de libros
            for (int l = 0; l < contadorLi; l++){ 
                copiaLi[l] =Sololibros[l]; //Aqui rellenamos los datos
            }
            //Ahora sí se van a acomodar las cosas
            for(int g= 0;g <contadorLi ;g++)
                for(int j = g + 1;j < contadorLi;j++)
                    if(copiaLi[g].getTitulo().compareTo(copiaLi[j].getTitulo()) > 0){ //Intercambiamos
                        Articulo temp = copiaLi[g];
                        copiaLi[g] = copiaLi[j];
                        copiaLi[j] = temp;
            } 
            //una vez que estan acomodadas las cosas usaremos llenaremos el strung libro 
            for(int k = 0;k <contadorLi;k++){
                libros += (k+1)+":"+ copiaLi[k].getTitulo()+"\n";
       }
       return libros;     
       }
   }
   
   
   
     /**
     * Método para mostrar unicamente las peliculas ordenadas
     * @return String Todos las peliculas almacenados acomodados ascendentemente
     */
    public String PeliculasAsc(){
      //Primero hay qque saber cuantas peliculas  hay
      String Pelis="";
    
      int contadorPe = 0; //contador de cuantas peliculas hay 
      
      for (int u=0;u<na;u++){
        if(articulo[u] instanceof Pelicula) //si es pelicula entonces se suma  uno al contador
        contadorPe = contadorPe + 1;}
        
     if(contadorPe==0)   //si no hay peliculas no hay nada que mostrar 
        return "No hay peliculas";
     else{ //si hay peliculas entonces creamos un nuevo arreglo
        
      Articulo[] SoloPe = new Articulo[contadorPe]; //en este arreglo meteremos los libros
      
      for(int i =0;i<na;i++){
         if(articulo[i] instanceof Pelicula){
             for (int o=0;o<contadorPe;o++){
                 SoloPe[o]=articulo[i];
                }
        }
        //una vez que se llene el arreglo ya no se pueden meter mas cosas 
    }  
    
    //ahora si podemos acomodar las cosas
      Articulo[] copiaPe = new Articulo[contadorPe]; //vamos a crear una copia de libros
            for (int l = 0; l < contadorPe; l++){ 
                copiaPe[l] =SoloPe[l]; //Aqui rellenamos los datos
            }
            //Ahora sí se van a acomodar las cosas
            for(int g= 0;g <contadorPe ;g++)
                for(int j = g + 1;j < contadorPe;j++)
                    if(copiaPe[g].getTitulo().compareTo(copiaPe[j].getTitulo()) > 0){ //Intercambiamos
                        Articulo temp = copiaPe[g];
                        copiaPe[g] = copiaPe[j];
                        copiaPe[j] = temp;
            } 
            //una vez que estan acomodadas las cosas usaremos llenaremos el strung libro 
            for(int k = 0;k <contadorPe;k++){
                Pelis += (k+1)+":"+ copiaPe[k].getTitulo()+"\n";
       }
       return Pelis;     
       }
   }
   
   
     /**
     * Método para mostrar unicamente los discos ordenados
     * @return String Todos los discos almacenados acomodados ascendentemente
     */
   
     public String DiscosAsc(){
      //Primero hay qque saber cuantos discos hay
      String discos="";
    
      int contadordiscos;
      contadordiscos=0;
      for (int u=0;u<na;u++){
        if(articulo[u] instanceof Disco)
            contadordiscos = contadordiscos + 1;}
        
     if(contadordiscos==0)   //si no hay discos no hay nada que mostrar 
        return "No hay discos";
     else{ //si hay discos entonces creamos un nuevo arreglo
        
      Articulo[] Solodiscos= new Articulo[contadordiscos]; //en este arreglo meteremos los discos
      
      for(int i =0;i<na;i++){
         if(articulo[i] instanceof Disco){
             for (int o=0;o<contadordiscos;o++){
                 Solodiscos[o]=articulo[i];
                }
        }
        //una vez que se llene el arreglo ya no se pueden meter mas cosas 
    }  
    
    //ahora si podemos acomodar las cosas
      Articulo[] copiadiscos = new Articulo[contadordiscos]; //vamos a crear una copia de discos
            for (int l = 0; l < contadordiscos; l++){ 
                copiadiscos[l] =Solodiscos[l]; //Aqui rellenamos los datos
            }
            //Ahora sí se van a acomodar las cosas
            for(int g= 0;g <contadordiscos ;g++)
                for(int j = g + 1;j < contadordiscos;j++)
                    if(copiadiscos[g].getTitulo().compareTo(copiadiscos[j].getTitulo()) > 0){ //Intercambiamos
                        Articulo temp = copiadiscos[g];
                        copiadiscos[g] = copiadiscos[j];
                        copiadiscos[j] = temp;
            } 
            //una vez que estan acomodadas las cosas usaremos llenaremos el string discos
            for(int k = 0;k <contadordiscos;k++){
                discos += (k+1)+":"+ copiadiscos[k].getTitulo()+"\n";
       }
       return discos;     
       }
   }
   
   
     /**
     * Método para mostrar unicamente los VideoJuegos ordenados
     * @return String Todos los VideoJuegos almacenados acomodados ascendentemente
     */
   
     public String VideoJuegosAsc(){
      //Primero hay qque saber cuantos juegos hay
      String VJ="";
    
      int contadorVJ=0;
     
      for (int u=0;u<na;u++){
        if(articulo[u] instanceof VideoJuego)
            contadorVJ = contadorVJ + 1;}
        
     if(contadorVJ==0)   //si no hay juegos no hay nada que mostrar 
        return "No hay videojuegos";
     else{ //si hay juegos, entonces creamos un nuevo arreglo
        
      Articulo[] SoloVJ= new Articulo[contadorVJ]; //en este arreglo meteremos los juegos
      
      for(int i =0;i<na;i++){
         if(articulo[i] instanceof VideoJuego){
             for (int o=0;o<contadorVJ;o++){
                 SoloVJ[o]=articulo[i];
                }
        }
        //una vez que se llene el arreglo ya no se pueden meter mas cosas 
    }  
    
    //ahora si podemos acomodar las cosas
      Articulo[] copiaVJ = new Articulo[contadorVJ]; //vamos a crear una copia de VJ
            for (int l = 0; l < contadorVJ; l++){ 
                copiaVJ[l] =SoloVJ[l]; //Aqui rellenamos los datos
            }
            //Ahora sí se van a acomodar las cosas
            for(int g= 0;g <contadorVJ;g++)
                for(int j = g + 1;j < contadorVJ;j++)
                    if(copiaVJ[g].getTitulo().compareTo(copiaVJ[j].getTitulo()) > 0){ //Intercambiamos
                        Articulo temp = copiaVJ[g];
                        copiaVJ[g] = copiaVJ[j];
                        copiaVJ[j] = temp;
            } 
            //una vez que estan acomodadas las cosas usaremos llenaremos el string discosVJ
            for(int k = 0;k <contadorVJ;k++){
                VJ += (k+1)+":"+ copiaVJ[k].getTitulo()+"\n";
       }
       return VJ;     
       }
   }

    
    /**
     * Método para actualizar un Artículo
     * @param tipo Un número con el tipo de Artículo a actualizar: Libro (1), Disco(2), Película(3), (4) VideoJuego
     * @param titulo El titulo del Artículo a actualizar
     */
   public void actualizar(int tipo,String titulo){
      Articulo articulo = buscar(tipo,titulo);
      int op;
      String au;
      do{
        out.println("1. Titulo");
        out.println("2. Otros parámetros");
        out.println("3. Salir");
        op = lector.nextInt();
        switch(op){
               case 1: //Actualizar el titulo
                out.println("Escribe el titulo");
                au = lector1.next().toUpperCase();
                articulo.setTitulo(au);
                break;
               case 2: //Actualizar los demas parametros
               if(articulo != null)
                if(articulo instanceof Libro)
                   actualizaLibro(articulo);
                else if(articulo instanceof Disco)
                   actualizaDisco(articulo);
                else if(articulo instanceof Pelicula)
                   actualizaPelicula(articulo);
                else if(articulo instanceof VideoJuego)
                   actualizaVideoJuego(articulo);
                else
                   out.println("El articulo solicitado no existe!");
                    break;
            case 3: //Saliendo de menú de actualización
               out.println("Saliendo de actualizacion");
               break;
            default: //Control de errores
               out.println("Opción no valida");
         }
        }while (op != 3);
   }
    
    /*
     * Método auxiliar para buscar un Artículo dado el tipo del mismo
     * @param a El tipo de Artículo a buscar: Libro (1), Disco(2), Película(3)
     * @param t El título del Artículo
     */
   private Articulo buscar(int a,String t){
      if(!estaVacio()){ //Hay articulos
         switch(a){
            case 1: //Es un Libro  
               for(int i = 0;i < na;i++)//Lo buscamos
                  if(articulo[i] instanceof Libro && 
                  articulo[i].getTitulo().equals(t))//Lo encontró
                     return articulo[i];
               break;
          
            case 2: //Es un Disco
               for(int i = 0;i < na;i++)//Lo buscamos
                  if(articulo[i] instanceof Disco && 
                  articulo[i].getTitulo().equals(t))//Lo encontró
                     return articulo[i];
               break;
          
            case 3: //Es una Película
               for(int i = 0;i < na;i++)//Lo buscamos
                  if(articulo[i] instanceof Pelicula && 
                  articulo[i].getTitulo().equals(t))//Lo encontró
                     return articulo[i];
               break;
         }
         return null;
      }
      else
         return null;
   }

  /*
   * Método auxiliar que permite actualizar la información de un libro
   * @param b El Libro (Artículo) que se va a actualizar
   */
   private void actualizaLibro(Articulo b){
      int op,an;
      String au;
      Libro a = (Libro)b;
      do{
         out.println("1. Autor");
         out.println("2. Editorial");
         out.println("3. Anio de publicacion");
         out.println("4. Tema de libro");
         out.println("5. Pais");
         out.println("6. Salir");
         out.println("Escribe una opcion:");
         op = lector.nextInt();
         switch(op){
            case 1: //Actualizar el autor
               out.println("Escribe el nombre del autor");
               au = lector1.next().toUpperCase();
               a.setHechoPor(au);
               break;
            case 2: //Actualizar la editorial
               out.println("Escribe el nombre de la editorial");
               au = lector1.next().toUpperCase();
               a.setEditorial(au);
               break;
            case 3: //Actualizar el año
               out.println("Escribe el anio de publicacion");
               an = lector.nextInt();
               a.setAnio(an);
               break;
            case 4: //Actualizar el tema
               out.println("Escribe el tema del libro");
               au = lector1.next().toUpperCase();
               a.setTema(au);
               break;
            case 5: //Actualizar el pais
               out.println("Escribe el pais");
               au = lector1.next().toUpperCase();
               a.setPais(au);
               break;
            case 6: //Saliendo de menú de actualización
               out.println("Saliendo de edicion de Libro");
               break;
            default: //Control de errores
               out.println("Opción no valida");
         }
      }while(op != 6);
   }
  
  /*
   * Método auxiliar que permite actualizar la información de un Disco
   * @param b El Disco (Artículo) que se va a actualizar
   */
   private void actualizaDisco(Articulo b){
      int op,an;
      double cal;
      String au;
      Disco a = (Disco)b;
      do{
         out.println("1. Interprete");
         out.println("2. Anio de lanzamiento");
         out.println("3. Genero");
         out.println("4. Idioma");
         out.println("5. Num. Canciones");
         out.println("6. Calificacion");
         out.println("7. Pais");
         out.println("8. Salir");
         out.println("Escribe una opcion:");
         op = lector.nextInt();
         switch(op){
            case 1: //Actualizar interprete
               out.println("Escribe el nombre del interprete");
               au = lector1.next().toUpperCase();
               a.setHechoPor(au);
               break;
            case 2: //Actualizar el año
               out.println("Escribe el anio de publicacion");
               an = lector.nextInt();
               a.setAnio(an);
               break;
            case 3: //Actualizar el género
               out.println("Escribe el genero del disco");
               au = lector1.next().toUpperCase();
               a.setGenero(au);
               break;
            case 4: //Actualizar el idioma
               out.println("Escribe el idioma del disco");
               au = lector1.next().toUpperCase();
               a.setIdioma(au);
               break;
            case 5: //Actualizar el número de canciones
               out.println("Escribe el num. de canciones");
               an = lector.nextInt();
               a.setNumero(an);
               break;
            case 6: //Actualizar la calificacion
               out.println("Escribe la calificacion");
               cal = lector.nextDouble();
               a.setCalificacion(cal);
               break;
            case 7: //Actualizar el pais
               out.println("Escribe el pais de origen");
               au = lector1.next().toUpperCase();
               a.setPais(au);
               break;
            case 8: //Salir de menú de actualización
               out.println("Saliendo de edicion de Disco");
               break;
            default: //Control de errores
               out.println("Opcion no valida");
         }
      }while(op != 8);
   }
  
  /*
   * Método auxiliar que permite actualizar la información de una Película
   * @param b La Película (Artículo) que se va a actualizar
   */
   private void actualizaPelicula(Articulo b){
      int op,an;
      double du;
      String au;
      Pelicula a = (Pelicula)b;
      do{
         out.println("1. Actor");
         out.println("2. Anio de estreno");
         out.println("3. Genero");
         out.println("4. Idioma");
         out.println("5. Duracion");
         out.println("6. Actriz");
         out.println("7. Actriz");
         out.println("8. Actriz");
         out.println("9. Actriz");
         out.println("10. Salir");
         out.println("Escribe una opcion:");
         op = lector.nextInt();
         switch(op){
            case 1: //Actualizar el actor
               out.println("Escribe el nombre del actor");
               au = lector1.next().toUpperCase();
               a.setHechoPor(au);
               break;
            case 2: //Actualizar el año
               out.println("Escribe el anio de estreno");
               an = lector.nextInt();
               a.setAnio(an);
               break;
            case 3: //Actualizar el genero
               out.println("Escribe el genero de la pelicula");
               au = lector1.next().toUpperCase();
               a.setGenero(au);
               break;
            case 4: //Actualizar idioma
               out.println("Escribe el idioma de la pelicula");
               au = lector1.next().toUpperCase();
               a.setIdioma(au);
               break;
            case 5: //Actualizar la duración
               out.println("Escribe la duracion");
               du = lector.nextDouble();
               a.setDuracion(du);
               break;
            case 6: //Actualizar el nombre de la actriz
               out.println("Escribe el nombre de la actriz principal");
               au = lector1.next().toUpperCase();
               a.setActriz(au);
               break;
            case 7: //Actualizar el director
               out.println("Escribe el nombre del director");
               au = lector1.next().toUpperCase();
               a.setDirector(au);
               break;
            case 8: //Actualizar los subtitulos
               out.println("Escribe los subtitulos");
               au = lector1.next().toUpperCase();
               a.setSubtitulos(au);
               break;
            case 9: //Actualizar el pais
               out.println("Escribe el nombre del pais");
               au = lector1.next().toUpperCase();
               a.setPais(au);
               break;
            case 10: //Salir del menú de actualización
               out.println("Saliendo de edicion de Pelicula");
               break;
            default: //Control de errores
               out.println("Opcion no valida");
         }
      }while(op != 10);
   }   
   
     /*
   * Método auxiliar que permite actualizar la información de un VideoJuego
   * @param b El VideoJuego (Artículo) que se va a actualizar
   */
   private void actualizaVideoJuego(Articulo b){
      int op,an;
      double du;
      String au;
      VideoJuego a = (VideoJuego)b;
      do{
         out.println("1. Empresa");
         out.println("2. Anio de lanzamiento");
         out.println("3. Genero");
         out.println("4. Idioma");
         out.println("5. Plataforma");
         out.println("6. Numero de jugadores");
         out.println("7. Pais");
         out.println("8. Salir");
         out.println("Escribe una opcion:");
         op = lector.nextInt();
         switch(op){
            case 1: //Actualizar la empresa
               out.println("Escribe la empresa desarrolladora");
               au = lector1.next().toUpperCase();
               a.setHechoPor(au);
               break;
            case 2: //Actualizar el año
               out.println("Escribe el anio de lanzamiento");
               an = lector.nextInt();
               a.setAnio(an);
               break;
            case 3: //Actualizar el genero
               out.println("Escribe el genero del videojuego");
               au = lector1.next().toUpperCase();
               a.setGenero(au);
               break;
            case 4: //Actualizar idioma
               out.println("Escribe el idioma del videojuego");
               au = lector1.next().toUpperCase();
               a.setIdioma(au);
               break;
            case 5: //Actualizar la plataforma
               out.println("Escribe la plataforma");
               au = lector.next().toUpperCase();
               a.setPlataforma(au);
               break;
            case 6: //Actualizar el numero de jugadores
               out.println("Escribe el numero de jugadores");
               an = lector1.nextInt();
               a.setNumeroJ(an);
               break;
            case 7: //Actualizar el pais
               out.println("Escribe el pais");
               au = lector.next().toUpperCase();
               a.setPais(au);
               break;
            case 8: //Salir del menú de actualizacion
               out.println("Saliendo de edicion de VideoJuego");
               break;
            default: //Control de errores
               out.println("Opcion no valida");
         }
      }while(op != 8);
   }   
   
   
    /*****************************************************
    *****INFRAESTRUCTURA PARA LECTURA Y ESCRITURA*******
    ***********DE ARCHIVOS EN EN JAVA*******************
    ****************************************************
  */
  /**
   * Metodo que permite contar las lineas de un archivo
   * @param archivo Una cadena con el nombre del archivo
   * @return int El numero de lineas que tiene el archivo
   * @throws FileNotFoundException En caso de que el archivo no exista
   * @throws IOException En caso de que hubiera problemas con el flujo I/O
   */
  public int contarLineas(String archivo) throws FileNotFoundException,
                                                 IOException{
    FileReader fr = new FileReader(archivo); //Indentifica el archivo para lectura
    BufferedReader bfr = new BufferedReader(fr); //Se crea el buffer de lectura
    String linea;
    int nlineas = 0;
    linea = bfr.readLine(); //Leemos la primera línea
    while(linea != null){ //Mientras no lleguemos al EOF
      nlineas++; //Contamos la linea leída
      linea = bfr.readLine(); //Leemos la siguiente línea
    }
    return nlineas; //Devolvemos el total de lineas contadas
  }
   
  /**
   * Metodo que permite leer las lineas de un archivo y almacenarlas
   * en un arreglo
   * @param archivo Una cadena con el nombre del archivo
   * @throws FileNotFoundException En caso de que el archivo no exista
   * @throws IOException En caso de que hubiera problemas con el flujo
   */
  public void archivoAArreglo(String archivo) throws FileNotFoundException,
                                                     IOException{
    //Creamos el arreglo en función de las líneas que se hayan leído
    articulo = new Articulo[contarLineas(archivo)]; // aqui iba el +20 pero se lo quite pues ya puede crecer el arreglo
    FileReader fr = new FileReader(archivo); //Creamos el archivo de lectura
    BufferedReader bfr = new BufferedReader(fr); //Creamos el buffer de lectura
    String linea, cad[];
    linea = bfr.readLine(); //Leemos la primera linea
    while(linea != null){ //Mientras no lleguemos al EOF
      cad = linea.split(","); //Separamos la cadena
      
      if(cad[0].equals("L"))//Se trata de un Libro
/** 
 * Recordemos que los parametros que recibe el libro son:
 * 1 El título del Libro,2 El nombre de la persona que lo escribió, 3 El anio en que se hizo el Libro,
 * 4 El país de origen del Libro, 5 La editorial del Libro  y  6 El tema del Libro (en ese orden)
*/
      
        agregar(new Libro(cad[1].toUpperCase(), //Titulo
                          cad[2].toUpperCase(), //Quien lo escribió
                          Integer.parseInt(cad[3]), //Año
                          cad[4].toUpperCase(), //Pais 
                          cad[5].toUpperCase(),//editorial
                          cad[6].toUpperCase()//tema
                          )); //fin de la creacion
      
      else if(cad[0].equals("D"))//Se trata de un Disco
/**
   * Recordemos que el disco recibe estos parametros:
   * 1 El título del Disco, 2 El nombre de la persona que lo interpreta, 3 El anio en que se hizo lanzó el Disco, 4 El país de origen del Disco,
   * 5 El género del Disco, 6 El idioma del Disco, 7  El número de canciones del Disco y 8 La calificación del Disco (en ese orden)

   */      
        agregar(new Disco(cad[1].toUpperCase(), //Titulo
                           cad[2].toUpperCase(), //Interprete
                           Integer.parseInt(cad[3]), //Año
                           cad[4].toUpperCase(), //Pais
                           cad[5].toUpperCase(), //Genero
                           cad[6].toUpperCase(),//Idioma
                           Integer.parseInt(cad[7]),//Numero de canciones
                           Double.parseDouble(cad[8]) //calificacion 
                           )); //Fin de la creacion
                           
                           
       else if(cad[0].equals("V"))//Se trata de un VideJuego
/**
   * Recordemos que el VideoJuego recibe estos parametros:
   * 1 El título del Video Juego, 2 El nombre de la empresa que lo desarrolló
   * 3 El anio en que se lanzó eldisco, 4 El país de origen del disco
   * 5 El género del Video Juego, 6 El idioma del Video Juego
   * 7 Plataforma , 8 edicion y 9 El número de jugadores del Video Juego (en ese orden)
   */      
        agregar(new VideoJuego(cad[1].toUpperCase(), //Titulo
                           cad[2].toUpperCase(), //Empresa
                           Integer.parseInt(cad[3]), //Año
                           cad[4].toUpperCase(), //Pais
                           cad[5].toUpperCase(), //Genero
                           cad[6].toUpperCase(),//Idioma
                           cad[7].toUpperCase(),//No.canciones
                           Integer.parseInt(cad[8]), //Edicion
                           Integer.parseInt(cad[9])
                           )); //Fin de la creacion                     
      
      else if(cad[0].equals("P"))//Se trata de una Pelicula
/**
   * Recordemos que la pelicula recibe estos parametros:
   * 1 El título de la Película, 2 El nombre del actor principal, 3 El anio en que se estrenó
   * 4 El país de origen de la Película, 5 El género de la Película, 6 El idioma de la Película
   * 7 La duración en minutos de la Película, 8 El nombre de la actriz principal
   * 9 El nombre del director, 10 El idioma de los subtítulos
   */      
      
        agregar(new Pelicula(cad[1].toUpperCase(), //Título
                              cad[2].toUpperCase(), //Actor principal
                              Integer.parseInt(cad[3]), //Año
                              cad[4].toUpperCase(), //Pais
                              cad[5].toUpperCase(), //Genero
                              cad[6].toUpperCase(),//idioma
                              Double.parseDouble(cad[7]), //Duración
                              cad[8].toUpperCase(),//Actriz
                              cad[9].toUpperCase(),//Director
                              cad[10].toUpperCase()//Subtitulos
                              )); //Actriz
      
      linea = bfr.readLine(); //Leemos la siguiente línea
    }
  }
  
  /**
   * Metodo que permite almacenar los Artículos en un archivo de texto
   * @param archivo el nombre del archivo a almacenar
   * @throws IOException En caso de que hubiera problemas con el flujo
   */
  public void guardarArchivo(String archivo) throws IOException{
    FileWriter fw = new FileWriter(archivo); //Creamos el archivo de escritura
    BufferedWriter bfw = new BufferedWriter(fw); //Creamos el buffer de escritura
    //BufferedWriter bfw = new BufferedWriter(fw,true); <--Indica un archivo para append
    PrintWriter pw = new PrintWriter(bfw); //Creamos la "impresora"
    String linea = "";
    for(int i = 0;i < na;i++){
      if(articulo[i] instanceof Libro){ //Si es un Libro
        Libro l = (Libro)articulo[i]; //Se considera como Libro
        //Llamamos a todos los metodos get para obtener la informacion del libro, que son: 
        //Titulo, HechoPor,Anio,Pais, Editotial y Tema
        linea += "L, " + l.getTitulo() + ", " + l.getHechoPor() + ", " +
               l.getAnio() + ", " + l.getPais() +", " + l.getEditorial() + ", " + l.getTema();
      }
      
      
      else if(articulo[i] instanceof Disco){ //Si es un Disco
        Disco d = (Disco)articulo[i]; //Se considera como Disco
        //Llamamos a todos los metodos get para obtener la informacion del disco, que son;
        //Titulo, HechoPor,Anio,Pais,genero, idioma, numero y calificacion
        linea += "D, " + d.getTitulo() + ", " + d.getHechoPor() + ", " +
               d.getAnio() + ", " + d.getPais() + ", " + d.getGenero()+ "," + 
               d.getIdioma() +", " + d.getNumero() + ", " + d.getCalificacion();
      }
       
      else if(articulo[i] instanceof VideoJuego){ //Si es un juego
        VideoJuego v = (VideoJuego)articulo[i]; //Se considera como juego
        //Llamamos a todos los metodos get para obtener la informacion del juego, que son;
        //Titulo, HechoPor,Anio,Pais,Genero, Idioma, Plataforma, Edicion y NumeroJugadores
        linea += "V, " + v.getTitulo() + ", " + v.getHechoPor() + ", " +
               v.getAnio() + ", " + v.getPais() + ", " + v.getGenero()+ "," + 
               v.getIdioma() +", " + v.getPlataforma() + ", " + v.getEdicion() + ", "+ v.getNumeroJugadores();
               
      }
      
      
      
      else if(articulo[i] instanceof Pelicula){ //Si es una Película
        Pelicula p = (Pelicula)articulo[i]; //Se considera como Película
        //Llamamos a todos los metodos get para obtener la informacion de la pelicula, que son;
        //Titulo, HechoPor,Anio,Pais,Genero, Idioma, Actriz, Director, Subtitulos, Duracion
        linea += "P, " + p.getTitulo() + ", " + p.getHechoPor() + ", " +
               p.getAnio() + ", " + p.getPais() + ", " + p.getGenero()+ ", " + p.getIdioma() + ", " + 
               p.getDuracion()  + ", " + p.getActriz() + ", " + p.getDirector()  +
               "," + p.getSubtitulos();
      }
      linea += "\n";
      //pw.println(linea); //Imprimimos la línea actual
      //linea = ""; //Limpiamos la línea
    }
    pw.println(linea); //Imprimimos la línea actual
    linea = ""; //Limpiamos la línea
    pw.close(); //Cerramos el flujo de escritura, ¡Muy importante!
  }
}