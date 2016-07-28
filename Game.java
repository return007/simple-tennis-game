import java.awt.*;
import java.applet.*;

import javax.swing.JPanel;
import javax.swing.JFrame;

public class Game extends JPanel{
	
	int x, y;
	Game(){
		x = 0;		//These are the inital positions of the ball that we see
		y = 0;
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D obj = (Graphics2D) g;
		//obj.setColor(Color.BLUE);
		obj.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		obj.fillOval(x,y,30,30);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Mini Game");
		Game game = new Game();
		frame.add(game);
		frame.setSize(400, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//so that the program exits/terminates on closing
		
		while(game.x < 400 && game.y < 400){
			game.x++;
			game.y++;
			game.repaint();
			try{
				Thread.sleep(10);
			}
			catch(InterruptedException ie){
				System.out.println(ie);
			}
		}
	}
}
