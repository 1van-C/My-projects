/**
 * Detección de aliteraciones en input.
 * @author Carlos Iván Canto Varela
 * @version Final
 * @return Void, pero imprime las aliteraciones por línea de input.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Alliteration{
    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ir);
       
        while(in.ready()) {
            String line = in.readLine();
            String lineu = line.toUpperCase();
            String[] process = lineu.split(" ");
            int count = 0;
            for (int i=0, leng = process.length; i < leng-1;){
                if(process[i].charAt(0)==process[i+1].charAt(0)){
                    count += 1;
                    int n = i;
                    while (process[n].charAt(0)==process[i].charAt(0)){
                        if(i == leng-1){
                            break;
                        }
                        i += 1;
                    }
                }
                else{
                    i += 1;
                }
            }
            System.out.println(count);
        }
    }    
}