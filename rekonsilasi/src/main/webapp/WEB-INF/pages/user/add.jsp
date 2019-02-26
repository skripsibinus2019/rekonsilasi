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
				<form:form method="POST" action="/user-management/user/addSubmit" modelAttribute="data" >
					<div class="box-body">
						<div class="col-md-12">

							<!-- text input -->
							<div class="form-group">
								<label>Username</label> <form:input path="username" type="text" class="form-control"
									placeholder="Enter Username, Example: u055998" />
									<form:errors style="color:red;" path="username" />
							</div>
							<div class="form-group">
							<label>Email</label> <form:input path="email" type="email" class="form-control"
									placeholder="Enter Email, Example: john@bca.co.id" />
									<form:errors style="color:red;" path="email" />
							</div>
							<div class="form-group">
								<label>Password</label> <form:input path="password" type="password"
									class="form-control" placeholder="Enter Password" />
									<form:errors style="color:red;" path="password" />
							</div>
							<div class="form-group">
								<label>Confirm Password</label> <form:input type="password"
									class="form-control" path="passwordConfirm" placeholder="Confirm Password" />
									<form:errors style="color:red;" path="passwordConfirm" />
							</div>
							<div class="form-group">
								<label>First Name</label> <form:input path="first_name" type="text" class="form-control"
									placeholder="Enter First Name, Example: John" />
									<form:errors style="color:red;" path="first_name" />
							</div>
							<div class="form-group">
								<label>Last Name</label> <form:input path="last_name" type="text" class="form-control"
									placeholder="Enter Last Name, Example: Doe" />
									<form:errors style="color:red;" path="last_name" />
							</div>
							<div class="form-group">
								<label>Job Title</label> <form:input path="job_title" type="text" class="form-control"
									placeholder="Enter Job Title, Example: Head of Divison" />'
									<form:errors style="color:red;" path="job_title" />
							</div>
							<div class="form-group">
								<label>Role</label> <form:select path="roleId" type="text" class="form-control"
									items="${roleList}" />
									<form:errors style="color:red;" path="roleId" />
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