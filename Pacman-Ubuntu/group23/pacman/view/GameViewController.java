package group23.pacman.view;

import java.util.ArrayList;
import group23.pacman.MainApp;
import group23.pacman.controller.GameStateController;
import group23.pacman.model.Game;
import group23.pacman.model.GameObject;
import group23.pacman.model.Timer;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;

/** Controller class for the GameView screen */

public class GameViewController {
	
	
	/* View elements in GameView.fxml */
	@FXML
	private ImageView background_map;
	@FXML
	private ImageView digit_ones;
	@FXML
	private ImageView digit_tens;
	@FXML
	private ImageView digit_hunds;
	@FXML
	private ImageView digit_thous;
	@FXML
	private ImageView life_1;
	@FXML
	private ImageView life_2;
	@FXML
	private ImageView life_3;
	@FXML
	private ImageView lives_image;
	@FXML
	private ImageView min_ones;
	@FXML
	private ImageView colon;
	@FXML
	private ImageView sec_tens;
	@FXML
	private ImageView sec_ones;
	@FXML
	private ImageView start_timer;
	@FXML
	private ImageView whip_charges;
	@FXML
	private ImageView slash;
	@FXML
	private ImageView max_charges;
	@FXML
	private ImageView game_info_panel;

	private long holdTime;
	private long countDownTime;

	/* Reference to the main app */
	private MainApp mainApp;
	
	/* Paint to canvas */
	private GraphicsContext graphicsContext;
	
	/* Allows user control */
	private GameStateController gameStateController;
	
	/* Stores game state (paused, running)*/
	private boolean running = false;
	
	private AnimationTimer animationLoop;
	
	/* Time */
	private long time = 0;
	
	/* Used to manipulate time for showing to screen */
	private Timer timer;
	

	private String name;

	
	
	public GameViewController() {
		
	}
	
	/* Creates a game based on user selected map */
	public void setGame(Game game) {
		
		/* First, select map based on user input */
		char map = game.getMap();
		String backgroundImage;
		switch (map) {
    	case 'c' :
    		backgroundImage = "bg/background-forest_game.png";
    		break;
	    case 's' :
	    	backgroundImage = "bg/background-sea_game.png";
	    	break;
	    case 'd' :
	    	backgroundImage = "bg/background-deserttemple_game.png";
	    	break;
	    case 'r' :
	    	backgroundImage = "assets/tiles/mapBlock-ruins_game.png";
			break;
	    default :
	    	backgroundImage = "bg/background-classic_game.png";
	    	break;
		}
		/*Second, set the map as the background */
		background_map.setImage(new Image(backgroundImage));
		
		/* Create a controller through which the user may play the game */
		gameStateController = new GameStateController(this,game);
		
		int numberOfPlayers = game.getPlayers();
		
		switch (numberOfPlayers) {
		
			case 1:
				gameStateController.listenSinglePlayer();
				break;
			case 2:
				gameStateController.listenTwoPlayer();
				break;
			case 3:
				gameStateController.listenThreePlayer();
				break;
			default:
				gameStateController.listenSinglePlayer();
				break;
			
		}
		
	}
	
	/* Sets up initial view */
	@FXML
	private void initialize() {
		
		game_info_panel.setImage(new Image("bg/game_info_panel.png"));
		
		/* Initialize 2 minute timer */
		timer = new Timer(120);
		min_ones.setImage(new Image("assets/numbers/2.png"));
		colon.setImage(new Image("assets/misc/colon.png"));
		sec_tens.setImage(new Image("assets/numbers/0.png"));
		sec_ones.setImage(new Image("assets/numbers/0.png"));
		
		/* Lives */
		life_1.setImage(new Image("assets/Pacman/rightOpen.png"));
		life_2.setImage(new Image("assets/Pacman/rightOpen.png"));
		life_3.setImage(new Image("assets/Pacman/rightOpen.png"));
		
		/*Charges */
		whip_charges.setImage(new Image("assets/numbers/0.png"));
		slash.setImage(new Image("assets/misc/slash.png"));
		max_charges.setImage(new Image("assets/numbers/6.png"));
		
		/* Score starts off as 0 */
		String digitimage = "assets/numbers/0.png";
		digit_ones.setImage(new Image(digitimage));
		digit_tens.setImage(new Image(digitimage));
		digit_hunds.setImage(new Image(digitimage));
		digit_thous.setImage(new Image(digitimage));

	}
	
