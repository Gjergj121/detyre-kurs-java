package dice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiceButton extends JButton {
    private int value;

    public DiceButton() {
        value = -1;
        this.setIcon(DiceIcons.questionMark);
        this.setPreferredSize(new Dimension(60, 60));
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DiceButton source = (DiceButton) e.getSource();
                if (source.getValue() > -1) {
                    source.setSelected(!source.isSelected());

                    if (source.isSelected())
                        source.setRedIcon();
                    else
                        source.setIcon();
                }
            }
        });
    }

    private void setIcon() {
        if (value > -1)
            this.setIcon(DiceIcons.diceIcons[value-1]);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        this.setIcon(DiceIcons.diceIcons[value-1]);
    }

    public void setRedIcon() {
        if (value > -1)
            this.setIcon(DiceIcons.redDiceIcons[value]);
    }
}
