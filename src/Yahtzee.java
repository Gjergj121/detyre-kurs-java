import javax.swing.JFrame;


public class Yahtzee {

	public static void main(String[] args) {
		
		GUI window = new GUI();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(700, 500);
		window.setVisible(true);

	}

}
