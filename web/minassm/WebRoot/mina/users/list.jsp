<%@page pageEncoding="UTF-8"%>
<%@include file="/baseinclude/common.jsp"%>

<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

	<meta charset="utf-8" />

	<title>Metronic | Data Tables - Managed Tables</title>

	<meta content="width=device-width, initial-scale=1.0" name="viewport" />

	<meta content="" name="description" />

	<meta content="" name="author" />

	<!-- BEGIN GLOBAL MANDATORY STYLES -->

	<link href="<%=basePath%>media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

	<link href="<%=basePath%>media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>

	<link href="<%=basePath%>media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

	<link href="<%=basePath%>media/css/style-metro.css" rel="stylesheet" type="text/css"/>

	<link href="<%=basePath%>media/css/style.css" rel="stylesheet" type="text/css"/>

	<link href="<%=basePath%>media/css/style-responsive.css" rel="stylesheet" type="text/css"/>

	<%--<link href="<%=basePath%>media/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>

	--%><link href="<%=basePath%>media/css/uniform.default.css" rel="stylesheet" type="text/css"/>

	<!-- END GLOBAL MANDATORY STYLES -->

	<!-- BEGIN PAGE LEVEL STYLES -->

	<!--<link rel="stylesheet" type="text/css" href="<%=basePath%>media/css/select2_metro.css" />-->

	<link rel="stylesheet" href="<%=basePath%>media/css/DT_bootstrap.css" />

	<!-- END PAGE LEVEL STYLES -->

	<link rel="shortcut icon" href="<%=basePath%>media/image/favicon.ico" />

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="page-header-fixed">

	<div class="row-fluid">

					<div class="span12">

						<!-- BEGIN EXAMPLE TABLE PORTLET-->

						<div class="portlet box light-grey">   
						 	
						 	<!-- 参考：http://www.runoob.com/bootstrap/bootstrap-tables.html -->
						 	<table class="table">
								  <%--<caption>上下文表格布局</caption>
								  --%><thead>
								    <tr>
								      <th>账号</th>
								      <th>邮箱</th>
								      <th>创建日期</th></tr>
								  </thead>
								  <tbody id="table_content">
								  
								  
								    <!-- <tr data="[object" object]="">
								    <td>name_no_001</td>
								    <td>name_no_001@qq.com</td>
								    <td>Sat May 27 2017 23:24:11 GMT+0800 (中国标准时间)</td>
								    </tr>
								      -->
								  </tbody>
								</table>
						 	
						</div>
						<div id="page"></div>
						

						<!-- END EXAMPLE TABLE PORTLET-->

					</div>

				</div>

	<!-- END FOOTER -->

	<%@include file="/baseinclude/base_js.jsp"%>

	<!--[if lt IE 9]>

	<script src="<%=basePath%>media/js/excanvas.min.js"></script>

	<script src="<%=basePath%>media/js/respond.min.js"></script>  

	<![endif]-->   

	<script src="<%=basePath%>media/js/jquery.slimscroll.min.js" type="text/javascript"></script>

	<script src="<%=basePath%>media/js/jquery.blockui.min.js" type="text/javascript"></script>  

	<script src="<%=basePath%>media/js/jquery.cookie.min.js" type="text/javascript"></script>

	<script src="<%=basePath%>media/js/jquery.uniform.min.js" type="text/javascript" ></script>

	<!-- END CORE PLUGINS -->

	<!-- BEGIN PAGE LEVEL PLUGINS bootstrapt分页插件依赖jquery1.8与bootstrap.css 参考：bootstrap-paginator-->
	
	<script type="text/javascript" src="<%=basePath%>media/js/bootstrap-paginator.min.js"></script>

	<!-- END PAGE LEVEL PLUGINS -->

	<!-- BEGIN PAGE LEVEL SCRIPTS --> 

	<script src="<%=basePath%>mina/users/table-managed_cus.js"></script>     

	<script>

		jQuery(document).ready(function() {       
			
		   	TableManaged_cus.init();
			
		}); 
	 
	    	
	    	$(document).ajaxError(function(event,request,ajaxOptions,thrownError){ 
			   		if(request.responseText.indexOf('您已经太长时间没有操作,请刷新页面') > 0){   
				   //if(request.getResponseHeader("sessionstatus")=="sessionOut" ){
			    	var top = getTopWinow();
		            var yes = confirm('由于您长时间没有操作, session已过期, 请重新登录.');
		            if (yes) {
		                top.location.href = basePath+'/login';            
		            }
			    } 
			}); 
		
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
		
		
	</script>


<!-- END BODY -->

</html>