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
				    success: function(result){
						if ( result.resultCode == '00' ){
					    	manageBoardApp.boardList = result.resultList;
						}else{
							alert(result.resultMessage);
						}
				    },
				    
				    error: function (request, status, error){        
						console.error("status => " + request.status + "error => " + request.responseText);
						alert("오류가 발생하였습니다. 관리자에게 문의해주세요~");                  
				    }
				});
			},
			getBoard : (boardId) => {
				$.ajax({
				    url: "/board/api/get/" + boardId,
				    type: "GET",
				    dataType: "json",
				    success: function(result){
						if ( result.resultCode == '00' ){
					    	manageBoardApp.boardForm = result.resultList[0];
							manageBoardApp.isBoard = true;
						}else{
							alert(result.resultMessage);
						}
				    },
				    
				    error: function (request, status, error){        
						console.error("status => " + request.status + "error => " + request.responseText);
						alert("오류가 발생하였습니다. 관리자에게 문의해주세요~");                  
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
				    success: function(result){
						if ( result.resultCode == '00' ){
					    	alert('게시판 등록을 완료 하였습니다.');
							manageBoardApp.selectBoard();
							manageBoardApp.initBoard();
							headerApp.selectBoardList();
						}else{
							alert(result.resultMessage);
						}
				    },
				    
				    error: function (request, status, error){
						if (request.responseJSON.status === 400){
							alert(request.responseJSON.errors[0].defaultMessage);	
						}else{
							alert("오류가 발생하였습니다. 관리자에게 문의해주세요~");	
						}
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
				    success: function(result){
						if ( result.resultCode == '00' ){
					    	alert('게시판 수정을 완료 하였습니다.');
							manageBoardApp.selectBoard();
							manageBoardApp.initBoard();
							headerApp.selectBoardList();
							
						}else{
							alert(result.resultMessage);
						}
				    },
				    
				    error: function (request, status, error){        
						if (request.responseJSON.status === 400){
							alert(request.responseJSON.errors[0].defaultMessage);	
						}else{
							alert("오류가 발생하였습니다. 관리자에게 문의해주세요~");	
						}         
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
				    success: function(result){
						if ( result.resultCode == '00' ){
					    	alert('게시판 삭제를 완료 하였습니다.');
							manageBoardApp.selectBoard();
							headerApp.selectBoardList();
						}else{
							alert(result.resultMessage);
						}
				    },
				    
				    error: function (request, status, error){        
						console.error("status => " + request.status + "error => " + request.responseText);
						alert("오류가 발생하였습니다. 관리자에게 문의해주세요~");               
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
