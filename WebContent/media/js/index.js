jQuery(document).ready(function($) {
	$.when(loadFollowers(), loadFollowings(), loadTweets()).done(function() {
		console.log('Web page loaded!');
		$('#index_load').remove();
	});
});