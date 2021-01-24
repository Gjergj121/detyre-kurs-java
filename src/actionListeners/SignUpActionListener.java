package actionListeners;

import yahtzeeGame.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import java.awt.*;

public class SignUpActionListener implements ActionListener{

	JDialog dialogFrame;
	private Loja loja;
	private int lojtarIndex;
	private DBConnector dbConnector;

	public SignUpActionListener(JDialog dialogFrame, Loja loja, int lojtarIndex, DBConnector dbConnector) {
		this.dialogFrame = dialogFrame;
		this.loja = loja;
		this.lojtarIndex = lojtarIndex;
		this.dbConnector = dbConnector;
	}

	public void actionPerformed(ActionEvent e) {
		String title = "Yahtzee";

		String temp = JOptionPane.showInputDialog(null, "Emri,Mbiemri,Mosha e lojtarit " + (lojtarIndex + 1) + ": ", title, JOptionPane.QUESTION_MESSAGE);

		String[] emerMbiMosha = temp.split(",");

		dbConnector.insertLojtari(new Lojtar(emerMbiMosha[0], emerMbiMosha[1], Integer.parseInt(emerMbiMosha[2])));

		Lojtar lojtar = dbConnector.selectLojtariByEmri(emerMbiMosha[0]); // per ti marre id.
		//TODO: select piket e grumbulluara.

		loja.getLojtaret()[lojtarIndex] = lojtar;

		dialogFrame.setVisible(false);

		if (lojtarIndex < loja.getNumriLojtareve() - 1) {
			DialogFrame dialogFrame = new DialogFrame(loja, lojtarIndex+1, dbConnector);
			dialogFrame.initDialogFrame();
		} else {
			YahtzeeFrame yahtzeeFrame = new YahtzeeFrame();
			yahtzeeFrame.init(loja);
		}
		
	}
	
}
