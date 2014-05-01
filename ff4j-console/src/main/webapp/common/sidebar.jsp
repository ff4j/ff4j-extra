<%@ include file="/common/taglibs.jsp"%>

<div class="span3">
 <div id="sidebarbg"></div>
 <div id="sidebar">
  <div class="sidenav">

   <!--  Bloc SuperVision -->
   <div class="sidebar-widget" style="margin: -1px 0 0 0;">
    <h5 class="title"
     style="margin-bottom: 0; background-color: #4B6A87">
     <i class="icon-th-large icon-white"></i>&nbsp;&nbsp;Supervision
    </h5>
   </div>
   <div class="mainnav">
    <ul>
     <li>
      <a href="<c:url value='mainMenu'/>"><span class="icon16 icomoon-icon-dashboard"></span>DashBoard</a>
     </li>
     <li>
      <a href="#"><span class="icon16 icon16 icomoon-icon-mail-3""></span>Messages Broker</a>
       <ul class="sub">
       <li>
        <a href="<c:url value='/brokerHealth'/>" title="Get realtime satistic about activeMQ broker">
        <span class="icon16 icomoon-icon-bars"></span>Overview
        </a>
       </li>
       <li>
        <a href="<c:url value='/brokerBrowseQueuesAndTopics' />">
        <span class="icon16 icomoon-icon-database"></span>Browse Queues & Topic
        </a>
       </li>
       <li>
        <a href="<c:url value='/exchangeMessages' />">
        <span class="icon16 icomoon-icon-history"></span>Browse History & Replay
        </a>
       </li>
       <li>
        <a href="<c:url value='/brokerMessageEditor' />">
        <span class="icon16 icomoon-icon-pencil"></span>Message Creator
        </a>
       </li>
      </ul>
     </li>

     <li>
      <a href="#"><span class="icon16 icomoon-icon-folder-4"></span>File Monitor</a>
      <ul class="sub">
       <li><a href="<c:url value='/fileMonitorHealth'/>"><span
         class="icon16 icomoon-icon-bars"></span>Overview</a></li>
       <li><a href="<c:url value='/fileMonitorBrowseTransfers' />"><span
         class="icon16 icomoon-icon-graph"></span>Browse File Transfers</a></li>
       <li><a href="<c:url value='/fileMonitorSendFile' />"><span
         class="icon16 icomoon-icon-file-2"></span>Send File</a></li>
      </ul>
    </ul>
   </div>
   <!-- end .mainnav -->

   <!-- Bloc -->
   <div class="sidebar-widget" style="margin: -1px 0 -10px 0;">
    <h5 class="title" style="background-color: #4B6A87">
     <i class="icon-th-large icon-white"></i> &nbsp;&nbsp;User
     Management
    </h5>
   </div>
   <div class="mainnav">

    <ul>
     <li><a href="<c:url value='/adminUsers'/>"><span
       class="icon16 entypo-icon-users"></span>User Management</span></a></li>
     <li><a href="<c:url value='/adminActiveUsers'/>"><span
       class="icon16 icomoon-icon-users-2"></span>Active Users</span></a></li>
    </ul>

   </div>
   <!-- end .mainnav -->
  </div>

  <!-- Bloc -->
  <div class="sidebar-widget" style="margin: -1px 0 -10px 0;">
   <h5 class="title" style="background-color: #4B6A87">
    <i class="icon-th-large icon-white"></i> &nbsp;&nbsp;Personal
   </h5>
  </div>
  <div class="mainnav">
   <ul>
    <li><a href="<c:url value='/userform'/>"><span
      class="icon16 icomoon-icon-user-4"></span>Edit Profile</span></a></li>
   </ul>
  </div>
  <!-- end .mainnav -->
 </div>
</div>
