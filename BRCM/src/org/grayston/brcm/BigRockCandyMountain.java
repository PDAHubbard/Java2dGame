package org.grayston.brcm;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class BigRockCandyMountain {
	
	private static final String WINDOW_TITLE = null;
	private static final long timerTicksPerSecond = 60;
	private static boolean isRunning;
	private static boolean isApplication;
	private boolean fullscreen;
	
	private int width, height;
	private long lastLoopTime;
	private long lastFpsTime;
	private int fps;

	public BigRockCandyMountain(boolean fullscreen){
		this.fullscreen=fullscreen;
		
		width=800;
		height=600;
		
		initialise();		
	}
	
	/**
	 * Get the high resolution time in ms.
	 * Sys time is in nanos do div by 1000.
	 * @return time in ms
	 */
	public static long getTime(){
		return (Sys.getTime() * 1000) / timerTicksPerSecond;
	}
	
	/**
	 * Sleep for a certain number of ms
	 * @param duration Time in ms to sleep for
	 */
	public static void sleep(long duration){
		try{
			Thread.sleep((duration*timerTicksPerSecond)/1000);
		}catch (InterruptedException i){
			
		}
	}
	
	private void initialise() {
		try{
			if (!setDisplayMode())
				fullscreen=false;
			Display.setTitle(WINDOW_TITLE);
			Display.setFullscreen(fullscreen);
			Display.create();
			
			//grab the mouse
			if (isApplication) {
			Mouse.setGrabbed(true);
			}
			
			// 2d textures
			glEnable(GL_TEXTURE_2D);
			
			// disable as it's 2d
			glDisable(GL_DEPTH_TEST);
			
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			
			glOrtho(0, width, height, 0, -1, 1);
			glMatrixMode(GL_MODELVIEW);
			glLoadIdentity();
			glViewport(0,0,width, height);
		} catch (LWJGLException l){
			System.out.println("Game Exiting - exception in initialisation");
			l.printStackTrace();
			BigRockCandyMountain.isRunning = false;
		}
	}

	private boolean setDisplayMode() {
		try{
			DisplayMode[] dm = org.lwjgl.util.Display.getAvailableDisplayModes(width, height, -1, -1, -1, -1, 60, 60);
			
			org.lwjgl.util.Display.setDisplayMode(dm, new String[] {
					"width=" + width,
					"height=" + height,
					"freq=" + 60,
					"bpp=" + org.lwjgl.opengl.Display.getDisplayMode().getBitsPerPixel()
			});
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to enter fullscreen, continuing in windowed mode");
		}
		return false;
	}

	public static void main(String[] args) {
		
		isApplication = true;
		new BigRockCandyMountain(("-f".equals(args[0]))).execute();
		System.exit(0);
		}

	private void execute() {
		gameLoop();		
	}

	private void gameLoop() {
		while (BigRockCandyMountain.isRunning) {
			//clear screen
			
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glMatrixMode(GL_MODELVIEW);
			glLoadIdentity();
			
			//render the new frame
			renderNextFrame();
			
			Display.update();
		}
		//clean up
		soundManager.destroy();
		Display.destroy();

	}

	/**
	 * Notification that a frame is being rendered. Responsible for running game logic and rendering the scene
	 * 
	 */
	private void renderNextFrame() {
		Display.sync(60);
		
		long delta = getTime() - lastLoopTime;
		lastLoopTime = getTime();
		lastFpsTime += delta;
		fps++;
		
		//update the fps counter if a second has passed
		if (lastFpsTime >= 1000) {
			Display.setTitle(WINDOW_TITLE + " (FPS: " + fps + ")");
			lastFpsTime=0;
			fps=0;
		}
		
		//Move all entities
		if (!waitingForKeyPress && !soundManager.isPlayingSound()){
			for (Entity entity : entities) {
				entity.move(delta);
			}
		}
		
		//Draw all entities
		for (Entity entity : entities) {
			entity.draw();
		}
		
		//brute force collision
		for (int p=0; p< entities.size(); p++){
			for (int s=p+1; s < entities.size(); s++){
				Entity me = entities.get(p);
				Entity him = entities.get(s);
				
				if (me.collidesWith(him)) {
					me.collidedWith(him);
					him.collidedWith(me);
				}
			}
		}
		
		entities.removeAll(removeList);
		removeList.clear();
		
		if (logicRequiredThisLoop) {
			for ( Entity entity : entities ) {
				entity.doLogic();
			}
			logicRequiredThisLoop = false;
		}
		
		if (waitingForKeyPress) {
			message.draw(width/2-75, height/2);
		}
		
		// Move the player
		
		boolean L = hasInput(Keyboard.KEY_L);
		boolean H = hasInput(Keyboard.KEY_H);
		boolean J = hasInput(Keyboard.KEY_J);
		boolean K = hasInput(Keyboard.KEY_K);
		
		if (!soundManager.isPlayingSound()) {
			if (L && !H) {
				player.setDx(movespeed);
				soundManager.playSound(MOVE_RIGHT);
			}
			else if (H && !L) {
				player.SetDx(-movespeed);
				soundManager.playSound(MOVE_LEFT);
			}
			else if (J && !K) {
				player.setDy(-movespeed);
				soundManager.playSound(MOVE_DOWN);
			}
			else if (K&& !J) {
				player.setDy(movespeed);
				soundManager.playSound(MOVE_UP);
			}
		}
		
		if ((Display.isCloseRequested()) || Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && isApplication) {
			BigRockCandyMountain.isRunning=false;
		}
		
	}

}
