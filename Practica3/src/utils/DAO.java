package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.BeanLogin;
import models.BeanUser;

public class DAO {

	public DAO() {

	}

	public boolean isNewUser(BeanUser user) {
		System.out.println("comprovant isNewUser - 0");
		if (user.getError()[BeanUser.ErrorList.ERROR_USERNAME.getValue()] != 0
				|| user.getError()[BeanUser.ErrorList.ERROR_MAIL.getValue()] != 0)
			return false; // si els camps estan buits sortim de la funció

		DDBBHandler db = null;
		ResultSet isUsernameRepeated = null; // number of coincidences
		ResultSet isMailRepeated = null; // if it's different from 0 -> user
											// already in DB

		try {
			db = new DDBBHandler();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			isUsernameRepeated = (ResultSet) db
					.executeSQL("SELECT COUNT(*) AS isUsernameRepeated FROM twittsire.users WHERE username = '"
							+ user.getUsername() + "'");
			if (isUsernameRepeated.next()) {
				int repetitions = Integer.parseInt(isUsernameRepeated
						.getString("isUsernameRepeated"));
				if (repetitions != 0)
					user.setError(BeanUser.ErrorList.ERROR_USERNAME.getValue());
			}

			isMailRepeated = (ResultSet) db
					.executeSQL("SELECT COUNT(*) AS isMailRepeated FROM twittsire.users WHERE mail = '"
							+ user.getMail() + "'");
			if (isMailRepeated.next()) {
				int repetitions = Integer.parseInt(isMailRepeated
						.getString("isMailRepeated"));
				if (repetitions != 0)
					user.setError(BeanUser.ErrorList.ERROR_MAIL.getValue());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			db.disconnectBD(); // close the DB connection
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("comprovant isNewUser - te errors? "
				+ user.hasErrors());
		if (!user.hasErrors())
			return true;
		return false;
	}

	public boolean isNewUsername(String username) {
		try {
			DDBBHandler db = new DDBBHandler();
			ResultSet isUsernameRepeated = (ResultSet) db
					.executeSQL("SELECT COUNT(*) AS isUsernameRepeated FROM twittsire.users WHERE username = '"
							+ username + "'");
			if (isUsernameRepeated.next()) {
				int repetitions = Integer.parseInt(isUsernameRepeated
						.getString("isUsernameRepeated"));
				if (repetitions != 0) {
					db.disconnectBD();
					return false;
				} else {
					db.disconnectBD();
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean isNewMail(String mail) {
		try {
			DDBBHandler db = new DDBBHandler();
			ResultSet isMailRepeated = (ResultSet) db
					.executeSQL("SELECT COUNT(*) AS isMailRepeated FROM twittsire.users WHERE mail = '"
							+ mail + "'");
			if (isMailRepeated.next()) {
				int repetitions = Integer.parseInt(isMailRepeated
						.getString("isMailRepeated"));
				if (repetitions != 0) {
					db.disconnectBD();
					return false;
				} else {
					db.disconnectBD();
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public void insertUser(BeanUser user) {
		DDBBHandler db = null;
		try {
			db = new DDBBHandler();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			db.executeSQLUpdate("INSERT INTO twittsire.users (fullName, username, mail, pwd) VALUES ('"
					+ user.getFullName()
					+ "','"
					+ user.getUsername()
					+ "','"
					+ user.getMail() + "','" + user.getPwd() + "')");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			db.disconnectBD(); // close the DB connection
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean doLogin(BeanLogin user) {
		String username = user.getUsername();
		if (!isNewUsername(username)) {
			try {
				DDBBHandler db = new DDBBHandler();
				ResultSet checkInfo = (ResultSet) db
						.executeSQL("SELECT pwd AS userPwd FROM twittsire.users WHERE username = '"
								+ username + "'");
				if (checkInfo.next()) {
					String pwd = checkInfo.getString("userPwd");
					System.out.println("el password es: " + pwd);
					if (pwd.equals(user.getPwd())) {
						System.out.println("el password CONCORDA");
						db.disconnectBD();
						return true;
					} else {
						System.out.println("el password NO CONCORDA");
						user.setError(BeanLogin.ErrorList.ERROR_PWD.getValue());
						db.disconnectBD();
						return false;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			user.setError(BeanLogin.ErrorList.ERROR_USERNAME.getValue());
			return false;
		}
		return false;
	}

	// ///////////////////////////////////////////

	private class DDBBHandler {
		private Connection connection;
		private Statement statement;

		public DDBBHandler() throws Exception {
			String user = "consulter";
			String password = "qwerty";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager
					.getConnection("jdbc:mysql://localhost/twittsire?user="
							+ user + "&password=" + password);
			statement = connection.createStatement();
		}

		// execute queries
		public ResultSet executeSQL(String query) throws SQLException {
			return statement.executeQuery(query);
		}

		// update queries
		public void executeSQLUpdate(String query) throws SQLException {
			statement.executeUpdate(query);
		}

		// disconect BDHandler
		public void disconnectBD() throws SQLException {
			statement.close();
			connection.close();
		}
	}

}