<%@ include file="/jsp-layout/includes-taglibs.jsp"%>
<decorator:usePage id="template-login" />

<html lang="en">

 <head>
   <%@ include file="/jsp-layout/header.jsp" %>
   <decorator:head/>
   <title><decorator:title/> | <fmt:message key="webapp.name"/></title>
 </head>

 <body>
   <decorator:getProperty property="body.id" writeEntireProperty="true"/>
   <decorator:getProperty property="body.class" writeEntireProperty="true"/>
   <decorator:body/>
 </body>
 
</html>
