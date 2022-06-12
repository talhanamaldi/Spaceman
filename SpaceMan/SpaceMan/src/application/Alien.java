package application;
import java.util.*;

public class Alien {
	

        public int[][] AddAlien(){

            int[][] alien = new int[20][20];

            int counter = 0;

            while(counter < 30) {

                alien[Number()[0]][Number()[1]] = 4;
                counter++;
            }
            return alien;
        }


        public int[] Number() {
        	
        	int[] Number = new int[2];
        	
            int x = (int)(Math.random()*19);
            int y = (int)(Math.random()*19);

            if(x % 2 == 0 && y % 2 == 1) {
                Number[0] = x;
                Number[1] = y;
            }

            else if(y % 2 == 0 && x % 2 == 1) {
            	   Number[0] = x;
                   Number[1] = y;
            }
            else if(y % 2 == 0 && x % 2 == 0) {
         	   Number[0] = x + 1;
                Number[1] = y;
            }	
            else  {
            	Number[0] = x;
            	Number[1] = y + 1;
            }
            return Number;
        }
        
}
    
