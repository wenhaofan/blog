$(function(){
	
	//当可视高度大于正文高度时，添加样式使尾部固定在网页底部，否则移出
	function footerPosition() {
	  $("#footer").removeClass("fixed-bottom");
	  var contentHeight = document.body.scrollHeight;//网页正文全文高度
	  var winHeight = window.innerHeight;//可视窗口高度，不包括浏览器顶部工具栏

	  if (contentHeight <= winHeight) {
	      //当网页正文高度小于可视窗口高度时，为footer添加类fixed-bottom
	      $("#footer").addClass("fixed-bottom");
	    
	  } else {
	      $("#footer").removeClass("fixed-bottom");

	  }
	}

	footerPosition();
	$(window).resize(footerPosition);

});