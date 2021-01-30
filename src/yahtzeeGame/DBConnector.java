package yahtzeeGame;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This class demonstrates how to connect to MySQL and run some basic commands.
 * 
 * In order to use this, you have to download the Connector/J driver and add
 * its .jar file to your build path.  You can find it here:
 * 
 * http://dev.mysql.com/downloads/connector/j/
 * 
 * You will see the following exception if it's not in your class path:
 * 
 * java.sql.SQLException: No suitable driver found for jdbc:mysql://localhost:3306/
 * 
 * To add it to your class path:
 * 1. Right click on your project
 * 2. Go to Build Path -> Add External Archives...
 * 3. Select the file mysql-connector-java-5.1.24-bin.jar
 *    NOTE: If you have a different version of the .jar file, the name may be
 *    a little different.
 *    
 * The user name and password are both "root", which should be correct if you followed
 * the advice in the MySQL tutorial. If you want to use different credentials, you can
 * change them below. 
 * 
 * You will get the following exception if the credentials are wrong:
 * 
 * java.sql.SQLException: Access denied for user 'userName'@'localhost' (using password: YES)
 * 
 * You will instead get the following exception if MySQL isn't installed, isn't
 * running, or if your serverName or portNumber are wrong:
 * 
 * java.net.ConnectException: Connection refused
 */
public class DBConnector {

	private final String userName = "gjergj";

	private final String password = "gjergj";

	private final String serverName = "localhost";

	private final int portNumber = 3306;

	private final String dbName = "yahtzee";
	
	private final String lojtariTableName = "lojtari";
	private final String lojaTableName = "loja";
	private final String piketTableName = "piket";
	private Connection conn;

	public DBConnector() throws SQLException {
		conn = getConnection();
		createTables();
	}

