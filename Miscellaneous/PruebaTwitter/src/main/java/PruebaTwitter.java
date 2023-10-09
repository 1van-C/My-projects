import java.util.Scanner;

public class PruebaTwitter{
  
    public static boolean validarNombre(String name){
        return name.matches("^[A-Z]{1}[a-z]+" + "(\\s[A-Z][a-z]+)*$");
    }
    
    public static boolean validarApellido(String lastname){
        return lastname.matches("^[A-Z]{1}[a-z]+" + "(\\s[A-Z][a-z]+)?$");
    }
    
    public static boolean validarFecha(String birth){
        return birth.matches("(?!19/11/[0-9]{4})(0[1-9]|1[0-9]|2[0-9]|3[0-1])/" + "(0[1-9]|1[0-2])/" +
                "(19[0-9]{2}|20(0[0-9]|1[0-9]|2[0-2]))$");
    }
    
    public static boolean validarPais(String country){
        return country.matches("^ANT|ARG|BAH|BAR|BEL|BOL|BRA|CAN|CHI|COL|COS|CUB|DOM|ECU|ELS|GRA|GUA|GUY|HAI|HON|JAM"
                + "|MEX|NIC|PAN|PAR|PER|REP|SAC|SAV|SAL|SUR|TRI|URU|VEN$");
    }
    //The allowed countries come from: https://es.wikipedia.org/wiki/América.
    
    public static boolean validarCorreo(String email){
        return email.matches("^[a-zA-Z0-9_-]+@(gmail|hotmail).com$");
    }
    
    public static boolean validarUsuario(String user){
        return user.matches("^@[a-zA-Z0-9_-]+$");
    }
    
    public static void main(String[] args){
    
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Twitter. Create your account.");
        
        System.out.println("Input your name(s).");
        String nombre;
        nombre = input.nextLine();
        while(!validarNombre(nombre)){
            System.out.println("Input a valid name.");
            nombre = input.nextLine();
        }
        
        System.out.println("Input your last name(s).");
        String apellido;
        apellido = input.nextLine();
        while(!validarApellido(apellido)){
            System.out.println("Input a valid last name.");
            apellido = input.nextLine();
        }
        
        System.out.println("Input your birth date in the format dd/mm/yyyy.");
        String fecha;
        fecha = input.nextLine();
        while(!validarFecha(fecha)){
            System.out.println("Input a valid date.");
            fecha = input.nextLine();
        }
        
        System.out.println("Input your country of residence.");
        String pais;
        pais = input.nextLine();
        while(pais.equals("EUA") || !validarPais(pais)){
            if(pais.equals("EUA")){
                System.out.println("We are sorry, we do not like Americans. Input a better country.");
                pais = input.nextLine();
            } else if (!validarPais(pais)){
                System.out.println("Input a valid country.");
                pais = input.nextLine();
            }
        }
        
        System.out.println("Input your e-mail.");
        String correo;
        correo = input.nextLine();
        while(!validarCorreo(correo)){
            System.out.println("Input a valid e-mail.");
            correo = input.nextLine();
        }
        
        System.out.println("Input your username.");
        String usuario;
        usuario = input.nextLine();
        while(!validarUsuario(usuario)){
            System.out.println("Input a valid username.");
            usuario = input.nextLine();
        }
        
        System.out.println("Welcome to Twitter!");
    }
}
