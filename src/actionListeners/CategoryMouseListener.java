package actionListeners;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;


public class CategoryMouseListener extends MouseAdapter {
	
	private int playerIndex;
	private int shuma;
	
	public CategoryMouseListener(int index) {
		playerIndex = index;
	}
	
	
	public void mouseClicked (MouseEvent e) {
		
		JLabel source = (JLabel) e.getSource();
		
		if(source.getText() == "Njesha ") {
			
			
			
		}
		
	}
	
}
