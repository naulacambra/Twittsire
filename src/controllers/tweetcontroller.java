package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Follow;
import models.Tweet;
import models.User;
import utils.DAO;
import utils.JSON;

/**
 * Servlet implementation class tweetcontroller
 */
@WebServlet("/tweetcontroller")
public class tweetcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public tweetcontroller() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		JSON result = new JSON();
		// Definim les capceleres per definir el format de resposta
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		// Comprovem quina accio s'ens ha demanat amb la variable "action"
		switch (request.getParameter("action")) {
		case "getTweets":
			ArrayList<Tweet> tweets = new ArrayList<Tweet>();
			ArrayList<Follow> followings = new ArrayList<Follow>();
			result.addPair("success", true);
			User user = (User) session.getAttribute("user");
			if (user != null) {
				followings = Follow.getFollowings(user.getIdUser());
			}
			switch (request.getParameter("scoope")) {
			case "global":
				if (user != null) {
					tweets = Tweet.getTweets(user.getIdUser());
				} else {
					tweets = Tweet.getTweets();
				}
				break;
			case "user":
				if (user != null) {
					tweets = Tweet.getTweets(user.getIdUser(),
							request.getParameter("username"));
				} else {
					tweets = Tweet.getTweets(request.getParameter("username"));
				}
				break;
			case "personal":
				if (user != null) {
					tweets = Tweet.getTweets(user.getIdUser(),
							user.getUsername());
				}
				break;
			case "followings":
				if (user != null) {
					tweets = Tweet.getTweetsFromFollowings(user.getIdUser());
				}
				break;
			}
			result.addPair("tweets_count", tweets.size());
			session.setAttribute("tweets", tweets);
			session.setAttribute("followings", followings);
			break;
		case "createTweet":
			result.addPair("success", true);
			Tweet tempTweet = new Tweet(request.getParameter("tweet_text"),
					((User) session.getAttribute("user")).getIdUser());
			try {
				DAO database = new DAO();
				// Guardem l'objecte Tweet directament a la base de dades
				database.saveObject(tempTweet);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		// User user = (User) session.getAttribute("user");
		// if (user != null) {
		// } else {
		// result.addPair("success", false);
		// }
		// Escrivim en la resposta les dades en format JSON
		response.getWriter().write(result.toString());
	}

}
