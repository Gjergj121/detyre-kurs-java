package yahtzeeGame;

import dice.Zari;

import java.awt.*;
import javax.swing.*;

public class GUI extends JFrame{
	final int NUMRI_KATEGORIVE_PLUS_HEAD = 18;
	final int NUMRI_ZARAVE = 5;
	Zari dc;
	JPanel panel, mainPanel, categoryPanel, dicePanel;
	JPanel[] scorePanel;
	public JLabel[][] scoreLabels;
	JLabel[] kategoriaLabels = new JLabel[NUMRI_KATEGORIVE_PLUS_HEAD];
	JTextField tekst;
	int numriLojtareve;
	Lojtar[] lojtaret;
	Loja loja;
	
	public GUI(){
		
		dc = new Zari(NUMRI_ZARAVE);
		
		fillimiLojes();
		
		panel = new JPanel(new BorderLayout());
		addMainPanel();
		addDicePanel();
		add(panel);
	}
	
	
	public void fillimiLojes() {

		String title = "Loja Yahtzee";
		String n = JOptionPane.showInputDialog(null, "Vendosni numrin e lojtareve (1-4): ", title, JOptionPane.QUESTION_MESSAGE);

		try {
			numriLojtareve = Integer.parseInt(n);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Vendosni numer", title, JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		lojtaret = new Lojtar[numriLojtareve];
		
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

		loja = new Loja(numriLojtareve, NUMRI_ZARAVE, NUMRI_KATEGORIVE_PLUS_HEAD - 1, lojtaret);

	}
	
	
	public void addMainPanel() {
		
		mainPanel = new JPanel(new GridLayout(1, numriLojtareve + 1, 1, 1));
		addCategoryPanel();
		addScorePanel();
		panel.add(mainPanel, BorderLayout.CENTER);

	}
	
	public void addCategoryPanel() {
		//TODO: refactor emrat const static
		kategoriaLabels[0] = new JLabel("Kategorite");

		for (int i = 0; i < NUMRI_KATEGORIVE_PLUS_HEAD - 1; i++) {
			kategoriaLabels[i+1] = new JLabel(Category.CATEGORIES[i]);
		}

		categoryPanel = new JPanel();
		categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.Y_AXIS));
		categoryPanel.setBackground(Color.CYAN);

		kategoriaLabels[0].setForeground(Color.RED);
		
		for(int i = 0; i < NUMRI_KATEGORIVE_PLUS_HEAD; i++) {
			kategoriaLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			kategoriaLabels[i].setPreferredSize(new Dimension(200, 50));
			kategoriaLabels[i].setMaximumSize(new Dimension(250, 50));
			categoryPanel.add(kategoriaLabels[i]);
		}
		mainPanel.add(categoryPanel);

	}
	
	public void addScorePanel() {
		
		scorePanel = new JPanel[numriLojtareve];
		scoreLabels = new JLabel[NUMRI_KATEGORIVE_PLUS_HEAD][numriLojtareve];
		
		
		for(int i = 0; i < numriLojtareve; i++) {
			scorePanel[i] = new JPanel();
			scorePanel[i].setLayout(new BoxLayout(scorePanel[i], BoxLayout.Y_AXIS));
			
			scoreLabels[0][i] = new JLabel(lojtaret[i].getEmri()); // vendos ne elementin e pare te cdo kolone emrin e lojtarit
			scoreLabels[0][i].setHorizontalAlignment(SwingConstants.CENTER);
			scoreLabels[0][i].setPreferredSize(new Dimension(100, 50));
			scoreLabels[0][i].setMaximumSize(new Dimension(100, 50));
			scorePanel[i].add(scoreLabels[0][i]);

			for(int j = 1; j < NUMRI_KATEGORIVE_PLUS_HEAD; j++) {
				scoreLabels[j][i] = new JLabel("-");
				scoreLabels[j][i].setHorizontalAlignment(SwingConstants.CENTER);
				scoreLabels[j][i].setPreferredSize(new Dimension(100, 50));
				scoreLabels[j][i].setMaximumSize(new Dimension(100, 50));
				scorePanel[i].add(scoreLabels[j][i]);
			}
			
			mainPanel.add(scorePanel[i]);
		}	
		
	}
	
	
	public void addDicePanel() {
		
		dicePanel = new JPanel();
		dicePanel.setLayout(new BoxLayout(dicePanel, BoxLayout.X_AXIS));
		dicePanel.setBackground(Color.LIGHT_GRAY);
		
		dicePanel.add(dc.getHidhZaratButton());

		for(int i = 0; i < NUMRI_ZARAVE; i++) {
			dicePanel.add(dc.getDiceButtons()[i]);
		}
		
		tekst = new JTextField();
		dicePanel.add(tekst);
		
		panel.add(dicePanel, BorderLayout.SOUTH);
	}
	
}
