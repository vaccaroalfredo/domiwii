
<%@include file="../sky.jsp"%>
<div class="row" style="margin-top: 7px;font-size:26px;">
	<div class="col-xs-7 col-xs-offset-4 col-md-5 col-md-offset-5">
		<span style="margin-left:20px;color:#237FB6"></span>
	</div>
	<div class="col-xs-1 col-md-2">
		<div class="pull-right" style="margin-right:30px">
	  		<a title="Disconnetti come ${sessionScope.userSessionInfo.getUser()}" href="<c:url value="logout"/>" ><i aria-hidden="true" class="fa fa-user-times"></i></a>
	  	</div>
	</div>
</div>
<!-- <div class="text-center" style="margin-top: 7px;font-size:26px;"> -->
<!-- 	<span style="margin-left:20px;color:#237FB6">TOC DASHBOARD</span> -->
<!-- 	<div class="pull-right" style="margin-right:30px"> -->
<%--   		<a title="Disconnetti come ${sessionScope.userSessionInfo.getUser()}" href="<c:url value="logout"/>" ><i aria-hidden="true" class="fa fa-user-times"></i></a> --%>
<!--   	</div> -->
<!-- </div> -->
