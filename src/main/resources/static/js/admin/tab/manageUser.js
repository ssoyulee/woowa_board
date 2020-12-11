// let manageUserApp;

$(function() {
	let manageUserApp = new Vue({
	  	el: '#manageUserApp',
	  	data : {
			userList : null,
			isUser : false,
			loginId : headerApp.loginId,
			userForm : {
				userId : null,
				password : null,
				name : null,
				email : null,
				role : null
			},
			roleOptions : [
				{text : 'ADMIN', value : 'ADMIN'},
				{text : 'MEMBER', value : 'MEMBER'},
			]
		},
		methods : {
			selectUser : () => {
				$.ajax({
				    url: "/user/api/list",
				    type: "GET",
				    dataType: "json",
				    success: function(result){
						if ( result.resultCode == '00' ){
					    	manageUserApp.userList = result.resultList;
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
			getUser : (userId) => {
				$.ajax({
				    url: "/user/api/get/" + userId,
				    type: "GET",
				    dataType: "json",
				    success: function(result){
						if ( result.resultCode == '00' ){
					    	manageUserApp.userForm = result.resultList[0];
							manageUserApp.isUser = true;
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
			insertUser : () => {
				
				$.ajax({
					cache : false,
				    url: "/user/api/insert/",
				    type: "POST",
				    dataType: "json",
					contentType: "application/json",
					data : JSON.stringify(manageUserApp.userForm), 
				    success: function(result){
						if ( result.resultCode == '00' ){
					    	alert('사용자 등록을 완료 하였습니다.');
							manageUserApp.initUser();
							manageUserApp.selectUser();
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
			updateUser : () => {
				$.ajax({
					cache : false,
				    url: "/user/api/update/" + manageUserApp.userForm.userId,
				    type: "PUT",
				    dataType: "json",
					contentType: "application/json",
					data : JSON.stringify(manageUserApp.userForm),
				    success: function(result){
						if ( result.resultCode == '00' ){
					    	alert('사용자 수정을 완료 하였습니다.');
							manageUserApp.initUser();
							manageUserApp.selectUser();
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
			initUser : () => {
				manageUserApp.isUser = false;
				manageUserApp.userForm = {
					userId : null,
					password : null,
					name : null,
					email : null
				}
			},
			deleteUser : (userId) => {
				$.ajax({
					cache : false,
				    url: "/user/api/delete/" + userId,
				    type: "DELETE",
				    dataType: "json",
					contentType: "application/json",
				    success: function(result){
						if ( result.resultCode == '00' ){
					    	alert('사용자 삭제를 완료 하였습니다.');
							manageUserApp.selectUser();
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
		}
		
	});
	
	$('#headerApp').on('DOMSubtreeModified', '#heardLogin', function(){
		manageUserApp.loginId = headerApp.loginId;
		manageUserApp.role = headerApp.role;
		if (!manageUserApp.loginId)
			location.href = "/index";
		}
	});
	
	manageUserApp.selectUser();		
});
