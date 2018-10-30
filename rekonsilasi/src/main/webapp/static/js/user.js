$(document).ready(function() {
	$("#example1").DataTable({
		"processing" : true, // for show progress bar
		"serverSide" : true, // for process server side
		"filter" : true, // this is for disable filter (search box)
		"orderMulti" : false, // for disable multiple column at once
		"ajax" : {
			"url" : "/user/list",
			"type" : "POST",
			"datatype" : "json"
		},
		"columns" : [ {
			"data" : "username",
			"name" : "username",
			"autoWidth" : true
		},
        {
            data: null, render: function (data, type, row) {
                return "<a href='/user/"+ row.id + "/" + row.tableSource + "' class='btn btn-danger' onclick=DeleteData('" + row.CustomerID + "'); >Delete</a> <a href='/user/"+ row.id + "/" + row.tableSource + "' class='btn btn-primary' onclick=DeleteData('" + row.CustomerID + "'); >Edit</a>";
            }
        }]
	});
});