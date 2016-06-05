// ---------------------
//     VIEW FEATURES
// ---------------------

// Dynamically display a message in any view
function ff4j_displayMessage(msgType, msgInfo) {
	$('#message').html(
	 '<div class="alert alert-' + msgType + '" style="margin-left:50px;margin-right:20px">' +
      '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
      '<span style="font-style:normal">' + msgInfo + '</span></div>');
}

// Toggle OFF a feature through AXAJ call
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

// Toggle ON a feature through AXAJ call
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

// Triggered by clic, toggle ON/OFF depending on state
function toggle(flip) {
  if(!$(flip).is(':checked')) {
    ff4j_disable(flip);    
  } else {
	ff4j_enable(flip);
  }
}

// Display Custom Properties for current feature in the table
function showCustomProperties(uid) {
  console.log(uid);
  show('detailCustomP-' + uid);
  show('linkhideCustomP-' + uid);
  hide('linkCustomP-' + uid);
}

	function hideCustomProperties(uid) {
	 hide('detailCustomP-' + uid);
	 hide('linkhideCustomP-' + uid);
	 show('linkCustomP-' + uid);
	}

	function showFeaturePermissions(uid) {
	 show('detailPerm-' + uid);
	 show('linkhidePerm-' + uid);
	 hide('linkPerm-' + uid);	
	}

	function hideFeaturePermissions(uid) {
	 hide('detailPerm-' + uid);
	 hide('linkhidePerm-' + uid);
	 show('linkPerm-' + uid);
	}

// ---------------------
//  MODAL EDIT FEATURE
// ---------------------

// At Load, fill edit form with Data coming from REST Call
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
   		     //console.log(key + " -> " + feature.flippingStrategy.initParams[key]);
   		     $("#modalEdit #initParams").val(key + '=' + feature.flippingStrategy.initParams[key]);
   		}
   	  } 
      } else {
   	   $("#modalEdit #stratlist").hide();
   	   $("#modalEdit #strategy").val('');
       $("#modalEdit #initParams").val('');
      }
      
      // Permissions
      ff4j_drawPermissions(feature.uid);
      
      // CustomProperties
      ff4j_drawCustomProperties(feature.uid);
      
    });
}

// Display the permissions as dynamically changed in modal
function ff4j_drawPermissions(uid) {
  $("#modalEdit #permlist").hide();
  $("#modalEdit #permission").val('Public');
  $.get('api/features/' + uid,
    function(feature) {
	  var htmlForFixedValueList = '';
	  if (feature.permissions) {
		var permissions = feature.permissions;
		var arrayLength = permissions.length;
		if (arrayLength > 0) {
		  $("#modalEdit #permlist").show();
		  $("#modalEdit #permission").val('Define Permissions');
		  for (var i = 0; i < arrayLength; i++) {
			htmlForFixedValueList+= '<tr><td style="width:300px">' + permissions[i] + '</td><td>';
			htmlForFixedValueList+= '<a href="#" onclick="javascript:ff4j_removePermissionForFeature(\'' + uid + '\', \'' + permissions[i] + '\')" >';
			htmlForFixedValueList+= '<i class="icon-trash"></i></a></td></tr>';
		  }
		}
	  }
	  htmlForFixedValueList+= '<tr><td style="width:300px" colspan="2"><input type="text" id="nnfix" name="nnfix"  style="width:200px;height:18px;"/>';
	  htmlForFixedValueList+= '    <a class="color:#00ab8b" href="#" onclick="javascript:ff4j_createPermissionForFeature(\'' + uid + '\', $(\'#modalEdit #nnfix\').val())"';
	  htmlForFixedValueList+= '><i class="icon-plus" ></i></a></td></tr>';
	  $("#modalEditFeaturePermissions").html(htmlForFixedValueList);
	 });
}

// Triggered on '+' button in the permission table
function ff4j_createPermissionForFeature(uid, permName) {
  $.ajax({
    type : 'GET',
	url : $(location).attr('href'),
	data : 'op=addPermission&uid=' + uid + '&permission=' + permName,
	dataType : 'html',
	success : function(permissionList, statut) { 
		ff4j_drawPermissions(uid);
		$("#modalEdit #permlist").hide();
		$("#modalEdit #permission").val('No Permissions');
	},
	error : function(resultat, statut, erreur) {},
	complete : function(resultat, statut) {	console.log("Delete new permission " + perName);}
  });
}

