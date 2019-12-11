package basics;

import java.awt.Color;
import java.awt.Graphics;

public class Root extends GameObject{
	
	public Root(int x, int y, ID id) {
		super(x, y, id);
	}
	
	@Override
	public void tick() {
	}
	
	@Override
	public void render(Graphics allensdumb) {
		//allensdumb.setColor(Color.BLACK);
		//allensdumb.fillOval(x, y, 8, 8);
	}
}
