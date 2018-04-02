package group23.pacman;

import java.io.IOException;

import group23.pacman.model.Game;
import group23.pacman.view.GameModeSelectController;
import group23.pacman.view.GameViewController;
import group23.pacman.view.LevelSelectController;
import group23.pacman.view.WelcomeScreenController;
import javafx.application.Application; 
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene; 
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage; 

/**
 * The class that creates the platform and shows the main menu */

public class MainApp extends Application{ 
	
	private Stage gameWindow;
	private BorderPane rootLayout;
	private Scene scene;
	
	private int players;
	private char map;
	
	
	public static void main(String[] args) {
		
		launch(args);
	} 
	
	public void initRootLayout() {
		
		try {
			 /* Load root layout from fxml file */
			 FXMLLoader loader = new FXMLLoader();
			 loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			 rootLayout = (BorderPane) loader.load();

			 /* Show the scene containing the root layout */
			 scene = new Scene(rootLayout);
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

		
	}
	
	private void showWelcomeScreen() {
		
		try {
			
			/* Load/show the welcome screen */
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/WelcomeScreen.fxml"));
			AnchorPane welcomeScreen = (AnchorPane) loader.load();
			rootLayout.setCenter(welcomeScreen);

            /* Get the controller to manipulate this class */
			WelcomeScreenController controller = loader.getController();
			controller.setMainApp(this);
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void showLevelSelect() {
		
		try {
			
			/* Load/show the level select layout */
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/LevelSelect.fxml"));
			AnchorPane levelSelectScreen = (AnchorPane) loader.load();
			rootLayout.setCenter(levelSelectScreen);

            /* Get the controller to manipulate this class */
			LevelSelectController controller = loader.getController();
			controller.setMainApp(this);
			controller.listenToKeyEvents();

		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void showGameModeSelect() {
		
		try {
			
			/* Load/show the level select layout */
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/GameModeSelect.fxml"));
			AnchorPane levelSelectScreen = (AnchorPane) loader.load();
			rootLayout.setCenter(levelSelectScreen);

            /* Get the controller to manipulate this class */
			GameModeSelectController controller = loader.getController();
			controller.setMainApp(this);


		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showGameView() {
		
		try {
			
			/* Load/show the game view layout */
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/GameView.fxml"));
			AnchorPane gameView = (AnchorPane) loader.load();
			rootLayout.setCenter(gameView);
			
			/* Get the controller to manipulate this class */
			GameViewController controller = loader.getController();
			controller.setMainApp(this);
			
			/* Create game and pass to controller */
			Game game = new Game(map,players);
			controller.setGame(game);
			controller.initialDraw();
			controller.startGame();	
			
		}
		catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void setPlayers(int players) {
		
		this.players = players;
	}
	
	public void setMap(char map) {
		
		this.map = map;
	}
	
	
	public BorderPane getPane() {
		
		return this.rootLayout;
		
	}
	
	public Stage getStage() {
		
		return this.gameWindow;
	}
	
	public Scene getScene() {
		
		return this.scene;
	}
	

	
	
}