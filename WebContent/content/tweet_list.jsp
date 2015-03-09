<%@ page import="java.util.ArrayList"%>
<%@ page import="models.Tweet"%>
<%@ page import="models.User"%>
<%@ page import="models.Follow"%>
<%@ page import="managers.StringManager"%>
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
				data-username="<%=tweet.getUser().getUsername()%>"> <i
				class="fa fa-user-plus"></i></a>
			<%
				} else if (user.getIdUser() == tweet.getUser().getIdUser()) {
			%>
			<a href="#" class="delete_tweet" data-tweet="<%=tweet.getIdTweet()%>">
				<i class="fa fa-times"></i>
			</a> <a href="#" class="edit_tweet" data-tweet="<%=tweet.getIdTweet()%>">
				<i class="fa fa-pencil-square-o"></i>
			</a>
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
		<div class="tweet_comments col-12">
			<a href="" class="comment_tweet"
				data-idtweet="<%=tweet.getIdTweet()%>"><i
				class="fa fa-comment-o"></i></a> <span>Show <%=tweet.getCommentCount()
						+ " "
						+ StringManager.singularOrPlural(
								tweet.getCommentCount(), "comment", "comments")%></span>
			<textarea class="comment_area col-12"
				placeholder="Write here your comment"
				data-idtweet="<%=tweet.getIdTweet()%>"></textarea>
			<div class="comments_list"></div>
		</div>
	</div>
	<%
		}
	%>
</div>
<div id="edit_tweet_form" title="Edit tweet">
	<form>
	<input type="hidden" id="id_tweet_edit" value="" />
		<textarea id="tweet_textarea" class="col-12"></textarea>
		<!-- Allow form submission with keyboard without duplicating the dialog button -->
		<input type="submit" tabindex="-1"
			style="position: absolute; top: -1000px">
	</form>
</div>