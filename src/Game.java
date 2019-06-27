import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private boolean running;
	public static int WIDTH = 1280; //������
	public static int HEIGHT = 720; //������
	public static String NAME = "TEST_WINDOW"; //��������� ����

	
	long count_x_left = 0;
	long count_x_right = 0;
	long count_y_up = 0;
	long count_y_down = 0;
	public boolean leftPressed = false;
	public boolean rightPressed = false;
	public boolean upPressed = false;
	public boolean downPressed = false;
	public boolean shiftPressed = false;
	public boolean spacePressed = false;
	
	
	
	static int fps = 75;
	
	public static Hero hero = new Hero(0, 0, 0, 0, 0, 0, 0, "bullet.png");
	public static Entity[] field = new Entity[0];

	@Override
	public void run() {
		long lastTime = System.currentTimeMillis();
		long delta;
		double accumulator = 0;
		
		init();
			
		while(running) {
			delta = System.currentTimeMillis() - lastTime;
			lastTime = System.currentTimeMillis();
		    accumulator += delta;
			while(accumulator > 1000/fps){
		        update();
		        accumulator -= 1000/fps;
		        if(accumulator < 0) accumulator = 0;
		    }
		}
	}
		
	public void init() {
		addKeyListener(new KeyInputHandler());
	}
	
	public class KeyInputHandler extends KeyAdapter {
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
			if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
				shiftPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				spacePressed = true;
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
			if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
				shiftPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				spacePressed = false;
			}
		}
	}	

	public void render() {
		BufferStrategy bs = getBufferStrategy(); 
		if (bs == null) {
			createBufferStrategy(2); //������� BufferStrategy ��� ������ ������
			requestFocus();
			return;
		}
			
		java.awt.Graphics g = bs.getDrawGraphics(); //�������� Graphics �� ��������� ���� BufferStrategy
		g.setColor(Color.green); //������� ����
		g.fillRect(0, 0, getWidth(), getHeight()); //��������� �������������
		hero.draw(g);
		g.dispose();
		bs.show(); //��������
	}
		
	public void update() {
		hero.update(leftPressed, rightPressed, downPressed, upPressed, shiftPressed, spacePressed);
		for(int i = 0; i < field.length; i++) {
			
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
}
