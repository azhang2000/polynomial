package basics;
import basics.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
	private boolean start = false;
	private Handler handler;
	private Random r;
	private int currentMap = 0;
	public VectorField joe = new VectorField(xcoords[currentMap],ycoords[currentMap],scalars[currentMap]);
	private int score = 0;
	Player user = new Player(100,100,ID.Player, joe, xcoords[currentMap], ycoords[currentMap], scalars[currentMap]);
	
	
	//static double [] xcoord = {-2.67, -0.465, -0.78, 2.01, 2.9101, 1.02, 1.185, -2.595, 2.9251, -1.3951, -2.46, -1.3182, 1.3144, .9594, -1.9685, 2.5963, -0.5787, 0.4694, -2.3237, 2.5064, -1.9923};
	//static double [] ycoord = {2.85,-0.045,2.955,.99,2.9099, 2.1,-2.2052,.915,-0.6451,-2.205,-1.335,-1.7015,1.2458,-1.7301, .7956,2.6313,2.374,1.0001,2.5362,-0.4133,-1.0963};
	//static double [] scalar = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
	
	static double [] [] xcoords = {{1,-.5,-.5},{-1.96,-.55,-.9,.585},{-2.42,-1.6,-0.68,0.09,0.8},{-2.6,-1.2,-0.46,0.63,-0.77,0.92}};
	static double [] [] ycoords = {{0, .866, -.866},{2.001,1.95,.63,-.1},{-0.39,0.4,1.22,1.98,2.66},{1.1,1.2,2.55,1.23,-0.77,-0.69}};
	static double [] [] scalars = {{200,200,200},{200,-200,200,200},{200,-200,200,-200,200},{-200,200,-200,200,-200,200}};
	
	
	public Game() throws IOException {
		
	
	new Window(WIDTH, HEIGHT, "Let's Build a Game!",this);	
	handler = new Handler();
	this.addKeyListener(new KeyInput(handler));
	
	handler.addObject(user);
	
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
		if(!start) {
			if(user.getSpace()) {
				start = true;
				user.startPlayer();
			}
		}
		if (user.getX()>= 750 && user.getY()>=750) {
			user.setX(0);
			user.setY(0);
			URL urlanotherone = Game.class.getResource("/maps/anotherone.wav");
			Sound anotherone = new Sound(urlanotherone.getFile());
			anotherone.play();
			currentMap++;
			score += 1000 - (10 * user.getDeaths());
			if (currentMap < xcoords.length) {
				joe = new VectorField(xcoords[currentMap],ycoords[currentMap],scalars[currentMap]);
				user.updateMap(0, 0, joe, xcoords[currentMap], ycoords[currentMap], scalars[currentMap]);

				for(int i =0;i<xcoords[currentMap].length;i++) {
					System.out.println(xcoords[currentMap][i] + " " + ycoords[currentMap][i]);
			    }
			}
		}
	}
	private void render() throws IOException {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}		
		Graphics g = bs.getDrawGraphics();
		Image img;
		
		if(!start)
			img = ImageIO.read(Game.class.getResource("/maps/start.PNG"));
		else
			img = ImageIO.read(Game.class.getResource("/maps/"+currentMap+".PNG"));
		//Image img = ImageIO.read(Game.class.getResource("background.png"));
		g.drawImage(img, 0, 0, HEIGHT, WIDTH, null);
		g.setColor(Color.BLACK);
		//System.out.println(currentMap);
		if(start) {
			if (currentMap != xcoords.length) {
				String t = ("Death Count: " + user.getDeaths());
				String s = ("Score: " + score);
				g.drawString(t, 10, 20);
				g.drawString(s,  10, 40);
				handler.render(g);
			}
			else {
				user.stopPlayer();
				String s = ("Score: " + score);
				g.setColor(Color.WHITE);
				g.drawString(s, 390, 500);
				
			}
		}
		g.dispose();
		bs.show();
		
		
		
	}

	
	public static void main(String [] args) throws IOException {
		URL urlMusic = Game.class.getResource("/maps/music.wav");
		Sound music = new Sound(urlMusic.getFile());
		music.play();
		music.loop();
		music.setVolume(0.2f);
		new Game();
		
		
	}
	
}

