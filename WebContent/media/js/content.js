jQuery(document).ready(function($) {
	$(".content").click(function(event) {
		$('#content').load('Content', {
			content : $(this).data('content')
		});
	});
});