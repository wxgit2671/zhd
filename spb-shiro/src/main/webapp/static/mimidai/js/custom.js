(function($,window){
	$.fn.extend({
		loadContent:function(url){
			var element = $(this)
			$.get(url,function(data){
				var html = $(data).find('.content-body');
				element.html(html);
			});
		},
		loading:function(){
			$(this).html("<h3 class='smaller lighter'><i class='ace-icon fa fa-spinner fa-spin orange bigger-125'></i> 请稍候...</h3>");
		},
		ajaxSubForm : function(fun){
			var result = {};
			var formEle = $(this);
			if(formEle.is("form")){
				var enctype = formEle.attr("enctype") || "application/x-www-form-urlencoded";
				var action = formEle.attr("action") || window.location.href;
				if(enctype === 'multipart/form-data'){
					var data = new FormData();
					var files = formEle.find("input[type='file']");
					for(var i = 0 ;i<files.length ; i++){
						var fileEle = files[i];
						data.append(fileEle.name,fileEle.files[0]);
					}
					var paramStr = formEle.serialize();
					if(paramStr){
						var params = paramStr.split("&");
						for(var j = 0 ; j<params.length;j++){
							var param = params[j];
							data.append(param.split("=")[0],_getCharFromUtf8(param.split("=")[1]));
						}
					}
					result = http(data,action,fun);
					
				}else{
					
					var method = formEle.attr("method") || 'post';
					result = formEle.validateResult();
					if(result.code == '1'){
						$.ajax({
							url : action,
							dataType : 'json',
							type : method,
							data : formEle.serialize(),
							success : function(r){
								result = r;
								fun(result);
							},
							error : function(e){
								
							}
						});
					}else{
						fun(result);
					}
				}
				
			}else{
				console.error("只支持form表单异步提交!");
				result = {code : '0' , msg : '只支持form表单异步提交!'};
				fun(result);
			}
			return result;
		},
		addData : function(option){
			var form = $(this);
			if(form.is("form")){
				for(var name in option){
					var value = option[name];
					$(this).append('<input type="hidden" name="'+name+'" value="'+value+'"/>');
				}
				return this;
			}else{
				console.error("只支持form表单添加数据!");
			}
			
		},
		computeResult:function(){
			var current = $(this);
			var ex = current.attr("data-from");
			var prefix = current.attr("data-prefix");
			var suffix = current.attr("data-suffix");
			
			var text = "";
			
			var reg = new RegExp("([^+-/*]+(\\.[^+-/*]+)*)([+-/*]*)","g");
			var sum = 0;
			var op = "";
			do{
				result=reg.exec(ex);
				if(result){
					var currentNumber = 0 ;
					if(result[1].indexOf("#") == 0){
						currentNumber = $(result[1]).val();
						if(isNaN(currentNumber) || !currentNumber){
							currentNumber = 0;
						}else{
							currentNumber = parseFloat(currentNumber);
						}
					}else{
						currentNumber = parseFloat(result[1]);
					}
					if(op == ""){
						sum = currentNumber;
					}else{
						switch (op) {
						case "+":
							sum = sum.add(currentNumber);
							break;
						case "-":
							sum = sum.sub(currentNumber);
							break;
						case "*":
							sum = sum.mul(currentNumber);
							break;
						case "/":
							sum = sum.div(currentNumber);
							break;
						default:
							break;
						}
					}
					op = result[3];
					
				}
			}while (result!=null);
			text = sum
			if(prefix){
				text = prefix + text;
			}
			if(suffix){
				text = text + suffix;
			}
			if(current.is("input")){
				current.val(text);
			}else if(current.is("textarea")){
				current.val(text);
			}else{
				current.text(text);
			}
			return sum;
		}
	});
	$.extend({
		checkType:function(type , value){
			var flag = false;
			switch(type){
			case 'number' :
				if (!isNaN(value)){
					var reg = /^\d+$/;
					flag = reg.test(value);
				}
				break;
			default:
				flag = true;
				break;
			}
			return flag;
		}
	});
	function http(data,url,callback) {
		console.info(data);
		var result = {code:'0',msg:'提交失败!'};
		var xmlhttp;	
	    if (window.ActiveXObject) {
	      xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	    }else if (window.XMLHttpRequest) {
	      xmlhttp = new XMLHttpRequest();
	    }
	    if(xmlhttp){
    	  xmlhttp.onreadystatechange = function() {
  	        if (xmlhttp.readyState == 4) {
  	          if (xmlhttp.status == 200) {
  	        	  var result = $.parseJSON(xmlhttp.responseText);
  	        	  callback(result);
  	        	  return result;
  	          }else{
  	        	  return result;
  	          }
  	        }
  	      };
  	      xmlhttp.open("POST", url, true);
  	      xmlhttp.send(data);
      }else{
    	  callback(result);
    	  return result;
      }
	}
	function _getCharFromUtf8(str){
		var cstr = "";  
	    var nOffset = 0;  
	    if (str == "")  
	        return "";  
	    str = str.toLowerCase();  
	    nOffset = str.indexOf("%e");  
	    if (nOffset == -1)  
	        return str;  
	    while (nOffset != -1) {  
	        cstr += str.substr(0, nOffset);  
	        str = str.substr(nOffset, str.length - nOffset);  
	        if (str == "" || str.length < 9)  
	            return cstr;  
	        cstr += _utf8ToChar(str.substr(0, 9));  
	        str = str.substr(9, str.length - 9);  
	        nOffset = str.indexOf("%e");  
	    }  
	    return cstr + str;  
	}
	//将编码转换成字符  


	function _utf8ToChar(str) {
	    var iCode, iCode1, iCode2;  
	    iCode = parseInt("0x" + str.substr(1, 2));  
	    iCode1 = parseInt("0x" + str.substr(4, 2));  
	    iCode2 = parseInt("0x" + str.substr(7, 2));  
	    return String.fromCharCode(((iCode & 0x0F) << 12) | ((iCode1 & 0x3F) << 6) | (iCode2 & 0x3F));  
	}
})($,window);