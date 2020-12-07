let headerApp;

$(function() {
	headerApp = new Vue({
		el: '#headerApp',
	  	data : {
			boardList: [],
			isLogin : false,
			loginId : null,
			role : null
		},
		methods : {
			selectBoardList : () => {
				let param = {'delYn':'N'};
				$.ajax({
				    url: "/board/api/list",
				    type: "GET",
					data : param,
				    dataType: "json",
				    success: function(result){
						if ( result.resultCode == '00' ){
							$.each(result.resultList, (index,obj)=>{
								obj.url = '/board/index?boardId=' + obj.boardId;
							});
				    		headerApp.boardList = result.resultList;
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
	headerApp.selectBoardList();	
});

const setLogin = () => {
		
	let userId = getCookieValue("userId");
	headerApp.loginId = userId;
	if (userId){
		headerApp.isLogin = true;
	}else{
		headerApp.isLogin = false;
	}
	headerApp.role = getCookieValue("role");
	
}