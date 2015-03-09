<%@ page import="java.util.ArrayList"%>
<%@ page import="models.Tweet"%>

<div class="col-12">
	<%
		ArrayList<Tweet> comments = (ArrayList<Tweet>) session
				.getAttribute("comments");

		for (Tweet comment : comments) {
	%>
	<span class="col-12"><%= comment.getText() %></span>
	<div class="clear col-12"></div>
	<%
		}
	%>
</div>