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
		}, {
			"data" : "tableSource",
			"name" : "tableSource",
			"autoWidth" : true
		}, {
			"data" : "notes_baru",
			"name" : "notes_baru",
			"autoWidth" : true
		},
		{
			data: null, render: function (data, type, row) {
            	return '<span class="label label-danger">Unmatch</span>';
            }
        },
        {
            data: null, render: function (data, type, row) {
                return "<a href='/rekonsiliasi/"+ row.id + "/" + row.tableSource + "' class='btn btn-info' onclick=DeleteData('" + row.CustomerID + "'); >Propose</a>";
            }
        }]
	});
});

jQuery('#_id_batal').click(function() {
	document.location.href = '/rekonsiliasi';
});