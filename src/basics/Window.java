package basics;
import java.awt.Canvas;
import javax.swing.JFrame;
import java.awt.Dimension;

public class Window extends Canvas {

	private static final long serialVersionUID = 9141317500917205420L;
	
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);	
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
}
