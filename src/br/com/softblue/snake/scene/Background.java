package br.com.softblue.snake.scene;

import br.com.sofblue.snake.util.Constants;
import br.com.softblue.snake.graphics.Rect;

public class Background extends Rect {

	public Background() {
		super(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		setColor(Constants.BACKGROUND_COLOR);
	}	
}
