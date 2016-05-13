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

// Invoked by 
function toggle(flip) {
  if(!$(flip).is(':checked')) {
    ff4j_disable(flip);    
  } else {
	ff4j_enable(flip);
  }
}
