package yahtzeeGame;

import javax.swing.*;

public class InputDialogs {
    public static int getNumriELojtareve() {
        String title = "Loja Yahtzee";
        String n = JOptionPane.showInputDialog(null, "Vendosni numrin e lojtareve (1-4): ", title, JOptionPane.QUESTION_MESSAGE);
        int numriLojtareve = 0;

        try {
            numriLojtareve = Integer.parseInt(n);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Vendosni numer", title, JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        if (numriLojtareve < 1 || numriLojtareve > 4) {
            JOptionPane.showMessageDialog(null, "Loja Yahtzee mund te luhet me 1-4 lojtare!", title, JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        return numriLojtareve;
    }
}
