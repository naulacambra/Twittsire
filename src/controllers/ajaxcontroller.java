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
 * Aquest servlet s'encarrega de gestionar les peticions AJAX que es facin
 */
@WebServlet("/ajaxcontroller")
public class ajaxcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ajaxcontroller() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// No tractem les peticions GET
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Per tornar la resposta, creem una instancia de la classe JSON
		JSON result = new JSON();
		// Definim les capceleres per definir el format de resposta
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = null;
		// Comprovem quina accio s'ens ha demanat amb la variable "action"
		switch (request.getParameter("action")) {
		// Comprovar si ja existeix un nom d'usuari
		case "checkUsername":
			// Si hem arribat fins aqui, donem per bona la petició AJAX
			result.addPair("success", true);
			// Comprovem si el nom d'usuari donat existeix
			result.addPair("exists",
					User.usernameExists(request.getParameter("value")));
			break;
		case "checkUsernameAndMail":
			result.addPair("success", true);
			result.addPair(
					"exists",
					!User.usernameExists(request.getParameter("value"))
							&& !User.mailExists(request.getParameter("value")));
			break;
		// Comprovar si ja existeix un mail
		case "checkMail":
			// Si hem arribat fins aqui, donem per bona la petició AJAX
			result.addPair("success", true);
			// Comprovem si el mail donat ja està registrat
			result.addPair("exists",
					User.mailExists(request.getParameter("value")));
			break;
		// En cas de que s'estigui fent un login
		case "login":
			// Si hem arribat fins aqui, donem per bona la petició AJAX
			result.addPair("success", true);
			// Comprovem si s'està omplint el camp del login
			result.addPair(
					"login",
					User.userLoginWithMail(
							request.getParameter("username_mail"),
							request.getParameter("password"))
							|| User.userLoginWithUsername(
									request.getParameter("username_mail"),
									request.getParameter("password")));
			if (Boolean.valueOf(result.getValue("login"))) {
				User user = new User();
				// Si existeix l'email, carreguem les seves dades
				if (User.mailExists(request.getParameter("username_mail")))
					user.loadUser("mail", request.getParameter("username_mail"));
				// Si existeix el nom d'usuari, carreguem les seves dades
				else if (User.usernameExists(request
						.getParameter("username_mail")))
					user.loadUser("username",
							request.getParameter("username_mail"));
				else
					break;

				// Carreguem la sessió
				session = request.getSession();
				session.setAttribute("user", user);
			}
			break;
		// En cas de que s'estigui fent un logout
		case "logout":
			// Si hem arribat fins aqui, donem per bona la petició AJAX
			result.addPair("success", true);
			// Tanquem la sessió
			session = request.getSession(false);
			session.invalidate();
			break;
		case "register":
			try {
				// Si hem arribat fins aqui, donem per bona la petició AJAX
				result.addPair("success", true);
				User tempUser = new User();
				tempUser.setMail(request.getParameter("mail"));
				tempUser.setUsername(request.getParameter("username"));
				tempUser.setName(request.getParameter("name"));
				tempUser.setSurname(request.getParameter("surname"));
				tempUser.setPassword(request.getParameter("password"));
				DAO database = new DAO();
				database.saveObject(tempUser);
				session = request.getSession();
				session.setAttribute("user", tempUser);
			} catch (Exception e) {
				e.printStackTrace();
			}
		default:
		}
		// Escrivim en la resposta les dades en format JSON
		response.getWriter().write(result.toString());
	}
}
