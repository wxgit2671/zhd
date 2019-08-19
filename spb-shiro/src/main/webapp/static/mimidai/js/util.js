(function(window,$){
	 Date.prototype.format = function (fmt) { //author: meizz 
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "H+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	}
	
	Date.prototype.parse = function(str) {
		var reg = /(.+)-(.+)-(.+)( (.+):(.+):(.+))*/;
		var result = reg.exec(str);
		
		var year,month,day,hour,minute,second;
		if(result[1]){
			year = parseInt(result[1], 10);
		}
		if(result[2]){
			month = parseInt(result[2], 10);
		}
		if(result[3]){
			day = parseInt(result[3], 10);
		}
		if(result[5]){
			hour = parseInt(result[5], 10);
		}
		if(result[6]){
			minute = parseInt(result[6], 10);
		}
		if(result[7]){
			second = parseInt(result[7], 10);
		}
		
	 	var date = new Date(year || 0, month || 0, day || 0, hour||0, minute||0, second||0);
	 	return date;
	}
	Date.prototype.get = function(type) {
		var value = 0;
		switch(type){
			case "y":
				value = this.getFullYear();	
				break;
			case "M":
				value = this.getMonth() + 1;
				break;
			case "d":
				value = this.getDate();
				break;
			case "h":
				value = this.getHour();
				break;
			case "m":
				value = this.getMinutes();
				break;
			case "s":
				value = this.getSeconds();
				break;
			default:
				break;
		}
		return value;
	}
	/*返回相差的值，date是时间，unit是单位:y返回年，M返回月，d返回天，h返回小时，m返回分钟，s返回秒*/
	Date.prototype.sub = function(date,unit){
		var value = 0;
		
		var subTotal=Math.abs(this.getTime()-date.getTime());  //时间差的毫秒数
		switch(unit){
			case "y":
				value = this.getFullYear() - date.getFullYear();
				value = Math.abs(value);
				break;
			case "M":
				years = this.getFullYear() - date.getFullYear();
				value = Math.abs(years) * 12 + Math.abs(this.getMonth() - date.getMonth()); 
				break;
			case "d":
				value = Math.floor(subTotal/(24*3600*1000));
				break;
			case "h":
				value = Math.floor(subTotal/(3600*1000));
				value = this.getHour();
				break;
			case "m":
				value = Math.floor(subTotal/(60*1000));
				value = this.getMinutes();
				break;
			case "s":
				value = Math.floor(subTotal/(1000));
				value = this.getSeconds();
				break;
			default:
				value = subTotal;
				break;
		}
		return value;
	}
})(window,$);