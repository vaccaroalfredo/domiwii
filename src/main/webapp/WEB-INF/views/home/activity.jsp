<%@include file="../include.jsp"%>
<%@include file="../sky.jsp"%>
<div class="row" style="font-size: 26px;vertical-align:middle;width:100%; margin-bottom: 10px;">
	  <div class="col-md-7" style="float:left; text-align: left;">
		  <a href="<c:url value="/home"/>" title="Indietro"><i class="fa fa-chevron-circle-left" aria-hidden="true"></i></a>
		  <strong>Attivit&agrave;</strong>
	  </div>
	  <div class="col-md-5">
<!-- 	  		<div class="pull-right"> -->
<%-- 	  			<img alt="" style="height:40px" src="<c:url value="/resources/css/images/logonetcom.png" />" /> --%>
<!-- 	  		</div>	 -->
	  		<div class="pull-right" style="margin-right: 20px">
	  			<a title="Disconnetti come ${sessionScope.userSessionInfo.getUser()}" class="" href="<c:url value="logout"/>">
	  			<i aria-hidden="true" class="fa fa-user-times"></i>
	  			</a>
	  		</div>	
	  </div>
</div>
<c:set var="description" value="Descrizione"/>
<c:set var="gridName" scope="page" value="jqActivityGrid" />

<form:form id="searchForm" class="form-horizontal" method="GET" modelAttribute="searchBean" >
  <div class="form-group">
    <label for="person" class="col-sm-1 control-label">${description}</label>
    <div class="col-sm-2">
      <form:input type="text" class="form-control" id="description" path="description" placeholder="${description}" />
    </div>
    <label for="status" class="col-sm-1 control-label">Stato</label>
    <div class="col-sm-2">
      <form:select  class="form-control" id="status" path="status" >
	    <form:option value=""> --Seleziona stato--</form:option>
	    <form:options items="${statusList}"></form:options>
      </form:select>
    </div>
    <label class="col-sm-1 control-label">Inizio</label>
    <div class="col-sm-2 date">
        <div class="input-group date" id="div-startdate">
            <form:input type="text" class="form-control" id="startdate" path="startdate" name="date" />
            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
        </div>
    </div>
    <label class="col-sm-1 control-label">Fine</label>
    <div class="col-sm-2 date">
        <div class="input-group input-append date" id="div-enddate" >
            <form:input type="text" class="form-control" id="enddate"  path="enddate" name="date" />
            <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
        </div>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-1">
      <input type="button" class="btn btn-default" value="Ricerca" onclick="getActivities();"/>
    </div>
     <c:if test="${sessionScope.userSessionInfo.isAmministrator()}">
	    <div class="col-sm-1">
	      <button type="button" class="btn btn-default addRowElement" onclick="addRowTable('${gridName}');">Aggiungi attivit&agrave;</button>
	    </div>
    </c:if>
    <div class="col-sm-6">
	      <button type="button" class="btn btn-default"  onclick="getActivities(true);" ><i class="fa fa-file-excel-o"></i> Download Excel Document</button>
	      <a id="downloadexcel"  href="activity/downloadexcel" style="display:none" ></a>
	</div>
  </div>
</form:form> 

