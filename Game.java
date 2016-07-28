import java.awt.*;
import java.applet.*;

import javax.swing.JPanel;
import javax.swing.JFrame;

public class Game extends JPanel{
	
	int x, y;
	int mx,my;
	Game(int x, int y){
		this.x = x;		//These are the inital positions of the ball that we see
		this.y = y;
		mx = my = 1;	//Movement along x and y directions (steps)
	}
	
	public void moveBall(){
		if(mx > 0){
			//Movement along SE or NE Direction
			if(x + mx < getWidth()-30){
				x += mx;
			}
			else{
				mx = -mx;
				x += mx;
			}
		}
		else{
			if(x + mx > 0){
				x += mx;
			}
			else{
				mx = -mx;
				x += mx;
			}
		}
		if(my > 0){
			//Movement along SE or SW direction
			if(y+my < getHeight()-30){
				y += my;
			}
			else{
				my = -my;
				y += my;
			}
		}
		else{
			if(y+my > 0){
				y += my;
			}
			else{
				my = -my;
				y += my;
			}
		}
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
		Game game = new Game((int)(Math.random()*1000)%(300-40), 0);
		frame.add(game);
		frame.setSize(300, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//so that the program exits/terminates on closing
		
		while(true){
			game.moveBall();
			game.repaint();
			try{
				Thread.sleep(6);
			}
			catch(InterruptedException ie){
				System.out.println(ie);
			}
		}
	}
}
