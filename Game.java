import java.awt.*;
import java.applet.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Game extends JPanel{
	
	int x, y;
	int mx,my;
	int racquetX,racquetY;
	int speed;
	int score;
	
	Game(int x, int y){
		this.x = x;		//These are the inital positions of the ball that we see
		this.y = y;
		mx = my = 1;	//Movement along x and y directions (steps)
		racquetX = 0;
		racquetY = 0;
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
				stopRacquetInit(e);
			}
			public void keyPressed(KeyEvent e) {
				moveRacquetInit(e);
			}
		});
		setFocusable(true);
		speed = 0;
		score = 0;
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
			if(y+my <= getHeight()-30){
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
	
	public void stopRacquetInit(KeyEvent e){
		speed = 0;
	}
	
	public void moveRacquetInit(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			speed = -1;
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			speed = 1;
		}
	}
	
	public void moveRacquet(){
		if(speed > 0){
			if(racquetX + speed + 60 <= getWidth()){
				racquetX += speed;
			}
		}
		else if(speed < 0){
			if(racquetX + speed >= 0){
				racquetX += speed;
			}
		}
		if(y+30 == racquetY){
			if(x+20 >= racquetX && x <= racquetX+60){
				//Now the ball has touched the racquet, and hence must rebound from here
				my = -my;
				score++;
			}
		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D obj = (Graphics2D) g;
		//obj.setColor(Color.BLUE);
		obj.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		obj.fillOval(x,y,30,30);
		obj.fillRect(racquetX,racquetY,60,15);
	}
	
	boolean isOver(){
		boolean status = false;
		if(y+30 == getHeight())
			status = true;
		return status;
	}
	
	void finish(){
		JOptionPane.showMessageDialog(this,"Game Over!!!\nScore : "+score, "Game Over", JOptionPane.YES_NO_OPTION);
		System.exit(ABORT);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Mini Game");
		Game game = new Game((int)(Math.random()*1000)%(300-40), 0);
		frame.add(game);
		frame.setSize(300, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//so that the program exits/terminates on closing
		game.racquetY = 400-15;
		while(true){
			game.moveBall();
			game.moveRacquet();
			game.repaint();
			if(game.isOver()){
				break;
			}
			try{
				Thread.sleep(5);
			}
			catch(InterruptedException ie){
				System.out.println(ie);
			}
		}
		game.finish();
		System.out.println("Game Over!!!");
	}
}
