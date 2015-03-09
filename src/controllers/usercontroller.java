package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.User;
import utils.DAO;
import utils.JSON;

/**
 * Servlet implementation class usercontroller
 */
@WebServlet("/usercontroller")
public class usercontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public usercontroller() {
		super();
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
		User user = (User) session.getAttribute("user");
		if (user != null) {
			// Comprovem quina accio s'ens ha demanat amb la variable "action"
			switch (request.getParameter("action")) {
			case "editUser":
				try {
					result.addPair("success", true);
					user.setName(request.getParameter("name"));
					user.setSurname(request.getParameter("surname"));
					user.setPassword(request.getParameter("password"));
					DAO database = new DAO();
					database.updateObject(user);
					session.setAttribute("user", user);
				} catch (Exception e) {

				}
				break;
			}

		} else {
			result.addPair("success", false);
		}
		// Escrivim en la resposta les dades en format JSON
		response.getWriter().write(result.toString());
	}
}
