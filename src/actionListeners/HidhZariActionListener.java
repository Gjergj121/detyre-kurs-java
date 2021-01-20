package actionListeners;

import dice.DiceButton;
import yahtzeeGame.Category;
import yahtzeeGame.Loja;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static java.awt.Color.BLACK;
import static java.awt.Color.RED;
import static java.lang.String.valueOf;

public class HidhZariActionListener implements ActionListener {

    private final DiceButton[] diceButtons;
    private final JLabel[][] piketELojtareve;

    private final Loja loja;
    private Category category;

    public HidhZariActionListener(DiceButton[] diceButtons, Loja loja, JLabel[][] piketELojtareve) {
        this.diceButtons = diceButtons;
        this.piketELojtareve = piketELojtareve;
        this.loja = loja;
        this.category = new Category(loja.getNumriZarave());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (loja.getCurrentTurn() > 2){
            return;
        }

        Random randGen = new Random();

        for (int i = 0; i < loja.getNumriZarave(); i++) {
            if (! diceButtons[i].isSelected()) {  // nqs zari nuk eshte i shtypur nga perdoruesi, ndryshoj vleren
                int randValue = randGen.nextInt(6) + 1;
                diceButtons[i].setValue(randValue);
                loja.updateDiceState(i, randValue);
            }
        }

        int[] categoryResults = category.llogaritTeGjithaKategoriteSipasRradhes(loja.getDiceState());

        updatePiketELojtareve(categoryResults);

        loja.nextTurn();
    }

    private void updatePiketELojtareve(int[] categoryResults) {
        for (int i = 0; i < categoryResults.length; i++) {
            JLabel temp = piketELojtareve[i][loja.getCurrentPlayer()];

            if (temp.isEnabled() ) {
                if (categoryResults[i] > 0) {
                    temp.setText(valueOf(categoryResults[i]));
                    temp.setForeground(RED);
                }
                else {
                    temp.setText("-");
                    temp.setForeground(BLACK);
                }
            }
        }
    }

}
