<div id="menudiv" class="collapse in" style="font-size: 26px;">
	<ul class="nav nav-tabs">
	
		    <li class="${sessionScope.userSessionInfo.isMonitorAccess() ? 'active' : ''}"> 		<a href="<c:url value="/home"/>" title="Indietro" ><i class="fa fa-chevron-circle-left" aria-hidden="true"></i></a></li>
<%-- 	    <li class="${sessionScope.userSessionInfo.isMonitorAccess() ? 'active' : ''}"> 		<a href="<c:url value="/home/monitor"/>"  >Monitor</a></li> --%>
<%-- 	    <li class="${sessionScope.userSessionInfo.isActivityAccess() ? 'active' : ''}">		<a href="<c:url value="/home/activity"/>">Attivit&agrave;</a></li> --%>
<%-- 	    <li class="${sessionScope.userSessionInfo.isNoteAccess() ? 'active' : ''}">			<a href="<c:url value="/home/note"/>">Note</a></li> --%>
	    <c:if test="${sessionScope.userSessionInfo.isAmministrator()}">
		    <li class="${sessionScope.userSessionInfo.isUserAccess() ? 'active' : ''}">		<a href="<c:url value="/home/user"/>">Utenti</a></li>
		    <li class="${sessionScope.userSessionInfo.isSettingAccess() ? 'active' : ''}">	<a href="<c:url value="/home/setting"/>">Impostazioni</a></li>
		   
	    </c:if>
	</ul>
	
</div>





