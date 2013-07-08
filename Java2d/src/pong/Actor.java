package pong;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Actor {

	protected int dx;
	protected int dy;
	protected int x;
	protected int y;
	protected int boundx;
	protected int boundy;
	protected Image image;
	protected int width, height;

	public Actor() {
		super();
	}

	public void move() {
	
	}

	public int getx() {
		return x;
	}

	public int gety() {
		return y;
	}

	public Image getImage() {
		return image;
	}

	public Rectangle getBounds(){
		return new Rectangle(x, y, width, height);
	}
	
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}