package actionListeners;

import dice.DiceButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class HidhZariActionListener implements ActionListener {

    private final DiceButton[] diceButtons;
    private final int nrZarave;

    public HidhZariActionListener(int nrZarave, DiceButton[] diceButtons) {
        this.nrZarave = nrZarave;
        this.diceButtons = diceButtons;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Random randGen = new Random();

        for (int i = 0; i < nrZarave; i++) {
            if (! diceButtons[i].isSelected()) {
                  diceButtons[i].setValue(randGen.nextInt(6) + 1);
            }
        }


    }

}
