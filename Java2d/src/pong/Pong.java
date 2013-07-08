package pong;

import javax.swing.JFrame;

public class Pong extends JFrame {

	public Pong() {
		add(new Board(400,300));
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 315);
        setLocationRelativeTo(null);
        setTitle("Pong");
        setResizable(false);
        setVisible(true);
	}
	
	public static void main(String[] args) {
		new Pong();

	}

}
