<%@ page import="models.User"%>
<%
	User user = (User) session.getAttribute("user");
%>
<div class="col-12">
	<img id="user_avatar" src="" />
	<span class="col-12" id="user_username"><%=user.getUsername()%></span>
	<span class="col-12" id="user_mail"><%= user.getMail() %></span>
	<span class="col-6" id="user_name"><%= user.getName() %></span>
	<span class="col-6" id="user_surname"><%= user.getSurname() %></span>
	<button class="edit_user col-6">Edit profile</button>
	<button class="logout col-6">Logout</button>
</div>
<div id="edit_user_form" title="Edit profile">
	<form>
	<input type="hidden" id="id_user_edit" value="" />
		<label class="col-12" for="name">Name</label>
		<input class="col-12" type="text" id="name" name="name"/>
		<label class="col-12" for="surname">Surname</label>
		<input class="col-12" type="text" id="surname" name="surname"/>
		<label class="col-12" for="password">Password</label>
		<input class="col-12" type="password" id="password" name="password"/>
		<label class="col-12" for="repeat_password">Repeat password</label>
		<input class="col-12" type="password" id="repeat_password" name="repeat_password"/>
		<!-- Allow form submission with keyboard without duplicating the dialog button -->
		<input type="submit" tabindex="-1"
			style="position: absolute; top: -1000px">
	</form>
</div>