<%@ include file="/jsp-tiles/taglibs.jsp" %>


<!-- ************** Navigation BAR ************** -->

<div class="navbar navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container">
    	
    	<!-- Button if width is no more sufficient (collapse) -->
    	<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"  style="margin-top:15px">
    		<span class="icon-bar"></span>
    		<span class="icon-bar"></span>
    		<span class="icon-bar"></span>
    	</a>
    	
    	<!-- Link to Home -->
        <a class="brand" href="/home.do" >
        	<img src="./img/icons/ff4j.png" style="height:40px">
        	&nbsp;&nbsp;&nbsp;Administration Console 
        		@&nbsp;<span style="color:#9ce1cd"><c:out value="${sessionScope.envbean.envId}" > -- </c:out></span>
        </a>
        
        <!-- Right part of the nav bar -->
        <div class="nav-collapse"  style="margin-top:15px">
	       <ul class="nav pull-right">
	       
	      <!-- Environnement already selected but several available -->
          <c:if test="${fn:length(envbean.listOfConnection) gt 1 && not empty envbean.envId}">
	          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">
	           <img src="./img/icons/switch2.png" style="height:20px">&nbsp; 
	           	<c:out value="${envbean.envId}"/><b class="caret"></b></a>
	            <ul class="dropdown-menu">
	            	 <c:forEach items="${envbean.listOfConnection}" var="conn">
	            	 	<li><a href="javascript:$('#env').val('${conn.id}');$('#envchange').submit()">
	            	 	<img src="./img/icons/switch.png" style="height:15px">
	            	 	&nbsp;${conn.id}</a></li>
				     </c:forEach>
	            </ul>
	          
	          </li>
          </c:if>
          
          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">
          	<c:if test="${pageContext.response.locale == 'en'}">
          	<img src="./img/icons/flag/english.png" style="height:20px"> 
          </c:if>
           <c:if test="${pageContext.response.locale == 'fr'}">
          	<img src="./img/icons/flag/france.png" style="height:20px"> 
          </c:if>
          <c:if test="${pageContext.response.locale == 'de'}">
          	<img src="./img/icons/flag/germany.png" style="height:20px"> 
          </c:if>
            <fmt:message key="navbar.language" />
          	<b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="?lang=en"><img src="./img/icons/flag/english.png" style="height:15px">&nbsp;English</a></li>
              <li><a href="?lang=fr"><img src="./img/icons/flag/france.png" style="height:15px">&nbsp;Français</a></li>
              <li><a href="?lang=de"><img src="./img/icons/flag/germany.png" style="height:15px">&nbsp;Deutsch</a></li>
            </ul>
          </li>
          
           <li class="dropdown">
          <a href="<c:url value="j_spring_security_logout" />" > 
            <i class="icon-remove icon-white"></i> <fmt:message key="navbar.logout" /></a>
          </li>
          
          </li>
        </ul>

      	</div>
      	<!--/.nav-collapse -->
    </div>
    <!-- /container --> 
  </div>
  <!-- /navbar-inner --> 
</div>
<!-- /navbar -->
