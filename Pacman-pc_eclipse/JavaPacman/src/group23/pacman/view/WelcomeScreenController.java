package group23.pacman.view;

import group23.pacman.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class WelcomeScreenController {
	
	private MainApp mainApp;
	@FXML
	private Button playBtn;
	
	@FXML
	private Button optionsBtn;
	
	@FXML
	private Button exitBtn;
	
	@FXML 
	private ImageView playBtnImage;
	
	@FXML
	private ImageView background;
	
	
	public WelcomeScreenController() {
		
		
	}
	
	
	@FXML
	private void initialize() {

	}
	
	public void setBackground() {
		Image mainMenuBackground = new Image("bg/background-main.png");
		//background = new ImageView(mainMenuBackground);
		background.setImage(mainMenuBackground);

		Image playImage = new Image("assets/button-play.png",200,100,false,false);
		playBtnImage.setImage(playImage);
		

	}
	
	@FXML
	private void handlePlay(KeyEvent event) {
		
		if (event.getCode() == KeyCode.ENTER) {
			char level = 's';
			GameScene gameScene = new GameScene(mainApp.getStage(),level);
			gameScene.setGameMode(1);
			gameScene.start();
		}
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
}
