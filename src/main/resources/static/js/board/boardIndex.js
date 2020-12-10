let boardApp;

$(function() {
	boardApp = new Vue({
	  	el: '#boardApp',
	  	data : {
			boardId : '',
			board: {},
			postList : [],
			loginId : headerApp.loginId,
			page : 1,
			pageCount : 5,
			totalPage : 0,
			totalCount : 0
		},
		methods : {
			selectBoard : ()=>{
				$.ajax({
				    url: "/board/api/get/" + reqBoardId,
				    type: "GET",
				    dataType: "json",
				    success: function(data){
						if ( data.resultCode == '00' ){
					    	boardApp.board = data.resultList[0];
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
			prevPage : ()=>{
				if ( boardApp.page - 1 > 0 ){
					boardApp.page = boardApp.page -1;
					boardApp.selectPostList();
				}
			},
			nextPage : ()=>{
				if ( boardApp.page + 1 <= boardApp.totalPage ){
					boardApp.page = boardApp.page +1;
					boardApp.selectPostList();
				}				
			},
			selectPostList : ()=>{
				
				let param = {
					'page' : boardApp.page,
					'pageCount' : boardApp.pageCount,
				}
				
				$.ajax({
				    url: "/post/api/page/" + reqBoardId,
				    type: "GET",
					data : param,
				    dataType: "json",
				    success: function(data){
						if ( data.resultCode == '00' ){
							$.each(data.resultList, (index, data)=>{
								data.url = "/post/detail?boardId="+data.boardId+"&postId=" + data.postId
							})
					    	boardApp.postList = data.resultList;
							boardApp.totalPage = data.totalPage;
							boardApp.totalCount = data.totalCount;
							
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
			goInsertPage : () => {
				location.href = "/post/form?boardId=" + reqBoardId;
			}
		}
		
	});
	
	boardApp.selectBoard();
	boardApp.selectPostList();

	$('#headerApp').on('DOMSubtreeModified', '#heardLogin', function(){
		boardApp.loginId = headerApp.loginId;
	});
		
});

