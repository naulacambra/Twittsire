// Aquesta classe conté tot el que fa referencia a les valoracions dels tweets

package models;

import java.sql.ResultSet;

import utils.DAO;

public class Rating {
	private int idTweet;
	private int idUser;
	private int rate;

	public String[] definition = { "idTweet", "idUser", "rate" };

	public Rating(int idTweet, int idUser, Integer rate) {
		this.idTweet = idTweet;
		this.idUser = idUser;
		this.rate = rate;
	}

	public Rating(Integer idTweet, int idUser, Integer rate) {
		this.idTweet = idTweet;
		this.idUser = idUser;
		this.rate = rate;
	}

	public int getIdTweet() {
		return idTweet;
	}

	public void setIdTweet(int idTweet) {
		this.idTweet = idTweet;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	// Mètode que comprobarà si existeix la relació de rating a la base de dades 
	public boolean exists() {
		try {
			DAO database = new DAO();
			ResultSet result = database
					.executeSelectSQL("SELECT * FROM Rating r WHERE idUser = "
							+ this.idUser + " AND idTweet = " + this.idTweet);
			if (result.first())
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Mètode per esborrar una valoració
	public boolean delete() {
		try {
			DAO database = new DAO();
			database
					.executeInsertSQL("DELETE FROM Rating WHERE idUser = "
							+ this.idUser + " AND idTweet = " + this.idTweet);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// Mètode per actualitzar les valoracions
	public void update() {
		try {
			DAO database = new DAO();
			database.executeInsertSQL("UPDATE Rating r SET rate = " + this.rate
					+ " WHERE idUser = " + this.idUser + " AND idTweet = "
					+ this.idTweet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
