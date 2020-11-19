<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Inserisci libro</title>

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
			var genere = $('#genere').val();
			var titolo = $('#titolo').val();
			var trama = $('#trama').val();

			if ( genere == "") {
				alert('Genere non è valido');
				stopSubmit();
			}
			if (titolo == "") {
				alert('Titolo non è valido');
				stopSubmit();
			}
			if (trama == "") {
				alert('Trama non è valida');
				stopSubmit();
			}
			if ($("#select_autore").val() == "0") {
				var nome = $('#nome').val();
				var cognome = $('#cognome').val();	
				if(nome == ""){
					alert('Nome autore non è valido');
					stopSubmit();
				}
				if(cognome =="" ){
					alert('Cognome autore non è valido');
					stopSubmit();
				}
			}
			
			location.reload();
		}

		function stopSubmit() {
			$("#form").submit(function(e) {
				e.preventDefault();
			});
		}
		$("#nuovo_autore").hide();

		$('#select_autore').change(function() {
			if ($(this).val() == "0") {
				$("#nuovo_autore").show();
			}else{
				$("#nuovo_autore").hide();
			}
		})

	});

	$(document).ready(function() {
		$("#hide").click(function() {
			$("p").hide();
		});
		$("#show").click(function() {
			$("#nuovo_autore").show();
		});
	});
</script>
</head>
<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">
	
	<div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}" role="alert">
			${successMessage}
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
			${errorMessage}
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div class="alert alert-danger alert-dismissible fade show d-none"
			role="alert">
			Operazione fallita!
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
				<h5>Inserisci nuovo libro</h5>
			</div>
			<div class='card-body'>

				<h6 class="card-title">
					I campi con <span class="text-danger">*</span> sono obbligatori
				</h6>

				<form method="post" action="${pageContext.request.contextPath}/insert/ExecuteInsertLibroServlet"
					novalidate="novalidate" id="form">

					<div class="form-row">
						<div class="form-group col-md-4">
							<label>Genere <span class="text-danger">*</span>
							</label> <input type="text" name="genere" id="genere"
								class="form-control" placeholder="Inserire il codice" required>
						</div>

						<div class="form-group col-md-4">
							<label>Titolo <span class="text-danger">*</span>
							</label> <input type="text" name="titolo" id="titolo"
								class="form-control" placeholder="Inserire la descrizione"
								required>
						</div>


						<div class="form-group col md-4">
							<label for="exampleFormControlSelect1">Autore</label> <select
								class="form-control" id="select_autore" name="idAutore">
								<option value="Scegli">Scegli</option>
								<option value="0">Nuovo autore</option>
								<c:forEach items="${requestScope.listaAutoriAttribute}"
									var="autore">
									<option value="${autore.id}">${autore.nome} ${autore.cognome}</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="form-row">
						<div class="form-group col-md-12">
							<label>Trama <span class="text-danger">*</span>
							</label>
							<div class="form-group">
								<textarea class="form-control" id="trama" name="trama"
									rows="2"></textarea>
							</div>
						</div>

						
					</div>


						<div class="form-row" id="nuovo_autore" >
						
							<div class="form-group col-md-4">
								<label>Nome <span class="text-danger">*</span>
								</label> <input type="text" name="nome" id="nome" class="form-control"
									placeholder="Inserire il nome" required>
							</div>

							<div class="form-group col-md-4">
								<label>Cognome <span class="text-danger">*</span>
								</label> <input type="text" name="cognome" id="cognome"
									class="form-control" placeholder="Inserire il cognome" required>
							</div>

							<div class="form-group col-md-4">
								<label>Data di nascita <span class="text-danger">*</span>
								</label>
								<div class="form-group">
									<input type="date" name="dataNascita" id="dataNascita"
										class="form-control" placeholder="Inserire la data di nascita"
										required>
								</div>
							</div>

						</div>


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