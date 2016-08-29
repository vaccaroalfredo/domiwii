<!doctype html>
<html>
<head>
<%@include file="include.jsp"%>
<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/login/login.css" />" />
<style>
.message_warning {
    color: #9F6000;
    background-color: #FEEFB3;
    
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
		<div class="pen-title">
		  <h1>Autenticazione</h1><span><i class='fa fa-paint-brush'></i></span>
		</div>
		
		<div class="module form-module">
			
<%-- 			<c:choose> --%>
	    		<c:if test="${loginBean.noapplicationuser}">
					<div class="toggle"><i class="fa fa-pencil-square-o fa-fw"></i>
					    <div class="tooltip">Click Me</div>
					</div>
				</c:if>
				
<%-- 			</c:choose> --%>
			<div class="form">
				<form:form id="loginForm" method="post" action="login" modelAttribute="loginBean">
				<form:label path="username">Inserisci la tua username</form:label>
		        	<form:input id="username" name="username" path="username" /><br>
			    	<form:label path="password">Inserisci la tua password</form:label>
		        	<form:password id="password" name="password" path="password" /><br>
		        		
			        <input type="submit" value="Invia" />
			        <c:if test="${not empty message}">
						<div class="" align="center">
							<font color="red">
								${message}
<!-- 								<i class="fa fa-warning"></i> -->
								
							</font>	
						</div>
					</c:if>
			    </form:form>
		    </div>
		    <c:if test="${loginBean.noapplicationuser}">
		     <div class="form">
			    <h2>Crea un utente</h2>
			    <form:form id="loginForm" method="post" action="register" modelAttribute="loginBean">
			      <form:label path="username">Username</form:label>
			      <form:input id="username"  path="username" />
			      <form:label path="username">Password</form:label>
			      <form:password id="password"  path="password" />
			      <input type="submit" value="Registra" />
			    </form:form>
			  </div>
			  </c:if>
	    </div>
	    
	<c:choose>
	    <c:when test="${loginBean.noapplicationuser}">
			<!-- The jQuery library is a prerequisite for all jqSuite products -->
			<script type="text/ecmascript" src="<c:url value="/resources/js/jquery-1.11.0.min.js"/>"></script>
			<script type="text/javascript" src="<c:url value="/resources/js/login/login.js"/>"></script>
			<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/font-awesome.min.css"/>"/>       
	    </c:when>    
    	<c:otherwise>
        	<style>
        		.form-module .form {
				    display: inherit;
				}
        	</style>
    	</c:otherwise>
	</c:choose>    
	 
</body>
<!-- Finding path for resources -->
<%-- <script type="text/javascript" src="<c:url value="/resources/js/prova.js" />"></script>  --%>
<!-- <script type="text/javascript"  >OnInit()</script> -->
</html>