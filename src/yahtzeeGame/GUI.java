package yahtzeeGame;

import actionListeners.HidhZariActionListener;
import actionListeners.PiketMouseListener;
import dice.DiceButton;

import javax.swing.*;
import java.awt.*;

import static yahtzeeGame.Category.CATEGORIES;

public class GUI extends JFrame{
	private final int NUMRI_KATEGORIVE = 17;
	private final int NUMRI_ZARAVE = 5;
	private DiceButton[] diceButtons;
	private JButton hidhZaratButton;
	private JPanel panel, mainPanel, categoryPanel, dicePanel;
	private JPanel[] piketPanel;

	private JLabel[] emrateElojtareveLabel;
	private JLabel[][] piketELojtareveLabels;
	private JLabel kategoriaTitle;
	JLabel[] kategoriaLabels = new JLabel[NUMRI_KATEGORIVE];
	JTextField currentPlayerName;
	Loja loja;
	
	public GUI(){
		fillimiLojes();

		panel = new JPanel(new BorderLayout());

		addMainPanel();

		setDiceButtons();
		setHidhZaratButton();
		addDicePanel();

		//TODO: addListiners method
		addMouseListenersToScoreLables();

		add(panel);
	}
	
	
	public void fillimiLojes() {

		String title = "Loja Yahtzee";
		String n = JOptionPane.showInputDialog(null, "Vendosni numrin e lojtareve (1-4): ", title, JOptionPane.QUESTION_MESSAGE);
		int numriLojtareve = 0;

		try {
			numriLojtareve = Integer.parseInt(n);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Vendosni numer", title, JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		Lojtar[] lojtaret = new Lojtar[numriLojtareve];
		
		if(numriLojtareve <1 || numriLojtareve >4) {
			JOptionPane.showMessageDialog(null, "Loja Yahtzee mund te luhet me 1-4 lojtare!", title, JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		String emriTemp;
		for(int i = 0; i < numriLojtareve; i++) {
			// TODO: Shto mbiemri mosha
			emriTemp =  JOptionPane.showInputDialog(null, "Emri i lojtarit "+ (i+1) + ": ", title, JOptionPane.QUESTION_MESSAGE);
			lojtaret[i] = new Lojtar(emriTemp, "", -1);
		}

		loja = new Loja(numriLojtareve, NUMRI_ZARAVE, NUMRI_KATEGORIVE, lojtaret);
		emrateElojtareveLabel = new JLabel[numriLojtareve];
	}
	
	
	public void addMainPanel() {
		
		mainPanel = new JPanel(new GridLayout(1, loja.getNumriLojtareve() + 1, 1, 1));
		addCategoryPanel();
		addScorePanel();
		panel.add(mainPanel, BorderLayout.CENTER);

	}
	
	public void addCategoryPanel() {
		kategoriaTitle = createKategoriLabel("Kategorite");
		kategoriaTitle.setForeground(Color.RED);

		categoryPanel = new JPanel();
		categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.Y_AXIS));
		categoryPanel.setBackground(Color.CYAN);

		categoryPanel.add(kategoriaTitle);

		for(int i = 0; i < NUMRI_KATEGORIVE; i++) {
			kategoriaLabels[i] = createKategoriLabel(CATEGORIES[i]);
			categoryPanel.add(kategoriaLabels[i]);
		}
		mainPanel.add(categoryPanel);

	}

	private JLabel createKategoriLabel(String name) {
		JLabel kategoriLabel = new JLabel(name);
		kategoriLabel.setHorizontalAlignment(SwingConstants.CENTER);
		kategoriLabel.setPreferredSize(new Dimension(200, 50));
		kategoriLabel.setMaximumSize(new Dimension(250, 50));
		return  kategoriLabel;
	}

	public void addScorePanel() {
		
		piketPanel = new JPanel[loja.getNumriLojtareve()];
		piketELojtareveLabels = new JLabel[NUMRI_KATEGORIVE][loja.getNumriLojtareve()];
		
		
		for(int lojtarIndex = 0; lojtarIndex < loja.getNumriLojtareve(); lojtarIndex++) {
			piketPanel[lojtarIndex] = new JPanel();
			piketPanel[lojtarIndex].setLayout(new BoxLayout(piketPanel[lojtarIndex], BoxLayout.Y_AXIS));

			emrateElojtareveLabel[lojtarIndex] = createPiketLojtarLabel(loja.getLojtaret()[lojtarIndex].getEmri());

			piketPanel[lojtarIndex].add(emrateElojtareveLabel[lojtarIndex]);

			for(int kategoriIndex = 0; kategoriIndex < NUMRI_KATEGORIVE; kategoriIndex++) {
				piketELojtareveLabels[kategoriIndex][lojtarIndex] = createPiketLojtarLabel("-");

				piketELojtareveLabels[kategoriIndex][lojtarIndex] .setEnabled(false);

				if (lojtarIndex == loja.getCurrentPlayer()) //TODO: pse getCurrentTurn?? Nuk duhet getCurrentPlayer?
					piketELojtareveLabels[kategoriIndex][lojtarIndex].setEnabled(true);

				piketPanel[lojtarIndex].add(piketELojtareveLabels[kategoriIndex][lojtarIndex]);
			}

			mainPanel.add(piketPanel[lojtarIndex]);
		}	
		
	}

	private void addMouseListenersToScoreLables() {
		for(int lojtarIndex = 0; lojtarIndex < loja.getNumriLojtareve(); lojtarIndex++) {
			for(int kategoriIndex = 0; kategoriIndex < NUMRI_KATEGORIVE; kategoriIndex++) {
					piketELojtareveLabels[kategoriIndex][lojtarIndex].addMouseListener( new PiketMouseListener(kategoriIndex, loja, diceButtons, piketELojtareveLabels));
				}
			}
		}

	private JLabel createPiketLojtarLabel(String name) {
		JLabel lojtarLabel = new JLabel(name); 
		lojtarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lojtarLabel.setPreferredSize(new Dimension(100, 50));
		lojtarLabel.setMaximumSize(new Dimension(100, 50));
		return lojtarLabel;
	}


	public void addDicePanel() {
		
		dicePanel = new JPanel();
		dicePanel.setLayout(new BoxLayout(dicePanel, BoxLayout.X_AXIS));
		dicePanel.setBackground(Color.LIGHT_GRAY);
		
		dicePanel.add(hidhZaratButton);

		for(int i = 0; i < NUMRI_ZARAVE; i++) {
			dicePanel.add(diceButtons[i]);
		}
		
		currentPlayerName = new JTextField();
		dicePanel.add(currentPlayerName);
		
		panel.add(dicePanel, BorderLayout.SOUTH);
	}

	private void setDiceButtons() {
		diceButtons = new DiceButton[NUMRI_ZARAVE];

		for(int i = 0; i < NUMRI_ZARAVE; i++) {
			diceButtons[i] = new DiceButton();
		}
	}

	private void setHidhZaratButton() {
		hidhZaratButton = new JButton("Hidh zarat");
		HidhZariActionListener hidhZariActionListener = new HidhZariActionListener(diceButtons, loja, piketELojtareveLabels);
		hidhZaratButton.addActionListener(hidhZariActionListener);
	}

}
