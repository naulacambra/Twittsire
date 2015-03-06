<%@ page import="java.util.ArrayList"%>
<%@ page import="models.Tweet"%>
<div id="tweet_list" class="col-12">
	<%
		ArrayList<Tweet> tweets = (ArrayList<Tweet>) session
				.getAttribute("tweets");

		for (Tweet tweet : tweets) {
	%>
	<div class="tweet">
		<span>By: <%=tweet.getUser().getUsername()%></span> <span><%=tweet.getText()%></span>
	</div>
	<%
		}
	%>
</div>