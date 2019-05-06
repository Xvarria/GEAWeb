<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./includes/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="medidorList"/></title>
<%@ include file="./includes/basic.jsp"%>
<script type="text/javascript" src="../js/medidorList.js"></script>
</head>
<body>
	<form id="medidorForm" name="medidorForm">
		<%@ include file="./includes/standardHiddenFields.jsp"%>
		<div class="container">
			<div class="row upperSpace">
				<div class="col-md-10 offset-md-1">
					<fmt:message key="medidorList"/>
				</div>
			</div>
			<div class="row">
				<div class="col-md-10 offset-md-1">
					<table id="MedidorTable">
						<thead>
							<tr>
								<th><fmt:message key="medidor.medidorId"/></th>
								<th><fmt:message key="medidor.fechaInclusion"/></th>
								<th><fmt:message key="medidor.fechaUltimaLectura"/></th>
								<th><fmt:message key="medidor.ultimaLectura"/></th>
								<th><fmt:message key="encabezado.detalle"/></th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
<script type="text/javascript">
	$(document).ready(function(){
   		initDatatableOnList();
	});
</script>
</html>
