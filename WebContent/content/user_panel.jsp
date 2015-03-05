<%@ page import="models.User"%>
<%
	session = pageContext.getSession();
	User user = (User) session.getAttribute("user");
%>
<div class="col-12">
	<img id="user_avatar" src="" />
	<span id="user_username"><%=user.getUsername()%></span>
	<span id="user_mail"><%= user.getMail() %></span>
</div>