	/* Pauses/starts the game */
	public void toggleState() {

		this.running = !this.running;
	}
	
	
	public void initialDraw() {
		
		
		/* Add canvas for drawing the wall objects, and only 
		 * draw the walls once */
		Canvas canvasWall = new Canvas(1366,768);
		mainApp.getPane().getChildren().add(canvasWall);
		GraphicsContext gcWall = canvasWall.getGraphicsContext2D();
		ArrayList<GameObject> objects = gameStateController.getGame().getOtherGameObjects();
		for (GameObject object : objects) {
			if (object.getType() == GameObject.TYPE.WALL) {
				object.draw(gcWall);
			}
		}
		
		/* Add separate canvas for drawing everything else */
		Canvas canvas = new Canvas(1366,768);
		mainApp.getPane().getChildren().add(canvas);
		
		graphicsContext = canvas.getGraphicsContext2D();
		gameStateController.update();
		gameStateController.update();
		
		
		draw(graphicsContext);
		startCountdown();
		
	}
	
	
	public void startGame() {

		time = System.currentTimeMillis();

		/* Animation timer to update frames */
		animationLoop = new AnimationTimer() {
			public void handle(long now) {
				
				/* Make sure game isn't in paused state */
				if (running == true) {
					graphicsContext.clearRect(0, 0, 1366, 768);
					gameStateController.update();
					draw(graphicsContext);
					updateTimer();
				}
				else {
					time = System.currentTimeMillis();
				}
			}
		};
		animationLoop.start();

	}
	
	
	public void stopGame() {
		
		running = false;
		animationLoop.stop();
	}
	
	
	/* Update after timer times out */
	public void finalUpdate() {
		
		graphicsContext.clearRect(0, 0, 1366, 768);
		gameStateController.update();
		draw(graphicsContext);
	}
	
	
	/* Count-down that shows at the start of every new round/game */
	public void startCountdown() {
		
		/* Count down timer starts at 3 seconds */
		Timer timerStart = new Timer(0);
		
		/* The game is paused while counting down */
		running = false;
		
		start_timer.setImage(new Image(getDigit((char)timerStart.getSecOnes())));
		
		countDownTime = System.currentTimeMillis();
		
		new AnimationTimer() {
			
			public void handle(long now) {
				
				/* Count down 1 second every second */
				if (System.currentTimeMillis() - countDownTime >= 1000) {
					timerStart.countDown(1);
					countDownTime = System.currentTimeMillis();
					start_timer.setImage(new Image(getDigit((char)timerStart.getSecOnes())));
				}
				/* After time has counted to 0, start the game */
				if (timerStart.timedOut()) {
					this.stop();
					start_timer.setImage(new Image("assets/misc/empty.png"));
					running = true;
				}
				
			}

		}.start();
		
	}
	
	
	/* Updates time remaining */
	private void updateTimer() {
		
		if (System.currentTimeMillis() - time >= 1000) {
			timer.countDown(1);
			time = System.currentTimeMillis();
			setTimerImage();
		}

	}
	
	
	/* Shows the current time to the screen */
	public void setTimerImage() {
		
		min_ones.setImage(new Image(getDigit((char)timer.getMinOnes())));
		sec_tens.setImage(new Image(getDigit((char)timer.getSecTens())));
		sec_ones.setImage(new Image(getDigit((char)timer.getSecOnes())));
	}
	
	
	/* Draws all objects */
	private void draw(GraphicsContext graphicsContext) {
		
		
		gameStateController.getGame().getPacman().draw(graphicsContext);
		gameStateController.getGame().getPacman().getWhip().draw(graphicsContext);
		updateScore();
		updateWhipCharges();
		
		/* Draws other objects (pellets) */
		ArrayList<GameObject> objects = gameStateController.getGame().getOtherGameObjects();
		for (GameObject object : objects) {
			if (object.getType() != GameObject.TYPE.WALL)
			object.draw(graphicsContext);
		}
		
		gameStateController.getGame().getGhost().draw(graphicsContext);
		gameStateController.getGame().getGhost2().draw(graphicsContext);
		gameStateController.getGame().getGhost3().draw(graphicsContext);
		gameStateController.getGame().getGhost4().draw(graphicsContext);
		
		
		/* Conditionals for end of game */
		if (gameStateController.levelCleared()) {
			graphicsContext.drawImage(new Image("assets/misc/pass.png", 500, 250,false,false),283,284);
		}
		else if (gameStateController.gameOver()) {
			graphicsContext.drawImage(new Image("assets/misc/game_over.png",500,250,false,false),283,284);
		}
		
		
	}
	
	
	/* Method used in GameStateController to leave game via button press */
	public void showMenu() {
		
		stopGame();
		mainApp.gameToMenu();
		mainApp.showWelcomeScreen();
	}
	
	
	public void showGameEnd() {
		
		stopGame();
		holdFrame();

	}
	
	
	private void holdFrame() {
		
		
		Timer holdTimer = new Timer(2);
		holdTime = System.currentTimeMillis();
		
		new AnimationTimer() {
			
			public void handle(long now) {
				
				
				if (System.currentTimeMillis() - holdTime >= 1000) {
					holdTimer.countDown(1);
					holdTime = System.currentTimeMillis();
					
				}
				if (holdTimer.timedOut()) {
					this.stop();
					/* Calculate bonuses */
					int time = timer.getTimeRemaining();
					int lives = gameStateController.getGame().getPacman().getLives();
					int score = gameStateController.getGame().getIntScore();
					System.out.println("Time  = " + time);
					System.out.println("Lives  = " + lives);
					System.out.println("Score  = " + score);
	
					mainApp.showResults(time,lives,score,gameStateController.getGame().getMap());
				}
			}
		}.start();
	}
	
	
	
	
	/* Creates dialog stage using method from mainApp to get user's name */
	public void showTextField() {
		
		mainApp.setName();
		name = mainApp.getName();
	}
	
	
	/* Updates the images of score digits to reflect user's score */
	private void updateScore() {
		
		/* Updates each digit */
		for (int i = 0; i < 4 ; i++) {
			setDigitImage(getDigit(gameStateController.getGame().getScore().charAt(i)), i);
		}
	}
	
	
	/* Helper function for updateScore() */
	private void setDigitImage(String image, int digit) {
		switch (digit) {
			case 0 :
				digit_ones.setImage(new Image(image));
				break;
			case 1 :
				digit_tens.setImage(new Image(image));
				break;
			case 2 :
				digit_hunds.setImage(new Image(image));
				break;
			case 3 :
				digit_thous.setImage(new Image(image));
				break;
		}
	}
	
	
	/* Prints to UI how many charges left */
	private void updateWhipCharges() {
		
		whip_charges.setImage(new Image(getDigit(gameStateController.getGame().getCharges().charAt(0))));
	}
	

