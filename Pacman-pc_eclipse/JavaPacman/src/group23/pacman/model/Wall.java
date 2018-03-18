package group23.pacman.model;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Wall extends GameObject{
	
	private Rectangle wallHitBox;
	private Image mapBlock;
	private double x;
	private double y;
	public Wall(Rectangle rectangle,char map) {
		
		
		/* Choose which tile block to load into the map */
		String mapBlock;
		switch (map) {
			case 's' :
				mapBlock = "assets/mapBlock-sea.png";
				break;
			case 'd' :
				mapBlock = "assets/mapBlock-desert.png";
				break;
			default :
				mapBlock = "assets/mapBlock-default.png";
				break;
		}
		this.mapBlock = new Image(mapBlock,10,10,false,false);
		
		/* Set up object for collision detection */
		this.wallHitBox = rectangle;
		this.type = GameObject.TYPE.WALL;
		this.x = wallHitBox.getX();
		this.y = wallHitBox.getY();

	}

	
	public double getX() {
		
		return this.wallHitBox.getX();
	}
	
	public double getY() {
		
		return this.wallHitBox.getY();
	}
	
	
	public Rectangle getHitBox() {
		 
		return this.wallHitBox;
	}
	
	public void draw(GraphicsContext graphicsContext) {
		
		graphicsContext.drawImage(mapBlock,x,y);

	}
	
	
}
