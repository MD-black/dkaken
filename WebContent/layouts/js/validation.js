/*global console, alert, $*/

/*
	File Name 		: validation.js
	Dev Name  		: Mohammed Darwish
	Creation Date 	: 11/7/2017
*/

$(document).ready(function(){
	
	/*Get the web browser language for the current user.*/
	var userLang = $('html').attr('lang');
	
	/*$("form").find('input[type=text],textarea,select').filter(':visible:first').focus();*/
	
	/*Check the password Strength on keyup*/
	$('#password').keyup(function() {
		$('#result').html(checkStrength($('#password').val()))
	});
	
	/*Start Login validation*/
	var login_usernameError = true;
	var login_passwordError = true;
	
	$(".login_username").blur(function(){
		var errorMsg = '';
		if( $(this).val() == '' ){
			if( userLang == "en" ){
				errorMsg = "<p>Username can't be empty.</p>";
			}else{
				errorMsg = "<p>\u0627\u0633\u0645 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u064a\u062c\u0628 \u0623\u0646 \u0644\u0627 \u064a\u0643\u0648\u0646 \u0641\u0627\u0631\u063a</p>";
			}
		}
		if($(this).val() != '' ){
			if($(this).val().length < 5 ){
				if( userLang == "en" ){
					errorMsg = errorMsg + "<p>Username can't be less than 5 letters.</p>";
				}else{
					errorMsg = errorMsg + "<p>\u0627\u0633\u0645 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u064a\u062c\u0628 \u0623\u0646 \u0644\u0627 \u064a\u0643\u0648\u0646 \u0623\u0642\u0644 \u0645\u0646 \u0035 \u062d\u0631\u0648\u0641</p>";
				}
			}
			
			if( $(this).val().length > 20 ){
				if( userLang == "en" ){
					errorMsg = errorMsg + "<p>Username can't be greater than 20 letters.</p>";
				}else{
					errorMsg = errorMsg + "<p>\u0627\u0633\u0645 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u064a\u062c\u0628 \u0623\u0646 \u0644\u0627 \u064a\u0643\u0648\u0646 \u0623\u0643\u062b\u0631 \u0645\u0646 \u0032\u0030 \u062d\u0631\u0641</p>";
				}
			}
		}
		
		if(errorMsg != ''){
			addError($(this), errorMsg);
			login_usernameError = true;
		}else{
			removeError($(this));
			login_usernameError = false;
		}
	});
	
	$(".login_password").blur(function(){
		var errorMsg = '';
		if($(this).val() != '' ){
			if( $(this).val() == '' ){
				if( userLang == "en" ){
					errorMsg = "<p>Password can't be empty.</p>";
				}else{
					errorMsg = "<p>\u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u062a\u0643\u0648\u0646 \u0641\u0627\u0631\u063a\u0647</p>";
				}
			}
			if($(this).val() != '' ){
				if($(this).val().length < 5 ){
					if( userLang == "en" ){
						errorMsg = errorMsg + "<p>Password can't be less than 5 letters.</p>";
					}else{
						errorMsg = errorMsg + "<p>\u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u062a\u0643\u0648\u0646 \u0623\u0642\u0644 \u0645\u0646 \u0035 \u062d\u0631\u0648\u0641</p>";
					}
				}
				if( $(this).val().length > 20 ){
					if( userLang == "en" ){
						errorMsg = errorMsg + "<p>Password can't be greater than 20 letters.</p>";
					}else{
						errorMsg = errorMsg + "<p>\u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u062a\u0643\u0648\u0646 \u0623\u0643\u062b\u0631 \u0645\u0646 \u0032\u0030 \u062d\u0631\u0641</p>";
					}
				}
			}
		}
		
		if(errorMsg != ''){
			addError($(this), errorMsg);
			login_passwordError = true;
		}else{
			removeError($(this));
			$("#result").html('');
			login_passwordError = false;
		}
	});
	
	$(".login-form").submit(function(event){
		$(".login_username").blur();
		$(".login_password").blur();
		if( login_usernameError === true || login_passwordError === true ){
			event.preventDefault();
			$(this).find('input[type=text],textarea,select').filter(':visible:first').blur();
		}
	});
	
	
	/*End Login validation*/
	
	/*Start Signup validation*/
	var signup_usernameError = true;
	var signup_passwordError = true;
	var signup_emailError = true;
	
	$(".signup_username").blur(function(){
		var errorMsg = '';
		if( $(this).val() == '' ){
			if( userLang == "en" ){
				errorMsg = "<p>Username can't be empty.</p>";
			}else{
				errorMsg = "<p>\u0627\u0633\u0645 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0641\u0627\u0631\u063a</p>";
			}
		}
		if($(this).val() != '' ){
			if($(this).val().length < 5 ){
				if( userLang == "en" ){
					errorMsg = errorMsg + "<p>Username can't be less than 5 letters.</p>";
				}else{
					errorMsg = errorMsg + "<p>\u0627\u0633\u0645 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u0644\u0627 \u064a\u062c\u0628 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0642\u0644 \u0645\u0646 \u0035 \u062d\u0631\u0648\u0641</p>";
				}
			}
			
			if( $(this).val().length > 20 ){
				if( userLang == "en" ){
					errorMsg = errorMsg + "<p>Username can't be greater than 20 letters.</p>";
				}else{
					errorMsg = errorMsg + "<p>\u0627\u0633\u0645 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u0644\u0627 \u064a\u062c\u0628 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0643\u062b\u0631 \u0645\u0646 \u0032\u0030 \u062d\u0631\u0641</p>";
				}
			}
		}
		
		if(errorMsg != ''){
			addError($(this), errorMsg);
			signup_usernameError = true;
		}else{
			removeError($(this));
			signup_usernameError = false;
		}
	});
	
	$(".signup_password").blur(function(){
		var errorMsg = '';	
		
		if($(this).val() != '' ){
			if( checkStrength($(this).val()) === "Too short" ||  checkStrength($(this).val()) === "\u0635\u063a\u064a\u0631\u0629 \u062c\u062f\u0627"){
				if( userLang == "en" ){
					errorMsg = errorMsg + "<p>This password is too short.</p>";
				}else{
					errorMsg = errorMsg + "<p>\u0639\u062f\u062f \u062d\u0631\u0648\u0641 \u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631 \u0642\u0644\u064a\u0644 \u062c\u062f\u0627</p>";
				}
			}
		}
		
		if(errorMsg != ''){
			addError($(this), errorMsg);
			signup_passwordError = true;
		}else{
			removeError($(this));
			$("#result").html('');
			signup_passwordError = false;
		}
	});
	
	
	$(".signup_email").blur(function(){
		var errorMsg = '';
		if( $(this).val() == '' ){
			if( userLang == "en" ){
				errorMsg = "<p>Email can't be empty.</p>";
			}else{
				errorMsg = "<p>\u0627\u0644\u0628\u0631\u064a\u062f \u0627\u0644\u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0641\u0627\u0631\u063a</p>";
			}
		}
		
		if($(this).val() != ''){
			if( !isValidEmailAddress($(this).val()) ){
				if( userLang == "en" ){
					errorMsg = "<p>Please enter a valid email address.</p>";
				}else{
					errorMsg = "<p>\u0627\u0644\u0631\u062c\u0627\u0621 \u0625\u062f\u062e\u0627\u0644 \u0628\u0631\u064a\u062f \u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a \u0641\u0639\u0627\u0644</p>";
				}
			}
		}
		
		if(errorMsg != ''){
			addError($(this), errorMsg);
			signup_emailError = true;
		}else{
			removeError($(this));
			signup_emailError = false;
		}
	});

	
	$(".signup-form").submit(function(event){
		$(".signup_username").blur();
		$(".signup_password").blur();
		$(".signup_email").blur();
		if( signup_usernameError === true || signup_passwordError === true || signup_emailError === true){
			event.preventDefault();
			$(this).find('input[type=text],textarea,select').filter(':visible:first').blur();
		}
	});
	/*End Signup validation*/
	
	/*Start users validation*/
	var users_UsernameError = true;
	var users_PasswordError = true;
	var users_EmailError = true;
	var users_FullNameError = true;
	
	$(".users_username").blur(function(){
		var errorMsg = '';
		if( $(this).val() == '' ){
			if( userLang == "en" ){
				errorMsg = "<p>Username can't be empty.</p>";
			}else{
				errorMsg = "<p>\u0627\u0633\u0645 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u064a\u062c\u0628 \u0623\u0646 \u0644\u0627 \u064a\u0643\u0648\u0646 \u0641\u0627\u0631\u063a</p>";
			}
		}
		if($(this).val() != '' ){
			if($(this).val().length < 5 ){
				if( userLang == "en" ){
					errorMsg = errorMsg + "<p>Username can't be less than 5 letters.</p>";
				}else{
					errorMsg = errorMsg + "<p>\u0627\u0633\u0645 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u064a\u062c\u0628 \u0623\u0646 \u0644\u0627 \u064a\u0643\u0648\u0646 \u0623\u0642\u0644 \u0645\u0646 \u0035 \u062d\u0631\u0648\u0641</p>";
				}
			}
			
			if( $(this).val().length > 20 ){
				if( userLang == "en" ){
					errorMsg = errorMsg + "<p>Username can't be greater than 20 letters.</p>";
				}else{
					errorMsg = errorMsg + "<p>\u0627\u0633\u0645 \u0627\u0644\u0645\u0633\u062a\u062e\u062f\u0645 \u064a\u062c\u0628 \u0623\u0646 \u0644\u0627 \u064a\u0643\u0648\u0646 \u0623\u0643\u062b\u0631 \u0645\u0646 \u0032\u0030 \u062d\u0631\u0641</p>";
				}
			}
		}
		
		if(errorMsg != ''){
			addError($(this), errorMsg);
			users_UsernameError = true;
		}else{
			removeError($(this));
			users_UsernameError = false;
		}
	});
	
	$(".users_password").blur(function(){
		var errorMsg = '';
		if($(this).val() != '' ){
			if( checkStrength($(this).val()) === "Too short" || checkStrength($(this).val()) === "\u0635\u063a\u064a\u0631\u0629 \u062c\u062f\u0627" ){
				if( userLang == "en" ){
					errorMsg = errorMsg + "<p>This password is too short.</p>";
				}else{
					errorMsg = errorMsg + "<p>\u0639\u062f\u062f \u062d\u0631\u0648\u0641 \u0643\u0644\u0645\u0629 \u0627\u0644\u0645\u0631\u0648\u0631 \u0642\u0644\u064a\u0644 \u062c\u062f\u0627</p>";
				}
			}
		}
		
		if(errorMsg != ''){
			addError($(this), errorMsg);
			users_PasswordError = true;
		}else{
			removeError($(this));
			$("#result").html('');
			users_PasswordError = false;
		}
	});
	
	
	
	$(".users_email").blur(function(){
		var errorMsg = '';
		if( $(this).val() == '' ){
			if( userLang == "en" ){
				errorMsg = "<p>Email can't be empty.</p>";
			}else{
				errorMsg = "<p>\u0627\u0644\u0628\u0631\u064a\u062f \u0627\u0644\u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0641\u0627\u0631\u063a</p>";
			}	
		}
		
		if($(this).val() != ''){
			if( !isValidEmailAddress($(this).val()) ){
				if( userLang == "en" ){
					errorMsg = "<p>Please enter a valid email address.</p>";
				}else{
					errorMsg = "<p>\u0627\u0644\u0631\u062c\u0627\u0621 \u0625\u062f\u062e\u0627\u0644 \u0628\u0631\u064a\u062f \u0625\u0644\u0643\u062a\u0631\u0648\u0646\u064a \u0641\u0639\u0627\u0644</p>";
				}
			}
		}
		
		if(errorMsg != ''){
			addError($(this), errorMsg);
			users_EmailError = true;
		}else{
			removeError($(this));
			users_EmailError = false;
		}
	});
	
	$(".users_fullName").blur(function(){
		var errorMsg = '';
		if( $(this).val() == '' ){
			if( userLang == "en" ){
				errorMsg = "<p>Full name can't be empty.</p>";
			}else{
				errorMsg = "<p>\u0627\u0644\u0625\u0633\u0645 \u0627\u0644\u0643\u0627\u0645\u0644 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0641\u0627\u0631\u063a</p>";
			}
		}
		if( $(this).val() != '' ){
			if( $(this).val().length < 10 ){
				if( userLang == "en" ){
					errorMsg = "<p>Full name can't be less than 10 letters.</p>";
				}else{
					errorMsg = "<p>\u0627\u0644\u0625\u0633\u0645 \u0627\u0644\u0643\u0627\u0645\u0644 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0642\u0644 \u0645\u0646 \u0031\u0030 \u062d\u0631\u0648\u0641</p>";
				}
			}
			
			if( $(this).val().length > 100 ){
				if( userLang == "en" ){
					errorMsg = "<p>Full name can't be greater than 100 letters.</p>";
				}else{
					errorMsg = "<p>\u0627\u0644\u0625\u0633\u0645 \u0627\u0644\u0643\u0627\u0645\u0644 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0643\u062b\u0631 \u0645\u0646 \u0031\u0030\u0030 \u062d\u0631\u0641</p>";
				}
			}
		}
		
		if(errorMsg != ''){
			addError($(this), errorMsg);
			users_FullNameError = true;
		}else{
			removeError($(this));
			users_FullNameError = false;
		}
	});
	
	$(".users-form").submit(function(event){
		$(".users_username").blur();
		$(".users_password").blur();
		$(".users_email").blur();
		$(".users_fullName").blur();
		if( users_UsernameError === true || 
			users_PasswordError === true || 	
			users_EmailError === true || 
			users_FullNameError === true){
			event.preventDefault();
			$(this).find('input[type=text],textarea,select').filter(':visible:first').blur();
		}
	});
	/*End users validation*/ 
	
	
	/*Start new item validation*/
	var newitemNameError = true;
	var newitemDescriptionError = true;
	var newitemPriceError = true;
	var newitemCountryError = true;
	
	$(".new-item-name").blur(function(){
		var errorMsg = '';
		if( $(this).val() == '' ){
			if( userLang == "en" ){
				errorMsg = "<p>Name can't be empty.</p>";
			}else{
				errorMsg = "<p>\u0627\u0644\u0625\u0633\u0645 \u064a\u062c\u0628 \u0623\u0646 \u0644\u0627 \u064a\u0643\u0648\u0646 \u0641\u0627\u0631\u063a</p>";
			}
		}
		if( $(this).val() != '' ){
			if($(this).val().length < 2 ){
				if( userLang == "en" ){
					errorMsg = errorMsg + "<p>Name can't be less than 2 letters.</p>";
				}else{
					errorMsg = errorMsg + "<p>\u0627\u0644\u0625\u0633\u0645 \u064a\u062c\u0628 \u0623\u0646 \u0644\u0627 \u064a\u0643\u0648\u0646 \u0623\u0642\u0644 \u0645\u0646 \u062d\u0631\u0641\u064a\u0646</p>";
				}
			}
			if( $(this).val().length > 50 ){
				if( userLang == "en" ){
					errorMsg = errorMsg + "<p>Name can't be greater than 50 letters.</p>";
				}else{
					errorMsg = errorMsg + "<p>\u0627\u0644\u0625\u0633\u0645 \u064a\u062c\u0628 \u0623\u0646 \u0644\u0627 \u064a\u0643\u0648\u0646 \u0623\u0643\u062b\u0631 \u0645\u0646 \u0035\u0030 \u062d\u0631\u0641</p>";
				}
			}
		}
		if(errorMsg != ''){
			addError($(this), errorMsg);
			newitemNameError = true;
		}else{
			removeError($(this));
			newitemNameError = false;
		}
	});
	
	$(".new-item-description").blur(function(){
		var errorMsg = '';
		if( $(this).val() == '' ){
			if( userLang == "en" ){
				errorMsg = "<p>Description can't be empty.</p>";
			}else{
				errorMsg = "<p>\u0627\u0644\u0648\u0635\u0641 \u064a\u062c\u0628 \u0623\u0646 \u0644\u0627 \u064a\u0643\u0648\u0646 \u0641\u0627\u0631\u063a</p>";
			}
		}
		if( $(this).val() != '' ){
			if($(this).val().length < 10 ){
				if( userLang == "en" ){
					errorMsg = errorMsg + "<p>Description can't be less than 10 letters.</p>";
				}else{
					errorMsg = errorMsg + "<p>\u0627\u0644\u0648\u0635\u0641 \u064a\u062c\u0628 \u0623\u0646 \u0644\u0627 \u064a\u0643\u0648\u0646 \u0623\u0642\u0644 \u0645\u0646 \u0031\u0030 \u062d\u0631\u0648\u0641</p>";
				}
			}
			if( $(this).val().length > 200 ){
				if( userLang == "en" ){
					errorMsg = errorMsg + "<p>Description can't be greater than 200 letters.</p>";
				}else{
					errorMsg = errorMsg + "<p>\u0627\u0644\u0648\u0635\u0641 \u064a\u062c\u0628 \u0623\u0646 \u0644\u0627 \u064a\u0643\u0648\u0646 \u0623\u0643\u062b\u0631 \u0645\u0646 \u0032\u0030\u0030 \u062d\u0631\u0641</p>";
				}
			}
		}
		if(errorMsg != ''){
			addError($(this), errorMsg);
			newitemDescriptionError = true;
		}else{
			removeError($(this));
			newitemDescriptionError = false;
		}
	});
	
	$(".new-item-price").blur(function(){
		var errorMsg = '';
		if( $(this).val() == '' ){
			if( userLang == "en" ){
				errorMsg = "<p>Price can't be empty.</p>";
			}else{
				errorMsg = "<p>\u0627\u0644\u0633\u0639\u0631 \u064a\u062c\u0628 \u0623\u0646 \u0644\u0627 \u064a\u0643\u0648\u0646 \u0641\u0627\u0631\u063a</p>";
			}
		}
		if( $(this).val() != '' ){
			if( isNaN($(this).val()) ){
				if( userLang == "en" ){
					errorMsg = "<p>Price should be numbers.</p>";
				}else{
					errorMsg = "<p>\u0627\u0644\u0633\u0639\u0631 \u064a\u062c\u0628 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0631\u0642\u0627\u0645 \u0635\u062d\u064a\u062d\u0629</p>";
				}
			}
			if( !isNaN($(this).val()) ){
				if($(this).val() > 9999999){
					if( userLang == "en" ){
						errorMsg = "<p>Price can't be greater than 9999999.</p>";
					}else{
						errorMsg = "<p>\u0627\u0644\u0633\u0639\u0631 \u064a\u062c\u0628 \u0623\u0646 \u0644\u0627 \u064a\u0643\u0648\u0646 \u0623\u0643\u0628\u0631 \u0645\u0646 \u0039\u0039\u0039\u0039\u0039\u0039\u0039</p>";
					}
				}
				if($(this).val() < 0){
					if( userLang == "en" ){
						errorMsg = "<p>Price can't be less than 0.</p>";
					}else{
						errorMsg = "<p>\u0627\u0644\u0633\u0639\u0631 \u064a\u062c\u0628 \u0623\u0646 \u0644\u0627 \u064a\u0643\u0648\u0646 \u0623\u0642\u0644 \u0645\u0646 \u0030</p>";
					}
				}
			}
		}
		if(errorMsg != ''){
			addError($(this), errorMsg);
			newitemPriceError = true;
		}else{
			removeError($(this));
			newitemPriceError = false;
		}
	});
	
	$(".new-item-country").blur(function(){
		var errorMsg = '';
		if( $(this).val() == '' ){
			if( userLang == "en" ){
				errorMsg = "<p>Country can't be empty.</p>";
			}else{
				errorMsg = "<p>\u0627\u0633\u0645 \u0627\u0644\u0628\u0644\u062f \u0627\u0644\u0645\u0635\u0646\u0639 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0641\u0627\u0631\u063a</p>";
			}
		}
		if(errorMsg != ''){
			addError($(this), errorMsg);
			newitemCountryError = true;
		}else{
			removeError($(this));
			newitemCountryError = false;
		}
	});
		
	$(".new-item-form").submit(function(event){
		$(".new-item-name").blur();
		$(".new-item-description").blur();
		$(".new-item-price").blur();
		$(".new-item-country").blur();
		
		if( newitemNameError === true || 
			newitemDescriptionError === true ||
			newitemPriceError === true ||
			newitemCountryError === true){
			
			event.preventDefault();
			$(this).find('input[type=text],textarea,select').filter(':visible:first').blur();
		}else{
			$(".new-item-category").prop("disabled", false);  
		}
	});
	/*End new item validation*/
	
	/*Start Comments validation*/
	var itemsCommentError = true;
	
	$(".items-comment").blur(function(){
		var errorMsg = '';
		if( $(this).val() == '' ){
			if(userLang == "en"){
				errorMsg = "<p>Comment can't be empty.</p>"; 
			}else{
				errorMsg = "<p>\u0627\u0644\u062a\u0639\u0644\u064a\u0642 \u064a\u062c\u0628 \u0623\u0646 \u0644\u0627 \u064a\u0643\u0648\u0646 \u0641\u0627\u0631\u063a</p>";
			}
		}
		if(errorMsg != ''){
			addError($(this), errorMsg);
			itemsCommentError = true;
		}else{
			removeError($(this));
			itemsCommentError = false;
		}
	});
	
	$(".comment-form").submit(function(event){
		$(".items-comment").blur();
		
		if( itemsCommentError === true){
			event.preventDefault();
			$(this).find('input[type=text],textarea,select').filter(':visible:first').blur();
		}
	});
	/*End Comments validation*/
	
	/*addError function*/
	function addError(object, errorMsg){
		
		var userLang = $('html').attr('lang');
		
		object.parent().find(".asterisk").fadeIn(100);
		if( object.parent().children(".client-errors").length ){
			object.parent().children(".client-errors").remove();
		}
		
		if(object.is("select")){ 
			if(userLang == "en"){
				object.parent().children(".selectboxit-container").after("<div class='client-errors wow rotateInUpLeft'><div style='color:#a94442' class='alert alert-danger'>"+errorMsg+"</div></div>");
			}else{
				object.parent().children(".selectboxit-container").after("<div class='client-errors wow rotateInUpRight'><div style='color:#a94442' class='alert alert-danger'>"+errorMsg+"</div></div>");
			}
		}else{
			if(userLang == "en"){
				object.after("<div class='client-errors wow rotateInUpLeft'><div style='color:#a94442' class='alert alert-danger'>"+errorMsg+"</div></div>");
			}else{
				object.after("<div class='client-errors wow rotateInUpRight'><div style='color:#a94442' class='alert alert-danger'>"+errorMsg+"</div></div>");
			}
		}
	}
	
	/*removeError function*/
	function removeError(object){
		object.parent().find(".alert-danger").css({
			backgroundColor: "#1abc9c",
			borderColor: "#1abc9c"
		});
		object.parent().find(".client-errors").fadeOut(1000, function(){
			object.parent().find(".client-errors").remove();
		});
		object.parent().find(".asterisk").fadeOut(100);
	}
	
	/*Validate email regex function*/
	function isValidEmailAddress(emailAddress) {
	    var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
	    return pattern.test(emailAddress);
	};
	
	/*Check the password strength function*/
	function checkStrength(password) {
		var strength = 0;
		if (password.length < 6) {
			$('#result').removeClass();
			$('#result').addClass('short');
			if( userLang == "en" ){
				return 'Too short';
			}else{
				return '\u0635\u063a\u064a\u0631\u0629 \u062c\u062f\u0627';
			}
		}
		if (password.length > 7){
			strength += 1;
		}
		// If password contains both lower and uppercase characters, increase strength value.
		if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/)){
			strength += 1;
		}
		// If it has numbers and characters, increase strength value.
		if (password.match(/([a-zA-Z])/) && password.match(/([0-9])/)){
			strength += 1;
		}
		// If it has one special character, increase strength value.
		if (password.match(/([!,%,&,@,#,$,^,*,?,_,~])/)){
			strength += 1;
		}
		// If it has two special characters, increase strength value.
		if (password.match(/(.*[!,%,&,@,#,$,^,*,?,_,~].*[!,%,&,@,#,$,^,*,?,_,~])/)){
			strength += 1;
		}
		// Calculated strength value, we can return messages
		// If value is less than 2
		if (strength < 2) {
			$('#result').removeClass();
			$('#result').addClass('weak');
			if( userLang == "en" ){
				return 'Weak';
			}else{
				return '\u0636\u0639\u064a\u0641\u0629';
			}
		} else if (strength == 2) {
			$('#result').removeClass();
			$('#result').addClass('good');
			if( userLang == "en" ){
				return 'Good';
			}else{
				return '\u062c\u064a\u062f\u0629';
			}
		} else {
			$('#result').removeClass();
			$('#result').addClass('strong');
			if( userLang == "en" ){
				return 'Strong';
			}else{
				return '\u0642\u0648\u064a\u0629';
			}
		}
	}
	
});