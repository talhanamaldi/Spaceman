package application;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Squares extends StackPane{
	
	public static final int SQUARE_SIZE = 40;
	public static final int W = 800;
	public static final int H = 800;
	
	public static final int X_QSUARES = W / SQUARE_SIZE;
	public static final int Y_QSUARES = H / SQUARE_SIZE;
	
	public int x, y;
	
	public boolean hasAlien;
	public boolean hasbstar;
	public boolean haswstar;
	public boolean haswall;
	
	
	public Rectangle border = new Rectangle(SQUARE_SIZE - 2,SQUARE_SIZE - 2); // Border of the cells
	public Text text = new Text(); 
	
	public Image alienimage;			// We cannot directly use the image, we put the images in the frames (as called imageView)
	public ImageView alienview;
	
	public Image bluestarimage;
	public ImageView bluestarview;
	
	public Image whitestarimage;
	public ImageView whitestarview;
	
	public Image astronautimage;
	public ImageView astronautview;
	
	public Image wallimage;
	public ImageView wallview;

	public Squares(int x, int y, boolean hasAlien, boolean hasbstar, boolean haswstar, boolean haswall) {    // Construction of each cell in the map
		this.x = x;
		this.y = y;
		this.hasAlien = hasAlien;
		this.hasbstar = hasbstar;
		this.haswstar = haswstar;
		this.haswall = haswall;
		
		alienimage = new Image("alien2.jpg");
		alienview = new ImageView(alienimage);
		
		bluestarimage = new Image("bluestar.jpg");
		bluestarview = new ImageView(bluestarimage);
		
		whitestarimage = new Image("whitestar.jpg");
		whitestarview = new ImageView(whitestarimage);
		
		astronautimage = new Image("astronaut.jpg");
		astronautview = new ImageView(astronautimage);
		
		wallimage = new Image("wall.jpg");
		wallview = new ImageView(wallimage);	
		
		border.setStroke(Color.GREY);
		
		if(hasAlien) {
			getChildren().add(alienview);

		}
		else if(hasbstar){
			getChildren().add(bluestarview);

		}
		else if(haswstar){
			getChildren().add(whitestarview);

		}
		else if(haswall){
			getChildren().add(wallview);

		}
		
		else if(Main.isMove) {
			getChildren().add(astronautview);
		}
		else {
			text.setText("");
		}
		
		
		text.setFont(Font.font(20));
		text.setVisible(false);
		alienview.setVisible(false);
		bluestarview.setVisible(false);
		whitestarview.setVisible(false);
		wallview.setVisible(false);
		
		getChildren().addAll(border,text); // First, border is added, then text or image 
		
		setTranslateX(x*SQUARE_SIZE);  // 
		setTranslateY(y*SQUARE_SIZE);
		
	}
	
}
