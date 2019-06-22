import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	
	private boolean running;
	public static int WIDTH = 1200; //ширина
	public static int HEIGHT = 600; //высота
	public static String NAME = "TUTORIAL 1"; //заголовок окна
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	public boolean downPressed = false;
	private boolean upPressed = false;
	public boolean shiftPressed = false;
	
	long count_x_left = 0;
	long count_x_right = 0;
	long count_y_up = 0;
	long count_y_down = 0;
	
	int accumulator = 0;
	int multiplier = 1;
	
	static int fps = 120;
	
	public static Sprite hero;
	private static int x = 0;
	private static int y = 0;

	public void run() {
		long lastTime = System.currentTimeMillis();
		long delta;
		
		init();
			
		while(running) {
			delta = System.currentTimeMillis() - lastTime;
			lastTime = System.currentTimeMillis();	
		    accumulator += delta + 1000/fps - 5;
		    update(120);
		    
			while(accumulator > 1000/fps){
				render();
		        accumulator -= 1000/fps;
		        if(accumulator < 0) accumulator = 0;
		    }
		}
	}
		
	public void init() {
		addKeyListener(new KeyInputHandler());
		hero = getSprite("man.png");
	}
	
		
	private Sprite getSprite(String path) {

		BufferedImage sourceImage = null;
		try {
			java.net.URL url = this.getClass().getClassLoader().getResource(path);
			sourceImage = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sprite sprite = new Sprite(Toolkit.getDefaultToolkit().createImage(sourceImage.getSource()));
		return sprite;
	}
	

	public void render() {
		BufferStrategy bs = getBufferStrategy(); 
		if (bs == null) {
			createBufferStrategy(2); //создаем BufferStrategy дл€ нашего холста
			requestFocus();
			return;
		}
			
		java.awt.Graphics g = bs.getDrawGraphics(); //получаем Graphics из созданной нами BufferStrategy
		g.setColor(Color.cyan); //выбрать цвет
		g.fillRect(0, 0, getWidth(), getHeight()); //заполнить пр€моугольник
		hero.draw(g, x, y);
		g.dispose();
		bs.show(); //показать
	}
		
	public void update(long fps) {
		if (leftPressed == true) {
			count_x_left++;
			if(count_x_left == fps / 15) {
				x--;
				count_x_left = 0;
			}
		}

		if (rightPressed == true) {
			count_x_right++;
			if(count_x_right == fps / 15) {
				x++;
				count_x_right = 0;
			}
		}
		
		if (upPressed == true) {
			count_y_up++;
			if(count_y_up == fps / 15) {
				y--;
				count_y_up = 0;
			}
		}
		
		if (downPressed == true) {
			count_y_down++;
			if(count_y_down == fps / 15) {
				y++;
				count_y_down = 0;
			}
		}
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		JFrame frame = new JFrame(Game.NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //выход из приложени€ по нажатию клавиши ESC
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER); //добавл€ем холст на наш фрейм
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		
		
		
		game.start();
	}
	
	public void start() {
		running = true;
		new Thread(this).start();
	}
	
	private class KeyInputHandler extends KeyAdapter {

		public void keyPressed(KeyEvent e) { //клавиша нажата
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				leftPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				rightPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				upPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				downPressed = true;
			}
		} 	
		public void keyReleased(KeyEvent e) { //клавиша отпущена
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				leftPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				rightPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				upPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				downPressed = false;
			}
		}
	}

}
