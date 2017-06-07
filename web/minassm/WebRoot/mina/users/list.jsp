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

	<link href="<%=basePath%>media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>

	<link href="<%=basePath%>media/css/uniform.default.css" rel="stylesheet" type="text/css"/>

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
						
						

						<!-- END EXAMPLE TABLE PORTLET-->

					</div>

				</div>

	<!-- END FOOTER -->

	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

	<script>
		var basePath="<%=basePath%>";
	</script>
	<!-- BEGIN CORE PLUGINS -->

	<script src="<%=basePath%>media/js/jquery-1.10.1.min.js" type="text/javascript"></script>

	<script src="<%=basePath%>media/js/jquery.json-2.4.min.js" type="text/javascript"></script>

	<script src="<%=basePath%>media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>

	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

	<script src="<%=basePath%>media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      

	<script src="<%=basePath%>media/js/bootstrap.min.js" type="text/javascript"></script>

	<!--[if lt IE 9]>

	<script src="<%=basePath%>media/js/excanvas.min.js"></script>

	<script src="<%=basePath%>media/js/respond.min.js"></script>  

	<![endif]-->   

	<script src="<%=basePath%>media/js/jquery.slimscroll.min.js" type="text/javascript"></script>

	<script src="<%=basePath%>media/js/jquery.blockui.min.js" type="text/javascript"></script>  

	<script src="<%=basePath%>media/js/jquery.cookie.min.js" type="text/javascript"></script>

	<script src="<%=basePath%>media/js/jquery.uniform.min.js" type="text/javascript" ></script>

	<!-- END CORE PLUGINS -->

	<!-- BEGIN PAGE LEVEL PLUGINS bootstrapt分页插件依赖jquery1.8与bootstrap.css 参考：http://www.cnblogs.com/xmfdsh/p/4041187.html-->
	
	<script type="text/javascript" src="<%=basePath%>media/js/bootstrap-paginator.min.js"></script>

	<!-- END PAGE LEVEL PLUGINS -->

	<!-- BEGIN PAGE LEVEL SCRIPTS --> 

	<script src="<%=basePath%>media/js/table-managed_cus.js"></script>     

	<script>

		jQuery(document).ready(function() {       
			
		   TableManaged_cus.init();
			
		});

	</script>


<!-- END BODY -->

</html>