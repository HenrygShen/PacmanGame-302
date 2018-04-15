package group23.pacman.view;

import group23.pacman.MainApp;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/** The controller class for the welcome screen view */
public class WelcomeScreenController {
	
	/* Constants - do not change */
	private final int BUTTON_WIDTH = 300;
	private final int BUTTON_HEIGHT = 50;
	private final float FADE_SPEED = 0.02f;
	
	
	/* View elements in WelcomeScreen.fxml */
	@FXML 
	private ImageView playBtnImage;
	@FXML 
	private ImageView tutorialBtnImage;
	@FXML
	private ImageView leaderboardBtnImage;
	@FXML
	private ImageView creditsBtnImage;
	@FXML
	private ImageView singlePlayerImage;
	@FXML
	private ImageView twoPlayerImage;
	@FXML
	private ImageView threePlayerImage;
	@FXML
	private ImageView exitBtnImage;
	@FXML
	private ImageView background;
	@FXML
	private ImageView fade;
	@FXML
	private ImageView title;
	
	
	/* Main app copy kept to use when referencing to show other views */ 
	private MainApp mainApp;
	
	/* Helps keep track of which button is hovered over */
	private int buttonIndex;
	
	/* Locks the user in game mode selection */
	private boolean playSelected;
	
	/* Keeps track of which game mode is hovered over */
	private int numPlayers;
	
	
	/* Fade variables */
	private float opacity;
	private long time;
	
	
	
