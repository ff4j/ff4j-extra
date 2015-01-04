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
      
  <div class="span12">
	<div class="widget widget-nopad">
		<div class="widget-header">
			<i class="icon-circle-arrow-right"></i>
			<h3><fmt:message key="home.select.title" /></h3>
		</div>
		
		<div class="widget-content" style="height:500px;padding:10px">
      	
      	 <c:choose>
      	  
      	  <c:when test="${envbean.confFilePresent == false}">
      	  
      		<p>The configuration File <b><span style="font-family:Courier New;color:#AA0000;">ff4j-console-conf.xml</b></span> 
      			<font color="#FF0000"><b><u>has NOT been found</u></font></b> in classpath or close to current archive, please check. 
      		<p style="margin-left:10px">Here is a sample
      			file to explain structure :
      			</p>
      			
<div style="font-family:Courier New;color:#000088;margin-left:10px;padding:10px;margin-right:20px;border:1px solid #888888">
      			 &lt;?xml version="1.0" encoding="UTF-8"?&gt;
 <br>&lt;ff4j-console&gt;
	
<br>&nbsp;	<i><span style="color:#008800"> &lt;!-- Users who access to console --&gt;</i></span>
<br>&nbsp;	&lt;users&gt;
<br>&nbsp;&nbsp;		 &lt;user name="admin" password="admin" language="en" admin="true"/&gt;
<br>&nbsp;&nbsp;		 &lt;user name="scott" password="tiger"	language="fr"/&gt;
<br>&nbsp;	&lt;/users&gt;
	
<br>&nbsp; 	<i><span style="color:#008800">&lt;!-- Remote environment --&gt;</i></span>
<br>&nbsp;	 &lt;connections&gt;
<br>&nbsp;&nbsp;		 &lt;connection id="MyApp DEV" mode="http" url="http://localhost:8081/ff4j-demo/api" /&gt;
<br>&nbsp;&nbsp;		 &lt;connection id="MyApp UAT" mode="http" url="http://uatserver:8282/webapi/api" authKey="ABCDE" /&gt;
<br>&nbsp;	 &lt;/connections&gt;

<br>&nbsp; 	<i><span style="color:#008800">	 &lt;!-- Properties --&gt;</i></span>
<br>&nbsp;	 &lt;properties&gt;
<br>&nbsp;&nbsp;		 &lt;property name="language" value="fr" /&gt;
<br>&nbsp;	 &lt;/properties&gt;
<br> &lt;/ff4j-console &gt;
      			</div>
      			      		
      	  </c:when>
      	  
      	  <c:when test="${ (envbean.confFilePresent == true) && (envbean.confFileParsed == false) }">
      			<p>Configuration File <b><span style="font-family:Courier New;color:#000000;">ff4j-console-conf.xml</b></span> 
      			has been found but has <font color="#AA0000"><b><u>invalid syntax</u></font></b> please check against
      			this sample
      			</p>
      			
      			<div style="font-family:Courier New;color:#000088;margin-left:10px;padding:10px;margin-right:20px;border:1px solid #888888">
      			 &lt;?xml version="1.0" encoding="UTF-8"?&gt;
 <br>&lt;ff4j-console&gt;
	
<br>&nbsp;	<i><span style="color:#008800"> &lt;!-- Users who access to console --&gt;</i></span>
<br>&nbsp;	&lt;users&gt;
<br>&nbsp;&nbsp;		 &lt;user name="admin" password="admin" language="en" admin="true"/&gt;
<br>&nbsp;&nbsp;		 &lt;user name="scott" password="tiger"	language="fr"/&gt;
<br>&nbsp;	&lt;/users&gt;
	
<br>&nbsp; 	<i><span style="color:#008800">&lt;!-- Remote environment --&gt;</i></span>
<br>&nbsp;	 &lt;connections&gt;
<br>&nbsp;&nbsp;		 &lt;connection id="MyApp DEV" mode="http" url="http://localhost:8081/ff4j-demo/api" /&gt;
<br>&nbsp;&nbsp;		 &lt;connection id="MyApp UAT" mode="http" url="http://uatserver:8282/webapi/api" authKey="ABCDE" /&gt;
<br>&nbsp;	 &lt;/connections&gt;

<br>&nbsp; 	<i><span style="color:#008800">	 &lt;!-- Properties --&gt;</i></span>
<br>&nbsp;	 &lt;properties&gt;
<br>&nbsp;&nbsp;		 &lt;property name="language" value="fr" /&gt;
<br>&nbsp;	 &lt;/properties&gt;
<br> &lt;/ff4j-console &gt;
      			</div>
      			
      			
      	  </c:when>
      	  
      	  <c:otherwise>
      		
			<h6 class="bigstats"> 
				<p><fmt:message key="home.select.msg1" /></p>
				<p><fmt:message key="home.select.msg2" />
			</h6>
			  <form action="<c:url value='/loadConfig.do'/>" id="envcheck" method="POST" >
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
			
			</c:otherwise>
      		</c:choose>
      		    </div>
						</div>
					</div>
				</div>
				
				
      		
      
      </div>
    </div>
  </div>

</body>