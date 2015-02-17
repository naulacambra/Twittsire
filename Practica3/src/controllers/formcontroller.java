package controllers;

import java.io.IOException;

import models.BeanUser;
import utils.BeanUtilities;
import utils.DAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class formcontroller
 */
public class formcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public formcontroller() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DAO dao = new DAO();
		BeanUser user = new BeanUser();
		BeanUtilities.populateBean(user, request);

		System.out.println("username:" + user.getUsername());
		System.out.println("mail:" + user.getMail());

		if (user.getFullName().isEmpty())
			user.setError(BeanUser.ErrorList.ERROR_FULLNAME.getValue());
		if (user.getUsername().isEmpty())
			user.setError(BeanUser.ErrorList.ERROR_USERNAME.getValue());
		if (user.getMail().isEmpty())
			user.setError(BeanUser.ErrorList.ERROR_MAIL.getValue());
		if (user.getPwd().isEmpty())
			user.setError(BeanUser.ErrorList.ERROR_PWD.getValue());
		if (!user.getPwd().equals(user.getPwd_check())
				|| user.getPwd_check().isEmpty())
			user.setError(BeanUser.ErrorList.ERROR_PWDCHECK.getValue());

		for (int i : user.getError()) {
			System.out.println("- " + i);
		}
		
		if (user.isComplete() && dao.isNewUser(user)) {
			System.out.println("Fer un insert a la BD: " + user.getUsername()
					+ " " + user.getMail());
			dao.insertUser(user);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("Registration.html");
			if (dispatcher != null)
				dispatcher.forward(request, response);
		} else {
			System.out.println("no fer insert a la BD: " + user.getUsername()
					+ " " + user.getMail());
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("form.jsp");
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
		DAO dao = new DAO();
		System.out.println("POST REQUEST");
		if (request.getParameter("action") != null) {
			System.out.println("AJAX request");
			switch (request.getParameter("action")) {
			case "validateUsername":
				System.out.println("he rebut la peticio USERNAME: "
						+ request.getParameter("data"));
				if (dao.isNewUsername(request.getParameter("data"))) {
					String text = "valid";
					response.setContentType("text/plain"); // Set content type
															// of the response
															// so that jQuery
															// knows what it can
															// expect.
					response.setCharacterEncoding("UTF-8"); // set encoding
					response.getWriter().write(text); // Write response body.
				}
				break;
			case "validateMail":
				System.out.println("he rebut la peticio MAIL: "
						+ request.getParameter("data"));
				if (dao.isNewMail(request.getParameter("data"))) {
					String text = "valid";
					response.setContentType("text/plain"); 
					response.setCharacterEncoding("UTF-8"); 
					response.getWriter().write(text); 
				}
				break;
			default:
				System.out.println("default ajax switch");
				break;
			}
		} else if (request.getParameter("action") == null){
			doGet(request, response);
		}

	}

}
