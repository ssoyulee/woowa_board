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
				    success: function(data){
						if ( data.resultCode == '00' ){
					    	manageUserApp.userList = data.resultList;
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
			getUser : (userId) => {
				$.ajax({
				    url: "/user/api/get/" + userId,
				    type: "GET",
				    dataType: "json",
				    success: function(data){
						if ( data.resultCode == '00' ){
					    	manageUserApp.userForm = data.resultList[0];
							manageUserApp.isUser = true;
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
			insertUser : () => {
				
				$.ajax({
					cache : false,
				    url: "/user/api/insert/",
				    type: "POST",
				    dataType: "json",
					contentType: "application/json",
					data : JSON.stringify(manageUserApp.userForm), 
				    success: function(data){
						if ( data.resultCode == '00' ){
					    	alert('사용자 등록을 완료 하였습니다.');
							manageUserApp.initUser();
							manageUserApp.selectUser();
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
			updateUser : () => {
				$.ajax({
					cache : false,
				    url: "/user/api/update/" + manageUserApp.userForm.userId,
				    type: "PUT",
				    dataType: "json",
					contentType: "application/json",
					data : JSON.stringify(manageUserApp.userForm),
				    success: function(data){
						if ( data.resultCode == '00' ){
					    	alert('사용자 수정을 완료 하였습니다.');
							manageUserApp.initUser();
							manageUserApp.selectUser();
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
				    success: function(data){
						if ( data.resultCode == '00' ){
					    	alert('사용자 삭제를 완료 하였습니다.');
							manageUserApp.selectUser();
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
	
	$('#headerApp').on('DOMSubtreeModified', '#heardLogin', function(){
		manageUserApp.loginId = headerApp.loginId;
		manageUserApp.role = headerApp.role;
		if (!manageUserApp.loginId){
//			alert('로그인 정보가 존재하지 않아 게시판으로 이동합니다.');
			location.href = "/index";
		}
	});
	
	manageUserApp.selectUser();		
});
