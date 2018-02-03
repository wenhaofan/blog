/**
 * Created by Lemon on 2017/10/28.
 */

$(function(){
    function iframeWidth(){
        var width =$(window).width()-180;
        $("#right").css("width",width);
        var height=$(window).height()-43;
        $("#right").css("height",height);

    }

    iframeWidth();
    $(window).resize(iframeWidth);
    
    $(".li-hover").click(function(){
    	hideLi($(this));
    	$(this).addClass("li-current");
    })
    
    
})

function hideLi(current){
	var tempCurrent=current;
	var tagName;
	var $li;
	while(true){
		$li=tempCurrent.prev();
		tempCurrent=$li;
		if(tempCurrent.length==0){
			break;
		}
		tagName=$li[0].tagName;
		if(tagName="LI"){
			$li.removeClass("li-current");
		}else{
			break;
		}
	}
	tempCurrent=current;
	while(true){
		$li=tempCurrent.next();
		tempCurrent=$li;
		if(tempCurrent.length==0){
			break;
		}
		tagName=$li[0].tagName;
		if(tagName="LI"){
			$li.removeClass("li-current");
		}else{
			break;
		}
	}
}