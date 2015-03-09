package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Rating;
import models.User;
import utils.DAO;
import utils.JSON;

/**
 * Aquest servlet s'encarrega de gestionar les crides per valorar els tweets
 */
@WebServlet("/ratingcontroller")
public class ratingcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ratingcontroller() {
		super();
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
		if (session != null) {
			User user = (User) session.getAttribute("user");
			if (user != null) {
				// Comprovem quina accio s'ens ha demanat amb la variable "action"
				Rating rate = null;
				switch (request.getParameter("action")) {
				// En cas de que es vulgui valorar un tweet
				case "rateTweet":
					// Si hem arribat fins aqui, donem per bona la petició AJAX
					result.addPair("success", true);
					// Mirem si s'ha fet una valoració d'un tweet especific, d'un usuari concret
					rate = new Rating(Integer.valueOf(request
							.getParameter("tweet")), user.getIdUser(),
							Integer.valueOf(request.getParameter("rate")));
					// Si aquesta valoració existeix, actualitzem la valoració total del tweet
					if (rate.exists()) {
						rate.update();
					} else {
						// Si no existeix aquesta valoració, la creem de nova 
						DAO database;
						try {
							database = new DAO();
							database.saveObject(rate);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					break;
				// En cas de que es vulgui deixar de valorar un tweet (unlike tweet)
				case "unrateTweet":
					// Si hem arribat fins aqui, donem per bona la petició AJAX
					result.addPair("success", true);
					// Mirem si s'ha fet una valoració d'un tweet especific, d'un usuari concret
					rate = new Rating(Integer.valueOf(request
							.getParameter("tweet")), user.getIdUser(),
							Integer.valueOf(request.getParameter("rate")));
					// Si aquesta valoració existeix, la esborrem
					if (rate.exists()) {
						rate.delete();
					}
					break;
				}
			} else {
				// Si arribem aqui, la petició AJAX no s'ha processat correctament
				result.addPair("success", false);
			}
		} else {
			// Si arribem aqui, la petició AJAX no s'ha processat correctament
			result.addPair("success", false);
		}
		// Escrivim en la resposta les dades en format JSON
		response.getWriter().write(result.toString());
	}

}
