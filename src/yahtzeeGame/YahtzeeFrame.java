package yahtzeeGame;

import actionListeners.*;
import dice.DiceButton;

import javax.swing.*;
import java.awt.*;

import static yahtzeeGame.Category.CATEGORIES;

public class YahtzeeFrame extends JFrame{
	private final int NUMRI_KATEGORIVE = 17;
	private final int NUMRI_ZARAVE = 5;
	private final int PIKET_E_SIPERME_INDEX = 6;
    private final int PIKET_E_POSHTME_INDEX = 15;
    private final int BONUS_INDEX = 7;
    private final int TOTAL_INDEX = 16;
    
	private DiceButton[] diceButtons;
	private JButton hidhZaratButton, leaderBoard;
	private JPanel panel, mainPanel, categoryPanel, dicePanel;
	private JPanel[] piketPanel;

	private JLabel[] emrateElojtareveLabel;
	private JLabel[][] piketELojtareveLabels;
	private JLabel kategoriaTitle;
	JLabel[] kategoriaLabels = new JLabel[NUMRI_KATEGORIVE];
	JTextField currentTurnTextField;
	Loja loja;

	public void init(Loja loja){
		this.loja = loja;
		emrateElojtareveLabel = new JLabel[loja.getNumriLojtareve()];

		panel = new JPanel(new BorderLayout());

		addMainPanel();

		setDiceButtons();
		setHidhZaratButton();
		addDicePanel();

		addListeners();

		add(panel);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900, 500);
		this.setVisible(true);
	}
	

	private void addListeners() {
		addHidhZariActionListener();
		addMouseListenersToScoreLables();
	}

	private void addHidhZariActionListener() {
		HidhZariActionListener hidhZariActionListener = new HidhZariActionListener(diceButtons, loja, piketELojtareveLabels, currentTurnTextField);
		hidhZaratButton.addActionListener(hidhZariActionListener);
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

			emrateElojtareveLabel[lojtarIndex] = createPiketLojtarLabel(loja.getLojtari(lojtarIndex).getEmri());

			piketPanel[lojtarIndex].add(emrateElojtareveLabel[lojtarIndex]);

			for(int kategoriIndex = 0; kategoriIndex < NUMRI_KATEGORIVE; kategoriIndex++) {
				piketELojtareveLabels[kategoriIndex][lojtarIndex] = createPiketLojtarLabel("-");

				piketELojtareveLabels[kategoriIndex][lojtarIndex] .setEnabled(false);

				if (lojtarIndex == loja.getCurrentPlayerIndex())
					piketELojtareveLabels[kategoriIndex][lojtarIndex].setEnabled(true);

				piketPanel[lojtarIndex].add(piketELojtareveLabels[kategoriIndex][lojtarIndex]);
			}
			
			piketELojtareveLabels[PIKET_E_SIPERME_INDEX][lojtarIndex].setEnabled(false);
			piketELojtareveLabels[BONUS_INDEX][lojtarIndex].setEnabled(false);
			piketELojtareveLabels[PIKET_E_POSHTME_INDEX][lojtarIndex].setEnabled(false);
			piketELojtareveLabels[TOTAL_INDEX][lojtarIndex].setEnabled(false);

			mainPanel.add(piketPanel[lojtarIndex]);
		}	
		
	}

	private void addMouseListenersToScoreLables() {
		for(int lojtarIndex = 0; lojtarIndex < loja.getNumriLojtareve(); lojtarIndex++) {
			for(int kategoriIndex = 0; kategoriIndex < NUMRI_KATEGORIVE; kategoriIndex++) {
					piketELojtareveLabels[kategoriIndex][lojtarIndex].addMouseListener(
							new PiketMouseListener(kategoriIndex, loja, diceButtons, piketELojtareveLabels, currentTurnTextField)
					);
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
		
		currentTurnTextField = new JTextField();
		currentTurnTextField.setText("Lojtari " + loja.getCurrentPlayer().getEmri() + " ka turnin");
		currentTurnTextField.setEditable(false);
		dicePanel.add(currentTurnTextField);
		
		leaderBoard = new JButton("Leaderboard");
		leaderBoard.addActionListener(new LeaderBoardActionListener(loja.getDbConnector()));
		dicePanel.add(leaderBoard);
		
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

	}

}
