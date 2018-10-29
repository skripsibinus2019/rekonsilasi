<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Propose
        <small>advanced tables</small>
      </h1>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Propose Save</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
     <form:form id="formpost" modelAttribute="data" action="/rekonsiliasi/${data.id}/${data.tableSource}/confirm" method="post">
		<form:hidden path="id"/>
			<table width="99%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" style="display: block;" class="form">	
						<tr>
								<td class="label">
									<form:label path="wsId">
										WSID
									</form:label>
								</td>
								<td>:</td>
								<td colspan="5">
									${data.wsId}
									<form:hidden path="wsId"/>
								</td>
							</tr>
							<tr>
								<td class="label">
									<form:label path="amount" cssErrorClass="errorLabel">
										Amount
									</form:label>
								</td>
								<td>:</td>
								<td colspan="5">
									${data.amount}
									<form:hidden path="amount"/>
								</td>
							</tr>
							<tr>
								<td class="label">
									<form:label path="transactionDate" cssErrorClass="errorLabel">
										Transaction Date
									</form:label>
								</td>
								<td>:</td>
								<td colspan="5">
									${data.transactionDate}
									<form:hidden path="transactionDate"/>
								</td>
							</tr>
							<tr>
								<td>
									<textarea name="confirm" rows="4" cols="50" form="formpost"></textarea>
								</td>
							</tr>
					</table>
			<tr>
				<td><br/> 
						<br/> <input type="submit" value="Proses"> <input type="button" value="Kembali">
				<input type="hidden" name="_batal" id="_id_batal" value="0">
				</td>
			</tr>
		</table>
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
    
    