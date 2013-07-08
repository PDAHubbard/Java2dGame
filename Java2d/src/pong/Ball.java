package pong;

import java.util.Random;

import javax.swing.ImageIcon;

public class Ball extends Actor {

	private String ball = "ball.png";
	
	public Ball(int boundx, int boundy){
		this.boundx=boundx-5;
		this.boundy=boundy-15;
		Random r = new Random();
		x = r.nextInt(boundx);
		y = r.nextInt(boundy);
		dx = r.nextInt(9) - 4;
		dy = r.nextInt(9) - 4;
		
		ImageIcon ii = new ImageIcon(this.getClass().getResource(ball));
		image = ii.getImage();
		
		width=5;
		height=5;
	}
	
	public void move(){
		if (x<=0 || x>=boundx) dx=-dx;
		if (y<=0 || y>=boundy) dy=-dy;
		x+=dx;
		y+=dy;		
	}
	
	public void hit(){
		dx=-dx;
		//dy=-dy;
	}
}
