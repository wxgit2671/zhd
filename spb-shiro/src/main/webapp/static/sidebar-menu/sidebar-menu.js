/*
 * mimidai 
 * 
 * */
(function ($) {
 $.fn.sidebarMenu = function (options) {
  options = $.extend({}, $.fn.sidebarMenu.defaults, options || {});
  //alert(option);
  var target = $(this);
  //debugger;
  //alert(JSON.stringify(target));
  target.addClass('nav');
  target.addClass('nav-list');
  //alert(options.data);
  if (options.data) {
   init(target, options.data);
  }
  else {
   if (!options.url) return;
   $.getJSON(options.url, options.param, function (data) {
    init(target, data);
   });
  }
  //var url = window.location.pathname;   
  //menu = target.find("[href='" + url + "']");
  //menu.parent().addClass('active');
  //menu.parent().parentsUntil('.nav-list', 'li').addClass('active').addClass('open');
  function init(target, data) {
   $.each(data, function (i, item) {
	   var focusPath=getFocusPath();
	var circleTime=1;
	//alert(shiroTag);
	//debugger;
	//alert(shiroTag.attr('name'));
	//console.log(shiroTag);
	var shiro=$('<shiro:hasPermission></shiro:hasPermission>');
	shiro.attr('name',item.mark);
    var li = $('<li></li>');
   // alert(focusPath==item.path);
//    alert("focusPath:"+focusPath);
//    alert("item.url:"+item.url);
 //   if(focusPath==item.url){  匹配相等
      var perMenuUrl=item.url;
//      alert("perMenu="+perMenuUrl);
//      var aa;
//      var cc= aa.indexindexOf(focusPath);
      if(perMenuUrl!=undefined){
    	if(focusPath.length>2){   //如果没有该条件 则会导致访问根路径时，全部匹配成功
          if(perMenuUrl.indexOf(focusPath) >= 0){  //菜单路径 包含 不带参数的当前请求路径
          	//alert("get in");
          	li.attr("class","active");
          	li.addClass("active");
          	//alert(item.url);
          	//li.parent().parent().addClass("active open");
          }
    	}
      }
      

    var a = $('<a></a>');
    var nodeInfo;
    if(item.fatherId!='0'){
    	nodeInfo=item.fatherId+'.'+item.id;
    }else{
    	nodeInfo=item.id;
    }
    a.attr('node', nodeInfo);
    //target
    if(item.target=='1'){
    	a.attr('target','_blank');
    }
    var icon = $('<i></i>');
    //icon.addClass('glyphicon');
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
         shiro.append(li);
    }
    else {
    	var href = 'javascript:addTabs({id:\'' + item.id + '\',title: \'' + item.text + '\',close: true,url: \'' + item.url + '\'});';
    	//var href = '/'+item.url;
    	//alert(item.url);
    	icon.addClass('menu-icon fa fa-at');
    	a.append(icon); 
        a.append(text);

    	var hrefTemp;
    	if(typeof(item.url) == 'undefined'){
    		hrefTemp='#';
    	}else{
    		hrefTemp=item.url;
    	}
//    	alert(getRootPath());
//    	1.用js判断一个字符串是否是以某个子字符串开头如：ssss001是否以ssss开头，可以这样做：
//    	var fdStart = strCode.indexOf("ssss");
    	// 获取当前路径  带http 协议 即：http://cms.mimidai.com/jiekou
    	var curWwwPathout=window.document.location.href;
    	
    	var hrefResult=getRootPath()+hrefTemp;
//    	alert("ruguo ceshi :" +hrefResult);
    	
    	var runEnv=curWwwPathout.indexOf("http://cms.mimidai.com");
    	
    	if(runEnv == 0){
    		//生产环境下 方案一
    		var realPath='http://cms.mimidai.com';
    		hrefResult=realPath+hrefTemp;
    		
    	}else{
    		//如果 当前访问路径不是 生产环境环境
   		 	hrefResult=getRootPath()+hrefTemp;
    	}
//   	else{
		//生产环境下  方案二 （相对路径方式 ）
//   		var realPath='http://cms.mimidai.com';
//   		hrefResult=hrefTemp; 		
//	}
    	
//    	//测试生产环境地址  start
//    	var realPath='http://cms.mimidai.com';
//		hrefResult=realPath+hrefTemp;
////		alert("如果为生产则访问："+hrefResult);
//    	//测试生产环境地址  end
//    	// 测试生产环境 使用相对路径访问 start
//		hrefResult=hrefTemp;
//		// 测试生产环境 使用相对路径访问 end
		
    	
    	//alert(hrefResult);
    	//var curentPro=function getRootPath();
    /*	alert(curentPro+hrefTemp);
    	hrefTemp=curentPro+hrefTemp;*/
    	
    	//alert(item.url.substr(1));
    	//alert(item.url);
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
     shiro.append(li);
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
     var runEnv=curWwwPath.indexOf("http://cms.mimidai.com"); //22 length   //测试改为cow
     if(runEnv==0){
    	 //生产路径
    	 AIMPATH=curWwwPath.substr(22);  //带参数的方式 
    	 AIMPATH=pathName;               //不带参数的配置
    	 //alert("不带参数的urlPath="+AIMPATH);
     }
     
     return AIMPATH;
 }
 
 
 $.fn.sidebarMenu.defaults = {
  url: null,
  param: null,
  data: null
 };
})(jQuery);