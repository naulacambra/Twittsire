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
import models.User;
import utils.DAO;
import utils.JSON;

/**
 * Servlet implementation class followercontroller
 */
@WebServlet("/followercontroller")
public class followercontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public followercontroller() {
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
		User user = (User) session.getAttribute("user");
		if (user != null) {
			// Comprovem quina accio s'ens ha demanat amb la variable "action"
			switch (request.getParameter("action")) {
			case "getFollowers":
				result.addPair("success", true);
				ArrayList<Follow> followers = Follow.getFollowers(user
						.getUsername());
				result.addPair("followers_count", followers.size());
				session.setAttribute("followers", followers);
				break;
			case "getFollowings":
				result.addPair("success", true);
				ArrayList<Follow> followings = Follow.getFollowings(user
						.getUsername());
				result.addPair("followers_count", followings.size());
				session.setAttribute("followings", followings);
				break;
			case "followUser":
				result.addPair("success", true);
				try {
					User followedUser = new User();
					followedUser.loadUser("username",
							request.getParameter("username"));
					Follow tempFollow = new Follow(user.getIdUser(),
							followedUser.getIdUser());
					DAO database = new DAO();
					database.saveObject(tempFollow);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "unfollowUser":
				result.addPair("success", true);
				User followedUser = new User();
				followedUser.loadUser("username",
						request.getParameter("username"));
				Follow tempFollow = new Follow(user.getIdUser(),
						followedUser.getIdUser());
				if(tempFollow.exists())
					tempFollow.delete();
				break;
			}
		} else {
			result.addPair("success", false);
		}
		// Escrivim en la resposta les dades en format JSON
		response.getWriter().write(result.toString());
	}

}
