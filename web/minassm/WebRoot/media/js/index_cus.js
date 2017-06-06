var Index_cus = function(){
	
	var baseIframe =$("#base_iframe");
	
	function initIframe(){ 
		var lis=$("ul > .change_iframe"); 
		$.each(lis, function() {
			var item = this;
			$(this).on("click",function(){
				var str_json=$(item).attr("data");
				var data_json=eval('(' + str_json + ')'); 
				baseIframe.attr("src",basePath+data_json.url);
				$("#title").html(data_json.title+" <small>"+data_json.discription+"</small>");
				$("#breadcrumb_2").html(data_json.title);
				
			}); 
		});
	}
	
	return {
	
		init : function(){
			
			initIframe(); 
		
		}
		
	
	};
	
}();