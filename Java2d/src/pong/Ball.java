package pong;

import java.util.Random;

import javax.swing.ImageIcon;

public class Ball extends Actor {

	private String ball = "ball.png";
	
	private static volatile Ball instance = null;
	
	private Ball(){
	}
	
	private void setup(){
		Random r = new Random();
		x = boundx / 2;
		y = boundy / 2;
		while (dx==0){
			dx = r.nextInt(9) - 4;
		}
		dy = r.nextInt(5) - 2;
		
		ImageIcon ii = new ImageIcon(this.getClass().getResource(ball));
		image = ii.getImage();
		
		width=5;
		height=5;
	}
	
	public static Ball get(){
		if (instance==null){
			synchronized (Ball.class){
				if (instance==null){
					instance=new Ball();
				}
			}
		}
		return instance;
	}
	
	public void setBounds(int boundx, int boundy){
		this.boundx=boundx-5;
		this.boundy=boundy-15;
		setup();
	}
	public void move(){
		if (x<=0 || x>=boundx) dx=-dx;
		if (y<=0 || y>=boundy) dy=-dy;
		x+=dx;
		y+=dy;		
	}
	
	public void hit(int offset){
		dx=-dx;
		dy+=offset;
		System.out.println("dy=("+dy+")");
	}
}
