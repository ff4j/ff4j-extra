<%@ include file="/jsp-tiles/taglibs.jsp" %>


<!-- ******************* -->
<!-- ** IMPORT MODAL  ** -->
<!-- ******************* -->
<form class="form-horizontal" action="<c:url value='/clearCache.do'/>" method="POST" enctype="multipart/form-data" >

  <div class="modal hide fade" id="modalClearCache" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    
    <div class="modal-header">   
      <button class="close" data-dismiss="modal">x</button>   
      <h4 id="myModalLabel"><i class="icon-file icon-white"></i>&nbsp;Clear Cache</h3>
    </div>
    
      <div class="modal-body">
        <c:choose>
	      	<c:when test="${homebean.cache eq true}">
	      		<p> Please find below features within cache : </p>
	      		<table class="table table-striped table-bordered" style="font-size:12px">
	      		 <c:forEach items="${homebean.cacheFeature}" var="feature" >
	      		 <tr style="height:12px">
					<td><a class="ff4j-tooltip"  tooltip="${feature}">${feature}</a></td>
				</tr>
		 		</c:forEach>
		 		</table>
	      	</c:when>
	      	<c:otherwise>
	      		 <p/>No cache provided</p>
	      	</c:otherwise>
      	</c:choose>
    </div>
    
    <div class="modal-footer">
      <button class="btn btn" data-dismiss="modal"><i class="icon-remove" ></i>&nbsp;Cancel</button>
      <c:if test="${homebean.cache eq true}">
      	<button class="btn btn-green" type="submit"><i class="icon-magic icon-white" ></i>&nbsp;ClearCache </button>
      </c:if>
   </div>

  </div>
</form>