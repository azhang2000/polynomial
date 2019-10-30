package basics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player extends GameObject {
	Random r = new Random();
	public Player(int x, int y, ID id) {
		super(x, y, id);

	}

	@Override
	public void tick() {

		x+=velx;
		y+=vely;
		if(x>800 || y>800 ||x<0 || y<0) {
			x=0;
			y=0;
		}
	}

	@Override
	public void render(Graphics g) {
		Random rand = new Random();
		float red = rand.nextFloat();
		float green = rand.nextFloat();
		float blu = rand.nextFloat();
		Color random = new Color(red,green,blu);
		g.setColor(random);
		g.fillRect(x, y, 10, 10);
	}

	
}
