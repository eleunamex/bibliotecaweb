<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="../header.jsp" />
<title>Lista libri</title>

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
		
		<div class="alert alert-danger alert-dismissible fade show d-none"
			role="alert">
			Esempio di operazione fallita!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="alert alert-info alert-dismissible fade show d-none"
			role="alert">
			Aggiungere d-none nelle class per non far apparire
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div class='card'>
			<div class='card-header'>
				<h5>Lista dei libri</h5>
			</div>
			<div class='card-body'>
			
					<c:if test="${sessionScope.isAdmin eq true or sessionScope.isClassic  eq true }">
						<a href="${pageContext.request.contextPath}/insert/PrepareInsertLibro" id="submit"
						class="btn btn-success">Aggiungi libro</a>
					</c:if>

				<c:if test="${!empty requestScope.listaLibriAttribute}">

					<div class='table-responsive'>
						<table class='table table-striped '>
							<thead>
								<tr>
									<th>Id</th>
									<th>Genere</th>
									<th>Titolo</th>
									<th>Autore</th>
									<th>Azioni</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="libro"
									items="${requestScope.listaLibriAttribute}">
									<tr>
										<td><c:out value="${libro.id}" /></td>
										<td><c:out value="${libro.genere}" /></td>
										<td><c:out value="${libro.titolo}" /></td>
										<td><c:out
												value="${libro.autore.nome} ${libro.autore.cognome}" /></td>
										<td><a class="btn  btn-sm btn-outline-secondary"
											href="${pageContext.request.contextPath}/DettagliLibroServlet?idDaInviareComeParametro=
											<c:out value="${libro.id}"/>">Visualizza
										</a> 
												<c:if test="${sessionScope.isAdmin eq true or sessionScope.isClassic  eq true }">
													<a class="btn  btn-sm btn-outline-primary ml-2 mr-2"
														href="${pageContext.request.contextPath}/update/PrepareUpdateLibroServlet?idDaInviareComeParametro=
														<c:out value="${libro.id}"/>">Modifica
													</a>
												</c:if>
											
												<c:if test="${sessionScope.isAdmin eq true or sessionScope.isClassic  eq true }">
													<button class="btn btn-outline-danger btn-sm"
														data-toggle="modal"
														data-target="#myModal<c:out value="${libro.id}"/>">Elimina
													</button>
												</c:if>
										</td>

									</tr>

									<!-- MODAL DELETE -->
									<div class="modal fade"
										id="myModal<c:out value="${libro.id}"/>" tabindex="-1"
										role="dialog" aria-labelledby="exampleModalLabel"
										aria-hidden="true">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel">Vuoi
														eliminare il libro?</h5>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>
												<div class="modal-body" id="idar" id="idar">
													<c:out value="${libro.titolo}" />
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary"
														data-dismiss="modal">Annulla</button>
													<a type="button" class="btn btn-danger"
														href="${pageContext.request.contextPath}/delete/DeleteLibroServlet?idDaInviareComeParametro=
													<c:out value="${libro.id}"/>">Delete
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
				<c:if test="${empty requestScope.listaLibriAttribute}">
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