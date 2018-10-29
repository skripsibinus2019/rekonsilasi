$(document).ready(function() {
	$("#example1").DataTable({
		"processing" : true, // for show progress bar
		"serverSide" : true, // for process server side
		"filter" : true, // this is for disable filter (search box)
		"orderMulti" : false, // for disable multiple column at once
		"ajax" : {
			"url" : "/Rekonsiliasi/List",
			"type" : "POST",
			"datatype" : "json"
		},
		"columns" : [ {
			"data" : "id",
			"name" : "id",
			"autoWidth" : true
		}, {
			"data" : "wsId",
			"name" : "wsId",
			"autoWidth" : true
		}, {
			"data" : "amount",
			"name" : "amount",
			"autoWidth" : true
		}, {
			"data" : "transactionDate",
			"name" : "transactionDate",
			"autoWidth" : true
		},
		{
			data: null, render: function (data, type, row) {
            	if(row.status == 0){
            		return '<span class="label label-danger">Unmatch</span>';
            	}else if(row.status == 1){
            		return '<span class="label label-warning">Purposed</span>';
            	}
            }
        },
        {
            data: null, render: function (data, type, row) {
                return "<a href='#' class='btn btn-info' onclick=DeleteData('" + row.CustomerID + "'); >Purpose</a>";
            }
        }]
	});
});