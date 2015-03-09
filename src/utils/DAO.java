package utils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Arrays;

import managers.StringManager;

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

	public boolean saveObject(Object o) {
		Class<?> clazz;
		String[] definition, values;
		String query = "";
		try {
			clazz = Class.forName(o.getClass().getName());
			definition = ((String[]) (clazz.getField("definition").get(o)));
			values = new String[definition.length];
			query = "INSERT INTO "
					+ o.getClass().getSimpleName()
					+ "("
					+ StringManager.removeFirstAndLast(Arrays
							.asList(definition).toString()) + ") VALUES (";
			for (int i = 0; i < definition.length; i++) {
				switch (clazz
						.getMethod(
								"get" + StringManager.capitalize(definition[i]))
						.getReturnType().getSimpleName()) {
				case "int":
				case "Integer":
					try {
						values[i] = clazz
								.getMethod(
										"get"
												+ StringManager
														.capitalize(definition[i]))
								.invoke(o).toString();
					} catch (java.lang.NullPointerException e) {
						values[i] = "null";
					}
					break;
				case "boolean":
					values[i] = Boolean.valueOf(clazz
							.getMethod(
									"get"
											+ StringManager
													.capitalize(definition[i]))
							.invoke(o).toString()) ? "1" : "0";
					break;
				case "String":
				default:
					values[i] = "\""
							+ clazz.getMethod(
									"get"
											+ StringManager
													.capitalize(definition[i]))
									.invoke(o).toString() + "\"";
				}
			}
			query += StringManager.removeFirstAndLast(Arrays.asList(values)
					.toString()) + ")";
			executeInsertSQL(query);
			return true;
		} catch (ClassNotFoundException | NoSuchFieldException
				| SecurityException | IllegalArgumentException
				| IllegalAccessException | InvocationTargetException
				| NoSuchMethodException | SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void disconnectBD() throws SQLException {
		statement.close();
		connection.close();
	}
}