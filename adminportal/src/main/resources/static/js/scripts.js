/**
 *  For Medicines
**/

$(document).ready(function() {
	$('.delete-med').on('click', function (){
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'remove';
	    /*]]>*/
		
		var id=$(this).attr('id');
		
		bootbox.confirm({
			message: "Are you sure to remove this medicine? It can't be undone.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.post(path, {'id':id}, function(res) {
						location.reload();
					});
				}
			}
		});
	});
	
	
	
//	$('.checkboxMed').click(function () {
//        var id = $(this).attr('id');
//        if(this.checked){
//            medIdList.push(id);
//        }
//        else {
//            medIdList.splice(medIdList.indexOf(id), 1);
//        }
//    })
	
	$('#deleteSelected').click(function() {
		var idList= $('.checkboxMed');
		var medIdList=[];
		for (var i = 0; i < idList.length; i++) {
			if(idList[i].checked==true) {
				medIdList.push(idList[i]['id'])
			}
		}
		
		console.log(medIdList);
		
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'removeList';
	    /*]]>*/
	    
	    bootbox.confirm({
			message: "Are you sure to remove all selected medicines? It can't be undone.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.ajax({
						type: 'POST',
						url: path,
						data: JSON.stringify(medIdList),
						contentType: "application/json",
						success: function(res) {
							console.log(res); 
							location.reload()
							},
						error: function(res){
							console.log(res); 
							location.reload();
							}
					});
				}
			}
		});
	});
	
	$("#selectAllMeds").click(function() {
		if($(this).prop("checked")==true) {
			$(".checkboxMed").prop("checked",true);
		} else if ($(this).prop("checked")==false) {
			$(".checkboxMed").prop("checked",false);
		}
	})
});



/**
 *  For Users
**/

$(document).ready(function() {
    $('.delete-user').on('click', function (){
        /*<![CDATA[*/
        var path = /*[[@{/}]]*/'remove';
        /*]]>*/
        
        var id=$(this).attr('id');
        
        bootbox.confirm({
            message: "Are you sure to remove this user? It can't be undone.",
            buttons: {
                cancel: {
                    label:'<i class="fa fa-times"></i> Cancel'
                },
                confirm: {
                    label:'<i class="fa fa-check"></i> Confirm'
                }
            },
            callback: function(confirmed) {
                if(confirmed) {
                    $.post(path, {'id':id}, function(res) {
                        location.reload();
                    });
                }
            }
        });
    });
    
    
    
//  $('.checkboxUser').click(function () {
//        var id = $(this).attr('id');
//        if(this.checked){
//            userIdList.push(id);
//        }
//        else {
//            userIdList.splice(userIdList.indexOf(id), 1);
//        }
//    })
    
    $('#deleteSelected').click(function() {
        var idList= $('.checkboxUser');
        var userIdList=[];
        for (var i = 0; i < idList.length; i++) {
            if(idList[i].checked==true) {
                userIdList.push(idList[i]['id'])
            }
        }
        
        console.log(userIdList);
        
        /*<![CDATA[*/
        var path = /*[[@{/}]]*/'removeList';
        /*]]>*/
        
        bootbox.confirm({
            message: "Are you sure to remove all selected users? It can't be undone.",
            buttons: {
                cancel: {
                    label:'<i class="fa fa-times"></i> Cancel'
                },
                confirm: {
                    label:'<i class="fa fa-check"></i> Confirm'
                }
            },
            callback: function(confirmed) {
                if(confirmed) {
                    $.ajax({
                        type: 'POST',
                        url: path,
                        data: JSON.stringify(userIdList),
                        contentType: "application/json",
                        success: function(res) {
                            console.log(res); 
                            location.reload()
                            },
                        error: function(res){
                            console.log(res); 
                            location.reload();
                            }
                    });
                }
            }
        });
    });
    
    $("#selectAllUsers").click(function() {
        if($(this).prop("checked")==true) {
            $(".checkboxUser").prop("checked",true);
        } else if ($(this).prop("checked")==false) {
            $(".checkboxUser").prop("checked",false);
        }
    })
});