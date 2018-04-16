package group23.pacman.view;


import group23.pacman.MainApp;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/** Controller class for the LevelSelect screen */

public class LevelSelectController {
	
	/* Constant - do not change */
	private final int MAX_BACKGROUND_INDEX = 2;
	private final float FADE_SPEED = 0.02f;
	
	/* FXML elements in LevelSelect.fxml */
	@FXML
	private ImageView background;
	@FXML
	private ImageView levelImage;
	@FXML
	private ImageView leftArrow;
	@FXML
	private ImageView rightArrow;
	@FXML
	private ImageView fade;
	
	
	/* Main app copy kept to use when referencing to get its scene. */
	private MainApp mainApp;
	private Scene scene;

	
	/* Variables for showing which background/level/map will be set */
	private int index;
	private Image seaBackground;
	private Image desertBackground;
	private Image classicBackground;
	private Image[] backgrounds;

	
	/* Variable to control scroll speed */
	private long lastTime;
		
	/* Boolean to prevent animation to happen to already animated image */
	private boolean animated;
	
	/* Fade variables */
	private float opacity;
	private long time;
	
	
	/* Constructor */
	public LevelSelectController() {
		
		lastTime = 0;
		animated = false;
		
	}
	
	
	/* Sets up images and backgrounds for initial view */
	@FXML
	private void initialize() {
		
		/* Set up background of this view */
		Image backgroundImage = new Image("bg/background-levelSelect.png");
		background.setImage(backgroundImage);
		
		/* Fade */
		fade.setImage(new Image("bg/blackbg.png"));
		opacity = 1;
		fade.setOpacity(opacity);
		fadeTransition(0);
		
		/* Set up level backgrounds to scroll through */
		seaBackground = new Image("bg/background-sea_levelselect.png");
		desertBackground = new Image("bg/background-deserttemple_levelselect.png");
		classicBackground = new Image("bg/background-forest_levelselect.png");
		backgrounds = new Image[3];
		backgrounds[0] = classicBackground;
		backgrounds[1] = seaBackground;
		backgrounds[2] = desertBackground;
		index = 0;
		levelImage.setImage(backgrounds[index]);
		
		/* Load the arrows */
		Image leftArrowImage = new Image("assets/buttons/leftArrow.png",110,110,false,false);
		Image rightArrowImage = new Image("assets/buttons/rightArrow.png",110,110,false,false);		
		leftArrow.setImage(leftArrowImage);
		rightArrow.setImage(rightArrowImage);
		
	}
	
	
	private void fadeTransition(int mode) {
		
		time = System.currentTimeMillis();
		
		AnimationTimer fadeAnimation = new AnimationTimer() {
			public void handle(long now) {
				
				/* Every 0.05 seconds, move the two backgrounds to the left at SCROLL_SPEED pixels
				 * When it is time to loop,move the images back to the right by the amount scrolled. */
				
				if (System.currentTimeMillis() - time > 0.05f) {
					
					if (mode == 0) {
						opacity -= FADE_SPEED;
					}
					else {
						opacity += FADE_SPEED;
					}
					
					fade.setOpacity(opacity);
					time = System.currentTimeMillis();
				}
				
				if (mode == 0) {
					if (opacity <= 0) {
						this.stop();
					}
				}
				else if (mode == 1){
					if (opacity >= 1) {
						mainApp.showGameView();
						this.stop();
						
					}
				}
				else if (mode == 2) {
					
					if (opacity >= 1) {
						mainApp.showWelcomeScreen();
						this.stop();
						
					}
				}
			}
		};
		fadeAnimation.start();
		
	}
	

