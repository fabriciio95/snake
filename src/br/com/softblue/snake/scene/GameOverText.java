package br.com.softblue.snake.scene;

import static br.com.sofblue.snake.util.Constants.GAME_OVER_COLOR;
import static br.com.sofblue.snake.util.Constants.GAME_OVER_LOCATION;
import static br.com.sofblue.snake.util.Constants.GAME_OVER_TEXT;

import br.com.softblue.snake.graphics.Text;

public class GameOverText extends Text{

	public GameOverText(int score) {
		super(GAME_OVER_COLOR, String.format(GAME_OVER_TEXT, score), GAME_OVER_LOCATION);
	}

}