// Triggered when picking "Public" in permission dropdown
function ff4j_clearPermissionsForFeature(uid) {
 $.ajax({
   type : 'GET',
   url : $(location).attr('href'),
   data : 'op=clearPermissions&uid=' + uid,
   dataType : 'html',
   success : function(permissionList, statut) { ff4j_drawPermissions(uid);},
   error : function(resultat, statut, erreur) {},
   complete : function(resultat, statut) {	console.log("Delete new permission " + perName);}
  });
 
}

// Triggered when deleting a dedicated permission
function ff4j_removePermissionForFeature(uid, permName) {
  $.ajax({
    type: 'GET',
	url: $(location).attr('href'),
	data : 'op=deletePermission&uid=' + uid + '&permission=' + permName,
	dataType : 'html',
	success : function(permissionList, statut) { ff4j_drawPermissions(uid); },
    error : function(resultat, statut, erreur) { },
    complete : function(resultat, statut){  console.log("Delete new permission " + perName); }
  });
}

// Display inner permissions
function ff4j_drawCustomProperties(uid) {
  $.get('api/features/' + uid,
    function(feature) {
	  var htmlForProperties = '';
	  if (feature.customProperties) {
	    for (var key in feature.customProperties) {
	      if (feature.customProperties.hasOwnProperty(key)) {
	        htmlForProperties+= '<tr><td style="width:300px">' + feature.customProperties[key].name + '=' + feature.customProperties[key].value + '</td><td>';
			htmlForProperties+= '<a href="#" onclick="javascript:ff4j_editPropertiesForFeature(\'' + uid + '\', \'' + feature.customProperties[key].name + '\')" >';
			htmlForProperties+= '<i class="icon-pencil"></i></a></td><td>';
			htmlForProperties+= '<a href="#" onclick="javascript:ff4j_removePropertiesForFeature(\'' + uid + '\', \'' + feature.customProperties[key].name + '\')" >';
			htmlForProperties+= '<i class="icon-trash"></i></a></td></tr>';
	      }
		}
	  }
	  htmlForProperties+= '<tr><td style="width:300px" colspan="3">';
	  htmlForProperties+= '<a data-toggle="modal" href="#modalCreateProperty" class="open-createPropertyDialog color:#00ab8b" href="#" data-featureid="' + uid + '" >';
	  htmlForProperties+= '<i class="icon-plus" ></i>&nbsp; News</a></td></tr>';
	  $("#modalEditFeatureProperties").html(htmlForProperties);
  });
}

// --> Operations on feature custom properties
function ff4j_editPropertiesForFeature(uid, propName) {
	alert("EDIT PROPERTY " + propName);
}
function ff4j_removePropertiesForFeature(uid, propName) {
	alert("RMV PROPERTY " + propName);
}
function ff4j_createPropertiesForFeature(uid, propName) {
	alert("CREATE PROPERTY " + propName);
}
// <--

//---------------------
// MODAL EDIT PROPERTY
//---------------------

function ff4j_updateModalEditProperty(name) {
  var modalEditProperty = $("#modalEditProperty");
  $.get('api/properties/' + name, 
    function(myProperty) {
	  modalEditProperty.find("#uid").val(myProperty.name);
	  modalEditProperty.find("#name").val(myProperty.name);
	  modalEditProperty.find("#desc").val(myProperty.description);
	  modalEditProperty.find("#pType").val(myProperty.type);
	  modalEditProperty.find("#pValue").val(myProperty.value);
      ff4j_drawFixedValues(myProperty.name);
 	});
}

