package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.User;
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
		// Comprovem quina accio s'ens ha demanat amb la variable "action"
		switch (request.getParameter("action")) {
		// Comprovar si ja existeix un nom d'usuari
		case "checkUsername":
			// Si hem arribat fins aqui, donem per bona la petici� AJAX
			result.addPair("success", true);
			// Comprovem si el nom d'usuari donat existeix
			result.addPair("exists",
					User.usernameExists(request.getParameter("data")));
			break;
		// Comprovar si ja existeix un mail
		case "checkMail":
			// Si hem arribat fins aqui, donem per bona la petici� AJAX
			result.addPair("success", true);
			// Comprovem si el mail donat ja est� registrat
			result.addPair("exists",
					User.mailExists(request.getParameter("data")));
			break;
		// En cas de que s'estigui fent un login
		case "login":
			// Si hem arribat fins aqui, donem per bona la petici� AJAX
			result.addPair("success", true);
			// Comprovem si s'est� omplint el camp del login
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
				//Si existeix l'email, carreguem les seves dades
				if (User.mailExists(request.getParameter("username_mail")))
					user.loadUser("mail", request.getParameter("username_mail"));
				//Si existeix el nom d'usuari, carreguem les seves dades
				else if (User.usernameExists(request
						.getParameter("username_mail")))
					user.loadUser("username",
							request.getParameter("username_mail"));
				else
					break;

				// Carreguem la sessi� 
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
			}
			break;
		// En cas de que s'estigui fent un logout
		case "logout":
			// Si hem arribat fins aqui, donem per bona la petici� AJAX
			result.addPair("success", true);
			System.out.println("logging out!");
			// Tanquem la sessi�
			HttpSession session = request.getSession(false);
			session.invalidate();
			break;
		default:
		}
		// Escrivim en la resposta les dades en format JSON
		response.getWriter().write(result.toString());
	}
}
