package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.DAO;
import utils.Encryption;

public class BeanUser {

	// Definim les variables que contindrà l'usuari i que s'hauran d'omplir
	private String name = "";
	private String surname = "";
	private String username = "";
	private String mail = "";
	private String pwd = "";
	private int[] error = { 0, 0 };
	// Definim un DAO per poder fer consultes a la base de dades
	private static DAO database = null;

	/* Getters */
	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getUsername() {
		return username;
	}

	public String getMail() {
		return mail;
	}

	public String getPwd() {
		return pwd;
	}

	public int[] getError() {
		return error;
	}

	/* Setters */
	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setPwd(String pwd) {
		this.pwd = Encryption.MD5(pwd);
	}

	/* Logic Functions */
	// Comprovem si les dades de l'usuari estan completes abans de guardar-lo a
	// la base de dades
	public boolean isComplete() {
		return (hasValue(getUsername()) && hasValue(getMail())
				&& hasValue(getName()) && hasValue(getSurname()) && hasValue(getPwd()));
	}

	// Funció auxiliar per comprovar si un camp té valor
	private boolean hasValue(String val) {
		return ((val != null) && (!val.equals("")));
	}

	public String toString() {
		return this.name + " " + this.surname + " " + this.username + " "
				+ this.mail + " " + this.pwd;
	}

	// Funció per comprovar si un nom d'usuari està registrat a la base de dades
	static public boolean usernameExists(String username) {
		// Comprovem si ja hem definit la base de dades
		if (database == null)
			try {
				// En cas de que no ho haguem fet, la instanciem
				database = new DAO();
			} catch (Exception e) {
				e.printStackTrace();
				return true;
			}

		try {
			// Fem la consulta a la base de dades
			ResultSet result = database
					.executeSQL("SELECT count(*) as count FROM Users WHERE username = '"
							+ username + "'");
			// Movem el cursor a la primera posició del resultat
			if (result.first())
				// Mirem si la quantitat d'usuaris amb aquest nom d'usuari és
				// mes gran que 0
				if (result.getInt("count") > 0)
					// Si ho és retornem que si que existeix aquest nom d'usuari
					return true;
				else
					// Si no, retornem que no està registrat
					return false;
			else
				return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return true;
		}
	}

	// Funció per comprovar si un mail ja està registrat
	static public boolean mailExists(String mail) {
		// Comprovem si ja hem definit la base de dades
		if (database == null)
			try {
				// En cas de que no ho haguem fet, la instanciem
				database = new DAO();
			} catch (Exception e) {
				e.printStackTrace();
				return true;
			}

		try {
			// Fem la consulta a la base de dades
			ResultSet result = database
					.executeSQL("SELECT count(*) as count FROM Users WHERE mail = '"
							+ mail + "'");
			// Movem el cursor a la primera posició del resultat
			// mes gran que 0
			if (result.first())
				if (result.getInt("count") > 0)
					// Si ho és retornem que si que existeix aquest nom d'usuari
					return true;
				else
					// Si no, retornem que no està registrat
					return false;
			else
				return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return true;
		}
	}

	// Funció per retornar tots els usuaris registrats
	public static ArrayList<BeanUser> getUsers() {
		// Creem un ArrayList de BeanUsers inicialment buit
		ArrayList<BeanUser> users = new ArrayList<BeanUser>();
		// Comprovem si ja hem definit la base de dades
		if (database == null)
			try {
				// En cas de que no ho haguem fet, la instanciem
				database = new DAO();
			} catch (Exception e) {
				e.printStackTrace();
			}
		try {
			// Fem la consulta per obtenir tots els usuaris guardats a la base
			// de dades
			ResultSet result = database.executeSelectSQL("SELECT * FROM Users");
			// Recorrem l'array
			while (result.next()) {
				// Creem un nou BeanUser i li omplim les dades
				BeanUser tempUser = new BeanUser();
				tempUser.setName(result.getString("name"));
				tempUser.setSurname(result.getString("surname"));
				tempUser.setUsername(result.getString("username"));
				tempUser.setMail(result.getString("mail"));
				tempUser.setPwd(result.getString("pwd"));
				// Afegim l'usuari a l'ArrayList
				users.add(tempUser);
			}
			// Retornem l'array d'usuaris
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			return users;
		}
	}

	// Guardem l'usuari a la base de dades
	public void saveUser() {
		// Comprovem si ja hem definit la base de dades
		if (database == null)
			try {
				// En cas de que no ho haguem fet, la instanciem
				database = new DAO();
			} catch (Exception e) {
				e.printStackTrace();
			}

		try {
			// Generem la query per guardar l'usuari
			database.executeInsertSQL("INSERT INTO Users (`name`, surname, username, mail, pwd) "
					+ "VALUES ('"
					+ name
					+ "', '"
					+ surname
					+ "', '"
					+ username
					+ "', '" + mail + "', '" + pwd + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
