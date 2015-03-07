jQuery(function($) {
	$('#tweet_form').submit(function(e) {
		e.preventDefault();
		var form = $(this);
		$.ajax({
			url : "tweetcontroller",
			type : "POST",
			dataType : "json",
			data : $(form).serialize(),
			success : function(response) {
				response = parseResponse(response);
				/* Comprovem si la cridada ajax ha anat bé */
				if (response.success) {
					loadTweets();
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