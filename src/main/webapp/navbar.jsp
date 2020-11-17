<!-- navbar -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-success">

	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarsExampleDefault" aria-controls="navbarCollapse"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarsExampleDefault">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active">
				<a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">Home
					<span class="sr-only">(current)</span>
				</a>
			</li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="dropdown01"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Articoli</a>
				<div class="dropdown-menu" aria-labelledby="dropdown01">
					<a class="dropdown-item" href="ListArticoliServlet">Lista articoli</a> 
						<a class="dropdown-item" href="articolo/cerca_articolo.jsp">Cerca articolo</a> 
						<c:if test = "${sessionScope.user.ruolo eq 'admin' || sessionScope.user.ruolo eq 'operator'}">
							<a class="dropdown-item" href="PrepareInsertArticoloServlet">Inserisci articolo</a> 
						</c:if>
				</div>
			</li>
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="dropdown01"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Categorie
				</a>
				<div class="dropdown-menu" aria-labelledby="dropdown01">
						<a class="dropdown-item" href="ListCategorieServlet">Lista categorie</a> 
						<a class="dropdown-item" href="categoria/cerca_categoria.jsp">Cerca categoria</a> 
						<c:if test = "${sessionScope.user.ruolo eq 'admin' || sessionScope.user.ruolo eq 'operator'}">
							<a class="dropdown-item" href="PrepareInsertCategoriaServlet">Inserisci categoria</a>
						</c:if>
				</div>
			</li>
			<li class="nav-item">
			<a class="nav-link" >
				${sessionScope.user.nome} ${sessionScope.user.cognome}
				${sessionScope.user.codice_fiscale}
			</a>
			</li>

		</ul>
		<form class="form-inline my-2 my-lg-0">
			<a class="btn btn-outline-light my-2 my-sm-0" href="LogoutServlet">Sign out</a>
		</form>

	</div>
</nav>
