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
        }, {
            data: null, render: function (data, type, row) {
                return "<a href='/approval/"+ row.id + "/" + "approve" + "' class='btn btn-info' onclick=DeleteData('" + row.CustomerID + "'); >Approve</a>";
            }
        },
        {
            data: null, render: function (data, type, row) {
                return "<a href='/approval/"+ row.id + "/" + "reject" + "' class='btn btn-info' onclick=DeleteData('" + row.CustomerID + "'); >Reject</a>";
            }
        }]
	});
});

jQuery('#_id_batal').click(function() {
	document.location.href = '/rekonsiliasi';
});