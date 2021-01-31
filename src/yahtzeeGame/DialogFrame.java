package yahtzeeGame;

import actionListeners.SignInActionListener;
import actionListeners.SignUpActionListener;

import javax.swing.*;
import java.awt.*;

public class DialogFrame {

    private JDialog dialogFrame;
    private JButton signIn, signUp, imageButton;
    private Loja loja;



    public DialogFrame(Loja loja) {
        this.loja = loja;
    }


    public JDialog getDialogFrame() {
        return dialogFrame;
    }

    public void initDialogFrame() {

        dialogFrame = new JDialog();
        dialogFrame.setLayout(new FlowLayout());
        dialogFrame.setTitle("Regjistrimi i lojtarit " + (loja.getLojtaret().size() + 1));

        Icon yahtzeeImage = new ImageIcon("src/images/yahtzeePhoto.jpg");

        imageButton = new JButton(yahtzeeImage);

        signIn = new JButton("Sign In");
        signUp = new JButton("Sign Up");

        signIn.addActionListener(new SignInActionListener(dialogFrame, loja));
        signUp.addActionListener(new SignUpActionListener(dialogFrame, loja));

        dialogFrame.add(imageButton);
        dialogFrame.add(signIn);
        dialogFrame.add(signUp);

        dialogFrame.setSize(400, 300);
        dialogFrame.setVisible(true);

    }
}
