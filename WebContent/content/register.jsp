<div class="col-12">
	<form class="register_form" data-content="content/user_panel.jsp">
		<input type="hidden" name="action" value="register" class="col-12" />
		<input data-action="checkUsername" type="text" id="username" name="username" class="username validate col-12" placeholder="Username"/>
		<label class="error" for="username">This username is already taken</label>
		<input data-action="checkMail" type="text" id="mail" name="mail" class="mail validate col-12" placeholder="Mail"/>
		<label class="error" for="mail">This mail is already taken</label>
		<input type="text" id="name" name="name" class="name col-12" placeholder="Name"/>
		<input type="text" id="surname" name="surname" class="surname col-12" placeholder="Surname"/>
		<input type="password" name="password" class="col-12" placeholder="Password"/>
		<input type="password" name="repeat_password" class="col-12" placeholder="Repeat password"/>
		<input type="submit" value="login" class="col-12"/>
	</form>
</div>