<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8" />
	<title><sitemesh:title /></title>

	<meta name="description" content="overview &amp; stats" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

	<!-- bootstrap & fontawesome -->
	<link href="//apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet" media="screen" />
	<link href="//apps.bdimg.com/libs/fontawesome/4.4.0/css/font-awesome.css" rel="stylesheet"  />

	<!-- ace styles -->
	<link rel="stylesheet" href="${ctx }/static/ace/1.3.3/css/ace.css" class="ace-main-stylesheet" id="main-ace-style" />
	<link rel="stylesheet" href="${ctx}/static/mimidai/css/main.css"/>
	<!--[if lte IE 9]>
		<link rel="stylesheet" href="${ctx }/static/ace/1.3.3/css/ace-part2.min.css" class="ace-main-stylesheet" />
	  	<link rel="stylesheet" href="${ctx }/static/ace/1.3.3/css/ace-ie.min.css" />
	<![endif]-->

	<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->
	<!--[if lte IE 8]>
	<script src="${ctx }/static/ace/1.3.3/js/html5shiv.min.js"></script>
	<script src="${ctx }/static/ace/1.3.3/js/respond.min.js"></script>
	<![endif]-->

	<!--[if !IE]> -->
	<script type="text/javascript" src="//apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<!-- <![endif]-->
	<!--[if IE]>
	<script type="text/javascript" src="//apps.bdimg.com/libs/jquery/1.11.3/jquery.min.js"></script>
	<![endif]-->
	<script src="//apps.bdimg.com/libs/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	
	<!-- ace scripts -->
	<script src="${ctx }/static/ace/1.3.3/js/ace-elements.min.js"></script>
	<script src="${ctx }/static/ace/1.3.3/js/ace.min.js"></script>
	<link rel="stylesheet" href="${ctx }/static/viewer/viewer.css" />
	<script type="text/javascript" src="${ctx }/static/viewer/viewer.js"></script>
	<script type="text/javascript" src="${ctx }/static/viewer/main.js"></script>
	<sitemesh:head />
</head>
<body class="no-skin">
	<div class="main-container" id="main-container">
		<div class="main-content">
			<div class="main-content-inner">
				<sitemesh:body />
			</div>
		</div>
	</div><!-- /.main-container -->
</body>
</html>


