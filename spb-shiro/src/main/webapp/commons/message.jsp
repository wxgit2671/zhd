<%@page language="java" contentType="text/html;charset=UTF-8"%>
<!-- 使用此信息提示框 需要有message  和   code   -->
<c:if test="${code == '1'}">
	<div id="custom_message_alert" class="alert alert-success alert-dismissible fade in message" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		${msg }
	</div>
</c:if>
<c:if test="${code == '0'}">
	<div id="custom_message_alert" class="alert alert-danger alert-dismissible fade in message" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		${msg }
	</div>
</c:if>
<div id="custom_message_success" class="alert alert-success alert-dismissible fade in message" role="alert" hidden="true">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
</div>
<div id="custom_message_failed" class="alert alert-danger alert-dismissible fade in message" role="alert" hidden="true">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
</div>

<script type="text/javascript">
<c:if test="${!empty code}">
	(function(){
		setTimeout(function(){
			$('#custom_message_alert').alert('close');
		}, 5000)
	})();
</c:if>
	function alertMessage(code,msg) {
		if (code == '0') {
            $('#custom_message_success').removeAttr("hidden").text(msg);
        } else {
            $('#custom_message_failed').removeAttr("hidden").text(msg);
		}
        setTimeout(function(){
            $("div[id^='custom_message_']").attr("hidden", true);
        }, 5000);
    }
</script>
