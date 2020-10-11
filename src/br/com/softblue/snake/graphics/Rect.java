package br.com.softblue.snake.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Rect extends Drawable {

	private Rectangle rectangle;
	
	public Rect(Point location,Dimension dimension) {
		this.rectangle = new Rectangle(location, dimension);
	}
	
	public Rect(int x, int y, int width, int height) {
		this.rectangle = new Rectangle(x ,y , width, height);
	}
	
	public Rect() {
		this.rectangle = new Rectangle(0, 0);
	}
	
	public Point getLocation() {
		return rectangle.getLocation();
	}
	
	public void setLocation(Point location) {
		this.rectangle.setLocation(location);
	}
	
	public Dimension getDimension() {
		return rectangle.getSize();
	}
	
	public void setDimension(Dimension dimension) {
		this.rectangle.setSize(dimension);
	}
	
	public boolean intersects(Rect other) {
		return rectangle.intersects(other.rectangle);
	}

	@Override
	public void draw(Graphics g) {
		g.fillRect((int)this.rectangle.getLocation().getX(), (int) this.rectangle.getLocation().getY(), (int) this.rectangle.getSize().width, (int) this.rectangle.getSize().height);
	}
}
