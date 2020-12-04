const enter = () =>{
	if(event.keyCode==13){
		login();
	}
}
const login = () =>{
	
	var user = {
		"userId" : $("#userId").val(),
		"password" : $("#password").val()
	}

    $.ajax({
        cache : false,
        url : "/user/login", // 요기에
        type : "POST", 
		dataType : "json",
		contentType: "application/json",
        data : JSON.stringify(user), 
        success : function(data) {
            if ( data.resultCode == '00' ) {
				alert('로그인 성공');
				$(opener.location).attr("href", "javascript:setLogin();");
				window.close();
			} else {
				alert(data.resultMsg);
			}
        }, // success 

        error : function(xhr, status) {
            alert(xhr + " : " + status);
        }
    }); // $.ajax */
}