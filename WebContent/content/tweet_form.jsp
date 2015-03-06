
<%
	if (session.getAttribute("user") != null) {
%>
<div class="col-12">
	<form id="tweet_form">
		<input type="hidden" name="action" value="createTweet" />
		<textarea name="tweet_text" placeholder="Escribe aquí tu tweet" class="col-12"></textarea>
		<input type="submit" value="Twittear" class="right"/>
	</form>
</div>
<%
	}
%>