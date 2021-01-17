package dice;

import actionListeners.HidhZariActionListener;
//import dice.DiceButton;

import java.awt.*;
import javax.swing.JButton;
 
public class Zari {

	private DiceButton[] diceButtons;
	private JButton hidhZaratButton;
	int nrZarave;

	public Zari(int nrZarave) {
		this.nrZarave = nrZarave;
		setDiceButtons();
		setHidhZaratButton();
	}

	public JButton[] getDiceButtons() {
		return diceButtons;
	}
	//TODO:
	private void setDiceButtons() {
		diceButtons = new DiceButton[nrZarave];

		for(int i = 0; i < nrZarave; i++) {
			diceButtons[i] = new DiceButton();
		}
	}

	public JButton getHidhZaratButton() {
		return hidhZaratButton;
	}

	private void setHidhZaratButton() {
		hidhZaratButton = new JButton("Hidh zarat");
		HidhZariActionListener hidhZariActionListener = new HidhZariActionListener(nrZarave, diceButtons);
		hidhZaratButton.addActionListener(hidhZariActionListener);
	}
	
/*	public void actionPerformed(ActionEvent event) {
		for(int i = 0; i < 5; i++) {
			
			if(event.getSource() == diceButtons[i]) {
				JButton source = (JButton) event.getSource();
				
				if(source.getText() == "?")
					return;
				else if(source.getIcon() == diceIcons[i])
					source.setIcon(redDiceIcons[i]);
				else
					source.setIcon(diceIcons[i]);
				
				return;
			}	
		}
		
	}*/
	
}

