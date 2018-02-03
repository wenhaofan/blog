//设置填充置顶栏的div的高度,并在窗口改变时重新计算
function topPadding() {
    var height = $("#top").height();
    $("#top-padding").css("height", height + "px");
    console.log("height:"+height);
}
topPadding();
$(window).resize(topPadding);