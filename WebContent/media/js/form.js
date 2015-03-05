jQuery(document).ready(function($) {
	/* Quan l'usuari abandona el camp 'username' disparem la cridada AJAX */
	$('#username').focusout(function() {
		ajaxCall({
			/* Definim quina acci� volem fer en el servlet */
			action : 'checkUsername',
			/* Enviem el nom d'usuari que ha de comprovar */
			data : $('#username').val()
		}, $('.error_label[for="username"]'), function(response) {
			response = parseResponse(response);
			/* Comprovem si la cridada ajax ha anat b� */
			if (response.success) {
				if (response.exists)
					/*
					 * Si el nom d'usuari ja est� registrat mostrem l'error en
					 * la vista
					 */
					$('.error_label[for="username"]').show();
			} else {
				console.warn("Something went wrong");
			}
		});
	});
	/* Quan l'usuari abandona el camp 'mail' disparem la cridada AJAX */
	$('#mail').focusout(function() {
		ajaxCall({
			/* Definim quina acci� volem fer en el servlet */
			action : 'checkMail',
			/* Enviem el mail que ha de comprovar */
			data : $('#mail').val()
		}, $('.error_label[for="mail"]'), function(response) {
			response = parseResponse(response);
			/* Comprovem si la cridada ajax ha anat b� */
			if (response.success) {
				if (response.exists)
					/* Si el mail ja est� registrat mostrem l'error en la vista */
					$('.error_label[for="mail"]').show();
			} else {
				console.warn("Something went wrong");
			}
		});
	});
	/*
	 * Cada cop que es modifica el camp de 'Repetir password' es comprova si t�
	 * el mateix valor que el camp 'password'
	 */
	$('#pwd_check').keyup(function() {
		if ($(this).val() != $('#pwd').val())
			/* Si no t� el mateix valor mostrem l'error a la vista */
			$('.error_label[for="pwd_check"]').show();
		else
			$('.error_label[for="pwd_check"]').hide();
	});
	/*
	 * Cada cop que l'usuari entra en un camp, amaguem la etiqueta d'error
	 * associada a aquest camp
	 */
	$('input').focus(function() {
		$('label.error_label[for="' + $(this).attr('name') + '"]').hide();
	});
	/* !Reset error labels */
});

/* Funció per facilitar les cridades AJAX */
function ajaxCall(data, errorElement, successFunction) {
	$.ajax({
		url : "ajaxcontroller",
		type : "POST",
		dataType : "json",
		data : data,
		success : successFunction,
		error : function(response) {
			response = response[0];
			console.log("error");
			console.log(response);
		}
	});
}
/* Funció per poder tractar correctament la resposta de les cridades AJAX */
function parseResponse(response) {
	count = 0;
	new_response = {};
	response.forEach(function(element) {
		for (key in element) {
			new_response[key] = response[count][key];
			++count;
		}
	});
	return new_response;
}
