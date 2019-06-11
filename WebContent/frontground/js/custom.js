
jQuery(document).ready(function($) {	
    
	function lightbox()
    {
        jQuery("a[data-rel^='prettyPhoto']").prettyPhoto({
		theme: 'pp_default',
		overlay_gallery: false,
		show_title: false,
        social_tools: false,
		hideflash: true
	});
    }
    if (jQuery().prettyPhoto)
    {
        lightbox();
    }
    if (jQuery().quicksand)
    {
    
        var $holder = $(".portfolio");
        var $data = $holder.clone();
        var $preferences = {
            duration: 500,
            easing: "easeInQuad"
        };
        jQuery(".filter a").click(function (e)
        {
            jQuery(".filter li").removeClass("current");
            // Get the class attribute value of the clicked link
		     var $filterClass = jQuery(this).parent().attr("class");
             jQuery(this).parent().addClass("current");
            if ($filterClass == "all")
            {
                var $filteredData = $data.find("li");
            }
            else
            {
                var $filteredData = $data.find("li[data-type~=" + $filterClass + "]");
            }
            $holder.quicksand($filteredData, $preferences, function ()
            {
                lightbox();
            });
        });
    }
	

});

/* Menu */

jQuery(document).ready(function() {		 
      // Create the dropdown base
       jQuery("<select />").appendTo("nav");

      // Create default option "Go to..."
      jQuery("<option />", {
        "selected": "selected",
        "value"   : "",
        "text"    : "Go to..."
      }).appendTo("nav select");

      // Populate dropdown with menu items
       jQuery("nav a").each(function() {
        var el = jQuery(this);
       jQuery("<option />", {
          "value"   : el.attr("href"),
          "text"    : el.text()
        }).appendTo("nav select");
        });
		
		// To make dropdown actually work
	   // To make more unobtrusive: http://css-tricks.com/4064-unobtrusive-page-changer/
      jQuery("nav select").change(function() {
        window.location = jQuery(this).find("option:selected").val();
      });
	  
	  
    }); 
	
/* Twiiter */


/* Flexslider */
 jQuery(window).load(function() {
    jQuery('.flexslider').flexslider({
		touchSwipe: true,     
		controlNav: true,
		slideshow: true,                
		slideshowSpeed: 7000,
		animationDuration: 600, 
		randomize: false, 
		pauseOnAction: true,    
		pauseOnHover: false, 
	});
  });
			

	
jQuery(document).ready(function() {	

jQuery(function() {
			jQuery(".meter > span").each(function() {
				jQuery(this)
					.data("origWidth", jQuery(this).width())
					.width(0)
					.animate({
						width: jQuery(this).data("origWidth")
					}, 1200);
			});
		});
  
 
/* Testminal */
  
   jQuery(function(){ 
			// Testimonials Setting    
        	jQuery('.testimonials ul').cycle({
				timeout: 4000, 
				fx:      'fade', 
				pause:   true,	
				cleartypeNoBg: true, 
				pauseOnPagerHover: 0
        });
		});


/* Scale vid */
	
	 jQuery(".scale_vid").fitVids();		
  
  
  
  
  });
  
  
jQuery(document).ready(function() {	

/* Supperfish */

jQuery('ul.sf-menu').superfish(
		 {
            delay: 200,
            animation: {
                opacity: "show",
                height: "show"
            },
            speed: "fast",
            autoArrows: false,
            dropShadows: false
        });  



});

		
/* Do it function  */

jQuery(document).ready(function() {	
	
	
	/* Tabs */
	tabsInit();
	
	/* accordion */
	accordion('.accordion-items','.accordion-item','.accordion-item-body','.accordion-item-header a');
	
});

/* accordion */
function accordion(b,bb,bbb,a){
	jQuery(a).click(function(e){
		if(jQuery(this).hasClass('active')){
			jQuery(this).removeClass('active').parents(bb).find(bbb).stop(true,true).slideUp(500,'easeOutQuad');
		}
		else{
			jQuery(this).parents(b).find(a+'.active').removeClass('active').parents(bb).find(bbb).stop(true,true).slideUp(500,'easeOutQuad');			
			jQuery(this).addClass('active').parents(bb).find(bbb).stop(true,true).slideDown(500,'easeOutQuad');
		}
		return false;
	});
	jQuery(b).each(function(index, element) {
		ax=jQuery(element).find(a+'.active');
		//if(!ax.size()){ax=jQuery(element).find(a).eq(0).addClass('active');}
		ax.parents(bb).find(bbb).slideDown(0);
	});
}


/* Carousel  */

jQuery(window).load(function(){
			jQuery('#carousel-works').carouFredSel({
				responsive: true,
				width: '100%',
				auto: false,
				circular	: false,
				infinite	: false,
				prev : {
					button		: "#prev_port",
					key			: "left",
						},
				next : {
					button		: "#next_port",
					key			: "right",
							},
				swipe: {
					onMouse: true,
					onTouch: true
					},
				items: {
					visible: {
						min: 1,
						max: 4
					}
				}
			});

		});

/* Tooltips Tipsy */

