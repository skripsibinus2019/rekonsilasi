<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		User <small>Add User</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
		<li><a href="#">User</a></li>
		<li class="active">Add User</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-xs-6 col-xs-offset-3">
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">Add New User</h3>
				</div>
				<!-- /.box-header -->
				<form:form method="POST" action="/user/addSubmit" modelAttribute="data" >
					<div class="box-body">
						<div class="col-md-12">

							<!-- text input -->
							<div class="form-group">
								<label>Username</label> <form:input path="username" type="text" class="form-control"
									placeholder="Enter Username" />
							</div>
							<div class="form-group">
								<label>Password</label> <form:input path="password" type="password"
									class="form-control" placeholder="Enter Password" />
							</div>
							<div class="form-group">
								<label>Confirm Password</label> <input type="password"
									class="form-control" placeholder="Confirm Password">
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