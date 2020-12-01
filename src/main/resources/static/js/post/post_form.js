let post_app;

$(function() {

	post_app = new Vue({
		el: '#post_app',
	  	data : {
			post : {
		      		postId : null,
					boardId : null,
					postTitle : null,
					postContent : null,
					userId : null,
			}

		},
		methods : {
	
			post_insert : () => {
				
				post_app.post.postContent = $("#summernote").summernote("code");
				
			    $.ajax({
			        cache : false,
			        url : "/post/api/insert", // 요기에
			        type : "POST", 
					dataType : "json",
					contentType: "application/json",
			        data : JSON.stringify(post_app.post), 
			        success : function(data) {
			            if ( data.resultCode == '00' ) {
							alert('글쓰기 성공');
							location.href = "/board/index?boardId=" + post_app.post.boardId;
						}
			        }, // success 
			
			        error : function(xhr, status) {
			            alert(xhr + " : " + status);
			        }
			    }); // $.ajax */
			},
			
			post_update : () => {
				
				post_app.post.postContent = $("#summernote").summernote("code");
				
			    $.ajax({
			        cache : false,
			        url : "/post/api/update/"+post_app.post.postId, // 요기에
			        type : "PUT", 
					dataType : "json",
					contentType: "application/json",
			        data : JSON.stringify(post_app.post), 
			        success : function(data) {
			            if ( data.resultCode == '00' ) {
							alert('게시물 수정 성공');
							location.href = "/board/index?boardId=" + post_app.post.boardId;
						}
			        }, // success 
			
			        error : function(xhr, status) {
			            alert(xhr + " : " + status);
			        }
			    }); // $.ajax */
			}			
		}
	});
	

	$('#summernote').summernote({
		height: 300,                 // set editor height
		minHeight: null,             // set minimum height of editor
		maxHeight: '100%',             // set maximum height of editor
		focus: true                  // set focus to editable area after initializing summernote
	});	
	
	if (p_postId){
   		 $.ajax({
	        cache : false,
	        url : "/post/api/get/" + p_postId, // 요기에
	        type : "GET", 
			contentType: "application/json", 
	        success : function(data) {
	            if ( data.resultCode == '00' ) {
					let _post = data.resultList[0]
					post_app.post.postId = _post.postId;
					post_app.post.boardId = _post.boardId;
					post_app.post.postTitle = _post.postTitle;
					post_app.post.postContent = _post.postContent;  
					$("#summernote").summernote("code", post_app.post.postContent);
				}
	        }, // success 
	
	        error : function(xhr, status) {
	            alert(xhr + " : " + status);
	        }
	    }); // $.ajax */
	} else {
		post_app.post.boardId = p_boardId;
		post_app.post.userId = header_app.login_id
	}
});


		