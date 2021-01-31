package actionListeners;

import yahtzeeGame.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class SignInActionListener implements ActionListener  {

	JDialog dialogFrame;
	private Loja loja;

	public SignInActionListener(JDialog dialogFrame, Loja loja) {
		this.dialogFrame = dialogFrame;
		this.loja = loja;
	}
	
	
	public void actionPerformed(ActionEvent event) {

		String title = "Yahtzee";


		String emriTemp = JOptionPane.showInputDialog(null, "Emri i lojtarit " + (loja.getLojtaret().size() + 1) + ": ", title, JOptionPane.QUESTION_MESSAGE);

		Lojtar lojtar = loja.getDbConnector().selectLojtariByEmri(emriTemp);

		if (lojtar == null) {
			JOptionPane.showMessageDialog(null, "Lojtari " + emriTemp + " nuk u gjet !", title, JOptionPane.INFORMATION_MESSAGE);
			return;
		}

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
