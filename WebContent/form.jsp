<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="models.BeanUser"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Exemple Formulari</title>
<link rel="stylesheet" type="text/css" href="css/estil.css" />
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript" src="js/form.js"></script>
<script>
	$(document).ready(function() {
		$("#registerForm").validate();
	});
</script>
</head>
<body>
	<%
		BeanUser user = null;
		if (request.getAttribute("username") != null) {
			user = (BeanUser) request.getAttribute("username");
		} else {
			user = new BeanUser();
		}
	%>
	<form action="/epaw_p2/formcontroller" method="post" id=registerForm>
		<table>
			<tr>
				<td>Name</td>
				<td><input type="text" name="name"
					value="<%=user.getName()%>" id="name" class="required" /></td>
			</tr>
			<tr>
				<td>Surname</td>
				<td><input type="text" name="surname"
					value="<%=user.getSurname()%>" id="surname" class="required" /></td>
			</tr>
			<tr>
				<td>Username</td>
				<td><input type="text" name="username"
					value="<%=user.getUsername()%>" id="username" class="required"
					minlength="5" /></td>
				<td><label class="error_label" for="username">The username already exists in our DB!</label></td>
			</tr>
			<tr>
				<td>Mail</td>
				<td><input type="text" name="mail" value="<%=user.getMail()%>"
					id="mail" class="required email" /></td>
				<td><label class="error_label" for="mail">This mail is already used by other user</label></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="pwd" id="pwd"
					class="required" minlength="8" /></td>
			</tr>
			<tr>
				<td>Repeat password</td>
				<td><input type="password" name="pwd_check"
					id="pwd_check" class="required" /></td>
				<td><label class="error_label" for="pwd_check">The passwords fields doesn't match</label></td>
			</tr>
			<tr>
				<td><input type="submit" value="Enviar"></td>
			</tr>
		</table>
	</form>
</body>
</html>