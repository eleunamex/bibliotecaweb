<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Dettagli autore</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">

</head>

<c:set var="autore" scope="page"
	value="${requestScope.autoreDaInviareAPaginaDettagli }" />

<body>
	<jsp:include page="../navbar.jsp" />

	<main role="main" class="container">

		<div class='card'>
			<div class='card-header'>Dettagli autore</div>


			<div class='card-body'>
				<dl class="row">
					<dt class="col-sm-3 text-right">ID:</dt>
					<dd class="col-sm-9">
						<c:out value="${autore.id}" />
					</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Nome:</dt>
					<dd class="col-sm-9">
						<c:out value="${autore.nome}" />
					</dd>
				</dl>

				<dl class="row">
					<dt class="col-sm-3 text-right">Cognome:</dt>
					<dd class="col-sm-9">
						<c:out value="${autore.cognome}" />
					</dd>
				</dl>
				
				<dl class="row">
					<dt class="col-sm-3 text-right">Data di nascita:</dt>
					<dd class="col-sm-9">
						<c:out value="${autore.dataNascita}" />
					</dd>
				</dl>
				
				<dl class="row">
					<dt class="col-sm-3 text-right">Libri scritti:</dt>
					<dd class="col-sm-9">
						
						<c:forEach items="${autore.listaLibri}" var="libro">
							<c:out value="${libro.titolo}" /><br>
						</c:forEach>
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