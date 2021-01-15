import java.awt.*;
import javax.swing.*;

public class GUI extends JFrame{
	
	Zari dc;
	JPanel panel, mainPanel, categoryPanel, dicePanel;
	JPanel[] scorePanel;
	JLabel[][] score_label;
	JLabel[] kategoria = new JLabel[18];
	JTextField tekst;
	int numri_lojtareve;
	String[] emri = new String[4];
	
	public GUI(){
		
		dc = new Zari();
		
		fillimiLojes();
		
		panel = new JPanel(new BorderLayout());
		addMainPanel();
		addDicePanel();
		
		add(panel);
	}
	
	
	public void fillimiLojes() {
		
		String n = JOptionPane.showInputDialog(null, "Vendosni numrin e lojtareve (1-4): ", "Loja Yahtzee", JOptionPane.QUESTION_MESSAGE);
		numri_lojtareve = Integer.parseInt(n);
		
		if(numri_lojtareve<1 || numri_lojtareve>4) {
			JOptionPane.showMessageDialog(null, "Loja Yahtzee mund te luhet me 1-4 lojtare!", "Loja Yahtzee", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		for(int i = 0; i < numri_lojtareve; i++) {
			 emri[i] =  JOptionPane.showInputDialog("Emri i lojtarit "+ (i+1) + ": ");
		}
		
	}
	
	
	public void addMainPanel() {
		
		mainPanel = new JPanel(new GridLayout(1, 5, 1, 1));
		addCategoryPanel();
		addScorePanel();
		panel.add(mainPanel, BorderLayout.CENTER);

	}
	
	public void addCategoryPanel() {
		kategoria[0] = new JLabel("Kategorite");
		kategoria[1] = new JLabel("Njesha ");
		kategoria[2] = new JLabel("Dysha ");
		kategoria[3] = new JLabel("Tresha ");
		kategoria[4] = new JLabel("Katra ");
		kategoria[5] = new JLabel("Pesa ");
		kategoria[6] = new JLabel("Gjashta ");
		kategoria[7] = new JLabel("Piket e siperme ");
		kategoria[8] = new JLabel("Bonus (35) ");
		kategoria[9] = new JLabel("Tre me nje vlere ");
		kategoria[10] = new JLabel("Kater me nje vlere ");
		kategoria[11] = new JLabel("Tre dhe Dy (25) ");
		kategoria[12] = new JLabel("Kater te njepasnjeshme (30) ");
		kategoria[13] = new JLabel("Pese te njepasnjeshme (40) ");
		kategoria[14] = new JLabel("E njejta vlere (50) ");
		kategoria[15] = new JLabel("Cdo rast ");
		kategoria[16] = new JLabel("Piket e poshtme ");
		kategoria[17] = new JLabel("TOTAL ");
		
		categoryPanel = new JPanel();
		categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.Y_AXIS));
		categoryPanel.setBackground(Color.CYAN);
		
		kategoria[0].setForeground(Color.RED);
		
		for(int i = 0; i < 18; i++) {
		//	categoryPanel.add(Box.createGlue());
			kategoria[i].setHorizontalAlignment(SwingConstants.CENTER);
			kategoria[i].setPreferredSize(new Dimension(200, 50));
			kategoria[i].setMaximumSize(new Dimension(200, 50));
			categoryPanel.add(kategoria[i]);
		}
		
		mainPanel.add(categoryPanel);
	}
	
	public void addScorePanel() {
		
		scorePanel = new JPanel[numri_lojtareve];
		score_label = new JLabel[18][numri_lojtareve];
		
		
		for(int i = 0; i < numri_lojtareve; i++) {
			scorePanel[i] = new JPanel();
			scorePanel[i].setLayout(new BoxLayout(scorePanel[i], BoxLayout.Y_AXIS));
			
			score_label[0][i] = new JLabel(emri[i]); // vendos ne elementin e pare te cdo kolone emrin e lojtarit
			score_label[0][i].setHorizontalAlignment(SwingConstants.CENTER);
			score_label[0][i].setPreferredSize(new Dimension(100, 50));
			score_label[0][i].setMaximumSize(new Dimension(100, 50));
			scorePanel[i].add(score_label[0][i]);
			
			for(int j = 1; j < 18; j++) {
				score_label[j][i] = new JLabel("-");
				score_label[j][i].setHorizontalAlignment(SwingConstants.CENTER);
				score_label[j][i].setPreferredSize(new Dimension(100, 50));
				score_label[j][i].setMaximumSize(new Dimension(100, 50));
				scorePanel[i].add(score_label[j][i]);
			}
			
			mainPanel.add(scorePanel[i]);
		}	
		
	}
	
	
	public void addDicePanel() {
		
		dicePanel = new JPanel();
		dicePanel.setLayout(new BoxLayout(dicePanel, BoxLayout.X_AXIS));
		dicePanel.setBackground(Color.LIGHT_GRAY);
		
		dicePanel.add(dc.hidhZaratButton);
		
		for(int i = 0; i < 5; i++) {
			dc.buton[i].setPreferredSize(new Dimension(60, 60));
			dc.buton[i].setIcon(dc.dice[i]);
			dicePanel.add(dc.buton[i]);
		}
		
		tekst = new JTextField();
		dicePanel.add(tekst);
		
		panel.add(dicePanel, BorderLayout.SOUTH);
	}
	
}
