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
<script src="${pageContext.request.contextPath}/assets/js/jquery-3.4.1.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.min.js"></script>

<style type="text/css">
.error {
      color: red;
   }
</style>
<script>
$(function() {

	 $("#form").validate({
	        rules : {
	            genere : {
	              required : true
	            },
	            titolo : {
	                required : true,
	            },
	            trama : {
	                required : true,
	            },
	            nome : {
	                required : true
	              },
	              cognome : {
	                  required : true,
	              },
	              dataNascita : {
	                  required : true,
	              }
	        },
	        messages: {
	        	genere: "Genere non valido",
	        	titolo: "Titolo non valido",
	        	trama: "Trama  non valida",
	        	  nome: "Nome non valido",
	              cognome: "Cognome non valido",
	              dataNascita: "Data di nascita non valida"
	        },
	        submitHandler: function(form) {
	            form.submit();
	        }
	    });
	
	$("#nuovo_autore").show();

		$('#select_autore').change(function() {
			if ($(this).val() == "0") {
				$("#nuovo_autore").show();
			} else {
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
		
		<div class="alert alert-danger alert-dismissible fade show ${requestScope.errorValidation==null?'d-none': ''}"
			role="alert">
			<c:forEach items="${requestScope.errorValidation}" var="errore">
       			 ${errore}<br>
    		</c:forEach>
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
								class="form-control" placeholder="Inserire il codice" 
								value="${requestScope.libro.genere}"
								required>
						</div>

						<div class="form-group col-md-4">
							<label>Titolo <span class="text-danger">*</span>
							</label> <input type="text" name="titolo" id="titolo"
								class="form-control" placeholder="Inserire la descrizione"
								value="${requestScope.libro.titolo}"
								required>
						</div>


						<div class="form-group col md-4">
							<label for="exampleFormControlSelect1">Autore</label> <select
								class="form-control" id="select_autore" name="idAutore">
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
									rows="2" >${requestScope.libro.trama}</textarea>
							</div>
						</div>
					</div>


						<div class="form-row" id="nuovo_autore" >
						
							<div class="form-group col-md-4">
								<label>Nome <span class="text-danger">*</span>
								</label> <input type="text" name="nome" id="nome" class="form-control"
									placeholder="Inserire il nome"
									value="${requestScope.autore.nome}" required>
							</div>

							<div class="form-group col-md-4">
								<label>Cognome <span class="text-danger">*</span>
								</label> <input type="text" name="cognome" id="cognome"
									class="form-control" placeholder="Inserire il cognome" 
									value="${requestScope.autore.cognome}" required>
							</div>

							<div class="form-group col-md-4">
								<label>Data di nascita <span class="text-danger">*</span>
								</label>
								<div class="form-group">
									<input type="date" name="dataNascita" id="dataNascita"
										class="form-control" placeholder="Inserire la data di nascita"
										value="${requestScope.autore.dataNascita}"
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