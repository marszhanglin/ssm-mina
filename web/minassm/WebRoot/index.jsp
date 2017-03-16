<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script src="js/jquery-1.10.2.min.js" type="text/javascript"></script>


<script type = "text/javascript">
  $(function(){
       location.href = "<%=basePath%>index.do";
  });
</script>  