import yahtzeeGame.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class Yahtzee {

	public static void main(String[] args) {
		DBConnector dbConnector = null;
		try {
			dbConnector = new DBConnector();
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			System.exit(0);
		}
		
		dbConnector.insertNewLoje();
		int id = dbConnector.selectCurrentLoja();
		int numriLojtareve = InputDialogs.getNumriELojtareve();

		Loja loja = new Loja(id, numriLojtareve);
		loja.setDbConnector(dbConnector);

		DialogFrame dialogFrame = new DialogFrame(loja);
		dialogFrame.initDialogFrame();
	}



}
