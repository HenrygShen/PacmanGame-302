public class Game {
	
	private Pacman pacman;
	
	public Game() {
		
		pacman = new Pacman(30,334);
	}
	
	public void update() {

		pacman.update();
	}

	public Pacman getPacman() {
		
		return this.pacman;
	}
}
