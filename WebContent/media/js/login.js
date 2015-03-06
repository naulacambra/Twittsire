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
						loadFollowers();
						loadFollowings();
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

	$('.logout').click(function(e) {
		e.preventDefault();
		var container = $(this).parents('.container');
		$.ajax({
			url : "ajaxcontroller",
			type : "POST",
			dataType : "json",
			data : {
				action : "logout"
			},
			success : function(response) {
				response = parseResponse(response);
				/* Comprovem si la cridada ajax ha anat bé */
				if (response.success) {
					$(container).load('contentcontroller', {
						content : 'content/login.jsp'
					});
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
