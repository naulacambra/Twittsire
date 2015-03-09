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
 * Aquest servlet s'encarrega de gestionar les crides pels followers i followings
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
		//Si rebem una petició GET la processem com si fos POST
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
			// En cas de que s'estigui demanant els followers
			case "getFollowers":
				// Si hem arribat fins aqui, donem per bona la petició AJAX
				result.addPair("success", true);
				// Guardem en una llista tots els followers de l'usuari
				ArrayList<Follow> followers = Follow.getFollowers(user
						.getIdUser());
				ArrayList<User> usersFollowers =  new ArrayList<User>();
				// Per a cada usuari follower de la llista, el carreguem
				for(Follow follow : followers){
					User tempUser =  new User();
					tempUser.loadUser("idUser", follow.getIdUserFollower());
					usersFollowers.add(tempUser);
				}
				result.addPair("followers_count", followers.size());
				// Els carreguem a la sessió també
				session.setAttribute("followers", followers);
				session.setAttribute("usersFollowers", usersFollowers);
				break;
			// En cas de que s'estigui demanant els followings
			case "getFollowings":
				// Si hem arribat fins aqui, donem per bona la petició AJAX
				result.addPair("success", true);
				// Guardem en una llista tots els followings de l'usuari
				ArrayList<Follow> followings = Follow.getFollowings(user
						.getIdUser());
				ArrayList<User> usersFollowings =  new ArrayList<User>();
				// Per a cada following de la llista, el carreguem
				for(Follow follow : followings){
					User tempUser =  new User();
					tempUser.loadUser("idUser", follow.getIdUserFollowed());
					usersFollowings.add(tempUser);
				}
				result.addPair("followings_count", followings.size());
				// Els carreguem a la sessió també
				session.setAttribute("followings", followings);
				session.setAttribute("usersFollowings", usersFollowings);
				break;
			// En cas de que es vulgui fer un follow
			case "followUser":
				// Si hem arribat fins aqui, donem per bona la petició AJAX
				result.addPair("success", true);
				try {
					User followedUser = new User();
					// Carreguem l'usuari en questió
					followedUser.loadUser("username",
							request.getParameter("username"));
					// Creem l'usuari al qual es seguirà i fem la relació "follow" entre ells
					Follow tempFollow = new Follow(user.getIdUser(),
							followedUser.getIdUser());
					// Guardem aquesta instancia a la base de dades
					DAO database = new DAO();
					database.saveObject(tempFollow);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			// En cas de que es vulgui fer un unfollow
			case "unfollowUser":
				// Si hem arribat fins aqui, donem per bona la petició AJAX
				result.addPair("success", true);
				User followedUser = new User();
				// Carreguem l'usuari en questió
				followedUser.loadUser("username",
						request.getParameter("username"));
				// Creem l'usuari al qual es deixarà de seguir i agafem la relació
				Follow tempFollow = new Follow(user.getIdUser(),
						followedUser.getIdUser());
				// Esborrem aquesta relació "follow" si existia
				if(tempFollow.exists())
					tempFollow.delete();
				break;
			}
		} else {
			// Si arribem aqui, la petició AJAX no s'ha processat correctament
			result.addPair("success", false);
		}
		// Escrivim en la resposta les dades en format JSON
		response.getWriter().write(result.toString());
	}

}
