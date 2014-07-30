<%@ include file="/jsp-tiles/taglibs.jsp" %>

<!-- ************** Navigation BAR ************** -->

<div class="navbar navbar-fixed-top">
  <div class="navbar-inner">
    <div class="container">
    	
    	<!-- Button if width is no more sufficient (collapse) -->
    	<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"  style="margin-top:15px">
    		<span class="icon-bar"></span>
    		<span class="icon-bar"></span>
    		<span class="icon-bar"></span>
    	</a>
    	
    	<!-- Link to Home -->
        <a class="brand" href="index.html" >
        	<img src="./img/icons/ff4j.png" style="height:40px">
        	&nbsp;&nbsp;&nbsp; ADMIN @ DEV
        </a>
        
        <!-- Right part of the nav bar -->
        <div class="nav-collapse"  style="margin-top:15px">
	        <ul class="nav pull-right">
          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                            class="icon-cog icon-white"></i> Choose Language <b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="javascript:;"><img src="./img/icons/flag/english.png" style="height:15px"> English</a></li>
              <li><a href="javascript:;"><img src="./img/icons/flag/france.png" style="height:15px"> Frensh</a></li>
              <li><a href="javascript:;"><img src="./img/icons/flag/germany.png" style="height:15px"> German</a></li>
            </ul>
          </li>
          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">
          <img src="./img/icons/switch2.png" style="height:20px">&nbsp; Instance<b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="javascript:;"> <img src="./img/icons/switch.png" style="height:15px">&nbsp; DEV</a></li>
              <li><a href="javascript:;"> <img src="./img/icons/switch.png" style="height:15px">&nbsp; UAT</a></li>
            </ul>
          </li>
        </ul>

      	</div>
      	<!--/.nav-collapse -->
    </div>
    <!-- /container --> 
  </div>
  <!-- /navbar-inner --> 
</div>
<!-- /navbar -->


<!-- ************** SUB Navigation BAR ************** -->
<div class="subnavbar">
  <div class="subnavbar-inner">
    <div class="container">
      <ul class="mainnav">
        <li class="active">
        	<a href="index.html">
        		<i class="icon-dashboard"></i>
        		<span>Dashboard</span>
        	</a>
        </li>
        <li>
        	<a href="features.html">
        		<i class="icon-list-alt"></i>
        		<span>Features</span>
        	</a>
        </li>
        <li>
        	<a href="reports.html">
        		<i class="icon-bar-chart"></i>
        		<span>Monitoring</span>
        	</a>
        </li>
        <li>
        	<a href="reports.html">
        		<i class="icon-cog"></i>
        		<span>Settings</span>
        	</a>
        </li>
      </ul>
    </div>
    <!-- /container --> 
  </div>
  <!-- /subnavbar-inner --> 
</div>
<!-- /subnavbar -->


	