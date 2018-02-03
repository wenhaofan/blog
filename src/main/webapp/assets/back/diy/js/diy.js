/**
 * Created by Lemon on 2018/1/18.
 */

var $clickLi;

function hideMenu(){
    $("aside").hide();
    
    $("main").css("width","100%");
    $("#show").show();
}
document.onkeydown=function()   {
    if (event.ctrlKey == true && event.keyCode == 83) {//Ctrl+S 
        
    	if(confirm("是否确认保存？")){
    	
    		
    		
    		var form=new FormData($("#dataForm")[0]);
    		
//    		var content=$("textarea").val();
//    		var menuId=$("#menuId").val();
//    		form.append("content",content);
//    		form.append("menuId",menuId);
    		
    		
    		
    		$.ajax({
    			type:"post",
    			url:"/admin/diy/html/doUpdate",
    			data:$("#dataForm").serialize(),
    			dataType:"json",
    			processData:false,
    			success:function(data){
    				if(data.state=="ok"){
    					alert("保存成功!");
    				}else{
    					alert("系统繁忙,请稍后再试!");
    				}
    			}
    		});
    	}
        
        return false;
    }
}
$(function(){
	
	$("body").on("click",".menu-directory", function() {
		
			
			var $currentObj=$(this);
			var isClick=$currentObj.attr("isClick");
			if(isClick=="true"){
				hideDirectory(this);
			}else if(isClick=="false"){
				showDirectory(this);
			}else{
				listDirectory(this);
			}
			
			if($clickLi!=undefined){
				$clickLi.removeClass("current");
			}
			
			$(this).addClass("current");
			$clickLi=$(this);
		});
	
	function hideDirectory(currentObj){
		var $currentObj=$(currentObj);
		$currentObj.next().hide();
		$currentObj.attr("isClick","false");
	}
	function showDirectory(currentObj){
		var $currentObj=$(currentObj);
		$currentObj.next().show();
		$currentObj.attr("isClick","true");
	}
	function listDirectory(currentObj){
		var $currentObj=$(currentObj);
		var menuId=$(currentObj).attr("menuId");
		$.ajax({
			type:"post",
			url:"/admin/diy/html/listDirectory/"+menuId,
			data:{'menuId':menuId},
			success:function(data){
				var menuTree=$("<li></li>");
				var menus=$("<ul></ul>")
				$.each(data.menus,function(index,obj){
					var menu=$("<li></li>")
					if(obj.type==0){
						menu.addClass("menu-file");
					}else{
						menu.addClass("menu-directory");
					}
					
					menu.attr("menuid",obj.currentId);
					menu.text(obj.name);
					menus.append(menu);
				});
				menuTree.append(menus);
				$currentObj.after(menuTree);
				$currentObj.attr("isClick","true");
			}
		})
	}
	
    $("#show").click(function(){
        $("aside").css("display","block");
        $("main").css("width","85%");
        $("#show").hide();
    });
    
    $("body").on("click",".menu-file",function(){
    	var currentId=$(this).attr("menuId");
    	var treename=$(this).attr("treename");
    
    	if($clickLi!=undefined){
			$clickLi.removeClass("current");
		}
		
		$(this).addClass("current");
		$clickLi=$(this);
    	$.ajax({
    		type:"post",
    		url:"/admin/diy/html/update/"+currentId,
    		success:function(data){
    			if(data.state=="fail"){
    				alert("系统繁忙,请稍后再试!");
    			}else{
//    				ue.execCommand( 'inserthtml',"");
//    				ue.execCommand( 'inserthtml', data.content);
//    				ue.execCommand( 'source');
//    				ue.setHeight(1800);
    				$("#textarea").val("");
	    			$("#textarea").val(data.html.content)
	    			$("#textarea").trigger("focus");
	    			$("#menuId").val(currentId);
	    			
    			}
    			
    		}
    	})
    });
})





$(function () {
    // 为每一个textarea绑定事件使其高度自适应
    $.each($("textarea"), function(i, n){
        autoTextarea($(n)[0]);
    });
})
/**
 * 文本框根据输入内容自适应高度
 * {HTMLElement}   输入框元素
 * {Number}        设置光标与输入框保持的距离(默认0)
 * {Number}        设置最大高度(可选)
 */
var autoTextarea = function (elem, extra, maxHeight) {
    extra = extra || 0;
    var isFirefox = !!document.getBoxObjectFor || 'mozInnerScreenX' in window,
    isOpera = !!window.opera && !!window.opera.toString().indexOf('Opera'),
        addEvent = function (type, callback) {
            elem.addEventListener ?
                elem.addEventListener(type, callback, false) :
                elem.attachEvent('on' + type, callback);
        },
        getStyle = elem.currentStyle ? 
        function (name) {
            var val = elem.currentStyle[name];
            if (name === 'height' && val.search(/px/i) !== 1) {
                var rect = elem.getBoundingClientRect();
                return rect.bottom - rect.top -
                       parseFloat(getStyle('paddingTop')) -
                       parseFloat(getStyle('paddingBottom')) + 'px';       
            };
            return val;
        } : function (name) {
            return getComputedStyle(elem, null)[name];
        },
    minHeight = parseFloat(getStyle('height'));
    elem.style.resize = 'both';//如果不希望使用者可以自由的伸展textarea的高宽可以设置其他值

    var change = function () {
        var scrollTop, height,
            padding = 0,
            style = elem.style;

        if (elem._length === elem.value.length) return;
        elem._length = elem.value.length;

        if (!isFirefox && !isOpera) {
            padding = parseInt(getStyle('paddingTop')) + parseInt(getStyle('paddingBottom'));
        };
        scrollTop = document.body.scrollTop || document.documentElement.scrollTop;

        elem.style.height = minHeight + 'px';
        if (elem.scrollHeight > minHeight) {
            if (maxHeight && elem.scrollHeight > maxHeight) {
                height = maxHeight - padding;
                style.overflowY = 'auto';
            } else {
                height = elem.scrollHeight - padding;
                style.overflowY = 'hidden';
            };
            style.height = height + extra + 'px';
            scrollTop += parseInt(style.height) - elem.currHeight;
            document.body.scrollTop = scrollTop;
            document.documentElement.scrollTop = scrollTop;
            elem.currHeight = parseInt(style.height);
        };
    };

    addEvent('propertychange', change);
    addEvent('input', change);
    addEvent('focus', change);
    change();
};

