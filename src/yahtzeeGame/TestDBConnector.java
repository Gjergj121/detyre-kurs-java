package yahtzeeGame;

import java.sql.SQLException;
import java.util.List;

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

        //app.insertLojtari(new Lojtar("Joan", "Plepi", 10));

        app.insertLoja();
        int id = app.selectCurrentLoja();
        System.out.println(id);
        Lojtar lojtar = app.selectLojtariByEmri("Joan");

        List<Integer> res = app.selectPiketPerLojtar(1);
        System.out.println(res.get(0));

        System.out.println(lojtar.getId());
        app.close();
    }

}
