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
				    success: function(data){
						if ( data.resultCode == '00' ){
					    	managePostApp.postList = data.resultList;
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
			deletePost : (postId) => {
				$.ajax({
					cache : false,
				    url: "/post/api/delete/" + postId,
				    type: "DELETE",
				    dataType: "json",
				    success: function(data){
						if ( data.resultCode == '00' ){
					    	alert('게시물 삭제를 완료 하였습니다.');
							managePostApp.selectPost();
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
		managePostApp.loginId = headerApp.loginId;
		managePostApp.role = headerApp.role;
	});
	
	managePostApp.selectPost();
});

/*	
$(function() {
	$('#test').on('click', ()=>{
		alert('manageUserApp');
	})
});	
*/