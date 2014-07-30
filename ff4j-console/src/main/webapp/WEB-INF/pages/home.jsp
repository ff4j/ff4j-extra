<%@ include file="/jsp-tiles/taglibs.jsp" %>

  <div class="main-inner">
    <div class="container">
      <div class="row">
      
        <div class="span6">
        
          <!--  General Informations -->
          <div class="widget widget-nopad">
           
            <div class="widget-header"> <i class="icon-home"></i>
              <h3> General Information</h3>
            </div>
           
            <div class="widget-content">
              <div class="widget big-stats-container">
                <div class="widget-content">
                	<h6 class="bigstats">Your are connected to <a href="#">DEV</a> using <a href="#">HTTP (RESTFul API)</a>
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
                		 		text-transform:lowercase;"><h4> 39 day(s) 18 Hour(s) 48 min 27 s</td>
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
                		 	InMemoryFeatureStore
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
                		 	<h4> --- </td>
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
                		 	<h4> --- </td>
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
                		 	<h4> InMemoryEventRepository</td>
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
                		 		text-transform:lowercase;"><h4> 1.2.0 </td>
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
              <h3>Σ Feature Hit</h3>
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
      </div>
      <!-- /row --> 
    </div>
    <!-- /container --> 
  </div>
  <!-- /main-inner --> 