<!-- <hr align="center"/>  -->
	
	
	<%@include file="modal.jsp"%>
	<table id="${gridName}"></table>
    <div id="${gridName}Pager"></div>
    
    <script type="text/javascript" src="<c:url value="/resources/js/application/jqgridtable.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/application/activity_col_model.js"/>"></script>
 	<script type="text/javascript" src="<c:url value="/resources/js/application/search.js"/>"></script>
    <script type="text/javascript"> 
    	var excel=false;
    	$(document).ready(function () {
    		initSearchElement("searchForm","startdate","enddate" )
    		getActivities();
        });
    	
    	function validation(){
	  		var startdate=$('#startdate').val()
	  		var enddate=$('#enddate').val()
	  		if (enddate!="" && startdate!="" ){
		  		var d1 = Date.parse(startdate);
		  		var d2 = Date.parse(enddate);
		  		if ((d2-d1)<=0){
		  			response={
							errordescription:"Nei filtri di ricerca il campo Inizio deve essere precedente al campo Fine"
					}
					errorDialogShow(0, response)
		  			return false;
		  		}
	  		}else{
	  			response={
						errordescription:"Nei filtri di ricerca i campo Inizio-Fine devono essere valorizzati"
				}
				errorDialogShow(0, response)
	  			return false;
	  			
	  		}
	  		
	  		return true;
    	}
    	
    	function getActivities(excelDownload){
    		
    		if (validation()==true){
    		
    		excel= typeof excelDownload !== 'undefined' ? excelDownload : false;
    		$(".addRowElement").removeAttr("disabled");
			$.ajax({
				type: "POST",
				contentType: "application/json; charset=utf-8",
				url: '<c:url value="/home/activity/get" />',
				data: JSON.stringify({
					description:$('#description').val(),
					startdate: 	$('#startdate').val(),
					enddate:	$('#enddate').val(),
					status:		$('#status').val()
				}),
				dataType: "json",
				success: function (data) {
					
					$.jgrid.gridUnload('#${gridName}'); 
					config={
			    			gridName:		'${gridName}',
			    			colModel:		createActivityColumnModel(),
			    			data:			data,
			    			onLoadComplete:	function(data){
			    				
			    		        	 		  //console.log(data)
			    							  setAltRowscolor();
				    		        		  $("td[aria-describedby='${gridName}_result']").css("text-align","center");
				    		        		  $("td[aria-describedby='${gridName}_result']").css("vertical-align","middle");
			    		        	 
			    							  
			    							},
			    			imgSource:		'<c:url value="/resources/css/images" />',	
			    			urlGetData:		'',
			    			urlSaveData:	'<c:url value="/home/activity/save" />',
			    			urlDeleteData:	'<c:url value="/home/activity/del" />',
			    			isMonitor:	 	false,
			    			refreshGrid: 	function(){},
			    			editGridRow:	true,//${sessionScope.userSessionInfo.canEditActivity()},
			    			onBeforeEditGridRow:function(rowid){
								    				function controlLenghtTextarea(textarea,limit){
								    					
								    					  var length = $(textarea).val().length;
								    					  var maxlenght=limit;
								    					  var length = maxlenght-length;
								    					  if (length<0){
								    						   var response={
								    					    			errordescription:"Campo descrizione,raggiungo limite di 1000 caratteri",
								    					    		    errortitle:"Attenzione!"
								    					       };
								    						   $(textarea).val($(textarea).val().substring(0,maxlenght));
								    					       errorDialogShow(0, response);
								    					      
								    					  }
								    					
								    				}
					    							var row=$("#" + $.jgrid.jqID(rowid));
								    				row.find("td[aria-describedby$='_result']").css("text-align","left");
								    				row.find("td[aria-describedby$='_result']").css("vertical-align","top");
								    				row.find("td[aria-describedby$='_description']").find("textarea").keyup(function() {
								    					controlLenghtTextarea(this,1000);
					    							  });
								    				row.find("td[aria-describedby$='_error']").find("textarea").keyup(function() {
								    					controlLenghtTextarea(this,1000);  
								    					
					    							  });
			    			},
			    			onAfterSaveGridRow:	function(rowid){
					    							var row=$("#" + $.jgrid.jqID(rowid));
								    				row.find("td[aria-describedby$='_result']").css("text-align","center");
								    				row.find("td[aria-describedby$='_result']").css("vertical-align","middle");
							},
			    			deleteGridRow:	${sessionScope.userSessionInfo.canDeleteActivity()},
			    			afterCompleteAdd:getActivities,
			    			autocompleteUser:'<c:url value="/home/user/getuser" />',
			    			validateData:	function(rowid){
						    				var row=$("#" + $.jgrid.jqID(rowid))
						    				row.find("td[aria-describedby$='creationdate']").find("input").attr("disabled","");
						    				<c:if test="${not sessionScope.userSessionInfo.isAmministrator()}">
						    					row.find("td[aria-describedby$='_owner']").find("input").attr("disabled","");
						    					row.find("td[aria-describedby$='_startdate']").find("input").attr("disabled","");
						    					row.find("td[aria-describedby$='_enddate']").find("input").attr("disabled","");
						    					row.find("td[aria-describedby$='_description']").find("textarea").attr("disabled","");
						    				</c:if>
						        			
						    				var value=""
						    				var status=row.find("td[aria-describedby$='_status']").find("select")
						    				if (typeof status.size=='undefined' ){//|| typeof status.size=='function'
						    					value=row.find("td[aria-describedby$='_status']").attr("title");	
						    				}else{
						    					value=row.find("td[aria-describedby$='_status']").find("select").val();
						    				}
						    				if  (typeof value=='undefined' || value==""){
					    						value=row.find("td[aria-describedby$='_status']").attr("title")
					    					}
						    				
						    				
			    		        	 
						    				var result=row.find("td[aria-describedby$='_result']").find("select")
						        			if (value=="2" || value=='Chiuso'){//CLOSED
						        				result.removeAttr("disabled");
						        			
						        				if (typeof result.size=='undefined' ){//|| typeof result.size=='function'
							    					value=row.find("td[aria-describedby$='_result']").attr("title");
						        				}else{
							    					value=row.find("td[aria-describedby$='_result']").find("select").val();
						        				}
						        				
						        				if  (typeof value=='undefined' || value==""){
					        						value=row.find("td[aria-describedby$='_result']").attr("title");
					        					}
						        				
						        				if (value=="true" || value=="Positivo"){
						        					row.find("td[aria-describedby$='_faildescription']").find("textarea").attr("disabled","");
						        				}else{
			    	                            	row.find("td[aria-describedby$='_faildescription']").find("textarea").removeAttr("disabled");
						        				}
						        				
						        			}else{
						        				result.attr("disabled","");
						        				row.find("td[aria-describedby$='_faildescription']").find("textarea").attr("disabled","");
						        				if (result.size()==0){
						        					row.find("td[aria-describedby$='_result']").html("");
						        					row.find("td[aria-describedby$='_faildescription']").html("");
						        				}
						        			}
			    			}
			    				
			    		}
			    		initTable(config)
			    		$('#${gridName}').jqGrid('setCaption', 'Risultati');
// 			    		<c:if test="${sessionScope.userSessionInfo.canAddActivity()}">
// 			    			$('#${gridName}').jqGrid('setLabel', 'custom', 'Comandi <button type="button" class="btn addRowElement" style="padding: 0px 6px;" onclick="addRowTable();"><i class="fa fa-plus fa-fw"></i></button>');
// 			    		</c:if>
			    		
				    	if (excel==true){
				    		
				    		window.location.href = $('#downloadexcel').attr('href') 
				    	}
	
				    	excel=false;	
			    	
				    	
				},
				error:  function (jqXHR, timeout, message) {
					response={
							errortitle:"Sessione scaduta",
							errordescription:"Automaticamente verrai rimandato alla login!"
					}
					errorDialogShow(0, response)
				    var contentType = jqXHR.getResponseHeader("Content-Type");
				    if (jqXHR.status === 200 && contentType.toLowerCase().indexOf("text/html") >= 0) {
				    	 
				        // assume that our login has expired - reload our current page
				        window.location.reload();
				    }
				}
		});
			
    	}//if validation
	   }
    </script>

