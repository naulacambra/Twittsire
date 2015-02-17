package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.BeanLogin;
import utils.BeanUtilities;
import utils.DAO;

/**
 * Servlet implementation class logincontroller
 */
public class logincontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public logincontroller() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DAO dao = new DAO();
		BeanLogin login = new BeanLogin();
		BeanUtilities.populateBean(login, request);
		if (login.isComplete() && dao.doLogin(login)) {

			HttpSession session = request.getSession();
			session.setAttribute("user", login.getUsername());
			System.out.println("Se ha hecho el login." + session.toString());
			System.out.println("User:" + session.getAttribute("user"));
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("loginOk.jsp");
			if (dispatcher != null)
				dispatcher.forward(request, response);
		} else {
			request.setAttribute("login", login);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			if (dispatcher != null)
				dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
