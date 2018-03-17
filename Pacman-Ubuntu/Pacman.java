
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Pacman extends GameObject{
	
	
	private static final int SPRITE_HEIGHT = 30;
	private static final int SPRITE_WIDTH = 30;
	private static final int SPEED = 2;
	private char vector;
	private AnimationManager animationManager;

	private char queuedDirection;
	
	//private Controls controller;
	private Rectangle theoreticalHitBox;
	public Pacman(int x,int y) {
		


		/* Set up the frame animation for the main character */
		Image leftC = new Image("assets/Pacman/leftClosed.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image leftO = new Image("assets/Pacman/leftOpen.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image rightC = new Image("assets/Pacman/rightClosed.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image rightO = new Image("assets/Pacman/rightOpen.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image upC = new Image("assets/Pacman/upClosed.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image upO = new Image("assets/Pacman/upOpen.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image downC = new Image("assets/Pacman/downClosed.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		Image downO = new Image("assets/Pacman/downOpen.png",SPRITE_WIDTH,SPRITE_HEIGHT,false,false);
		
		Image[] leftMove = new Image[2];
		leftMove[0] = leftC;
		leftMove[1] = leftO;
		
		Image[] rightMove = new Image[2];
		rightMove[0] = rightC;
		rightMove[1] = rightO;
		
		Image[] upMove = new Image[2];
		upMove[0] = upC;
		upMove[1] = upO;
		
		Image[] downMove = new Image[2];
		downMove[0] = downC;
		downMove[1] = downO;
		
		Animation leftAnimation = new Animation(leftMove,0.3f);
		Animation rightAnimation = new Animation(rightMove,0.3f);
		Animation upAnimation = new Animation(upMove,0.3f);
		Animation downAnimation = new Animation(downMove,0.3f);
		
		Animation[] movementAnimations = new Animation[4];
		movementAnimations[0] = leftAnimation;
		movementAnimations[1] = rightAnimation;
		movementAnimations[2] = upAnimation;
		movementAnimations[3] = downAnimation;
		
		animationManager = new AnimationManager(movementAnimations);
		

		/* Sets up the main character's hit-box */
		hitBox = new Rectangle();
		hitBox.setHeight(SPRITE_HEIGHT);
		hitBox.setWidth(SPRITE_WIDTH);
		hitBox.setX(x);
		hitBox.setY(y);
		theoreticalHitBox = new Rectangle();
		theoreticalHitBox.setHeight(SPRITE_HEIGHT);
		theoreticalHitBox.setWidth(SPRITE_WIDTH);
		theoreticalHitBox.setX(x);
		theoreticalHitBox.setY(y);
		
		this.vector = 'S';
		this.queuedDirection = 'S';
		
		
	
	}
	
	public void update() {	
		
		animationManager.update();
		playAnimation();
	}
	
	public void queueMovement(char queuedDirection) {
		
		this.queuedDirection = queuedDirection;
	}
	
	public void draw(GraphicsContext graphicsContext) {
		
		animationManager.draw(graphicsContext,this.hitBox.getX(),this.hitBox.getY());
	}
    
    public void setDirection(char vector) {
    	
    	this.vector = vector;	
    }
    
    public char getDirection() {
    	
    	return this.vector;
    }
    
    public char getQDirection() {
    	
    	return this.queuedDirection;
    }
    
    public boolean collidedWith(GameObject object) {
    	
    	
    	Rectangle hitBox = object.getHitBox();
    	
    	return this.hitBox.intersects(hitBox);
    }

    
    public boolean checkforQueuedAction() {
    		
	    return (queuedDirection != vector);
    }
    
    public boolean oppositeDirection() {
    	switch (vector) {
    		case 'S':
    			return true;
			case 'U':
				if (queuedDirection == 'D') {
					return true;
				}
			case 'D':
				if (queuedDirection == 'U') {
					return true;
				}
			case 'L':
				if (queuedDirection == 'R') {
					return true;
				}
			case 'R':
				if (queuedDirection == 'L') {
					return true;
				}
    	}
    	return false;
    }
        

    public void setNewMove() {
    	
    	this.hitBox.setY((int)theoreticalHitBox.getY());
    	this.hitBox.setX((int)theoreticalHitBox.getX());
    	setDirection(queuedDirection);
    }
    
    public void changeMovement() {
    
    		
    	if (this.vector == 'U') {
			this.hitBox.setY((int)hitBox.getY() - SPEED);
		}
		else if (this.vector == 'D') {
			this.hitBox.setY((int)hitBox.getY() + SPEED);
		}
		else if (this.vector == 'L') {
			this.hitBox.setX((int)hitBox.getX() - SPEED);
		}
		else if (this.vector == 'R') {
			this.hitBox.setX((int)hitBox.getX() + SPEED);
		}
    }

    
    public void playAnimation() {
    	
    	if (this.vector == 'S') {
			animationManager.playAction(1);
		}
		if (this.vector == 'U') {
			animationManager.playAction(2);
		}
		else if (this.vector == 'D') {
			animationManager.playAction(3);
		}
		else if (this.vector == 'L') {
			animationManager.playAction(0);
		}
		else if (this.vector == 'R') {
			animationManager.playAction(1);
		}
    }
    
   public void resetPosition(double x, double y) {
	   
		if (this.vector == 'U') {
			this.hitBox.setY((int)y + 10);
		}
		else if (this.vector == 'D') {
			this.hitBox.setY((int)y - SPRITE_HEIGHT);
		}
		else if (this.vector == 'L') {
			this.hitBox.setX((int)x + 10);
		}
		else if (this.vector == 'R') {
			this.hitBox.setX((int)x - SPRITE_WIDTH);
		}
    }
}