package controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Rating;
import models.Tweet;
import models.User;
import utils.DAO;
import utils.JSON;

/**
 * Servlet implementation class ratingcontroller
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
				// Comprovem quina accio s'ens ha demanat amb la variable
				// "action"
				Rating rate = null;
				switch (request.getParameter("action")) {
				case "rateTweet":
					result.addPair("success", true);
					rate = new Rating(Integer.valueOf(request
							.getParameter("tweet")), user.getIdUser(),
							Integer.valueOf(request.getParameter("rate")));
					if (rate.exists()) {
						rate.update();
					} else {
						DAO database;
						try {
							database = new DAO();
							database.saveObject(rate);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					break;
				case "unrateTweet":
					result.addPair("success", true);
					rate = new Rating(Integer.valueOf(request
							.getParameter("tweet")), user.getIdUser(),
							Integer.valueOf(request.getParameter("rate")));
					if (rate.exists()) {
						rate.delete();
					}
					break;
				}
			} else {
				result.addPair("success", false);
			}
		} else {
			result.addPair("success", false);
		}
		response.getWriter().write(result.toString());
	}

}
