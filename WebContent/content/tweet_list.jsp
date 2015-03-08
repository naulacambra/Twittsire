<%@ page import="java.util.ArrayList"%>
<%@ page import="models.Tweet"%>
<%@ page import="models.User"%>
<%@ page import="models.Follow"%>
<div id="tweet_list" class="col-12">
	<%
		User user = (User) session.getAttribute("user");
		ArrayList<Tweet> tweets = (ArrayList<Tweet>) session
				.getAttribute("tweets");
		ArrayList<Follow> followings = (ArrayList<Follow>) session
				.getAttribute("followings");

		for (Tweet tweet : tweets) {
	%>
	<div class="tweet col-12">
		<div class="tweet_header col-12">
			<%
				if (user != null) {
						if (!user.getUsername().equals(
								tweet.getUser().getUsername())) {
							String extra_class = "";
							for (Follow follow : followings) {
								if (follow.getIdUserFollowed() == tweet.getUser()
										.getIdUser()) {
									extra_class = "followed";
								}
							}
			%>
			<a href="#" class="follow_user <%=extra_class%>"
				data-username="<%=tweet.getUser().getUsername()%>"><i
				class="fa fa-user-plus"></i></a>
			<%
				}
					}
			%>
			<a href="#" class="user_link"
				data-username="<%=tweet.getUser().getUsername()%>"><%=tweet.getUser().getUsername()%></a>
			<div class="tweet_rating right">
				<a href="#"
					class="rate_tweet like <%=tweet.getRate() == 1 ? "rated" : ""%>"
					data-rate="1" data-idTweet="<%=tweet.getIdTweet()%>"><i
					class="fa fa-thumbs-o-up"></i></a> <a href="#"
					class="rate_tweet dislike <%=tweet.getRate() == -1 ? "rated" : ""%>"
					data-rate="-1" data-idTweet="<%=tweet.getIdTweet()%>"><i
					class="fa fa-thumbs-o-down"></i></a>
			</div>
		</div>
		<span class="col-12 tweet_content"><%=tweet.getText()%></span>
	</div>
	<%
		}
	%>
</div>