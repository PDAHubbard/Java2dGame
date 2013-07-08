package pong;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Bat extends Actor{

	private String bat = "bat.png";
	private int speed;
	private int upkey = KeyEvent.VK_UP;
	private int downkey = KeyEvent.VK_DOWN;

	public Bat(int boundx, int boundy, int xpos, int speed){
		this.boundx=boundx-15;
		this.boundy=boundy-100;
		this.x=xpos;
		this.y=boundy/2;
		this.speed=speed;
		
		ImageIcon ii = new ImageIcon(this.getClass().getResource(bat));
		image = ii.getImage();
		
		width = 15;
		height = 85;
	}
	
	public void setKeys(int up, int down){
		upkey=up;
		downkey=down;
	}
	
	public void move() {
		
		y+=dy;
		
		if (y<=5) y=5;
		if (y>=boundy) y=boundy;
	}
	
	public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == upkey) {
            dy = -speed;
        }

        if (key == downkey) {
            dy = speed;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == upkey) {
            dy = 0;
        }

        if (key == downkey) {
            dy = 0;
        }
    }
}
