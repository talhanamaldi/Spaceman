package application;
import java.util.Arrays;

public class MAP {
	
	public static int[][] PlayerMAP = new int[20][20];
	public static int SolutionPath = 5;
	public static int tried = 6;
	
	
	public int[][] AddObjects(){ //We created the instances of the objects
		
		
		BlueStar bluestar = new BlueStar();
		WhiteStar whitestar = new WhiteStar();
		Alien alien = new Alien();
		Wall walls = new Wall();
		
		int[][] Alien = new int[20][20];
		Alien = alien.AddAlien();
		int[][] WhiteStar = new int[20][20];
		WhiteStar = whitestar.AddStar('w');
		int[][] BlueStar = new int[20][20];
		BlueStar = bluestar.AddStar('b');
		int[][] Wall = new int[20][20];
		Wall = walls.RandomWall();
		int[][] DotWall = new int[20][20];
		DotWall = walls.DotWall();
		
		
		for(int y = 0 ; y < PlayerMAP.length  ; y++) {    //We added the walls first because of precedence. We do not want that Wall array do not overlap the other objects array
			for(int x = 0 ; x < PlayerMAP.length ; x++) {
				PlayerMAP[x][y] = Wall[x][y];
			}
		}
		
		
		for(int j = 0 ; j < PlayerMAP.length ; j++) {
			for(int i = 0 ; i < PlayerMAP.length ; i++) {
		
				if(DotWall[i][j] != 0 && PlayerMAP[i][j] == 0 )
					PlayerMAP[i][j] = DotWall[i][j];
				
				if(BlueStar[i][j] != 0 && PlayerMAP[i][j] == 0 )
					PlayerMAP[i][j] = BlueStar[i][j];
				
				if(WhiteStar[i][j] != 0 && PlayerMAP[i][j] == 0 )
					PlayerMAP[i][j] = WhiteStar[i][j];
			     
				if(Alien[i][j] != 0 && PlayerMAP[i][j] == 0 ) {
					PlayerMAP[i][j] = Alien[i][j];   	
			}	  
		}
	}
		
		PlayerMAP[1][19] = 3;
		PlayerMAP[2][19] = 3;
		PlayerMAP[3][19] = 3;
		PlayerMAP[3][18] = 3;
		PlayerMAP[3][17] = 3;			
		PlayerMAP[4][17] = 3;
		PlayerMAP[5][17] = 3;
		
		PlayerMAP[18][12] = 3;
		PlayerMAP[17][13] = 3;
		PlayerMAP[16][13] = 3;
		PlayerMAP[16][14] = 3;
		PlayerMAP[16][15] = 3;
		
	
		
	return PlayerMAP;
	}	
			
		public static boolean Path(int x, int y){  // We find the solution path here (all possible paths), with recursion
			
			boolean gone = false;
			
			if(control(x, y)){
				
				PlayerMAP[x][y] = tried;
				
				if(x ==  PlayerMAP.length - 1 && y == 0)
					gone = true; 
				else {
					gone = Path(x - 1, y); 
					if(!gone)
						gone = Path(x, y - 1); 
					if(!gone)
						gone = Path(x + 1, y); 
					if(!gone)
						gone = Path(x, y + 1); 
				}
				if (gone)  
					PlayerMAP[x][y] = SolutionPath;
			}
			return gone;
		}
		
			

		public static boolean control(int x, int y)
		{
			boolean isPositive = false; 
			
			if (x >= 0 && x < MAP.PlayerMAP.length && y >= 0 && y < MAP.PlayerMAP.length)
			
				if (PlayerMAP[x][y] == 0 )
					isPositive = true;
		return isPositive;
		}
		
		
		public String toString(){
			String output = "\n";
			for (int i = 0; i < MAP.PlayerMAP.length; i++){
				for (int j = 0; j < MAP.PlayerMAP[i].length; j++)
					output += MAP.PlayerMAP[j][i] + " ";
				output += "\n";}
			return output;
			}
}