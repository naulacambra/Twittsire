jQuery(document).ready(
		function($) {
			function isScrolledIntoView(elem) {
				/*
				 * var $elem = $(elem); var $window = $(window);
				 * 
				 * var docViewTop = $(window).scrollTop(); var docViewBottom =
				 * docViewTop + $(window).height();
				 * 
				 * var elemTop = $(elem).offset().top; var elemBottom = elemTop +
				 * $(elem).height();
				 */

				return (($(elem).height() <= $(window).scrollTop()
						+ $(window).height()) && ($(elem).offset().top >= $(
						window).scrollTop()));
			}

			$(window).scroll(function() {
				var last_tweet = $('.tweet:last-child');
				if (isScrolledIntoView(last_tweet)) {
					/* Load next tweets */
				}
			});
		});