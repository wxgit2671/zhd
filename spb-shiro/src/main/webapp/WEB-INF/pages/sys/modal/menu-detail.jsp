<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>菜单详情</title>
</head>
<body>


	<div class="modal-body no-padding bootbox-data">
		<!-- /.page-header -->
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<form id="inputForm" modelAttribute="menu"
					action="${ctx}/sys/menu/save" method="post" class="form-horizontal">
					<input type="hidden" value=${fatherId}>
					<div class="form-group form-validator">
						<label class="col-sm-2 control-label no-padding-right" for="name">名称
						</label>
						<div class="col-sm-5">
							<input type="text" info="请输入名称" check="require" msg="登录名必填"
								value="${menu.name }" readonly="true" id="name" name="name" readonly="true"
								class="form-control" />
						</div>
						<div class="col-sm-5" imgfor="name"></div>
					</div>
					<div class="form-group form-validator">
						<label class="col-sm-2 control-label no-padding-right" for="path">链接</label>
						<div class="col-sm-5">
							<input type="text" info="请输入链接" id="path" name="path" readonly="true"
								value="${menu.path }" class="form-control" />
						</div>
						<div class="col-sm-5" imgfor="path"></div>
					</div>
					<div class="form-group form-validator">
						<label class="col-sm-2 control-label no-padding-right" for="mark">权限标识：</label>
						<div class="col-sm-5">
							<input type="text" info="请输入权限标识" check="require" msg="权限标识必填" readonly="true"
								value="${menu.mark }" id="mark" name="mark" class="form-control" />
						</div>
						<div class="col-sm-5" imgfor="mark"></div>
					</div>

					<div class="form-group form-validator">
						<label class="col-sm-2 control-label no-padding-right" for="flag">生效：</label>
						<div class="col-sm-2">

							<input type="text" value="${menu.flag eq '0'?'是':'否' }" readonly="true"
								class="form-control" />
							<%-- <select class="form-control" style="height: 30px;" name="flag" value="${menu.flag}">
								<option value="0">是</option>
								<option value="1">否</option>
							</select> --%>
						</div>
						<label class="col-sm-2 control-label no-padding-right">是否为左侧导航菜单：</label>
						<div class="col-sm-2">
							<input type="text" value="${menu.leftMenu eq '1' ?'是':'否' }" readonly="true"
								class="form-control" />
							<%-- <select class="form-control" style="height: 30px;" name="isMenu" value="${menu.isMenu }">
								<option value="true">是</option>
								<option value="false">否</option>
							</select> --%>
						</div>
					</div>
					<div class="form-group form-validator">
						<label class="col-sm-2 control-label no-padding-right" for="flag">排序：</label>
						<div class="col-sm-2">
							<input type="text" value="${menu.sort }" readonly="true"
								class="form-control" />
						</div>
						<label class="col-sm-2 control-label no-padding-right" for="flag">打开方式：</label>
						<div class="col-sm-2">
							<input type="text" value="${menu.target eq '1'?'新窗口':'当前系统风格'}" readonly="true"
								class="form-control" />
						</div>
					</div>
					<div class="form-group form-validator">
						<label class="col-sm-2 control-label no-padding-right"
							for="description">简述：</label>
						<div class="col-sm-5">
							<textarea id="description" class="form-control"
								name="description" placeholder="该菜单没有相关信息"  maxlength="250" readonly="true"
								value="${menu.description }"
								style="margin-bottom: 6px; width: 511px; height: 75px;">${menu.description }</textarea>
						</div>
						<div class="col-sm-5" imgfor="description"></div>
					</div>
				</form>
			</div>
		</div>

		<!-- /.page-content -->
	</div>
	<script type="text/javascript">
		$(function() {
			$("#inputForm").validator();
		});
	</script>
</body>
</html>