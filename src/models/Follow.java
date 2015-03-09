// Aquesta classe cont� tot lo que fa refer�ncia a les relacions entre usuaris, els follows
// Treballa amb directament amb la base de dades

package models;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

import utils.DAO;

public class Follow implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idUserFollower;
	private int idUserFollowed;

	public String[] definition = { "idUserFollower", "idUserFollowed" };

	public Follow(int idUserFollower, int idUserFollowed) {
		this.setIdUserFollower(idUserFollower);
		this.setIdUserFollowed(idUserFollowed);
	}

	/**
	 * @return the idUserFollower
	 */
	public int getIdUserFollower() {
		return idUserFollower;
	}

	/**
	 * @param idUserFollower
	 *            the idUserFollower to set
	 */
	public void setIdUserFollower(int idUserFollower) {
		this.idUserFollower = idUserFollower;
	}

	/**
	 * @return the idUserFollowed
	 */
	public int getIdUserFollowed() {
		return idUserFollowed;
	}

	/**
	 * @param idUserFollowed
	 *            the idUserFollowed to set
	 */
	public void setIdUserFollowed(int idUserFollowed) {
		this.idUserFollowed = idUserFollowed;
	}

	// M�tode que comprobar� si existeix la relaci� de follow a la base de dades
	public boolean exists() {
		try {
			DAO database = new DAO();
			ResultSet result = database
					.executeSelectSQL("SELECT * FROM Follow f WHERE idUserFollowed = "
							+ this.idUserFollowed
							+ " AND idUserFollower = "
							+ this.idUserFollower);
			if (result.first())
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// M�tode per esborrar un follow
	public boolean delete() {
		try {
			DAO database = new DAO();
			database.executeInsertSQL("DELETE FROM Follow WHERE idUserFollower = "
					+ this.idUserFollower
					+ " AND idUserFollowed = "
					+ this.idUserFollowed);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// M�tode per mostrar el llistat de followers d'un usuari
	public static ArrayList<Follow> getFollowers(int idUser) {
		ArrayList<Follow> followers = new ArrayList<Follow>();
		try {
			DAO database = new DAO();
			ResultSet result = database
					.executeSelectSQL("SELECT * FROM Follow f WHERE f.idUserFollowed = " + idUser);
			while (result.next()) {
				Follow tempFollower = new Follow(
						result.getInt("idUserFollower"),
						result.getInt("idUserFollowed"));
				followers.add(tempFollower);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return followers;
	}

	// M�tode per mostrar el llistat de followings d'un usuari
	public static ArrayList<Follow> getFollowings(int idUser) {
		ArrayList<Follow> followings = new ArrayList<Follow>();
		try {
			DAO database = new DAO();
			ResultSet result = database.executeSelectSQL("SELECT * "
					+ "FROM Follow f  WHERE f.idUserFollower = " + idUser);
			while (result.next()) {
				Follow tempFollower = new Follow(
						result.getInt("idUserFollower"),
						result.getInt("idUserFollowed"));
				followings.add(tempFollower);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return followings;
	}

}
