$(function(){
	var pageGet = "getHeadPortrait";
	var pageExit = "exit";
	var $liLogin = $('<li><a href="#">个人中心</a></li>'
				+'<li><a href="#">子链接二</a></li>'
				+'<li><a href="#">子链接三</a></li>'
				+'<li class="divider"></li>'
				+'<li><a  href="/">注销</a></li>');
	var $liExit =  $('<li ><a href="/">登录</a></li>');
	$.get(
			pageGet,
  			 function(result){
				if(result=="nullHeadPortrait"){
					
				}
				else if(result=="null"){
  						$("#div-navtop-headPortrait div").removeAttr("background-image");
  						$("#div-navtop-headPortrait ul").empty();
  	  					$("#div-navtop-headPortrait ul").append($liExit);
  	  					}
				else{
					$("#div-navtop-headPortrait div").css("background-image", "url(" + result + ")");
  					$("#div-navtop-headPortrait ul").empty();
  	  				$("#div-navtop-headPortrait ul").append($liLogin);
  	  				$("#div-navtop-headPortrait a[href='/']").click(function(){
  	  					$.get(pageExit);
  	  					});
  	  				}
  				}
  			);
	
	
});
