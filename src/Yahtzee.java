import yahtzeeGame.GUI;

import javax.swing.JFrame;

public class Yahtzee {

	public static void main(String[] args) {
		GUI yahtzeeFrame = new GUI();
		
		yahtzeeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		yahtzeeFrame.setSize(700, 500);
		yahtzeeFrame.setVisible(true);

	}

}
