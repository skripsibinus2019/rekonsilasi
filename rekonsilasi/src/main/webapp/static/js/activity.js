$(document).ready(function() {
	$("#example1").DataTable({
		"processing" : true, // for show progress bar
		"serverSide" : true, // for process server side
		"filter" : true, // this is for disable filter (search box)
		"orderMulti" : false, // for disable multiple column at once
		"ajax" : {
			"url" : "/activity/list",
			"type" : "POST",
			"datatype" : "json"
		},
		"columns" : [ {
			"data" : "logTransactionId",
			"name" : "logTransaction.id",
			"autoWidth" : true
		},
		{
            data: "status", render: function (data, type, row) {
            	if(row.status == 1){
            		return "Proposed";
            	}else if(row.status == 2){
            		return "Approved";
            	}else if(row.status == 3){
            		return "Rejected";
            	}
            }
        },
        {
			"data" : "notes",
			"name" : "notes",
			"autoWidth" : true
		},
		{
			"data" : "user.username",
			"name" : "user.username",
			"autoWidth" : true
		},
		{
			"data" : "createdAt",
			"name" : "createdAt",
			"autoWidth" : true
		}]
	});
});