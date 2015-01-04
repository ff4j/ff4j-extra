<%@ include file="/jsp-tiles/taglibs.jsp" %>
<head>
 <title><fmt:message key="home.title"/></title>
 
 <!-- PAGE HOME -->
 <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/css/pages/dashboard.css'/>" />
 <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/js/jqplot/jquery.jqplot.min.css'/>" />
 <script type="text/javascript" src="<c:url value='/js/jqplot/jquery.jqplot.min.js'/>"></script>
 <script type="text/javascript" src="<c:url value='/js/jqplot/plugins/jqplot.pieRenderer.min.js'/>"></script>
 <script type="text/javascript" src="<c:url value='/js/jqplot/plugins/jqplot.highlighter.min.js'/>"></script>
 <style>.divpie {width:525px;height:285px;padding:5px;}</style>
</head>
<body>

  <div class="main-inner">
    <div class="container">
      <div class="row">
     
        <div class="span6">
        
          <!--  General Informations -->
          <div class="widget widget-nopad">
           
            <div class="widget-header"> <i class="icon-home"></i>
              <h3><fmt:message key="home.general.title"/></h3>
            </div>
           
            <div class="widget-content">
              <div class="widget big-stats-container">
                <div class="widget-content">
                	<h6 class="bigstats">
                	  <fmt:message key="home.general.message.1"/>
                	  <a href="#"><c:out value="${envbean.envId}" >-</c:out></a> 
                	  <fmt:message key="home.general.message.2"/> <a href="#">
                	  <c:out value="${envbean.connectionMode}" >-</c:out></a>
                	</h6>
                	<center>
                	<table style="border-collapse:separate;border-spacing:0 10px;text-transform:none">
                		<tr>
                		 <td style="width:100px;text-align:right;margin-right:15px"><h4>
                		 <a class="ff4j-tooltip" tooltip="<fmt:message key="home.general.uptime.comment"/>" >
                		 	<fmt:message key="home.general.uptime"/>&nbsp;
                		 </a>
                		 </td>
                		 <td style="width:25px"><img src="<c:url value='/img/icons/uptime.png'/>" style="width:20px"></td>
                		 <td style="width:300px;color:#696969;
                		 		text-align:right;
                		 		-webkit-border-radius: 6px;
                		 		-moz-border-radius: 6px;
                		 		border-radius: 6px;
                		 		background-color:#d0f3e3;
                		 		padding:5px;
                		 		text-transform:lowercase;"><h4> <c:out value="${homebean.uptime}" >---</c:out></td>
                		 </tr>
                		 <tr>
                		 <td style="width:100px;text-align:right"><h4>
                		 <a class="ff4j-tooltip" tooltip="<fmt:message key="home.general.store.comment"/>">
                		 <fmt:message key="home.general.store"/>&nbsp;&nbsp;</a></td>
                		 <td style="width:25px"><img src="./img/icons/database.png" style="width:20px;height:20px"></td>
                		 <td style="width:300px;color:#696969;
                		 		text-align:right;
                		 		-webkit-border-radius: 6px;
                		 		-moz-border-radius: 6px;
                		 		border-radius: 6px;
                		 		background-color:#d0f3e3;
                		 		padding:5px;">
                		 	<h4>
                		 	<c:out value="${homebean.store}" >---</c:out>
                		 	</td>
                		 </tr>
                		 <tr>
                		 <td style="width:100px;text-align:right"><h4>
                		<a class="ff4j-tooltip" tooltip="<fmt:message key="home.general.caching.comment"/>">
                		 <fmt:message key="home.general.caching"/>&nbsp;&nbsp;</td>
                		 <td style="width:25px"><img src="./img/icons/random.png" style="width:20px;height:20px"></td>
                		<td style="width:300px;color:#696969;
                		 		text-align:right;
                		 		-webkit-border-radius: 6px;
                		 		-moz-border-radius: 6px;
                		 		border-radius: 6px;
                		 		background-color:#d0f3e3;
                		 		padding:5px;">
                		 	<h4><c:out value="${homebean.caching}" >---</c:out></td>
                		 </tr>
                		  <tr>
                		 <td style="width:100px;text-align:right"><h4>
                		 <a class="ff4j-tooltip" tooltip="<fmt:message key="home.general.security.comment"/>">
                		 <fmt:message key="home.general.security"/>&nbsp;&nbsp;</td>
                		 <td style="width:25px"><img src="./img/icons/padlock.png" style="width:20px;height:20px"></td>
                		<td style="width:300px;color:#696969;
                		 		text-align:right;
                		 		-webkit-border-radius: 6px;
                		 		-moz-border-radius: 6px;
                		 		border-radius: 6px;
                		 		background-color:#d0f3e3;
                		 		padding:5px;">
                		 	<h4> <c:out value="${homebean.security}" >---</c:out> </td>
                		 </tr>
                		  <tr>
                		 <td style="width:100px;text-align:right"><h4>
                		 <a class="ff4j-tooltip" tooltip="<fmt:message key="home.general.monitoring.comment"/>">
                		 <fmt:message key="home.general.monitoring"/>&nbsp;&nbsp;</td>
                		 <td style="width:25px"><img src="./img/icons/monitoring.png" style="width:20px;height:20px"></td>
                		 <td style="width:300px;color:#696969;
                		 		text-align:right;
                		 		-webkit-border-radius: 6px;
                		 		-moz-border-radius: 6px;
                		 		border-radius: 6px;
                		 		background-color:#d0f3e3;
                		 		padding:5px;">
                		 	<h4> <c:out value="${homebean.monitoring}" >---</c:out></td>
                		 </tr>
                		 <tr>
                		 <td style="width:100px;text-align:right;margin-right:15px"><h4>
                		  <a class="ff4j-tooltip" tooltip="<fmt:message key="home.general.version.comment"/>">
                		 	<fmt:message key="home.general.version"/>&nbsp;
                		 </a>
                		 </td>
                		 <td style="width:25px"><img src="./img/icons/version.png" style="width:20px"></td>
                		 <td style="width:300px;color:#696969;
                		 		text-align:right;
                		 		-webkit-border-radius: 6px;
                		 		-moz-border-radius: 6px;
                		 		border-radius: 6px;
                		 		background-color:#d0f3e3;
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
              <h3> <fmt:message key="home.stats.title" /></h3>
            </div>
             <div class="widget-content">
                  <div id="big_stats" class="cf">
                    <div class="stat">
                    	<span class="value" style="color:#ab8b00">
                    		<c:out value="${homebean.nbFeature}" >---</c:out><span style="font-size:14px">&nbsp;Feature(s)</span>
                    	</span>
                    	<i class="icon-th-list"></i>
                    </div>
                    <!-- .stat -->
                    
                    <div class="stat">
                    	
                    	<span class="value" style="color:#ab8b00">
                    		<c:out value="${homebean.nbGroup}" >---</c:out><span style="font-size:14px">&nbsp;Group(s)
                    	</span>
                    	<i class="icon-th-large"></i>
                    </div>
                    <!-- .stat -->
                    
                    <div class="stat"> 
                    	<span class="value" style="color:#ab8b00">
                    		<c:out value="${homebean.nbEvents}" >---</c:out><span style="font-size:14px">&nbsp;Hit(s)
                    	</span>
                    	<i class="icon-bar-chart"></i>
                    </div>
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
              <h3><fmt:message key="home.ops.title" /></h3>
            </div>
            <!-- /widget-header -->
            <div class="widget-content">
              <div class="shortcuts"> 
              	<a href="<c:url value='/features.do'/>" class="shortcut" >
              	  <i class="shortcut-icon icon-hdd"></i>
              	  <span class="shortcut-label"><fmt:message key="home.ops.store" /></span>
              	</a>
              	<a href="<c:url value='/stats.do'/>" class="shortcut">
              	 <i class="shortcut-icon icon-signal"></i>
              	 <span class="shortcut-label"><fmt:message key="home.ops.monitoring" /></span> 
              	</a>
              	<sec:authorize url="/clearCache.do">
              	<a data-toggle="modal" href="#modalClearCache" class="shortcut" href="<c:url value='/clearcache'/>" class="shortcut">
              	 <i class="shortcut-icon icon-magic"></i> 
              	 <span class="shortcut-label"><fmt:message key="home.ops.clearcache" /></span> 
              	</a>
              	</sec:authorize>
              	<sec:authorize url="/clearCache.do">
              	<a href="#" class="shortcut"> 
              	 <i class="shortcut-icon icon-warning-sign"></i>
              	 <span class="shortcut-label"><fmt:message key="home.ops.maintenance" /></span> 
              	</a>
              	</sec:authorize>
              </div>
              <!-- /shortcuts --> 
            </div>
            <!-- /widget-content --> 
          </div>
          <!-- /widget -->
          <div class="widget">
            <div class="widget-header"> <i class="icon-signal"></i>
              <h3><fmt:message key="home.graph.title" /></h3>
            </div>
            <!-- /widget-header -->
            <div class="widget-content" style="margin:0px;padding:0px">
             
              <div id="pie" class="divpie"></div>
              
              <script  type="text/javascript">
              $(document).ready(function(){
            	var pieData = ${homebean.todayHitCountData};
    			var plot = $.jqplot('pie', [pieData], {
    				animate: true,
    				animateReplot: true,
    				seriesDefaults:{ 
        				renderer:$.jqplot.PieRenderer,
        				shadow:true,
        				rendererOptions: {
        					fill:true,
        					showDataLabels: true, 
        					dataLabelPositionFactor: 0.75,
        					sliceMargin: 5, 
        					seriesColors: ${homebean.todayHitCountColors}
    					},
        				trendline:{ show: true }
    				},
        			legend:{ 
        				show: true, 
        				location: 'w' 
        			},
        			grid: {
        				drawGridlines: false,
        				drawBorder: false, 
        				shadow:false, 
        				background: '#FFFFFF'
        			},
        			highlighter: {
        	            show: true,
        	            formatString:'%s',
        	            tooltipLocation: 'n',
        	            useAxesFormatters:false
        	        }
    			}); // plot2
			  }); // function
              </script>            
              
              <!-- /area-chart --> 
            </div>
            <!-- /widget-content --> 
          </div>
          <!-- /widget -->
          
        </div>
     
      
      </div>
      <!-- /row --> 
    </div>
    <!-- /container --> 
  </div>
  <!-- /main-inner --> 
  
  <%@ include file="features-modal-clearcache.jsp" %>
  
  
</body>