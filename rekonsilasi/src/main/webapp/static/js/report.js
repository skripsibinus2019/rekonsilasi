$(document).ready(function() {
	$("#example1").DataTable({
		"processing" : true, // for show progress bar
		"serverSide" : true, // for process server side
		"filter" : true, // this is for disable filter (search box)
		"orderMulti" : false, // for disable multiple column at once
		"ajax" : {
			"url" : "/log_transaction/data",
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
		}, {
			"data" : "tableSource",
			"name" : "tableSource",
			"autoWidth" : true
		}, {
			"data" : "notes",
			"name" : "notes",
			"autoWidth" : true
		}, {
			data: "namaStatus", render: function (data, type, row) {
				if(row.namaStatus == "Pending"){
					return "<span class='label label-primary'>Proposed</span>";
				}else if(row.namaStatus == "Approved"){
					return "<span class='label label-success'>Approved</span>";
				}else if(row.namaStatus == "Rejected"){
					return "<span class='label label-danger'>Rejected</span>";
				}
            }
        },{
			data: null, render: function (data, type, row) {
				if(row.fav == 1){
					return "<a href='/log_transaction/"+ row.id + "' class='btn btn-info' ><i class='fa fa-info'></i></a> <a href='/favorite/"+ row.id + "' class='btn btn-warning' ><i class='fa fa-star'></i></a>";
				}else{
					return "<a href='/log_transaction/"+ row.id + "' class='btn btn-info' ><i class='fa fa-info'></i></a>";
				}
            }
        }]
	});
});

jQuery('#_id_batal').click(function() {
	document.location.href = '/rekonsiliasi';
});