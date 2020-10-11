package br.com.softblue.snake.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import br.com.softblue.snake.core.Direction;

public class Shape extends Drawable {

	private List<Rect> rects;
	
	public Shape(Color color) {
		super.setColor(color);
		rects = new ArrayList<Rect>();
	}
	
	public List<Rect> getRects() {
		return rects;
	}
	
	public Rect getFirstRect() {
		return rects.get(0);
	}
	
	public Rect getLastRect() {
		return rects.get(rects.size() - 1);
	}
	
	public void addRect(Rect rect) {
		rect.setColor(this.getColor());
		this.rects.add(rect);
	}
	
	public static Rect duplicate(Rect baseRect, Direction direction) {
		int baseX = (int) baseRect.getLocation().getX();
		int baseY = (int) baseRect.getLocation().getY();
		int baseWidth = (int) baseRect.getDimension().getWidth();
		int baseHeight = (int) baseRect.getDimension().getHeight();
		return new Rect(baseX + direction.getSgnX() *  baseWidth, baseY + direction.getSgnY() * baseHeight, baseWidth, baseHeight);
	}
	
	public boolean intersection(Rect other) {
		for (Rect rect : rects) {
			if(!rect.equals(other) && rect.intersects(other)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void draw(Graphics g) {
		for (Rect rect : rects) {
			rect.draw(g);
		}
	}
}
