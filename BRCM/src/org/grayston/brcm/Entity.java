package org.grayston.brcm;

import java.awt.Rectangle;

public abstract class Entity {

	protected float x,y;
	protected Sprite sprite;

	protected float dx, dy;
	private Rectangle me = new Rectangle();
	private Rectangle him = new Rectangle();
	
	protected Entity(Sprite sprite, int x, int y){
		this.sprite = sprite;
		this.x=x;
		this.y=y;
	}
	
	/**
	 * Update the location of the entity based on move speeds
	 * @param delta speed to move at
	 */
	public void move(long delta) {
		x += (delta * dx) / 1000;
		y += (delta * dx) / 1000;
	}
	
	public float getDx() {
		return dx;
	}

	public void setDx(float dx) {
		this.dx = dx;
	}

	public float getDy() {
		return dy;
	}

	public void setDy(float dy) {
		this.dy = dy;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void draw(){
		sprite.draw((int) x, (int) y);
	}
	
	public void doLogic(){
		
	}
	
	public boolean collidesWith(Entity other) {
		me.setBounds((int) x, (int) y, sprite.getWidth(), sprite.getHeight());
		him.setBounds((int); other.x, (int) other.y, other.sprite.getWidth(), other.sprite.getHeight());
		
		return me.intersects(him);
	}
	
	public abstract void collidedWith(Entity other);
}