	/* Draws to the screen how many lives the player has left */
	public void showLivesLeft() {
		
		for (int i = gameStateController.getGame().getPacman().getLives(); i < 3; i++) {
			setLivesImage("assets/misc/empty.png", i);
		}
	}
	
	
	/* Helper function for showLivesLeft() */
	public void setLivesImage(String image, int number) {
		
		switch (number) {
		case 0 :
			life_1.setImage(new Image(image));
			break;
		case 1 :
			life_2.setImage(new Image(image));
			break;
		case 2 :
			life_3.setImage(new Image(image));
			break;
		}
	}
	
	
	/* Helper function to break score digits down (to more easily show in UI) */
	private String getDigit(char digit){

        switch (digit){
            case '0' :
                return "assets/numbers/0.png";
            case '1' :
                return "assets/numbers/1.png";
            case '2' :
                return "assets/numbers/2.png";
            case '3' :
                return "assets/numbers/3.png";
            case '4' :
                return "assets/numbers/4.png";
            case '5' :
                return "assets/numbers/5.png";
            case '6' :
                return "assets/numbers/6.png";
            case '7' :
                return "assets/numbers/7.png";
            case '8' :
                return "assets/numbers/8.png";
            case '9' :
                return "assets/numbers/9.png";
            default :
                return "assets/numbers/0.png";
        }
    }

	
	
	/** PUBLIC GETTERS AND SETTERS BELOW */
	
	/* Public getter to pass name to GameStateController */
	public String getName() {
		
		return name;
	}
	
	/* Public getter to pass scene to GameStateController */
	public Scene getScene() {
		
		return this.mainApp.getScene();
	}
	
	/* Public setter to reference main app */
	public void setMainApp(MainApp mainApp) {
		
		this.mainApp = mainApp;
	}
	
	public Timer getTimer() {
		
		return this.timer;
	}
	
	

}
