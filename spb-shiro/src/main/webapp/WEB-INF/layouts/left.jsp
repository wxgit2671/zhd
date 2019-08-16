<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<script language="javascript" type="text/javascript"
	src="${ctx}/static/treeTable/jquery.treeTable.min.js"></script>


<link href="${ctx}/static/treeTable/themes/vsStyle/treeTable.min.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript">
	$(function() {
		var menus;
		var menusV;
		$.ajax({
			url : '${ctx }/sys/menu/leftjsp',
			async : false,
			success : function(result) {
				//alert(result);
				//alert(JSON.stringify(result));
				menus = result.data;
				var target = $('#menu');
				$('#menu').sidebarMenu({
					data : menus
				}, target);
			}
		});

		$("li.active").parents("li").addClass("open active");

	});
	
	
	
/*
 * mimidai 
 * 
 * */
(function ($) {
 $.fn.sidebarMenu = function (options) {
  options = $.extend({}, $.fn.sidebarMenu.defaults, options || {});
  var target = $(this);
  target.addClass('nav');
  target.addClass('nav-list');
  if (options.data) {
   init(target, options.data);
  }
  else {
   if (!options.url) return;
   $.getJSON(options.url, options.param, function (data) {
    init(target, data);
   });
  }
  function init(target, data) {
   $.each(data, function (i, item) {
	// 打开匹配成功的目录
	var focusPath=getFocusPath();
    var li = $('<li></li>');
      var perMenuUrl=item.url;
      if(perMenuUrl!=undefined){
    	if(focusPath.length>2){   //如果没有该条件 则会导致访问根路径时，全部匹配成功
          if(perMenuUrl.indexOf(focusPath) >= 0){  //菜单路径 包含 不带参数的当前请求路径
          	li.attr("class","active");
          	li.addClass("active");
          }
    	}
      }
      
    //以下内容为组装目录结构
    var a = $('<a></a>');
    var nodeInfo;
    if(item.fatherId!='0'){
    	nodeInfo=item.fatherId+'.'+item.id;
    }else{
    	nodeInfo=item.id;
    }
    a.attr('node', nodeInfo);
    //target 打开方式 1的话，打开新页面
    if(item.target=='1'){
    	a.attr('target','_blank');
    }
    var icon = $('<i></i>');
    //icon.addClass('glyphicon');
    //定义图标数组
    var iconArray=['menu-icon fa fa-desktop','menu-icon fa fa-list','menu-icon fa fa-btc','menu-icon fa fa-user','menu-icon fa fa-credit-card','menu-icon fa fa-bar-chart-o','menu-icon fa fa-at','menu-icon fa fa-pencil-square-o'];
    //icon.addClass(item.icon);  配置icon
    //icon.addClass('menu-icon fa fa-list'); 当前配置导致 全局 统一图标
    var text = $('<span></span>');
    text.addClass('menu-text').text(item.text);
    //a.append(icon); 为配合 分类化图表移动
    //a.append(text);
    if (item.menus&&item.menus.length>0) {
    	 var iconSit=i%8;
    	 icon.addClass(iconArray[iconSit]);
    	 a.append(icon); 
         a.append(text);
    	 a.attr('href', '#');
         a.addClass('dropdown-toggle');
         var arrow = $('<b></b>');
         arrow.addClass('arrow').addClass('arrow fa fa-angle-down');
         a.append(arrow);
         li.append(a);
         var menus = $('<ul></ul>');
         menus.addClass('submenu');
         init(menus, item.menus);
         li.append(menus);
    }
    else {
    	var href = 'javascript:addTabs({id:\'' + item.id + '\',title: \'' + item.text + '\',close: true,url: \'' + item.url + '\'});';
    	icon.addClass('menu-icon fa fa-at');
    	a.append(icon); 
        a.append(text);
        //为没有子菜单的菜单添加id
        a.attr('id', item.id);
        
    	var hrefTemp;
    	if(typeof(item.url) == 'undefined'){
    		hrefTemp='#';
    	}else{
    		hrefTemp=item.url;
    	}
    	
    	var curWwwPathout=window.document.location.href;
    	
    	var hrefResult=getRootPath()+hrefTemp;
    	
    	//debugger;
    	var hrefhost="${ctx }";
    	hrefResult=hrefhost+hrefTemp;
    	//alert(hrefResult);
    	
    	
    	a.attr('href', hrefResult); // 单击链接 
//    	alert(a.attr('href'));
    	//a.attr('href', href); 
     //if (item.istab)
     // a.attr('href', href);
     //else {
     // a.attr('href', item.url);
     // a.attr('title', item.text);
     // a.attr('target', '_blank')
     //}
     li.append(a);
     // shiro.append(li);
    }
   // debugger;
    target.append(li);
  /*  if(focusPath==item.url){
    	alert("get in");
    	li.attr("class","active");
    	li.addClass("active");
    	alert(item.url);
    	var test=li.parent();
    	var test1= test.parent().addClass("active open");
    	alert(test1);
    	alert(test1.length);
    	alert(test1.html);
    	alert(JSON.stringify(test1));
    	li.parents('li').attr("class","active open");
    	li.parents('li').addClass("active open");
    }*/
    //alert(JSON.stringify(target));
   });
  }
 }
 /** 
  * //获取当前项目根路径 
  * @return {TypeName}  
  */  
 function getRootPath(){      
     //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp      
     var curWwwPath=window.document.location.href;  
//     alert("curWwwPath  ="+curWwwPath);
     //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp      
     var pathName=window.document.location.pathname;      
     var pos=curWwwPath.indexOf(pathName);      
     //获取主机地址，如： http://localhost:8083      
     var localhostPaht=curWwwPath.substring(0,pos);      
     //获取带"/"的项目名，如：/uimcardprj      
     var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);      
     return(localhostPaht+projectName);  
 };
 /**
  * 选中 信息 li 的样式 改为 active
  * */
 function getFocusPath(){
	 //获取当前网址，如： http://cms.mimidai.com/share/meun.jsp    22
     var curWwwPath=window.document.location.href;      
     //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp      
     var pathName=window.document.location.pathname;      
     var pos=curWwwPath.indexOf(pathName);      
     //获取主机地址，如： http://localhost:8083      
     var localhostPaht=curWwwPath.substring(0,pos);      
     //获取带"/"的项目名，如：/uimcardprj      
     var AIMPATH=pathName.substring(pathName.substr(1).indexOf('/')+1);
//     alert("如果是生产 匹配"+pathName);
//     //获取 URL查询部分
//     var search =window.document.location.search;
//     //alert("aim :"+AIMPATH);
//     //alert("search :"+search);
//     if(search!=''){
//    	 AIMPATH=AIMPATH+search;
//     }
    // var runEnv=curWwwPath.indexOf("http://cms.mimidai.com"); //22 length   //测试改为cow
     /* if(runEnv==0){
    	 //生产路径
    	 AIMPATH=curWwwPath.substr(22);  //带参数的方式 
    	 AIMPATH=pathName;               //不带参数的配置
    	 //alert("不带参数的urlPath="+AIMPATH);
     } */
     sublength="${ctx }".length;
     //alert("ctx is :"+sublength);
     AIMPATH=pathName.substr(sublength);
     //alert(AIMPATH+"!!!");
     return AIMPATH;
 }
 
 
 $.fn.sidebarMenu.defaults = {
  url: null,
  param: null,
  data: null
 };
})(jQuery);
	

</script>
<div class="sidebar" id="sidebar">
	<ul class="nav nav-list" id="menu">
		<li class=""><a href="${ctx }/"> <i
				class="menu-icon fa fa-home"></i> <span class="menu-text"> 首页
			</span>
		</a> <b class="arrow"></b></li>
	</ul>

	<div class="sidebar-collapse" id="sidebar-collapse">
		<i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
			data-icon2="icon-double-angle-right"></i>
	</div>
	<!-- 底部收缩图标 -->
	<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
		<i class="ace-icon fa fa-angle-double-left"
			data-icon1="ace-icon fa fa-angle-double-left"
			data-icon2="ace-icon fa fa-angle-double-right"></i>
	</div>

</div>