<%@ page import="java.util.ArrayList"%>
<%@ page import="models.Tweet"%>
<div id="tweet_list" class="col-12">
	<%
		ArrayList<Tweet> tweets = (ArrayList<Tweet>) session
				.getAttribute("tweets");

		for (Tweet tweet : tweets) {
	%>
	<div class="tweet col-12">
		<div class="tweet_header col-12">
			<a href="#" class="user_link"
				data-username="<%=tweet.getUser().getUsername()%>"><%=tweet.getUser().getUsername()%></a>
			<div class="tweet_rating right">
				<a href="#" class="rate_tweet like <%= tweet.getRate() == 1 ? "rated" : "" %>" data-rate="1" data-idTweet="<%= tweet.getIdTweet() %>"><i class="fa fa-thumbs-o-up"></i></a>
				<a href="#" class="rate_tweet dislike <%= tweet.getRate() == -1 ? "rated" : "" %>" data-rate="-1" data-idTweet="<%= tweet.getIdTweet() %>"><i class="fa fa-thumbs-o-down"></i></a>
			</div>
		</div>
		<span class="col-12 tweet_content"><%=tweet.getText()%></span>
	</div>
	<%
		}
	%>
</div>