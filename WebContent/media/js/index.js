jQuery(document).ready(function($) {
	$.when(loadGlobal()).done(function() {
		console.log('Web page loaded!');
		$('#index_load').remove();
	});
	
	$('.header_button').click(function(){
		$('.header_button').removeClass('selected');
		$(this).addClass('selected');
		loadTweets($(this).data('scoope'));
	});
});