package br.com.softblue.snake.core;

import java.awt.Rectangle;

import br.com.sofblue.snake.util.Constants;
import br.com.sofblue.snake.util.GameUtils;
import br.com.softblue.snake.graphics.Rect;
import br.com.softblue.snake.scene.Background;
import br.com.softblue.snake.scene.Food;
import br.com.softblue.snake.scene.GameOverText;
import br.com.softblue.snake.scene.PlayAgainText;
import br.com.softblue.snake.scene.Snake;

public class Game implements Runnable{
	
	private GameWindow gameWindow;
	private Snake snake;
	private Food food;


	public void start() {
		snake = new Snake();
		gameWindow = new GameWindow(snake);
		food = new Food(snake, gameWindow.getDrawingArea());
		addElementsToScreen();
		new Thread(this).start();
		
	}
	
	private void addElementsToScreen() {
		gameWindow.getRenderer().add(new Background());
		gameWindow.getRenderer().add(snake);
		gameWindow.getRenderer().add(food);
	}
	
	@Override
	public void run() {
		
		do {
			gameWindow.repaint();
			snake.move();
			food.checkIfEaten(snake, gameWindow.getDrawingArea());
			GameUtils.sleep(Constants.SLEEP_TIME);
		} while(!isGameOver());
		
		processGameOver();
		processNewGame();
		
	}
	
	private boolean isGameOver() {
		return snake.collideWithItSelf() || isSnakeHitBounds();
	}
	
	private void processGameOver() {
		gameWindow.getRenderer().remove(snake);
		gameWindow.getRenderer().remove(food);
		gameWindow.getRenderer().add(new GameOverText(food.getEatenTimes()));
		gameWindow.repaint();
	}
	
	private void processNewGame() {
		PlayAgainText text = new PlayAgainText();
		gameWindow.getRenderer().add(text);
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		start();
	}
	
	private boolean isSnakeHitBounds() {
		Rect head = snake.getFirstRect();
		Rectangle drawingArea = gameWindow.getDrawingArea();
		
		int headX = (int) head.getLocation().getX();
		int headY = (int) head.getLocation().getY();
		
		int areaX1 = (int) drawingArea.getMinX();
		int areaY1 = (int) drawingArea.getMinY() - Constants.SNAKE_PIECE_SIZE * 2;
		
		int areaX2 = (int) drawingArea.getMaxX();
		int areaY2 = (int) drawingArea.getMaxY();
		
		if(headX <= areaX1 || headX + Constants.SNAKE_PIECE_SIZE >= areaX2) {
			return true;
		}
		
		if(headY <= areaY1 || headY + Constants.SNAKE_PIECE_SIZE >= areaY2) {
			return true;
		}
		
		return false;
	}
}
