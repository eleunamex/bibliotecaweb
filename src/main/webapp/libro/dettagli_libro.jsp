<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Dettagli libro</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">

</head>

<c:set var="libro" scope="page"
	value="${requestScope.libroDaInviareAPaginaDettagli }" />

<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<div class='card'>
			<div class='card-header'>Dettagli libro</div>


			<div class='card-body'>
				<dl class="row">
					<dt class="col-sm-3 text-right">ID:</dt>
					<dd class="col-sm-9">
						<c:out value="${libro.id}" />
					</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Genere:</dt>
					<dd class="col-sm-9">
						<c:out value="${libro.genere}" />
					</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Titolo:</dt>
					<dd class="col-sm-9">
						<c:out value="${libro.titolo}" />
					</dd>
				</dl>
				
				<dl class="row">
					<dt class="col-sm-3 text-right">Trama:</dt>
					<dd class="col-sm-9">
						<c:out value="${libro.trama}" />
					</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Autore:</dt>
					<dd class="col-sm-9">
						<c:out value="${libro.autore.nome} ${libro.autore.cognome}" />
					</dd>
				</dl>
				
				<dl class="row">
					<dt class="col-sm-3 text-right">Data di nascita:</dt>
					<dd class="col-sm-9">
						<c:out value="${libro.autore.dataNascita}" />
					</dd>
				</dl>

			</div>

			<div class='card-footer'>
				<button onclick="window.history.go(-1); return false;" type="submit" value="back"
					class='btn btn-outline-secondary' style='width: 85px'>Indietro
				</button>
			</div>
		</div>

		<!-- end main container -->
	</main>
	<jsp:include page="../footer.jsp" />

</body>
</html>