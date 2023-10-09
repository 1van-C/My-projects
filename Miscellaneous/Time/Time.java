/**
 * Time counter for the duration of a football match.
 * @author Carlos Iván Canto Varela
 * @version Final
 * @return Void, but prints the match duration for each line of the input.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Time{
    public static void main(String[] args) throws IOException {

        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ir);
        
        String hours, minutes;
        int h, m, h1, h2, m1, m2;

        while(in.ready()){
            String line = in.readLine();
            String[] list = line.split(" ");
            h1 = Integer.parseInt(list[0]);
            h2 = Integer.parseInt(list[2]);
            m1 = Integer.parseInt(list[1]);
            m2 = Integer.parseInt(list[3]);

            if (h2 - h1 < 0){
                if(m2 - m1 < 0){
                    h = 23 + h2 - h1;
                    m = 60 + m2 - m1;
                }
                else{
                    h = 24 + h2 - h1;
                    m = m2 - m1;
                }
                hours = Integer.toString(h);
                minutes = Integer.toString(m);
            }

            else if (h2 == h1){
                if(m2 == m1){
                    h = 24;
                    m = 0;
                }
                else if (m2 - m1 < 0){
                    h = 23;
                    m = 60 + m2 - m1;
                }
                else{
                    h = 0;
                    m = m2 - m1;
                }
                hours = Integer.toString(h);
                minutes = Integer.toString(m);
            }

            else if (h2 == h1 + 1){
                if(m2 - m1 < 0){
                    h = 0;
                    m = 60 + m2 - m1;
                }
                else{
                    h = 1;
                    m = m2 - m1;
                }
                hours = Integer.toString(h);
                minutes = Integer.toString(m);
            }

            else if (h2 == 24){
                if (h2==0){
                    if (m2 - m1 < 0){
                        h = 23;
                        m = 60 + m2 - m1;
                    }
                    else{
                        h = 0;
                        m = m2 - m1;
                    }
                }
                if (m2 - m1 < 0){
                    h = 23 - h1;
                    m = 60 + m2 - m1;
                }
                else{
                    h = h2 - h1;
                    m = m2 - m1;
                }
                hours = Integer.toString(h);
                minutes = Integer.toString(m);
            }
            
            else{
                if(m2 - m1 < 0){
                    h = h2 - h1 - 1;
                    m = 60 + m2 - m1;
                }
                else{
                    h = h2 - h1;
                    m = m2 - m1;
                }
                hours = Integer.toString(h);
                minutes = Integer.toString(m);
            }
            System.out.println("O JOGO DUROU " + hours + " HORA(S) E " + minutes + " MINUTO(S)");
        }
    }
}