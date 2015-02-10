jQuery(document).ready(function($) {
	$('#loginForm').submit(function(e) {
		e.preventDefault();
		ajaxCall({
			/* Definim quina acció volem fer en el servlet */
			action : 'login',
			/* Enviem el nom d'usuari que ha de comprovar */
			data : {
				mail: $('#mail').val(),
				password: $('#password').val()
			}
		}, $('.error_label[for="username"]'), function(response) {
			response = parseResponse(response);
			/* Comprovem si la cridada ajax ha anat bé */
			console.log(response);
			if (response.success) {
				if (!response.exists)
					/*
					 * Si el nom d'usuari ja està registrat mostrem l'error en
					 * la vista
					 */
					$('.error_label[for="mail"]').show();
				else{
//					if(response.login)
//						window.location.href = "list.jsp";
				}
			} else {
				console.warn("Something went wrong");
			}
		});
	});
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