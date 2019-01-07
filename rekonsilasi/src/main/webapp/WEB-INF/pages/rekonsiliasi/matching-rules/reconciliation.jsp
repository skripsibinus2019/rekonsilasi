<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Reconciliation Table
        <small>From Table A and CSV Table</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">Reconciliation</a></li>
        <li class="active">Index</li>
      </ol>
    </section>
    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <!-- /.box-header -->
            <div class="box-body">
            <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')">
              <table id="admin" class="table table-bordered table-hover" width="100%">
                <thead>
                <tr>
                  <th>ID</th>
                  <th>WSID</th>
                  <th>Amount</th>
                  <th>Transaction Date</th>
                  <th>Table Source</th>
                  <th>Status</th>
                </tr>
                </thead>
                <tbody>
               	</tbody>
               	<tfoot>
                </tfoot>
              </table>
            </sec:authorize>
            
            <sec:authorize access="hasAnyRole('ROLE_OPERATOR')">
              <table id="user" class="table table-bordered table-hover" width="100%">
                <thead>
                <tr>
                  <th>ID</th>
                  <th>WSID</th>
                  <th>Amount</th>
                  <th>Transaction Date</th>
                  <th>Table Source</th>
                  <th>Status</th>
                  <th>Action</th>
                </tr>
                </thead>
                <tbody>
               	</tbody>
               	<tfoot>
                </tfoot>
              </table>
            </sec:authorize>
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