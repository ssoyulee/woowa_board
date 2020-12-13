let app;

$(function() {
	app = new Vue({
	  	el: '#app',
	  	data : {
			hackerNews: [],
		},
		methods : {
			selectHackerNews : () => {
				$.ajax({
				    url: "/hacker/api/select",
				    type: "GET",
				    dataType: "json",
				    success: function(result){
						console.debug("resultCode => ", result);
						if ( result.resultCode == '00' ){	
							var vueList = new Array();
							$.each(result.listHacker, (index, news)=>{
								if ( news != null ){
									vueList.push(news);	
								}
							});										
					    	app.hackerNews = vueList;
						}else{
							alert(result.resultMessage);
						}
				    },
				    error: function (request){
						console.error("status => " + request.status + "error => " + request.responseText);
						alert("오류가 발생하였습니다. 관리자에게 문의해주세요~");
				    }
				});
			},
		}
		
	});
	
	app.selectHackerNews();
	
});

