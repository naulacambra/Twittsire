jQuery(document).ready(function($) {
	$('.login_form').submit(function(e) {
		e.preventDefault();
		var form = $(this);
		var container = $(this).parents('.container');
		$.ajax({
			url : "ajaxcontroller",
			type : "POST",
			dataType : "json",
			data : $(form).serialize(),
			success : function(response) {
				response = parseResponse(response);
				/* Comprovem si la cridada ajax ha anat bé */
				if (response.success) {
					if (response.login) {
						$(container).load('contentcontroller', {
							content : $(form).data('content')
						});
					}
				} else {
					console.warn("Something went wrong");
				}
			},
			error : function(response) {
				console.log("error");
				console.log(response.responseText);
			}
		});
	});
});

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