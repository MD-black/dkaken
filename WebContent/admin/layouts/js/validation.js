/*global console, alert, $*/

/*
	File Name 		: validation.js
	Dev Name  		: Mohammed Darwish
	Creation Date 	: 11/7/2017
*/

$(document).ready(function(){
	
	/*Get the web browser language for the current admin.*/
	var userLang = $('html').attr('lang');
	
	/*Focus the first text,textarea,select input field in the page*/
	$("form").find('input[type=text],textarea,select').filter(':visible:first').focus();
	
	/*Check the password Strength on keyup*/
	$('#password').keyup(function() {
		$('#result').html(checkStrength($('#password').val()))
	});
	
	
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
	
	
	/*Start categories validation*/
	var categoryNameError = true;
	var categoryNameArError = true;
	var categoryOrderingError = true;
	var categoryDiscriptionError = true;
	var categoryDiscriptionArError = true;
	
	$(".category-name").blur(function(){
		var errorMsg = '';
		if( $(this).val() == '' ){
			if( userLang == "en" ){
				errorMsg = "<p>Name can't be empty.</p>";
			}else{
				errorMsg = "<p>\u0627\u0644\u0625\u0633\u0645 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0641\u0627\u0631\u063a</p>";
			}
		}
		if( $(this).val() != '' ){
			if($(this).val().length < 2 ){
				if( userLang == "en" ){
					errorMsg = errorMsg + "<p>Name can't be less than 2 letters.</p>";
				}else{
					errorMsg = errorMsg + "<p>\u0627\u0644\u0625\u0633\u0645 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0642\u0644 \u0645\u0646 \u062d\u0631\u0641\u064a\u0646</p>";
				}
			}
			if( $(this).val().length > 50 ){
				if( userLang == "en" ){
					errorMsg = errorMsg + "<p>Name can't be greater than 50 letters.</p>";
				}else{
					errorMsg = errorMsg + "<p>\u0627\u0644\u0625\u0633\u0645 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0643\u062b\u0631 \u0645\u0646 \u0035\u0030 \u062d\u0631\u0641</p>";
				}
			}
		}
		if(errorMsg != ''){
			addError($(this), errorMsg);
			categoryNameError = true;
		}else{
			removeError($(this));
			categoryNameError = false;
		}
	});
	
	$(".category-nameAr").blur(function(){
		var errorMsg = '';
		if( $(this).val() == '' ){
			if( userLang == "en" ){
				errorMsg = "<p>Arabic Name can't be empty.</p>";
			}else{
				errorMsg = "<p>\u0627\u0644\u0625\u0633\u0645 \u0628\u0627\u0644\u0639\u0631\u0628\u064a\u0629 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0641\u0627\u0631\u063a</p>";
			}
		}
		if( $(this).val() != '' ){
			if($(this).val().length < 2 ){
				if( userLang == "en" ){
					errorMsg = errorMsg + "<p>Arabic Name can't be less than 2 letters.</p>";
				}else{
					errorMsg = errorMsg + "<p>\u0627\u0644\u0625\u0633\u0645 \u0628\u0627\u0644\u0639\u0631\u0628\u064a\u0629 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0642\u0644 \u0645\u0646 \u062d\u0631\u0641\u064a\u0646</p>";
				}
			}
			if( $(this).val().length > 50 ){
				if( userLang == "en" ){
					errorMsg = errorMsg + "<p>Arabic Name can't be greater than 50 letters.</p>";
				}else{
					errorMsg = errorMsg + "<p>\u0627\u0644\u0625\u0633\u0645 \u0628\u0627\u0644\u0639\u0631\u0628\u064a\u0629 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0643\u062b\u0631 \u0645\u0646 \u0035\u0030 \u062d\u0631\u0641</p>";
				}
			}
		}
		if(errorMsg != ''){
			addError($(this), errorMsg);
			categoryNameArError = true;
		}else{
			removeError($(this));
			categoryNameArError = false;
		}
	});
	
	$(".category-description").blur(function(){
		var errorMsg = '';
		if( $(this).val() != '' ){
			if( $(this).val().length > 100 ){
				if( userLang == "en" ){
					errorMsg = errorMsg + "<p>Description can't be greater than 100 letters.</p>";
				}else{
					errorMsg = errorMsg + "<p>\u0648\u0635\u0641 \u0627\u0644\u0642\u0633\u0645 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0643\u062b\u0631 \u0645\u0646 \u0031\u0030\u0030 \u062d\u0631\u0641</p>";
				}
			}
		}
		if(errorMsg != ''){
			addError($(this), errorMsg);
			categoryDiscriptionError = true;
		}else{
			removeError($(this));
			categoryDiscriptionError = false;
		}
	});
	
	$(".category-descriptionAr").blur(function(){
		var errorMsg = '';
		if( $(this).val() != '' ){
			if( $(this).val().length > 100 ){
				if( userLang == "en" ){
					errorMsg = errorMsg + "<p>Arabic Description can't be greater than 100 letters.</p>";
				}else{
					errorMsg = errorMsg + "<p>\u0648\u0635\u0641 \u0627\u0644\u0642\u0633\u0645 \u0628\u0627\u0644\u0639\u0631\u0628\u064a\u0629 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0643\u062b\u0631 \u0645\u0646 \u0031\u0030\u0030 \u062d\u0631\u0641</p>";
				}
			}
		}
		if(errorMsg != ''){
			addError($(this), errorMsg);
			categoryDiscriptionArError = true;
		}else{
			removeError($(this));
			categoryDiscriptionArError = false;
		}
	});
	
	
	$(".category-ordering").blur(function(){
		var errorMsg = '';
		if( $(this).val() != '' ){
			if( isNaN($(this).val()) ){
				if( userLang == "en" ){
					errorMsg = "<p>Category order should be numbers.</p>";
				}else{
					errorMsg = "<p>\u062a\u0631\u062a\u064a\u0628 \u0627\u0644\u0642\u0633\u0645 \u064a\u062c\u0628 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0631\u0642\u0627\u0645</p>";
				}
			}
			if( !isNaN($(this).val()) ){
				if($(this).val() > 9999999){
					if( userLang == "en" ){
						errorMsg = "<p>Category order can't be greater than 9999999.</p>";
					}else{
						errorMsg = "<p>\u062a\u0631\u062a\u064a\u0628 \u0627\u0644\u0642\u0633\u0645 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0627\u0643\u0628\u0631 \u0645\u0646 \u0039\u0039\u0039\u0039\u0039\u0039\u0039</p>";
					}
				}
				if($(this).val() < 0){
					if( userLang == "en" ){
						errorMsg = "<p>Category order can't be less than 0.</p>";
					}else{
						errorMsg = "<p>\u062a\u0631\u062a\u064a\u0628 \u0627\u0644\u0642\u0633\u0645 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0642\u0644 \u0645\u0646 \u0030</p>";
					}
				}
			}
		}
		if(errorMsg != ''){
			addError($(this), errorMsg);
			categoryOrderingError = true;
		}else{
			removeError($(this));
			categoryOrderingError = false;
		}
	});
	
	$(".categories-form").submit(function(event){
		$(".category-name").blur();
		$(".category-nameAr").blur();
		$(".category-ordering").blur();
		$(".category-description").blur();
		$(".category-descriptionAr").blur();
		if( categoryNameError === true || 
			categoryNameArError === true ||	
			categoryOrderingError === true ||
			categoryDiscriptionError === true ||
			categoryDiscriptionArError === true){
			event.preventDefault();
			$(this).find('input[type=text],textarea,select').filter(':visible:first').blur();
		}
	});
	/*End categories validation*/
	
	/*Start items validation*/
	var itemNameError = true;
	var itemDescriptionError = true;
	var itemPriceError = true;
	var itemCountryError = true;
	var itemStatusValueError = true;
	var itemUserValueError = true;
	var itemCategoryValueError = true;
	
	$(".item-name").blur(function(){
		var errorMsg = '';
		if( $(this).val() == '' ){
			if( userLang == "en" ){
				errorMsg = "<p>Name can't be empty.</p>";
			}else{
				errorMsg = "<p>\u0627\u0644\u0625\u0633\u0645 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0641\u0627\u0631\u063a</p>";
			}
		}
		if( $(this).val() != '' ){
			if($(this).val().length < 2 ){
				if( userLang == "en" ){
					errorMsg = errorMsg + "<p>Name can't be less than 2 letters.</p>";
				}else{
					errorMsg = errorMsg + "<p>\u0627\u0644\u0625\u0633\u0645 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0642\u0644 \u0645\u0646 \u062d\u0631\u0641\u064a\u0646</p>";
				}
			}
			if( $(this).val().length > 50 ){
				if( userLang == "en" ){
					errorMsg = errorMsg + "<p>Name can't be greater than 50 letters.</p>";
				}else{
					errorMsg = errorMsg + "<p>\u0627\u0644\u0625\u0633\u0645 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0643\u062b\u0631 \u0645\u0646 \u0035\u0030 \u062d\u0631\u0641</p>";
				}
			}
		}
		if(errorMsg != ''){
			addError($(this), errorMsg);
			itemNameError = true;
		}else{
			removeError($(this));
			itemNameError = false;
		}
	});
	
	$(".item-description").blur(function(){
		var errorMsg = '';
		if( $(this).val() == '' ){
			if( userLang == "en" ){
				errorMsg = "<p>Description can't be empty.</p>";
			}else{
				errorMsg = "<p>\u0627\u0644\u0648\u0635\u0641 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0641\u0627\u0631\u063a</p>";
			}
		}
		if( $(this).val() != '' ){
			if($(this).val().length < 10 ){
				if( userLang == "en" ){
					errorMsg = errorMsg + "<p>Description can't be less than 10 letters.</p>";
				}else{
					errorMsg = errorMsg + "<p>\u0627\u0644\u0648\u0635\u0641 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0642\u0644 \u0645\u0646 \u0031\u0030 \u062d\u0631\u0648\u0641</p>";
				}
			}
			if( $(this).val().length > 1000 ){
				if( userLang == "en" ){
					errorMsg = errorMsg + "<p>Description can't be greater than 1000 letters.</p>";
				}else{
					errorMsg = errorMsg + "<p>\u0627\u0644\u0648\u0635\u0641 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0643\u062b\u0631 \u0645\u0646 \u0031\u0030\u0030\u0030 \u062d\u0631\u0641</p>";
				}
			}
		}
		if(errorMsg != ''){
			addError($(this), errorMsg);
			itemDescriptionError = true;
		}else{
			removeError($(this));
			itemDescriptionError = false;
		}
	});
	
	$(".item-price").blur(function(){
		var errorMsg = '';
		if( $(this).val() == '' ){
			if( userLang == "en" ){
				errorMsg = "<p>Price can't be empty.</p>";
			}else{
				errorMsg = "<p>\u0627\u0644\u0633\u0639\u0631 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0641\u0627\u0631\u063a</p>";
			}
		}
		if( $(this).val() != '' ){
			if( isNaN($(this).val()) ){
				if( userLang == "en" ){
					errorMsg = "<p>Price should be numbers.</p>";
				}else{
					errorMsg = "<p>\u0627\u0644\u0633\u0639\u0631 \u064a\u062c\u0628 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0631\u0642\u0627\u0645</p>";
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
						errorMsg = "<p>\u0627\u0644\u0633\u0639\u0631 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0642\u0644 \u0645\u0646 \u0030</p>";
					}
				}
			}
		}
		if(errorMsg != ''){
			addError($(this), errorMsg);
			itemPriceError = true;
		}else{
			removeError($(this));
			itemPriceError = false;
		}
	});
	
	$(".item-country").blur(function(){
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
			itemCountryError = true;
		}else{
			removeError($(this));
			itemCountryError = false;
		}
	});
	
	$(document).on("change", ".item-status", function(){
		$("#itemStatusValue").val( $(".item-status").val() );
		var errorMsg = '';
		if( $("#itemStatusValue").val() == '' || $("#itemStatusValue").val() == '0' ){
			if( userLang == "en" ){
				errorMsg = "<p>Please choose the status for this item.</p>";
			}else{
				errorMsg = "<p>\u0627\u0644\u0631\u062c\u0627\u0621 \u0627\u0644\u0642\u064a\u0627\u0645 \u0628\u0625\u062e\u062a\u064a\u0627\u0631 \u0627\u0644\u062d\u0627\u0644\u0629 \u0644\u0647\u0627\u0630\u0627 \u0627\u0644\u0625\u0639\u0644\u0627\u0646</p>";
			}
		}
		if(errorMsg != ''){
			addError($(this), errorMsg);
			itemStatusValueError = true;
		}else{
			removeError($(this));
			itemStatusValueError = false;
		}
		
		
	});
	$(document).on("change", ".item-user", function(){
		$("#itemUserValue").val( $(".item-user").val() );
		var errorMsg = '';
		if( $("#itemUserValue").val() == '' || $("#itemUserValue").val() == '0' ){
			if( userLang == "en" ){
				errorMsg = "<p>Please choose user for this item.</p>";
			}else{
				errorMsg = "<p>\u0627\u0644\u0631\u062c\u0627\u0621 \u0627\u0644\u0642\u064a\u0627\u0645 \u0628\u0625\u062e\u062a\u064a\u0627\u0631 \u0635\u0627\u062d\u0628 \u0647\u0627\u0630\u0627 \u0627\u0644\u0625\u0639\u0644\u0627\u0646</p>";
			}
		}
		if(errorMsg != ''){
			addError($(this), errorMsg);
			itemUserValueError = true;
		}else{
			removeError($(this));
			itemUserValueError = false;
		}
	});
	$(document).on("change", ".item-category", function(){
		$("#itemCategoryValue").val( $(".item-category").val() );
		var errorMsg = '';
		if( $("#itemCategoryValue").val() == '' || $("#itemCategoryValue").val() == '0' ){
			if( userLang == "en" ){
				errorMsg = "<p>Please choose category for this item.</p>";
			}else{
				errorMsg = "<p>\u0627\u0644\u0631\u062c\u0627\u0621 \u0627\u0644\u0642\u064a\u0627\u0645 \u0628\u0625\u062e\u062a\u064a\u0627\u0631 \u0642\u0633\u0645 \u0644\u0647\u0627\u0630\u0627 \u0627\u0644\u0625\u0639\u0644\u0627\u0646</p>";
			}
		}
		if(errorMsg != ''){
			addError($(this), errorMsg);
			itemCategoryValueError = true;
		}else{
			removeError($(this));
			itemCategoryValueError = false;
		}
	});
	
	
	$("#itemStatusValue").blur(function(){
		var errorMsg = '';
		if( $(this).val() == '' || $(this).val() == '0' ){
			if( userLang == "en" ){
				errorMsg = "<p>Please choose the status for this item.</p>";
			}else{
				errorMsg = "<p>\u0627\u0644\u0631\u062c\u0627\u0621 \u0627\u0644\u0642\u064a\u0627\u0645 \u0628\u0625\u062e\u062a\u064a\u0627\u0631 \u0627\u0644\u062d\u0627\u0644\u0629 \u0644\u0647\u0627\u0630\u0627 \u0627\u0644\u0625\u0639\u0644\u0627\u0646</p>";
			}
		}
		if(errorMsg != ''){
			addError($(this), errorMsg);
			itemStatusValueError = true;
		}else{
			removeError($(this));
			itemStatusValueError = false;
		}
	});
	
	$("#itemUserValue").blur(function(){
		var errorMsg = '';
		if( $(this).val() == '' || $(this).val() == '0' ){
			if( userLang == "en" ){
				errorMsg = "<p>Please choose user for this item.</p>";
			}else{
				errorMsg = "<p>\u0627\u0644\u0631\u062c\u0627\u0621 \u0627\u0644\u0642\u064a\u0627\u0645 \u0628\u0625\u062e\u062a\u064a\u0627\u0631 \u0635\u0627\u062d\u0628 \u0647\u0627\u0630\u0627 \u0627\u0644\u0625\u0639\u0644\u0627\u0646</p>";
			}
		}
		if(errorMsg != ''){
			addError($(this), errorMsg);
			itemUserValueError = true;
		}else{
			removeError($(this));
			itemUserValueError = false;
		}
	});	
	
	$("#itemCategoryValue").blur(function(){
		var errorMsg = '';
		if( $(this).val() == '' || $(this).val() == '0' ){
			if( userLang == "en" ){
				errorMsg = "<p>Please choose category for this item.</p>";
			}else{
				errorMsg = "<p>\u0627\u0644\u0631\u062c\u0627\u0621 \u0627\u0644\u0642\u064a\u0627\u0645 \u0628\u0625\u062e\u062a\u064a\u0627\u0631 \u0642\u0633\u0645 \u0644\u0647\u0627\u0630\u0627 \u0627\u0644\u0625\u0639\u0644\u0627\u0646</p>";
			}
		}
		if(errorMsg != ''){
			addError($(this), errorMsg);
			itemCategoryValueError = true;
		}else{
			removeError($(this));
			itemCategoryValueError = false;
		}
	});
	
	$(".items-form").submit(function(event){
		var action = $("#action").val();
		$(".item-name").blur();
		$(".item-description").blur();
		$(".item-price").blur();
		$(".item-country").blur();
		$("#itemStatusValue").blur();
		$("#itemUserValue").blur();
		$("#itemCategoryValue").blur();
		
		if(action === "add"){
			if( itemNameError === true || 
				itemDescriptionError === true ||
				itemPriceError === true ||
				itemCountryError === true ||
				itemStatusValueError === true ||
				itemUserValueError === true ||
				itemCategoryValueError === true){
				
				event.preventDefault();
				$(this).find('input[type=text],textarea,select').filter(':visible:first').blur();
			}
		}else{
			if( itemNameError === true || 
					itemDescriptionError === true ||
					itemPriceError === true ||
					itemCountryError === true){
					
					event.preventDefault();
					$(this).find('input[type=text],textarea,select').filter(':visible:first').blur();
				}
		}
	});
	/*End items validation*/
	
	
	/*Start comments validation*/
	var commentCommentError = true;
	
	$(".comments_comment").blur(function(){
		var errorMsg = '';
		if( $(this).val() == '' ){
			if( userLang == "en" ){
				errorMsg = "<p>Comment can't be empty.</p>";
			}else{
				errorMsg = "<p>\u0627\u0644\u062a\u0639\u0644\u064a\u0642 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0641\u0627\u0631\u063a</p>";
			}
		}
		if( $(this).val() != '' ){
			if( $(this).val().length > 1000 ){
				if( userLang == "en" ){
					errorMsg = errorMsg + "<p>Comment can't be greater than 1000 letters.</p>";
				}else{
					errorMsg = errorMsg + "<p>\u0627\u0644\u062a\u0639\u0644\u064a\u0642 \u0644\u0627 \u064a\u0645\u0643\u0646 \u0623\u0646 \u064a\u0643\u0648\u0646 \u0623\u0643\u062b\u0631 \u0645\u0646 \u0031\u0030\u0030\u0030 \u062d\u0631\u0641</p>";
				}
			}
		}
		if(errorMsg != ''){
			addError($(this), errorMsg);
			commentCommentError = true;
		}else{
			removeError($(this));
			commentCommentError = false;
		}
	});
	
	
	
	$(".comments-form").submit(function(event){
		$(".category-name").blur();
		if( commentCommentError === true ){
			event.preventDefault();
			$(this).find('input[type=text],textarea,select').filter(':visible:first').blur();
		}
	});
	/*End comments validation*/
	
	
	/*addError function*/
	function addError(object, errorMsg){
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