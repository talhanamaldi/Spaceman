package application;
import java.util.*;
public class Score {

    public static int score = 1000;
    
    public static int addScore (int x, int y, String m) {
        if (m.equals("W") && Main.Yposition > 0) {
            score -= 10;
            if (Control.BlueStarControl(x,y)) {
                score += 50;
                System.out.println("Blue star found: 50 points");
                return score;
            }

           
            if(Control.WhiteStarControl(x, y)) {
                score += 60;
                System.out.println("White star found: 60 points");
                return score;
            }
           

            if(Control.AlienControl(x, y)) {

                score -= 40;
                System.out.println("Alien! You have lost 40 points");
                return score;

            }
           
            if (score <= 0) {
            	
                System.out.println("Game over!");
                return -1;
            }
            return score;
            }
        
        
        if (m.equals("A") && Main.Xposition > 0) {
            score -= 10;
            if (Control.BlueStarControl(x,y)) {
                score += 50;
                System.out.println("Blue star found: 50 points");
                return score;
            }

           
            if(Control.WhiteStarControl(x, y)) {
                score += 60;
                System.out.println("White star found: 60 points");
                return score;
            }
           

            if(Control.AlienControl(x, y)) {

                score -= 40;
                System.out.println("Alien! You have lost 40 points");
                return score;

            }
            
            if (score <= 0) {
            	
                System.out.println("Game over!");
                return -1;
            }
            
            return score;
            }
        
        
        if (m.equals("S") && Main.Yposition < 19) {
            score -= 10;
            if (Control.BlueStarControl(x,y)) {
                score += 50;
                System.out.println("Blue star found: 50 points");
                return score;
            }

           
            if(Control.WhiteStarControl(x, y)) {
                score += 60;
                System.out.println("White star found: 60 points");
                return score;
            }
           

            if(Control.AlienControl(x, y)) {

                score -= 40;
                System.out.println("Alien! You have lost 40 points");
                return score;

            }
            
            if (score <= 0) {
            	
                System.out.println("Game over!");
                return -1;
            }
            
            return score;
            }
        
        
        if (m.equals("D") && Main.Xposition < 19) {
            score -= 10;
            if (Control.BlueStarControl(x,y)) {
                score += 50;
                System.out.println("Blue star found: 50 points");
                return score;
            }

           
            if(Control.WhiteStarControl(x, y)) {
                score += 60;
                System.out.println("White star found: 60 points");
                return score;
            }
           

            if(Control.AlienControl(x, y)) {

                score -= 40;
                System.out.println("Alien! You have lost 40 points");
                return score;

            }
            
            if (score <= 0) {
            	
                System.out.println("Game over!");
                return -1;
            }
            
            return score;
            }
        
        return score;
    }
   }