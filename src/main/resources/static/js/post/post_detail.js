
let post_detail_app;

$(function() {
	post_detail_app = new Vue({
	  	el: '#post_detail_app',
	  	data : {
			post : {},
			comment_list : [],
			new_comment : {
				userId : null,
				postId : null,
				commentContent : ''
			}
		},
		methods : {
			select_post : ()=>{
				$.ajax({
				    url: "/post/api/get/" + p_postId,
				    type: "GET",
				    dataType: "json",
				    success: function(data){
						if ( data.resultCode == '00' ){
					    	post_detail_app.post = data.resultList[0];
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
			
			select_comments : ()=>{
				$.ajax({
				    url: "/comment/api/get/" + p_postId,
				    type: "GET",
				    dataType: "json",
				    success: function(data){
						if ( data.resultCode == '00' ){
					    	post_detail_app.comment_list = data.resultList;
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
			
			go_update_page : ()=>{
				location.href = "/post/form?boardId="+p_boardId+"&postId=" + p_postId;
			},
			
			post_delete : ()=>{
				$.ajax({
				    url: "/post/api/delete/" + p_postId,
				    type: "DELETE",
				    dataType: "json",
				    success: function(data){
						if ( data.resultCode == '00' ){
							alert('글삭제 성공');
							location.href = "/board/index?boardId=" + post_detail_app.post.boardId;
						}	
				    },
				    error: function (request, status, error){        
						var msg = "ERROR : " + request.status + "<br>"
						msg +=  + "내용 : " + request.responseText + "<br>" + error;
						alert(msg);              
				    }
				});	
			},
			
			comment_insert : () => {

				console.log(post_detail_app.new_comment)
				
			    $.ajax({
			        cache : false,
			        url : "/comment/api/insert", // 요기에
			        type : "POST", 
					dataType : "json",
					contentType: "application/json",
			        data : JSON.stringify(post_detail_app.new_comment), 
			        success : function(data) {
			            if ( data.resultCode == '00' ) {
							alert('댓글 작성 완료');
							post_detail_app.select_comments();
						}
			        }, // success 
			
			        error : function(xhr, status) {
			            alert(xhr + " : " + status);
			        }
			    }); // $.ajax */
				
			}
			
		}
	});
	
	post_detail_app.select_post();
	post_detail_app.select_comments();
	
	post_detail_app.new_comment.userId = header_app.login_id;
	post_detail_app.new_comment.postId = p_postId;
});		
	