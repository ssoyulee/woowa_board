let header_app;

$(function() {
	header_app = new Vue({
		el: '#header_app',
	  	data : {
			board_list: [],
			is_login : false,
			login_id : null 
		},
		methods : {
			select_board_list : () => {
				$.ajax({
				    url: "/board/api/list",
				    type: "GET",
				    dataType: "json",
				    success: function(result){
						if ( result.resultCode == '00' ){
							$.each(result.resultList, (index,obj)=>{
								obj.url = '/board/index?boardId=' + obj.boardId;
							});
				    		header_app.board_list = result.resultList;
						}else{
							alert(result.resultMessage);
						}
				    },
				    
				    error: function (request, status, error){        
						var msg = "ERROR : " + request.status + "<br>"
						msg +=  + "내용 : " + request.responseText + "<br>" + error;
						alert(msg);              
				    }
				});
			},
			
			set_login : (is_login) => {
				user_info.is_login = is_login;
				if (is_login){
					user_info.text_login = '로그인';	
				}else{
					user_info.text_login = '로그아웃';
				}
			},
			
			login : () =>{
				window.open("/user/index", "a", "width=500, height=400, left=100, top=50");
			},
			
			logout : () =>{
			
			    $.ajax({
			        cache : false,
			        url : "/user/logout", // 요기에
			        type : "GET", 
			        success : function(data) {
			            if ( data.resultCode == '00' ) {
							alert('로그아웃 성공');
							setLogin();
						}else{
							alert(data.resultMsg);
						}
			        }, // success
			        error : function(xhr, status) {
			            alert(xhr + " : " + status);
			        }
			    }); // $.ajax */
			}			
			 
		}
	});

	setLogin();
	// 게시판 목록을 가져온다.
	header_app.select_board_list();	
});

const setLogin = () => {
		
	let cookie = getCookieValue("userId");
	if (cookie){
		header_app.login_id = cookie;
		header_app.is_login = true;
	}else{
		header_app.is_login = false;
	}
	
}
const getCookieValue = (key) => {
  let cookieKey = key + "="; 
  let result = "";
  const cookieArr = document.cookie.split(";");
  
  for(let i = 0; i < cookieArr.length; i++) {
    if(cookieArr[i][0] === " ") {
      cookieArr[i] = cookieArr[i].substring(1);
    }
    
    if(cookieArr[i].indexOf(cookieKey) === 0) {
      result = cookieArr[i].slice(cookieKey.length, cookieArr[i].length);
      return result;
    }
  }
  return result;
}

