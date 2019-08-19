/*
 * 表单 被 .form-validator div 包裹   图片div指定imgfor属性指向表单的name
 * */

(function($) {
	$.fn.extend({ 
		validator : function(){
			var check_flag = true;
			var obj = this;
			console.log(obj);
			obj.find('input,select,textarea').each(function(){
				console.log($(this));
				$(this).bind('focus',function(e){
					var name = $(this).attr('name');
					var msg = $(this).attr('info');
					var imgDiv = $('div[imgfor="' + name + '"]');
					//单独匹配没有匹配成功 name,age,class
					if(imgDiv.length == 0){
						imgDiv = $('div[imgfor*="' + name + ',"]');
						if(imgDiv.length == 0){
							imgDiv = $('div[imgfor$="' + name + '"]');
						}
					}
					createImg(imgDiv,'info', msg);
				});
			});
			
			//form中的表单 绑定change单个验证
			obj.find('input,select,textarea').each(function(e) {
				$(this).bind('blur',function(){
					submitValidata($(this));
				});
			});
			
			//提交前的回调函数
			obj.submit(function(e){
				var flags = new Array();
				obj.find('input,select,textarea').each(function(e) {
					var check_flag = submitValidata($(this));
					flags.push(check_flag);
				});
				for(var i = 0; i < flags.length; i++){
					if(!flags[i]){
						return false;
					}
				}
				return true;
			});
		},
		//验证
		validate : function(){
			var check_flag = true;
			var obj = this;
			obj.find('input,select,textarea').each(function(e) {
				check_flag = submitValidata($(this));
				if(!check_flag){
					return check_flag;
				}
			});
			return check_flag;
		},
		inputValidate : function(){
			var check_flag = true;
			var input = this;
			check_flag = submitValidata(input);
			return check_flag; 
		},
		validateResult :function(){
			var result = true;
			var obj = this;
			obj.find('input,select,textarea').each(function(e) {
				result = _validateElement($(this));
				if(result.code != '1'){
					return false;
				}
			});
			return result;
		}
	});
	
	
	/* 表单提交验证 */
	var submitValidata = function(formElement){
		var result = _validateElement(formElement)
		var check_flag = result.code == '1';
		return check_flag;
	}
	/*验证元素返回结果对象*/
	var _validateElement = function (formElement){
		
		var result = {code:'1',msg:''};
		
		var name = formElement.attr('name');
		var msg = formElement.attr('msg');
		var info=formElement.attr('info');
		
		if (formElement.attr("check")) {
			var formCheckVal = formElement.attr("check");
			//如果需要多个验证器
			if(formCheckVal.indexOf(',') != -1){
				var validateArray = formCheckVal.split(',');
				var resultArray = new Array();
				for(var i = 0; i < validateArray.length; i++){
					var resultObj = validate(formElement.val(), validateArray[i]);
					resultArray.push(resultObj);
				}
				//开始验证结果
				for(var i = 0; i < resultArray.length; i++){
					//验证不成功
					if (resultArray[i].code == '0') {
						if(msg.indexOf('|') != -1){
							msg = resultObj.msg || msg.split('|')[i] || msg || info;
						}
						
						result.code = '0',
						result.msg = msg;
						break;
					}
				}
				
			//如果只有一个验证器
			}else{
				var resultObj = validate(formElement.val(), formCheckVal);
				result.code = resultObj.code,
				result.msg = resultObj.msg || msg || info;;
			}
		}
		_changeClassByResult(formElement , result);
		return result;
		
	}
	/*修改验证结果展示区样式*/
	var _changeClassByResult = function(formElement , reuslt){
		var name = formElement.attr("name");
		var imgDiv = $('div[imgfor*="' + name + '"]');
		//单独匹配没有匹配成功 name,age,class
		if(imgDiv.length == 0){
			imgDiv = $('div[imgfor*="' + name + ',"]');
			if(imgDiv.length == 0){
				imgDiv = $('div[imgfor$="' + name + '"]');
			}
		}
		var parentDiv = formElement.closest('.form-validator');
		//验证不成功
		if (reuslt.code == '0') {
			//去除验证成功的样式
			parentDiv.removeClass('has-success');
			//添加验证失败的样式
			parentDiv.addClass('has-error');
			createImg(imgDiv,'error',reuslt.msg);
		}else{
			parentDiv.addClass('has-success');
			parentDiv.removeClass('has-error');
			createImg(imgDiv,'success');
		}
	}
	/* 验证数据类型 */
	var validate = function(data, datatype) {
		var result = {};
		if (datatype.indexOf("|")) {
			tmp = datatype.split("|");
			datatype = tmp[0];
			data2 = tmp[1];
		}
		switch (datatype) {
		case "require":
			if(!data2){ 
				//默认是空字符窜
				data2="";
			}
			if (data == data2) {
				result.code = "0";
			} else {
				result.code = "1";
			}
			return result;
			break;
		case "max_size":
			if(data2){
				if(data.length<=data2){
					result.code="1";
				}else{
					result.code="0";
				}
				return result;
			}
			break;
		case "min_size":
			if(data2){
				if(data.length>=data2){
					result.code="1";
				}else{
					result.code="0";
				}
				return result;
			}
			break;
		case "phone":
			var reg = /^\d{11}$/;
			if (reg.test(data)){
				result.code = "1";
			} else {
				result.code = "0";
			}
			return result;
			break;
		case "email":
			if(!data){
				result.code = '1';
				return result;
			}
			var reg = /^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$/g;
			if(reg.test(data)){
				result.code = "1";
			}else{
				result.code = "0";
			}
			return result;
			break;
		case "number":
			if(data2&&!isNaN(data2)){
				data2=parseInt(data2);
			}else{
				data2=3;
			}
			var reg ;
			//如果小数位为0则为自然数,不能有小数位.否则判断小数位数是否符合
			if(data2==0){
				reg = new RegExp("^[0-9]+$");
			}else{
				reg = new RegExp("^[0-9]+(\.[0-9]{1,"+data2+"}){0,1}$");
			}
			
			if(!data){
				result.code='1';
				return result;
			}
			if(reg.test(data)){
				result.code = "1";
			}else{
				result.code = "0";
			}
			return result;
			break;
		case "html":
			var reg = /<...>/;
			if(reg.test(data)){
				result.code = "1";
			}else{
				result.code = "0";
			}
			return result;
			break;
		case "eqt":
			data2 = $("#" + data2).val();
			if(data >= data2){
				result.code = "1";
			}else{
				result.code = "0";
			}
			return result;
			break;
		case "eq":
			data2 = $("#" + data2).val();
			if(data == data2){
				result.code = "1";
			}else{
				result.code = "0";
			}
			return result
			break;
		case "idcard":
			data2 = "";
			if(data != data2){
				result.code = "1";
			}else{
				result.code = "0";
			}
			return result
			break;
		case "remote":
			var remoteurl = data2;
			var flag = {};
			$.ajax({
				url : remoteurl,
				dataType : 'json',
				data : {
					'parameter' : data
				},
				type : 'post',
				async : false,
				success : function(r){
					var result = r;
					flag = result;
				},
				error : function(e){
					alert('请求失败！');
				}
			});
			return flag;
			break;
		case "checkCode":
			var remoteurl = data2;
			var flag = {};
			$.ajax({
				url : remoteurl,
				dataType : 'json',
				data : {
					'parameter' : data,
					'username' : $('#username').val()
				},
				type : 'post',
				async : false,
				success : function(r){
					var result = r;
					flag = result;
				},
				error : function(e){
					alert('请求失败！');
				}
			});
			return flag;
			break;
		case "date":
			if(data){
				var reg = /^\d{4}(\-|\/|\.)[0,1]\d\1\d{1,2}$/;
				if(reg.test(data)){
					result.code = "1";
				}else{
					result.msg = "日期格式不正确！";
					result.code = "0";
				}
			}else{
				result.code = "1";
			}
			return result;
			break;
		case "password":
			if(data){
				var reg = /^(?=.*?[a-zA-Z])(?=.*?[0-6])[A-Za-z0-9]{6,20}$/;
				if(reg.test(data)){
					result.code = "1";
				}else{
					result.code = "0";
				}
			}else{
				result.code = "0";
			}
			return result;
			break;
		case "chinese":
			if(data){
				var reg = /^[\u4e00-\u9fa5]{0,}$/;
				if(reg.test(data)){
					result.code = "1";
				}else{
					result.code = "0";
				}
			}else{
				result.code = "1";
			}
			return result;
		case "english":
			if(data){
				var reg = /^[a-zA-Z ]{0,}$/;
				if(reg.test(data)){
					result.code = "1";
				}else{
					result.code = "0";
				}
			}else{
				result.code = "1";
			}
			return result;
		case "officeTelephone":
			if(!data){
				result.code = "1";
				return result;
			}
			if(data){
				var reg = /0\d{2,3}-\d{5,9}|0\d{2,3}-\d{5,9}/;
				if(reg.test(data)){
					result.code = "1";
				}else{
					result.code = "0";
				}
			}else{
				result.code = "0";
			}
			return result;
		case "englishAndNumber":
			if(data){
				var reg = /^[a-zA-Z0-9 ]{0,}$/;
				if(reg.test(data)){
					result.code = "1";
				}else{
					result.code = "0";
				}
			}else{
				result.code = "1";
			}
			return result;
		}
	}
	
 	var createImg = function(parentDiv,flag,msg){
 		msg = msg||"";
 		switch (flag) {
		case "success":
			//添加成功的图标
			if(parentDiv.find('.img-validator').length == 0){
				var span = $('<span class="span-validator"></span>');
				var img = $('<i alt="" class="img-validator img-validator-yes"></i>');
				var p = $('<spn class="msg-validator"></span>');
				span.append(img);
				span.append(p);
				parentDiv.prepend(span);
			}else{
				parentDiv.find('.img-validator').removeClass('img-validator-no');
				parentDiv.find('.img-validator').removeClass('img-validator-info');
				parentDiv.find('.img-validator').addClass('img-validator-yes');
				parentDiv.find('.msg-validator').empty();
				
			}
			break;
		case "error":
			if(parentDiv.find('.img-validator').length == 0){
				var span = $('<span class="span-validator"></span>');
				var img = $('<i alt="" class="img-validator img-validator-no"></i>');
				var p = $('<spn class="msg-validator"></span>');
				p.append('  ' + msg);
				span.append(img);
				span.append(p);
				parentDiv.prepend(span);
			}else{
				parentDiv.find('.img-validator').removeClass('img-validator-yes');
				parentDiv.find('.img-validator').removeClass('img-validator-info');
				parentDiv.find('.img-validator').addClass('img-validator-no');
				parentDiv.find('.msg-validator').empty().append('  ' + msg);
			}
			break;
		case "info":
			if(parentDiv.find('.img-validator').length == 0){
				var span = $('<span class="span-validator"></span>');
				var img = $('<i alt="" class="img-validator img-validator-info"></i>');
				span.append(img);
				var p = $('<spn class="msg-validator"></span>');
				p.append('  ' + msg);
				span.append(p);
				parentDiv.prepend(span);
			}else{
				parentDiv.find('.img-validator').removeClass('img-validator-yes');
				parentDiv.find('.img-validator').removeClass('img-validator-no');
				parentDiv.find('.img-validator').addClass('img-validator-info');
				parentDiv.find('.msg-validator').empty().append('  ' + msg);
			}
			break;
		}
	} 
	
}(window.jQuery));
