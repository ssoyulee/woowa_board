// let manageBoardApp;

$(function() {
	let managePostApp = new Vue({
	  	el: '#managePostApp',
	  	data : {
			postList : null
		},
		methods : {
			selectPost : () => {
				$.ajax({
				    url: "/post/api/list",
				    type: "GET",
				    dataType: "json",
				    success: function(result){
						if ( result.resultCode == '00' ){
							$.each(result.resultList, (index, post)=>{
								post.url = "/post/detail?boardId="+post.boardId+"&postId=" + post.postId
							});							
					    	managePostApp.postList = result.resultList;
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
			deletePost : (postId) => {
				$.ajax({
					cache : false,
				    url: "/post/api/delete/" + postId,
				    type: "DELETE",
				    dataType: "json",
				    success: function(result){
						if ( result.resultCode == '00' ){
					    	alert('게시물 삭제를 완료 하였습니다.');
							managePostApp.selectPost();
						}else{
							alert(result.resultMessage);
						}
				    },
				    
				    error: function (request, status, error){        
						console.error("status => " + request.status + "error => " + request.responseText);
						alert("오류가 발생하였습니다. 관리자에게 문의해주세요~");              
				    }
				});					
			}				
		}
		
	});
	
	$('#headerApp').on('DOMSubtreeModified', '#heardLogin', function(){
		managePostApp.loginId = headerApp.loginId;
		managePostApp.role = headerApp.role;
	});
	
	managePostApp.selectPost();
});
