package group23.pacman;

import java.io.IOException;

import group23.pacman.model.Game;
import group23.pacman.view.CharacterSelectController;
import group23.pacman.view.GameViewController;
import group23.pacman.view.HelpScreenController;
import group23.pacman.view.LevelSelectController;
import group23.pacman.view.WelcomeScreenController;
import javafx.application.Application; 
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene; 
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage; 

/**The class that creates the platform and shows the main menu.
 * Also has the methods for showing other screens.
*/

public class MainApp extends Application{ 
	
	/* The window for showing the game/application */
	private Stage gameWindow;
	
	/* Layout to draw UI onto */
	private BorderPane rootLayout;
	
	private Scene scene;
	
	/* Stores the player mode selected from GameModeSelect */
	private int numPlayers;
	
	private int player2;
	
	private int player3;
	
	
	/* Stores the map selected from LevelSelect */
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
	
	
	/* The screen that greets the user */
	public void showWelcomeScreen() {
		
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
	
	
	/* 	*/
	public void showHelp() {
		
		try {
			
			/* Load/show the help screen layout */
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/HelpScreen.fxml"));
			AnchorPane helpScreen = (AnchorPane) loader.load();
			rootLayout.setCenter(helpScreen);
            /* Get the controller to manipulate this class */
			HelpScreenController controller = loader.getController();
			controller.setMainApp(this);
			controller.listenToKeyEvents();


		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/* The screen that allows the user to select a map */
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
	
	/* Shows char select */
	public void showCharacterSelect() {
		
		try {
			/* Load/show the character select view layout */
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CharacterSelect.fxml"));
			AnchorPane characterSelectScreen = (AnchorPane) loader.load();
			rootLayout.setCenter(characterSelectScreen);
			/* Get the controller to manipulate this class */
			CharacterSelectController controller = loader.getController();
			controller.setMainApp(this);
			controller.setPlayers(numPlayers);
			
			//TODO
			// pass ghost number and which sprites
			
		}
		catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/* The game screen which will be showing the actual game play */
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
			Game game = new Game(map,numPlayers,player2,player3);
			controller.setGame(game);
			controller.initialDraw();
			controller.startGame();	
			
		}
		catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	/* Public setter to pass game mode back to this class from GameModeSelectController */
	public void setPlayers(int players) {
		
		this.numPlayers = players;
	}
	
	public void setPlayer2(int ghostIndex) {
		
		player2 = ghostIndex;
	}
	
	public void setPlayer3(int ghostIndex) {
		
		player3 = ghostIndex;
	}
	
	/* Public setter to pass map back to this class from LevelSelectController */
	public void setMap(char map) {
		
		this.map = map;
	}
	
	/* Public getter to add elements to the layout (Currently only used by GameViewController) */
	public BorderPane getPane() {
		
		return this.rootLayout;
		
	}

	/* Public getter for scene (mainly used to add key listeners) */
	public Scene getScene() {
		
		return this.scene;
	}
	

	
	
}