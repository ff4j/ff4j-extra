<%@ include file="/jsp-tiles/taglibs.jsp" %>


<div class="subnavbar">
  <div class="subnavbar-inner">
    <div class="container">
      <ul class="mainnav">
        <li class="active">
        	<a href="<c:url value='/home'/>">
        		<i class="icon-dashboard"></i>
        		<span>Dashboard</span>
        	</a>
        </li>
        <li>
        	<a href="<c:url value='/features'/>">
        		<i class="icon-list-alt"></i>
        		<span>Features</span>
        	</a>
        </li>
        <li>
        	<a href="<c:url value='/stats'/>">
        		<i class="icon-bar-chart"></i>
        		<span>Monitoring</span>
        	</a>
        </li>
        <li>
        	<a href="<c:url value='/settings'/>">
        		<i class="icon-cog"></i>
        		<span>Settings</span>
        	</a>
        </li>
      </ul>
    </div>
    <!-- /container --> 
  </div>
  <!-- /subnavbar-inner --> 
</div>
<!-- /subnavbar -->