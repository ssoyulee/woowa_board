let app;

$(function() {
	app = new Vue({
	  	el: '#app',
	  	data : {
			hacker_news: [],
		},
		methods : {
			select_hacker_news : function(){
				$.ajax({
				    url: "/hacker/api/select",
				    type: "GET",
				    dataType: "json",
				    success: function(data){
						if ( data.resultCode == '00' ){
					    	app.hacker_news = data.listHacker;
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
	
	app.select_hacker_news();
	
});

