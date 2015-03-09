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

	$('.follow_user').click(function(e) {
		e.preventDefault();
		var follow_button = $(this);
		if ($(follow_button).hasClass('followed')) {
			action = 'unfollowUser';
			$(follow_button).removeClass('followed');
		} else {
			var action = 'followUser';
			$(follow_button).addClass('followed');
		}
		/* Follow Tweet */
		$.ajax({
			url : "followercontroller",
			type : "POST",
			dataType : "json",
			data : {
				action : action,
				username : $(this).data('username')
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

	$('.delete_tweet').click(function(e) {
		e.preventDefault();
		var current_scoope = $('.header_button.selected').data('scoope');
		/* Follow Tweet */
		$.ajax({
			url : "tweetcontroller",
			type : "POST",
			dataType : "json",
			data : {
				action : "deleteTweet",
				tweet : $(this).data('tweet')
			},
			success : function(response) {
				response = parseResponse(response);
				loadTweets(current_scoope)
			},
			error : function(response) {
				console.warn(response.responseText);
			}
		});
	});

	$('.comment_area').keypress(
			function(e) {
				var code = e.keyCode || e.which
				if (code == 13) {
					e.preventDefault();
					if ($(this).val() != '') {
						var current_scoope = $('.header_button.selected').data(
								'scoope');
						var comment_area = $(this);
						$(comment_area).attr("disabled", "disabled");
						/* Comment Tweet */
						$.ajax({
							url : "tweetcontroller",
							type : "POST",
							dataType : "json",
							data : {
								action : "commentTweet",
								tweet : $(comment_area).data('idtweet'),
								text : $(comment_area).val()
							},
							success : function(response) {
								response = parseResponse(response);
								loadTweets(current_scoope);
								/* load comments */
							},
							error : function(response) {
								console.warn(response.responseText);
							}
						});
					}
				}
			});

	$('.comment_tweet').click(function(e) {
		e.preventDefault();
		var comments_list = $(this).parent().find('.comments_list');
		/* Comment Tweet */
		$.ajax({
			url : "tweetcontroller",
			type : "POST",
			dataType : "json",
			data : {
				action : "loadComments",
				tweet : $(this).data('idtweet')
			},
			success : function(response) {
				response = parseResponse(response);
				if (response.success)
					$(comments_list).load('content/comments_list.jsp');
			},
			error : function(response) {
				console.warn(response.responseText);
			}
		});
	});
}