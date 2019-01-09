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
				<c:if test="${method eq \"approve\" }">
				<div class="box-header">
					<h3 class="box-title">Approve</h3>
				</div>
				</c:if>
				<c:if test="${method eq \"reject\" }">
				<div class="box-header">
					<h3 class="box-title">Reject</h3>
				</div>
				</c:if>
				<!-- /.box-header -->
				<div class="box-body">
					<form:form id="formpost" modelAttribute="data"
						action="/approval/${id}/${method}/process"
						method="POST" class="form-horizontal">
						<form:hidden path="id" />
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
							<div class="col-sm-10">	${noted}</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">New Notes</label>
							<div class="col-sm-10">
								<textarea id="text" name="notes" rows="4" cols="50" form="formpost"
								 class="form-control"></textarea>
							</div>
						</div>
						<!-- /.box-body -->
						<div class="box-footer">
							<a href="/approval" class="btn btn-primary">Kembali</a>
							<input type="hidden" name="_batal" id="_id_batal" value="0">
							<button type="submit" value="Proses"
								class="btn btn-info pull-right">Proses</button>
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

