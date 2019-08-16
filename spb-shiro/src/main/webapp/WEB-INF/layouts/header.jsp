<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<script type="text/javascript">
	$(function() {
		// ajax 获取 未读消息数量
		$.ajax({
			url : '${ctx }/sys/header/alert',
			/* async : false, */
			success : function(result) {
				//alert("success");
				$('#manager_alert_span').text(result.data);

			}
		});
	});
	function queryMangerAlertList(){
		var num =$('#manager_alert_span').text();
		//if(num>0){
			 window.open("${ctx }/sys/header/list");
		//}
	}
</script>
<div id="navbar" class="navbar navbar-default">
	<div class="navbar-container" id="navbar-container">
		<div class="navbar-header pull-left">
			<a href="#" class="navbar-brand"> <small> <img style="vertical-align: sub; width: 25px"
					src="${ctx }${lu }" /> ${sn }
			</small>
			</a>
		</div>
		<div class="navbar-buttons navbar-header pull-right" role="navigation">
			<ul class="nav ace-nav">
				<!-- 提示信息 -->
				<li class="purple dropdown-modal">
					<a href="#" onclick="queryMangerAlertList()" aria-expanded="false"> <!-- data-toggle="dropdown" class="dropdown-toggle" -->
						<i class="ace-icon fa fa-bell"></i>
						<span id="manager_alert_span" class="badge badge-important"></span>
					</a>

					<!-- <ul class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
						<li class="dropdown-header">
							<i class="ace-icon fa fa-exclamation-triangle"></i>
							8 Notifications
						</li>

						<li class="dropdown-content ace-scroll" style="position: relative;"><div class="scroll-track" style="display: none;"><div class="scroll-bar"></div></div><div class="scroll-content">
							<ul class="dropdown-menu dropdown-navbar navbar-pink">
								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-pink fa fa-comment"></i>
												New Comments
											</span>
											<span class="pull-right badge badge-info">+12</span>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										<i class="btn btn-xs btn-primary fa fa-user"></i>
										Bob just signed up as an editor ...
									</a>
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-success fa fa-shopping-cart"></i>
												New Orders
											</span>
											<span class="pull-right badge badge-success">+8</span>
										</div>
									</a>
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-info fa fa-twitter"></i>
												Followers
											</span>
											<span class="pull-right badge badge-info">+11</span>
										</div>
									</a>
								</li>
							</ul>
						</div></li>

						<li class="dropdown-footer">
							<a href="#">
								See all notifications
								<i class="ace-icon fa fa-arrow-right"></i>
							</a>
						</li>
					</ul> -->
				</li>
				<!-- 用户登录信息 -->
				<li class="light-blue"><a data-toggle="dropdown" href="#" class="dropdown-toggle"> <img
						class="nav-user-photo" src="${ctx }/static/ace/1.3.3/avatars/user.jpg" /> <span class="user-info"> 欢迎,</span>
							<span class=""><shiro:principal property="name" /></span>
					</span> <i class="ace-icon fa fa-caret-down"></i>
				</a>

					<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
						<li><a href="${ctx }/sys/manager/updatePwd"> <i class="ace-icon fa fa-key"></i> 修改密码
						</a></li>
						<li class="divider"></li>
						<li><a href="${ctx }/logout"> <i class="ace-icon fa fa-power-off"></i> 退出系统
						</a></li>
					</ul></li>
			</ul>
		</div>
		<audio id="sound" autoplay="autoplay"/>
	</div>
	<!-- /.navbar-container -->
</div>