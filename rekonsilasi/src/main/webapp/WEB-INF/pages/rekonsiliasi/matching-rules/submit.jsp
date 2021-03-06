<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		Matching <small>Rules</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
		<li><a href="#">Reconciliation</a></li>
		<li class="active">Matching Rules</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-xs-6 col-xs-offset-3">
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">Matching Rules</h3>
				</div>
				<!-- /.box-header -->
				<form:form method="POST" action="/matching-rules/submitMatching" modelAttribute="data" enctype="multipart/form-data" >
					<div class="box-body">
						<div class="col-md-12">
							<label>Column ${nowColumn}</label> <form:select path="${nowColumn}" type="text" class="form-control"
									items="${columnList}" />
									<form:input type="hidden" path="nowColumn" value="${nowColumn}" />
						</div>
					</div>
					<div class="box-footer">
						<button type="submit" class="btn btn-primary">Submit</button>
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