<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		Dashboard <small>Aplication</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
		<li class="active">Dashboard</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<!-- Info boxes -->
	<div class="row">
		<div class="col-md-4 col-sm-6 col-xs-12">
			<div class="info-box">
				<span class="info-box-icon bg-aqua">A</span>

				<div class="info-box-content">
					<span class="info-box-text">DATA-A (Main Data)</span> <span
						class="info-box-number">${data.countA}</span>
				</div>
				<!-- /.info-box-content -->
			</div>
			<!-- /.info-box -->
		</div>
		<!-- /.col -->
		<div class="col-md-4 col-sm-6 col-xs-12">
			<div class="info-box">
				<span class="info-box-icon bg-red">B</span>

				<div class="info-box-content">
					<span class="info-box-text">DATA-B</span> <span
						class="info-box-number">${data.countB}</span>
				</div>
				<!-- /.info-box-content -->
			</div>
			<!-- /.info-box -->
		</div>
		<!-- /.col -->

		<!-- fix for small devices only -->
		<div class="clearfix visible-sm-block"></div>

		<!-- /.col -->
		<div class="col-md-4 col-sm-6 col-xs-12">
			<div class="info-box">
				<span class="info-box-icon bg-yellow">CSV</span>

				<div class="info-box-content">
					<span class="info-box-text">DATA-CSV</span> <span
						class="info-box-number">${data.countCSV}</span>
				</div>
				<!-- /.info-box-content -->
			</div>
			<!-- /.info-box -->
		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->

	<div class="row">
		<div class="col-md-12">
			<!-- TABLE: LATEST ORDERS -->
			<div class="box box-info">
				<div class="box-header with-border">
					<h3 class="box-title">Your Favorite Report</h3>

					<div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool"
							data-widget="collapse">
							<i class="fa fa-minus"></i>
						</button>
						<button type="button" class="btn btn-box-tool"
							data-widget="remove">
							<i class="fa fa-times"></i>
						</button>
					</div>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<div class="table-responsive">
						<table class="table no-margin">
							<thead>
								<tr>
									<th>Report ID</th>
									<th>WSID</th>
									<th>Amount</th>
									<th>Transaction Date</th>
									<th>Status</th>
									<th>Notes</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="favs" items="${fav}">
								<tr>
									<td><a href="log_transaction/${favs.logTransaction.id}">${favs.logTransaction.id}</a></td>
									<td>${favs.logTransaction.wsId}</td>
									<td>${favs.logTransaction.amount}</td>
									<td>${favs.logTransaction.transactionDate}</td>
									<c:if test="${favs.logTransaction.status eq \"1\" }">
										<td><span class="label label-primary">Proposed</span></td>
									</c:if>
									<c:if test="${favs.logTransaction.status eq \"2\" }">
										<td><span class="label label-success">Approved</span></td>
									</c:if>
									<c:if test="${favs.logTransaction.status eq \"3\" }">
										<td><span class="label label-danger">Rejected</span></td>
									</c:if>
									<td>${favs.logTransaction.notes}</td>
									<td><a href="/favoriteDelete/${favs.favId}" class="btn btn-warning"><i class="fa fa-trash"></i></a></td>
								</tr>
							</c:forEach>  
							</tbody>
						</table>
					</div>
					<!-- /.table-responsive -->
				</div>
				<!-- /.box-body -->
				<div class="box-footer clearfix">
					<a href="/rekonsiliasi"
						class="btn btn-sm btn-info btn-flat pull-left">Propose New
						Data</a> <a href="/log_transaction"
						class="btn btn-sm btn-default btn-flat pull-right">View All
						Report</a>
				</div>
				<!-- /.box-footer -->
			</div>
			<!-- /.box -->

		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-md-6">
			<!-- DONUT CHART -->
			<div class="box box-danger">
				<div class="box-header with-border">
					<h3 class="box-title">Data A - Data B</h3>

					<div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool"
							data-widget="collapse">
							<i class="fa fa-minus"></i>
						</button>
						<button type="button" class="btn btn-box-tool"
							data-widget="remove">
							<i class="fa fa-times"></i>
						</button>
					</div>
				</div>
				<div class="box-body">
					<canvas id="pieChart" style="height: 250px"></canvas>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
		<div class="col-md-6">
			<!-- DONUT CHART -->
			<div class="box box-danger">
				<div class="box-header with-border">
					<h3 class="box-title">Data A - Data CSV</h3>

					<div class="box-tools pull-right">
						<button type="button" class="btn btn-box-tool"
							data-widget="collapse">
							<i class="fa fa-minus"></i>
						</button>
						<button type="button" class="btn btn-box-tool"
							data-widget="remove">
							<i class="fa fa-times"></i>
						</button>
					</div>
				</div>
				<div class="box-body">
					<canvas id="pieChart" style="height: 250px"></canvas>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
	</div>
	<!-- /.row -->
</section>
<!-- /.content -->