function ff4j_drawFixedValues(name) {
  $("#modalEditPropertyFixedValues").html('');
  $.get('api/properties/' + name, 
    function(myProperty) {
	  if (myProperty.fixedValues) {
	 	var htmlForValues = '<div class="btn-group">';
	 	htmlForValues+= '<input type="text" name="pValue" id="pValue" style="width:350px;height:18px;color:#008bab;background-color:white" value="' + myProperty.value + '" readonly="readonly">';
	 	htmlForValues+= '<button type="button" class="btn btn-green dropdown-toggle" data-toggle="dropdown" style="float:right;margin-left:-5px" >'
	 	htmlForValues+= '<span class="caret"></span>';
	 	htmlForValues+= '<span class="sr-only"><i class="icon-cog icon-white" ></i></span>';
	 	htmlForValues+= '</button>';
	 	htmlForValues+= '<ul class="dropdown-menu" role="menu" style="width:350px;">';
	 				    
	 	var htmlForFixedValueList = '';
	 	for (fixed in myProperty.fixedValues) {
	 	  htmlForFixedValueList+= '<tr><td>' + myProperty.fixedValues[fixed] + '</td><td>';
	 	  htmlForFixedValueList+= '<a href="#" onclick="javascript:ff4j_deleteFixedValue(\'' + myProperty.name + '\',\'' +  myProperty.fixedValues[fixed] + '\')" style="width:6px;" >';
	 	  htmlForFixedValueList+= '<i class="icon-trash" style="margin-left:-5px;"></i></a></td></tr>';
	 	  htmlForValues+= '<li><a href="#" onclick="javascript:$(\'#modalEditProperty #pValue\').val(\'' + myProperty.fixedValues[fixed] + '\');">' + myProperty.fixedValues[fixed] + '</a></li>';
	 	}
	 	htmlForValues+= '</ul></div> </div>';
	 	$("#div-value-property").html(htmlForValues);
	 			 	
	 } else {
	    // no myProperty.fixedValues
	 	var htmlForValue = '<input type="text" name="pValue" id="pValue" style="width:350px;height:18px;color:#008bab;background-color:white" value="';
	 	htmlForValue+= myProperty.value;
	 	htmlForValue+= '" required/>';
	 	$("#div-value-property").html(htmlForValue);
	 }
	 	
	 htmlForFixedValueList+= '<tr><td><input type="text" id="nnfix" name="nnfix"  style="width:200px;height:18px;"/></td>';
	 htmlForFixedValueList+= '    <td><a href="#" onclick="javascript:ff4j_createFixedValue(\'' + myProperty.name + '\', $(\'#modalEditProperty #nnfix\').val())"';
	 htmlForFixedValueList+= ' style="width:6px;"><i class="icon-plus" style="margin-left:-5px;"></i></a></td></tr>';
	 $("#modalEditPropertyFixedValues").html(htmlForFixedValueList);
    	
  });
}

function ff4j_deleteFixedValue(pName, fValue) {
  $.ajax({
   type : 'GET',
   url : $(location).attr('href'),
   data : 'op=deleteFixedValue&uid=' + pName + '&fixedValue=' + fValue,
   dataType : 'html',
   success : function(permissionList, statut) { 
    ff4j_drawFixedValues(pName);
   },
   error : function(resultat, statut, erreur) {},
   complete : function(resultat, statut) {	
    console.log("Delete fixedValue for " + pName);
   }
 });
}
 
function ff4j_createFixedValue(pName, fValue) {
  $.ajax({
    type : 'GET',
	url : $(location).attr('href'),
	data : 'op=addFixedValue&uid=' + pName + '&fixedValue=' + fValue,
	dataType : 'html',
	success : function(permissionList, statut) { 
		ff4j_drawFixedValues(pName);
	},
	error : function(resultat, statut, erreur) {},
	complete : function(resultat, statut) {	
		console.log("Create fixedValue for " + pName);
	}
  });
}
 
function ff4j_changePropertyType(pName, pType) {
	 $('#modalEditProperty #pType').val(pType);
	 ff4j_updateModalEditProperty(any)(pName);
 }

//---------------------
// Utilities
//---------------------

function show (toBlock){
  setDisplay(toBlock, 'block');
}
function hide (toNone) {
  setDisplay(toNone, 'none');
}

function setDisplay (target, str) {
  var targetObj = document.getElementById(target);
  if (targetObj) {
    if (targetObj.style) {
	  targetObj.style.display = str;
	} else {
	  console.log(target + ' does not have style property');
    }
  } else {
    console.log(target + ' does not exist');
  }
}

