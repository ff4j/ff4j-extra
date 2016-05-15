// ---------------------
//     VIEW FEATURES
// ---------------------

function ff4j_displayMessage(msgType, msgInfo) {
	$('#message').html(
	 '<div class="alert alert-' + msgType + '" style="margin-left:50px;margin-right:20px">' +
      '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
      '<span style="font-style:normal">' + msgInfo + '</span></div>');
}

function ff4j_disable(flip) {
  $.ajax({
    type: 'GET',
    url: $(location).attr('href'),
    data : 'op=disable&uid=' + $(flip).attr('id'),
    dataType : 'html',
    success : function(code_html, statut){
    	ff4j_displayMessage("success", "Toggle OFF feature <b>" + $(flip).attr('id') + "</b>");
    },
    error : function(resultat, statut, erreur){
       displayMessage("error", statut + "-" + erreur);
 	   $(flip).prop('checked', false);
    },
    complete : function(resultat, statut){
    	console.log("Toggle OFF feature <b>" + $(flip).attr('id') + "</b>");
    }
  });
}

function ff4j_enable(flip) {
  $.ajax({
   type: 'GET',
   url: $(location).attr('href'),
   data : 'op=enable&uid=' + $(flip).attr('id'),
   dataType : 'html',
   success : function(code_html, statut){
     ff4j_displayMessage("success", "Toggle ON feature <b>" + $(flip).attr('id') + "</b>");
   },
   error : function(resultat, statut, erreur){
     displayMessage("error", statut + "-" + erreur);
	 $(flip).prop('checked', false);
   },
   complete : function(resultat, statut){
     console.log("Toggle ON feature <b>" + $(flip).attr('id') + "</b>");
   }
  });
}


function toggle(flip) {
  if(!$(flip).is(':checked')) {
    ff4j_disable(flip);    
  } else {
	ff4j_enable(flip);
  }
}

// ---------------------
//   MODAL EDIT FEATURE
// ---------------------

function ff4j_updateModalEditFeature(uid) {
	var modalEdit = $("#modalEdit");
	
	 $.get('api/features/' + uid,
    function(feature) {
	  modalEdit.find("#uid").val(feature.uid);
	  modalEdit.find("#desc").val(feature.description);
      
      // Group
      if (feature.group) {
       $("#modalEdit #groupName").val(feature.group);
      }
      
      // Strategy 
      if (feature.flippingStrategy) {
   	   $("#modalEdit #stratlist").show();
   	   $("#modalEdit #strategy").val(feature.flippingStrategy.type);
   	   for (var key in feature.flippingStrategy.initParams) {
   	    if (feature.flippingStrategy.initParams.hasOwnProperty(key)) {
   		     console.log(key + " -> " + feature.flippingStrategy.initParams[key]);
   		     $("#modalEdit #initParams").val(key + '=' + feature.flippingStrategy.initParams[key]);
   		}
   	  } 
      } else {
   	   $("#modalEdit #stratlist").hide();
   	   $("#modalEdit #strategy").val('');
       $("#modalEdit #initParams").val('');
      }
      
      // Permissions
      $("#modalEdit #permlist").hide();
      $("#modalEdit #permission").val('Public');
      if (feature.permissions) {
    	  $("#modalEdit #permlist").show();
    	  $("#modalEdit #permission").val('Define required roles...');
    	  ff4j_updatePermissionsEditFeature(feature.uid, feature.permissions);
      }
    });
}

function ff4j_updatePermissionsEditFeature(uid, permissions) {
   	var htmlForFixedValueList = '';
   	var arrayLength = permissions.length;
   	for (var i = 0; i < arrayLength; i++) {
   	 htmlForFixedValueList+= '<tr><td style="width:300px">' + permissions[i] + '</td><td>';
     htmlForFixedValueList+= '<a href="#" onclick="javascript:ff4j_removePermissions(\'' + permissions[i] + '\')" >';
     htmlForFixedValueList+= '<i class="icon-trash"></i></a></td></tr>';
    }
    htmlForFixedValueList+= '<tr><td style="width:300px"><input type="text" id="nnfix" name="nnfix"  style="width:250px;height:18px;"/></td>';
  	htmlForFixedValueList+= '    <td><a href="#" onclick="javascript:ff4j_createPermissionForFeature(\'' + uid + '\', $(\'#modalEdit #nnfix\').val())"';
  	htmlForFixedValueList+= '><i class="icon-plus" ></i></a></td></tr>';
  	$("#modalEditFeaturePermissions").html(htmlForFixedValueList);
}

function ff4j_createPermissionForFeature(uid, permName) {
	alert('add' + permName + ' for ' + uid);
	$.ajax({
		type: 'POST',
		url: $(location).attr('href') + 'api/features/uid',
		data : 'newPermission=' + permName,
		dataType : 'json',
		    success : function(newPermission, statut){
		    	ff4j_updatePermissionsEditFeature(newPermission);
		    },
		    error : function(resultat, statut, erreur){
		    },
		    complete : function(resultat, statut){
		    	console.log("Add new permission " + perName);
		    }
		  });
	 
}

function ff4j_removePermissionForFeature(uid, permName) {
	alert('remove' + permName + ' for ' + uid);
}


