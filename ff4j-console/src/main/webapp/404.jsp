<%@ include file="/common/taglibs.jsp" %>
<head>
    <title>404 | Page not found</title>
    <%@ include file="/common/header.jsp" %>
</head>
<body class="loginPage" id="login">

	<div class="loginContainer">
		<form class="form-horizontal" method="post" id="loginForm"
			  action="<c:url value='/j_security_check'/>"
			  autocomplete="off"
    		  onsubmit="saveUsername(this);return validateForm(this)" >

			<div class="form-row row-fluid">
				<div class="span12">
					<h2 >
						<img src="<c:url value='/images/404_man.jpg' />" >
					</h2>
				</div>
			</div>

			<div class="form-row row-fluid" >
				<div class="span12">
					<div class="row-fluid">
						<div class="form-actions">
							<div class="span12 controls" >
							<center>
								<a href="<c:url value='mainMenu'/>" class="btn btn-danger" >
								 	<i class="icon-home icon-white"></i>
								 	Back to HOME
								</a>
							</center>
                            </div>
                        </div>
					</div>
				</div>
			</div>

		</form>

	</div>

<c:set var="scripts" scope="request">
	<script type="text/javascript">
		<%@ include file="/scripts/login.js"%>
	</script>
</c:set>

</body>