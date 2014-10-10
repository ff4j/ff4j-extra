<%@ include file="/jsp-tiles/taglibs.jsp" %>

<!-- ******************* -->
<!-- ** TOGGLE GROUP  ** -->
<!-- ******************* -->
<form class="form-horizontal" action="<c:url value='/features'/>" method="POST" name="toggleGroupForm" id="toggleGroupForm">

  <div class="modal hide fade" id="modalToggle" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    
    <div class="modal-header">   
      <button class="close" data-dismiss="modal">x</button>   
      <h4 id="myModalLabel"><i class="icon-th-list icon-white"></i>&nbsp;Toggle a group of features</h3>
    </div>

    <div class="modal-body" style="height:250px">

    <!-- Group --> 
    <div class="control-group">  
     <label class="control-label" for="groupName" style="color:#00ab8b;font-style:normal">GroupName</label>
        <div class="controls">
          <div class="btn-group">
            <input type="text" name="groupName" id="groupName" style="width:250px;height:30px;" readonly="readonly" />
            <button type="button" class="btn btn-green dropdown-toggle" data-toggle="dropdown" style="float:right;margin-right:79px">
              <span class="caret"></span>
              <span class="sr-only"><i class="icon-inbox icon-white" ></i></span>
            </button>
            <ul class="dropdown-menu" role="menu" style="width:250px;font-style:normal;color:#696969">
            	<c:forEach items="${featuresbean.groupList}" var="group">
 					<li><a href="javascript:$('#modalToggle #groupName').val('${group}');">${group}</a></li>
 				</c:forEach>
            
              <li class="divider"></li>
              <li><a href="javascript:$('#modalToggle #groupName').val('');">None</a></li>
            </ul>
          </div> <!-- btn-group -->     
        </div> <!-- controls -->
      </div> <!-- control-group -->
      
       <div class="control-group">  
      <label class="control-label" for="ope" style="color:#00ab8b;font-style:normal">Operation</label>  
      <div class="controls">
        <div class="btn-group">
            <input type="text" name="ope" id="ope" style="width:250px;height:30px;font-style:normal" readonly="readonly">
            <button type="button" class="btn btn-green dropdown-toggle" data-toggle="dropdown" style="float:right;margin-right:79px">
              <span class="caret"></span>
              <span class="sr-only"><i class="icon-cog icon-white" ></i></span>
            </button>
            <ul class="dropdown-menu" role="menu" style="width:250px;">
                <li><a href="javascript:$('#modalToggle #ope').val('enable');">enable</a></li>
                <li><a href="javascript:$('#modalToggle #ope').val('disable');">disable</a></li>
            </ul>
          </div>
        </div>
        </div>
    </div>
    
    <div class="modal-footer">
      <button class="btn btn" data-dismiss="modal"><i class="icon-remove" ></i>&nbsp;Cancel</button>
      <button class="btn btn-green" type="submit" ><i class="icon-check icon-white" ></i>&nbsp;Validate</button>
    </div>
    
    
   <input type="hidden" name="op" value="toggleGroup"  />

  </div>
</form>