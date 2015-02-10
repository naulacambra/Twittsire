package utils;

import java.sql.*;

//Classe per poder fer les consultes a la base de dades
public class DAO {
	private Connection connection;
	private Statement statement;

	public DAO() throws Exception {
		String user = "mysql";
		String password = "prac";
		String database = "twittsire";
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection("jdbc:mysql://localhost/"
				+ database + "?user=" + user + "&password=" + password);
		statement = connection.createStatement();
	}

	// execute queries
	public ResultSet executeSQL(String query) throws SQLException {
		return statement.executeQuery(query);
	}

	public ResultSet executeSelectSQL(String query) throws SQLException {
		statement = connection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result = statement.executeQuery(query);
		statement = connection.createStatement();
		return result;
	}

	public void executeInsertSQL(String query) throws SQLException {
		statement.execute(query);
	}

	// TODO: code for updates for Assignments 2, 3 and 4.
	public void disconnectBD() throws SQLException {
		statement.close();
		connection.close();
	}
}