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
 * Aquest servlet s'encarrega de mostrar els tweets
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
		// Si rebem una petició GET la processem com si fos POST
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
		User user = (User) session.getAttribute("user");
		Tweet tempTweet = new Tweet();
		// Comprovem quina accio s'ens ha demanat amb la variable "action"
		switch (request.getParameter("action")) {
		// En cas de que es vulgui agafar tots els tweets
		case "getTweets":
			// Guardem en dues llistes els tweets i els usuaris followings
			ArrayList<Tweet> tweets = new ArrayList<Tweet>();
			ArrayList<Follow> followings = new ArrayList<Follow>();
			// Si hem arribat fins aqui, donem per bona la petició AJAX
			result.addPair("success", true);
			// Si l'usuari existeix, ens retornarà els seus followings
			if (user != null) {
				followings = Follow.getFollowings(user.getIdUser());
			}
			//Comprovem quina accio s'ens ha demanat amb la variable "scoope"
			switch (request.getParameter("scoope")) {
			// En cas de que estigui seleccionat l'opció "global", mostrarà tots els tweets
			case "global":
				if (user != null) {
					tweets = Tweet.getTweets(user.getIdUser());
				} else {
					tweets = Tweet.getTweets();
				}
				break;
			// En cas de que estigui seleccionat l'opció "user", mostrarà nomès els tweets de l'usuari en concret
			case "user":
				if (user != null) {
					tweets = Tweet.getTweets(user.getIdUser(),
							request.getParameter("username"));
				} else {
					tweets = Tweet.getTweets(request.getParameter("username"));
				}
				break;
			// En cas de que estigui seleccionat l'opció "personal", mostrarà els tweets de l'usuari loguejat
			case "personal":
				if (user != null) {
					tweets = Tweet.getTweets(user.getIdUser(),
							user.getUsername());
				}
				break;
			// En cas de que estigui seleccionat l'opció "personal", mostrarà els tweets dels usuaris als quals es segueix
			case "followings":
				if (user != null) {
					tweets = Tweet.getTweetsFromFollowings(user.getIdUser());
				}
				break;
			}
			result.addPair("tweets_count", tweets.size());
			// Guardem els tweets i els followings en sessió
			session.setAttribute("tweets", tweets);
			session.setAttribute("followings", followings);
			break;
		// En cas de que es vulgui escriure un tweet
		case "createTweet":
			// Si hem arribat fins aqui, donem per bona la petició AJAX
			result.addPair("success", true);
			// Creem el tweet nou
			tempTweet = new Tweet(request.getParameter("tweet_text"),
					((User) session.getAttribute("user")).getIdUser());
			try {
				DAO database = new DAO();
				// Guardem l'objecte Tweet directament a la base de dades
				database.saveObject(tempTweet);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		// En cas de que es vulgui esborrar un tweet
		case "deleteTweet":
			// Si hem arribat fins aqui, donem per bona la petició AJAX
			result.addPair("success", true);
			tempTweet = new Tweet();
			// Carregarem el tweet en questio i l'esborrarem
			tempTweet.loadTweet(Integer.valueOf(request.getParameter("tweet")));
			tempTweet.delete();
			break;
		// En cas de que es vulgui comentar en un tweet
		case "commentTweet":
			if (user != null) {
				try {
					// Si hem arribat fins aqui, donem per bona la petició AJAX
					result.addPair("success", true);
					tempTweet = new Tweet();
					// Definim les variables del tweet
					tempTweet.setIdUser(user.getIdUser());
					tempTweet.setText(request.getParameter("text"));
					tempTweet.setIdTweetParent(Integer.valueOf(request
							.getParameter("tweet")));
					// Guardem a la base de dades
					DAO database = new DAO();
					database.saveObject(tempTweet);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		// En cas de que es vulgui carregar els comentaris d'un tweet
		case "loadComments":
			// Si hem arribat fins aqui, donem per bona la petició AJAX
			result.addPair("success", true);
			// Guardem en una llista tots els comentaris i els mostrem
			ArrayList<Tweet> comments = Tweet.getComments(Integer
					.valueOf(request.getParameter("tweet")));
			session.setAttribute("comments", comments);
			break;
		// En cas de que es vulgui editar un tweet
		case "editTweet":
			// Si hem arribat fins aqui, donem per bona la petició AJAX
			result.addPair("success", true);
			try {
				// Carreguem el tweet en questio i el modifiquem
				tempTweet = new Tweet();
				tempTweet.loadTweet(Integer.valueOf(request
						.getParameter("tweet")));
				tempTweet.setText(request.getParameter("text"));
				// El guardem a la base de dades
				DAO database = new DAO();
				database.updateObject(tempTweet);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		// Escrivim en la resposta les dades en format JSON
		response.getWriter().write(result.toString());
	}
}
