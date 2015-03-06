jQuery(document).ready(function($) {
	$.when(loadFollowers(), loadFollowings()).done(function() {
		console.log('Web page loaded!');
		$('#index_load').remove();
	});
});