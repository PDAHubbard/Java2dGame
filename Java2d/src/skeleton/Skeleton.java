package skeleton;

import javax.swing.JFrame;

public class Skeleton extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 501187451339743835L;

	public Skeleton() {
		add(new Board());
		setTitle("Skeleton");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300,200);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
	
	public static void main(String[] args) {
		new Skeleton();
	}

}
