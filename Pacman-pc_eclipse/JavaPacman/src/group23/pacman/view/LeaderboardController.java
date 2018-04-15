package group23.pacman.view;

import java.io.BufferedReader;
import java.io.FileReader;

import group23.pacman.MainApp;
import group23.pacman.model.ScoreHandler;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LeaderboardController {

	private MainApp mainApp;
	
	@FXML
	private ImageView background;
	@FXML
	private ImageView first_character1;
	@FXML
	private ImageView first_character2;
	@FXML
	private ImageView first_character3;
	@FXML
	private ImageView first_character4;
	@FXML
	private ImageView first_character5;
	@FXML
	private ImageView first_character6;
	@FXML
	private ImageView first_character7;
	@FXML
	private ImageView first_character8;
	@FXML
	private ImageView first_character9;
	@FXML
	private ImageView first_character10;
	
	@FXML
	private ImageView second_character1;
	@FXML
	private ImageView second_character2;
	@FXML
	private ImageView second_character3;
	@FXML
	private ImageView second_character4;
	@FXML
	private ImageView second_character5;
	@FXML
	private ImageView second_character6;
	@FXML
	private ImageView second_character7;
	@FXML
	private ImageView second_character8;
	@FXML
	private ImageView second_character9;
	@FXML
	private ImageView second_character10;
	
	@FXML
	private ImageView third_character1;
	@FXML
	private ImageView third_character2;
	@FXML
	private ImageView third_character3;
	@FXML
	private ImageView third_character4;
	@FXML
	private ImageView third_character5;
	@FXML
	private ImageView third_character6;
	@FXML
	private ImageView third_character7;
	@FXML
	private ImageView third_character8;
	@FXML
	private ImageView third_character9;
	@FXML
	private ImageView third_character10;
	@FXML
	private ImageView first_score1;
	@FXML
	private ImageView first_score2;
	@FXML
	private ImageView first_score3;
	@FXML
	private ImageView first_score4;
	@FXML
	private ImageView second_score1;
	@FXML
	private ImageView second_score2;
	@FXML
	private ImageView second_score3;
	@FXML
	private ImageView second_score4;
	@FXML
	private ImageView third_score1;
	@FXML
	private ImageView third_score2;
	@FXML
	private ImageView third_score3;
	@FXML
	private ImageView third_score4;

	
	
	
	public LeaderboardController() {
		
	}
	
	
	@FXML
	private void initialize() {
		
		
		background.setImage(new Image("assets/Elements-Leaderboard/background-leaderboard.png"));
		ScoreHandler scoreHandler = new ScoreHandler();
		String names[] = scoreHandler.getNames();
		names[0] = names[0].toLowerCase();
		names[1] = names[1].toLowerCase();
		names[2] = names[2].toLowerCase();
		int scores[] = scoreHandler.getHighScores();
		String maps[] = scoreHandler.getMaps();
		
		/* Set up #1 */
		if (names[0].length() >= 1) {
			first_character1.setImage(new Image(getCharacter(names[0].charAt(0))));
			if (names[0].length() >=2) {
				first_character2.setImage(new Image(getCharacter(names[0].charAt(1))));
				if (names[0].length() >=3) {
					first_character3.setImage(new Image(getCharacter(names[0].charAt(2))));
					if (names[0].length() >=4) {
						first_character4.setImage(new Image(getCharacter(names[0].charAt(3))));
						if (names[0].length() >=5) {
							first_character5.setImage(new Image(getCharacter(names[0].charAt(4))));
							if (names[0].length() >=6) {
								first_character6.setImage(new Image(getCharacter(names[0].charAt(5))));
								if (names[0].length() >=7) {
									first_character7.setImage(new Image(getCharacter(names[0].charAt(6))));
									if (names[0].length() >=8) {
										first_character8.setImage(new Image(getCharacter(names[0].charAt(7))));
										if (names[0].length() >=9) {
											first_character9.setImage(new Image(getCharacter(names[0].charAt(8))));
											if (names[0].length() >=10) {
												first_character10.setImage(new Image(getCharacter(names[0].charAt(9))));
											}
										}
									}
								}
							}
						}
						
					}
				}
			}
		}
		String firstScoreString = Integer.toString(scores[0]);
		if (scores[0] >=0) {
			first_score1.setImage(new Image(getCharacter(firstScoreString.charAt(0))));
			if (scores[0] >= 10) {
				first_score2.setImage(new Image(getCharacter(firstScoreString.charAt(1))));
				if (scores[0] >= 100) {
					first_score3.setImage(new Image(getCharacter(firstScoreString.charAt(2))));
					if (scores[0] >= 100) {
						first_score4.setImage(new Image(getCharacter(firstScoreString.charAt(3))));
					}
				}
				
			}
		}
			
		
		
		/* Set up #2 */
		if (names[1].length() >= 1) {
			second_character1.setImage(new Image(getCharacter(names[1].charAt(0))));
			if (names[1].length() >=2) {
				second_character2.setImage(new Image(getCharacter(names[1].charAt(1))));
				if (names[1].length() >=3) {
					second_character3.setImage(new Image(getCharacter(names[1].charAt(2))));
					if (names[1].length() >=4) {
						second_character4.setImage(new Image(getCharacter(names[1].charAt(3))));
						if (names[1].length() >=5) {
							second_character5.setImage(new Image(getCharacter(names[1].charAt(4))));
							if (names[1].length() >=6) {
								second_character6.setImage(new Image(getCharacter(names[1].charAt(5))));
								if (names[1].length() >=7) {
									second_character7.setImage(new Image(getCharacter(names[1].charAt(6))));
									if (names[1].length() >=8) {
										second_character8.setImage(new Image(getCharacter(names[1].charAt(7))));
										if (names[1].length() >=9) {
											second_character9.setImage(new Image(getCharacter(names[1].charAt(8))));
											if (names[1].length() >=10) {
												second_character10.setImage(new Image(getCharacter(names[1].charAt(9))));
											}
										}
									}
								}
							}
						}
						
					}
				}
			}
		}
		
		String secondScoreString = Integer.toString(scores[0]);
		if (scores[1] >=0) {
			second_score1.setImage(new Image(getCharacter(secondScoreString.charAt(0))));
			if (scores[1] >= 10) {
				second_score2.setImage(new Image(getCharacter(secondScoreString.charAt(1))));
				if (scores[1] >= 100) {
					second_score3.setImage(new Image(getCharacter(secondScoreString.charAt(2))));
					if (scores[1] >= 100) {
						second_score4.setImage(new Image(getCharacter(secondScoreString.charAt(3))));
					}
				}
				
			}
		}
		
		/* Set up #3 */
		if (names[2].length() >= 1) {
			third_character1.setImage(new Image(getCharacter(names[2].charAt(0))));
			if (names[2].length() >=2) {
				third_character2.setImage(new Image(getCharacter(names[2].charAt(1))));
				if (names[2].length() >=3) {
					third_character3.setImage(new Image(getCharacter(names[2].charAt(2))));
					if (names[2].length() >=4) {
						third_character4.setImage(new Image(getCharacter(names[2].charAt(3))));
						if (names[2].length() >=5) {
							third_character5.setImage(new Image(getCharacter(names[2].charAt(4))));
							if (names[2].length() >=6) {
								third_character6.setImage(new Image(getCharacter(names[2].charAt(5))));
								if (names[2].length() >=7) {
									third_character7.setImage(new Image(getCharacter(names[2].charAt(6))));
									if (names[2].length() >=8) {
										third_character8.setImage(new Image(getCharacter(names[2].charAt(7))));
										if (names[2].length() >=9) {
											third_character9.setImage(new Image(getCharacter(names[2].charAt(8))));
											if (names[2].length() >=10) {
												third_character10.setImage(new Image(getCharacter(names[2].charAt(9))));
											}
										}
									}
								}
							}
						}
						
					}
				}
			}
		}
		
		String thirdScoreString = Integer.toString(scores[0]);
		if (scores[2] >=0) {
			third_score1.setImage(new Image(getCharacter(thirdScoreString.charAt(0))));
			if (scores[2] >= 10) {
				third_score2.setImage(new Image(getCharacter(thirdScoreString.charAt(1))));
				if (scores[2] >= 100) {
					third_score3.setImage(new Image(getCharacter(thirdScoreString.charAt(2))));
					if (scores[2] >= 100) {
						third_score4.setImage(new Image(getCharacter(thirdScoreString.charAt(3))));
					}
				}
				
			}
		}
		
		
	
	}
	
	
	public void addKeyListener() {
		
		mainApp.getScene().setOnKeyPressed(new EventHandler<KeyEvent> (){
	    	@Override
	    	public void handle(KeyEvent event) {
	    		
	    		if (event.getCode() == KeyCode.ESCAPE) {
	    			mainApp.showWelcomeScreen();
	    		}
	    	}
	    });
	}
	
	
	/* Helper function */
	private String getCharacter(char character) {
		
		switch (character) {
			case 'a' :
				return "assets/letters/a.png";
			case 'b' :
				return "assets/letters/b.png";
			case 'c' :
				return "assets/letters/c.png";
			case 'd' :
				return "assets/letters/d.png";
			case 'e' :
				return "assets/letters/e.png";
			case 'f' :
				return "assets/letters/f.png";
			case 'g' :
				return "assets/letters/g.png";
			case 'h' :
				return "assets/letters/h.png";
			case 'i' :
				return "assets/letters/i.png";
			case 'j' :
				return "assets/letters/j.png";
			case 'k' :
				return "assets/letters/k.png";
			case 'l' :
				return "assets/letters/l.png";
			case 'm' :
				return "assets/letters/m.png";
			case 'n' :
				return "assets/letters/n.png";
			case 'o' :
				return "assets/letters/o.png";
			case 'p' :
				return "assets/letters/p.png";
			case 'q' :
				return "assets/letters/q.png";
			case 'r' :
				return "assets/letters/r.png";
			case 's' :
				return "assets/letters/s.png";
			case 't' :
				return "assets/letters/t.png";
			case 'u' :
				return "assets/letters/u.png";
			case 'v' :
				return "assets/letters/v.png";
			case 'w' :
				return "assets/letters/w.png";
			case 'x' :
				return "assets/letters/x.png";
			case 'y' :
				return "assets/letters/y.png";
			case 'z' :
				return "assets/letters/z.png";
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
				return "assets/misc/empty.png";
		}
	}
	
	
	public void setMainApp(MainApp mainApp) {
		
		this.mainApp = mainApp;
	}
}