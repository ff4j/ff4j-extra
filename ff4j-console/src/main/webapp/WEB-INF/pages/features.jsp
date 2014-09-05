<%@ include file="/jsp-tiles/taglibs.jsp" %>
<script>
$(document).ready(function() {
    // put your jQuery code here.
	$( "#li-features" ).addClass( "active");
	$( "#li-dashboard" ).removeClass( "active");
	$( "#li-settings" ).removeClass( "active");
	$( "#li-monitoring" ).removeClass( "active");
});
</script>
<div class="main-inner">
		<div class="container">
			<div class="row">

<c:if test="${not empty featuresbean.message}">
 <div class="alert alert-${featuresbean.messageType}" style="margin-left:50px;margin-right:20px">
  <button type="button" class="close" data-dismiss="alert">&times;
  </button>
  <span style=font-style:normal;color:#696969;">${featuresbean.message}</span>
 </div>
</c:if>
 
				<div class="span12">

					<!--  General Informations -->
					<div class="widget widget-nopad">

						<div class="widget-header">
							<i class="icon-home"></i>
							<h3>Features</h3>
						</div>

						<div class="widget-content">
							<div class="widget-content">
								<table class="table table-striped table-bordered">
     <thead>
      <tr>
       <th style="width:20%;text-align:center">Feature</th>
       <th style="width:20%;text-align:center">Group</th>
       <th style="width:25%;text-align:center">Permissions</th>
       <th style="width:25%;text-align:center">Strategy</th>
       <th style="width:8%;text-align:center">Toggle</th>
       <th style="width:5%;text-align:center">E</th>
       <th style="width:5%;text-align:center">D</th>
      </tr>
    </thead>
    <tbody> 
     <c:forEach items="${featuresbean.listOfFeatures}" var="feat">
     	 <tr>
		<td><a class="ff4j-tooltip"  tooltip="${feat.description}">${feat.uid}</a></td>
		<td><c:out value="${feat.group}">--</c:out></td>
		<td>
		 <c:forEach items="${feat.permissions}" var="perm" varStatus="permstatus">
		 	<c:out value="${permstatus.first ? '' : ','}${perm}"></c:out>
		 </c:forEach>
		</td>
		<td><c:out value="${feat.flippingStrategy}">--</c:out></td>
		<td style="width:8%;text-align:center">
			<label class="switch switch-green">
			 <c:choose>
				<c:when test="${feat.enable}">
					<input id="${feat.uid}" type="checkbox" class="switch-input" onclick="javascript:toggle(this)" checked>
				</c:when>
				<c:otherwise>
					<input id="${feat.uid}" type="checkbox" class="switch-input" onclick="javascript:toggle(this)" >
				</c:otherwise>
			</c:choose>
				
				<span class="switch-label" data-on="On" data-off="Off"></span>
				<span class="switch-handle"></span>
			</label>
		</td>
		<td style="width:5%;text-align:center">
		<a data-toggle="modal" href="#modalEdit" data-id="${feat.uid}" 
				data-desc="${feat.description}" 
				data-group="${feat.group}" 
				data-strategy="${feat.flippingStrategy.class.name}" 
				data-stratparams="${feat.flippingStrategy.initParams}" 
				data-permissions="${feat.permissions}" 
				style="width:6px;" class="open-EditFlipDialog btn">
			<i class="icon-pencil" style="margin-left:-5px;"></i>
		</a>
		</td>
		<td style="width:5%;text-align:center">
			<a href="<c:url value='/features?op=delete&uid=${feat.uid}'/>" style="width:6px;" class="btn">
			<i class="icon-trash" style="margin-left:-5px;"></i>
			</a>
		</td>
		</tr>
     </c:forEach>
      
    </tbody>
   </table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
<%@ include file="features-modal-create.jsp" %>

<%@ include file="features-modal-edit.jsp" %>

<%@ include file="features-modal-import.jsp" %>



<!-- Toggle -->
<script type="text/javascript">
 function toggle(flip) {        
    if(!$(flip).is(':checked')) {
       $.get($(location).attr('href') + '?op=disable&uid=' + $(flip).attr('id'),
             function(responseText) { 
                  $('#message').html(responseText);         
              });
    } else {
      $.get($(location).attr('href') + '?op=enable&uid=' + $(flip).attr('id'),
             function(responseText) { 
                  $('#message').html(responseText);         
              });
    }
  }
</script>

	