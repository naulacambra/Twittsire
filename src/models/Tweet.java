package models;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

import utils.DAO;

public class Tweet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String text;
	private int idUser;
	private Integer idTweetOrigin;
	private Integer idTweetParent;
	private User user;
	private int idTweet;
	private int rate;
	private int commentCount;

	public String[] definition = { "text", "idUser", "idTweetOrigin", "idTweetParent" };

	public Tweet() {
	}

	public Tweet(String text, int idUser) {
		this.text = text;
		this.idUser = idUser;
		this.rate = 0;
	}

	public Tweet(String text, int idUser, User user, int idTweet) {
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

	public User getUser() {
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

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getIdTweetOrigin() {
		return idTweetOrigin;
	}

	public void setIdTweetOrigin(Integer idTweetOrigin) {
		this.idTweetOrigin = idTweetOrigin;
	}

	public Integer getIdTweetParent() {
		return idTweetParent;
	}

	public void setIdTweetParent(Integer idTweetParent) {
		this.idTweetParent = idTweetParent;
	}

	public boolean loadTweet(int idTweet) {
		try {
			DAO database = new DAO();
			ResultSet result = database
					.executeSQL("SELECT * FROM Tweet WHERE idTweet = "
							+ idTweet);
			if (result.first()) {
				this.setText(result.getString("text"));
				this.setIdUser(result.getInt("idUser"));
				this.setIdTweet(idTweet);
				this.setRate(result.getInt("rate"));
				return true;
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete() {
		try {
			DAO database = new DAO();
			database.executeInsertSQL("DELETE FROM Tweet WHERE idTweet = "
					+ this.idTweet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*Get all tweets for unregistered users*/
	public static ArrayList<Tweet> getTweets() {
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		try {
			DAO database = new DAO();
			ResultSet result = database
					.executeSelectSQL("SELECT t.*, u.username, COUNT(c.idTweet) as comment_count FROM Tweet t LEFT JOIN User u ON t.idUser = u.idUser LEFT JOIN tweet c ON t.idTweet = c.idTweetParent WHERE t.idTweetParent IS NULL GROUP BY (IFNULL(c.idTweetParent, t.idTweet)) ORDER BY t.created_at DESC");
			while (result.next()) {
				User tempUser = new User();
				tempUser.loadUser("username", result.getString("username"));
				Tweet tempTweet = new Tweet(result.getString("text"),
						result.getInt("idUser"), tempUser,
						result.getInt("idTweet"));
				tempTweet.setCommentCount(result.getInt("comment_count"));
				tweets.add(tempTweet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tweets;
	}
	/*Get tweets and their rates from the logged user*/
	public static ArrayList<Tweet> getTweets(int idUser) {
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		try {
			DAO database = new DAO();
			ResultSet result = database
					.executeSelectSQL("SELECT t.*, u.username, r.rate, COUNT(c.idTweet) as comment_count "
							+ "FROM Tweet t "
							+ "LEFT JOIN User u "
							+ "ON t.idUser = u.idUser " 
							+ "LEFT JOIN Rating r "
							+ "ON r.idTweet = t.idTweet AND r.idUser = " + idUser + " "
							+ "LEFT JOIN tweet c "
							+ "ON t.idTweet = c.idTweetParent "
							+ "WHERE t.idTweetParent IS NULL "
							+ "GROUP BY (IFNULL(c.idTweetParent, t.idTweet)) "		
							+ "ORDER BY t.created_at DESC");
			while (result.next()) {
				User tempUser = new User();
				tempUser.loadUser("username", result.getString("username"));
				Tweet tempTweet = new Tweet(result.getString("text"),
						result.getInt("idUser"), tempUser,
						result.getInt("idTweet"), result.getInt("rate"));
				tempTweet.setCommentCount(result.getInt("comment_count"));
				tweets.add(tempTweet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tweets;
	}
	/*Get tweets from specific user*/
	public static ArrayList<Tweet> getTweets(String username) {
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		try {
			DAO database = new DAO();
			ResultSet result = database
					.executeSelectSQL("SELECT t.*, u.username, COUNT(c.idTweet) as comment_count "
							+ "FROM Tweet t "
							+ "LEFT JOIN User u "
							+ "ON t.idUser = u.idUser "
							+ "LEFT JOIN tweet c "
							+ "ON t.idTweet = c.idTweetParent "
							+ "WHERE u.username = '" + username + "' "
							+ "AND t.idTweetParent IS NULL "
							+ "GROUP BY (IFNULL(c.idTweetParent, t.idTweet))"
							+ "ORDER BY t.created_at DESC");
			while (result.next()) {
				User tempUser = new User();
				tempUser.loadUser("username", result.getString("username"));
				Tweet tempTweet = new Tweet(result.getString("text"),
						result.getInt("idUser"), tempUser,
						result.getInt("idTweet"), 0);
				tempTweet.setCommentCount(result.getInt("comment_count"));
				tweets.add(tempTweet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tweets;
	}
	/**/
	public static ArrayList<Tweet> getTweets(int idUser, String username) {
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		try {
			DAO database = new DAO();
			ResultSet result = database
					.executeSelectSQL("SELECT t.*, u.username, r.rate, COUNT(c.idTweet) as comment_count "
							+ "FROM Tweet t " 
							+ "LEFT JOIN User u "
							+ "ON t.idUser = u.idUser " 
							+ "LEFT JOIN Rating r "
							+ "ON r.idTweet = t.idTweet AND r.idUser = " + idUser + " "
							+ "LEFT JOIN tweet c "
							+ "ON t.idTweet = c.idTweetParent " 
							+ "WHERE u.username = '" + username + "' "
							+ "AND t.idTweetParent IS NULL "
							+ "GROUP BY (IFNULL(c.idTweetParent, t.idTweet)) " 
							+ "ORDER BY t.created_at DESC");
			while (result.next()) {
				User tempUser = new User();
				tempUser.loadUser("username", result.getString("username"));
				Tweet tempTweet = new Tweet(result.getString("text"),
						result.getInt("idUser"), tempUser,
						result.getInt("idTweet"), result.getInt("rate"));
				tempTweet.setCommentCount(result.getInt("comment_count"));
				tweets.add(tempTweet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tweets;
	}

	public static ArrayList<Tweet> getTweetsFromFollowings(int idUser) {
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		try {
			DAO database = new DAO();
			ResultSet result = database
					.executeSelectSQL("SELECT t.*, u.username, r.rate, COUNT(c.idTweet) as comment_count "
							+ "FROM Follow f "
							+ "LEFT JOIN Tweet t "
							+ "ON t.idUser = f.idUserFollowed "
							+ "LEFT JOIN User u "
							+ "ON u.idUser = f.idUserFollowed "
							+ "LEFT JOIN Rating r "
							+ "ON r.idTweet = t.idTweet AND r.idUser = f.idUserFollower "
							+ "LEFT JOIN tweet c "
							+ "ON t.idTweet = c.idTweetParent "
							+ "WHERE f.idUserFollower = " + idUser + " "
							+ "AND t.idTweetParent IS NULL "
							+ "GROUP BY (IFNULL(c.idTweetParent, t.idTweet)) "
							+ "ORDER BY t.created_at DESC");
			while (result.next()) {
				User tempUser = new User();
				tempUser.loadUser("username", result.getString("username"));
				Tweet tempTweet = new Tweet(result.getString("text"),
						result.getInt("idUser"), tempUser,
						result.getInt("idTweet"), result.getInt("rate"));
				tempTweet.setCommentCount(result.getInt("comment_count"));
				tweets.add(tempTweet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tweets;
	}
}
