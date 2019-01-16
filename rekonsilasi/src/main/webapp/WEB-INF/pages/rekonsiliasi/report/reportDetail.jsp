<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		Reporting
		<small>Detail</small>
	</h1>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-xs-offset-3 col-xs-6">
		<div class="box">
				<div class="box-header">
					<h3 class="box-title">Detail</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<table class="table table-bordered">
						<tr>
		                <td><b>REPORT ID</b></td>
					    <td>${data[0].logTransaction.id}</td>
					  </tr>
		           	   <tr>
		                <td><b>WSID</b></td>
					    <td>${data[0].logTransaction.wsId}</td>
					  </tr>
					  <tr>
		                <td><b>AMOUNT</b></td>
					    <td>${data[0].logTransaction.amount}</td>
					  </tr>
					  <tr>
		                <td><b>TRANSACTION DATE</b></td>
					    <td>${data[0].logTransaction.transactionDate}</td>
					  </tr>
					  <tr>
		                <td><b>TABLE SOURCE</b></td>
					    <td>${data[0].logTransaction.tableSource}</td>
					  </tr>
					  <tr>
		                <td><b>TABLE SOURCE ID</b></td>
					    <td>${data[0].logTransaction.tableSourceId}</td>
					  </tr>
					  
					</table>
				</div>
		</div>
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">Timeline</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<!-- The time line -->
          <ul class="timeline">
          <c:forEach var="status" items="${data}">   
            <!-- timeline time label -->
            <li class="time-label">
                  <span class="bg-yellow">
                    ${status.createdAt}
                  </span>
            </li>
            <!-- /.timeline-label -->
            <!-- timeline item -->
            <li>
            <c:if test="${status.status eq \"1\" }">
              <i class="fa fa-plus-circle bg-blue"></i>
            </c:if>
            <c:if test="${status.status eq \"2\" }">
              <i class="fa fa-pencil bg-green"></i>
            </c:if>
            <c:if test="${status.status eq \"3\" }">
              <i class="fa fa-ban bg-red"></i>
            </c:if>

              <div class="timeline-item">
				<c:if test="${status.status eq \"1\" }">
                <h3 class="timeline-header"><a href="#">${status.user.first_name} ${status.user.last_name}</a> Proposed Your Transaction</h3>
				</c:if>
				
				<c:if test="${status.status eq \"2\" }">
                <h3 class="timeline-header"><a href="#">${status.user.first_name} ${status.user.last_name}</a> Approved Your Transaction</h3>
				</c:if>
				
				<c:if test="${status.status eq \"3\" }">
                <h3 class="timeline-header"><a href="#">${status.user.first_name} ${status.user.last_name}</a> Rejected Your Transaction</h3>
				</c:if>
                <div class="timeline-body">
                   ${status.notes}
                </div>
              </div>
            </li>
            <!-- END timeline item -->
            </c:forEach>  
           
            <li>
              <i class="fa fa-clock-o bg-gray"></i>
            </li>
          </ul>
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

