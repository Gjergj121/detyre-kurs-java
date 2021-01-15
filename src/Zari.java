import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
 
public class Zari {
	
	private class ZariActionListener implements ActionListener {
	    private int zari;
	    private Icon[] diceIcons;

	    public ZariActionListener(Icon[] diceIcons, int nr) {
	        this.nr = nr;
	        this.diceIcons = diceIcons;
	    }

	    public void actionPerformed(ActionEvent e) {
	    	((JButton) e.getSource()).setIcon(red_dice[i]);
	    }
	}
	
	final Icon[] diceRefactor = {	
							new ImageIcon("C:\\Users\\USER\\Desktop\\Detyra_kursit_java\\zara\\dice1.png"),
							new ImageIcon("C:\\Users\\USER\\Desktop\\Detyra_kursit_java\\zara\\dice2.png"),
							new ImageIcon("C:\\Users\\USER\\Desktop\\Detyra_kursit_java\\zara\\dice3.png"),
							new ImageIcon("C:\\Users\\USER\\Desktop\\Detyra_kursit_java\\zara\\dice4.png"),
							new ImageIcon("C:\\Users\\USER\\Desktop\\Detyra_kursit_java\\zara\\dice5.png"),
							new ImageIcon("C:\\Users\\USER\\Desktop\\Detyra_kursit_java\\zara\\dice6.png")
							};
	
	
	private final Icon[] red_dice = {
						new ImageIcon("C:\\Users\\USER\\Desktop\\Detyra_kursit_java\\zara\\dice1_red.png"),
						new ImageIcon("C:\\Users\\USER\\Desktop\\Detyra_kursit_java\\zara\\dice2_red.png"),
						new ImageIcon("C:\\Users\\USER\\Desktop\\Detyra_kursit_java\\zara\\dice3_red.png"),
						new ImageIcon("C:\\Users\\USER\\Desktop\\Detyra_kursit_java\\zara\\dice4_red.png"),
						new ImageIcon("C:\\Users\\USER\\Desktop\\Detyra_kursit_java\\zara\\dice5_red.png"),
						new ImageIcon("C:\\Users\\USER\\Desktop\\Detyra_kursit_java\\zara\\dice6_red.png")
						};
	
	JButton[] buton;
	JButton hidhZaratButton;
	
	public Zari(){
		
		buton = new JButton[5];
		hidhZaratButton = new JButton("Hidh zarat");
		hidhZaratButton.addActionListener(this);
		
		for(int i = 0; i < 5; i++) {
			buton[i] = new JButton();
		//	buton[i].setText("?");
			buton[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					JButton source = (JButton) event.getSource();
					source.setIcon(red_dice[i]);
				}
			});
		}
		
	}
	
	public void actionPerformed(ActionEvent event) {
		for(int i = 0; i < 5; i++) {
			
			if(event.getSource() == buton[i]) {
				JButton source = (JButton) event.getSource();
				
				if(source.getText() == "?")
					return;
				else if(source.getIcon() == diceRefactor[i])
					source.setIcon(red_dice[i]);
				else
					source.setIcon(diceRefactor[i]);
				
				return;
			}	
		}
		
	}
	
}

