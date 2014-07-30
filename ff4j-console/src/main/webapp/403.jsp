<%@ include file="/jsp-tiles/taglibs.jsp"%>
<html>
<head>
<%@ include file="/jsp-tiles/header.jsp"%>
<title><fmt:message key="webapp.name" /></title>
</head>
<body>

	<!-- Bandeau avec logout, alert et profile -->
	<%@ include file="/jsp-tiles/navbar.jsp"%>

	<div class="main-inner">
		<div class="container">
			<div class="row">

				<div class="span12">

					<!--  General Informations -->
					<div class="widget widget-nopad">

						<div class="widget-header">
							<i class="icon-home"></i>
							<h3>Error 403 : Forbidden !</h3>
						</div>

						<div class="widget-content">
							<div class="widget-content">
								<img src="<c:url value='/img/403_man.jpg' />">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- extra -->
	<%@ include file="/jsp-tiles/extra.jsp"%>

	<!-- Footer -->
	<%@ include file="/jsp-tiles/footer.jsp"%>

</body>
</html>
