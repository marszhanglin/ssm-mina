var TableManaged_cus = function () {
    
    return {
    	currentpage:1,
    	rows:1,
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
								     + '     <td>'+new Date(item.createTime)+'</td>'
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
                        alignment: "right",
                        itemContainerClass: function (type, page, current) {
			                return (page === current) ? "active" : "pointer-cursor";
			            },// 给li标签添加样式类
                        useBootstrapTooltip:true,//提示
                        tooltipTitles: function (type, page, current) {//自定义提示
					        switch (type) {
					        case "first":
					            return "Go To First Page <i class='icon-fast-backward icon-white'></i>";
					        case "prev":
					            return "Go To Previous Page <i class='icon-backward icon-white'></i>";
					        case "next":
					            return "Go To Next Page <i class='icon-forward icon-white'></i>";
					        case "last":
					            return "Go To Last Page <i class='icon-fast-forward icon-white'></i>";
					        case "page":
					            return "Go to page " + page + " <i class='icon-file icon-white'></i>";
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