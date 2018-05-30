var TableManaged_cus = function () {
    
	/** 对Date的原型链进行修改*/
	Date.prototype.format = function(format)
		{
		 var o = {
		 "M+" : this.getMonth()+1, //month
		 "d+" : this.getDate(),    //day
		 "h+" : this.getHours(),   //hour
		 "m+" : this.getMinutes(), //minute
		 "s+" : this.getSeconds(), //second
		 "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
		 "S" : this.getMilliseconds() //millisecond
		 }
		 if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
		 (this.getFullYear()+"").substr(4 - RegExp.$1.length));
		 for(var k in o)if(new RegExp("("+ k +")").test(format))
		 format = format.replace(RegExp.$1,
		 RegExp.$1.length==1 ? o[k] :
		 ("00"+ o[k]).substr((""+ o[k]).length));
		 return format;
		}
	
	
	
    return {
    	currentpage:1,
    	rows:2,
    	/**
    	 * 初始化
    	 */
        init: function () { 
        	this._refreshPage();
        },
        /**
         * 刷新页面
         * 当前页
         * 长度
         */
        _refreshPage:function(){
        	var Tmcus = this;
        	$.post(basePath+"users/grid",{currentpage:Tmcus.currentpage,rows:Tmcus.rows},function(result){
        		console.log(result);
        		if(result){ 
        			Tmcus._refreshTableList(result); 
        			Tmcus._refreshPaginator(result); 
        		}
        	},"json"); 
        },
        /**
		 * 收到响应后刷新列表
		 */
		_refreshTableList : function(result){
				var tr_html_arr=[];   
				$.each(result.list, function (index, item) { //遍历返回的json
		             tr_html_arr.push( ' <tr  data="'+$.toJSON(item).split('"').join('\'')+'">'
								     + '     <td>'+item.name+'</td>'
								     + '     <td>'+item.email+'</td>'
								     + '     <td>'+new Date(item.createTime).format("yyyy-MM-dd hh:mm:ss")+'</td>'
								     + ' </tr>');
		        });
		        $("#table_content").html(tr_html_arr.join(''));
		},
		/**
		 * 收到响应后刷新分页
		 */
        _refreshPaginator : function(result){
                    var Tmcus = this;
                    var options = {
                        bootstrapMajorVersion: 2, //版本
                        currentpage: Tmcus.currentpage, //当前页数
                        totalPages: result.pagecount, //总页数
                        alignment: "left",  // 靠左  适配小屏幕
                        itemContainerClass: function (type, page, current) {
			                return (page === current) ? "active" : "pointer-cursor";
			            },// 给li标签添加样式类
                        useBootstrapTooltip:true,//提示
                        tooltipTitles: function (type, page, current) {//自定义提示
					        switch (type) {
					        case "first":
					            return "跳转首页 <i class='icon-fast-backward icon-white'></i>";
					        case "prev":
					            return "跳转上一页  <i class='icon-backward icon-white'></i>";
					        case "next":
					            return "跳转下一页 <i class='icon-forward icon-white'></i>";
					        case "last":
					            return "跳转末页 <i class='icon-fast-forward icon-white'></i>";
					        case "page":
					            return "跳转至 " + page + " <i class='icon-file icon-white'></i>";
					        }
					    },
                        itemTexts: function (type, page, current) {
                            switch (type) {
                                case "first":
                                    return "首页";
                                case "prev":
                                    return "上一页";
                                case "next":
                                    return "下一页";
                                case "last":
                                    return "末页";
                                case "page":
                                    return page;
                            }
                        },//点击事件，用于通过Ajax来刷新整个list列表
                        onPageClicked: function (event, originalEvent, type, page) {
                        	Tmcus.currentpage = page;
                        	Tmcus._refreshPage();
                             //console.log(event);
                             //console.log(originalEvent);
                             //console.log(type);
                             //console.log(page);
                        }
                    };
                    $('#page').bootstrapPaginator(options);
        
        }

    };

}();