	/* Constructor */
	public WelcomeScreenController() {
		
		
	}

	
	/* Sets up images and backgrounds for initial view */
	@FXML
	private void initialize() {
		
		/* Loads all button and background assets to their respective ImageView elements */
		title.setImage(new Image("assets/misc/title.png"));
		background.setImage(new Image("bg/background-main.png"));

		/* Images for layering over buttons */
		playBtnImage.setImage(new Image("assets/buttons/button-play-highlighted.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
		tutorialBtnImage.setImage(new Image("assets/buttons/button-tutorial.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
		leaderboardBtnImage.setImage(new Image("assets/buttons/button-leaderboard.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
		creditsBtnImage.setImage(new Image("assets/buttons/button-credits.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
		exitBtnImage.setImage(new Image("assets/buttons/button-exit.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
		
		/* Fade animation for coming into and out of this view*/
		fade.setImage(new Image("bg/blackbg.png"));
		opacity = 0;
		fade.setOpacity(opacity);

		
		/* Player does not start off with the selection of game modes(i.e. single,two, or three player) */
		playSelected = false;
		
		/* Default game mode is single player */
		numPlayers = 1;
		
		/* First button highlighted and selected is the Play button */
		buttonIndex= 0;
		
	}
	
	
	private void fadeTransition() {
		
		time = System.currentTimeMillis();
		
		AnimationTimer fadeAnimation = new AnimationTimer() {
			public void handle(long now) {
				
				/* Every 0.05 seconds increase the opacity of the black background to give illusion of fading out of this scene */
				if (System.currentTimeMillis() - time > 0.05f) {
					
					opacity += FADE_SPEED;
					fade.setOpacity(opacity);
					time = System.currentTimeMillis();
				}
				if (opacity >= 1) {
					if (numPlayers == 1) {
						this.stop();
						mainApp.showLevelSelect();
					}
					else {
						this.stop();
						mainApp.showCharacterSelect();
					}	
					
				}
			}
		};
		fadeAnimation.start();
	}
	
	
	/* Adds listener to the button in this view */
	public void addKeyListener() {

		mainApp.getScene().setOnKeyPressed(new EventHandler<KeyEvent> (){
			@Override
			public void handle(KeyEvent event) {
				/* KEY PRESS TYPE 1 */
				/* ENTER is the confirmation key */
				if (event.getCode() == KeyCode.ENTER) {
					
					/* If the player presses ENTER while selecting game mode, save the game mode and play fade animation, then send user to the map/level selection screen 
					 * If there is more than one player, then send them to the character selection screen. */
					if (playSelected) {
						
						mainApp.setPlayers(numPlayers);
						fadeTransition();

					}
					
					/* If user presses ENTER while not selecting game mode, but is hovered on the play button, show the various game modes and 
					 * the buttons for selection. */
					else if (buttonIndex == 0) {
						
						/* Lock user in game mode select */
						playSelected = true;
						
						/* Default game mode is single player */
						numPlayers = 1;
						
						/* Load buttons */
						Image singlePlayer = new Image("assets/buttons/singlePlayer.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false);
						singlePlayerImage.setImage(singlePlayer);
						
						Image twoPlayer = new Image("assets/buttons/twoPlayer.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false);
						twoPlayerImage.setImage(twoPlayer);
						
						Image threePlayer = new Image("assets/buttons/ThreePlayer.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false);
						threePlayerImage.setImage(threePlayer);
						
						highlightPlayers();
						
					}
					
					/* If user presses ENTER while hovering over the tutorial button */
					else if (buttonIndex == 1) {
						
						mainApp.showHelp();
					}
					
					/* If user presses ENTER while on the exit button, close the game */
					else if (buttonIndex == 2) {
						
						//TODO leaderboard
						//mainApp.showLeaderboard();
					}
					else if (buttonIndex == 3) {
						
						mainApp.showCredits();
					}
					else if (buttonIndex == 4) {
						Platform.exit();
					}
				}
				
				/* Key press TYPE 2 */
				/* The UP and DOWN key presses help the user navigate this view */
				else if (event.getCode() == KeyCode.UP) {
					
					/* If locked in game mode selection, the up key navigates through the game mode selection buttons */
					if (playSelected) {
						
						/* Never have less than 1 player */
						numPlayers--;
						numPlayers = (numPlayers < 1) ? 1 : numPlayers;
						highlightPlayers();
					}
					
					/* If not locked in game mode selection, go up the button list */
					else {
						
						/* Button index starts at 0, never less */
						buttonIndex--;
						buttonIndex = (buttonIndex < 0 ) ? 0 : buttonIndex;
						highlightButton();
					}
				}
				/* Key press TYPE 3 */
				/* DOWN key has the same functionality as UP key description, though in the other direction */
				else if (event.getCode() == KeyCode.DOWN) {
					
					if (playSelected) {
						numPlayers++;
						numPlayers = (numPlayers > 3) ? 3 : numPlayers;
						highlightPlayers();
					}
				
					else {
						buttonIndex++;
						buttonIndex = (buttonIndex > 4 ) ? 4 : buttonIndex;
						highlightButton();
					}
				}
				
				/* Key press TYPE 4 */
				/* ESCAPE key does nothing unless user is trying to break out of game mode select */
				else if (event.getCode() == KeyCode.ESCAPE) {
					
					if (playSelected) {
						singlePlayerImage.setImage(new Image("assets/misc/empty.png"));
						twoPlayerImage.setImage(new Image("assets/misc/empty.png"));
						threePlayerImage.setImage(new Image("assets/misc/empty.png"));
						playSelected = false;
						numPlayers = 1;
					}
				}
				
				/* DEBUG statements */
				System.out.println("Button Index " + buttonIndex);
				System.out.println("Number of players " + numPlayers + "\n");
			}
		});
		

	}
	
	
	/* Public setter to reference main application */
	public void setMainApp(MainApp mainApp) {
		
		this.mainApp = mainApp;
	}
	
	
	
	/** BELOW ARE HELPER FUNCTIONS WHICH HELP WITH THE ANIMATION OF THIS VIEW **/
	/* Helper function for highlighting buttons to show which button is being hovered over*/
	private void highlightPlayers() {
		
		if (numPlayers == 1) {
			singlePlayerImage.setImage(new Image("assets/buttons/singlePlayer-highlighted.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			twoPlayerImage.setImage(new Image("assets/buttons/twoPlayer.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			threePlayerImage.setImage(new Image("assets/buttons/threePlayer.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
		}
		else if (numPlayers == 2) {
			singlePlayerImage.setImage(new Image("assets/buttons/singlePlayer.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			twoPlayerImage.setImage(new Image("assets/buttons/twoPlayer-highlighted.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			threePlayerImage.setImage(new Image("assets/buttons/threePlayer.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
		}
		else if (numPlayers == 3) {
			singlePlayerImage.setImage(new Image("assets/buttons/singlePlayer.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			twoPlayerImage.setImage(new Image("assets/buttons/twoPlayer.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			threePlayerImage.setImage(new Image("assets/buttons/threePlayer-highlighted.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
		}
	}
	
	/* Helper function for highlighting buttons to show which button is being hovered over*/
	private void highlightButton() {
		
		if (buttonIndex == 0) {
			playBtnImage.setImage(new Image("assets/buttons/button-play-highlighted.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			tutorialBtnImage.setImage(new Image("assets/buttons/button-tutorial.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			leaderboardBtnImage.setImage(new Image("assets/buttons/button-leaderboard.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			creditsBtnImage.setImage(new Image("assets/buttons/button-credits.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			exitBtnImage.setImage(new Image("assets/buttons/button-exit.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
		}
		else if (buttonIndex == 1) {
			playBtnImage.setImage(new Image("assets/buttons/button-play.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			tutorialBtnImage.setImage(new Image("assets/buttons/button-tutorial-highlighted.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			leaderboardBtnImage.setImage(new Image("assets/buttons/button-leaderboard.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			creditsBtnImage.setImage(new Image("assets/buttons/button-credits.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			exitBtnImage.setImage(new Image("assets/buttons/button-exit.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
		}
		else if (buttonIndex == 2) {
			playBtnImage.setImage(new Image("assets/buttons/button-play.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			tutorialBtnImage.setImage(new Image("assets/buttons/button-tutorial.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			leaderboardBtnImage.setImage(new Image("assets/buttons/button-leaderboard-highlighted.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			creditsBtnImage.setImage(new Image("assets/buttons/button-credits.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			exitBtnImage.setImage(new Image("assets/buttons/button-exit.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
		}
		else if (buttonIndex == 3) {
			playBtnImage.setImage(new Image("assets/buttons/button-play.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			tutorialBtnImage.setImage(new Image("assets/buttons/button-tutorial.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			leaderboardBtnImage.setImage(new Image("assets/buttons/button-leaderboard.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			creditsBtnImage.setImage(new Image("assets/buttons/button-credits-highlighted.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			exitBtnImage.setImage(new Image("assets/buttons/button-exit.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
		}
		else if (buttonIndex == 4) {
			playBtnImage.setImage(new Image("assets/buttons/button-play.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			tutorialBtnImage.setImage(new Image("assets/buttons/button-tutorial.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			leaderboardBtnImage.setImage(new Image("assets/buttons/button-leaderboard.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			creditsBtnImage.setImage(new Image("assets/buttons/button-credits.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
			exitBtnImage.setImage(new Image("assets/buttons/button-exit-highlighted.png",BUTTON_WIDTH,BUTTON_HEIGHT,false,false));
		}

	}

}
