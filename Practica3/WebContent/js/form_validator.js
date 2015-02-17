	(function($,W,D)
			{
			    var FORM = {};
			 
			    FORM.VALIDATE =
			    {
			        setupFormValidation: function()
			        {
			            //form validation rules
			            $("#registerForm").validate({
			                rules: {
			                    fullName: "required",
			                	username: {
			                		required: true,
			                		minlength: 4
			                	},
			                	mail: {
			                	    required: true,
			                	    email: true
			                	},
			                	pwd: {
			                	    required: true,
                        			minlength: 5
			                	},
			                	pwd_check: {
			                		required: true,
			                	    equalTo: "#pwd"
			                	}
			                },
			                messages: {
			                	fullName: "entra el teu nom sencer",
			                	username: "entra un usuari de no menys de 4 caràcters",
			                	mail:
				                 {
				                    required: "entra una direcció de correu",
				                    email: "el correu entrat no és vàlid"
				                 },
			                	pwd: "entra una contrasenya de no menys de 5 caràcters",
			                	pwd_check: "..les contrasenyes han de coincidir ¬¬"
			                },
			                submitHandler: function(form) {
			                   // form.submit();
								$('#content').load('formcontroller',
										$("#registerForm").serialize());
			                }
			            });
			        },
			        validateUsername: function()
			        {
						$('#username').focusout(function() {
							$.ajax({
								url : "formcontroller",
								type : "POST",
								data : {
									action: 'validateUsername',
									data: $('#username').val()
								},
								success : function(response) {
									console.log(response);
									if(response == "valid"){
										console.log("valid username");
										$("#usernameAjaxError").empty();
									}else{
										console.log("invalid username");
										$("#usernameAjaxError").text("usuari ja registrat");
									}
								},
								error: function(response){
									console.log("error AJAX validateUsername");
								}
							});
						});
			        },
			        validateMail: function()
			        {
						$('#mail').focusout(function() {
							$.ajax({
								url : "formcontroller",
								type : "POST",
								data : {
									action: 'validateMail',
									data: $('#mail').val()
								},
								success : function(response) {
									console.log(response);
									if(response == "valid"){
										console.log("valid mail");
										$("#mailAjaxError").empty();
									}else{
										console.log("invalid mail");
										$("#mailAjaxError").text("correu ja registrat");
									}
								},
								error: function(response){
									console.log("error AJAX validateMail");
								}
							});
						});
			        }
			        
			    }
			 
			    //when the dom has loaded setup form validation rules
			    $(D).ready(function($) {
			        FORM.VALIDATE.setupFormValidation();
			        FORM.VALIDATE.validateUsername();
			        FORM.VALIDATE.validateMail();
			    });	    
			    
			 
			})(jQuery, window, document);