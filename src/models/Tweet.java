package models;

import java.sql.ResultSet;
import java.util.ArrayList;

import utils.DAO;

public class Tweet {
	private String text;
	private int idUser;
	private User user;
	private int idTweet;
	private int rate;

	public String[] definition = { "text", "idUser" };

	public Tweet(String text, int idUser) {
		this.text = text;
		this.idUser = idUser;
		this.rate = 0;
	}
	
	public Tweet(String text, int idUser, User user, int idTweet){
		this(text, idUser);
		this.user = user;
		this.idTweet = idTweet;
		this.rate = 0;
	}
	
	public Tweet(String text, int idUser, User user, int idTweet, int rate) {
		this(text, idUser, user, idTweet);
		this.rate = rate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	public User getUser(){
		return this.user;
	}

	public int getIdTweet() {
		return idTweet;
	}

	public void setIdTweet(int idTweet) {
		this.idTweet = idTweet;
	}
	
	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public static ArrayList<Tweet> getTweets() {
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		try {
			DAO database = new DAO();
			ResultSet result = database
					.executeSelectSQL("SELECT t.*, u.username FROM Tweet t LEFT JOIN User u ON t.idUser = u.idUser ORDER BY t.created_at DESC");
			while (result.next()) {
				User tempUser = new User();
				tempUser.loadUser("username", result.getString("username"));
				Tweet tempTweet = new Tweet(result.getString("text"),
						result.getInt("idUser"), tempUser, result.getInt("idTweet"));
				tweets.add(tempTweet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tweets;
	}
	
	public static ArrayList<Tweet> getTweets(int idUser) {
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		try {
			DAO database = new DAO();
			ResultSet result = database
					.executeSelectSQL("SELECT t.*, u.username, r.rate FROM Tweet t LEFT JOIN User u ON t.idUser = u.idUser LEFT JOIN Rating r ON r.idTweet = t.idTweet AND r.idUser = " + idUser + " ORDER BY t.created_at DESC");
			while (result.next()) {
				User tempUser = new User();
				tempUser.loadUser("username", result.getString("username"));
				Tweet tempTweet = new Tweet(result.getString("text"),
						result.getInt("idUser"), tempUser, result.getInt("idTweet"), result.getInt("rate"));
				tweets.add(tempTweet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tweets;
	}
	
	public static ArrayList<Tweet> getTweets(String username) {
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		try {
			DAO database = new DAO();
			ResultSet result = database
					.executeSelectSQL("SELECT t.*, u.username FROM Tweet t LEFT JOIN User u ON t.idUser = u.idUser WHERE u.username = '" + username + "' ORDER BY t.created_at DESC");
			while (result.next()) {
				User tempUser = new User();
				tempUser.loadUser("username", result.getString("username"));
				Tweet tempTweet = new Tweet(result.getString("text"),
						result.getInt("idUser"), tempUser, result.getInt("idTweet"), 0);
				tweets.add(tempTweet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tweets;
	}
	
	public static ArrayList<Tweet> getTweets(int idUser, String username) {
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		try {
			DAO database = new DAO();
			ResultSet result = database
					.executeSelectSQL("SELECT t.*, u.username, r.rate FROM Tweet t LEFT JOIN User u ON t.idUser = u.idUser LEFT JOIN Rating r ON r.idTweet = t.idTweet AND r.idUser = " + idUser + " WHERE u.username = '" + username + "' ORDER BY t.created_at DESC");
			while (result.next()) {
				User tempUser = new User();
				tempUser.loadUser("username", result.getString("username"));
				Tweet tempTweet = new Tweet(result.getString("text"),
						result.getInt("idUser"), tempUser, result.getInt("idTweet"), result.getInt("rate"));
				tweets.add(tempTweet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tweets;
	}
}
