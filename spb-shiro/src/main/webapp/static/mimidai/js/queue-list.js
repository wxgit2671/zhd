$(function(){
    $("input[name='term']").blur(function(){
        var target = $(this);
        var hasError = false;
        var data = target.val();
        if(!data){
            target.removeClass("error-border");
            return;
        }
        if(data && !isNaN(data)){
            data=parseInt(data);
            if(data<=0 || data>24){
                hasError= true;
            }
        }else{
            hasError = true;
        }
        if(hasError){
            if(!target.hasClass("error-border")){
                target.addClass("error-border");
            }
        }else{
            target.removeClass("error-border");
        }
    })
})