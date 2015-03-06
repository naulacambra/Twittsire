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

function loadFollowers() {
	/* Load followers */
	$.ajax({
		url : "followercontroller",
		type : "POST",
		dataType : "json",
		data : {
			action : 'getFollowers'
		},
		success : function(response) {
			response = parseResponse(response);
			if (response.success) {
				if (response.followers_count > 0) {

				} else {
					$('#followers').load('content/empty_followers.jsp');
				}
			} else {
				$('#followers').html('');
			}
		},
		error : function(response) {

		}
	});
}

function loadFollowings() {
	/* Load followings */
	$.ajax({
		url : "followercontroller",
		type : "POST",
		dataType : "json",
		data : {
			action : 'getFollowings'
		},
		success : function(response) {
			response = parseResponse(response);
			if (response.success) {
				if (response.followings_count > 0) {

				} else {
					$('#followings').load('content/empty_followings.jsp');
				}
			} else {
				$('#followings').html('');
			}
		},
		error : function(response) {

		}
	});
}

function loadTweets() {
	/* Load tweets */
	$.ajax({
		url : "tweetcontroller",
		type : "POST",
		dataType : "json",
		data : {
			action : 'getTweets',
			scoope : 'global'
		},
		success : function(response) {
			response = parseResponse(response);
			if (response.success) {
				if (response.tweets_count > 0) {
					$('#content').load('content/tweet_list.jsp');
				} else {
					$('#content').load('content/empty_tweets.jsp');
				}
			} else {
				$('#content').html('');
			}
		},
		error : function(response) {

		}
	});
}