<%@ page import="java.util.ArrayList"%>
<%@ page import="models.User"%>

<div id="followings_list" class="col-12">
<h3>FOLLOWINGS</h3>
	<%
		ArrayList<User> followings = (ArrayList<User>) session
				.getAttribute("usersFollowings");

		for (User user : followings) {
	%>
	<div class="following">
		<span><%=user.getUsername()%></span>
	</div>
	<%
		}
	%>
</div>