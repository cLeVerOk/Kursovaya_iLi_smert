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
	
	public static Sprite hero;
	private static int x = 0;
	private static int y = 0;

	@Override
	public void run() {
		long lastTime = System.currentTimeMillis();
		long delta;
		
		init();
			
		while(running) {
			delta = System.currentTimeMillis() - lastTime;
			lastTime = System.currentTimeMillis();	
			update(delta);
			render();
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
		
	public void update(long delta) {
		if (leftPressed == true) {
			x--;
		}

		if (rightPressed == true) {
			x++;
		}
		
		if (upPressed == true) {
			y--;
		}
		
		if (downPressed == true) {
			y++;
		}
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
