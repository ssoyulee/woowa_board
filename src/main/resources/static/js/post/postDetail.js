
let postDetailApp;

$(function() {
	postDetailApp = new Vue({
	  	el: '#postDetailApp',
	  	data : {
			post : {},
			commentList : [],
			newComment : '',
			loginId : headerApp.loginId,
			role : headerApp.role
		},
		methods : {
			selectPost : ()=>{
				$.ajax({
				    url: "/post/api/get/" + reqPostId,
				    type: "GET",
				    dataType: "json",
				    success: function(result){
						if ( result.resultCode == '00' ){
					    	postDetailApp.post = result.resultList[0];
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
			
			selectComments : ()=>{
				$.ajax({
				    url: "/comment/api/get/" + reqPostId,
				    type: "GET",
				    dataType: "json",
				    success: function(result){
						if ( result.resultCode == '00' ){
					    	postDetailApp.commentList = result.resultList;
						}else{
							postDetailApp.commentList = [];
							console.info(result.resultMessage);
						}		
				    },
				    error: function (request, status, error){        
						console.error("status => " + request.status + "error => " + request.responseText);
						alert("오류가 발생하였습니다. 관리자에게 문의해주세요~");            
				    }
				});						
			},
			
			goUpdatePage : ()=>{
				location.href = "/post/form?boardId="+reqBoardId+"&postId=" + reqPostId;
			},
			
			deletePost : ()=>{
				$.ajax({
				    url: "/post/api/delete/" + reqPostId,
				    type: "DELETE",
				    dataType: "json",
				    success: function(result){
						if ( result.resultCode == '00' ){
							alert('글삭제 성공');
							location.href = "/board/index?boardId=" + postDetailApp.post.boardId;
						}	
				    },
				    error: function (request, status, error){        
						console.error("status => " + request.status + "error => " + request.responseText);
						alert("오류가 발생하였습니다. 관리자에게 문의해주세요~");           
				    }
				});	
			},
			
			insertComment : () => {

				console.log(postDetailApp.newComment)

				let commentBody = {
					userId : postDetailApp.loginId,
					postId : reqPostId,
					commentContent : postDetailApp.newComment
				};
				
			    $.ajax({
			        cache : false,
			        url : "/comment/api/insert", // 요기에
			        type : "POST", 
					dataType : "json",
					contentType: "application/json",
			        data : JSON.stringify(commentBody), 
			        success : function(result) {
			            if ( result.resultCode == '00' ) {
							alert('댓글 작성 완료');
							postDetailApp.selectComments();
						}
			        },
			        error: function (request, status, error){
						if (request.responseJSON.status === 400){
							alert(request.responseJSON.errors[0].defaultMessage);	
						}else{
							alert("오류가 발생하였습니다. 관리자에게 문의해주세요~");	
						}    
			        }
			    });
				
			},

			updateComment : (commentId) => {

				let commentBody = {
					userId : postDetailApp.loginId,
					postId : reqPostId,
					commentContent : $('#comment' +commentId).val()
				};
				
			    $.ajax({
			        cache : false,
			        url : "/comment/api/update/" + commentId, // 요기에
			        type : "PUT",
					dataType : "json",
					contentType: "application/json",
			        data : JSON.stringify(commentBody), 
			        success : function(result) {
			            if ( result.resultCode == '00' ) {
							alert('댓글 수정 완료');
							postDetailApp.selectComments();
						}
			        }, 
				    error: function (request, status, error){        
						if (request.responseJSON.status === 400){
							alert(request.responseJSON.errors[0].defaultMessage);	
						}else{
							alert("오류가 발생하였습니다. 관리자에게 문의해주세요~");	
						}       
				    }
			    });
				
			},

			deleteComment : (commentId)=>{
				$.ajax({
				    url: "/comment/api/delete/" + commentId,
				    type: "DELETE",
				    dataType: "json",
				    success: function(result){
						if ( result.resultCode == '00' ){
							alert('글삭제 성공');
							postDetailApp.selectComments();
						}	
				    },
				    error: function (request, status, error){        
						console.error("status => " + request.status + "error => " + request.responseText);
						alert("오류가 발생하였습니다. 관리자에게 문의해주세요~");           
				    }
				});	
			},				
			
		}
	});
	
	postDetailApp.selectPost();
	postDetailApp.selectComments();
	
	postDetailApp.newComment.userId = headerApp.loginId;
	postDetailApp.newComment.postId = reqPostId;
	
	$('#headerApp').on('DOMSubtreeModified', '#heardLogin', function(){
		postDetailApp.loginId = headerApp.loginId;
		postDetailApp.role = headerApp.role;
	});

});		
	