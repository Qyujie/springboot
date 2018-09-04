$(function(){
	//name
	$("#userinformation-name").val($("#userinformation-name").attr("value"));
	
	//birthday
	//year
	var selectYearValue = $("#select-year").attr("value");
	
	//第0个value为“年”，第1个value为2018，2->2017，规律：第i个加上它的value等于2019
	var birthdayYear = parseInt($("#select-year option:eq(1)").attr("value"))+1-selectYearValue;
	$("#select-year option:eq('"+birthdayYear+"')").attr("selected","selected");
	
	//month
	var birthdayMonth = $("#select-month").attr("value");
	if(birthdayMonth==""){
		$("#select-month option:eq(0)").attr("selected","selected");
	}
	else{
		$("#select-month option:eq('"+birthdayMonth+"')").attr("selected","selected");
	}
	
	//day
	var birthdayDay = $("#select-day").attr("value");
	if(birthdayDay==""){
		$("#select-day option:eq(0)").attr("selected","selected");
	}
	else{
		$("#select-day option:eq('"+birthdayDay+"')").attr("selected","selected");
	}
	
	//sex
	var sex = $("#userinformation-sex").attr("value");
	if(sex=="男"){
		$("#userinformation-sex option:eq(1)").attr("selected","selected");
	}
	else if(sex=="女"){
		$("#userinformation-sex option:eq(2)").attr("selected","selected");
	}
	else{
		$("#userinformation-sex option:eq(0)").attr("selected","selected");
	}
	
	//phone
	$("#userinformation-phone").val($("#userinformation-phone").attr("value"));
	
	//prefecture
	var prefecture = $("#userinformation-prefecture").attr("value");
	if(prefecture=="中国"){
		$("#userinformation-prefecture option:eq(1)").attr("selected","selected");
	}
	else if(prefecture=="其它"){
		$("#userinformation-prefecture option:eq(2)").attr("selected","selected");
	}
	else{
		$("#userinformation-prefecture option:eq(0)").attr("selected","selected");
	}
	
	//introduce
	var introduce = $('#userinformation-introduce').attr('value');
	$("#userinformation-introduce").text(introduce);
	
	//headPortrait
	$('#div-headPortrait').css("background-image", "url(" + $('#div-headPortrait').attr('value') + ")");
	
	//address
	$("#userinformation-address").val($("#userinformation-address").attr("value"));
	
	//realName
	$("#userinformation-real_name").val($("#userinformation-real_name").attr("value"));
	
	//idCard
	$("#userinformation-id_card").val($("#userinformation-id_card").attr("value"));
});
