package yahtzeeGame;

import java.sql.SQLException;

public class TestDBConnector {
    /**
     * Connect to the DB and do some stuff
     */
    public static void main(String[] args) {
        DBConnector app = null;
        try {
            app = new DBConnector();
        } catch (SQLException e) {
            System.out.println("ERROR: Could not connect to the database");
            e.printStackTrace();
        }

        app.createTables();

        app.close();
    }

}
