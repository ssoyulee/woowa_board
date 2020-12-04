let postApp;

$(function() {

	postApp = new Vue({
		el: '#postApp',
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
	
			insertPost : () => {
				
				postApp.post.postContent = $("#summernote").summernote("code");
				
			    $.ajax({
			        cache : false,
			        url : "/post/api/insert", // 요기에
			        type : "POST", 
					dataType : "json",
					contentType: "application/json",
			        data : JSON.stringify(postApp.post), 
			        success : function(data) {
			            if ( data.resultCode == '00' ) {
							alert('글쓰기 성공');
							location.href = "/board/index?boardId=" + postApp.post.boardId;
						}
			        }, // success 
			
			        error : function(xhr, status) {
			            alert(xhr + " : " + status);
			        }
			    }); // $.ajax */
			},
			
			updatePost : () => {
				
				postApp.post.postContent = $("#summernote").summernote("code");
				
			    $.ajax({
			        cache : false,
			        url : "/post/api/update/"+postApp.post.postId, // 요기에
			        type : "PUT", 
					dataType : "json",
					contentType: "application/json",
			        data : JSON.stringify(postApp.post), 
			        success : function(data) {
			            if ( data.resultCode == '00' ) {
							alert('게시물 수정 성공');
							location.href = "/board/index?boardId=" + postApp.post.boardId;
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
	
	if (reqPostId){
   		 $.ajax({
	        cache : false,
	        url : "/post/api/get/" + reqPostId, // 요기에
	        type : "GET", 
			contentType: "application/json", 
	        success : function(data) {
	            if ( data.resultCode == '00' ) {
					let _post = data.resultList[0]
					postApp.post.postId = _post.postId;
					postApp.post.boardId = _post.boardId;
					postApp.post.postTitle = _post.postTitle;
					postApp.post.postContent = _post.postContent;  
					$("#summernote").summernote("code", postApp.post.postContent);
				}
	        }, // success 
	
	        error : function(xhr, status) {
	            alert(xhr + " : " + status);
	        }
	    }); // $.ajax */
	} else {
		postApp.post.boardId = reqBoardId;
		postApp.post.userId = headerApp.loginId
	}
	
	$('#headerApp').on('DOMSubtreeModified', '#heardLogin', function(){
		postApp.loginId = headerApp.loginId;
		postApp.role = headerApp.role;
		if (!postApp.loginId){
			alert('로그인 정보가 존재하지 않아 게시판으로 이동합니다.');
			location.href = "/board/index?boardId=" + postApp.post.boardId;	
		}
	});
	
});


		