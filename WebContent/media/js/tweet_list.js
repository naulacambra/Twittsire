function bindUsernameLinks(){
	$('.user_link').click(function(e){
		e.preventDefault();
		loadTweets('user', $(this).data('username'));
	});
}