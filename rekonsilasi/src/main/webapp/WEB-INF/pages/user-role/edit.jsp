<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		Role <small>Edit Role</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> User Management</a></li>
		<li><a href="#">Roles</a></li>
		<li class="active">Add Role</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-xs-6 col-xs-offset-3">
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">Edit Role</h3>
				</div>
				<!-- /.box-header -->
				<form:form method="POST" modelAttribute="data" >
					<div class="box-body">
						<div class="col-md-12">

							<!-- text input -->
							<div class="form-group">
								<label>Role Name</label> <form:input path="roleName" type="text" class="form-control"
									placeholder="Enter Role Name" />
							</div>
							<div class="form-group">
								<label>Role Description</label> <form:input path="description" type="text"
									class="form-control" placeholder="Enter Role Description" />
							</div>
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