package actionListeners;


import dice.DiceButton;
import yahtzeeGame.Loja;
import yahtzeeGame.Lojtar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import static java.awt.Color.BLACK;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;


public class PiketMouseListener extends MouseAdapter {

	private final int PIKET_E_SIPERME_INDEX = 6;
    private final int PIKET_E_POSHTME_INDEX = 15;
    private final int BONUS_INDEX = 7;
    private final int TOTAL_INDEX = 16;
	
	private final DiceButton[] diceButtons;
	private final JLabel[][] piketELojtareve;

	private final int kategoriIndex;
	private final Loja loja;
	private final JTextField currentTurnTextField;

	public PiketMouseListener(int kategoriIndex, Loja loja, DiceButton[] diceButtons, JLabel[][] piketELojtareve, JTextField currentTurnTextField) {
		this.loja = loja;
		this.kategoriIndex = kategoriIndex;
		this.diceButtons = diceButtons;
		this.piketELojtareve = piketELojtareve;
		this.currentTurnTextField = currentTurnTextField;
	}

	
	public void mouseClicked (MouseEvent e) {
		JLabel source = (JLabel) e.getSource();

		if (!source.isEnabled())
			return;

		String value = source.getText();
		int piket = value.equals("-") ? 0 : parseInt(value);
		loja.updatePiket(kategoriIndex, piket);

		source.setText(String.valueOf(piket));
		source.setForeground(BLACK);
		source.setEnabled(false);

		resetDiceAndPiket();

		if (loja.isPikeESiperme()) {
			updateJLabels(loja.getPIKET_E_SIPERME_INDEX(), loja.llogaritDheUpdatePiketESiperme());

			updateJLabels(loja.getBONUS_INDEX(), loja.llogaritDheUpdateBonus());
		}

		if (loja.isPiketEPoshtme()){
			updateJLabels(loja.getPIKET_E_POSHTME_INDEX(), loja.llogaritDheUpdatePiketEPoshtme());
		}

		if (loja.isEndGameForCurrentPlayer()) {
			updateJLabels(loja.getTOTAL_INDEX(), loja.llogaritDheUpdateTotalin());

			if (loja.getCurrentPlayerIndex() == loja.getNumriLojtareve() - 1)
				endGame();
		}

		loja.nextPlayer();

		currentTurnTextField.setText("Lojtari " + loja.getCurrentPlayer().getEmri() + " ka turnin!");
	}

	private void endGame() {
		Lojtar fituesi = loja.lojtariFitues();
		loja.save_totalin();
		JOptionPane.showMessageDialog(null, "Urime " + fituesi.getEmri() + "! Ju jeni fituesi.");
		System.exit(0);
	}

	private void updateJLabels(int index, int value) {
		piketELojtareve[index][loja.getCurrentPlayerIndex()].setText(String.valueOf(value));
		piketELojtareve[index][loja.getCurrentPlayerIndex()].setEnabled(false);
	}

	private void resetDiceAndPiket() {
		for (DiceButton diceButton : diceButtons)
			diceButton.resetDice();

		int nextPlayer = loja.getNextPlayer();
		int currentPlayer = loja.getCurrentPlayerIndex();

		for (int i = 0; i < loja.getNUMRI_KATEGORIVE(); i++) {

			if (! loja.getKategoriteEZgjedhuraPerLojtar()[i][currentPlayer]) {
				piketELojtareve[i][currentPlayer].setText("-");
				piketELojtareve[i][currentPlayer].setForeground(BLACK);
			}

			piketELojtareve[i][currentPlayer].setEnabled(false);

			if (! loja.getKategoriteEZgjedhuraPerLojtar()[i][nextPlayer])
				piketELojtareve[i][nextPlayer].setEnabled(true);
			
		}
		
		piketELojtareve[PIKET_E_SIPERME_INDEX][nextPlayer].setEnabled(false);
		piketELojtareve[BONUS_INDEX][nextPlayer].setEnabled(false);
		piketELojtareve[PIKET_E_POSHTME_INDEX][nextPlayer].setEnabled(false);
		piketELojtareve[TOTAL_INDEX][nextPlayer].setEnabled(false);
	}

}
