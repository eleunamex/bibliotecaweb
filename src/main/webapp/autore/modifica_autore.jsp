<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Modifica autore</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/assets/js/jquery-3.4.1.min.js"></script>

</head>

<c:set var="autore" scope="page"
	value="${requestScope.autoreDaInviareAPaginaModifica}" />

<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<div
			class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
			role="alert">
			${errorMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div
			class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}"
			role="alert">
			${successMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div class='card'>
			<div class='card-header'>
				<h5>Modifica autore</h5>
			</div>
			<div class='card-body'>
				<form method="post"
					action="${pageContext.request.contextPath}/ExecuteUpdateAutoreServlet"
					novalidate="novalidate" id="form">

					<div class="form-row">
						<div class="form-group col-md-4">
							<label>Nome <span class="text-danger"></span>
							</label> <input type="text" name="nome" id="nome" class="form-control"
								value="${autore.nome}" required>
						</div>

						<div class="form-group col-md-4">
							<label>Cognome <span class="text-danger"></span>
							</label> <input type="text" name="cognome" id="cognome"
								class="form-control" value="${autore.cognome}" required>
						</div>

						<div class="form-group col-md-4">
							<label>Data di nascita <span class="text-danger"></span>
							</label> <input type="date" name="dataNascita" id="dataNascita"
								class="form-control" value="${autore.dataNascita}" required>
						</div>

					</div>
					
					<input type="hidden" class="form-control" name="idAutore" id="idAutore"
						value="<c:out value="${autore.id}"/>" readonly>
						<input type="hidden" class="form-control" name="cercaNomeAutore" id="cercaNomeAutore"
						value="<c:out value="${requestScope.cercaNomeAutore}"/>" readonly>
						<input type="hidden" class="form-control" name="cercaCognomeAutore" id="cercaCognomeAutore"
						value="<c:out value="${requestScope.cercaCognomeAutore}"/>" readonly>
						
					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-success">Conferma</button>
				</form>


				<!-- end card-body -->
			</div>
			<div class='card-footer'>
				<button onclick="window.history.go(-1); return false;" type="submit"
					value="back" class='btn btn-outline-secondary' style='width: 85px'>Indietro
				</button>
			</div>
		</div>


		<!-- end container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>