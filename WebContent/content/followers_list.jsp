<%@ page import="java.util.ArrayList"%>
<%@ page import="models.User"%>

<div id="followers_list" class="col-12">
	<%
		ArrayList<User> followers = (ArrayList<User>) session
				.getAttribute("usersFollowers");

		for (User user : followers) {
	%>
	<div class="follower">
		<span><%=user.getUsername()%></span>
	</div>
	<%
		}
	%>
</div>