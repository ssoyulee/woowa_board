
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
				    success: function(data){
						if ( data.resultCode == '00' ){
					    	postDetailApp.post = data.resultList[0];
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
			
			selectComments : ()=>{
				$.ajax({
				    url: "/comment/api/get/" + reqPostId,
				    type: "GET",
				    dataType: "json",
				    success: function(data){
						if ( data.resultCode == '00' ){
					    	postDetailApp.commentList = data.resultList;
						}else{
							console.info(data.resultMessage);
						}		
				    },
				    error: function (request, status, error){        
						var msg = "ERROR : " + request.status + "<br>"
						msg +=  + "내용 : " + request.responseText + "<br>" + error;
						alert(msg);              
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
				    success: function(data){
						if ( data.resultCode == '00' ){
							alert('글삭제 성공');
							location.href = "/board/index?boardId=" + postDetailApp.post.boardId;
						}	
				    },
				    error: function (request, status, error){        
						var msg = "ERROR : " + request.status + "<br>"
						msg +=  + "내용 : " + request.responseText + "<br>" + error;
						alert(msg);              
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
			        success : function(data) {
			            if ( data.resultCode == '00' ) {
							alert('댓글 작성 완료');
							postDetailApp.selectComments();
						}
			        }, // success 
			
			        error : function(xhr, status) {
			            alert(xhr + " : " + status);
			        }
			    }); // $.ajax */
				
			},

			updateComment : (commentId) => {

				let commentBody = {
					userId : postDetailApp.loginId,
					commentContent : $('#comment' +commentId).val()
				};
				
				debugger;
			    $.ajax({
			        cache : false,
			        url : "/comment/api/update/" + commentId, // 요기에
			        type : "PUT",
					dataType : "json",
					contentType: "application/json",
			        data : JSON.stringify(commentBody), 
			        success : function(data) {
			            if ( data.resultCode == '00' ) {
							alert('댓글 수정 완료');
							postDetailApp.selectComments();
						}
			        }, // success 
			
			        error : function(xhr, status) {
			            alert(xhr + " : " + status);
			        }
			    }); // $.ajax */
				
			},

			deleteComment : (commentId)=>{
				$.ajax({
				    url: "/comment/api/delete/" + commentId,
				    type: "DELETE",
				    dataType: "json",
				    success: function(data){
						if ( data.resultCode == '00' ){
							alert('글삭제 성공');
							postDetailApp.selectComments();
						}	
				    },
				    error: function (request, status, error){        
						var msg = "ERROR : " + request.status + "<br>"
						msg +=  + "내용 : " + request.responseText + "<br>" + error;
						alert(msg);              
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
	