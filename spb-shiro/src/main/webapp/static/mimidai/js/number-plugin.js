(function(window){
	//封装一些方法##############################3
	/**
	  ** 加法函数，用来得到精确的加法结果
	  ** 说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
	  ** 调用：accAdd(arg1,arg2)
	  ** 返回值：arg1加上arg2的精确结果
	  **/
	function accAdd(arg1, arg2) {
	    var r1, r2, m, c;
	    try {
	         r1 = arg1.toString().split(".")[1].length;
	     }catch (e) {
	         r1 = 0;
	     }
	     try {
	         r2 = arg2.toString().split(".")[1].length;
	     } catch (e) {
	         r2 = 0;
	     }
	     c = Math.abs(r1 - r2);
	     m = Math.pow(10, Math.max(r1, r2));
	     if (c > 0) {
	         var cm = Math.pow(10, c);
	         if (r1 > r2) {
	             arg1 = Number(arg1.toString().replace(".", ""));
	             arg2 = Number(arg2.toString().replace(".", "")) * cm;
	         } else {
	             arg1 = Number(arg1.toString().replace(".", "")) * cm;
	             arg2 = Number(arg2.toString().replace(".", ""));
	         }
	     } else {
	         arg1 = Number(arg1.toString().replace(".", ""));
	         arg2 = Number(arg2.toString().replace(".", ""));
	     }
	     var r = parseFloat((arg1 + arg2) / m);
	     return isNaN(r)?0:r;;
	}
	 
	//给Number类型增加一个add方法，调用起来更加方便。
	Number.prototype.add = function (arg) {
		 return accAdd(arg, this);
	};

	/**
	 ** 减法函数，用来得到精确的减法结果
	 ** 说明：javascript的减法结果会有误差，在两个浮点数相减的时候会比较明显。这个函数返回较为精确的减法结果。
	 ** 调用：accSub(arg1,arg2)
	 ** 返回值：arg1加上arg2的精确结果
	 **/
	function accSub(arg1, arg2) {
	    var r1, r2, m, n;
	    try {
	        r1 = arg1.toString().split(".")[1].length;
	    }
	    catch (e) {
	        r1 = 0;
	    }
	    try {
	        r2 = arg2.toString().split(".")[1].length;
	    }
	    catch (e) {
	        r2 = 0;
	    } 
	    m = Math.pow(10, Math.max(r1, r2)); //last modify by deeka //动态控制精度长度
	    n = (r1 >= r2) ? r1 : r2;
	    
	    var r = parseFloat(((arg1 * m - arg2 * m) / m).toFixed(n));
	    return isNaN(r)?0:r;;
	    
	}

	// 给Number类型增加一个mul方法，调用起来更加方便。
	Number.prototype.sub = function (arg) {
	    return accSub(this, arg);
	};

	/**
	 ** 乘法函数，用来得到精确的乘法结果
	 ** 说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。
	 ** 调用：accMul(arg1,arg2)
	 ** 返回值：arg1乘以 arg2的精确结果
	 **/
	function accMul(arg1, arg2) {
	    var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
	    try {
	        m += s1.split(".")[1].length;
	    }
	    catch (e) {
	    }
	    try {
	        m += s2.split(".")[1].length;
	    }
	    catch (e) {
	    }
	    return parseFloat(Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m));
	}

	// 给Number类型增加一个mul方法，调用起来更加方便。
	Number.prototype.mul = function (arg) {
	    return accMul(arg, this);
	};
	/** 
	 ** 除法函数，用来得到精确的除法结果
	 ** 说明：javascript的除法结果会有误差，在两个浮点数相除的时候会比较明显。这个函数返回较为精确的除法结果。
	 ** 调用：accDiv(arg1,arg2)
	 ** 返回值：arg1除以arg2的精确结果
	 **/
	function accDiv(arg1, arg2) {
	    var t1 = 0, t2 = 0, r1, r2;
	    try {
	        t1 = arg1.toString().split(".")[1].length;
	    }
	    catch (e) {
	    }
	    try {
	        t2 = arg2.toString().split(".")[1].length;
	    }
	    catch (e) {
	    }
	    with (Math) {
	        r1 = Number(arg1.toString().replace(".", ""));
	        r2 = Number(arg2.toString().replace(".", ""));
	        return parseFloat((r1 / r2) * pow(10, t2 - t1));
	    }
	}

	//给Number类型增加一个div方法，调用起来更加方便。
	Number.prototype.div = function (arg) {
	    return accDiv(this, arg);
	};
	//给Number类型增加一个div方法，调用起来更加方便。
	Number.prototype.format = function (arg) {
	    return _format(this, arg);
	};
	//格式化数字
	function _format(s,n,flag){//s是要格式化的数字n是保留小数位数
	    n = n > 0 && n <= 20 ? n : 2;   
	    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	    var l = s.split(".")[0].split("").reverse(),   
	    r = s.split(".")[1];   
	    t = ""; 
	    var i;
	    for(i = 0; i < l.length; i ++ )   
	    {   
	        t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
	    }
	    var result = t.split("").reverse().join("") + "." + r;
	    return result;   
	}
	function computeFee(day,percent,fee){
		
	}
})(window);