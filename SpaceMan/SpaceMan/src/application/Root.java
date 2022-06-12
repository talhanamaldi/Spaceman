package application;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Root {
	
	public static Pane root;
	
	public Image heartimage; // We cannot directly use the image, we put the images in the frames (as called imageView)
	public ImageView heartview;
	
	public Image heartimage2;
	public ImageView heartview2;
	
	public Image heartimage3;
	public ImageView heartview3;
	
	public Image upimage;
	public ImageView upview;
	
	public Image leftimage;
	public ImageView leftview;
	
	public Image rightimage;
	public ImageView rightview;
	
	public Image downimage;
	public ImageView downview;
	
	public Image Earthimage;
	public ImageView Earthview;
	
	public Text score = new Text();
	public Text warning = new Text();
	public Text decide = new Text();
	public Text information = new Text();
	
	public Button BlueUse = new Button("Use");
	public Button BlueNotUse = new Button("Not Use");
	
	public Button WhiteUse = new Button("Use");
	public Button WhiteNotUse = new Button("Not Use");

	public Button Up = new Button();
	public Button Right = new Button();
	public Button Left = new Button();
	public Button Down = new Button();
	
	public Squares[][] grid = new Squares[Squares.X_QSUARES][Squares.Y_QSUARES]; // Constructed the map
	
	public Pane createRoot() {
		
		root = new Pane();
		
		root.setPrefSize(Squares.W + 300, Squares.H);
		
		for(int x = 0; x < Squares.X_QSUARES; x++) {  // Initialized the objects on the map
			for(int y = 0; y < Squares.Y_QSUARES; y++) {
		
				Squares square = new Squares(x,y,Control.AlienControl(x,y),Control.BlueStarControl(x,y),Control.WhiteStarControl(x,y),Control.WallControl(x,y));
				
				grid[x][y] = square;
				root.getChildren().add(grid[x][y]);
			}
		}
		
		for(int x = 0; x < Squares.X_QSUARES; x++) {
			for(int y = 0; y < Squares.Y_QSUARES; y++) {
				
				Squares square = grid[x][y];
				
				if(square.hasAlien || square.hasbstar || square.haswall || square.haswstar) continue; 
				
				int Total = 0;

                for(int k = 0; k < findNeighbors(square).size(); k++) {

                    if(findNeighbors(square).get(k).hasAlien) Total +=1;
                    if(findNeighbors(square).get(k).hasbstar) Total +=1;
                    if(findNeighbors(square).get(k).haswstar) Total +=1;

                }
				
				if(Total > 0) {
					square.text.setText(String.valueOf(Total));	
				}				 
			}
		}
		
		heartimage = new Image("heart.jpg");
		heartview = new ImageView(heartimage);
		
		heartview.setX(890);
		heartview.setY(30);
		
		heartimage2 = new Image("heart.jpg");
		heartview2 = new ImageView(heartimage2);
		
		heartview2.setX(920);
		heartview2.setY(30);
		
		heartimage3 = new Image("heart.jpg");
		heartview3 = new ImageView(heartimage3);
		
		heartview3.setX(950);
		heartview3.setY(30);
			
		Earthimage = new Image("Earth.png");
		Earthview = new ImageView(Earthimage);
		
		Earthview.setX(800);
		Earthview.setY(0);
		
		rightimage = new Image("Right.png");
		rightview = new ImageView(rightimage);
		
		upimage = new Image("Up.png");
		upview = new ImageView(upimage);
		
		downimage = new Image("Down.png");
		downview = new ImageView(downimage);
		
		leftimage = new Image("Left.png");
		leftview = new ImageView(leftimage);
	
		score.setX(905);								// We decided the location, the writing style, the font size and the color of the messages
		score.setY(70);
		score.setFont(Font.font("Verdana",20));
		score.setFill(Color.BLACK);
		
		warning.setX(820);
		warning.setY(100);
		warning.setFont(Font.font("Verdana",16));
		warning.setFill(Color.GREY);
		
		decide.setX(820);
		decide.setY(150);
		decide.setFont(Font.font("Verdana",16));
		decide.setFill(Color.BLACK);
		
		information.setX(810);
		information.setY(305);
		information.setFont(Font.font("Verdana",16));
		information.setFill(Color.GREEN);

		BlueUse.setLayoutX(860);
		BlueUse.setLayoutY(175);
		BlueUse.setVisible(false);
		
		BlueNotUse.setLayoutX(920); 
		BlueNotUse.setLayoutY(175);
		BlueNotUse.setVisible(false);
		
		WhiteUse.setLayoutX(860);
		WhiteUse.setLayoutY(175);
		WhiteUse.setVisible(false);
		
		WhiteNotUse.setLayoutX(920); 
		WhiteNotUse.setLayoutY(175);
		WhiteNotUse.setVisible(false);
		
		Up.setLayoutX(920);
		Up.setLayoutY(230);
		Up.setGraphic(upview);
		Up.setVisible(false);
		
		Right.setLayoutX(950);
		Right.setLayoutY(260);
		Right.setGraphic(rightview);
		Right.setVisible(false);
		
		Left.setLayoutX(890);
		Left.setLayoutY(260);
		Left.setGraphic(leftview);
		Left.setVisible(false);
		
		Down.setLayoutX(920);
		Down.setLayoutY(260);
		Down.setGraphic(downview);
		Down.setVisible(false);
			
		BlueUse.setOnAction(e -> {
				
			decide.setText("Which direction do you\nwant to choose?");
			
			BlueUse.setVisible(false);
			BlueNotUse.setVisible(false);
			
			Up.setVisible(true);
			Right.setVisible(true);
			Left.setVisible(true);
			Down.setVisible(true);
			
			Up.setOnAction(b -> {
				Main.BlueUP();
				Up.setVisible(false);
				Right.setVisible(false);
				Left.setVisible(false);
				Down.setVisible(false);
				BlueUse.setVisible(false);
				BlueNotUse.setVisible(false);});
			
			Right.setOnAction(b -> {
				Main.BlueRIGHT();
				Up.setVisible(false);
				Right.setVisible(false);
				Left.setVisible(false);
				Down.setVisible(false);
				BlueUse.setVisible(false);
				BlueNotUse.setVisible(false);});
			
			Left.setOnAction(b -> {
				Main.BlueLEFT();
				Up.setVisible(false);
				Right.setVisible(false);
				Left.setVisible(false);
				Down.setVisible(false);
				BlueUse.setVisible(false);
				BlueNotUse.setVisible(false);});
			
			Down.setOnAction(b -> {
				Main.BlueDOWN();
				Up.setVisible(false);
				Right.setVisible(false);
				Left.setVisible(false);
				Down.setVisible(false);
				BlueUse.setVisible(false);
				BlueNotUse.setVisible(false);});
			
			
		});
		
		BlueNotUse.setOnAction(e -> {
			BlueUse.setVisible(false);
			BlueNotUse.setVisible(false);
			
			Up.setVisible(false);
			Right.setVisible(false);
			Left.setVisible(false);
			Down.setVisible(false);
		});
		
		WhiteUse.setOnAction(e -> {
			decide.setText("Which direction do you want\nto see?");
			
			WhiteUse.setVisible(false);
			WhiteNotUse.setVisible(false);
			
			Up.setVisible(true);
			Right.setVisible(true);
			Left.setVisible(true);
			Down.setVisible(true);
			
			Up.setOnAction(b -> {
				Main.whiteUP();
				Up.setVisible(false);
				Right.setVisible(false);
				Left.setVisible(false);
				Down.setVisible(false);
				WhiteUse.setVisible(false);
				WhiteNotUse.setVisible(false);});
			
			Right.setOnAction(b -> {
				Main.whiteRIGHT();
				Up.setVisible(false);
				Right.setVisible(false);
				Left.setVisible(false);
				Down.setVisible(false);
				WhiteUse.setVisible(false);
				WhiteNotUse.setVisible(false);});
			
			Left.setOnAction(b -> {
				Main.whiteLEFT();
				Up.setVisible(false);
				Right.setVisible(false);
				Left.setVisible(false);
				Down.setVisible(false);
				WhiteUse.setVisible(false);
				WhiteNotUse.setVisible(false);});
			
			Down.setOnAction(b -> {
				Main.whiteDOWN();
				Up.setVisible(false);
				Right.setVisible(false);
				Left.setVisible(false);
				Down.setVisible(false);
				WhiteUse.setVisible(false);
				WhiteNotUse.setVisible(false);});		
		});
		
		WhiteNotUse.setOnAction(e -> {
			
			WhiteUse.setVisible(false);
			WhiteNotUse.setVisible(false);
			
			Up.setVisible(false);
			Right.setVisible(false);
			Left.setVisible(false);
			Down.setVisible(false);
		});
		
		root.getChildren().add(score);
		root.getChildren().add(warning);
		root.getChildren().add(decide);
		
		root.getChildren().add(BlueUse);
		root.getChildren().add(BlueNotUse);
		
		root.getChildren().add(Earthview);
		root.getChildren().add(heartview);
		root.getChildren().add(heartview2);
		root.getChildren().add(heartview3);
		
		root.getChildren().add(Up);
		root.getChildren().add(Right);
		root.getChildren().add(Left);
		root.getChildren().add(Down);
		
		root.getChildren().add(WhiteUse);
		root.getChildren().add(WhiteNotUse);
		
		root.getChildren().add(information);

		return root;
	}
	

	public List<Squares> findNeighbors(Squares square){  //We do not know the number of squares around the current square
		List<Squares> neighbors = new ArrayList<>();
		
		// sss    
		// sxs
		// sss
		
		int[] points = new int[] { 
			  
				-1,-1,
				-1,0,
				-1,1,
				
				0,-1,
				0,1,
				
				1,-1,
				1,0,
				1,1
		};
		
		for (int i = 0; i < points.length; i++) { 
			int dx = points[i];
			int dy = points[++i];
			
			int neighborX = square.x + dx; 
			int neighborY = square.y + dy;
			
		
			
			if(neighborX >= 0 && neighborX < Squares.X_QSUARES && neighborY >= 0 && neighborY < Squares.Y_QSUARES) {
				neighbors.add(grid[neighborX][neighborY]);
			}
		} 
		return neighbors;
	}
}
