var Index_cus = function(){
	
	var baseIframe =$("#base_iframe");
	
	
	/**
	 * 在页面中任何嵌套层次的窗口中获取顶层窗口
	 * @return 当前页面的顶层窗口对象
	 */
	function getTopWinow(){
		var p = window;
		while(p != p.parent){
			p = p.parent;
		}
		return p;
	}
	
	function initIframe(){ 
		var lis=$("ul > .change_iframe"); 
		$.each(lis, function() {
			var item = this;
			$(this).on("click",function(){
				var str_json=$(item).attr("data");
				var data_json=eval('(' + str_json + ')'); 
				if(data_json.url=="exit_system"){
					var top = getTopWinow();
					top.location.href = basePath+'/login';
				}else{
					baseIframe.attr("src",basePath+data_json.url);
					$("#title").html(data_json.title+" <small>"+data_json.discription+"</small>");
					$("#breadcrumb_2").html(data_json.title);
				}
				
				
			}); 
		});
	}
	
	return {
	
		init : function(){
			
			initIframe(); 
		
		}
		
	
	};
	
}();