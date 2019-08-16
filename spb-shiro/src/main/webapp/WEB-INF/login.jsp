<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="description" content="User login page" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>${sn }-后台</title>

<meta name="description" content="overview &amp; stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

<!-- bootstrap & fontawesome -->
<link href="//apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet" media="screen" />
<link href="//apps.bdimg.com/libs/fontawesome/4.4.0/css/font-awesome.css" rel="stylesheet" />
<!--[if !IE]> -->
<script type="text/javascript" src="//apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<!-- <![endif]-->
<!--[if IE]>
		<script type="text/javascript" src="//apps.bdimg.com/libs/jquery/1.11.3/jquery.min.js"></script>
		<![endif]-->
<script src="//apps.bdimg.com/libs/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<script type="text/javascript">
	var ctx = '${ctx }';
</script>
</head>
<body>
	<div class="container">
		<br /> <br /> <br /> <br /> <br /> <br /> <br />
		<div class="page-header">
			<h1 style="margin-left: 320px;">${sn }</h1>
		</div>
		<div class="row">
			<div class="span16">
				<%@include file="/commons/message.jsp"%>
				<form action="${ctx}/login" method="post" accept-charset="utf-8">
					<fieldset id="login-form" style="margin-left: 350px; width: 540px;">
						<div class="clearfix">
							<label>用户名：</label>
							<div class="input">
								<input class="xlarge disabled" id="loginName" name="loginName" size="30" type="text" />
							</div>
						</div>
						<!-- /clearfix -->
						<div class="clearfix">
							<label>密码：</label>
							<div class="input">
								<input class="xlarge disabled" id="password" name="password" size="30" type="password" />
							</div>
						</div>
						<!-- /clearfix -->
						<div class="clearfix">
							<label>验证码：</label>
							<div class="input">
								<input type="text" id="codeInput" class="input-sm" placeholder=" 验证码" name="code" /> <img style="margin-left: 49px; margin-top: -4px;" title="点击刷新验证码"
									alt="点击刷新验证码" src="${ctx}/basic/code" id="codeImg">
							</div>
						</div>
						<!-- /clearfix -->
						<br />
						<div class="actions">
							<input type="submit" class="btn btn-primary btn-large" value="Login">
						</div>
					</fieldset>
				</form>
			</div>
		</div>
		<!-- /row -->
		<br />
	</div>
	<!-- /container -->

	<footer class="footer">
		<div class="container">
			<p class="pull-right">&copy; ${cn } 2016.</p>
		</div>
	</footer>

	<script type="text/javascript">
		jQuery(function($) {
			$('input').bind('keydown', function(event) {
				if (event.keyCode == '13') {
					checkCode();
				}
			});

			$('#codeImg').bind('click', function() {
				var src = $(this).attr("src");
				var randomnum = Math.random();
				$(this)[0].src = src + "?r=" + randomnum;
			});
		});

		function checkCode() {
			var code = $("#codeInput").val();
			if (code == '') {
				bootbox.alert({
					title : '提示',
					message : "请输入验证码",
					callback : function() {
						$("#codeInput").focus();
					}
				});
			} else {
				var url = ctx + "/checkCode?code=" + code;
				$.get(url, function(result) {
					if (result.code == '0') {
						bootbox.alert({
							title : '提示',
							message : result.msg,
							callback : function() {
								$('#codeImg').click();
								$("#codeInput").focus();
							}
						});
					} else {
						$("#loginForm").submit();
					}
				});
			}
		}
	</script>
</body>
</html>