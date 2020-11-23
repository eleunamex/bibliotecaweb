<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Inserisci autore</title>


<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">

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
            nome : {
              required : true
            },
            cognome : {
                required : true
            },
            dataNascita : {
            	required : true,
                date : true,
                dateITA : true
            }
        },
        messages: {
            nome: "Nome non valido",
            cognome: "Cognome non valido",
            dataNascita: "Data di nascita non valida"
        },
        submitHandler: function(form) {
            form.submit();
        }
    });
});
</script>

</head>
<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">


		<div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}"
			role="alert">
			${successMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
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
				<h5>Inserisci un autore</h5>
			</div>
			<div class='card-body'>
				<form method="post" action="${pageContext.request.contextPath}/insert/ExecuteInsertAutoreServlet"
					novalidate="novalidate" id="form">

					<div class="form-row">
						<div class="form-group col-md-4">
							<label >Nome<span class="text-danger"></span>
							</label> <input type="text" name="nome" id="nome"
								class="form-control" placeholder="Inserire il nome"
								value="${requestScope.autore.nome}">
						</div>

						<div class="form-group col-md-4">
							<label >Cognome <span class="text-danger"></span>
							</label> <input type="text" name="cognome" id="cognome"
								class="form-control" placeholder="Inserire il cognome"
								value="${requestScope.autore.cognome}">
						</div>
						
						<div class="form-group col-md-4">
							<label>Data di nascita <span class="text-danger"></span>
							</label> <input type="date" name="dataNascita" id="dataNascita"
								class="form-control" placeholder="Inserire il nome"
								value="${requestScope.autore.dataNascita}">
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