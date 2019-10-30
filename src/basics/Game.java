package basics;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Game extends Canvas implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH= 800, HEIGHT = 800;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private Random r;
	public Game() {
		
	
	new Window(WIDTH, HEIGHT, "Let's Build a Game!",this);	
	handler = new Handler();
	this.addKeyListener(new KeyInput(handler));
	handler.addObject(new Player(100,100,ID.Player));
	r = new Random();
	/*for(int i=0; i<0;i++) {
		handler.addObject(new Player(r.nextInt(50),r.nextInt(50),ID.Player));
	}*/
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	public synchronized void stop() {
		try {
				thread.join();
				running = false;
				
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta+=(now - lastTime)/ns;
			lastTime = now;
			while(delta>=1)
			{
				tick();
				delta--;
			}
			if(running) {
				try {
					render();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
			frames++;
			if(System.currentTimeMillis()-timer>1000)
			{
				timer +=1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	private void tick() {
		handler.tick();
		
	}
	private void render() throws IOException {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Image img = ImageIO.read(Game.class.getResource("/maps/background.png"));
		//Image img = ImageIO.read(Game.class.getResource("background.png"));
		g.drawImage(img, 0, 0, HEIGHT, WIDTH, null);
		g.setColor(Color.BLACK);
		handler.render(g);
		g.dispose();
		bs.show();
		
	}
	
	public static void main(String [] args) {
		
		new Game();
		
		
	}
	
}

