package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener{
	
	private Timer timer;
	private Ball ball;
	private Bat bat1, bat2;
	private int boundx, boundy;
	private int score_player1, score_player2;
	Font font = new Font("Verdana", Font.BOLD, 18);
    FontMetrics metr = this.getFontMetrics(font);

	public Board(int boundx, int boundy){
		
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		this.boundx=boundx;
		this.boundy=boundy;
		
		score_player1=0;
		score_player2=0;
		
		setupBall();
		setupPlayer1();
		setupPlayer2();
		
		timer = new Timer(5, this);
		timer.start();
	}
	
	private void setupPlayer2() {

		bat2 = new Bat(boundx, boundy, boundx-50, 5);
		bat2.setKeys(KeyEvent.VK_O, KeyEvent.VK_L);
	}

	private void setupPlayer1() {
		bat1 = new Bat(boundx, boundy, 50, 5);
		bat1.setKeys(KeyEvent.VK_Q, KeyEvent.VK_A);
	}

	private void setupBall() {

		ball = new Ball(boundx, boundy);
		
	}

	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(ball.getImage(), ball.getx(), ball.gety(), this);
		g2d.drawImage(bat1.getImage(), bat1.getx(), bat1.gety(), this);
		g2d.drawImage(bat2.getImage(), bat2.getx(), bat2.gety(), this);
		
		String sc1 = new String(new Integer(score_player1).toString());
		String sc2 = new String(new Integer(score_player2).toString());
		String score = sc1 + " | " + sc2;
		
		g.setColor(Color.cyan);
		g.setFont(font);
		g.drawString(score, (boundx-metr.stringWidth(score))/2, 50);
		
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	
	public void actionPerformed(ActionEvent e){
		ball.move();
		bat1.move();
		bat2.move();
		checkCollisions();
		drawScore();
		repaint();
	}
	
	private void drawScore() {
		// TODO Auto-generated method stub
		
	}

	public void checkCollisions(){
		Rectangle rball = ball.getBounds();
		Rectangle rbat1 = bat1.getBounds();
		Rectangle rbat2 = bat2.getBounds();
		
		if (rball.intersects(rbat1) || rball.intersects(rbat2)){
			ball.hit();
		}
		
		if (ball.getx()<=0){
			score_player2++;
		}
		if (ball.getx()>=boundx-5){
			score_player1++;
		}
	}
	
    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            bat1.keyReleased(e);
            bat2.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            bat1.keyPressed(e);
            bat2.keyPressed(e);
        }
    }
}
