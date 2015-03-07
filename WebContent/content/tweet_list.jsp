<%@ page import="java.util.ArrayList"%>
<%@ page import="models.Tweet"%>
<div id="tweet_list" class="col-12">
	<%
		ArrayList<Tweet> tweets = (ArrayList<Tweet>) session
				.getAttribute("tweets");

		for (Tweet tweet : tweets) {
	%>
	<div class="tweet col-12">
	<a href="#" class="user_link col-12 display" data-username="<%=  tweet.getUser().getUsername() %>"><%=tweet.getUser().getUsername()%></a>
		<span class="col-12 tweet_content"><%=tweet.getText()%></span>
	</div>
	<%
		}
	%>
</div>