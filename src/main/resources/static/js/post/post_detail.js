
let post_detail_app;

$(function() {
	post_detail_app = new Vue({
	  	el: '#post_detail_app',
	  	data : {
			post : {},
			comment_list : [],
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
		}
	});
	
	post_detail_app.select_post();
	post_detail_app.select_comments();
});		
	