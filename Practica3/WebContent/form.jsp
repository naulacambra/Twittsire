<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="models.BeanUser"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulari</title>
<link rel="stylesheet" type="text/css" href="css/form.css" />
<script type="text/javascript" src="jquery/jquery-1.7.1.js"></script>
<script type="text/javascript" src="jquery/jquery.validate.js"></script>
<script type="text/javascript" src="js/form_validator.js"></script>
</head>
<body>
	<%
		BeanUser user = null;
		if (request.getAttribute("user") != null) {
			user = (BeanUser) request.getAttribute("user");
		} else {
			user = new BeanUser();
		}
	%>
	<form id=registerForm action="" method="POST">
		<div id="form-content">
			<fieldset>

				<div class="fieldgroup">
					<label for="fullName">Nom i cognom:</label> <input type="text"
						name="fullName" value="<%=user.getFullName()%>" id="fullName" />
					<%
						if (user.getError()[BeanUser.ErrorList.ERROR_FULLNAME.getValue()] == 1) {
					%>
					<label class="error">Introdueix el teu nom argh!</label>
					<%
						}
					%>
				</div>

				<div class="fieldgroup">
					<label for="username">Nom d'usuari:</label> <input type="text"
						name="username" value="<%=user.getUsername()%>" id="username" />
					<span id="usernameAjaxError" class="ajax-error"></span>
					<%
						if (user.getError()[BeanUser.ErrorList.ERROR_USERNAME.getValue()] == 1) {
					%>
					<label class="error">Aquest compte d' usuari ja està sent
						utilitzat</label>
					<%
						}
					%>
				</div>

				<div class="fieldgroup">
					<label for="mail">Correu electrònic:</label> <input type="text"
						name="mail" value="<%=user.getMail()%>" id="mail" /> <span
						id="mailAjaxError" class="ajax-error"></span>
					<%
						if (user.getError()[BeanUser.ErrorList.ERROR_MAIL.getValue()] == 1) {
					%>
					<label class="error">Aquest compte de correu ja està sent
						utilitzat</label>
					<%
						}
					%>
				</div>

				<div class="fieldgroup">
					<label for="pwd">Contrasenya:</label> <input type="password"
						name="pwd" value="" id="pwd" />
					<%
						if (user.getError()[BeanUser.ErrorList.ERROR_PWD.getValue()] == 1) {
					%>
					<label class="error">Introdueix una contraseya</label>
					<%
						}
					%>
				</div>

				<div class="fieldgroup">
					<label for="pwd_check">Repeteix la contrasenya:</label> <input
						type="password" name="pwd_check" value=""
						id="pwd_check" />
					<%
						if (user.getError()[BeanUser.ErrorList.ERROR_PWDCHECK.getValue()] == 1) {
					%>
					<label class="error">Les contrasenyes no concordent</label>
					<%
						}
					%>
				</div>
				<div class="fieldgroup">
					<input type="submit" value="Enviar" class="submit" />
				</div>
			</fieldset>
		</div>
	</form>
</body>
</html>