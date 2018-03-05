import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage; 
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameScene {
	
	
	Stage mainStage;
	
	private Group root;
    private Scene scene;
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private Game game;

	public GameScene(Stage mainStage) {
		
		this.mainStage = mainStage;
        
		root = new Group();
		scene = new Scene(root);
	    canvas = new Canvas( 1366, 768 );
	    ImageView iv = new ImageView(new Image("mapOne.png"));
	    root.getChildren().add(iv);
	    root.getChildren().add(canvas);
	    graphicsContext = canvas.getGraphicsContext2D();
	    
	    

	    scene.setOnKeyPressed(new EventHandler<KeyEvent> (){
	    	@Override
	    	public void handle(KeyEvent e) {
	    	/* switch to switch statements later */
	    	
		    	if (e.getCode() == KeyCode.UP) {
		    		game.getPacman().setDirection(1);
		    	}
		    	else if (e.getCode() == KeyCode.DOWN) {
		    		game.getPacman().setDirection(2);
		    	}
		    	else if (e.getCode() == KeyCode.LEFT) {
		    		game.getPacman().setDirection(3);
		    	}
		    	else if (e.getCode() == KeyCode.RIGHT) {
		    		game.getPacman().setDirection(4);
		    	}
	    	}
	    });
	    
	    mainStage.setScene(scene);
	    mainStage.show();

	}
	
	public void setGameMode(int gameType) {
		
		if (gameType == 1) {
			game = new Game();
		}
		
		
	}
	
	
	public void start() {
		
		 new AnimationTimer() {
			 	
		        public void handle(long currentNanoTime) {
		        	
		        	graphicsContext.clearRect(0, 0, 1366, 768);
		        	game.update();
		        	draw(graphicsContext);

		        }
		    }.start();
		
	}
	
	public void draw(GraphicsContext graphicsContext) {
		game.getPacman().draw(graphicsContext);
	}
	


	
	
	
}
