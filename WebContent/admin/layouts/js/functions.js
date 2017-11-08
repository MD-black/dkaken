/*global console, alert, $*/

/*
	File Name 		: functions.js
	Dev Name  		: Mohammed Darwish
	Creation Date 	: 11/7/2017
*/

$(document).ready(function(){
	
	/*Trigger the wow.js animations*/
	new WOW().init(); 
	
	/*Adjust the login page background photo height*/
	$(".login").css("height", $(window).height());
	
	/*Adjust the login form position*/
	$(".login-form").css("marginTop", ($(window).height() - $(".login-form").height()) / 2 );
	
	/*Adjust the copyright div position*/
	$(".copyright").css("marginTop", (($(window).height() - $(".login-form").height()) / 2) - 50 );
	
	
	/*On page resizing*/
	$(window).on("resize", function(){
		
		//adjust the login page background photo height
		$(".login").css("height", $(window).height());
		
		//adjust the login form position
		$(".login-form").css("marginTop", ($(window).height() - $(".login-form").height()) / 2 );
		
		//adjust the copyright div position
		$(".copyright").css("marginTop", (($(window).height() - $(".login-form").height()) / 2) - 50 );
		
	});
	
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
	
	/*
	** placeholder hide and show on focus and blur
	** this functions is used to show or hide placeholder on focus/blur
	*/
	/*$("[placeholder]").focus(function(){
		$(this).attr("data-text", $(this).attr("placeholder"));
		$(this).attr("placeholder", "");
	}).blur(function(){
		$(this).attr("placeholder", $(this).attr("data-text"));
	});*/
	
	/*Trigger the data table*/
	$('#table_id').DataTable({
        select: {
        	style: 'single'
    	},
    	"lengthMenu": [ 5, 10, 15, 20, 25 ]
	});
	
	/*Trigger the selectboxit*/
	$("select").selectBoxIt({
		autoWidth: false
	}); 
	
	/*Success and failer message delete in 5 seconds*/
	$(".success-msg").delay(3000).fadeOut(400);
	$(".fail-msg").delay(3000).fadeOut(400);  
	
	/*Show asterisks on required input fields.*/ 
	$("input").each(function(){
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
	
	
	/*Show and hide btn approve*/
	$(document).on("click", ".main-table tbody tr, .selected", function(){
		if( $(this).parents("table").hasClass("dashboard-table") ){
			if( $(this).children(".row-username").find("i").hasClass("not-approved") ){
				$(this).parents(".panel-body").siblings(".panel-heading").children(".main-table-actions").children(".others").find(".btn-approve").css("display","inline-block");
			}else{
				$(this).parents(".panel-body").siblings(".panel-heading").children(".main-table-actions").children(".others").find(".btn-approve").css("display","none");
			}
		}else{
			if( $(".selected").children(".row-username").find("i").hasClass("not-approved") ){
				$(".btn-approve").css("display", "inline-block");
			}else{
				$(".btn-approve").css("display", "none");
			}
		}
	});
		
		
	var rowCount = 0;
	var statusCount = 0;
	
	/*Change href url for the deleted row and prevent default if the row is not selected.*/
	$(".btn-delete, .btn-edit, .btn-approve").on("click", function(event){
		if( $("tr").hasClass("selected") ){
			/*var id = $(".selected").children(".row-id").text();*/
			var id = 0;
			if( $(this).parents(".panel").length ){
				var id = $(this).parents(".panel-heading").siblings(".panel-body").children(".table-responsive").children(".main-table").find(".selected").children(".row-id").text();
			}else{
				var id = $(".selected").children(".row-id").text();
				if( id.indexOf("#ID") >= 0 ){
					id = id.split("#ID")[1];
				}
			}
			
			
			var oldURL = $(this).attr("href");
			var newURL = oldURL + "&id=" + id;
			
			
			$("tbody tr").each(function(){
				rowCount = ++rowCount;
				if( $(this).children(".row-username").find("i").hasClass("not-approved") ){
					statusCount = ++statusCount;
				}
			});
			if($(this).is(".btn-approve")){
				if( rowCount == statusCount ){
					newURL = newURL + "&status=pending";
				}else if(rowCount > statusCount){
					newURL = newURL + "&status=all";
				}
			}
			if($(this).is(".btn-delete")){
				if( rowCount == statusCount ){
					newURL = newURL + "&status=pending";
				}else if(rowCount > statusCount){
					newURL = newURL + "&status=all";
				}
			}
			
			
			$(this).attr("href", newURL);
		}else{
			event.preventDefault();
			$(".others-msg").fadeIn(500).delay(1000).fadeOut(500);
		}
	});
	
	/*Show hover message on categories options*/
	$(".is-visible-false").hover(function(){
		$(this).next(".vis-false").fadeIn();
	}, function(){
		$(this).next(".vis-false").fadeOut();
	});
	$(".is-comment-allowed-false").hover(function(){
		$(this).next(".comm-false").fadeIn();
	}, function(){
		$(this).next(".comm-false").fadeOut();
	});
	$(".is-ads-allowed-false").hover(function(){
		$(this).next(".ads-false").fadeIn();
	}, function(){
		$(this).next(".ads-false").fadeOut();
	});
	
	/*Show the confirm message on delete*/ 
	$(".confirm").on("click", function(){
		return confirm("Are you sure you want to delete ?");
	});
	
	/*Manage the categories full and classic views*/
	$(".cats-view span").on("click", function(){
		$(this).addClass("active").siblings("span").removeClass("active");
		if( $(this).data("value") === "classic" ){
			$(".category .cat-options").fadeOut(100);
			$(".category .child-categories").fadeOut(100);
		}else{
			$(".category .cat-options").fadeIn(100);
			$(".category .child-categories").fadeIn(100);
		}
	});
	
	/*Show and hide the category options on click*/
	$(".category h3").on("click", function(){
		$(this).next(".cat-options").fadeToggle(100);
		$(this).next(".cat-options").next(".child-categories").fadeToggle(100);
	});
	
	/*Show and hide latests on panel-plus click*/
	$(".panel-plus").on("click", function(){
		$(this).parent(".panel-heading").siblings(".panel-body").fadeToggle(0);
		$(this).siblings(".main-table-actions").css("display", "inline");
		$(this).siblings(".main-table-actions").toggleClass("dps");
		$(this).toggleClass("choosed");
		if( $(this).hasClass("choosed") ){
			$(this).html("<i class='fa fa-minus'></i>");
		}else{
			$(this).html("<i class='fa fa-plus'></i>");
		}
		
		if( $(this).siblings(".main-table-actions").hasClass("dps") ){
			$(this).siblings(".main-table-actions").css("display", "inline");
		}else{
			$(this).siblings(".main-table-actions").css("display", "none");
		}
		
	});
	
	/*Show and hide child categories anchors on hover*/
	$(".child-categories .child").hover(function(){
		$(this).find(".anchor-action").fadeIn(0);
	}, function(){
		$(this).find(".anchor-action").fadeOut(0);
	});

});