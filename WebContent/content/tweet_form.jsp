
<%
	if (session.getAttribute("user") != null) {
%>
<div class="col-12">
	<form id="tweet_form">
		<input type="hidden" name="action" value="createTweet" />
		<textarea name="tweet_text" placeholder="Escribe aqu� tu tweet"></textarea>
		<input type="submit" value="Twittear" />
	</form>
</div>
<%
	}
%>