let header_app;

$(function() {
	header_app = new Vue({
		el: '#header_app',
	  	data : {
			board_list: [],
		},
		methods : {
			select_board_list : () => {
				$.ajax({
				    url: "/board/api/list",
				    type: "GET",
				    dataType: "json",
				    success: function(data){
						if ( data.resultCode == '00' ){
							$.each(data.resultList, (index,data)=>{
								data.url = '/board/index?boardId=' + data.boardId;
							});
				    		header_app.board_list = data.resultList;
						}else{
							alert(data.resultMessage);
						}
				    },
				    
				    error: function (request, status, error){        
						var msg = "ERROR : " + request.status + "<br>"
						msg +=  + "내용 : " + request.responseText + "<br>" + error;
						alert(msg);              
				    }
				});
			}
		}
	});
	
	// 게시판 목록을 가져온다.
	header_app.select_board_list();	
});


const open_popup_login = () =>{
	 window.open("/user/index", "a", "width=400, height=300, left=100, top=50");
}