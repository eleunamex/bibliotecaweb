<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Modifica libro</title>

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
$().ready(function() {
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
            }
        },
        messages: {
        	genere: "Genere non valido",
        	titolo: "Titolo non valido",
        	trama: "Trama  non valida"
        },
        submitHandler: function(form) {
            form.submit();
        }
    });
});
</script>
</head>
<c:set var="libro" scope="page" value="${requestScope.libroDaInviareAPaginaModifica}" />
<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">
	
	<div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}" role="alert">
			${successMessage}
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
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
				<h5>Modifica libro</h5>
			</div>
			<div class='card-body'>

				<h6 class="card-title">
					I campi con <span class="text-danger">*</span> sono obbligatori
				</h6>

				<form method="post" action="${pageContext.request.contextPath}/update/ExecuteUpdateLibroServlet"
					novalidate="novalidate" id="form">

					<div class="form-row">
						<div class="form-group col-md-4">
							<label>Genere <span class="text-danger">*</span>
							</label> <input type="text" name="genere" id="genere"
								class="form-control" value="<c:out value="${libro.genere}"/>" required>
						</div>

						<div class="form-group col-md-4">
							<label>Titolo <span class="text-danger">*</span>
							</label> <input type="text" name="titolo" id="titolo"
								class="form-control" value="<c:out value="${libro.titolo}"/>"
								required>
						</div>

						<div class="form-group col md-4">
							<label for="exampleFormControlSelect1">Autore</label> 
							<select class="form-control" id="idAutore" name="idAutore">
								<c:forEach items="${requestScope.listaAutoriAttribute}" var="autore">
									<option value="${autore.id}" ${libro.autore.id eq autore.id ? 'selected' : ''}>
									${autore.nome} ${autore.cognome}
									</option>
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
									rows="2" ><c:out value="${libro.trama}"/></textarea>
							</div>
						</div>
					</div>
					
						<input type="hidden" class="form-control" name="id" id="id"
						value="<c:out value="${libro.id}"/>" readonly >

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