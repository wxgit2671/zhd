<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>菜单编辑</title>
</head>
<body>
	

	<div class="modal-body no-padding bootbox-data">
		
		<!-- /.page-header -->
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<form id="inputForm" modelAttribute="menu"
					action="${ctx}/sys/menu/addMenuSubmit" method="post" class="form-horizontal">
					<input type="hidden" name="fatherId" value=${fatherId}>
					<div class="form-group form-validator">
						<label class="col-sm-2 control-label no-padding-right" for="name">名称<span style="color:red">*</span>
						</label>
						<div class="col-sm-5">
							<input type="text" info="请输入名称" check="require" msg="登录名必填" placeholder="必填项"
								id="name" name="name" class="form-control" />
						</div>
						<div class="col-sm-5" imgfor="name"></div>
					</div>
					<div class="form-group form-validator">
						<label class="col-sm-2 control-label no-padding-right" for="path">链接</label>
						<div class="col-sm-5">
							<input type="text" info="请输入链接" id="path" name="path"
								class="form-control" />
						</div>
						<div class="col-sm-5" imgfor="path"></div>
					</div>
					<div class="form-group form-validator">
						<label class="col-sm-2 control-label no-padding-right" for="mark">权限标识<span style="color:red">*</span></label>
						<div class="col-sm-5">
							<input type="text" info="请输入权限标识" check="require" msg="权限标识必填" placeholder="必填项"
								id="mark" name="mark" class="form-control" />
						</div>
						<div class="col-sm-5" imgfor="mark"></div>
					</div>

					<div class="form-group form-validator">
						<label class="col-sm-2 control-label no-padding-right" for="flag">生效</label>
						<div class="col-sm-2">
							<select class="form-control" style="height: 30px;" name="flag">
								<option value="0">是</option>
								<option value="1">否</option>
							</select>
						</div>
					</div>
					<div class="form-group form-validator">
						<label class="col-sm-2 control-label no-padding-right">导航菜单</label>
						<div class="col-sm-2">
							<select class="form-control" style="height: 30px;" name="leftMenu">
								<option value="1">是</option>
								<option value="0">否</option>
							</select>
						</div>
					</div>
					<div class="form-group form-validator">
						<label class="col-sm-2 control-label no-padding-right" for="name">排序
						</label>
						<div class="col-sm-5">
							<input type="number" info="请输入1-99999以内的整数" check="require" msg="" min="1" max="99999"  placeholder="请输入1-99999以内的整数"  
								value="${menu.sort }" id="sort" name="sort" class="form-control" />
						</div>
						<div class="col-sm-5" imgfor="sort"></div>
					</div>
					<div class="form-group form-validator">
						<label class="col-sm-2 control-label no-padding-right" for="flag">生效</label>
						<div class="col-sm-2">
							<select class="form-control" style="height: 30px; width:120px;" name="target">
								<option value="0">当前系统风格</option>
								<option value="1">新窗口</option>
							</select>
						</div>
					</div>
					<div class="form-group form-validator">
						<label class="col-sm-2 control-label no-padding-right"
							for="description">简述</label>
						<div class="col-sm-5">
							<textarea id="description" class="form-control"
								name="description" placeholder="菜单简述（不超过250个字符）" maxlength="250"
								style="margin-bottom: 6px;  width: 385px; height: 103px;"></textarea><!-- width: 511px; height: 75px; -->
						</div>
						<div class="col-sm-5" imgfor="description"></div>
					</div>
				


				<!-- 	<div class="clearfix form-actions">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn btn-info" type="submit">
								<i class="ace-icon fa fa-check bigger-110"></i> 提交
							</button>
						</div>
						<div class="col-sm-5"></div>
					</div> -->
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