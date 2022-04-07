package org.dzsystems.dao.implement;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseHandler {

//	private final static Logger LOGGER = LoggerFactory.getLogger(DataBaseHandler.class);
	private static Connection connection;

	public static Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
				System.exit(1);
			}
			File dbfile = new File("");
			String url = "jdbc:sqlite:" + dbfile.getAbsolutePath() + "/test.db";
			try {
				connection = DriverManager.getConnection(url);
				initialise();
				System.out.println("Connection to database is established. Tables are created.");
				System.out.println("Database file: " + url);
			} catch (SQLException ex) {
				ex.printStackTrace();
				System.exit(1);
			}
		}
		return connection;
	}

	public static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
				System.out.println("Connection to database is closed.");
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	private static void initialise() throws SQLException {
		Statement statement = connection.createStatement();
		boolean wellCreated = statement.execute(
				"CREATE TABLE IF NOT EXISTS wells (" +
						"id INTEGER PRIMARY KEY AUTOINCREMENT ," +
						"name VARCHAR (32) NOT NULL UNIQUE )");

		boolean equipCreated = statement.execute(
				"CREATE TABLE IF NOT EXISTS equipment " +
						"( id INTEGER PRIMARY KEY AUTOINCREMENT ," +
						"   name VARCHAR (32) NOT NULL UNIQUE ," +
						"   well_id INTEGER ," +
						"   FOREIGN KEY (well_id) REFERENCES well (id))");
	}
}
