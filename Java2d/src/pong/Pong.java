package pong;

import javax.swing.JFrame;

public class Pong extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6876111330624142640L;

	public Pong() {
		add(new Board(700,300));
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 315);
        setLocationRelativeTo(null);
        setTitle("Pong");
        setResizable(false);
        setVisible(true);
	}
	
	public static void main(String[] args) {
		new Pong();

	}

}
