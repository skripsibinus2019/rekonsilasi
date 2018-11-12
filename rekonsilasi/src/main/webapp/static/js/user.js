$(document).ready(function() {
	$("#example1").DataTable({
		"processing" : true, // for show progress bar
		"serverSide" : true, // for process server side
		"filter" : true, // this is for disable filter (search box)
		"orderMulti" : false, // for disable multiple column at once
		"ajax" : {
			"url" : "/user-management/user/list",
			"type" : "POST",
			"datatype" : "json"
		},
		"columns" : [ {
			"data" : "username",
			"name" : "username",
			"autoWidth" : true
		},
		{
            data: "first_name", render: function (data, type, row) {
                return row.first_name + " " + row.last_name ;
            }
        },
        {
			"data" : "email",
			"name" : "email",
			"autoWidth" : true
		},
		{
			"data" : "job_title",
			"name" : "job_title",
			"autoWidth" : true
		},
        {
            data: null, render: function (data, type, row) {
                return "<a class='btn btn-danger' onclick=DeleteData('" + row.userId + "'); >Delete</a> <a href='/user-management/user/edit/"+ row.userId  +" ' class='btn btn-primary' />Edit</a>";
            }
        }]
	});
});


function DeleteData(id)  
{  
        var url = "/user-management/user/delete";  
    swal({
        title: "Are you sure?",
        text: "Once deleted, you will not be able to recover this imaginary file!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                $.post(url, { "userId" : id } , function (data) {
                	 $.ajax({
                	        type: "post",
                	        url: url,
                	        contentType: "application/x-www-form-urlencoded",
                	        data: { "userId" : id },
                	        success: function(data) {}
                	      })
                	      .done(function(data) {
                	    	  oTable = $('#example1').DataTable();
                              oTable.draw();
                              swal("Poof! Your imaginary file has been deleted!", {
                                  icon: "success",
                              });
                	      })
                	      .error(function(data) {
                	    	  alert("Something Went Wrong!");
                	      });
                	  }); 
            } else {
                swal("Your imaginary file is safe!");
            }
        });
            
    }  