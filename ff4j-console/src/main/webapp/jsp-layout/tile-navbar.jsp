<%@ include file="/common/taglibs.jsp" %>

	<!-- Header reporter sur toutes les pages -->
	<div id="header" >
	 <div class="container" >
	 	
	  <div class="navbar" >
	    
	    <!-- Banner1 -->
		<div class="navbar-inner" style="background:url(<c:url value='/images/bannerX.jpeg' />);height:117px;">
		 <div style="position:absolute;top:5px;color:white"><fmt:message key="webapp.version" /></div>
	 	
		 <span class="brand" >
		   <img style="height:117px;position:absolute;top:0px;-5px;margin-left:-5px" src="<c:url value='/images/banner2.png' />">
		   <div style="padding-top:10px;margin-left:180px;margin-top:35px;color:#dddddd"><fmt:message key="webapp.name"/></div>
		 </span>
		 
		 <!-- Banner part notification -->
		 <div class="nav-no-collapse">
		   <ul class="nav" style="float:right;padding-top:20px">
             <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
               <span class="icon16 icomoon-icon-bell-2"></span>
               <span class="notification">3</span>
			  </a>
			  <ul class="dropdown-menu">
				<li class="menu">
				 <ul class="notif">
				  <li class="header"><strong>Alerts</strong> (3) items</li>
					<li>
					  <a href="#">
					    <span class="icon"><i class="icon16 icomoon-icon-mail-2"></i></span>
					    <span class="event" style="color:red">2 Rejected Message(s)</span>
					   </a>
					</li>
					<li>
					  <a href="#">
					    <span class="icon"><span class="icon16 icomoon-icon-file"></span></span>
					    <span class="event">1 File not processed</span>
					   </a>
					</li>
				  </ul><!-- notif -->
				</li><!-- menu -->
			  </ul>
				</li>
			

			 <!-- Logout Button -->
			 <li>
			  <a href="<c:url value='logout'/>" >
				<span class="icon16 icomoon-icon-exit"></span> Logout
			</a>
			 </li>
			
           </ul>
          </div>
			<span style="float:right;color:white;margin-top:85px;margin-right:-50px"><fmt:message key="webapp.version" /></span>
		 </div>
		</div>
	 
	  
	  <div class="heading">

      <!-- Train  -->
      <ul class="breadcrumb" style="float:left">
       <li>
        <a href="#" class="tip" title="back to dashboard">
          <span class="icon16 icomoon-icon-home"></span>
        </a>
        <span class="divider">
         <span class="icon16 icomoon-icon-arrow-right-2"></span>
        </span>
       </li>
       <li class="brand" style="font-weight:bold;font-size:14px">${title}
        <span class="divider">
         <span class="icon16 icomoon-icon-arrow-right-2"></span>
        </span>
       </li>
       <li class="brand" style="font-weight:bold;font-size:14px">${subtitle}
      </ul>
      
       <span class="right" style="margin-top:20px">	
       	<h4>
		 <span class="icon16 icomoon-icon-user-2"></span> ${pageContext.request.remoteUser}
		 (<a href="<c:url value='/userform'/>" style="font-weight:normal"><span class="icon16 icomoon-icon-pencil"></span> edit</a>)</h4>
       </span>

   </div><!-- End .heading-->
   
    </div>
	</div>
	