<%@ include file="/jsp-tiles/taglibs.jsp"%>
<html>
<head>
  <%@ include file="/jsp-tiles/header.jsp" %>
  <decorator:head />
</head>
<body>

	 <!-- Bandeau avec logout, alert et profile -->
	 <%@ include file="/jsp-tiles/navbar.jsp" %>
	 
	 <!-- Menu -->
	 <c:if test="${not empty envbean.envId}">
	 <%@ include file="/jsp-tiles/subnavbar.jsp" %>
	 </c:if>
	 
	 <!-- Contenu de la page -->
	 <div id="wrapper" class="container" >
   		<div class="row">
   		<div class="span9"> 
    		<div id="content" class="clearfix" >
	 			<decorator:body/>
     		</div>
  		</div>
  	   </div>
  	 </div>
  	 
	 <!-- extra -->
 	 <%@ include file="/jsp-tiles/extra.jsp"%>
 
     <!-- Footer -->
     <%@ include file="/jsp-tiles/footer.jsp"%>

<form action="<c:url value='/home'/>" id="envchange" method="POST" >
	<input type="hidden" id="env" name="env" value="" />
</form>

</body>
</html>


	
