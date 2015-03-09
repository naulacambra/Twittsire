<%@ page import="models.User"%>
<%
	User user = (User) session.getAttribute("user");
%>
<div class="col-12">
	<img id="user_avatar" src="" />
	<span class="col-12" id="user_username"><%=user.getUsername()%></span>
	<span class="col-12" id="user_mail"><%= user.getMail() %></span>
	<button class="logout">Logout</button>
</div>