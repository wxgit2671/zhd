<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="targetAttr" type="java.lang.String" required="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${targetAttr == null || targetAttr == '' }">
	<div class="modal-footer no-margin-top">
		<div class="pull-left">
			共 <span class="text-success"><b>${page.totalCount}</b></span> 条记录
		</div>
		<ul class="pagination pull-right no-margin">
			<li title="首页" class="prev ${page.firstPage?'disabled':'' }"><a href="javascript:${page.firstPage?'return false;':'' }pageReload('1','${page.orderBy}','${page.orderDir}');"> <i
					class="ace-icon fa fa-angle-double-left"></i>
			</a></li>
			<li title="上一页" class="prev ${page.firstPage?'disabled':'' }"><a href="javascript:${page.firstPage?'return false;':'' }pageReload('${page.pageNo - 1 }','${page.orderBy}','${page.orderDir}');"> <i
					class="ace-icon fa fa-angle-left"></i>
			</a></li>
			<c:forEach var="i" items="${page.slider }">
				<li <c:if test="${page.pageNo==i }">class="active"</c:if>>
				 <a href="javascript:pageReload('${i }','${page.orderBy}','${page.orderDir}');">${i }</a></li>
			</c:forEach>
			<li title="下一页" class="next ${page.lastPage?'disabled':'' }"><a href="javascript:${page.lastPage?'return false;':'' }pageReload('${page.pageNo + 1 }','${page.orderBy}','${page.orderDir}');"> <i
					class="ace-icon fa fa-angle-right"></i>
			</a></li>
			<li title="尾页" class="next ${page.lastPage?'disabled':'' }"><a href="javascript:${page.lastPage?'return false;':'' }pageReload('${page.totalPages}','${page.orderBy}','${page.orderDir}');"> <i
					class="ace-icon fa fa-angle-double-right"></i>
			</a></li>
			<li title="跳转页">&nbsp;&nbsp; <span style="border: none;padding: 0 0 0 0;"><input type="text" value="${page.pageNo}" id="gotoPageNo" style="width: 50px;height:31px;"></span><a href="javascript:goto();">跳转</a></li>
			<li title="跳转"></li>
		</ul>
	</div>
	<script>
		function goto() {
			pageReload($('#gotoPageNo').val(),'${page.orderBy}','${page.orderDir}')
		}
		function pageReload(pageNo,orderBy,orderDir){
			if($("form:first").length){
				$("form:first").addData({
					pageNo:pageNo,
					orderBy:orderBy,
					pageSize:'${page.pageSize}',
					orderDir:orderDir
				}).submit()
			}else{
				var href = window.location.href;
				if(href){
					var index = href.indexOf("?");
					if(index != -1){
						var queryStr = href.split("?")[0];
						var paramStr = href.split("?")[1];
						var params = paramStr.split("&");
						for(var i in params){
							var param = params[i];
							if(param.indexOf("pageNo=") != -1){
							}else if(param.indexOf("orderBy=") != -1){
							}else if(param.indexOf("orderDir=") != -1){
							}else{
								 if(href.indexOf("?") == -1){
									 queryStr += "?" + params;
								 }else{
									 queryStr += "&" + params;									 
								 }
							}
						}
						if(queryStr.indexOf("?") == -1){
							href = queryStr + "?pageNo="+pageNo+"&orderBy="+orderBy+"&orderDir="+orderDir;
						}else{
							href = queryStr + "&pageNo="+pageNo+"&orderBy="+orderBy+"&orderDir="+orderDir;
						}
					}else{
						href += "?pageNo="+pageNo+"&orderBy="+orderBy+"&orderDir="+orderDir;
					}
					window.location = href;
				}else{
					
				}
			}
		}
	</script>
</c:if>
<c:if test="${targetAttr != null && targetAttr != ''}">
	<ul class="pagination">
		<li title="首页" class="prev ${page.firstPage?'disabled':'' }">
			<a href="javascript:${page.firstPage?'return false;':'' }addParam('${targetAttr}','${1}','${page.orderBy}','${page.orderDir}');"> 
				<i class="ace-icon fa fa-angle-double-left"></i>
			</a>
		</li>
		<li title="上一页" class="prev ${page.firstPage?'disabled':'' }">
			<a href="javascript:${page.firstPage?'return false;':'' }addParam('${targetAttr}','${page.pageNo - 1 }','${page.orderBy}','${page.orderDir}');"> 
				<i class="ace-icon fa fa-angle-left"></i>
			</a>
		</li>

		<c:forEach var="i" items="${page.slider }">
			<li <c:if test="${page.pageNo==i }">class="active"</c:if>>
			 	<a href="javascript:addParam('${targetAttr}','${i}','${page.orderBy}','${page.orderDir}');">${i }</a>
			</li>
		</c:forEach>
		<li title="下一页" class="next ${page.lastPage?'disabled':'' }">
			<a href="javascript:${page.lastPage?'return false;':'' }addParam('${targetAttr}','${page.pageNo + 1 }','${page.orderBy}','${page.orderDir}');">
				<i class="ace-icon fa fa-angle-right"></i>
			</a>
		</li>
		<li title="尾页" class="next ${page.lastPage?'disabled':'' }">
			<a href="javascript:${page.lastPage?'return false;':'' }addParam('${targetAttr}','${page.totalPages}','${page.orderBy}','${page.orderDir}');">
				<i class="ace-icon fa fa-angle-double-right"></i>
			</a>
		</li>
	</ul>
	<script>
		console.info("分页js");
		function addParam(targetAttr,pageNo,orderBy,orderDir){
			if(targetAttr){
				var element = $(targetAttr);
				var query = element.attr("data-query");
				var target = element.attr("data-target");
				if(query.indexOf("\?") != -1){
					var reg = /&pageNo=.*&orderBy=.*&orderDir=.*/;
					if(reg.test(query)){
						element.attr("data-query",query.replace(reg,"&pageNo="+pageNo+"&orderBy="+orderBy+"&orderDir="+orderDir));
					}else{
						element.attr("data-query",query+"&pageNo="+pageNo+"&orderBy="+orderBy+"&orderDir="+orderDir);
					}
				}else{
					element.attr("data-query",query+"?pageNo="+pageNo+"&orderBy="+orderBy+"&orderDir="+orderDir);
				}
				element.trigger("div.reload");
			}
		}
	</script>
</c:if>