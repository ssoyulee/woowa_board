let app;

$(function() {
	app = new Vue({
	  	el: '#app',
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
					    	app.board = data.resultList[0];
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
				    url: "/post/api/get/" + p_boardId,
				    type: "GET",
				    dataType: "json",
				    success: function(data){
						if ( data.resultCode == '00' ){
							$.each(data.resultList, (index, data)=>{
								data.url = "/post/detail?postId=" + data.postId
							})
					    	app.post_list = data.resultList;
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
			}
		}
		
	});
	
	app.select_board();
	app.select_post_list();
	
});

