package br.com.softblue.snake.graphics;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Renderer {

	private List<Drawable> drawables;
	private Graphics gImage;
	
	public Renderer(Graphics gImage) {
		this.drawables = new ArrayList<Drawable>();
		this.gImage = gImage;
	}
	
	public synchronized void render() {
		for (Drawable drawable : drawables) {
			gImage.setColor(drawable.getColor());
			drawable.draw(gImage);
		}
	}
	
	public synchronized void add(Drawable d) {
		this.drawables.add(d);
	}
	
	public synchronized void remove(Drawable d) {
		this.drawables.remove(d);
	}
}
