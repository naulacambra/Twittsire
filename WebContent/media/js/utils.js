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

function checkDatabase(){
	/* Load followers */
	$.ajax({
		url : "ajaxcontroller",
		type : "POST",
		dataType : "json",
		data : {
			action : 'checkDatabase'
		},
		success : function(response) {
			response = parseResponse(response);
		},
		error : function(response) {

		}
	});
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
					$('#followers').load('content/followers_list.jsp');
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
					$('#followings').load('content/followings_list.jsp');
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

function loadTweets(scoope, username) {
	if (scoope == null)
		scoope = 'global';
	/* Load tweets */
	$.ajax({
		url : "tweetcontroller",
		type : "POST",
		dataType : "json",
		data : {
			action : 'getTweets',
			scoope : scoope,
			username : username
		},
		success : function(response) {
			response = parseResponse(response);
			if (response.success) {
				if (response.tweets_count > 0) {
					$('#content').load('content/tweet_list.jsp', {},
							function() {
								bindTweetLinks();
							});
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

function editTweet() {
	var tweet_text = $('#edit_tweet_form').find('#tweet_textarea').val();
	var id_tweet = $('#edit_tweet_form').find('#id_tweet_edit').val();
	/* Edit tweet */
	$.ajax({
		url : "tweetcontroller",
		type : "POST",
		dataType : "json",
		data : {
			action : 'editTweet',
			tweet : id_tweet,
			text : tweet_text
		},
		success : function(response) {
			response = parseResponse(response);
			if (response.success) {
				loadTweets($('.header_button.selected').data('scoope'));
			}
		},
		error : function(response) {

		}
	});
}

function bindEditProfile() {
	dialog_user = $("#edit_user_form").dialog({
		autoOpen : false,
		height : 300,
		width : 350,
		modal : true,
		buttons : {
			"Edit profile" : function() {
				editUser();
				dialog_user.dialog("close");
			},
			Cancel : function() {
				dialog_user.dialog("close");
			}
		}
	});

	$('.edit_user').click(function(e) {
		e.preventDefault();
		var name = $(this).parent().find('#user_name').text();
		var surname = $(this).parent().find('#user_surname').text();
		$(dialog_user).find('#name').val(name);
		$(dialog_user).find('#surname').val(surname);
		dialog_user.dialog("open");
	});
}

function editUser() {
	var name = $('#edit_user_form').find('#name').val();
	var surname = $('#edit_user_form').find('#surname').val();
	var password = $('#edit_user_form').find('#password').val();
	/* Edit tweet */
	$.ajax({
		url : "usercontroller",
		type : "POST",
		dataType : "json",
		data : {
			action : 'editUser',
			name : name,
			surname : surname,
			password : password
		},
		success : function(response) {
			response = parseResponse(response);
			if (response.success) {
				$('#right_sidebar').load('contentcontroller', {
					content : 'content/user_panel.jsp'
				}, bindEditProfile);
			}
		},
		error : function(response) {

		}
	});
}