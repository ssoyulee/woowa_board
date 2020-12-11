$(function() {
	$('#manageTab').on('click',(obj)=>{
		
		let tabId = $(obj.target).attr('id');
		gotoTabContent(tabId);

	});
	
	const gotoTabContent = (tabId) => {
		
		let tabUrl = '';
		
		if ( tabId === 'manageUserTab' ){
			tabUrl = "/admin/manage/user";
		} else if ( tabId === 'manageBoardTab' ){
			tabUrl = "/admin/manage/board";
		} else if ( tabId === 'managePostTab' ){
			tabUrl = "/admin/manage/post";
		} else if ( tabId === 'manageCommentTab' ){
			tabUrl = "/admin/manage/comment";
		}
		
		$.ajax({
			cache : false,
			type: "GET",
			url: tabUrl,
			dataType: "html",
			success: function(resultHtml){
				$('#tabContent').html(resultHtml);
			}
		});	
	}
	
	
	gotoTabContent('manageUserTab');
});