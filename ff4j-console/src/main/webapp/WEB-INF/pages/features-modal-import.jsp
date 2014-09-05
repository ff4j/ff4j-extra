<%@ include file="/jsp-tiles/taglibs.jsp" %>


<!-- ******************* -->
<!-- ** IMPORT MODAL  ** -->
<!-- ******************* -->
<form class="form-horizontal" action="<c:url value='/features'/>" method="POST" enctype="multipart/form-data" >

  <div class="modal hide fade" id="modalImport" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    
    <div class="modal-header">   
      <button class="close" data-dismiss="modal">x</button>   
      <h4 id="myModalLabel"><i class="icon-file icon-white"></i>&nbsp;Import Features</h3>
    </div>

    <div class="modal-body">
      <p/>Choose an <b>XML</b> FF4J configuration file to be imported in the current repository</p>
      <input type="hidden" name="op"        value="import"  />
      <input type="file"   name="flipFile"  class="btn btn-green"/>
    </div>
    
    <div class="modal-footer">
      <button class="btn btn" data-dismiss="modal"><i class="icon-remove" ></i>&nbsp;Cancel</button>
      <button class="btn btn-green" type="submit"><i class="icon-file icon-white" ></i>&nbsp;Import </button>
    </div>

  </div>
</form>