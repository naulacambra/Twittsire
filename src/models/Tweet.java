package models;

import java.sql.ResultSet;
import java.util.ArrayList;

import utils.DAO;

public class Tweet {
	private String text;
	private int idUser;
	private User user;
	public String[] definition = { "text", "idUser" };

	public Tweet(String text, int idUser) {
		this.text = text;
		this.idUser = idUser;
	}

	public Tweet(String text, int idUser, User user) {
		this(text, idUser);
		this.user = user;
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
						result.getInt("idUser"), tempUser);
				tweets.add(tempTweet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tweets;
	}
}
