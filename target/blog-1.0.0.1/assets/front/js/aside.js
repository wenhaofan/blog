$(function(){
	  function asidePosition() {
          var height = $("#top-padding").height();
          var windowWidth = window.innerWidth;
          var fontSize = $("body").css('font-size');
          var fixedHeight = parseInt(height) + parseInt(fontSize) * 0.5;

          if (windowWidth >= 768) {
              $("#fixed-aside").css('top', fixedHeight);
          } else {
              $("#fixed-aside").removeClass("fixed-aside");
              $("#fixed-aside").css("position", "static");
          }

      }

      asidePosition();
      $(window).resize(asidePosition);
});
