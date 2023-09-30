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
    //Los paises permitidos fueron sacados de https://es.wikipedia.org/wiki/América.
    
    public static boolean validarCorreo(String email){
        return email.matches("^[a-zA-Z0-9_-]+@(gmail|hotmail).com$");
    }
    
    public static boolean validarUsuario(String user){
        return user.matches("^@[a-zA-Z0-9_-]+$");
    }
    
    public static void main(String[] args){
    
        Scanner input = new Scanner(System.in);
        System.out.println("Bienvenido a Twitter. Crea tu cuenta a continuacion.");
        
        System.out.println("Ingresa tu(s) nombre(s).");
        String nombre;
        nombre = input.nextLine();
        while(!validarNombre(nombre)){
            System.out.println("Ingresa un nombre valido.");
            nombre = input.nextLine();
        }
        
        System.out.println("Ingresa tu(s) apellido(s).");
        String apellido;
        apellido = input.nextLine();
        while(!validarApellido(apellido)){
            System.out.println("Ingresa un apellido valido.");
            apellido = input.nextLine();
        }
        
        System.out.println("Ingresa tu fecha de nacimiento en el formato dd/mm/aaaa.");
        String fecha;
        fecha = input.nextLine();
        while(!validarFecha(fecha)){
            System.out.println("Ingresa una fecha valida.");
            fecha = input.nextLine();
        }
        
        System.out.println("Ingresa tu pais de residencia.");
        String pais;
        pais = input.nextLine();
        while(pais.equals("EUA") || !validarPais(pais)){
            if(pais.equals("EUA")){
                System.out.println("Lo sentimos, somos racistas contra los gringos. Ingresa un verdadero pais.");
                pais = input.nextLine();
            } else if (!validarPais(pais)){
                System.out.println("Ingresa un pais valido.");
                pais = input.nextLine();
            }
        }
        
        System.out.println("Ingresa tu correo electronico.");
        String correo;
        correo = input.nextLine();
        while(!validarCorreo(correo)){
            System.out.println("Ingresa un correo valido.");
            correo = input.nextLine();
        }
        
        System.out.println("Ingresa tu nombre de usuario.");
        String usuario;
        usuario = input.nextLine();
        while(!validarUsuario(usuario)){
            System.out.println("Ingresa un usuario valido.");
            usuario = input.nextLine();
        }
        
        System.out.println("¡Bienvenido a Twitter!");
    }
}
