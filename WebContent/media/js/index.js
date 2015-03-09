jQuery(document).ready(function($) {
	$.when(checkDatabase(), loadFollowers(), loadFollowings(), loadTweets()).done(function() {
		console.log('Web page loaded!');
		$('#index_load').remove();
	});
	
	$('.header_button').click(function(){
		$('.header_button').removeClass('selected');
		$(this).addClass('selected');
		loadTweets($(this).data('scoope'));
	});
});