		function int(I,k){  
			//define variable  
			var ff=0;  
			var j;  
			j=Math.round(I/k)-I/k;  
			if (j>=0.5)  
			ff=Math.round(I/k)-1;  
			if (j<=0)  
			ff=Math.round(I/k);  
			return ff;  
		}
		function queryByPage(queryStr,pageNo,orderBy,orderDir){
			var form = $("<form></form>").attr("action",window.loaction).attr("method","post");
			var paramMaps = queryStr.split("&");
			for(var i in paramMaps){
				var params = paramMaps[i].split("=");
				var input = $("<input/>").attr("name",params[0]).val(params[1]);
				form.append(input);
			}
			form.submit();
		}
		function click_nav_menu(obj_node) {
			url = $(obj_node).attr("href");
			if (url.length > 0 && url!="#") {
				node = $(obj_node).attr("node");
				set_cookie("current_node", node);
			} else {
				//node = $(obj_node).parent().find("ul li a:first").attr("node");
				//set_cookie("current_node", node);
				//url = $(obj_node).parent().find("ul li a:first").attr("href");
				//if (url !== undefined) {
				//	location.href = url;
				//}
				//return false;
			}
		}

		/*设置 cookie*/
		function set_cookie(key, value, exp, path, domain, secure) {
			path = "/";
			var cookie_string = key + "=" + escape(value);
			if (exp) {
				cookie_string += "; expires=" + exp.toGMTString();
			}
			if (path)
				cookie_string += "; path=" + escape(path);
			if (domain)
				cookie_string += "; domain=" + escape(domain);
			if (secure)
				cookie_string += "; secure";
			document.cookie = cookie_string;
		}

		/*读取 cookie*/
		function get_cookie(cookie_name) {
			var results = document.cookie.match('(^|;) ?' + cookie_name + '=([^;]*)(;|$)');
			if (results)
				return (unescape(results[2]));
			else
				return null;
		}

		/*删除 cookie*/
		function del_cookie(cookie_name) {
			var cookie_date = new Date();
			//current date & time
			cookie_date.setTime(cookie_date.getTime() - 1);
			document.cookie = cookie_name += "=; expires=" + cookie_date.toGMTString();
		}
		
		$(document).ready(function() {
			//屏蔽所有回车键
			$("*").keydown(function() {
				//keyCode=13是回车键
	            if (event.keyCode == "13") {
	            }
	        });
			$(".sidebar .nav a").click(function() {
				click_nav_menu($(this));
			})
			$('.ul_table .tbody input[type=checkbox]').removeAttr('checked');
			$('.ul_table').delegate('.tbody input[type=checkbox]', 'click', function() {
				$(this).closest('.tbody').toggleClass('selected');
				if (this.checked)
					ul_table.display_bar(1);
				//display action toolbar when a message is selected
				else {
					ul_table.display_bar($('.ul_table input[type=checkbox]:checked').length);
					//determine number of selected messages and display/hide action toolbar accordingly
				}
			});
			$('#id-toggle-all').removeAttr('checked').on('click', function() {
				if (this.checked) {
					ul_table.select_all();
				} else
					ul_table.select_none();
			});
			breadcrumb="";
			current_node = get_cookie("current_node");
			$(".sidebar .nav a[node='" + current_node + "']").parents("li").each(function(index,element){
				if (index > 0) {
					$(this).addClass("active open");
				} else {
					$(this).addClass("active");
				}
				breadcrumb='<li>'+$(this).find("a:first").text()+'</li>'+breadcrumb;					
			});
			
			if ($(".select-list dd")) {
				$(".select-list dd").click(function(){
					var unvalidate = $(this).attr("unvalidate");
					if(!unvalidate){
						$(this).siblings(".selected").removeClass("select-all selected");
						$(this).addClass("select-all selected");

						var bind = $(this).attr("data-bind").split("-");
						if (bind[1]=="all"){
							bind[1]="";
						}
						$(bind[0]).val(bind[1]);
					}
					
				});
			}
			//所有具有change-chinese属性的的元素中的文字，把阿拉伯数字变成中文
			$("*[change-chinese]").each(function(){
				var text = $(this).text();
				var reg = new RegExp("(\\d+)","g");
				var mas = text.match(reg);
				for(var i in mas){
					var cha = new change(mas[i]);
					text = text.replace(mas[i],cha.pri_ary());
				}
				$(this).text(text);
			});
			//绑定局部刷新事件
			$("*[data-query]").bind("div.reload",function(){
				var current = $(this);
				var target = current.attr("data-target");
				//目标是自身
				if(target === '_self'){
					target = current;
				}
				var query = current.attr("data-query");
				var belong = current.attr("data-belong");
				$(target).loading();
				$(target).loadContent(query);
				if(belong){
					$("*[data-belong='"+belong+"'][data-default]").removeAttr("data-default");
					current.attr("data-default","");
				}
				
			});
            //监测当前需要局部刷新的元素
            $("*[data-query]").each(function(){
                var element = $(this);
                var target = element.attr("data-target");
                var query = element.attr("data-query");
                if(typeof element.attr("data-default") != 'undefined'){
                    element.trigger("div.reload");
                } else if (typeof $(this).attr("data-collapse") != 'undefined') {
                	//为加载时闭合的局部页面绑定一次性加载事件
                    $(this).siblings().find("*[data-action]").bind("click",function(){
                        if(typeof $(this).attr("data-ready") == 'undefined'){
                            $(target).loading();
                            $(target).loadContent(query);
                            $(this).attr("data-ready",true);
                        }
                    });
                }
            });
			//绑定点击事件
			$("*[data-query]").click(function(){
				if(typeof $(this).attr("data-tab") != 'undefined'){
                    //若处于闭合状态则展开
                    if($(this).parent().parent().is('.collapsed')){
                        $(this).siblings().find("*[data-action]").click();
                    } else {
                        $(this).trigger("div.reload");
                    }
				}
			});
			$(".select-all[type='checkbox']").bind("click",function(){
				var $selectAll = $(this);
				var range = $selectAll.data("range");
				var checked = $selectAll[0].checked;
				$(range).find(".select-single[type='checkbox']").each(function(){
					$(this)[0].checked = checked;
				});
			});
			$(".select-single[type='checkbox']").bind("click",function(){
				var $selectSingle = $(this);
				var range = $selectSingle.data("range");
				var checked = $selectSingle[0].checked;
				var $selectAll = $(range).find(".select-all");
				if($selectAll[0].checked != checked){
					var flag = false;//默认不选中
					$(range).find(".select-single[type='checkbox']").each(function(){
						if($(this)[0].checked){
							flag = true;//只要有一个选中，selectAll选中
							return false;
						}
					});
					$selectAll[0].checked = flag;
				}
			});
			$("*[data-type]").bind('check.type',function(){
				var current = $(this);
				var type = current.data("type");
				var value = current.val() || current.text();
				if(value){
					var flag = $.checkType(type,value);
					if(!flag){
						value = "";
						bootbox.alert({
							title : '提示',
							message : '类型不符'
						});
					}
					current.val() ? current.val(value) : current.text(value);
				}
			});
			$("*[number-min],*[number-max]").bind('check.range',function(){
				var flag = false;
				
				var current = $(this);
				var value = current.val() || current.text();
				if(value){
					value = isNaN(value) ? 0 : parseFloat(value);
					
					var max = isNaN(current.attr("number-max")) ? Number.MAX_VALUE : parseFloat(current.attr("number-max"));
					var min = isNaN(current.attr("number-min")) ? Number.MIN_VALUE : parseFloat(current.attr("number-min"));
					if(max >= min){
						if(max < value){
							value = max;
							flag = true;
						}
						if(min > value){
							value = min;
							flag = true;
						}
					}
					if(flag){
						bootbox.alert({
							title : '提示',
							message : '超出范围'
						});
					}
					current.val() ? current.val(value) : current.text(value);
				}
			});
			$("*[data-type]").bind('blur',function(){
				$(this).trigger("check.type");
				$(this).trigger("check.range");
			});
//			function select(element, target, val) {
//				$(target).val(val);
//				$(element).siblings(".selected").removeClass(
//						"select-all selected");
//				$(element).addClass("select-all selected");
//			}
			//$(".breadcrumb").append(breadcrumb);
		}); 	