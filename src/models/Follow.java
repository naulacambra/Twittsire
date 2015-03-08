package models;

import java.sql.ResultSet;
import java.util.ArrayList;

import utils.DAO;

public class Follow {
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

	public boolean exists(){
		try {
			DAO database = new DAO();
			ResultSet result = database
					.executeSelectSQL("SELECT * FROM Follow f WHERE idUserFollowed = "
							+ this.idUserFollowed + " AND idUserFollower = " + this.idUserFollower);
			if (result.first())
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete() {
		try {
			DAO database = new DAO();
			database
					.executeInsertSQL("DELETE FROM Follow WHERE idUserFollower = "
							+ this.idUserFollower + " AND idUserFollowed = " + this.idUserFollowed);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static ArrayList<Follow> getFollowers(String username) {
		ArrayList<Follow> followers = new ArrayList<Follow>();
		try {
			DAO database = new DAO();
			ResultSet result = database
					.executeSelectSQL("SELECT * FROM Follow f LEFT JOIN User u ON f.idUserFollowed = u.idUser AND u.username = '"
							+ username + "'");
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

	public static ArrayList<Follow> getFollowings(String username) {
		ArrayList<Follow> followings = new ArrayList<Follow>();
		try {
			DAO database = new DAO();
			ResultSet result = database
					.executeSelectSQL("SELECT * FROM Follow f LEFT JOIN User u ON f.idUserFollower = u.idUser AND u.username = '"
							+ username + "'");
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
