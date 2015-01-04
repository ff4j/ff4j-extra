<%@ include file="/jsp-tiles/taglibs.jsp"%>
<html>
<head>
<%@ include file="/jsp-tiles/header.jsp"%>
<title>ERROR - <fmt:message key="webapp.name" /></title>
</head>
<body>

	<div class="main-inner">
		<div class="container">
			<div class="row">
				<div class="span12">
					<div class="widget widget-nopad">
						<div class="widget-header">
							<i class="icon-warning-sign"></i>
							<h3 style="color: #880000">
								500 -
								<fmt:message key="errorPage.title" />
							</h3>
						</div>

						<div class="widget-content" style="padding:10px">
							<p><fmt:message key="errorPage.message" />
							<p>
							<a href="<c:url value='/home.do'/>" class="btn btn-warning" style="float:right;margin-top:-25px">
									<i class="icon-home icon-white"></i>&nbsp;&nbsp;&nbsp;Back to Home
								</a>
								</p>
							<p><b>Error Message : </b>
							<span style="color: RED">${errorMsg}</span>
							<p><b>Error Stack : </b>
							<br><span style="color:#687684">${errorStack}</span>
							</p>
						</div>
					</div>
				</div> <!-- span -->
			</div>
		</div>
	</div>
	
	