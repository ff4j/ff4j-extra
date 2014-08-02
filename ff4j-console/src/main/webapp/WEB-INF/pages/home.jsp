<%@ include file="/jsp-tiles/taglibs.jsp" %>
<head>
    <title><fmt:message key="home.title"/></title>
    <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/css/pages/dashboard.css'/>" /> 
</head>
<body>

  <div class="main-inner">
    <div class="container">
      <div class="row">
      	
       <c:choose>
      	<c:when test="${not empty envbean.envId}">
        
        <div class="span6">
        
          <!--  General Informations -->
          <div class="widget widget-nopad">
           
            <div class="widget-header"> <i class="icon-home"></i>
              <h3> General Information</h3>
            </div>
           
            <div class="widget-content">
              <div class="widget big-stats-container">
                <div class="widget-content">
                	<h6 class="bigstats">Your are connected to <a href="#"><c:out value="${envbean.envId}" >-</c:out></a> 
                	using the <a href="#"><c:out value="${envbean.connectionMode}" >-</c:out></a> mode.
                	</h6>
                	<center>
                	<table style="border-collapse:separate;border-spacing:0 10px;text-transform:none">
                		<tr>
                		 <td style="width:100px;text-align:right;margin-right:15px"><h4>
                		 <a >
                		 	Uptime&nbsp;
                		 </a>
                		 </td>
                		 <td style="width:25px"><img src="<c:url value='/img/icons/uptime.png'/>" style="width:20px"></td>
                		 <td style="width:300px;color:#696969;
                		 		text-align:right;
                		 		-webkit-border-radius: 6px;
                		 		-moz-border-radius: 6px;
                		 		border-radius: 6px;
                		 		background-color:#eef8ee;
                		 		padding:5px;
                		 		text-transform:lowercase;"><h4> <c:out value="${homebean.uptime}" >---</c:out></td>
                		 </tr>
                		 <tr>
                		 <td style="width:100px;text-align:right"><h4>
                		 <a class="ff4j-tooltip" tooltip="Externalize your features in any database">Store&nbsp;&nbsp;</a></td>
                		 <td style="width:25px"><img src="./img/icons/database.png" style="width:20px;height:20px"></td>
                		 <td style="width:300px;color:#696969;
                		 		text-align:right;
                		 		-webkit-border-radius: 6px;
                		 		-moz-border-radius: 6px;
                		 		border-radius: 6px;
                		 		background-color:#eef8ee;
                		 		padding:5px;">
                		 	<h4>
                		 	<c:out value="${homebean.store}" >---</c:out>
                		 	</td>
                		 </tr>
                		 <tr>
                		 <td style="width:100px;text-align:right"><h4>
                		 <a class="ff4j-tooltip" tooltip="Limit overhead with a cache (inMemory, redis, ehcache...)">Caching&nbsp;&nbsp;</td>
                		 <td style="width:25px"><img src="./img/icons/random.png" style="width:20px;height:20px"></td>
                		<td style="width:300px;color:#696969;
                		 		text-align:right;
                		 		-webkit-border-radius: 6px;
                		 		-moz-border-radius: 6px;
                		 		border-radius: 6px;
                		 		background-color:#eef8ee;
                		 		padding:5px;">
                		 	<h4><c:out value="${homebean.caching}" >---</c:out></td>
                		 </tr>
                		  <tr>
                		 <td style="width:100px;text-align:right"><h4>
                		 <a class="ff4j-tooltip" tooltip="Define ACL on you features (Spring-Security)">Security&nbsp;&nbsp;</td>
                		 <td style="width:25px"><img src="./img/icons/padlock.png" style="width:20px;height:20px"></td>
                		<td style="width:300px;color:#696969;
                		 		text-align:right;
                		 		-webkit-border-radius: 6px;
                		 		-moz-border-radius: 6px;
                		 		border-radius: 6px;
                		 		background-color:#eef8ee;
                		 		padding:5px;">
                		 	<h4> <c:out value="${homebean.security}" >---</c:out> </td>
                		 </tr>
                		  <tr>
                		 <td style="width:100px;text-align:right"><h4>
                		 <a class="ff4j-tooltip" tooltip="Export metrics on feature usage to graph">Monitoring&nbsp;&nbsp;</td>
                		 <td style="width:25px"><img src="./img/icons/monitoring.png" style="width:20px;height:20px"></td>
                		 <td style="width:300px;color:#696969;
                		 		text-align:right;
                		 		-webkit-border-radius: 6px;
                		 		-moz-border-radius: 6px;
                		 		border-radius: 6px;
                		 		background-color:#eef8ee;
                		 		padding:5px;">
                		 	<h4> <c:out value="${homebean.monitoring}" >---</c:out></td>
                		 </tr>
                		 <tr>
                		 <td style="width:100px;text-align:right;margin-right:15px"><h4>
                		 <a >
                		 	Version&nbsp;
                		 </a>
                		 </td>
                		 <td style="width:25px"><img src="./img/icons/version.png" style="width:20px"></td>
                		 <td style="width:300px;color:#696969;
                		 		text-align:right;
                		 		-webkit-border-radius: 6px;
                		 		-moz-border-radius: 6px;
                		 		border-radius: 6px;
                		 		background-color:#eef8ee;
                		 		padding:5px;
                		 		text-transform:lowercase;"><h4> <c:out value="${homebean.version}" >---</c:out> </td>
                		 </tr>
                	</table>
                	</center>
                </div>
                
              </div>
            </div>
            
          </div>
          
          
          <div class="widget widget-nopad">
            <div class="widget-header"> <i class="icon-star"></i>
              <h3> Features Statistics</h3>
            </div>
           
             <div class="widget-content">
                  <div id="big_stats" class="cf">
                    <div class="stat">
                    <a class="ff4j-tooltip" tooltip="Number of features">
                    <i class="icon-heart"></i>
                    </a><span class="value">57</span> </div>
                    <!-- .stat -->
                    
                    <div class="stat"> <i class="icon-th"></i> <span class="value">60</span> </div>
                    <!-- .stat -->
                    
                    
                    <div class="stat"> <i class="icon-bar-chart"></i> <span class="value">1.282</span> </div>
                    <!-- .stat -->
                     
                 
                  </div>
                </div>
                <!-- /widget-content --> 

          </div>
          
          
        </div>
         <!-- /collone 1 .. span6 -->
        
        <!-- /span6 -->
        <div class="span6">
          <div class="widget">
            <div class="widget-header"> <i class="icon-wrench"></i>
              <h3>Activities</h3>
            </div>
            <!-- /widget-header -->
            <div class="widget-content">
              <div class="shortcuts"> 
              	<a href="javascript:;" class="shortcut">
              	  <i class="shortcut-icon icon-th-list"></i>
              	  <span class="shortcut-label">Store</span>
              	</a>
              	<a href="javascript:;" class="shortcut">
              	 <i class="shortcut-icon icon-signal"></i>
              	 <span class="shortcut-label">Monitoring</span> 
              	</a>
              	<a href="javascript:;" class="shortcut">
              	 <i class="shortcut-icon icon-magic"></i> 
              	 <span class="shortcut-label">Clear Cache</span> 
              	</a>
              	<a href="javascript:;" class="shortcut"> 
              	 <i class="shortcut-icon icon-warning-sign"></i>
              	 <span class="shortcut-label">Maintenance</span> 
              	</a>
              </div>
              <!-- /shortcuts --> 
            </div>
            <!-- /widget-content --> 
          </div>
          <!-- /widget -->
          <div class="widget">
            <div class="widget-header"> <i class="icon-signal"></i>
              <h3>Feature Hit</h3>
            </div>
            <!-- /widget-header -->
            <div class="widget-content">
              <canvas id="area-chart" class="chart-holder" height="250" width="538"> </canvas>
              <!-- /area-chart --> 
            </div>
            <!-- /widget-content --> 
          </div>
          <!-- /widget -->
          
        </div>
        <!-- /span6 --> 
        
      </c:when>
      	<c:otherwise>
      		<div class="span6">

					<!--  General Informations -->
					<div class="widget widget-nopad">

						<div class="widget-header">
							<i class="icon-home"></i>
							<h3>Select Environment...</h3>
						</div>

						<div class="widget-content" style="height:300px;">
							
							<p><h6 class="bigstats"> Several environments have been defined in configuration.</p>
							
							Please choose the target you want to reach.</h6></p>
							
							 <form action="<c:url value='/home'/>" id="envcheck" method="POST" >
							  <div class="btn-group" style="margin-left:50px">
				             <ul class="dropdown-menu" role="menu" style="width:150px;float:left">
				              <c:forEach items="${envbean.listOfConnection}" var="conn">
				              	<li><a href="javascript:$('#env').val('${conn.id}');$('#envcheck').submit()">${conn.id}</a></li>
				              </c:forEach>
				              </ul>
				               <input type="text" name="env" id="env" style="width:150px;height:30px;font-style:normal;float:left;" readonly="readonly">
				            <button type="button" class="btn btn-green dropdown-toggle" data-toggle="dropdown" style="float:left">
				              <span class="sr-only"> <i class="icon-th-list icon-white"></i></span>
				              <span class="caret"></span>
				            </button>
				            
				              </div>
				            </form>
							
							</div>
							
						</div>
					</div>
				</div>
      	</c:otherwise>
      </c:choose>
      
      </div>
      <!-- /row --> 
    </div>
    <!-- /container --> 
  </div>
  <!-- /main-inner --> 
  
</body>