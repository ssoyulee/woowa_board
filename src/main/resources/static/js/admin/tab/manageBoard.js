// let manageBoardApp;

$(function() {
	let manageBoardApp = new Vue({
	  	el: '#manageBoardApp',
	  	data : {
			boardList : null,
			isBoard : false,
			loginId : headerApp.loginId,
			boardForm : {
				boardId : null,
				boardName : null,
				explanation : null,
				userId : null
			}
		},
		methods : {
			selectBoard : () => {
				$.ajax({
				    url: "/board/api/list",
				    type: "GET",
				    dataType: "json",
				    success: function(data){
						if ( data.resultCode == '00' ){
					    	manageBoardApp.boardList = data.resultList;
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
			getBoard : (boardId) => {
				$.ajax({
				    url: "/board/api/get/" + boardId,
				    type: "GET",
				    dataType: "json",
				    success: function(data){
						if ( data.resultCode == '00' ){
					    	manageBoardApp.boardForm = data.resultList[0];
							manageBoardApp.isBoard = true;
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
			insertBoard : () => {
				
				manageBoardApp.boardForm.userId = manageBoardApp.loginId;
				$.ajax({
					cache : false,
				    url: "/board/api/insert/",
				    type: "POST",
				    dataType: "json",
					contentType: "application/json",
					data : JSON.stringify(manageBoardApp.boardForm), 
				    success: function(data){
						if ( data.resultCode == '00' ){
					    	alert('게시판 등록을 완료 하였습니다.');
							manageBoardApp.selectBoard();
							headerApp.selectBoardList();
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
			updateBoard : () => {
				
				manageBoardApp.boardForm.userId = manageBoardApp.loginId;
				$.ajax({
					cache : false,
				    url: "/board/api/update/" + manageBoardApp.boardForm.boardId,
				    type: "PUT",
				    dataType: "json",
					contentType: "application/json",
					data : JSON.stringify(manageBoardApp.boardForm),
				    success: function(data){
						if ( data.resultCode == '00' ){
					    	alert('게시판 수정을 완료 하였습니다.');
							manageBoardApp.selectBoard();
							headerApp.selectBoardList();
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
			initBoard : () => {
				manageBoardApp.isBoard = false;
				manageBoardApp.boardForm = {
					boardId : null,
					boardName : null,
					explanation : null
				}				
			},
			deleteBoard : (boardId) => {
				$.ajax({
					cache : false,
				    url: "/board/api/delete/" + boardId,
				    type: "DELETE",
				    dataType: "json",
				    success: function(data){
						if ( data.resultCode == '00' ){
					    	alert('게시판 삭제를 완료 하였습니다.');
							manageBoardApp.selectBoard();
							headerApp.selectBoardList();
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
		manageBoardApp.loginId = headerApp.loginId;
		manageBoardApp.role = headerApp.role;
	});
	
	manageBoardApp.selectBoard()
});
