<%@ include file="/jsp-tiles/taglibs.jsp" %>

<!-- ******************* -->
<!-- ** EDIT FORM ** -->
<!-- ******************* -->

<form class="form-horizontal" action="<c:url value='/features'/>" method="POST" >
  <div class="modal hide fade" id="modalEdit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    
    <div class="modal-header">
      <button class="close" data-dismiss="modal">x</button>
      <h4 id="myModalLabel">Edit Feature</h4>
    </div>
    
  <!-- Body -->
  <div class="modal-body" style="height:500px">
    
    <!-- Name --> 
    <div class="control-group"> 
      <label class="control-label" for="uid" style="color:#00ab8b;font-style:normal;font-weight:normal">Feature Name</label>
      <div class="controls">   
        <input type="text" name="uid" id="uid" style="width:250px;height:30px;" required/>  
      </div> 
    </div> 

    <!-- Description --> 
    <div class="control-group">  
      <label class="control-label" for="desc" style="color:#00ab8b;font-style:normal">Description</label>  
      <div class="controls">
        <textarea class="field span4" name="desc" id="desc" rows="3"></textarea>
      </div>
    </div>

    <!-- Group --> 
    <div class="control-group">  
      <label class="control-label" for="group" style="color:#00ab8b;font-style:normal">Group</label>  
        <div class="controls">
          <div class="btn-group">
            <input type="text" name="groupName" id="groupName" style="width:250px;height:30px;" />
            <button type="button" class="btn btn-green dropdown-toggle" data-toggle="dropdown" style="float:right;margin-right:79px">
              <span class="caret"></span>
              <span class="sr-only"><i class="icon-inbox icon-white" ></i></span>
            </button>
            <ul class="dropdown-menu" role="menu" style="width:250px;font-style:normal;color:#696969">
             	<c:forEach items="${featuresbean.groupList}" var="group">
 					<li><a href="javascript:$('#modalEdit #groupName').val('${group}');">${group}</a></li>
 				</c:forEach>
              <li class="divider"></li>
              <li><a href="javascript:$('#groupName').val('');">None</a></li>
            </ul>
          </div> <!-- btn-group -->     
        </div> <!-- controls -->
      </div> <!-- control-group -->

    <!-- Strategy --> 
    <div class="control-group">  
      <label class="control-label" for="strategy" style="color:#00ab8b;font-style:normal">Strategy</label>  
      <div class="controls">
        <div class="btn-group">
            <input type="text" name="strategy" id="strategy" style="width:250px;height:30px;font-style:normal" >
            <button type="button" class="btn btn-green dropdown-toggle" data-toggle="dropdown" style="float:right;margin-right:79px">
              <span class="caret"></span>
              <span class="sr-only"><i class="icon-cog icon-white" ></i></span>
            </button>
            <ul class="dropdown-menu" role="menu" style="width:250px;">
                <li><a href="javascript:
                	$('#modalEdit #strategy').val('org.ff4j.strategy.el.ExpressionFlipStrategy');
                	$('#modalEdit #stratlist').show();
                	$('#modalEdit #initParams').val('expression=dummy OR foo')">Expression</a>
                </li>
              <li><a href="javascript:
              		$('#modalEdit #strategy').val('org.ff4j.strategy.PonderationStrategy');
              		$('#modalEdit #stratlist').show();
              		$('#modalEdit #initParams').val('weight=0.5')">Ponderation</a></li>
                <li><a href="javascript:
               		$('#modalEdit #strategy').val('org.ff4j.strategy.ReleaseDateFlipStrategy');
               		$('#modalEdit #stratlist').show();
               		$('#modalEdit #initParams').val('releaseDate=2013-07-14-14:00')">ReleaseDate</a></li>
              <li><a href="javascript:
              		$('#modalEdit #strategy').val('org.ff4j.strategy.ClientFilterStrategy');
              		$('#modalEdit #stratlist').show();
              		$('#modalEdit #initParams').val('grantedClients=c1,c2,c3')">ClientFilter</a></li>
              <li><a href="javascript:
              		$('#modalEdit #strategy').val('org.ff4j.strategy.ServerFilterStrategy');
              		$('#modalEdit #stratlist').show();
              		$('#modalEdit #initParams').val('grantedServers=s1,s2,s3')">ServerFilter</a></li>
              <li class="divider"></li>
              <li><a href="javascript:
              		$('#modalEdit #strategy').val('');
              		$('#modalEdit #stratlist').hide();
              		$('#modalEdit #startparam').val('')">None</a></li>
            </ul>
          </div>
        </div>
         <div id="stratlist" class="controls hide" style-"font-style:normal">
        <p/><br/><span style="color:#00ab8b"><i class="icon-star"></i>&nbsp;Please Give init param :
          <br><input type="text" name="initParams" id="initParams" style="width:250px;height:30px;font-style:normal">
      </div>
      </div>

    <!-- Permissions -->
    <c:if test="${not empty featuresbean.htmlPermissions}">
    <div class="control-group">  
      <label class="control-label" for="permission" style="color:#00ab8b;font-style:normal">Permissions</label>  
      <div class="controls">
        <div class="btn-group">
            <input type="text" name="permission" id="permission" 
            	style="width:250px;height:30px;font-style:normal" value="Public" readonly="readonly">
            <button type="button" class="btn btn-green dropdown-toggle" data-toggle="dropdown" style="float:right;margin-right:79px">
              <span class="caret"></span>
              <span class="sr-only"><i class="icon-user icon-white" ></i></span>
            </button>
            <ul class="dropdown-menu" role="menu" style="width:250px;font-style:normal">
                <li><a href="javascript:$('#modalEdit #permission').val('Public');$('#modalEdit #permlist').hide();"><i class="icon-flag" ></i>&nbsp;Public</a></li>
                <li><a href="javascript:$('#modalEdit #permission').val('Restricted');$('#modalEdit #permlist').show();"><i class="icon-lock" ></i>&nbsp;Restricted</a></li>
            </ul>
        </div>
      </div>
      <div id="permlist" class="controls hide" style="font-style:normal">
        <p/><br/><span style="color:#00ab8b"><i class="icon-list"></i>&nbsp;Please select your permissions :</span>
        ${featuresbean.htmlPermissions}
      </div>
     </div><!-- Control group -->
     </c:if>
     
    </div> <!-- Modal-body -->
    
    <div class="modal-footer">
      <button class="btn btn" data-dismiss="modal"><i class="icon-remove" ></i>&nbsp;Cancel</button>
      <button class="btn btn-green" type="submit"><i class="icon-refresh icon-white" ></i>&nbsp;Update </button>
    </div>

     <script type="text/javascript" >
      $(document).on("click", ".open-EditFlipDialog", function () {
        $("#modalEdit #uid").val($(this).data('id'));
        $("#modalEdit #desc").val($(this).data('desc'));
        $("#modalEdit #groupName").val($(this).data('group'));
        
        /* --------- */
        /* Strategy  */
        /* --------- */
        var strategy = $(this).data('strategy');
        $("#modalEdit #strategy").val(strategy);
        if (strategy == '') {
          $("#modalEdit #stratlist").hide();
          $("#modalEdit #initParams").val('');
        } else {
          $("#modalEdit #stratlist").show();
          var initParams = $(this).data('stratparams').replace("{", "").replace("}", "");
          $("#modalEdit #initParams").val(initParams);
        }
        
        /* ------------ */
        /* Permissions. */
        /* ------------ */
        var permissions = $(this).data('permissions').replace("[", "").replace("]", "");
        if (permissions == '') {
          $("#modalEdit #permlist").hide();
          $("#modalEdit #permission").val('Public');
        } else {
          $("#modalEdit #permission").val('Restricted');
          $("#modalEdit #permlist").show();
       	  // Split permissions
       	  var arrayOfPermissions = permissions.split(',');
          for(perm in arrayOfPermissions) {
        	$("#modalEdit #perm-check-" + arrayOfPermissions[perm].trim()).attr('checked', true);
          }
        }
        
        });
      </script>
      
    <input type="hidden" name="op" value="update"  />
  </div>
</form>