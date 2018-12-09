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
            data: null, render: function (data, type, row) {
            	if(row.tableSourceA != null){
            		return "A";
            	}else{
            		return "B";
            	}
            }
        }, {
        	"data" : "namaStatus",
        	"name" : "namaStatus",
        	"autoWidth" : true
        }]
	});
});

jQuery('#_id_batal').click(function() {
	document.location.href = '/rekonsiliasi';
});