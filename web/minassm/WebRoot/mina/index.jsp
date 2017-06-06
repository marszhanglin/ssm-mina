<%@page pageEncoding="UTF-8"%>
<%@include file="/baseinclude/common.jsp"%>
<!DOCTYPE html>

<!-- 

Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 2.3.1

Version: 1.3

Author: KeenThemes

Website: http://www.keenthemes.com/preview/?theme=metronic

Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469

-->

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->

<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>

	<meta charset="utf-8" />

	<title>模板 | Admin Dashboard Template</title>

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
	
	<!-- id 是用来切换样式的 详细：app.js的setColor-->
	<link href="<%=basePath%>media/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>

	<link href="<%=basePath%>media/css/uniform.default.css" rel="stylesheet" type="text/css"/>

	<!-- END GLOBAL MANDATORY STYLES -->

	<!-- BEGIN PAGE LEVEL STYLES --> 

	<link href="<%=basePath%>media/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>

	<link href="<%=basePath%>media/css/daterangepicker.css" rel="stylesheet" type="text/css" />

	<link href="<%=basePath%>media/css/fullcalendar.css" rel="stylesheet" type="text/css"/>

	<link href="<%=basePath%>media/css/jqvmap.css" rel="stylesheet" type="text/css" media="screen"/>

	<link href="<%=basePath%>media/css/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen"/>

	<!-- END PAGE LEVEL STYLES -->

	<link rel="shortcut icon" href="<%=basePath%>media/image/favicon.ico" />

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="page-header-fixed">

	<!-- BEGIN HEADER -->

	<div class="header navbar navbar-inverse navbar-fixed-top">

		<!-- BEGIN TOP NAVIGATION BAR -->

		<div class="navbar-inner">

			<div class="container-fluid">

				<!-- BEGIN LOGO -->

				<a class="brand" href="index.html">

				<img src="<%=basePath%>media/image/logo.png" alt="logo"/>

				</a>

				<!-- END LOGO -->

				<!-- BEGIN RESPONSIVE MENU TOGGLER -->

				<a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">

				<img src="<%=basePath%>media/image/menu-toggler.png" alt="" />

				</a>          

				<!-- END RESPONSIVE MENU TOGGLER -->            

				<!-- BEGIN TOP NAVIGATION MENU 顶部右侧菜单栏-->              

				<ul class="nav pull-right">
					<li>
					<!-- BEGIN STYLE CUSTOMIZER 自定义样式--> 
						<div class="color-panel hidden-phone">

							<div class="color-mode-icons icon-color" style="padding: 15px 10px 15px 10px;"></div>

							<div class="color-mode-icons icon-color-close" style="padding: 15px 10px 15px 10px;" ></div>

							<div class="color-mode">

								<p>THEME COLOR</p>

								<ul class="inline">

									<li class="color-black current color-default" data-style="default"></li>

									<li class="color-blue" data-style="blue"></li>

									<li class="color-brown" data-style="brown"></li>

									<li class="color-purple" data-style="purple"></li>

									<li class="color-grey" data-style="grey"></li>

									<li class="color-white color-light" data-style="light"></li>

								</ul>

								<label>

									<span>Layout</span>

									<select class="layout-option m-wrap small">

										<option value="fluid" selected>Fluid</option>

										<option value="boxed">Boxed</option>

									</select>

								</label>

								<label>

									<span>Header</span>

									<select class="header-option m-wrap small">

										<option value="fixed" selected>Fixed</option>

										<option value="default">Default</option>

									</select>

								</label>

								<label>

									<span>Sidebar</span>

									<select class="sidebar-option m-wrap small">

										<option value="fixed">Fixed</option>

										<option value="default" selected>Default</option>

									</select>

								</label>

								<label>

									<span>Footer</span>

									<select class="footer-option m-wrap small">

										<option value="fixed">Fixed</option>

										<option value="default" selected>Default</option>

									</select>

								</label>

							</div>

						</div>

						<!-- END BEGIN STYLE CUSTOMIZER -->  
					</li>
				</ul>

				<!-- END TOP NAVIGATION MENU --> 

			</div>

		</div>

		<!-- END TOP NAVIGATION BAR -->

	</div>

	<!-- END HEADER -->

	<!-- BEGIN CONTAINER -->

	<div class="page-container">

		<!-- BEGIN SIDEBAR -->

		<div class="page-sidebar nav-collapse collapse">

			<!-- BEGIN SIDEBAR MENU 菜单栏 -->        

			<ul class="page-sidebar-menu">

				<li>

					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->

					<div class="sidebar-toggler hidden-phone"></div>

					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->

				</li>

				<li>

					<!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->

					<form class="sidebar-search">

						<div class="input-box">

							<a href="javascript:;" class="remove"></a>

							<input type="text" placeholder="Search..." />

							<input type="button" class="submit" value=" " />

						</div>

					</form>

					<!-- END RESPONSIVE QUICK SEARCH FORM -->

				</li> 
				
				<li class="last change_iframe " data="{url:'users/list',title:'用户管理',discription:'用户信息管理'}">

					<a href="#">

					<i class="icon-bar-chart"></i> 

					<span class="title">用户管理</span>

					</a>

				</li>

			</ul>

			<!-- END SIDEBAR MENU -->

		</div>

		<!-- END SIDEBAR -->

		<!-- BEGIN PAGE -->

		<div class="page-content">

			<!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->

			<div id="portlet-config" class="modal hide">

				<div class="modal-header">

					<button data-dismiss="modal" class="close" type="button"></button>

					<h3>Widget Settings</h3>

				</div>

				<div class="modal-body">

					Widget settings form goes here

				</div>

			</div>

			<!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->

			<!-- BEGIN PAGE CONTAINER-->

			<div class="container-fluid">

				<!-- BEGIN PAGE HEADER-->

				<div class="row-fluid">

					<div class="span12"> 
					
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->

						<h3 id="title" class="page-title">

							用户管理 <small>用户信息管理</small>

						</h3>

						<ul class="breadcrumb" style="margin-bottom: 5px;">

							<li>

								<i class="icon-home"></i>

								<a href="#">Home</a> 

								<i class="icon-angle-right"></i>

							</li>

							<li><a id="breadcrumb_2" href="#">用户管理</a></li>

							<li class="pull-right no-text-shadow">

								<div id="dashboard-report-range" class="dashboard-date-range tooltips no-tooltip-on-touch-device responsive" data-tablet="" data-desktop="tooltips" data-placement="top" data-original-title="Change dashboard date range">

									<i class="icon-calendar"></i>

									<span></span>

									<i class="icon-angle-down"></i>

								</div>

							</li>

						</ul>

						<!-- END PAGE TITLE & BREADCRUMB-->

					</div>

				</div>

				<!-- END PAGE HEADER-->


			</div>

			<div id="dashboard" class="row-fluid"> 
			<!-- 无边框：frameborder="0" 参考：http://www.cnblogs.com/kingboy2008/archive/2011/05/10/2055545.html -->
			<!--  -->
			<iframe id="base_iframe" src="<%=basePath %>users/list"  style="margin-left: 19px;width: 1080px;height: 640px;" frameborder="0"  ></iframe>
			</div>
			
			

			<!-- END PAGE CONTAINER-->    

		</div>

		<!-- END PAGE -->

	</div>

	<!-- END CONTAINER -->

	<!-- BEGIN FOOTER  底部 -->

	<div class="footer">

		<div class="footer-inner">

			2013 &copy; Metronic by keenthemes.

		</div>

		<div class="footer-tools">

			<span class="go-top">

			<i class="icon-angle-up"></i>

			</span>

		</div>

	</div>

	<!-- END FOOTER -->
	
	<script>
		var basePath="<%=basePath%>";
	</script>

	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

	<!-- BEGIN CORE PLUGINS -->

	<script src="<%=basePath%>media/js/jquery-1.10.1.min.js" type="text/javascript"></script>

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


	<!-- BEGIN PAGE LEVEL SCRIPTS -->

	<script src="<%=basePath%>media/js/app.js" type="text/javascript"></script>     

	<script src="<%=basePath%>media/js/index_cus.js" type="text/javascript"></script> 
	<!-- END PAGE LEVEL SCRIPTS -->  

	<script>

		jQuery(document).ready(function() {     
		
		   App.init(); // initlayout and core plugins
		  
		   Index_cus.init();
		   
		});

	</script>

	<!-- END JAVASCRIPTS -->

<%--<script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script></body>

--%><!-- END BODY -->

</html>