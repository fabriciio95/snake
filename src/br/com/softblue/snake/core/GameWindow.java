package br.com.softblue.snake.core;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import br.com.sofblue.snake.util.Constants;
import br.com.softblue.snake.graphics.Renderer;
import br.com.softblue.snake.scene.Snake;

public class GameWindow extends JFrame implements KeyListener{//JFrame é uma janela
	private static final long serialVersionUID = 1L;
	
	private Renderer renderer;
	private Snake snake;
	private Image buffer;
	private Graphics gImage;
	private Rectangle drawingArea;
	private long lastKeyboardEventTime;
	private int moves;
	
	public GameWindow(Snake snake) {
		this.snake = snake;
		this.moves += 1;
		setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		setResizable(false);
		setTitle(Constants.WINDOW_TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);//Centralizando a janela no meio da tela, pois esse método centraliza a janela a partir do componente passado como parametro, quando é passado null ele apenas centraliza a janela na tela
		addKeyListener(this);//Falando para a JVM que o objeto dessa classe irá ouvir e tratar os eventos
		setVisible(true);
		buffer = createImage(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		gImage = buffer.getGraphics();
		renderer = new Renderer(gImage);
		defineDrawingArea();
	}

	public Renderer getRenderer() {
		return renderer;
	}
	
	@Override
		public void paint(Graphics gScreen) {//JVM que chama na hora que for desenhar as coisas na tela, pois esta classe extende de JFrame(que é uma janela) e na hora de renderizar esta janela esse método é chamdo
			if(gImage == null || renderer == null) {
				return;
			}
			renderer.render();
			gScreen.drawImage(buffer, 0, 0, null);
		}

	private void defineDrawingArea() {
		int upperY = (int) (Constants.WINDOW_HEIGHT - getContentPane().getSize().getHeight());
		drawingArea = new Rectangle(0, upperY, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT - upperY);
	}
	
	public Rectangle getDrawingArea() {
		return drawingArea;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		long now = System.currentTimeMillis(); // retorna a hora atual do sistema em millisegundos
		
		if(now - lastKeyboardEventTime < Constants.GAME_MIN_TIME_BETWEEN_KEYBOARD_EVENTS) {
			return;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_W) {
			snake.up();
			moves++;
		} else if(e.getKeyCode() == KeyEvent.VK_D) {
			snake.right();
			moves++;
		} else if(moves != 1 && e.getKeyCode() == KeyEvent.VK_A) {
			snake.left();
		} else if(e.getKeyCode() == KeyEvent.VK_S) {
			snake.down();
			moves++;
		} else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		} 
		
		lastKeyboardEventTime = now;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
