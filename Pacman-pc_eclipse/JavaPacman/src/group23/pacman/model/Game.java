package group23.pacman.model;
import java.io.File;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
	This is the class that handles all the game logics - collisions, level handling, and creation of the map.
 */
public class Game {
	
	
	private Pacman pacman;
	private Ghost ghost;
	private Media chompNoise;
	private MediaPlayer mediaPlayer;
	private ArrayList<GameObject> objects;
	private Board board;
	private char map;
	private int score;
	
	
	public Game(char map) {
		
		this.map = map;
		board = new Board();
		board.setGame(this);
		board.createBoard();
		objects = board.getObjects();
		pacman = new Pacman(board.getPacman()[0],board.getPacman()[1]);
		ghost = new Ghost(board.getGhost()[0],board.getGhost()[1]);
		chompNoise = new Media(new File("bin/assets/sfx/chompNoise.mp3").toURI().toString());
		
		score = 0;
	
	}
	
	
	public void update( ) {
		
		checkCollisions();
		pacman.update();
		ghost.update();	
	}
	
	private void checkCollisions() {
		
		ghost.queueMovement();
		
		
		/* If the currently queued direction is not equal to the current direction we are moving in, and it is possible 
		   for us to turn in our current (x,y) position, test if turn is valid (not into a wall), then set the queued direction
		   if valid. */
		if (pacman.checkforQueuedAction() && board.validTurningPoint((int) pacman.getX(), (int) pacman.getY())) {
			if (board.isValidDestination(pacman.getQDirection(), (int) pacman.getX(), (int) pacman.getY())){
				pacman.setDirection(pacman.getQDirection());
				pacman.changeMovement();
				//return;
			}
		}
		if (board.isValidDestination(pacman.getDirection(), (int) pacman.getX(), (int) pacman.getY())) {
			pacman.changeMovement();
		}
		
		
		if (ghost.checkforQueuedAction() && board.validTurningPoint((int) ghost.getX(), (int) ghost.getY())) {
			if (board.isValidDestination(ghost.getQDirection(), (int) ghost.getX(), (int) ghost.getY())){
				ghost.setDirection(ghost.getQDirection());
				ghost.changeMovement();
				return;
			}
		}
		if (board.isValidDestination(ghost.getDirection(), (int) ghost.getX(), (int) ghost.getY())) {
			ghost.changeMovement();
		}
		
		
	
		/* Loops through the game objects to check if the player has collided with a pellet. Pellet is removed on collision
		 * and TODO: Score will increase */
		for (GameObject object : objects) {

			if (pacman.collidedWith(object)) {
				if (object.getType() == GameObject.TYPE.PELLET) {
					playSfx(chompNoise);
					objects.remove(object);
					score++;
					break;
				}
		
			}
		}
	}
	
	

	public Pacman getPacman() {
		
		return this.pacman;
	}
	
	public Ghost getGhost() {
		
		return this.ghost;
	}
	
	public char getMap() {
		
		return this.map;
	}	
	
	public void drawObjects(GraphicsContext graphicsContext) {
		
		for (GameObject object : objects) {
			object.draw(graphicsContext);
		}
	}
	
	public void playSfx(Media sfx) {
		mediaPlayer = new MediaPlayer(sfx);
		mediaPlayer.setVolume(0.3);
		mediaPlayer.play();
	}
	
	public String getScore() {
		String tempScore = Integer.toString(this.score);
		tempScore = new StringBuilder(tempScore).reverse().toString();
        while (tempScore.length() < 4){
           	tempScore = tempScore + "x";
        }
        return tempScore;
	}


}
