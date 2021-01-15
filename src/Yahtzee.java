import javax.swing.JFrame;


public class Yahtzee {

	public static void main(String[] args) {
		//TODO: Remove.
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		GUI window = new GUI();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(700, 500);
		window.setVisible(true);

	}

}
