<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        User Transaction
        <small>Activity</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">User</a></li>
        <li class="active">User Activity</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
      
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
            
              <h3 class="box-title">User Activity</h3>
            </div>
            <!-- /.box-header -->
            <div class="col-xs-12"></div>
            <div class="box-body">
            
              <table id="activity" class="table table-bordered table-hover" width="100%">
                <thead>
                <tr>
                  <th>Log Transaction ID</th>
                  <th>Activity</th>
                  <th>Notes</th>
                  <th>Action Time</th>
                </tr>
                </thead>
                <tbody>
               	</tbody>
               	<tfoot>
                </tfoot>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
    
    