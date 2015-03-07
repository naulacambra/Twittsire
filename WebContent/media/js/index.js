jQuery(document).ready(function($) {
	$.when(loadFollowers(), loadFollowings(), loadTweets()).done(function() {
		console.log('Web page loaded!');
		$('#index_load').remove();
	});
	
	$('.header_button').click(function(){
		loadTweets($(this).data('scoope'));
	});
});