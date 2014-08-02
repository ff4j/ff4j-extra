<%@ include file="/jsp-tiles/taglibs.jsp" %>


<div class="subnavbar">
  <div class="subnavbar-inner">
    <div class="container">
      <ul class="mainnav">
        <li id="li-dashboard" class="active">
        	<a href="<c:url value='/home'/>">
        		<i class="icon-dashboard"></i>
        		<span><fmt:message key="navbar.dashboard" /></span>
        	</a>
        </li>
        <li id="li-features">
        	<a href="<c:url value='/features'/>">
        		<i class="icon-list-alt"></i>
        		<span><fmt:message key="navbar.features" /></span>
        	</a>
        </li>
        <li id="li-monitoring">
        	<a href="<c:url value='/stats'/>">
        		<i class="icon-bar-chart"></i>
        		<span><fmt:message key="navbar.monitoring" />
        	</a>
        </li>
        <li id="li-settings" >
        	<a href="<c:url value='/settings'/>">
        		<i class="icon-cog"></i>
        		<span><fmt:message key="navbar.settings" /></span>
        	</a>
        </li>
      </ul>
    </div>
    <!-- /container --> 
  </div>
  <!-- /subnavbar-inner --> 
</div>
<!-- /subnavbar -->