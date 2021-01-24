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

		Lojtar lojtar = null;

		while (lojtar == null) {
			// TODO: Shto mbiemri mosha
			String emriTemp = JOptionPane.showInputDialog(null, "Emri i lojtarit " + (lojtarIndex + 1) + ": ", title, JOptionPane.QUESTION_MESSAGE);

			lojtar = dbConnector.selectLojtariByEmri(emriTemp);

			if (lojtar == null)
				System.out.println("Lojtari nuk u gjet!"); //TODO: ta bejme jdialogpane? apo ta cojme tek sign up?
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
