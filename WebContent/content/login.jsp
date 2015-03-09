<div class="col-12">
	<form class="login_form" data-content="content/user_panel.jsp">
		<input type="hidden" name="action" value="login" class="col-12" />
		<input data-action="checkUsernameAndMail" type="text" id="username_mail" name="username_mail" class="username_mail validate col-12"/>
		<label class="error" for="username_mail">This username or mail doesn't exists</label>
		<input type="password" name="password" class="col-12"/>
		<input type="submit" value="login" class="col-12"/>
	</form>
</div>
<!-- <form action="/epaw_p3/formcontroller" method="post" id=loginForm> -->
<!-- 	<table> -->
<!-- 		<tr> -->
<!-- 			<td>Mail</td> -->
<!-- 			<td><input type="text" name="mail" value="" id="mail" -->
<!-- 				class="required email" /></td> -->
<!-- 			<td><label class="error_label" for="mail">This mail -->
<!-- 					isn't registered</label></td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td>Password</td> -->
<!-- 			<td><input type="password" name="pwd" id="pwd" class="required" -->
<!-- 				minlength="8" /></td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td><input type="submit" value="Enviar"></td> -->
<!-- 		</tr> -->
<!-- 	</table> -->
<!-- </form> -->