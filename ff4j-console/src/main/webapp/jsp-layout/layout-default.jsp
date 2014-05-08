<%@ include file="/includes.jsp"%>
<html>
<head>
  <%@ include file="/common/header.jsp" %>
  <decorator:head/>
   <title><fmt:message key="webapp.name"/></title>
</head>

<body>

	 <!-- Bandeau avec logout, alert et profile -->
	 <%@ include file="/common/navbar.jsp" %>
	 
	 <div id="wrapper" class="container">
   		<div class="row">

  		<!-- Barre de menu gauche -->
  		<%@ include file="/common/sidebar.jsp"%>
  
   		<!--content area -->
   		<div class="span9"> 
    		<div id="content" class="clearfix" style="padding-top:20px">
	 			<decorator:body/>
     		</div>
  		</div>

     <!-- Footer -->
     <%@ include file="/common/footer.jsp"%>
   
</body>
</html>


	
