<div class="col-12">
	<form class="login_form" data-content="content/user_panel.jsp">
		<input type="hidden" name="action" value="login" class="col-12" />
		<input data-action="checkUsernameAndMail" type="text" id="username_mail" name="username_mail" class="username_mail validate col-12"/>
		<label class="error" for="username_mail">This username or mail doesn't exists</label>
		<input type="password" name="password" class="col-12"/>
		<input type="submit" value="login" class="col-12"/>
	</form>
</div>