package actionListeners;

import yahtzeeGame.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import java.awt.*;

public class SignUpActionListener implements ActionListener{

	JDialog dialogFrame;
	private Loja loja;

	public SignUpActionListener(JDialog dialogFrame, Loja loja) {
		this.dialogFrame = dialogFrame;
		this.loja = loja;
	}

	public void actionPerformed(ActionEvent e) {
		String title = "Yahtzee";

		String temp = JOptionPane.showInputDialog(null, "Emri,Mbiemri,Mosha e lojtarit " + (loja.getLojtaret().size() + 1) + ": ", title, JOptionPane.QUESTION_MESSAGE);

		String[] emerMbiMosha = temp.split(",\\s*");

		loja.getDbConnector().insertLojtari(new Lojtar(emerMbiMosha[0], emerMbiMosha[1], Integer.parseInt(emerMbiMosha[2])));

		Lojtar lojtar = loja.getDbConnector().selectLojtariByEmri(emerMbiMosha[0]); // per ti marre id.

		loja.addLojtar(lojtar);

		dialogFrame.setVisible(false);

		if (loja.getLojtaret().size() < loja.getNumriLojtareve()) {
			DialogFrame dialogFrame = new DialogFrame(loja);
			dialogFrame.initDialogFrame();
		} else {
			YahtzeeFrame yahtzeeFrame = new YahtzeeFrame();
			yahtzeeFrame.init(loja);
		}
		
	}
	
}