jQuery(window).load(function() {
			jQuery('.tool_tipsy').tipsy({gravity: $.fn.tipsy.autoNS, fade:true});
});
	

/* #Site Tabs */

function tabsInit() {
	
	/*
	* Skeleton V1.1
	* Copyright 2011, Dave Gamache
	* www.getskeleton.com
	* Free to use under the MIT license.
	* http://www.opensource.org/licenses/mit-license.php
	* 8/17/2011
	*/
	
	/* Tabs Activiation
	================================================== */

	var tabs = jQuery('ul.tabs');

	tabs.each(function(i) {

		//Get all tabs
		var tab = jQuery(this).find('> li > a');
		tab.click(function(e) {

			//Get Location of tab's content
			var contentLocation = jQuery(this).attr('href');

			//Let go if not a hashed one
			if(contentLocation.charAt(0)=="#") {

				e.preventDefault();

				//Make Tab Active
				tab.removeClass('active');
				jQuery(this).addClass('active');

				//Show Tab Content & add active class
				jQuery(contentLocation).show().addClass('active').siblings().hide().removeClass('active');

			}
		});
	});	
}

/*  Totop plugin   */

		jQuery(document).ready(function() {
			
			jQuery().UItoTop({
				scrollSpeed: 500,
				easingType: 'easeOutQuart' 
	 		});
		});
		
/* Color effect */
	
// Background color animation 
        jQuery(document).ready(function(){
                jQuery("a.button,button").hover(function() {
                jQuery(this).stop().animate({ backgroundColor: "#639898" }, 600);
                },function() {
                 jQuery(this).stop().animate({ backgroundColor: "#ff9600" }, 400);
                });
		
		        jQuery("ul.sf-menu ul li").hover(function() {
                jQuery(this).stop().animate({ backgroundColor: "#333" }, 300);
                },function() {
                 jQuery(this).stop().animate({ backgroundColor: "#444" }, 300);
                });
				
				jQuery(".filter_wrapper ul li, .current").hover(function() {
                jQuery(this).stop().animate({ backgroundColor: "#ff9600" }, 300);
                },function() {
                 jQuery(this).stop().animate({ backgroundColor: "#b9b9b9" }, 300);
                });
				
				jQuery("ul.sf-menu > li").hover(function() {
                jQuery(this).stop().animate({ backgroundColor: "#444" }, 300);
                },function() {
                 jQuery(this).stop().animate({ backgroundColor: "#333" }, 300);
                });
				
 // font color animation 
                jQuery(".service_wrapper_inner h5 a,.service_dis2 h5 a,.view_title h6,.view_title_b h6,.title_b a h5").stop().hover(function() {
                jQuery(this).stop().animate({ color: "#ff9600" }, 400);
                },function() {
                jQuery(this).animate({ color: "#333" }, 500);
                });
				
				jQuery(".widget .categories li a").stop().hover(function() {
                jQuery(this).stop().animate({ color: "#555" }, 400);
                },function() {
                jQuery(this).animate({ color: "#ff9600" }, 500);
                });
				
				jQuery(".sub_pb_title a").stop().hover(function() {
                jQuery(this).stop().animate({ color: "#555" }, 400);
                },function() {
                jQuery(this).animate({ color: "#ff9600" }, 500);
                });
				
				jQuery(".top_info p a").stop().hover(function() {
                jQuery(this).stop().animate({ color: "#ff9600" }, 300);
                },function() {
                jQuery(this).animate({ color: "#f7f7f7" }, 300);
                });
				
				jQuery(".port_inner .text_soft").stop().hover(function() {
                jQuery(this).stop().animate({ color: "#ff9600" }, 400);
                },function() {
                jQuery(this).animate({ color: "#c6c6c6" }, 500);
                });
				
				jQuery(".footer_support_inner a").stop().hover(function() {
                jQuery(this).stop().animate({ color: "#333" }, 400);
                },function() {
                jQuery(this).animate({ color: "#eee" }, 500);
                });
				
				jQuery(".port_sing_ti a").stop().hover(function() {
                jQuery(this).stop().animate({ color: "#ff9600" }, 400);
                },function() {
                jQuery(this).animate({ color: "#333" }, 500);
                });
				
				jQuery(".blog_title a").stop().hover(function() {
                jQuery(this).stop().animate({ color: "#ff9600" }, 400);
                },function() {
                jQuery(this).animate({ color: "#333" }, 500);
                });
				
				jQuery(".blog_post a.readmore_b h6").stop().hover(function() {
                jQuery(this).stop().animate({ color: "#ff9600" }, 400);
                },function() {
                jQuery(this).animate({ color: "#333" }, 500);
                });
	
// border color animate	
				jQuery('.service_dis2_inner,.blog_3_content').hover(function() {
	            jQuery(this).animate({ borderBottomColor: "#ff9600" }, '400');
                },function() {
	            jQuery(this).animate({ borderBottomColor: "#f8f8f8" }, '500');
                });
				
				jQuery('.flickr_badge_image img').hover(function() {
	            jQuery(this).animate({ borderColor: "#ff9600" }, '400');
                },function() {
	            jQuery(this).animate({ borderColor: "#e3e3e3" }, '500');
                });

  });