<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Modifica utente</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/assets/js/jquery-3.4.1.min.js"></script>
<script>

	$(function() {
		$("#submit").click(function() {
			validateForm();
		});

		function validateForm() {
			var nome = $('#nome').val();
			var cognome = $('#cognome').val();
			var username = $('#username').val();
			var password = $('#password').val();
			var idRuolo = $('#idRuolo').val();

			if (nome == "") {
				alert('Nome non è valido');
				stopSubmit();
			}
			if (cognome == "") {
				alert('Cognome non è valido');
				stopSubmit();
			}
			if (username == "") {
				alert('Username non è valido');
				stopSubmit();
			}
			if (password == "") {
				alert('Password non è valida');
				stopSubmit();
			}
			if (idRuolo == "") {
				alert('Selezionare almeno un ruolo');
				stopSubmit();
			}

			location.reload();
		}

		function stopSubmit() {
			$("#form").submit(function(e) {
				e.preventDefault();
			});
		}

	});
</script>
</head>
<c:set var="utente" scope="page"
	value="${requestScope.utenteDaInviareAPaginaModifica}" />
<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<div
			class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}"
			role="alert">
			${successMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>


		<div
			class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
			role="alert">
			${errorMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div class='card'>
			<div class='card-header'>
				<h5>Modifica utente</h5>
			</div>
			<div class='card-body'>

				<h6 class="card-title">
					I campi con <span class="text-danger">*</span> sono obbligatori
				</h6>

				<form method="post"
					action="${pageContext.request.contextPath}/update/ExecuteUpdateUtenteServlet"
					novalidate="novalidate" id="form">

					<div class="form-row">
						<div class="form-group col-md-4">
							<label>Nome <span class="text-danger">*</span>
							</label> 
							<input type="text" name="nome" id="nome" class="form-control"
								value="<c:out value="${utente.nome}"/>" required>
						</div>

						<div class="form-group col-md-4">
							<label>Cognome <span class="text-danger">*</span>
							</label> 
							<input type="text" name="cognome" id="cognome"
								class="form-control" value="<c:out value="${utente.cognome}"/>"
								required>
						</div>

						<div class="form-group col-md-4">
							<label>Username <span class="text-danger">*</span>
							</label> 
							<input type="text" name="username" id="username"
								class="form-control" value="<c:out value="${utente.username}"/>"
								required>
						</div>

						<div class="form-group col-md-4">
							<label>Password <span class="text-danger">*</span>
							</label> <input type="password" name="password" id="password"
								class="form-control" value="<c:out value="${utente.password}"/>"
								required>
						</div>

						<div class="form-group col md-4">
							<label for="exampleFormControlSelect1">Stato</label> <select
								class="form-control" id="stato" name="stato">
								<c:if test="${utente.stato eq 'ATTIVO' }">
									<option value="ATTIVO" selected>ATTIVO</option>
									<option value="DISABILITATO">DISABILITATO</option>
								</c:if>
								<c:if test="${utente.stato ne 'ATTIVO' }">
									<option value="DISABILITATO" selected>DISABILITATO</option>
									<option value="ATTIVO">ATTIVO</option>
								</c:if>
							</select>
						</div>

						<div class="form-group col md-4">
							<label for="exampleFormControlSelect1">Ruoli</label>
							<c:forEach items="${requestScope.listaRuoliAttribute}" var="ruolo">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="${ruolo.id}" id="idRuolo" name="idRuolo"
										<c:forEach items="${utente.listaRuoli}" var="ruoloUtente">
											${ruoloUtente.id eq ruolo.id ? 'checked' : ''} 
										</c:forEach>
									>
									<label class="form-check-label" for="defaultCheck1">
										${ruolo.codice} 
									</label>
								</div>
							</c:forEach>
						</div>

					</div>

					<input type="hidden" class="form-control" name="id" id="id"
						value="<c:out value="${utente.id}"/>" readonly>

					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-success">Conferma
					</button>
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