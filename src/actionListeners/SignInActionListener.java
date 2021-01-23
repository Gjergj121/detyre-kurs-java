package actionListeners;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import java.awt.*;

public class SignInActionListener implements ActionListener  {

	JDialog dialogFrame;
	
	public SignInActionListener(JDialog dialogFrame) {
		this.dialogFrame = dialogFrame;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		String emri = JOptionPane.showInputDialog(null, "Emri i lojtarit: "," Sign In", JOptionPane.QUESTION_MESSAGE);
		
		dialogFrame.setVisible(false);
	}
	
}
