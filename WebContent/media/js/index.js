jQuery(document).ready(function($) {
	$.when(true).done(function() {
		console.log('Web page loaded!');
		$('#index_load').remove();
	});
	/* Load all initial content */
	/* Load followers */
	$.ajax({
		url : "followercontroller",
		type : "POST",
		dataType : "json",
		data : {
			action : 'getFollowers'
		},
		success : function(response) {
			/*Load followers into left sidebar*/
		},
		error : function(response) {

		}
	});
	/* Load followings */
	$.ajax({
		url : "followercontroller",
		type : "POST",
		dataType : "json",
		data : {
			action : 'getFollowings'
		},
		success : function(response) {
			/*Load followings into left sidebar*/
		},
		error : function(response) {

		}
	});
});