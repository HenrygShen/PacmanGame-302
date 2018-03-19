package group23.pacman.view;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import group23.pacman.MainApp;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LevelSelectController {
	
	private MainApp mainApp;
	private static final int MAX_BACKGROUND_INDEX = 1;
	
	@FXML
	private ImageView background;
	
	
	@FXML
	private ImageView levelImage;
	

	@FXML
	private ImageView leftArrow;
	
	@FXML
	private ImageView rightArrow;
	
	private int index;
	private Image seaBackground;
	private Image desertBackground;
	private Image[] backgrounds;
	
	
	public LevelSelectController() {
	}
	
	
	@FXML
	private void initialize() {
		
		/* Set up background of this view */
		Image backgroundImage = new Image("bg/background-levelSelect.png");
		background.setImage(backgroundImage);
		
		/* Set up level backgrounds to scroll through */
		seaBackground = new Image("bg/background-sea_game.png");
		desertBackground = new Image("bg/background-desert_game.png");
		backgrounds = new Image[2];
		backgrounds[0] = seaBackground;
		backgrounds[1] = desertBackground;
		index = 0;
		levelImage.setImage(backgrounds[index]);
		
		/* Load the arrows */
		Image leftArrowImage = new Image("assets/buttons/leftArrow.png",110,110,false,false);
		Image rightArrowImage = new Image("assets/buttons/rightArrow.png",110,110,false,false);		
		leftArrow.setImage(leftArrowImage);
		rightArrow.setImage(rightArrowImage);
		
		
	}

	
	@FXML
	private void handleImageScroll(KeyEvent event) {
		
		if (event.getCode() == KeyCode.ENTER) {
			
			char level;

			switch (index) {
				case 0 :
					level = 's';
					break;
				case 1 :
					level = 'd';
					break;
				default :
					level = 's';
					break;
			}
			
			GameScene gameScene = new GameScene(mainApp.getStage(),level);
			gameScene.setMap(level);
			gameScene.setGameMode(1);
			gameScene.start();
			
			
		}
		
		else if (event.getCode() == KeyCode.LEFT) {
			animateLeft();
			index--;
			index = (index < 0) ? MAX_BACKGROUND_INDEX : index;
		}
		
		else if (event.getCode() == KeyCode.RIGHT) {
			animateRight();
			index++;
			index = (index > MAX_BACKGROUND_INDEX) ? 0 : index;
		}
		
		levelImage.setImage(backgrounds[index]);
	}
	
	private void animateLeft() {

		leftArrow.setX(leftArrow.getX() - 40);
        leftArrow.setY(leftArrow.getY() - 40);
		leftArrow.setFitHeight(150);
		leftArrow.setFitWidth(150);
        leftArrow.setImage(new Image("assets/buttons/leftArrow.png",150,150,false,false));


        
	
	}
	
	private void animateRight() {
		
		leftArrow.setFitHeight(110);
		leftArrow.setFitWidth(110);
		 leftArrow.setX(leftArrow.getX() + 50);
	        leftArrow.setY(leftArrow.getY() + 50);
	        leftArrow.setImage(new Image("assets/buttons/leftArrow.png",110,110,false,false));

	
	}
	public void setMainApp(MainApp mainApp) {
		
		this.mainApp = mainApp;
	}
}