	/**
	 * Get a new database connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		conn = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
				connectionProps);

		return conn;
	}

	/**
	 * Run a SQL command which does not return a recordset:
	 * CREATE/INSERT/UPDATE/DELETE/DROP/etc.
	 * 
	 * @throws SQLException If something goes wrong
	 */
	private boolean executeUpdate(String command) throws SQLException {
	    Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        stmt.executeUpdate(command); // This will throw a SQLException if it fails
	        return true;
	    } finally {

	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }
	}

	private ResultSet executeQuery(String command) throws SQLException {
		Statement stmt = null;
		stmt = conn.createStatement();
		return stmt.executeQuery(command);
	}

	public void createTables() {

		// Create a table
		try {
			createLojtariTable();
			createLojaTable();
			createPiketTable();

			System.out.println("Created tables.");
	    } catch (SQLException e) {
			System.out.println("ERROR: Could not create the table");
			e.printStackTrace();
			return;
		}

	}

	public Lojtar insertPiket(int lojtarId, int lojaId, int piket) {
		String insertQuery = "INSERT * INTO " + this.lojaTableName
				+ " (lojtar_id, loja_id, piket) (" + lojtarId + ", " + lojaId + ", " + piket + ")";

		try {
			executeUpdate(insertQuery);
		} catch (SQLException e) {
			System.out.println("ERROR: Could not execute query.");
			e.printStackTrace();
		}

		return null;
	}

	public Lojtar insertLoja() {
		String insertLoja = "INSERT INTO " + this.lojaTableName + " () VALUES ()";
		System.out.println(insertLoja);
		try {
			executeUpdate(insertLoja);
		} catch (SQLException e) {
			System.out.println("ERROR: Could not execute query.");
			e.printStackTrace();
		}

		return null;
	}

	public void insertLojtari(Lojtar lojtar) {
		String insertQuery = "INSERT INTO " + this.lojtariTableName
				+ " (emri, mbiemri, mosha) VALUES ('" +
				 lojtar.getEmri() + "', '" + lojtar.getMbiemri() + "', " + lojtar.getMosha() + ")";

		System.out.println(insertQuery);
		try {
			executeUpdate(insertQuery);
		} catch (SQLException e) {
			System.out.println("ERROR: Could not execute query.");
			e.printStackTrace();
		}
	}
	public Lojtar selectMinRecord() {
		String selectQuery = "SELECT lojtari_id, SUM(piket) as total from piket GROUP BY lojtari_id ORDER BY total ASC LIMIT 1";

		return executeMinMaxRecord(selectQuery);
	}

	public Lojtar selectMaxRecord() {
		String selectQuery = "SELECT lojtari_id, SUM(piket) as total from piket GROUP BY lojtari_id ORDER BY total DESC LIMIT 1";

		return executeMinMaxRecord(selectQuery);
	}


	public Lojtar executeMinMaxRecord(String selectQuery) {

		try {
			ResultSet rs = executeQuery(selectQuery);

			if (!rs.isBeforeFirst())
				return null;

			rs.next();
			int lojtariId = rs.getInt("lojtari_id");
			rs.close();

			return selectLojtariById(lojtariId);

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return null;
	}

	private Lojtar selectLojtariById(int lojtariId) {
		String selectQuery = "SELECT * FROM " + this.lojtariTableName + " WHERE id=" + lojtariId;
		return selectLojtariQuery(selectQuery);
	}

	public Lojtar selectLojtariByEmri(String emri) {
		String selectQuery = "SELECT * FROM " + this.lojtariTableName + " WHERE emri='" + emri + "'";
		return selectLojtariQuery(selectQuery);
	}

	public List<Integer>  selectPiketPerLojtar(int lojtarId) {
		String selectQuery = "SELECT piket FROM " + this.piketTableName + " WHERE lojtari_id=" + lojtarId;

		List<Integer> results = new ArrayList<>();

		try {
			ResultSet rs = executeQuery(selectQuery);

			while(rs.next()) {
				results.add(rs.getInt("piket"));
				return results;
			}

			rs.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return results;
	}

	public int selectCurrentLoja() {
		String selectQuery = "SELECT MAX(id) FROM " + this.lojaTableName;
		try {
			ResultSet rs = executeQuery(selectQuery);
			rs.next();
			int id = rs.getInt("MAX(id)");
			rs.close();
			return id;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return -1;
	}

	public void insertPike(int lojtarId, int lojaId, int piket) {
		String insertQuery = "INSERT INTO " + this.piketTableName
				+ " (lojtari_id, loja_id, piket) VALUES (" +
				lojtarId + ", " + lojaId + ", " + piket + ")";
		try {
			executeUpdate(insertQuery);
		} catch (SQLException e) {
			System.out.println("ERROR: Could not execute query.");
			e.printStackTrace();
		}
	}

	public Lojtar selectLojtariQuery(String selectQuery) {
		try {
			// Supozojme qe emri eshte unik
			ResultSet rs = executeQuery(selectQuery);

			if (!rs.isBeforeFirst())
				return null;

			rs.next();
			Lojtar lojtar = new Lojtar(rs.getInt("id"), rs.getString("emri"), rs.getString("mbiemri"), rs.getInt("mosha"));
			rs.close();

			lojtar.setPiketEGrumbulluaraNeCdoLoje(selectPiketPerLojtar(lojtar.getId()));
			return lojtar;
		} catch (SQLException e) {
			System.out.println("ERROR: Could not execute query.");
			e.printStackTrace();
		}

		return null;
	}

	private void createLojtariTable() throws SQLException {
		String createString =
				"CREATE TABLE IF NOT EXISTS " + this.lojtariTableName + " ( " +
				"ID INTEGER NOT NULL AUTO_INCREMENT, " +
				"EMRI varchar(40) NOT NULL UNIQUE, " +
				"MBIEMRI varchar(40) NOT NULL, " +
				"MOSHA INT NOT NULL, " +
				"PRIMARY KEY (ID))";

		this.executeUpdate(createString);
	}

	private void createLojaTable() throws SQLException {
		String createString =
				"CREATE TABLE IF NOT EXISTS " + this.lojaTableName + " ( " +
						"ID INTEGER NOT NULL AUTO_INCREMENT, " +
						"PRIMARY KEY (ID))";

		this.executeUpdate(createString);
	}

	private void createPiketTable() throws SQLException {
		String createString =
				"CREATE TABLE IF NOT EXISTS " + this.piketTableName + " ( " +
						"LOJTARI_ID INTEGER NOT NULL, " +
						"LOJA_ID INTEGER NOT NULL, " +
						"PIKET INTEGER NOT NULL, " +
						"FOREIGN KEY (LOJTARI_ID) REFERENCES " + this.lojtariTableName + "(ID)," +
						"FOREIGN KEY (LOJA_ID) REFERENCES " + this.lojaTableName + "(ID)," +
						"PRIMARY KEY (LOJTARI_ID, LOJA_ID))";

		this.executeUpdate(createString);
	}

	public void close() {
		try {
			conn.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}



}