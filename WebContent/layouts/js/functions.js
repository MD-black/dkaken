/*global console, alert, $*/

/*
	File Name 		: functions.js
	Dev Name  		: Mohammed Darwish
	Creation Date 	: 11/7/2017
*/

$(document).ready(function(){ 
	
	/*Trigger the wow.js animations*/
	new WOW().init();
	
	/*Get the web browser language for the current user.*/
	var userLang = $('html').attr('lang');
	
	/*Trigger nice scroll*/
	$("body").niceScroll({
		cursorcolor: "rgb(26, 188, 156)",
		cursorwidth: "5px",
		cursorheight: "100px",
		cursorborder: "1px solid rgb(26, 188, 156)",
		cursorborderradius: "0"
	});
	
	/*Show and hide scroll top div on window scrolling*/
	$(window).on("scroll", function(){
		if( $(this).scrollTop() >= 1000 ){
			$(".scroll-top").fadeIn();
		}else{
			$(".scroll-top").fadeOut();
		}
	});
	
	/*Change window scrollTop to 0 on scrollTop div click*/
	$(".scroll-top").on("click", function(){
		$("html,body").animate({scrollTop: 0}, 1000);
	});
	
	/*Scroll to the top of the window on page paginate*/
	$(document).on("click", ".easyPaginateNav a", function(){
		$("html,body").animate({scrollTop: $(".easyPaginateList").offset().top}, 50);
	});
	
	/*Show and hide login and signup forms*/
	$(".forms span").on("click", function(){
		console.log( $(this).data("value")  );
		
		$(this).addClass("active").siblings().removeClass("active");
		/*$("form").css("display", "none");*/
		$(".login-form, .signup-form").css("display", "none");
		$("." + $(this).data("value") ).fadeIn(100);
		
	});
	
	/*Trigger the selectboxit*/
	$("select").selectBoxIt({
		autoWidth: false
	});
	
	/*Success and Failer message delete in 5 secondss*/ 
	$(".success-msg").delay(3000).fadeOut(400);  
	$(".fail-msg").delay(3000).fadeOut(400);   
	
	/*Show asterisks on required input fields.*/ 
	$("input, textarea").each(function(){
		if( $(this).hasClass("required") ){
			$(this).after("<span class='asterisk'>*</span>");
		}
	});
	
	/*Show password text on eye icon hover*/
	$(".show-password").hover(function(){
		$(this).parent().find(".password").attr("type", "text");
	}, function(){
		$(this).parent().find(".password").attr("type", "password");
	});
	
	/*Add class to the selected table row to mark it.*/
	$(".comments .main-table tr, .users .main-table tr, .items .main-table tr").on("click", function(){
		$(this).addClass("selected").siblings().removeClass("selected");
	});
	
	/*Show the confirm message on delete*/ 
	$(".confirm").on("click", function(){
		return confirm("Are you sure you want to delete ?");
	});
	
	/*Trigger the data table*/
	$('#table_id').DataTable({
        select: {
        	style: 'single'
    	},
    	"lengthMenu": [ 5, 10, 15, 20, 25 ]
	});
	
	/*Adjust the easyPaginate*/
	$('#easyPaginate').easyPaginate({
	    paginateElement: '.row',
	    elementsPerPage: 3,
	    effect: 'climb'
	});
	 
	/*Adjust the categoryPaginate*/
	$('#categoryPaginate').easyPaginate({
	    paginateElement: '.row',
	    elementsPerPage: 10,
	    effect: 'climb'
	});
	 
	/*Adjust the latestPaginate*/
	 $('#latestPaginate').easyPaginate({
        paginateElement: '.row',
        elementsPerPage: 20,
        effect: 'climb'
	 }); 
	 
	 /*Adjust the commentPaginate*/
	 $('#commentPaginate').easyPaginate({
        paginateElement: '.comm',
        elementsPerPage: 5,
        effect: 'climb'
    });
	 
	 /*Live adding for new items.*/
	$(".live").on("keyup", function(){
		$($(this).data("class")).text( $(this).val() );
	});
	
	/*Show waitting status for user item on hover*/
	$(".item-not-approved").hover(function(){
		$(this).parent().find(".waitting").fadeIn();
	}, function(){
		$(this).parent().find(".waitting").fadeOut();
	});
	
	/*Adding font icons to categories in the home page*/
	$(".home-page .category .parent a[class='cat-1']").before("<i class='fa fa-car' style='color:#e74c3c'></i>"); 
	$(".home-page .category .parent a[class='cat-2']").before("<i class='fa fa-mobile fa-lg' style='color: #d9796f; font-size: 28px;'></i>");
	$(".home-page .category .parent a[class='cat-3']").before("<i class='fa fa-gamepad fa-lg' style='color:#ab47bc'></i>");
	$(".home-page .category .parent a[class='cat-4']").before("<i class='fa fa-laptop fa-lg' style='color:#7e57c2'></i>");
	$(".home-page .category .parent a[class='cat-5']").before("<i class='fa fa-home fa-lg' style='color:#5c6bc0'></i>");
	$(".home-page .category .parent a[class='cat-6']").before("<i class='fa fa-home fa-lg' style='color:#2196f3'></i>");
	$(".home-page .category .parent a[class='cat-7']").before("<i class='fa fa-bed fa-lg' style='color:#26c6da'></i>");
	$(".home-page .category .parent a[class='cat-8']").before("<i class='fa fa-female fa-lg' style='color:#0cd3c0'></i>");
	$(".home-page .category .parent a[class='cat-9']").before("<i class='fa fa-male fa-lg' style='color:#66bb9a'></i>"); 
	$(".home-page .category .parent a[class='cat-10']").before("<i class='fa fa-child fa-lg' style='color:#d4e157'></i>");
	$(".home-page .category .parent a[class='cat-11']").before("<i class='fa fa-lemon-o fa-lg' style='color:#fdd835'></i>"); 
	$(".home-page .category .parent a[class='cat-12']").before("<i class='fa fa-gavel fa-lg' style='color:#ffb300'></i>"); 
	$(".home-page .category .parent a[class='cat-13']").before("<i class='fa fa-briefcase fa-lg' style='color:#fb8c00'></i>");
	$(".home-page .category .parent a[class='cat-14']").before("<i class='fa fa-search fa-lg' style='color:#ff7043'></i>");
	$(".home-page .category .parent a[class='cat-15']").before("<i class='fa fa-tripadvisor fa-lg' style='color:#8d6e63'></i>");
	$(".home-page .category .parent a[class='cat-16']").before("<i class='fa fa-bicycle fa-lg' style='color:#78909c'></i>");
	$(".home-page .category .parent a[class='cat-17']").before("<i class='fa fa-industry fa-lg' style='color:#377ea2'></i>");
	$(".home-page .category .parent a[class='cat-18']").before("<i class='fa fa-smile-o fa-lg' style='color:#f6a623'></i>"); 
	
	
	 
	/*Show item description on read more click.*/
	$(".item-description .read-more").on("click", function(){
		var whitspaceStyle = $(this).parent(".item-description").css("white-space");
		if( whitspaceStyle == "nowrap" ){
			$(this).parent(".item-description").css("white-space", "pre-line");
			if(userLang == "en"){
				$(this).text('Read Less');
			}else{
				$(this).text('\u0625\u062e\u0641\u0627\u0621 \u0627\u0644\u0648\u0635\u0641');
			}
		}else{
			$(this).parent(".item-description").css("white-space", "nowrap");
			if(userLang == "en"){
				$(this).text('Read More');
			}else{
				$(this).text('\u0625\u0642\u0631\u0623 \u0627\u0644\u0645\u0632\u064a\u062f');
			}
		}
	});
	

	/* Sending id to the modal using ajax on More click */
	$(".parent .more").on("click", function(){
		var selectedCategoryId = $(this).data("id");
		var selectedCategoryName = $(this).data("name");
		
		$.ajax({
            url: 'home_servlet.do?action=allChilds',
            type: 'POST',
            data: {"selectedCategoryId":selectedCategoryId, "selectedCategoryName":selectedCategoryName},
            success: function(r){
				
            	var modalBody = $(r).find(".modal-body");
            	var modalBodyDom = modalBody.get( 0 );
            	
            	var modalTitle = $(r).find(".modal-title");
            	var modalTitleDom = modalTitle.get( 0 );
            	$(".modal-body").html(modalBodyDom); 
            	$(".modal-title").html(modalTitleDom);
			}
        });
	});
	
	/*Click the user profile pic input file on the user image click*/
	$(".profile .user-profile-pic img").on("click", function(){
		$(".profile .profile-pic").click();
	});
	
	
	/*Send user new image to the server on image input file changing*/	
	$(document).on("change", ".profile .profile-pic", function(){
		
		var profilePic = $('#profile-pic')[0].files[0];
		var id = $("#id").val();
		
		 if( window.FormData ) {
	        formdata = new FormData();
	        formdata.append('profilePic', profilePic );
	        formdata.append('id', id);

	        $.ajax({
	            url: 'profile.do?action=editPic',
	            cache: false,
	            async: true,
	            type: 'POST',
	            data: formdata,
	            processData: false,
	            contentType: false,
	            beforeSend: function(){
				$(".user-profile-pic").html("<i class='fa fa-circle-o-notch fa-spin fa-3x fa-fw'></i><span class='sr-only'>Loading...</span>");
				},
	            success: function(r){
					/*console.log(r);*/
					setTimeout(function() {
			          delaySuccess(r);
			        }, 10000);  
				}
	        });
	    }
		
	});
	
	/*Relode the page on relode btn click*/
	$('.relode').click(function() {
		location.reload();
	});
	
	
	/*Start new item validation*/
	var newitemNameError = true;
	var newitemDescriptionError = true;
	var newitemPriceError = true;
	var newitemCountryError = true;
	var newItemCategoryValueError = true;
	var newItemStatusValueError = true;
	
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
	
	$("#newItemCategoryValue").blur(function(){
		var errorMsg = '';
		if( $(this).val() == '' || $(this).val() == '0' ){
			if( userLang == "en" ){
				errorMsg = "<p>Please choose a category for this item.</p>";
			}else{
				errorMsg = "<p>\u064a\u0631\u062c\u0649 \u0627\u0644\u0642\u064a\u0627\u0645 \u0628\u0625\u062e\u062a\u064a\u0627\u0631 \u0642\u0633\u0645 \u0644\u0647\u0627\u0630\u0627 \u0627\u0644\u0645\u0646\u062a\u062c</p>";
			}
		}
		if(errorMsg != ''){
			addError($(this), errorMsg);
			newItemCategoryValueError = true;
		}else{
			removeError($(this));
			newItemCategoryValueError = false;
		}
	});
	
	$("#newItemStatusValue").blur(function(){
		var errorMsg = '';
		if( $(this).val() == '' || $(this).val() == '0' ){
			if( userLang == "en" ){
				errorMsg = "<p>Please choose the status for the item.</p>";
			}else{
				errorMsg = "<p>\u064a\u0631\u062c\u0649 \u0627\u0644\u0642\u064a\u0627\u0645 \u0628\u0625\u062e\u062a\u064a\u0627\u0631 \u0627\u0644\u062d\u0627\u0644\u0629 \u0644\u0647\u0627\u0630\u0627 \u0627\u0644\u0645\u0646\u062a\u062c</p>";
			}
		}
		if(errorMsg != ''){
			addError($(this), errorMsg);
			newItemStatusValueError = true;
		}else{
			removeError($(this));
			newItemStatusValueError = false;
		}
	});
	
	
	$(document).on("change", ".new-item-category", function(){
		$("#newItemCategoryValue").val( $(".new-item-category").val() );
		var errorMsg = '';
		if( $("#newItemCategoryValue").val() == '' || $("#newItemCategoryValue").val() == '0' ){
			if( userLang == "en" ){
				errorMsg = "<p>Please choose a category for this item.</p>";
			}else{
				errorMsg = "<p>\u064a\u0631\u062c\u0649 \u0627\u0644\u0642\u064a\u0627\u0645 \u0628\u0625\u062e\u062a\u064a\u0627\u0631 \u0642\u0633\u0645 \u0644\u0647\u0627\u0630\u0627 \u0627\u0644\u0645\u0646\u062a\u062c</p>";
			}
		}
		if(errorMsg != ''){
			addError($(this), errorMsg);
			newItemCategoryValueError = true;
		}else{
			removeError($(this));
			newItemCategoryValueError = false;
		}
	});
	
	$(document).on("change", ".new-item-status", function(){
		$("#newItemStatusValue").val( $(".new-item-status").val() );
		var errorMsg = '';
		if( $("#newItemStatusValue").val() == '' || $("#newItemStatusValue").val() == '0' ){
			if( userLang == "en" ){
				errorMsg = "<p>Please choose the status for the item.</p>";
			}else{
				errorMsg = "<p>\u064a\u0631\u062c\u0649 \u0627\u0644\u0642\u064a\u0627\u0645 \u0628\u0625\u062e\u062a\u064a\u0627\u0631 \u0627\u0644\u062d\u0627\u0644\u0629 \u0644\u0647\u0627\u0630\u0627 \u0627\u0644\u0645\u0646\u062a\u062c</p>";
			}
		}
		if(errorMsg != ''){
			addError($(this), errorMsg);
			newItemStatusValueError = true;
		}else{
			removeError($(this));
			newItemStatusValueError = false;
		}
	});
	
	/*End new item validation*/
	
	/*Check the new item page fields on the item click*/
	$(".new-item .item-box .item-image img").on("click", function(){
		
		$(".new-item-name").blur();
		$(".new-item-description").blur();
		$(".new-item-price").blur();
		$(".new-item-country").blur();
		$("#newItemStatusValue").blur();
		$("#newItemCategoryValue").blur();
		if( newitemNameError === true || 
			newitemDescriptionError === true ||
			newitemPriceError === true ||
			newitemCountryError === true ||
			newItemStatusValueError === true ||
			newItemCategoryValueError === true ){
			
			$(this).find('input[type=text],textarea,select').filter(':visible:first').blur();
			return false;
		}
		
		$(".new-item .item-pic").click();
	});
	
	/*Send item new image to the server on image input file changing*/
	$(document).on("change", ".new-item .item-pic", function(){
		
		var itemPic = $('#item-pic')[0].files[0];
		var catId = $(".new-item-category").val();
		
		
		 if( window.FormData ) {
	        formdata = new FormData();
	        formdata.append('image', itemPic );
	        formdata.append('catId', catId );


	        $.ajax({
	            url: 'new_item.do?action=insertItemPic',
	            cache: false,
	            async: true,
	            type: 'POST',
	            data: formdata,
	            processData: false,
	            contentType: false,
	            beforeSend: function(){
	        	$(".new-item-category").prop("disabled", true);
	        	$(".new-item .item-price").fadeOut(0);
	        	$(".new-item .upload-icon").fadeOut(0);
				$(".new-item .item-box .item-image").html("<i class='fa fa-circle-o-notch fa-spin fa-3x fa-fw'></i><span class='sr-only'>Loading...</span>");
				},
	            success: function(r){
					/*console.log(r);*/
					setTimeout(function() {
			          itemDelaySuccess(r);
			        }, 10000);
				}
				
	        });
	    }
		
	});
	
});

/*delaySuccess function*/
function delaySuccess(r) {
	var profilePic = $(r).filter("img");
	console.log(profilePic);
	/*$(".user-profile-pic").load(profilePic);*/
	
	$(".user-profile-pic").html(profilePic);
	
	
	//another stubid workign way :)
	/*$(".user-profile-pic").html(profilePic).fadeOut(10000, function(){
		location.reload();
		$(".user-profile-pic").load(location.href + " #test", function(){
			$(".user-profile-pic").fadeIn();
		});
	});*/	
}

/*itemDelaySuccess function*/
function itemDelaySuccess(r) {
	var image = $(r).filter("img");
	
	$(".new-item .item-box .item-image").html(image);
	$("input[type='submit']").attr("disabled", false);
	$(".new-item .item-price").fadeIn(0);
	$(".new-item .upload-icon").fadeIn(0); 
}

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
		if(userLang == "en"){ //hoon
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