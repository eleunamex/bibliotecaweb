<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Lista autori</title>

<!-- style per le pagine diverse dalla index -->
<link href="${pageContext.request.contextPath}/assets/css/global.css"
	rel="stylesheet">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

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
				<h5>Lista degli autori</h5>
			</div>
			<div class='card-body'>
					<c:if test="${sessionScope.isAdmin eq true or sessionScope.isClassic  eq true }">
						<a href="autore/inserisci_autore.jsp" id="submit"
						class="btn btn-success">Aggiungi autore</a>
					</c:if>
				
				<c:if test="${!empty requestScope.listaAutoriAttribute}">

					<div class='table-responsive'>
						<table class='table table-striped '>
							<thead>
								<tr>
									<th>Id</th>
									<th>Nome</th>
									<th>Cognome</th>
									<th>Data di nascita</th>
									<th>Azioni</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="autore"
									items="${requestScope.listaAutoriAttribute}">
									<tr>
										<td><c:out value="${autore.id}" /></td>
										<td><c:out value="${autore.nome}" /></td>
										<td><c:out value="${autore.cognome}" /></td>
										<td><c:out value="${autore.dataNascita}" /></td>
										<td><a class="btn  btn-sm btn-outline-secondary"
											href="${pageContext.request.contextPath}/DettagliAutoreServlet?idDaInviareComeParametro=
											<c:out value="${autore.id}"/>">Visualizza
										</a> 
												<c:if test="${sessionScope.isAdmin eq true or sessionScope.isClassic  eq true }">
													<a class="btn  btn-sm btn-outline-primary ml-2 mr-2"
														href="${pageContext.request.contextPath}/update/PrepareUpdateAutoreServlet?idDaInviareComeParametro=
												<c:out value="${autore.id}"/>&cercaNomeAutore=${requestScope.cercaNomeAutore}&
												cercaCognomeAutore=${requestScope.cercaCognomeAutore}">Modifica
													</a>
													<button class="btn btn-outline-danger btn-sm"
														data-toggle="modal"
														data-target="#myModal<c:out value="${autore.id}"/>">Elimina
													</button>
												</c:if>
										</td>



									</tr>
									<!-- MODAL DELETE -->
									<div class="modal fade"
										id="myModal<c:out value="${autore.id}"/>" tabindex="-1"
										role="dialog" aria-labelledby="exampleModalLabel"
										aria-hidden="true">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel">Vuoi
														eliminare l'autore?</h5>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<div class="modal-body" id="idar" id="idar">
													<c:out value="${autore.nome} ${autore.cognome}" />
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary"
														data-dismiss="modal">Annulla</button>
													<a type="button" class="btn btn-danger"
														href="${pageContext.request.contextPath}/delete/DeleteAutoreServlet?idDaInviareComeParametro=
													<c:out value="${autore.id}"/>">Delete
													</a>
												</div>
											</div>
										</div>
									</div>
									<!-- END  MODAL DELETE -->
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>
				<c:if test="${empty requestScope.listaAutoriAttribute}">
					<p class="text-center">Non ci sono risultati.</p>
				</c:if>

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