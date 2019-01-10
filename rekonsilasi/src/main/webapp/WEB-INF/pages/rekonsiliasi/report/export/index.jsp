<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		Export <small>Data</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
		<li class="active">Export</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-xs-6 col-xs-offset-3">
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">Log Transaction Export</h3>
				</div>
				<!-- /.box-header -->
				<form:form method="POST" action="/matching-rules/submitMatchingUpload" enctype="multipart/form-data" >
					<div class="box-body">
					<div class="col-md-12">
							<label>Export Format</label> 
							<select class="form-control">
								<option>
								PDF
								</option>
								<option>
								XLS
								</option>
							</select>
						</div>
						<div class="col-md-6">
							<label>From</label> 
							<input type="text" class="form-control datepicker"/>
						</div>
						<div class="col-md-6">
							<label>To</label> 
							<input type="text" class="form-control datepicker"/>
						</div>
					</div>
					<div class="box-footer">
						<button type="submit" class="btn btn-primary">Export</button>
					</div>
				</form:form>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->
</section>
<!-- /.content -->