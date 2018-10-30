<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		Propose <small>advanced tables</small>
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
						action="/rekonsiliasi/${data.id}/${data.tableSource}/confirm"
						method="post" class="form-horizontal">
						<form:hidden path="id" />
						<div class="form-group">
							<label class="col-sm-2 control-label">WSID</label>

							<div class="col-sm-10">${data.wsId}</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Amount</label>

							<div class="col-sm-10">${data.amount}</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Transaction Date</label>

							<div class="col-sm-10">${data.transactionDate}</div>
						</div>
						<div class="form-group">
							<div class="col-sm-12">
								<textarea name="confirm" rows="4" cols="50" form="formpost"
									class="form-control"></textarea>
							</div>
						</div>
						<!-- /.box-body -->
						<div class="box-footer">
							<button type="submit" class="btn btn-default">Kembali</button>
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
