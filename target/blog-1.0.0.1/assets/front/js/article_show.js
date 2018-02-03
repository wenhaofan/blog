	SyntaxHighlighter.all();
	var timer;
	var isAdd=false;
	
   var hiddenProperty = 'hidden' in document ? 'hidden' :    
        'webkitHidden' in document ? 'webkitHidden' :    
        'mozHidden' in document ? 'mozHidden' :    
        null;
   
    var visibilityChangeEvent = hiddenProperty.replace(/hidden/i, 'visibilitychange');
    timerAddReadNum();
    var onVisibilityChange = function(){
        if (!document[hiddenProperty]) { 
        	timerAddReadNum();
            document.title='被发现啦(*´∇｀*)';
        }else{
            document.title='藏好啦(つд⊂)  ';
            clearTimer();
        }
    }
    document.addEventListener(visibilityChangeEvent, onVisibilityChange);
    
    function timerAddReadNum(){
    	  timer=window.setTimeout(function(){
    		  addReadNum();
    	  },1000); 
    }
    
    function clearTimer(){
    	window.clearTimeout(timer); 
    }
    
    function addReadNum(){
    	if(isAdd){
    		return;
    	}
    	$.ajax({
    		type:"post",
    		url:"/article/addReadNum/#(article.pkId)",
    		success:function(result){
    			if(result.state=="ok"){
    				var num=$("#readNum").text();
    				$("#readNum").text(parseInt(num)+1);
    				isAdd=true;
    			}
    		}
    	})
    }