let board_app;

$(function() {
	board_app = new Vue({
	  	el: '#board_app',
	  	data : {
			boardId : '',
			board: {},
			post_list : []
		},
		methods : {
			select_board : ()=>{
				$.ajax({
				    url: "/board/api/get/" + p_boardId,
				    type: "GET",
				    dataType: "json",
				    success: function(data){
						if ( data.resultCode == '00' ){
					    	board_app.board = data.resultList[0];
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
			},
			select_post_list : ()=>{
				$.ajax({
				    url: "/post/api/select/" + p_boardId,
				    type: "GET",
				    dataType: "json",
				    success: function(data){
						if ( data.resultCode == '00' ){
							$.each(data.resultList, (index, data)=>{
								data.url = "/post/detail?boardId="+data.boardId+"&postId=" + data.postId
							})
					    	board_app.post_list = data.resultList;
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
			},
			select_post : ()=>{
				alert('click');
			},
			
			go_insert_page : () => {
				location.href = "/post/form?boardId=" + p_boardId;
			}
		}
		
	});
	
	board_app.select_board();
	board_app.select_post_list();
	
});

