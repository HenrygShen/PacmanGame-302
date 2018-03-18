package group23.pacman;

import java.io.IOException;
import group23.pacman.view.GameScene;
import group23.pacman.view.WelcomeScreenController;
import javafx.application.Application; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene; 
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane; 
import javafx.stage.Stage; 
import javafx.scene.image.*;

/**
 * The class that creates the platform and shows the main menu */

public class MainApp extends Application{ 
	
	private Stage gameWindow;
	private BorderPane rootLayout;
	
	public static void main(String[] args) {
		
		launch(args);
		
	} 
	
	public void initRootLayout() {
		
		 try {
	            // Load root layout from fxml file.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
	            rootLayout = (BorderPane) loader.load();

	            // Show the scene containing the root layout.
	            Scene scene = new Scene(rootLayout);
	            gameWindow.setScene(scene);
	            gameWindow.show();
	        } 
		 catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	
	@Override 
	public void start(Stage gameWindow) {
		
		this.gameWindow = gameWindow;
		this.gameWindow.setTitle("Pacman");
		
		initRootLayout();
		showWelcomeScreen();
//		
//		/* Load the main menu background */
//		Image mainMenuBackground = new Image("bg/background-main.png");
//		ImageView iv = new ImageView(mainMenuBackground);
//		rootLayout.getChildren().add(iv);
//		
//		/* Set up the play button image and make it listen to click events */
//		Image playImage = new Image("assets/button-play.png",150,100,false,false);
//		Button playBtn = new Button("",new ImageView(playImage));
//		
//		playBtn.setOnAction(new EventHandler<ActionEvent>() { 
//			
//			@Override 
//			public void handle(ActionEvent event) { 
//				
//				/* Starts the game if play is clicked */
//				/* For sea level */
//				char level = 's';
//				GameScene gameScene = new GameScene(gameWindow,level);
//				gameScene.setGameMode(1);
//				gameScene.start();
//				
//				} 
//			}); 
//		rootLayout.getChildren().add(playBtn);
		
		
	}
	
	private void showWelcomeScreen() {
		
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/WelcomeScreen.fxml"));
			
			AnchorPane welcomeScreen = (AnchorPane) loader.load();
			rootLayout.setCenter(welcomeScreen);
			
			WelcomeScreenController controller = loader.getController();
			controller.setBackground();
			controller.setMainApp(this);
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Stage getStage() {
		
		return this.gameWindow;
	}
	

	
	
}