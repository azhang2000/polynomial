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
	

	static double [] xcoord = {-2.67, -0.465, -0.78, 2.01, 2.9101, 1.02, 1.185, -2.595, 2.9251, -1.3951, -2.46, -1.3182, 1.3144, .9594, -1.9685, 2.5963, -0.5787, 0.4694, -2.3237, 2.5064, -1.9923};
	static double [] ycoord = {2.85,-0.045,2.955,.99,2.9099, 2.1,-2.2052,.915,-0.6451,-2.205,-1.335,-1.7015,1.2458,-1.7301, .7956,2.6313,2.374,1.0001,2.5362,-0.4133,-1.0963};
	
    
	
	static double [] scalar = {10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};
	
	public static VectorField joe = new VectorField(xcoord,ycoord,scalar);
	
	public Game() throws IOException {
		
	
	new Window(WIDTH, HEIGHT, "Let's Build a Game!",this);	
	handler = new Handler();
	this.addKeyListener(new KeyInput(handler));
	handler.addObject(new Player(100,100,ID.Player));
	
	for(int i =0;i<xcoord.length;i++) {
		handler.addObject(new Root((int)xcoord[i],(int)ycoord[i],ID.Root));
    	
    }
	//r = new Random();
	
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
				//System.out.println("FPS: " + frames);
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
	
	public static void main(String [] args) throws IOException {
		
		new Game();
		
		
	}
	
}