	/* Adds key listener to scene */
	public void listenToKeyEvents() {
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
		    @Override
		    public void handle(KeyEvent event) {
		    	
		    	/* Selects the currently shown map/level */
		    	if (event.getCode() == KeyCode.ENTER) {	
			    	char level;
			    	switch (index) {
			    		case 0 :
			    			level = 'c';
			    			break;
			    		case 1 :
			   				level = 's';
			   				break;
			    		case 2 :
			    			level = 'd';
			    			break;
		    			default :
			    			level = 'c';
			    			break;
			    	}
			    		System.out.println("same");
			    	mainApp.setMap(level);
			    	fadeTransition(1);
			    		
		    	}
		    	else if (event.getCode() == KeyCode.LEFT) {
		    		
		    		/* Prevents background from scrolling too fast if key is held down
		    		 * Only changes every half second */
					if (System.currentTimeMillis() - lastTime > 500) {
						lastTime = System.currentTimeMillis();
						setLeftBackground();
					}
					if (!animated) {
						animateLeft();
						animated = true;
					}

				}
				
				else if (event.getCode() == KeyCode.RIGHT) {
					
					/* Prevents background from scrolling too fast if key is held down
		    		 * Only changes every half second */
					if (System.currentTimeMillis() - lastTime > 500) {
						lastTime = System.currentTimeMillis();
						setRightBackground();
					}
					if (!animated) {
						animateRight();
						animated = true;
					}
				}
				else if (event.getCode() == KeyCode.ESCAPE) {
					
					fadeTransition(2);
				}
				
		    }	    
		});
		
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>(){
		    @Override
		    public void handle(KeyEvent event) {
		    	if (event.getCode() == KeyCode.LEFT) {
		    		resetLArrow();
		    		lastTime = 0;
		    		animated = false;
				}
				
				else if (event.getCode() == KeyCode.RIGHT) {
					resetRArrow();
		    		lastTime = 0;
		    		animated = false;
				}
		    }	    
		});
	}
	
	
	/* Public setter for this class to reference the main application */
	public void setMainApp(MainApp mainApp) {
		
		this.mainApp = mainApp;
		this.scene = mainApp.getScene();
	}
		
	
	
	/** BELOW ARE HELPER FUNCTIONS WHICH HELP WITH THE ANIMATION OF THIS VIEW **/
	
	/* Set background functions - 
	 * Help scroll the background to the left or the right
	 */
	private void setLeftBackground() {
		index--;
		index = (index < 0) ? MAX_BACKGROUND_INDEX : index;
		levelImage.setImage(backgrounds[index]);
	}
	
	private void setRightBackground() {
		index++;
		index = (index > MAX_BACKGROUND_INDEX) ? 0 : index;
		levelImage.setImage(backgrounds[index]);
	}
	
	/* Animate functions help "animate" the arrow keys, by
	 * enlarging them as the respective key is held down.
	 * This gives the user feedback on the key press and is a nice little feature for the UI*/
	private void animateLeft() {
		
		leftArrow.setX(- 40);
        leftArrow.setY(- 40);
		leftArrow.setFitHeight(150);
		leftArrow.setFitWidth(150);
        leftArrow.setImage(new Image("assets/buttons/leftArrow.png",150,150,false,false));
        
	}
	
	private void animateRight() {
		
		rightArrow.setX(0);
		rightArrow.setY(-40);
		rightArrow.setFitHeight(150);
		rightArrow.setFitWidth(150);
		rightArrow.setImage(new Image("assets/buttons/rightArrow.png",150,150,false,false));

	}
	
	/* Reset functions help reset the arrows to their original size to let the 
	 * user know when the key is released.
	 */
	private void resetLArrow() {
		
		leftArrow.setX(0);
        leftArrow.setY(0);
		leftArrow.setFitHeight(110);
		leftArrow.setFitWidth(110);
        leftArrow.setImage(new Image("assets/buttons/leftArrow.png",110,110,false,false));
	}
	
	private void resetRArrow() {
		
		rightArrow.setX(0);
		rightArrow.setY(0);
		rightArrow.setFitHeight(110);
		rightArrow.setFitWidth(110);
		rightArrow.setImage(new Image("assets/buttons/rightArrow.png",110,110,false,false));
	}

}
