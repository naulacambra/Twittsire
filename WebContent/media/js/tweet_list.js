function bindTweetLinks() {
	$('.user_link').click(function(e) {
		e.preventDefault();
		loadTweets('user', $(this).data('username'));
	});

	$('.rate_tweet').click(function(e) {
		e.preventDefault();
		var rating_button = $(this);
		var action = 'rateTweet';
		if ($(rating_button).hasClass('rated')) {
			action = 'unrateTweet';
			$(rating_button).parent().find('.rate_tweet').removeClass('rated');
		} else {
			$(rating_button).parent().find('.rate_tweet').removeClass('rated');
			$(rating_button).addClass('rated');
		}
		/* Rate Tweet */
		$.ajax({
			url : "ratingcontroller",
			type : "POST",
			dataType : "json",
			data : {
				action : action,
				rate : $(this).data('rate'),
				tweet : $(this).data('idtweet')
			},
			success : function(response) {
				response = parseResponse(response);
				if (response.success) {

				} else {
					$(rating_button).removeClass('rated');
				}
			},
			error : function(response) {
				console.warn(response.responseText);
			}
		});
	});
}