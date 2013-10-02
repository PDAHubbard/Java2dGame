package org.grayston.brcm;

import static org.lwjgl.opengl.GL11.*;

public class Sprite {

	private int width, height;
	private Object image;
	private Texture texture;
	
	public Sprite(Object i, int width, int height){
		this.image=i;
		this.width=width;
		this.height=height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void draw(int x, int y){
		glPushMatrix();
		
		texture.bind();
		
		glTranslatef(x,y,0);
		
		glBegin(GL_QUADS);
		{
			glTexCoord2f(0,0);
			glVertex2f(0,0);
			
			glTexCoord2f(0, height);
			glVertex2f(0, height);
			
			glTexCoord2f(texture.getWidth(), texture.getHeight());
			glVertex2f(width, height);
			
			glTexCoord2f(width, 0);
			glVertex2f(width,0);
		}
		glEnd();
		
		glPopMatrix();
	}
}
