package basics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	private Handler handler;
	public KeyInput(Handler handler) {
		
		this.handler = handler;
	}
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		GameObject tempObject = handler.object.get(0);
		if(key==KeyEvent.VK_W)tempObject.setVelY(-5);
		if(key==KeyEvent.VK_A)tempObject.setVelX(-5);
		if(key==KeyEvent.VK_S)tempObject.setVelY(5);
		if(key==KeyEvent.VK_D)tempObject.setVelX(5);
		if(key==KeyEvent.VK_UP)tempObject.setVelY(-5);
		if(key==KeyEvent.VK_LEFT)tempObject.setVelX(-5);
		if(key==KeyEvent.VK_DOWN)tempObject.setVelY(5);
		if(key==KeyEvent.VK_RIGHT)tempObject.setVelX(5);
		if(key==KeyEvent.VK_SPACE)tempObject.setSpace();
	}
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		GameObject tempObject = handler.object.get(0);
		if(key==KeyEvent.VK_W)tempObject.setVelY(0);
		if(key==KeyEvent.VK_A)tempObject.setVelX(0);
		if(key==KeyEvent.VK_S)tempObject.setVelY(0);
		if(key==KeyEvent.VK_D)tempObject.setVelX(0);
		if(key==KeyEvent.VK_UP)tempObject.setVelY(0);
		if(key==KeyEvent.VK_LEFT)tempObject.setVelX(0);
		if(key==KeyEvent.VK_DOWN)tempObject.setVelY(0);
		if(key==KeyEvent.VK_RIGHT)tempObject.setVelX(0);		
	}
}
