package application;

public class Star {
	
    public int[][] AddStar(char a){
    	
    	int[][] star = new int[20][20];
    	
    	if(a == 'b') {
    		
            int counter = 0;

            while(counter < 10) {

                star[OddNumber()][OddNumber()] = 1;
                counter++;
            }
            return star;
    		
    	}
    	
    	else if(a == 'w') {

             int counter = 0;

             while(counter < 10) {

                 star[EvenNumber()][EvenNumber()] = 2;
                 counter++;
             }
             return star;
    	}
    	else {
    		return star;
    	}
        
    }
    
    public int OddNumber() {
    	
    	

        int x = (int)(Math.random()*19);

        if(x % 2 == 1) {
            return x;
        }
        else {
            return x + 1;
        }
    }
    
    public int EvenNumber() {
    	
    	
    	
        int x = (int)(Math.random()*19);

        if(x % 2 == 0) {
            return x;
        }
        else {
            return x + 1;
        }
    }


}
