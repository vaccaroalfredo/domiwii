<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="it">
<head>
<%@include file="../include.jsp"%>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>  

    
    <!-- The jQuery library is a prerequisite for all jqSuite products -->
    <script type="text/ecmascript" src="<c:url value="/resources/js/jquery-1.11.0.min.js"/>"></script> 

    
    <!-- This is the Javascript file of jqGrid -->   
    <script type="text/ecmascript" src="<c:url value="/resources/js/jquery.jqGrid.min.js"/>"></script>
    <!-- This is the localization file of the grid controlling messages, labels, etc.
    <!-- We support more than 40 localizations -->
    <script type="text/ecmascript" src="<c:url value="/resources/js/i18n/grid.locale-it.js"/>"></script>
    <!-- This is jquery ui -->   
    <script type="text/ecmascript" src="<c:url value="/resources/js/jquery-ui.min.js"/>"></script>
    
    <!-- MIT LICENSE -->
	<script type="text/ecmascript" src="<c:url value="/resources/js/jquery.datetimepicker.full.min.js"/>"></script>
    <script type="text/ecmascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/date.js"/>"></script>
    
    
    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/bootstrap.min.css" />" />
   
    <!-- A link to a jQuery UI ThemeRoller theme, more than 22 built-in and many more custom -->
    <!-- The link to the CSS that the grid needs -->
<%--     <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/jquery-ui.min.css" />" />  --%>
    <!-- The link to the CSS that the grid needs -->
    
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/ui.jqgrid-bootstrap.css" />"/>
<%--     <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/ui.jqgrid.css" />" /> --%>
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/jquery.datetimepicker.css" />" />
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/font-awesome.min.css"/>"/>
	<!-- Application custom css -->
	<link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/css/application/application.css" />" />

	 <script type="text/javascript">
	// Here we set the altRows option globally
	jQuery.extend(jQuery.jgrid.defaults, { altRows:true,styleUI : "Bootstrap" ,responsive :true,scrollOffset:0,autowidth:true,shrinkToFit : true,forceFit:true,hidegrid: false});//,
	</script>
	<style type="text/css">
		.ui-jqgrid .ui-pg-input { height:13px;font-size:1em; margin-left: 100px em;}
	</style>
</head>  
<body id="body" >  
        <div>
        	<tiles:insertAttribute name="header" />
        </div>  
	    <div style="float:left;padding:0 0 0 30px;height:15%;width:100%;">
	    	<tiles:insertAttribute name="menu" />
	    </div>  
	    <div id="containerbody" style="float:left;padding:30px; width:100%;height:100%" align="center">  
	    	<tiles:insertAttribute name="body" />
	    </div>
        <div style="clear:both">
        	<tiles:insertAttribute name="footer" />
        </div>  
</body> 
</html>