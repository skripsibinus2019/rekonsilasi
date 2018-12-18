<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		Approval Confirm
		<small>advanced tables</small>
	</h1>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-xs-offset-3 col-xs-6">
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">Propose</h3>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<form:form id="formpost" modelAttribute="data"
						action="/rekonsiliasi/${data.logTransId}/approve/process"
						method="POST" class="form-horizontal">
						<form:hidden path="id" />
						<form:hidden path="notes_lama"/>
						<div class="form-group">
							<label class="col-sm-2 control-label">WSID</label>

							<div class="col-sm-10">${data.wsId}</div>
							<form:hidden path="wsId"/>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Amount</label>

							<div class="col-sm-10">${data.amount}</div>
							<form:hidden path="amount"/>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Transaction Date</label>

							<div class="col-sm-10">${data.transactionDate}</div>
							<form:hidden path="transactionDate"/>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Notes</label>
							<div class="col-sm-10">	${data.notes_baru}</div>
							<form:hidden path="notes_baru"/>
						</div>
						<!-- /.box-body -->
						<div class="box-footer">
							<a href="/rekonsiliasi/${data.id}/${data.tableSource}" class="btn btn-primary">Kembali</a>
							<input type="hidden" name="_batal" id="_id_batal" value="0">
							<button type="submit" value="Proses"
								class="btn btn-info pull-right">Propose</button>
						</div>
						<!-- /.box-footer -->
					</form:form>
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

