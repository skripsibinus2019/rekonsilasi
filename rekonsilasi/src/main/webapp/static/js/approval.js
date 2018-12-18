$(document).ready(function() {
	$("#example1").DataTable({
		"processing" : true, // for show progress bar
		"serverSide" : true, // for process server side
		"filter" : true, // this is for disable filter (search box)
		"orderMulti" : false, // for disable multiple column at once
		"ajax" : {
			"url" : "/approval/data",
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
			"data" : "notes",
			"name" : "notes",
			"autoWidth" : true
		}, {
			"data" : "namaStatus",
			"name" : "namaStatus",
			"autoWidth" : true
		}, {
            data: null, render: function (data, type, row) {
                return "<a href='/approval/"+ row.id + "/" + "approve" + "' class='btn btn-info' onclick=DeleteData('" + row.CustomerID + "'); >Propose</a>";
            }
        },
        {
            data: null, render: function (data, type, row) {
                return "<a href='/approval/"+ row.id + "/" + "reject" + "' class='btn btn-info' onclick=DeleteData('" + row.CustomerID + "'); >Propose</a>";
            }
        }]
	});
});

jQuery('#_id_batal').click(function() {
	document.location.href = '/rekonsiliasi';
});