/*  Animated shadow */

	// Begin jQuery
	
	$(document).ready(function() {				
	
		// Append shadow image to each div
		
		$(".service_wrapper_holder .service_wrapper .image_shadow").append('<div class="shadow_wrapper"><img class="shadow" src="images/stuff/icons-shadow.png" width="81" height="27" alt="" /></div>');
	
		// Animate buttons, shrink and fade shadow
		
		$(".service_wrapper_holder .service_wrapper .image_shadow").hover(function() {
			var e = this;
		    $(e).find("").stop().animate({ marginTop: "-14px" }, 800, function() {
		    	$(e).find("").animate({ marginTop: "-10px" }, 800);
		    });
		    $(e).find("img.shadow").stop().animate({ width: "8%", height: "20px", marginLeft: "70px", opacity: 0.25 }, 800);
		},function(){
			var e = this;
		    $(e).find("").stop().animate({ marginTop: "0px" }, 800, function() {
		    	$(e).find("").animate({ marginTop: "4px" }, 800);
		    });
		    $(e).find("img.shadow").stop().animate({ width: "10%", height: "27px", marginLeft: "62px", opacity: 1 }, 800);
		});
	});