package yahtzeeGame;

import actionListeners.SignInActionListener;
import actionListeners.SignUpActionListener;

import javax.swing.*;
import java.awt.*;

public class DialogFrame {

    private JDialog dialogFrame;
    private JButton signIn, signUp, imageButton;
    private Loja loja;
    private int lojtarIndex;
    private DBConnector dbConnector;



    public DialogFrame(Loja loja, int lojtarIndex, DBConnector dbConnector) {
        System.out.println(lojtarIndex);
        this.lojtarIndex = lojtarIndex;
        this.loja = loja;
        this.dbConnector = dbConnector;
    }

    public DBConnector getDbConnector() {
        return dbConnector;
    }

    public JDialog getDialogFrame() {
        return dialogFrame;
    }

    public void initDialogFrame() {

        dialogFrame = new JDialog();
        dialogFrame.setLayout(new FlowLayout());
        dialogFrame.setTitle("Regjistrimi");

        Icon yahtzeeImage = new ImageIcon("src/images/yahtzeePhoto.jpg");

        imageButton = new JButton(yahtzeeImage);

        signIn = new JButton("Sign In");
        signUp = new JButton("Sign Up");

        signIn.addActionListener(new SignInActionListener(dialogFrame, loja, lojtarIndex, dbConnector));
        signUp.addActionListener(new SignUpActionListener(dialogFrame, loja, lojtarIndex, dbConnector));

        dialogFrame.add(imageButton);
        dialogFrame.add(signIn);
        dialogFrame.add(signUp);

        dialogFrame.setSize(400, 300);
        dialogFrame.setVisible(true);

    }
}
