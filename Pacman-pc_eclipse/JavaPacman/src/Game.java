import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Game {
	
	
	private Pacman pacman;
	private Ghost ghost;
	private Media chompNoise;
	private MediaPlayer mediaPlayer;
	private ArrayList<GameObject> objects;
	private Board board;
	
	
	public Game() {
		
		board = new Board();
		objects = board.getObjects();
		pacman = new Pacman(43,44);
		ghost = new Ghost(300,334);
		chompNoise = new Media(new File("bin\\assets\\sfx\\chompNoise.mp3").toURI().toString());
	
	}
	
	public void update() {
		
		checkCollisions();
		pacman.update();
		ghost.update();
		
	}
	
	private void checkCollisions() {
		
		//boolean crashed = false;
		//pacman.resetCrashCount();
		
		if (pacman.checkforQueuedAction() && board.canTurn((int) pacman.getX(), (int) pacman.getY())) {
			if (board.testNextMove(pacman.getQDirection(), (int) pacman.getX(), (int) pacman.getY())){
				pacman.setDirection(pacman.getQDirection());
				pacman.changeMovement();
				return;
			}
		}
		if (board.testNextMove(pacman.getDirection(), (int) pacman.getX(), (int) pacman.getY())) {
			pacman.changeMovement();
		}
		
		/*for (GameObject object : objects) {
			if (pacman.checkforQueuedAction() && (object.getType() == GameObject.TYPE.WALL)) {
				pacman.checkQueuedMovement(object);
			}
		}
		
		if((pacman.getCrashCount() == 0) && (pacman.checkforQueuedAction())) {
			pacman.setNewMove();
			return;
		}*/
		
		
		
		
		for (GameObject object : objects) {

			if (pacman.collidedWith(object)) {
				if (object.getType() == GameObject.TYPE.PELLET) {
					playSfx(chompNoise);
					objects.remove(object);
					break;
				}
				/*else if (object.getType() == GameObject.TYPE.WALL) {
					pacman.setMoving(false);
					pacman.resetPosition(object.getX(),object.getY());
					crashed = true;
					break;
				}*/
			}
		}
		
		/*if (!crashed) {
			pacman.setMoving(true);
		}*/
	}
	
	

	public Pacman getPacman() {
		
		return this.pacman;
	}
	
	public Ghost getGhost() {
		
		return this.ghost;
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


}
