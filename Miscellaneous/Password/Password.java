/**
 * Detección de contraseñas válidas en la entrada mediante regex.
 * @author Carlos Iván Canto Varela
 * @version Final
 * @return Void, pero imprime el veredicto.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 

public class Password{
    
    public static boolean validatePassword(String pwd){
        return pwd.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([0-9A-Za-z]{6,32})$");
    }
    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ir);
        
        while(in.ready()) {
            String line = in.readLine();
            if (validatePassword(line)){
                System.out.println("Senha valida.");
            }
            else{
                System.out.println("Senha invalida.");
            }
        }
    }
}