<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
 <title>${title}</title>
 <#assign home><@spring.url relativeUrl="/"/></#assign>
 <#assign actuinfo><@spring.url relativeUrl="/info"/></#assign>
 <#assign actujolokiaagent><@spring.url relativeUrl="/jolokia"/></#assign>
 <#assign actumetrics><@spring.url relativeUrl="/metrics"/></#assign>
 <#assign actutrace><@spring.url relativeUrl="/trace"/></#assign>
 
 <#assign odata><@spring.url relativeUrl="/odata/$metadata"/></#assign>
 <#assign crud><@spring.url relativeUrl="/api/trades"/></#assign>
 <#assign hateoas><@spring.url relativeUrl="/api/trades/hateoas"/></#assign>
 <#assign bootstrap><@spring.url relativeUrl="/css/bootstrap.min.css"/></#assign>
 <link rel="stylesheet" href="${bootstrap}" />
 <link rel="stylesheet" href="/css/heroic-features.css" />
</head>
<body>

 <!-- Navigation -->
    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <img src="img/educ.png" style="height:45px">
              
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="/"><span class="glyphicon glyphicon-home"></span>&nbsp;Overview </a></li>
                    <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                      <span class="glyphicon glyphicon-pencil"></span>&nbsp;API Design <span class="caret"></span></a>
                      <ul class="dropdown-menu" role="menu">
                        <li><a href="/api-design.html#define_service">1. Service Definition</a></li>
                        <li><a href="/api-design.html#define_entities">2. Define your entities</a></li>
                        <li><a href="/api-design.html#define_fsm">3. Define your state diagram</a></li>
                        <li><a href="/api-design.htmldefine_operations">4. Normalized operations</a></li>
                        <li><a href="/api-design.html#define_media">5. Select a media Type</a></li>
                        <li><a href="/api-design.html#define_resources">6. Define your resources</a></li>
                        <li><a href="/api-design.html#define_interfaces">7. Normalized interfaces</a></li>
                      </ul>
                    </li>
                    <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                       <span class="glyphicon glyphicon-wrench"></span>&nbsp;API Implementation <span class="caret"></span></a>
                      <ul class="dropdown-menu" role="menu">
                        <li><a href="/api-implem.html#create_services">1. Create your services</a></li>
                        <li><a href="/api-implem.html#create_resources">2. Create your resources (JAX-RS)</a></li>
                        <li><a href="/api-implem.html#init_jersey">2. Initialize Jersey</a></li>
                        <li><a href="/api-implem.html#init_springboot">3. Introducing SpringBoot</a></li>
                        <li><a href="/api-implem.html#tech_monitoring">4. Monitoring</a></li>
                        <li><a href="/api-implem.html#tech_security">5. Security</a></li>
                      </ul>
                    </li>
                    
                   <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                        <span class="glyphicon glyphicon-cloud"></span>&nbsp;API Showcase<span class="caret"></span></a>
                      <ul class="dropdown-menu" role="menu">
                        <li><a href="/swagger/index.html"><i class="icon-book icon-white"></i>1. Documentation swagger</a></li>
                        <li><a href="/usecases.html">2. REST Service</a></li>
                        <li><a href="/odata.html">3. ODATA Service</a></li>
                        <li><a href="http://localhost:8080/">4. Admin Console</a></li>
                      </ul>
                    </li>
                    
                    <li><a href="/support.html"><span class="glyphicon glyphicon-envelope"></span>&nbsp;Get Support </a></li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

 <!-- Page Content -->
    <div class="container">

        <!-- Title -->
        <div class="row">
		
		<p ><h1 style="color:#AA0000"><img src="img/sg.jpg" style="height:35px"></h1>
		<p><br/>This application is a template designed to ease bootraping of new API. You will found information regarding the technical stack
		and sample implementations of the different standards. In the following lines please find our manisfest.
		
		<h2><img src="img/diplom.png" style="height:35px">&nbsp;&nbsp;Principles</h2>
		<hr>
		<ul>
		 
		 <li>The components are designed to adopt the <span class="bold1">REST</span> architecture style. The services are RESTFul <i>(correct usage of HTTP
		 verbs)</i> and stateless <i>(calls do not rely on a state of the application)</i>.<br/><p/>
		 
		 <li>Each service must be <span class="bold1">secured, monitored, scalable</span> and ensure its <span class="bold1">SLA</span>.
		  <br/><p/>
        
		 <li>To promote <span class="bold1">lightweight microservices</span>, components can be deployed both as  <b>standalone application (fat jar)</b> 
		 and <b>Web Application</b><br/><p/>
		 
        </ul>
        <br/>
		<h2><img src="img/tools.png" style="height:35px">&nbsp;&nbsp;Technical Stack</h2>
		<hr>
        <ul>  
		 <li>From April 2015, <span class="bold1">JAVA8</span> is the only supported version of Java. You can then use Streams, 
          Lambdas and the free performance improvements.<br/><p/>
         
         <li>The inversion of control is performed with the framework <span class="bold1">Spring</span> in version 4 and we recommend to
          leverage on <b>JavaConfig</b> as much as possible..<br/><p/>
          
		 <li>The squeletton of the application is built using  <span class="bold1">Spring-boot</span> which offer a wide range of 
		  technical capabilities out of the box <i>(login=sg, password=sg)</i>:
		  <ol>
		   <li><a href="${actuinfo}" >Information Endpoint</a>
		   <li><a href="${actumetrics}" >JVM Metrics</a>
		   <li><a href="${actutrace}" >Http Traces</a>
		   <li><a href="${actujolokiaagent}" >Jolokia Agent (JMX)</a>
		  </ol>
		 <br/>
		 
		 <li>The REST services : 
		 <ol> 
		  <li>Are <b>exposed</b> with the default implementation of JAX-RS : <span class="bold1">Jersey2</span>
		  <li>Are <b>monitored</b> with <span class="bold1">JaxRs Filters</span>, the <span class="bold1">CodeHale Metrics</span> library and custom <span class="bold1">AOP</span> implementation. 
		    All events and metrics are sent asynchronously to different storages : log files, Tmon worker , elastic search database.
		  <li>Are <b>documented</b> with <span class="bold1">Swagger</span> which generate description JSON flows and provide dedicated Gui to test
		  <li>Are <b>secured</b> with <span class="bold1">Spring-security-oauth2</span> and leverage on <b>OpenIDConnect</b> standard.
          <li>Are <b>throttled</b> with dedicate custom implementation of <span class="bold1">leaky bucket</span> algorithm. The number of calls is stored <b>in-memory</b> or in distributed caching <b>hazelcast</b>.
		  
		  </ol>
		  <br/><p/>
		 
		 <li>REST interfaces can be normalized in different ways : 
		  <ol>
		      <li> By using the <a href="http://www.odata/org">ODATAv4</a> standard with its reference <span class="bold1">Olingo</span> implementation
		      <li> By using the <b>HateOAS</b> to display links instead of full reference. It's implementing with <span class="bold1">Spring-HateOas</span>
		      <li> By using major version number in the service URL
          
		 </ul>
		
	   </div>
	</div>
	
	 <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
	
</body>
</html>
