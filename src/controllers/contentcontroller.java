package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Aquest servlet s'encarrega de carregar de manera general el contingut de la pàgina web
 */
@WebServlet("/contentcontroller")
public class contentcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public contentcontroller() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Cridem els arxius jsp de la carpeta "content", els quals escollirem un depenent de l'acció demanada, a "utils.js"
		String content = (String)request.getParameter("content");
     	RequestDispatcher dispatcher = 
					request.getRequestDispatcher(content);
     	//Transferim el control a aquest dispatcher
		 if (dispatcher != null) dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Si rebem una petició POST la processem com si fos GET
		doGet(request,response);
	}

}
