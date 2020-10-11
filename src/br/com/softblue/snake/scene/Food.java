package br.com.softblue.snake.scene;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import br.com.sofblue.snake.util.Constants;
import br.com.sofblue.snake.util.GameUtils;
import br.com.softblue.snake.graphics.Rect;

public class Food extends Rect {
	
	private int eatenTimes;

	public Food(Snake snake, Rectangle drawingArea) {
		setColor(Constants.FOOD_COLOR);
		setDimension(new Dimension(Constants.FOOD_PIECE_SIZE, Constants.FOOD_PIECE_SIZE));
		setRandomLocation(snake, drawingArea);
		
	}
	
	public void setRandomLocation(Snake snake, Rectangle drawingArea) {
		int offset = 6; // Para a comida ficar 3 pixels fora da borda, para não correr o risco da comida ficar super colada na borda
		
		do {
			int minX = (int) drawingArea.getMinX() + offset;
			int minY = (int) drawingArea.getMinY() + offset;
			
			int maxX = (int) drawingArea.getMaxX() - Constants.FOOD_PIECE_SIZE - offset;
			int maxY = (int) drawingArea.getMaxY() - Constants.FOOD_PIECE_SIZE - offset;
			
			int randomX = GameUtils.random(minX, maxX);
			int randomY = GameUtils.random(minY, maxY);
			
			setLocation(new Point(randomX, randomY));
		} while(snake.intersection(this));
	}
	
	public int getEatenTimes() {
		return eatenTimes;
	}
	
	
	public void checkIfEaten(Snake snake, Rectangle drawingArea) {
		if(snake.intersection(this)) {
			eatenTimes++;
			setRandomLocation(snake, drawingArea);
			snake.elongate();
		}
	}
}