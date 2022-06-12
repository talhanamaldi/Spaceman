package application;
public class Control {
	
	
	public static int[][] control = MAP.PlayerMAP;
	
 	public static boolean AlienControl(int x, int y) {
 	
 		// Existence of Aliens
				boolean IsAlien;
					if(control[x][y] == 4) 
						IsAlien = true;
					else
						IsAlien = false; 
					
 		return IsAlien;
 	}	
 	
 		public static boolean BlueStarControl(int x, int y) {
 	 		
 	 		// Existence of Blue Star
 					boolean BlueStar;
 						if(control[x][y] == 1) 
 							BlueStar = true;
 						else
 							BlueStar = false;
 			return BlueStar;
 		}
 		
 		public static boolean WhiteStarControl(int x, int y) {
 	 		
 	 		// Existence of White Star
 					boolean WhiteStar;
 						if(control[x][y] == 2) 
 							WhiteStar = true;
 						else
 							WhiteStar = false; 
 			return WhiteStar;
 		}
 		
 		public static boolean WallControl(int x, int y) {
 	 		
 	 		// Existence of Walls
 					boolean Wall;
 						if(control[x][y] == 3) 
 							Wall = true;
 						else
 							Wall = false; 
 			return Wall;
 		}
		
	
	
	

}
