<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Approval
        <small>Data List</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Approval</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
      
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Approval</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
            <c:if test="${message eq \"Data Has Been Approved!\" }">
				<div class="alert alert-success alert-dismissible">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4><i class="icon fa fa-check"></i> Sucess!</h4>
                ${message}
                </div>
                </c:if>
                <c:if test="${message eq \"Data Has Been Rejected!\" }">
				<div class="alert alert-success alert-dismissible">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4><i class="icon fa fa-check"></i> Sucess!</h4>
                ${message}
                </div>
                </c:if>
              <table id="example1" class="table table-bordered table-hover" width="100%">
                <thead>
                <tr>
                  <th>WSID</th>
                  <th>Amount</th>
                  <th>Transaction Date</th>
                  <th>Table Source</th>
                  <th>Notes</th>
                  <th>Status</th>
                  <th>Approve</th>
                  <th>Reject</th>
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
    
    