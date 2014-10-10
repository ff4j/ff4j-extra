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
       
         <c:choose>
         
        <c:when test="${not empty featuresbean}">
       	<li class="active"><a href="#">
        		<i class="icon-list-alt"></i>
        		<span><fmt:message key="navbar.features" /></span>
        	</a>
        <li class="active2">
          <a data-toggle="modal" href="#modalCreate" class="open-createDialog" >
            <i class="icon-plus" style="margin-left:10px"></i>
            <span>New Feature</span>
          </a>
        </li>
        <li class="active2">
           <a data-toggle="modal" href="#modalImport" class="open-ImportDialog" >
            <i class="icon-file" style="margin-left:10px"></i>
            <span>Import XML</span>
          </a>
        </li>
        <li class="active2">
          <a href="<c:url value='/features?op=export'/>">
            <i class="shortcut-icon icon-download-alt"></i>
            <span>Export XML</span>
          </a>
        </li>
        <li class="active2">
          <a data-toggle="modal" href="#modalToggle" class="open-ToggleGroupDialog" >
            <i class="shortcut-icon icon-th-list"></i>
            <span>Toggle Group</span>
          </a>
        </li>
        </c:when>
        <c:otherwise>
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
        </c:otherwise>
        
        </c:choose>
      </ul>
    </div>
    <!-- /container --> 
  </div>
  <!-- /subnavbar-inner --> 
</div>
<!-- /subnavbar -->