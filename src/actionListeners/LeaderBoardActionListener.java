package actionListeners;

import yahtzeeGame.DBConnector;
import yahtzeeGame.Lojtar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeaderBoardActionListener implements ActionListener {

    private DBConnector dbConnector;

    public LeaderBoardActionListener(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Lojtar lojtar = dbConnector.selectMaxRecord();
        String title = "Leaderboard";
        String message = lojtar == null ? "Asnje loje nuk eshte luajtur." :
                                        lojtar.getEmri() + " ka piket maksimale " + lojtar.getPiketTotal();

        lojtar = dbConnector.selectMinRecord();

        message += "\n " + lojtar.getEmri() + " ka piket minimale " + lojtar.getPiketTotal();

        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
