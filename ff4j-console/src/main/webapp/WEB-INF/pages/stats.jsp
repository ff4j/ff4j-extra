<%@ include file="/jsp-tiles/taglibs.jsp" %>
<head>
 <title>FF4J Console - <fmt:message key="stats.title"/></title>

<!-- reference to JQPLOT -->
<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/js/jqplot/jquery.jqplot.min.css'/>" />
<script type="text/javascript" src="<c:url value='/js/jqplot/jquery.jqplot.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqplot/plugins/jqplot.pieRenderer.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqplot/plugins/jqplot.highlighter.min.js'/>"></script>


<script type="text/javascript" src="<c:url value='/js/jqplot/plugins/jqplot.barRenderer.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqplot/plugins/jqplot.categoryAxisRenderer.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqplot/plugins/jqplot.dateAxisRenderer.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqplot/plugins/jqplot.BezierCurveRenderer.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqplot/plugins/jqplot.barRenderer.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqplot/plugins/jqplot.pieRenderer.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jqplot/plugins/jqplot.pointLabels.min.js'/>"></script>

<style>.maincurveStyle {margin-top:10px;margin-left:10px}</style>
</head>
<body>

<script>
$(document).ready(function() {
    // put your jQuery code here.
	$( "#li-features" ).removeClass( "active");
	$( "#li-dashboard" ).removeClass( "active");
	$( "#li-settings" ).removeClass( "active");
	$( "#li-monitoring" ).addClass( "active");
});
</script>

<div class="main-inner">
		<div class="container">
			<div class="row">

				<div class="span8">
					<div class="widget widget-nopad">
						<div class="widget-header">
							<i class="icon-bar-chart"></i>
							<h3>${statsbean.barChartTitle}</h3>
						</div>
						<div class="widget-content">
							<div class="widget-content">
								
								<div id="maincurve" class="maincurveStyle"></div>
								<script  type="text/javascript">
								
								$(document).ready(function(){
							        $.jqplot.config.enablePlugins = true;
							        ${statsbean.barChartSeriesData}
							        
							        
							        var ticks = ${statsbean.barChartLabels};
							         
							        plot1 = $.jqplot('maincurve',  ${statsbean.barChartSeriesNames}, {
							            // Only animate if we're not using excanvas (not in IE 7 or IE 8)..
							            animate: !$.jqplot.use_excanvas,
							            stackSeries: true,
							            seriesDefaults:{
							                renderer:$.jqplot.BarRenderer,
							                pointLabels: { show: false }
							            },
							            seriesColors:${statsbean.barChartSeriesColors},
							            grid: { backgroundColor: '#eeffee'},
							            axes: {
							                xaxis: {
							                    renderer: $.jqplot.CategoryAxisRenderer,
							                    ticks: ticks
							                }
							            },
							            highlighter: { show: false }
							        });
							    });
								
								
              					</script>       
								
								
							</div>
						</div>
					</div>
				</div>
				
				<div class="span4">
					<div class="widget widget-nopad">
						<div class="widget-header">
							<i class="icon-edit"></i>
							<h3>Select your feature</h3>
						</div>
						
						<div class="widget-content" style="height: 290px; padding: 10px;">

							<form action="<c:url value='/stats.do'/>" id="envcheck" method="GET">

									
									<div class="btn-group">
									<label class="control-label" for="uid" style="color: #00ab8b; font-style: normal; font-weight: normal">
										Feature :
									</label>
									
										<ul class="dropdown-menu" role="menu"
											style="width: 150px; float: left">
											<li><a
												href="javascript:$('#uid').val('ALL');$('#envcheck').submit()">ALL</a></li>
											<c:forEach items="${statsbean.listOfFeatures}" var="feat">
												<li><a
													href="javascript:$('#uid').val('${feat.uid}');$('#envcheck').submit()">${feat.uid}</a></li>
											</c:forEach>
										</ul>
										<input type="text" name="uid" id="uid"
											style="width: 150px; height: 30px; font-style: normal; float: left;"
											readonly="readonly">
										<button type="button" class="btn btn-green dropdown-toggle"
											data-toggle="dropdown" style="float: left">
											<span class="sr-only"> <i
												class="icon-th-list icon-white"></i></span> <span class="caret"></span>
										</button>
									</div>



							</form>


						</div>
					</div>
					</div>
				</div>
			
			<div class="row">
			
				<div class="span8">
					<div class="widget">
						<div class="widget-header">
							<i class="icon-home"></i>
							<h3>${statsbean.pieChartTitle}</h3>
						</div>
						<div class="widget-content">
							 <div class="widget-content" style="margin:0px;padding:0px;padding-left:150px;">
             
              <div id="pie" class="divpie"></div>
              
              <script  type="text/javascript">
              $(document).ready(function(){
            	var pieData = ${statsbean.pieChartData};
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
        					seriesColors: ${statsbean.pieChartColors}
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
              
							</div>
						</div>
					</div>
				</div>
				
				
						</div>
					</div>
				</div>
				
				
			</div>
		</div>
	</div>