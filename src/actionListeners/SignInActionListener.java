package actionListeners;

import yahtzeeGame.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class SignInActionListener implements ActionListener  {

	JDialog dialogFrame;
	private Loja loja;
	private int lojtarIndex;
	private DBConnector dbConnector;

	public SignInActionListener(JDialog dialogFrame, Loja loja, int lojtarIndex, DBConnector dbConnector) {
		this.dialogFrame = dialogFrame;
		this.loja = loja;
		this.lojtarIndex = lojtarIndex;
		this.dbConnector = dbConnector;
	}
	
	
	public void actionPerformed(ActionEvent event) {

		String title = "Yahtzee";


		// TODO: Shto mbiemri mosha
		String emriTemp = JOptionPane.showInputDialog(null, "Emri i lojtarit " + (lojtarIndex + 1) + ": ", title, JOptionPane.QUESTION_MESSAGE);

		Lojtar lojtar = dbConnector.selectLojtariByEmri(emriTemp);

		if (lojtar == null) {
			JOptionPane.showMessageDialog(null, "Lojtari " + emriTemp + " nuk u gjet !", title, JOptionPane.INFORMATION_MESSAGE);
			return;
		}

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
