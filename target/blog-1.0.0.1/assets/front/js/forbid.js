/**
 * Created by fwh on 2017/9/30.
 */


$(document).ready(function(){
    $(document).bind("contextmenu",function(e){
        return false;
    });
});
function cantSee(){
    $(".hint").css("display","none");

}
document.onkeydown = function() {
    if (window.event && window.event.keyCode == 123) {
        var width= window.innerWidth;
        var height= window.innerHeight;
        var left=(width-200)/2;
        var top=(height-70)/2;
        $(".hint1").css("top",top+"px");
        $(".hint1").css("left",left+"px");
        $(".hint1").css("display","block");
        setTimeout(cantSee,3000);
        event.keyCode=0;
        event.returnValue=false;
    }
}

document.onmousedown=function(oEvent){
    if(window.event)
        oEvent=window.event;
    if(oEvent.button==2){

        var width= window.innerWidth;
    var height= window.innerHeight;
    var left=(width-200)/2;
    var top=(height-70)/2;
    $(".hint2").css("top",top+"px");
    $(".hint2").css("left",left+"px");
    $(".hint2").css("display","block");
    setTimeout(cantSee,5000);
    }
}
