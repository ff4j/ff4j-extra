<%@ include file="/jsp-tiles/taglibs.jsp"%>
<html>
<head>
  <%@ include file="/jsp-tiles/header.jsp" %>
  <decorator:head/>
   <title><fmt:message key="webapp.name"/></title>
</head>
<body>

	 <!-- Bandeau avec logout, alert et profile -->
	 <%@ include file="/jsp-tiles/navbar.jsp" %>
	 
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
   
</body>
</html>


	
