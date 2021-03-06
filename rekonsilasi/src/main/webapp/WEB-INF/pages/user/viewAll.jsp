<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Users
        <small>List</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">Users</a></li>
        <li class="active">List</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
      
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
            
              <h3 class="box-title">All Users</h3>
              <a href="/user-management/user/add" class='btn btn-info pull-right'>Add New</a>
            </div>
            <!-- /.box-header -->
            <div class="col-xs-12"></div>
            <div class="box-body">
            <c:if test="${not empty message}">
				<div class="alert alert-success alert-dismissible">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4><i class="icon fa fa-check"></i> Sucess!</h4>
                ${message}
                </div>
                </c:if>
            
              <table id="example1" class="table table-bordered table-hover" width="100%">
                <thead>
                <tr>
                  <th>USERNAME</th>
                  <th>NAME</th>
                  <th>EMAIL</th>
                  <th>JOB TITLE</th>
                  <th>Action</th>
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
    
    