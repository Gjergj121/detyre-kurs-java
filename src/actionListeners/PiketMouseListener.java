package actionListeners;


import dice.DiceButton;
import dice.DiceIcons;
import yahtzeeGame.Loja;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

import static java.awt.Color.BLACK;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;


public class PiketMouseListener extends MouseAdapter {

	private final DiceButton[] diceButtons;
	private final JLabel[][] piketELojtareve;

	private final int kategoriIndex;
	private final Loja loja;

	public PiketMouseListener(int kategoriIndex, Loja loja, DiceButton[] diceButtons, JLabel[][] piketELojtareve) {
		this.loja = loja;
		this.kategoriIndex = kategoriIndex;
		this.diceButtons = diceButtons;
		this.piketELojtareve = piketELojtareve;
	}

	
	public void mouseClicked (MouseEvent e) {
		JLabel source = (JLabel) e.getSource();

		if (!source.isEnabled())
			return;

		String value = source.getText();
		int piket = value.equals("-") ? 0 : parseInt(value);
		loja.updatePiket(kategoriIndex, piket);

		source.setForeground(BLACK);
		source.setEnabled(false);

		resetDiceAndPiket();

		loja.nextPlayer();
	}

	private void resetDiceAndPiket() {
		for (DiceButton diceButton : diceButtons)
			diceButton.resetDice();

		int nextPlayer = loja.getNextPlayer();
		int currentPlayer = loja.getCurrentPlayer();

		for (int i = 0; i < loja.getNumriKategorive(); i++) {

			if (! loja.getKategoriteEZgjedhuraPerLojtar()[i][currentPlayer]) {
				piketELojtareve[i][currentPlayer].setText("-");
				piketELojtareve[i][currentPlayer].setForeground(BLACK);
			}

			piketELojtareve[i][currentPlayer].setEnabled(false);

			if (! loja.getKategoriteEZgjedhuraPerLojtar()[i][nextPlayer])
				piketELojtareve[i][nextPlayer].setEnabled(true);
		}
	}

}
