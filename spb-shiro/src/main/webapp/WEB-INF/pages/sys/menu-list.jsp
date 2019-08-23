<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/commons/taglibs.jsp"%>
<%-- <%@ include file="/commons/treeTable.jsp"%> --%>

<!DOCTYPE html>
<html>
<head>
<title>菜单管理</title>
<script language="javascript" type="text/javascript"
	src="${ctx}/static/treeTable/jquery.treeTable.min.js"></script>
<link href="${ctx}/static/treeTable/themes/vsStyle/treeTable.min.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript">
	$(document).ready(function() {
		//debugger;
		$("#listForm").treeTable({
			expandLevel : 1,
			column : 1
		}).show();
	});
</script>
</head>
<body>
	<div class="breadcrumbs" id="breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="ace-icon fa fa-home home-icon"></i> <a
				href="${ctx }/">${sn }</a></li>
			<li>系统管理</li>
			<li>菜单管理</li>
		</ul>
	</div>

	<div class="page-content">
		<div class="page-header">
			<%@include file="/commons/message.jsp"%>
			<div class="clearfix">
				<div class="pull-left tableTools-container">
					<a class="btn btn-primary" onclick="addSubMenu(0)">新建一级菜单</a>
				</div>
			</div>
		</div>

		<!-- /.page-header -->
		<div class="row">

			<form id="listForm" method="post">
				<table id="treeTable"
					class="table  table-bordered table-hover table-condensed dataTables-example dataTable">
					<thead>
						<tr>
							<!-- <th><input type="checkbox" class="i-checks"></th> -->
							<th style="display: none">ID</th>
							<th>名称</th>
							<th>链接</th>
							<th>权限标识</th>
							<th>是否生效</th>
							<th>是否为导航菜单</th>
							<th>当前目录级排序</th>
							<th>打开方式</th>
							<th>操作</th>
							<!-- <sec:hasPermission name="sys:menu:edit">
                            <th>操作</th>
                        </sec:hasPermission> -->
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${menuList}" var="menu">
							<!-- zi 目录-->
							<c:if test="${menu.fatherId!=0}">
								<tr id='${menu.id}' pid='${menu.fatherId}'>
									<td style="display: none"><dd id="${menu.id}">${menu.id}</td>
									<td nowrap><a onclick="queryDetail(${menu.id})">${menu.name}</a></td>
									<td>${menu.path}</td>
									<td>${menu.mark}</td>
									<td>${menu.flag eq 0?'是':'否'}</td>
									<td>${menu.leftMenu eq '1'?'是':'否'}</td>
									<td>${menu.sort }</td>
									<td>${menu.target eq '1'?'新窗口':'当前系统风格'}</td>
									<td><a onclick="addSubMenu(${menu.id})"
										class="btn btn-info btn-xs"><i class="fa fa-search-plus"></i>
											添加下级菜单</a> <a onclick="updateMenu(${menu.id})"
										class="btn btn-success btn-xs"><i class="fa fa-edit"></i>
											修改</a> <c:if test="${menu.flag eq '0' }">
											<a onclick="doDelete(${menu.id})"
												class="btn btn-warning btn-xs"><i class="fa fa-trash"></i>
												失效</a>
											<a onclick="doDeletePhysical(${menu.id})"
												class="btn btn-danger btn-xs  disabled"><i
												class="fa fa-trash"></i> 物理删除（谨慎操作）</a>
										</c:if> <c:if test="${menu.flag ne '0' }">
											<a onclick="doDelete(${menu.id})"
												class="btn btn-warning btn-xs  disabled"><i
												class="fa fa-trash"></i> 失效</a>
											<a onclick="doDeletePhysical(${menu.id})"
												class="btn btn-danger btn-xs "><i class="fa fa-trash"></i>
												物理删除（谨慎操作）</a>
										</c:if> <%--  <a onclick="doDeletePhysical(${menu.id})"
												class="btn btn-danger btn-xs"><i class="fa fa-trash"></i>
											物理删除（谨慎操作）</a> <!-- btn btn-danger btn-xs --> --%></td>
								</tr>
							</c:if>
							<!-- 一级目录 -->
							<c:if test="${menu.fatherId==0}">
								<tr id='${menu.id}'>
									<td style="display: none"><dd id="${menu.id}">${menu.id}</td>
									<td nowrap><a onclick="queryDetail(${menu.id})">${menu.name}</a></td>
									<td>${menu.path}</td>
									<td>${menu.mark}</td>
									<td>${menu.flag eq 0?'是':'否'}</td>
									<td>${menu.leftMenu eq '1'?'是':'否'}</td>
									<td>${menu.sort }</td>
									<td>${menu.target eq '1'?'新窗口':'当前系统风格'}</td>
									<td><a onclick="addSubMenu(${menu.id})"
										class="btn btn-info btn-xs"><i class="fa fa-search-plus"></i>
											添加下级菜单</a> <a onclick="updateMenu(${menu.id})"
										class="btn btn-success btn-xs"><i class="fa fa-edit"></i>
											修改</a> <c:if test="${menu.flag eq '0' }">
											<a onclick="doDelete(${menu.id})"
												class="btn btn-warning btn-xs"><i class="fa fa-trash"></i>
												失效</a>
											<a onclick="doDeletePhysical(${menu.id})"
												class="btn btn-danger btn-xs  disabled"><i
												class="fa fa-trash"></i> 物理删除（谨慎操作）</a>
										</c:if> <c:if test="${menu.flag ne '0' }">
											<a onclick="doDelete(${menu.id})"
												class="btn btn-warning btn-xs  disabled"><i
												class="fa fa-trash"></i> 失效</a>
											<a onclick="doDeletePhysical(${menu.id})"
												class="btn btn-danger btn-xs "><i class="fa fa-trash"></i>
												物理删除（谨慎操作）</a>
										</c:if> <%--  <a onclick="doDeletePhysical(${menu.id})"
												class="btn btn-danger btn-xs"><i class="fa fa-trash"></i>
											物理删除（谨慎操作）</a> <!-- btn btn-danger btn-xs --> --%></td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>

				</table>
			</form>
		</div>

		<script type="text/javascript">
		 function doDelete(id){
					bootbox.confirm({
						title : '提示',
						message : '确定要将该菜单失效吗？',
						callback : function(result){
							if(result){
								window.location = '${ctx}/sys/menu/deleteMenu?id='+id;
							}	
						}
					});
				
		 };
		 function doDeletePhysical(id){
				bootbox.confirm({
					title : '警告',
					message : '确定要将该菜单物理删除吗（谨慎操作）？',
					callback : function(result){
						if(result){
							window.location = '${ctx}/sys/menu/deleteMenuPysical?id='+id;
						}	
					}
				});
			
	 };
		 
		 function addPre(id){
			 bootbox.dialog({
					width:'1024px',
					url:"${ctx}/sys/menu/create?id="+id,
					buttons:{
						"button":{
							"label" : "关闭",
							"className" : "btn-sm btn-danger",
						},
						 "success":{
							"label" : "保存",
							"className" : "btn btn-sm btn-sm btn-primary",
							"callback": function() {
								$("#inputForm").validator();
							}
						 }
					}
		 })
		 };
		 
		 function queryDetail(id){
			 bootbox.dialog({
					width:'1024px',
					url:"${ctx}/sys/menu/detail?id="+id,
					buttons:{
						"button":{
							"label" : "关闭",
							"className" : "btn-sm btn-danger",
						},
						/*  "success":{
							"label" : "保存",
							"className" : "btn btn-sm btn-sm btn-primary",
							"callback": function() {
								$("#inputForm").validator();
							}
						 } */
					}
		 })
		 };
		  function addSubMenu(id){
			 bootbox.dialog({
					url:"${ctx}/sys/menu/create?id="+id,//${ctx}/sys/menu/create?id=${menu.id }
					buttons:{
						"button":{
							"label" : "关闭",
							"className" : "btn-sm btn-danger",
						}
						 ,"success":{
							"label" : "保存",
							"className" : "btn-sm btn-primary",
							"callback": function() {
								if($("#name").val==null || $("#name").val() == ''){
									bootbox.alert({
										title:"操作提示",
										message:"菜单名称不能为空！"
									});
									return false;
								}else if($("#mark").val==null || $("#mark").val() == ''){
									bootbox.alert({
										title:"操作提示",
										message:"权限标识不能为空！"
									});
									return false;
								}else if($("#sort").val==null || $("#sort").val() == ''){
									bootbox.alert({
										title:"操作提示",
										message:"排序不能为空！"
									});
									return false;
								}else{
									 var sortNum=$("#sort").val();
									 var reg=new RegExp("^[1-9][0-9]{0,4}$");
									 //debugger;
									 if(!reg.exec(sortNum)){
										 bootbox.alert({
												title:"操作提示",
												message:"请输入1~99999以内的正整数！"
											});
											return false;
									 }
								}
								$.post('${ctx}/sys/menu/addMenuModalSubmit',$('#inputForm').serialize(),function(result,status){
									if(result.code == '0'){
										bootbox.alert({
											title:"操作提示",
											message:"操作成功！",
											callback:function(){
					                        	// alert 之后点击确定之后的回调函数  
					                        	//$("#queryForm").submit();
					                        	window.location.href= '${ctx}/sys/menu/list';
					                        }
										});
									}
								
								});
								
							}
						}
					} 
				});
				
		 };
		 function updateMenu(id){
			 bootbox.dialog({
					url:"${ctx}/sys/menu/updateMenuPre?id="+id,
					buttons:{
						"button":{
							"label" : "关闭",
							"className" : "btn-sm btn-danger",
						}
						 ,"success":{
							"label" : "保存",
							"className" : "btn-sm btn-primary",
							"callback": function() {
								if($("#name").val==null || $("#name").val() == ''){
									bootbox.alert({
										title:"操作提示",
										message:"菜单名称不能为空！"
									});
									return false;
								}else if($("#mark").val==null || $("#mark").val() == ''){
									bootbox.alert({
										title:"操作提示",
										message:"权限标识不能为空！"
									});
									return false;
								}else if($("#sort").val==null || $("#sort").val() == ''){
									bootbox.alert({
										title:"操作提示",
										message:"排序不能为空！"
									});
									return false;
								}else{
								
									 var sortNum=$("#sort").val();
									 
									 var reg=new RegExp("^[1-9][0-9]{0,4}$");
									 //debugger;
									 if(!reg.exec(sortNum)){
										 bootbox.alert({
												title:"操作提示",
												message:"请输入1~99999以内的正整数！"
											});
											return false;
									 }
								}
							/* 	if($("#target").val()==null || $("#target").val()==''){
									bootbox.alert({
										title:"操作提示",
										message:"排序不能为空！"
									});
									return false;
								} */
								$.post('${ctx}/sys/menu/updateMenuSumbit',$('#update-form').serialize(),function(result,status){
									if(result.code == '0'){
										bootbox.alert({
											title:"操作提示",
											message:"操作成功！",
											callback:function(){
					                        	// alert 之后点击确定之后的回调函数  
					                        	//$("#queryForm").submit();
					                        	window.location.href= '${ctx}/sys/menu/list';
					                        }
										});
									}
								});
							}
						}
					} 
				});
		 };
		 
		</script>
	</div>
	<!-- /.page-content -->
</body>
</html>