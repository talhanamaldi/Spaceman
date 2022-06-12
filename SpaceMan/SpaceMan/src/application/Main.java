package application;
	
import java.util.ArrayList;
import java.util.List;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Main extends Application {
	
	public Scene scene;
	public Root root;
	  
	public static int Xposition = 0;
	public static int Yposition = 19;	
	
	public static int LifeCounter = 0;
	
	public static boolean isMove = true; 
	public static boolean solvable = false;
	
	public static Squares[][] maingrid;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		MAP Game = new MAP();
		
		Game.AddObjects();
		
		root = new Root();
		
		scene = new Scene(root.createRoot());
		
		Image icon = new Image("spaceman1.jpg");
		stage.getIcons().add(icon);
		stage.setTitle("SpaceMan");
		stage.setResizable(false);
		
		root.information.setText("	    HOW TO PLAY\n\nYou have 3 lives during the game.\n\nWhen you encounter the asteroid,\nyou cannot pass through the area\nbounded by the asteroid and if you\ncollide with the asteroid, you will\nlose the 10 points.\n\nWhen you encounter a white star,\nyou will gain the ability to see an\nobject on one square in your\nmovement path and you will gain\nthe 60 points.\n\nWhen the blue star will be found,\nyou will have the right to jump\nover a square while moving and\nyou will gain the 50 points. After\nthe jumping, if you encounter the\nalien, the game will end.\n\nIf you encounter an alien, you will\nlose a life. ");
	
		
		maingrid = root.grid;
	
		MAP Path = new MAP();
		
		while(!solvable) {
		if (Path.Path(0, 19)) {
		
			System.out.println("The map is solvable!");
			root.warning.setText("THE MAP IS SOLVABLE!\n\nPLEASE PRESS SPACE TO START");

			solvable = true;
		}
		else {
			System.out.println("There is no possible solution for map!");
			root.warning.setText("THERE IS NO POSSIBLE SOLUTION FOR MAP!");
			root.warning.setText("THE MAP IS BEING RECREATED.");
			
			scene.setRoot(root.createRoot());

			Xposition = 0;
			Yposition = 19;
			LifeCounter = 0;
			Score.score = 1000;
			
			MAP ReCreat = new MAP();
			ReCreat.AddObjects();
		}
	}	
			System.out.println(Path);

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent event) {
			switch(event.getCode()) {
				case W:
					moveW();	
					stop500();
					break;
					
				case A:
					moveA();
					stop500();
					break;
					
				case S:
					moveS();
					stop500();
					break;
					
				case D:
					moveD();
					stop500();
					break;
						
				case SPACE:
					start();
					break;
					
				default:
					break;
				}	
			}
		});	
		
		stage.setScene(scene);
		stage.show();
	}
		
	public void moveW() {
		
		Yposition--;
		
		if(Yposition < 0) {
			root.warning.setText("You can't go out of bounds!");
			
			Yposition++;
		}
		
		maingrid[Xposition][Yposition].astronautview.setVisible(true);
		
		if(Yposition > 0) { // After every movement deletes before squares astronaut picture
            maingrid[Xposition][Yposition + 1].astronautview.setVisible(false);
        }
		
		root.warning.setText(null);
		root.decide.setText(null);
		
		root.BlueUse.setVisible(false);
		root.BlueNotUse.setVisible(false);
		
		root.WhiteUse.setVisible(false);
		root.WhiteNotUse.setVisible(false);
		
		root.Up.setVisible(false);
		root.Down.setVisible(false);
		root.Right.setVisible(false);
		root.Left.setVisible(false);
		

		
		if(maingrid[Xposition][Yposition].hasAlien) {  
			
			Yposition++;
			
			maingrid[Xposition][Yposition - 1].alienview.setVisible(true); 
			maingrid[Xposition][Yposition - 1].border.setFill(null);
			
			System.out.println("You've been caught by an alien!");
			root.warning.setText("You've been caught by an alien!");
			
			LifeCounter++;
			
			if(LifeCounter == 1) root.heartview.setVisible(false);
			if(LifeCounter == 2) root.heartview2.setVisible(false);
			if(LifeCounter == 3) {
				
				root.heartview3.setVisible(false);
				root.warning.setText("Game over.\n\nThe Map was being recreated.\n\nPlease press space to start again!");
				
				scene.setRoot(root.createRoot());
				
				Xposition = 0;
				Yposition = 19;
				LifeCounter = 0;
				Score.score = 1000;
				
				MAP Game = new MAP();
				Game.AddObjects();
				
			}
		}
		
		if(maingrid[Xposition][Yposition].hasbstar) {   
			
			maingrid[Xposition][Yposition].bluestarview.setVisible(true);
			
			System.out.println("You found blue star!");
			
			root.warning.setText("You found blue star");
			
			root.decide.setText("Do you want to use blue star?");
			root.BlueUse.setVisible(true);
			root.BlueNotUse.setVisible(true);
		}
		
		if(maingrid[Xposition][Yposition].haswstar) { 
			
			maingrid[Xposition][Yposition].whitestarview.setVisible(true);
			
			System.out.println("You found white star!");
			
			root.warning.setText("You found white star!");
			
			root.decide.setText("Do you want to use white star?");
			root.WhiteUse.setVisible(true);
			root.WhiteNotUse.setVisible(true);
			
			}
		
		if(maingrid[Xposition][Yposition].haswall) {  
			
			Yposition++;
			
			maingrid[Xposition][Yposition - 1].wallview.setVisible(true);
			maingrid[Xposition][Yposition - 1].border.setFill(null);
			
			System.out.println("You can't go through walls!");
			
			root.warning.setText("You can't go through walls!");
		}
		
		
		maingrid[Xposition][Yposition].text.setVisible(true);		
		maingrid[Xposition][Yposition].border.setFill(null);

		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 20; j++) {
				
				List<Squares> neighbors = new ArrayList<>();
				
				neighbors = root.findNeighbors(maingrid[i][j]);
				
				for(int k = 0; k < neighbors.size(); k++) {
					
					if(neighbors.get(k).hasAlien && i == Xposition && j == Yposition) {
						maingrid[i][j].border.setFill(Color.GREEN);
					}
				}		
			}
		}
		
		if(Xposition == 19 && Yposition == 0) {
			root.warning.setText("You won!");
			System.exit(0);
		}
		
		int scoreW = Score.addScore(Xposition, Yposition, "W");
		
		root.score.setText(String.valueOf(scoreW));	
		
		if(Score.score < 0) {
			root.heartview.setVisible(false);
			root.heartview2.setVisible(false);
			root.heartview3.setVisible(false);
			root.warning.setText("Game over.\n\nThe Map was being recreated.\n\nPlease press space to start again!");
			
			scene.setRoot(root.createRoot());
			
			Xposition = 0;
			Yposition = 19;
			LifeCounter = 0;
			Score.score = 1000;
			
			MAP Game = new MAP();
			Game.AddObjects();
		}
	}
	
	public void moveA() {
		
		Xposition--;
		
		if(Xposition < 0) {
			
			root.warning.setText("You can't go out of bounds!");
			
			Xposition++;
		}
		
		maingrid[Xposition][Yposition].astronautview.setVisible(true);
		
		if(Xposition > 0) { // After every movement deletes before squares astronaut picture
            maingrid[Xposition + 1][Yposition].astronautview.setVisible(false);
        }
		
		root.warning.setText(null);
		root.decide.setText(null);
		
		root.BlueUse.setVisible(false);
		root.BlueNotUse.setVisible(false);
		
		root.WhiteUse.setVisible(false);
		root.WhiteNotUse.setVisible(false);
		
		root.Up.setVisible(false);
		root.Down.setVisible(false);
		root.Right.setVisible(false);
		root.Left.setVisible(false);


		
		if(maingrid[Xposition][Yposition].hasAlien) {    
			
			Xposition++;
			
			maingrid[Xposition - 1][Yposition].alienview.setVisible(true);
			maingrid[Xposition - 1][Yposition].border.setFill(null);
			
			System.out.println("You've been caught by an alien!");
			root.warning.setText("You've been caught by an alien!");
			
			LifeCounter++;
			
			if(LifeCounter == 1) root.heartview.setVisible(false);
			if(LifeCounter == 2) root.heartview2.setVisible(false);
			if(LifeCounter == 3) {
				
				root.heartview3.setVisible(false);
				root.warning.setText("Game over.\n\nThe Map was being recreated.\n\nPlease press space to start again!");
				
				scene.setRoot(root.createRoot());
				
				Xposition = 0;
				Yposition = 19;
				LifeCounter = 0;
				Score.score = 1000;
				
				MAP Game = new MAP();
				Game.AddObjects();
		}
	}
		
		if(maingrid[Xposition][Yposition].hasbstar) {  
			
			maingrid[Xposition][Yposition].bluestarview.setVisible(true);
			
			System.out.println("You found blue star!");
			
			root.warning.setText("You found blue star!");
			root.decide.setText("Do you want to use blue star?");
			root.BlueUse.setVisible(true);
			root.BlueNotUse.setVisible(true);		
		}
		
		if(maingrid[Xposition][Yposition].haswstar) {    
			
			maingrid[Xposition][Yposition].whitestarview.setVisible(true);
			
			System.out.println("You found white star!");
			
			root.warning.setText("You found white star!");
			root.decide.setText("Do you want to use white star?");
			root.WhiteUse.setVisible(true);
			root.WhiteNotUse.setVisible(true);
		}
		
		if(maingrid[Xposition][Yposition].haswall) {  
			
			Xposition++;
			
			maingrid[Xposition - 1][Yposition].wallview.setVisible(true);
			maingrid[Xposition - 1][Yposition].border.setFill(null);
			
			System.out.println("You cant go through walls!");
			
			root.warning.setText("You cant go through walls!");
		}
		
		maingrid[Xposition][Yposition].text.setVisible(true);
		maingrid[Xposition][Yposition].border.setFill(null);
		
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 20; j++) {
				
				List<Squares> neighbors = new ArrayList<>();
				
				neighbors = root.findNeighbors(maingrid[i][j]);
				
				for(int k = 0; k < neighbors.size(); k++) {
					
					if(neighbors.get(k).hasAlien && i == Xposition && j == Yposition) {
						maingrid[i][j].border.setFill(Color.GREEN);
					}
				}		
			}
		}
		
		if(Xposition == 19 && Yposition == 0) {
			root.warning.setText("You won!");
			System.exit(0);
		}
		
		int scoreA = Score.addScore(Xposition, Yposition, "A");
		root.score.setText(String.valueOf(scoreA));	
		
		if(Score.score < 0) {
			root.heartview.setVisible(false);
			root.heartview2.setVisible(false);
			root.heartview3.setVisible(false);
			root.warning.setText("Game over.\n\nThe Map was being recreated.\n\nPlease press space to start again!");
			
			scene.setRoot(root.createRoot());
			
			Xposition = 0;
			Yposition = 19;
			LifeCounter = 0;
			Score.score = 1000;
			
			MAP Game = new MAP();
			Game.AddObjects();
		}
	}

	public void moveS() {
		
		Yposition++;
		

		if(Yposition > 19) {
			
			root.warning.setText("You can't go out of bounds!");
			
			Yposition--;
		}
		
		maingrid[Xposition][Yposition].astronautview.setVisible(true);
		
		if(Yposition < 19) {
            maingrid[Xposition][Yposition - 1].astronautview.setVisible(false);
        }
		root.warning.setText(null);
		root.decide.setText(null);
		
		root.BlueUse.setVisible(false);
		root.BlueNotUse.setVisible(false);
		
		root.WhiteUse.setVisible(false);
		root.WhiteNotUse.setVisible(false);
		
		root.Up.setVisible(false);
		root.Down.setVisible(false);
		root.Right.setVisible(false);
		root.Left.setVisible(false);

		
		if(maingrid[Xposition][Yposition].hasAlien) {   		
			
			Yposition--;
			
			maingrid[Xposition][Yposition + 1].alienview.setVisible(true);
			maingrid[Xposition][Yposition + 1].border.setFill(null);
			
			System.out.println("You've been caught by an alien!");
			root.warning.setText("You've been caught by an alien!");
			
			LifeCounter++;
						
			if(LifeCounter == 1) root.heartview.setVisible(false);
			if(LifeCounter == 2) root.heartview2.setVisible(false);
			if(LifeCounter == 3) {
				
				root.heartview3.setVisible(false);
				root.warning.setText("Game over.\n\nThe Map was being recreated.\n\nPlease press space to start again!");
				
				scene.setRoot(root.createRoot());
				
				Xposition = 0;
				Yposition = 19;
				LifeCounter = 0;
				Score.score = 1000;
				
				MAP Game = new MAP();
				Game.AddObjects();	
			}	
		}
		
		if(maingrid[Xposition][Yposition].hasbstar) {  
			
			maingrid[Xposition][Yposition].bluestarview.setVisible(true);
			
			System.out.println("You found blue star!");
			
			root.warning.setText("You found blue star!");
			root.decide.setText("Do you want to use blue star?");
			root.BlueUse.setVisible(true);
			root.BlueNotUse.setVisible(true);
		
		}
		
		if(maingrid[Xposition][Yposition].haswstar) {  
			maingrid[Xposition][Yposition].whitestarview.setVisible(true);
			
			System.out.println("You found white star!");
			
			root.warning.setText("You found white star!");
			root.decide.setText("Do you want to use white star?");
			root.WhiteUse.setVisible(true);
			root.WhiteNotUse.setVisible(true);
		}
		
		if(maingrid[Xposition][Yposition].haswall) {  
			
			Yposition--;
			
			maingrid[Xposition][Yposition+1].wallview.setVisible(true);
			maingrid[Xposition][Yposition+1].border.setFill(null);
			
			System.out.println("You can't go through walls!");
			
			root.warning.setText("You can't go through walls!");
		}
		
		maingrid[Xposition][Yposition].text.setVisible(true);	
		maingrid[Xposition][Yposition].border.setFill(null);
		
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 20; j++) {
				
				List<Squares> neighbors = new ArrayList<>();
				
				neighbors = root.findNeighbors(maingrid[i][j]);
				
				for(int k = 0; k < neighbors.size(); k++) {
					
					if(neighbors.get(k).hasAlien && i == Xposition && j == Yposition) {
					
						maingrid[i][j].border.setFill(Color.GREEN);
					}
				}		
			}
		}
		
		if(Xposition == 19 && Yposition == 0) {
			root.warning.setText("You won!");
			System.exit(0);
		}
		
		int scoreS = Score.addScore(Xposition, Yposition, "S");
		root.score.setText(String.valueOf(scoreS));
		
		if(Score.score < 0) {
			root.heartview.setVisible(false);
			root.heartview2.setVisible(false);
			root.heartview3.setVisible(false);
			root.warning.setText("Game over.\n\nThe Map was being recreated.\n\nPlease press space to start again!");
			
			scene.setRoot(root.createRoot());
			
			Xposition = 0;
			Yposition = 19;
			LifeCounter = 0;
			Score.score = 1000;
			
			MAP Game = new MAP();
			Game.AddObjects();
		}
	}
	
	public void moveD() {
	
		Xposition++;
		

		if(Xposition > 19) {
			
			root.warning.setText("You can't go out of bounds!");
			
			Xposition--;
		}
		
		maingrid[Xposition][Yposition].astronautview.setVisible(true);
		
		if(Xposition < 19) {
            maingrid[Xposition - 1][Yposition].astronautview.setVisible(false);
        }
		
		root.warning.setText(null);
		root.decide.setText(null);
		
		root.BlueUse.setVisible(false);
		root.BlueNotUse.setVisible(false);
		
		root.WhiteUse.setVisible(false);
		root.WhiteNotUse.setVisible(false);
		
		root.Up.setVisible(false);
		root.Down.setVisible(false);
		root.Right.setVisible(false);
		root.Left.setVisible(false);

		
		if(maingrid[Xposition][Yposition].hasAlien) {  
			
			Xposition--;
				
			maingrid[Xposition + 1][Yposition].alienview.setVisible(true);
			maingrid[Xposition + 1][Yposition].border.setFill(null);
			
			System.out.println("You've been caught by an alien!");
			root.warning.setText("You've been caught by an alien!");
			
			LifeCounter++;
			
			if(LifeCounter == 1) root.heartview.setVisible(false);
			if(LifeCounter == 2) root.heartview2.setVisible(false);
			if(LifeCounter == 3) {
				
				root.heartview3.setVisible(false);
				root.warning.setText("Game over.\n\nThe Map was being recreated.\n\nPlease press space to start again!");
				
				scene.setRoot(root.createRoot());
				
				Xposition = 0;
				Yposition = 19;
				LifeCounter = 0;
				Score.score = 1000;
				MAP Game = new MAP();	
				Game.AddObjects();	
			}
		}
		
		if(maingrid[Xposition][Yposition].hasbstar) {   
			
			maingrid[Xposition][Yposition].bluestarview.setVisible(true);
			
			System.out.println("You found blue star!");

			root.warning.setText("You found blue star!");
			root.decide.setText("Do you want to use blue star?");
			root.BlueUse.setVisible(true);
			root.BlueNotUse.setVisible(true);
		}
		
		if(maingrid[Xposition][Yposition].haswstar) {   
			
			maingrid[Xposition][Yposition].whitestarview.setVisible(true);
			
			System.out.println("You found white star!");
			
			root.warning.setText("You found white star!");
			root.decide.setText("Do you want to use white star?");
			root.WhiteUse.setVisible(true);
			root.WhiteNotUse.setVisible(true);
		}
		
		if(maingrid[Xposition][Yposition].haswall) {   
			
			Xposition--;
			
			maingrid[Xposition+1][Yposition].wallview.setVisible(true);
			maingrid[Xposition+1][Yposition].border.setFill(null);
			
			System.out.println("You can't go through walls!");
			
			root.warning.setText("You can't go through walls!");
		}
		
		
		maingrid[Xposition][Yposition].text.setVisible(true);
		maingrid[Xposition][Yposition].border.setFill(null);
		
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 20; j++) {
				
				List<Squares> neighbors = new ArrayList<>();
				
				neighbors = root.findNeighbors(maingrid[i][j]);
				
				for(int k = 0; k < neighbors.size(); k++) {
					
					if(neighbors.get(k).hasAlien && i == Xposition && j == Yposition) {
						maingrid[i][j].border.setFill(Color.GREEN);
					}
				}
			}
		}
		
		if(Xposition == 19 && Yposition == 0) {
			
			root.warning.setText("You won!");
			System.exit(0);
		}
		
		int scoreD = Score.addScore(Xposition, Yposition, "D");
		root.score.setText(String.valueOf(scoreD));
		
		if(Score.score < 0) {
			root.heartview.setVisible(false);
			root.heartview2.setVisible(false);
			root.heartview3.setVisible(false);
			root.warning.setText("Game over.\n\nThe Map was being recreated.\n\nPlease press space to start again!");
			
			scene.setRoot(root.createRoot());
			
			Xposition = 0;
			Yposition = 19;
			LifeCounter = 0;
			Score.score = 1000;
			
			MAP Game = new MAP();
			Game.AddObjects();
		}
	}
	
	public void start() {
		
		root.warning.setText(null);
		maingrid[0][19].border.setFill(null);
		root.information.setText(null);
		root.score.setText("1000");
	}
	
	
	
	public static void BlueDOWN() {
			
			maingrid[Xposition][Yposition + 1].text.setVisible(true);
			maingrid[Xposition][Yposition + 2].text.setVisible(true);
			
			maingrid[Xposition][Yposition + 1].border.setFill(null);
			maingrid[Xposition][Yposition + 2].border.setFill(null);
			
			Yposition += 2;
			
			maingrid[Xposition][Yposition - 1].astronautview.setVisible(false);
			maingrid[Xposition][Yposition].astronautview.setVisible(true);
			
			if(maingrid[Xposition][Yposition].hasAlien) {
				System.exit(0);
		}
	}
	
	public static void BlueRIGHT() {

			maingrid[Xposition + 1][Yposition].text.setVisible(true);
			maingrid[Xposition + 2][Yposition].text.setVisible(true);
			
			maingrid[Xposition + 1][Yposition].border.setFill(null);
			maingrid[Xposition + 2][Yposition].border.setFill(null);
		
			Xposition += 2;
			
			maingrid[Xposition - 1][Yposition].astronautview.setVisible(false);
			maingrid[Xposition][Yposition].astronautview.setVisible(true);
			
			if(maingrid[Xposition][Yposition].hasAlien) {
				System.exit(0);
		}
	}

	public static void BlueLEFT() {

			maingrid[Xposition - 1][Yposition].text.setVisible(true);
			maingrid[Xposition - 2][Yposition].text.setVisible(true);
			
			maingrid[Xposition - 1][Yposition].border.setFill(null);
			maingrid[Xposition - 2][Yposition].border.setFill(null);
		
			Xposition -= 2;
			
			maingrid[Xposition + 1][Yposition].astronautview.setVisible(false);
			maingrid[Xposition][Yposition].astronautview.setVisible(true);
			
			if(maingrid[Xposition][Yposition].hasAlien) {
				System.exit(0);
		}
	}

	public static void BlueUP() {

			maingrid[Xposition][Yposition - 1].text.setVisible(true);
			maingrid[Xposition][Yposition - 2].text.setVisible(true);
			
			maingrid[Xposition][Yposition - 1].border.setFill(null);
			maingrid[Xposition][Yposition - 2].border.setFill(null);
		
			Yposition -= 2;
			
			maingrid[Xposition][Yposition + 1].astronautview.setVisible(false);
			maingrid[Xposition][Yposition].astronautview.setVisible(true);
			
			if(maingrid[Xposition][Yposition].hasAlien) {
				System.exit(0);
		}
	}
	
	public static void whiteDOWN() {
		
		maingrid[Xposition][Yposition + 1].text.setVisible(true);
		maingrid[Xposition][Yposition + 1].border.setFill(null);
		maingrid[Xposition][Yposition + 1].astronautview.setVisible(false);
		
		if(maingrid[Xposition][Yposition + 1].hasAlien || maingrid[Xposition + 1][Yposition].hasbstar) {
			maingrid[Xposition][Yposition + 1].alienview.setVisible(true);
			maingrid[Xposition][Yposition + 1].bluestarview.setVisible(true);
		}
		

	}

	public static void whiteRIGHT() {

		maingrid[Xposition + 1][Yposition].text.setVisible(true);
		maingrid[Xposition + 1][Yposition].border.setFill(null);
		maingrid[Xposition + 1][Yposition].astronautview.setVisible(false);
		
		if(maingrid[Xposition + 1][Yposition].hasAlien || maingrid[Xposition + 1][Yposition].hasbstar ) {
			maingrid[Xposition + 1][Yposition].alienview.setVisible(true);
			maingrid[Xposition + 1][Yposition].bluestarview.setVisible(true);
		}
		
		
		
	}

	public static void whiteLEFT() {

		maingrid[Xposition - 1][Yposition].text.setVisible(true);
		maingrid[Xposition - 1][Yposition].border.setFill(null);
		maingrid[Xposition - 1][Yposition].astronautview.setVisible(false);
		
		if(maingrid[Xposition - 1][Yposition].hasAlien || maingrid[Xposition + 1][Yposition].hasbstar ) {
			maingrid[Xposition - 1][Yposition].alienview.setVisible(true);
			maingrid[Xposition - 1][Yposition].bluestarview.setVisible(true);
		}
	
	}

	public static void whiteUP() {

		maingrid[Xposition][Yposition - 1].text.setVisible(true);
		maingrid[Xposition][Yposition - 1].border.setFill(null);
		maingrid[Xposition][Yposition - 1].astronautview.setVisible(false);
		
		if(maingrid[Xposition][Yposition - 1].hasAlien || maingrid[Xposition + 1][Yposition].hasbstar ) {
			maingrid[Xposition][Yposition - 1].alienview.setVisible(true);
			maingrid[Xposition][Yposition - 1].bluestarview.setVisible(true);
		}
	}

	public void stop500() {
			try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stop1000() {
		try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}	
	
	public void stop2000() {
		try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
}