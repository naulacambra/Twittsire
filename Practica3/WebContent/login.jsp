<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="models.BeanLogin"%>

<link rel="stylesheet" type="text/css" href="css/form.css" />
<script type="text/javascript" src="jquery/jquery-1.7.1.js"></script>
<script type="text/javascript" src="jquery/jquery.validate.js"></script>
<script type="text/javascript" src="js/login_validator.js"></script>

<%
	BeanLogin login = null;
	if (request.getAttribute("login") != null) {
		login = (BeanLogin) request.getAttribute("login");
	} else {
		login = new BeanLogin();
	}
%>

<form id=registerForm action="" method="POST">
	<div id="form-content">
		<fieldset>
			<div class="fieldgroup">
				<label for="username">Nom d'usuari:</label> <input type="text"
					name="username" value="<%=login.getUsername()%>" id="username" />
				<span id="usernameAjaxError" class="ajax-error"></span>
				<%
					if (login.getError()[BeanLogin.ErrorList.ERROR_USERNAME.getValue()] == 1) {
				%>
				<label class="error">No existeix aquest usuari</label>
				<%
					}
				%>
			</div>

			<div class="fieldgroup">
				<label for="pwd">Contrasenya:</label> <input type="password"
					name="pwd" value="" id="pwd" />
				<%
					if (login.getError()[BeanLogin.ErrorList.ERROR_PWD.getValue()] == 1) {
				%>
				<label class="error">la contrassenya no és correcta</label>
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