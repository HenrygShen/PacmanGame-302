package group23.pacman.view;

import group23.pacman.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

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
	
	public void setMainApp(MainApp mainApp) {
		
		this.mainApp = mainApp;
	}
	
	@FXML
	private void handlePlay(KeyEvent event) {
		
		if (event.getCode() == KeyCode.ENTER) {
			
			mainApp.showLevelSelect();
		
		}
	}
	
	@FXML
	private void initialize() {
		
		Image mainMenuBackground = new Image("bg/background-main.png");
		background.setImage(mainMenuBackground);

		Image playImage = new Image("assets/button-play.png",200,100,false,false);
		playBtnImage.setImage(playImage);
	}

}
