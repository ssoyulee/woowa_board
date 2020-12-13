// let manageUserApp;

$(function() {
	let manageCommentApp = new Vue({
	  	el: '#manageCommentApp',
	  	data : {
			commentList : null
		},
		methods : {
			selectComment : () => {
				$.ajax({
				    url: "/comment/api/list",
				    type: "GET",
				    dataType: "json",
				    success: function(result){
						if ( result.resultCode == '00' ){
							$.each(result.resultList, (index, comment)=>{
								comment.url = "/post/detail?boardId="+comment.post.boardId+"&postId=" + comment.postId
							});										
					    	manageCommentApp.commentList = result.resultList;
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
			deleteComment : (commentId) => {
				$.ajax({
					cache : false,
				    url: "/comment/api/delete/" + commentId,
				    type: "DELETE",
				    dataType: "json",
				    success: function(result){
						if ( result.resultCode == '00' ){
					    	alert('댓글 삭제를 완료 하였습니다.');
							manageCommentApp.selectComment();
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
		manageCommentApp.loginId = headerApp.loginId;
		manageCommentApp.role = headerApp.role;
	});
		
	manageCommentApp.selectComment();
});
