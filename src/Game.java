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
	public static int WIDTH = 1200; //������
	public static int HEIGHT = 600; //������
	public static String NAME = "TUTORIAL 1"; //��������� ����
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
			try {
				Thread.sleep(7);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    accumulator += delta;
			while(accumulator > 1000/fps){
			    update(fps);
		        accumulator = 0;
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
			createBufferStrategy(2); //������� BufferStrategy ��� ������ ������
			requestFocus();
			return;
		}
			
		java.awt.Graphics g = bs.getDrawGraphics(); //�������� Graphics �� ��������� ���� BufferStrategy
		g.setColor(Color.cyan); //������� ����
		g.fillRect(0, 0, getWidth(), getHeight()); //��������� �������������
		hero.draw(g, x, y);
		g.dispose();
		bs.show(); //��������
	}
		
	public void update(long fps) {
		if (leftPressed == true) {
			count_x_left++;
			if(count_x_left == fps / 60) {
				x-=2;
				count_x_left = 0;
			}
		}

		if (rightPressed == true) {
			count_x_right++;
			if(count_x_right == fps / 60) {
				x+=2;
				count_x_right = 0;
			}
		}
		
		if (upPressed == true) {
			count_y_up++;
			if(count_y_up == fps / 60) {
				y-=2;
				count_y_up = 0;
			}
		}
		
		if (downPressed == true) {
			count_y_down++;
			if(count_y_down == fps / 60) {
				y+=2;
				count_y_down = 0;
			}
		}
		render();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		JFrame frame = new JFrame(Game.NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //����� �� ���������� �� ������� ������� ESC
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER); //��������� ����� �� ��� �����
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

		public void keyPressed(KeyEvent e) { //������� ������
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
		public void keyReleased(KeyEvent e) { //������� ��������
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
