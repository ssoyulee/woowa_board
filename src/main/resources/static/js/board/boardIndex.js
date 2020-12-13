
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
				    success: function(result){
						console.debug("resultCode => ", result);
						if ( result.resultCode == '00' ){
					    	boardApp.board = result.resultList[0];
						}else{
							alert(result.resultMessage);
						}		
				    },
				    error: function (request, status, error){        
						console.error("status => " + request.status + "error => " + request.responseText);
						alert("오류가 발생하였습니다. 관리자에게 문의해주세요~");        
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
				    success: function(result){
						console.debug("resultCode => ", result);
						if ( result.resultCode == '00' ){
							$.each(result.resultList, (index, post)=>{
								post.url = "/post/detail?boardId="+post.boardId+"&postId=" + post.postId
							});
					    	boardApp.postList = result.resultList;
							boardApp.totalPage = result.totalPage;
							boardApp.totalCount = result.totalCount;
							
						}else{
							boardApp.postList = [];
							alert(result.resultMessage);
						}	
				    },
				    error: function (request, status, error){        
						console.error("status => " + request.status + "error => " + request.responseText);
						alert("오류가 발생하였습니다. 관리자에게 문의해주세요~");        
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

