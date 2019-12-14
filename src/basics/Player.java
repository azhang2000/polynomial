package basics;

import java.awt.Color;

import java.awt.Graphics;
import java.io.File;
import java.net.URL;
import java.util.Random;

public class Player extends GameObject {
	Random r = new Random();
	private VectorField v;
	private double [] xFixed, yFixed, scalar;
	private int deaths = 0;
	private Sound death;
	private boolean start = false;
	public Player(int x, int y, ID id, VectorField v, double [] xFixed, double [] yFixed, double [] scalar) {
		super(x, y, id);
		this.v = v;
		this.xFixed = xFixed;
		this.yFixed = yFixed;
		this.scalar = scalar;
		URL urldeath = Game.class.getResource("/maps/death.wav");
		File file = new File(urldeath.getFile());
		death = new Sound(file.getPath());
		
	}

	public void updateMap(int x, int y, VectorField v, double [] xFixed, double [] yFixed, double [] scalar) {
		this.x = x;
		this.y = y;
		this.v = v;
		this.xFixed = xFixed;
		this.yFixed = yFixed;
		this.scalar = scalar;
		deaths = 0;
	}
	
	public int getDeaths() {
		return deaths;
	}
	
	
	
	public void startPlayer() {
		start = true;
	}
	
	public void stopPlayer() {
		start = false;
	}
	
	@Override
	public void tick() {
		/*if(x>=800 || y>=800 ||x<0 || y<0) {
			x=0;
			y=0;
		}*/
		if (start) {
			for (int i = 0 ; i < xFixed.length; i++) {
				double distance = Math.sqrt(Math.pow(x - xFixed[i], 2) + Math.pow(y-yFixed[i], 2));
				if (distance < 10) {
					if (scalar[i]>0) {
						death.play();
						x = 0;
						y = 0;
						deaths++;
						break;
					}
				}
			}
			if(x > 790) {
				x = 790;
			}
			else if(x < 0) {
				x = 0;
			}
			if(y > 780) {
				y = 780;
			}
			else if(y < 0) {
				y = 0;
			}
			x+=v.getXVector(x, y) + velx;
			y+=v.getYVector(x, y) + vely;
		}
		//System.out.println(v.getXVector(x,y) + velx);
		//System.out.println(v.getYVector(x,y) + vely);


	}

	@Override
	public void render(Graphics g) {
		Random rand = new Random();
		float red = rand.nextFloat();
		float green = rand.nextFloat();
		float blu = rand.nextFloat();
		Color random = new Color(red,green,blu);
		g.setColor(Color.MAGENTA);
		g.fillOval(x, y, 10, 10);
	}

	
}
