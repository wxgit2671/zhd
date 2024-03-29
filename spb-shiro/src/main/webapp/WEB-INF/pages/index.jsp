<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<script type="text/javascript">
	$(function(){
		$("#btnAlter").click(function(){
			bootbox.alert({
				title:"操作提示",
				message:"操作成功！"
			});
		});

		$("#btnAlter2").click(function(){
			bootbox.alert({
				width : "1200px",
				url : "${ctx}/modal/check/phonelist"
			});
		});
		
		$("#btnConfirm").click(function(){
			bootbox.confirm({
				title:"操作提示",
				message: "确定删除吗?",
				callback: function(result) {
					if(result) alert(1)
				}

			});
		});
		$("#btnCustomer").click(function(){
			bootbox.dialog({
				title : "自定义按钮操作",
				message: "<span class='bigger-110'>I am a custom dialog with smaller buttons</span>",
				buttons: 			
				{
					"success" :
					 {
						"label" : "<i class='ace-icon fa fa-check'></i> Success!",
						"className" : "btn-sm btn-success",
						"callback": function() {
							//Example.show("great success");
						}
					},
					"danger" :
					{
						"label" : "Danger!",
						"className" : "btn-sm btn-danger",
						"callback": function() {
							//Example.show("uh oh, look out!");
						}
					}, 
					"click" :
					{
						"label" : "Click ME!",
						"className" : "btn-sm btn-primary",
						"callback": function() {
							//Example.show("Primary button");
						}
					}, 
					"button" :
					{
						"label" : "Just a button...",
						"className" : "btn-sm"
					}
				}

			});
		});
	});
</script>
</head>
<body>
	<div class="breadcrumbs" id="breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="ace-icon fa fa-home home-icon"></i> <a href="/" th:href="@{/}">${sn }</a></li>
		</ul>
	</div>
</body>
</html>
