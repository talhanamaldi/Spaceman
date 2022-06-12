package application;
import java.util.*;
public class Wall {
	
	//There are some types of walls which straight type of L
	
	//L types Walls
	
	public int[][] threeSquareL(){
		int[][] Lwall = new int[2][2];
		
		 
		
		int x = (int)(Math.random()*18);
		int y = (int)(Math.random()*18);
				
				Lwall[x][y] = 3;
				Lwall[x][y + 1] = 3;
				Lwall[x + 1][y + 1] = 3;
			
		return Lwall;
	}

	public int[][] fiveSquareL(){
		int[][] Lwall = new int[3][3];
		
		 

		int x = (int)(Math.random()*17); 
		int y = (int)(Math.random()*17);
		
				Lwall[x][y] = 3;
				Lwall[x][y + 1] = 3;
				Lwall[x][y + 2] = 3;
				Lwall[x + 1][y + 2] = 3;
				Lwall[x + 2][y + 2] = 3;
		
		return Lwall;
	}
	
	public int[][] sevenSquareL(){
		int[][] Lwall = new int[4][4];
		
		 

		int x = (int)(Math.random()*16);
		int y =(int)(Math.random()*16);
		
				Lwall[x][y] = 3;
				Lwall[x][y + 1] = 3;
				Lwall[x][y + 2] = 3;
				Lwall[x][y + 3] = 3;
				Lwall[x + 1][y + 3] = 3;
				Lwall[x + 2][y + 3] = 3;
				Lwall[x + 3][y + 3] = 3;
			
		return Lwall;
	}
	
	public int[][] nineSquareL(){
		int[][] Lwall = new int[5][5];
		
		 

		int x = (int)(Math.random()*15);
		int y = (int)(Math.random()*15);
		
				Lwall[x][y] = 3;
				Lwall[x][y + 1] = 3;
				Lwall[x][y + 2] = 3;
				Lwall[x][y + 3] = 3;
				Lwall[x][y + 4] = 3;
				Lwall[x + 1][y + 4] = 3;
				Lwall[x + 2][y + 4] = 3;
				Lwall[x + 3][y + 4] = 3;
				Lwall[x + 4][y + 4] = 3;
			
		return Lwall;
	}
	
	//T types Walls
	
	public int[][] fourSquareT(){
		int[][] Twall = new int[3][3];
		
		 

		int x = (int)(Math.random()*17);
		int y = (int)(Math.random()*18);
		
				Twall[x][y] = 3;
				Twall[x + 1][y] = 3;
				Twall[x + 2][y] = 3;
				Twall[x + 1][y + 1] = 3;
			
		return Twall;
	}
	
	public int[][] sixSquareT(){
		int[][] Twall = new int[4][4];
		
		 

		int x = (int)(Math.random()*17);
		int y = (int)(Math.random()*16);
		
				Twall[x][y] = 3;
				Twall[x + 1][y] = 3;
				Twall[x + 2][y] = 3;
				Twall[x + 1][y + 1] = 3;
				Twall[x + 1][y + 2] = 3;
				Twall[x + 1][y + 3] = 3;
				
			
		return Twall;
	}
	
	//Straight Walls ( | and --)
	
	public int[][] fiveSquareVerticalLine(){
		int[][] Vwall = new int[20][20];
		
		 

		int x = (int)(Math.random()*19);
		int y = (int)(Math.random()*15);
		
				Vwall[x][y] = 3;
				Vwall[x][y + 1] = 3;
				Vwall[x][y + 2] = 3;
				Vwall[x][y + 3] = 3;
				Vwall[x][y + 4] = 3;
			
				
			
		return Vwall;
	}
	
	public int[][] fiveSquareHorizontalLine(){
		int[][] Hwall = new int[20][20];
		
		  

		int x = (int)(Math.random()*15);
		int y = (int)(Math.random()*19);
		
				Hwall[x][y] = 3;
				Hwall[x + 1][y] = 3;
				Hwall[x + 2][y] = 3;
				Hwall[x + 3][y] = 3;
				Hwall[x + 4][y] = 3;
				
			
		return Hwall;
	}
	
	//Dot Walls
	
	public int[][] DotWall(){
		int[][] Dwall = new int[20][20];
		
			int x = (int)(Math.random()*19);
			int y = (int)(Math.random()*19);
		
				Dwall[x][y] = 3;
	 	
			
		return Dwall;
	}
	
	
	public int[][] RandomWall(){
		
		int[][] RWall = new int[20][20];
		
		int x = (int)(Math.random()*15);
		int y = (int)(Math.random()*13);
		
	
		RWall[x][y] = 3;
		RWall[x][y + 1] = 3;
		RWall[x][y + 2] = 3;
		RWall[x][y + 3] = 3;
		RWall[x][y + 4] = 3;
		RWall[x + 1][y + 4] = 3;
		RWall[x + 2][y + 4] = 3;
		RWall[x + 3][y + 4] = 3;
		RWall[x + 4][y + 4] = 3;
		RWall[x + 4][y + 5] = 3;
		RWall[x + 4][y + 6] = 3;
		
		return RWall;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
