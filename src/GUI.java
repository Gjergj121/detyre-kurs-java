import dice.Zari;

import java.awt.*;
import javax.swing.*;

public class GUI extends JFrame{
	final int KATEGORITE = 18;
	final int NUMRI_ZARAVE = 5;
	Zari dc;
	JPanel panel, mainPanel, categoryPanel, dicePanel;
	JPanel[] scorePanel;
	JLabel[][] scoreLabels;
	JLabel[] kategoriaLabels = new JLabel[KATEGORITE];
	JTextField tekst;
	int numriLojtareve;
	String[] emri = new String[4];
	
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
		
		if(numriLojtareve <1 || numriLojtareve >4) {
			JOptionPane.showMessageDialog(null, "Loja Yahtzee mund te luhet me 1-4 lojtare!", title, JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		for(int i = 0; i < numriLojtareve; i++) {
			 emri[i] =  JOptionPane.showInputDialog(null, "Emri i lojtarit "+ (i+1) + ": ", title, JOptionPane.QUESTION_MESSAGE);
		}
		
	}
	
	
	public void addMainPanel() {
		
		mainPanel = new JPanel(new GridLayout(1, numriLojtareve + 1, 1, 1));
		addCategoryPanel();
		addScorePanel();
		panel.add(mainPanel, BorderLayout.CENTER);

	}
	
	public void addCategoryPanel() {
		kategoriaLabels[0] = new JLabel("Kategorite");
		kategoriaLabels[1] = new JLabel("Njesha ");
		kategoriaLabels[2] = new JLabel("Dysha ");
		kategoriaLabels[3] = new JLabel("Tresha ");
		kategoriaLabels[4] = new JLabel("Katra ");
		kategoriaLabels[5] = new JLabel("Pesa ");
		kategoriaLabels[6] = new JLabel("Gjashta ");
		kategoriaLabels[7] = new JLabel("Piket e siperme ");
		kategoriaLabels[8] = new JLabel("Bonus (35) ");
		kategoriaLabels[9] = new JLabel("Tre me nje vlere ");
		kategoriaLabels[10] = new JLabel("Kater me nje vlere ");
		kategoriaLabels[11] = new JLabel("Tre dhe Dy (25) ");
		kategoriaLabels[12] = new JLabel("Kater te njepasnjeshme (30) ");
		kategoriaLabels[13] = new JLabel("Pese te njepasnjeshme (40) ");
		kategoriaLabels[14] = new JLabel("E njejta vlere (50) ");
		kategoriaLabels[15] = new JLabel("Cdo rast ");
		kategoriaLabels[16] = new JLabel("Piket e poshtme ");
		kategoriaLabels[17] = new JLabel("TOTAL ");
		
		categoryPanel = new JPanel();
		categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.Y_AXIS));
		categoryPanel.setBackground(Color.CYAN);
		
		kategoriaLabels[0].setForeground(Color.RED);
		
		for(int i = 0; i < KATEGORITE; i++) {
		//	categoryPanel.add(Box.createGlue());
			kategoriaLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			kategoriaLabels[i].setPreferredSize(new Dimension(200, 50));
			kategoriaLabels[i].setMaximumSize(new Dimension(250, 50));
			categoryPanel.add(kategoriaLabels[i]);
		}
		
		mainPanel.add(categoryPanel);
	}
	
	public void addScorePanel() {
		
		scorePanel = new JPanel[numriLojtareve];
		scoreLabels = new JLabel[KATEGORITE][numriLojtareve];
		
		
		for(int i = 0; i < numriLojtareve; i++) {
			scorePanel[i] = new JPanel();
			scorePanel[i].setLayout(new BoxLayout(scorePanel[i], BoxLayout.Y_AXIS));
			
			scoreLabels[0][i] = new JLabel(emri[i]); // vendos ne elementin e pare te cdo kolone emrin e lojtarit
			scoreLabels[0][i].setHorizontalAlignment(SwingConstants.CENTER);
			scoreLabels[0][i].setPreferredSize(new Dimension(100, 50));
			scoreLabels[0][i].setMaximumSize(new Dimension(100, 50));
			scorePanel[i].add(scoreLabels[0][i]);
			
			for(int j = 1; j < KATEGORITE; j++) {
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
