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
				    success: function(data){
						if ( data.resultCode == '00' ){
					    	manageCommentApp.commentList = data.resultList;
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
			deleteComment : (commentId) => {
				$.ajax({
					cache : false,
				    url: "/comment/api/delete/" + commentId,
				    type: "DELETE",
				    dataType: "json",
				    success: function(data){
						if ( data.resultCode == '00' ){
					    	alert('댓글 삭제를 완료 하였습니다.');
							manageCommentApp.selectComment();
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
	
	$('#headerApp').on('DOMSubtreeModified', '#heardLogin', function(){
		manageCommentApp.loginId = headerApp.loginId;
		manageCommentApp.role = headerApp.role;
	});
		
	manageCommentApp.selectComment();
});

/*	
$(function() {
	$('#test').on('click', ()=>{
		alert('manageUserApp');
	})
});	
*/