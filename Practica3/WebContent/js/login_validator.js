(function($, W, D) {
	var FORM = {};
	FORM.VALIDATE = {
		setupFormValidation : function() {
			// form validation rules
			$("#registerForm").validate(
					{
						rules : {
							fullName : "required",
							username : {
								required : true,
								minlength : 4
							},
							pwd : {
								required : true,
								minlength : 5
							}
						},
						messages : {
							username : "recorda, has de possar el teu usuari",
							pwd : "ep, no t'oblidis la contrassenya"
						},
						submitHandler : function(form) {
							$('#content').load('logincontroller',
									$("#registerForm").serialize());

						}
					});
		}
	}
	// do when the dom has loaded setup form validation rules
	$(D).ready(function($) {
		FORM.VALIDATE.setupFormValidation();
	});
})(jQuery, window, document);