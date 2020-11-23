<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>

<jsp:include page="./header.jsp" />

<!-- Custom styles for this template -->
<link href="./assets/css/global.css" rel="stylesheet">
<style type="text/css">
body {
	padding-top: 3.5rem;
}
</style>

<title>Gestione articoli</title>
</head>

<body>

	<jsp:include page="./navbar.jsp"></jsp:include>

	<main role="main">
		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<div class="container">
				<h1 class="display-3">
					Biblioteca WEB <br>
				</h1>
				<div class="display-4">Bentornato ${sessionScope.user.nome}!</div>
				<p>This is a template for a simple marketing or informational
					website. It includes a large callout called a jumbotron and three
					supporting pieces of content. Use it as a starting point to create
					something more unique.</p>
				<div class="row">
					<p>
						<a class="btn btn-success btn-lg" href="libro/cerca_libro.jsp"
							role="button">Cerca libro &raquo; </a>
					</p>
					&nbsp;
					<p>
						<a class="btn btn-success btn-lg" href="autore/cerca_autore.jsp"
							role="button">Cerca autore &raquo; </a>
					</p>
					&nbsp;
						<c:if test="${sessionScope.isAdmin eq true }">
							<p>
								<a class="btn btn-success btn-lg"
									href="utente/cerca_utente.jsp" role="button">Cerca
									utente &raquo; </a>
							</p>
						</c:if>

				</div>
			</div>
		</div>
		
		<div class="container">
			<!-- Example row of columns -->
			<div class="row">
				<div class="col-md-4">
					<h2>
						Guests
						<span class="badge badge-warning">${sessionScope.isGuest eq true ? '(You)' : '' }</span>

					</h2>
					<p>Sono limitati ad osservare.</p>
				</div>
				<div class="col-md-4">
					<h2>
						Classics
						<span class="badge badge-warning">${sessionScope.isClassic eq true ? '(You)' : '' }</span>
					</h2>
					<p>Gestiscono tutta la biblioteca.</p>
				</div>
				<div class="col-md-4">
					<h2>
						Administrators
						<span class="badge badge-warning">${sessionScope.isAdmin eq true ? '(You)' : '' }</span>
					</h2>
					<p>Gestiscono tutta la biblioteca, utenti compresi.</p>

				</div>
			</div>

			<hr>

		</div>
		<!-- /container -->
	</main>

	<jsp:include page="./footer.jsp" />
</body>
</html>