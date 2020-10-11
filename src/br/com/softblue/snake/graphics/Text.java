package br.com.softblue.snake.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Text extends Drawable{
	
	private String text;
	private Point location;
	
	public Text(Color color, String text, Point location) {
		this.text = text;
		this.location = location;
		setColor(color);
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawString(text, (int) location.getX(), (int) location.getY());
	}

}
