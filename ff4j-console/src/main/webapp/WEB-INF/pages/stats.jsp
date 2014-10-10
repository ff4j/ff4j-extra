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
							<h3>Total utilisation over time</h3>
						</div>
						<div class="widget-content">
							<div class="widget-content">
								
								<div id="maincurve" class="maincurveStyle"></div>
								<script  type="text/javascript">
								
								$(document).ready(function(){
							        $.jqplot.config.enablePlugins = true;
							        var s1 = [2, 3, 4, 5, 8, 9, 16, 17, 32, 33, 64, 65, 128, 129, 128, 65, 64, 33, 32, 17, 16, 9, 8, 5];
							        var s2 = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 65, 128, 129, 128, 65, 64, 33, 32, 17, 16, 9, 8, 5];
							        
							        var ticks = ['0:00','0:30', '1:00', '1:30','2:00', '2:30','3:00','3:30','4:00','4:30','5:00',
							                     '5:30','6:00','6:30','7:00','7:30','8:00','8:30','9:00','9:30','10:00','11:00','12:00','13:00'];
							         
							        plot1 = $.jqplot('maincurve', [s1, s2], {
							            // Only animate if we're not using excanvas (not in IE 7 or IE 8)..
							            animate: !$.jqplot.use_excanvas,
							            stackSeries: true,
							            seriesDefaults:{
							                renderer:$.jqplot.BarRenderer,
							                pointLabels: { show: false }
							            },
							            seriesColors:['#00ab8b', '#34bda1'],
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

							<form action="<c:url value='/stats'/>" id="envcheck" method="GET">

									
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
			
				<div class="span4">
					<div class="widget widget-nopad">
						<div class="widget-header">
							<i class="icon-home"></i>
							<h3>Features Hits</h3>
						</div>
						<div class="widget-content">
							<div class="widget-content">
								 <div id="pie" class="divpie"></div>
              
              <script  type="text/javascript">
              $(document).ready(function(){
            	var pieData = [['sayHello', 852.0],['feat1', 824.0],['feat6', 837.0],['feat7', 792.0],['feat2', 781.0],['feat3', 792.0],['feat4', 814.0],['sayGoodBye', 800.0],['feat5', 810.0]];
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
        					seriesColors: ["#00ab8b","#1ab496","#34bda1","#4ec6ac","#68cfb7","#82d8c2","#9ce1cd","#b6ead8","#d0f3e3"]
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
				
				<div class="span4" >
					<div class="widget widget-nopad">
						<div class="widget-header">
							<i class="icon-bar-chart"></i>
							<h3>Features enabled ratio</h3>
						</div>
						<div class="widget-content">
							<div class="widget-content" style="height:300px;">
								 <div id="pie2" class="divpie"></div>
              
              <script  type="text/javascript">
              $(document).ready(function(){
            	var pieData = [['enable', 8.0],['disabled', 1.0]];
    			var plot = $.jqplot('pie2', [pieData], {
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
        					seriesColors: ["#00ab8b","#d0f3e3"]
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
				
				<div class="span4" >
					<div class="widget widget-nopad">
						<div class="widget-header">
							<i class="icon-bar-chart"></i>
							<h3>Trends (past 10 days)</h3>
						</div>
						<div class="widget-content">
							<div class="widget-content" style="height:300px;">
								 <div id="trend" class="divpie"></div>
              
              <script  type="text/javascript">
              $(document).ready(function(){
            	  
            	    var d1 = [[0, 20], [1, 30], [2, 58], [3, 83], [4, 79], [5, 70], [6,60], 
            	    [7, 45], [8, 60], [9, 50], [10, 30]];
            	 
            	    var plot1 = $.jqplot('trend', [d1], {
            	        grid: {
            	            drawBorder: false,
            	            shadow: false,
            	            background: 'rgba(0,0,0,0)'
            	        },
            	        highlighter: { show: true },
            	        seriesDefaults: { 
            	            shadowAlpha: 0.1,
            	            shadowDepth: 2,
            	            fillToZero: true
            	        },
            	        series: [
            	            {
            	            	color: '#b6ead8',
            	                negativeColor: 'rgba(64, 210, 180, 0.7)',
            	                showMarker: true,
            	                showLine: true,
            	                fill: true,
            	                fillAndStroke: true,
            	                markerOptions: {
            	                    style: 'filledCircle',
            	                    size: 8
            	                },
            	                rendererOptions: {
            	                    smooth: true
            	                }
            	            }
            	        ],
            	        axes: {
            	            xaxis: {
            	                // padding of 0 or of 1 produce same results, range 
            	                // is multiplied by 1x, so it is not padded.
            	                pad: 1.0,
            	                tickOptions: {
            	                  showGridline: false
            	                }
            	            },
            	            yaxis: {
            	                pad: 1.05
            	            }
            	        }
            	    });
            	 
            	});
								</script>
								
								
							</div>
						</div>
					</div>
				</div>
				
				
			</div>
		</div>
	</div>