<%@include file="../include.jsp"%>
<%@include file="../sky.jsp"%>
<div class="row" style="font-size: 26px;vertical-align:middle;width:100%; margin-bottom: 10px;">
	  <div class="col-md-7" style="float:left; text-align: left;">
	  <a href="<c:url value="/home"/>" title="Indietro"><i class="fa fa-chevron-circle-left" aria-hidden="true"></i></a>
	  <strong>Note</strong></div>
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
<c:set var="gridName" scope="page" value="jqNoteGrid" />
<c:set var="gridHighlightsName" scope="page" value="jqNoteHightlightGrid" />

<form:form id="searchForm" class="form-horizontal" method="GET" modelAttribute="noteFilterBean" >
  <div class="form-group">
    <label for="description" class="col-sm-1 control-label">${description}</label>
    <div class="col-sm-3">
      <form:input type="text" class="form-control" id="description" path="description" placeholder="${description}" />
    </div>
    <label for="archive" class="col-sm-1 control-label">Archiviata</label>
    <div class="col-sm-1 checkbox">
      <form:checkbox  class="" id="archive" path="archive"  value="${archive}" placeholder="${archive}" />
    </div>
     <label class="col-sm-1 control-label">Inizio</label>
    <div class="col-sm-2 date">
        <div class="input-group input-append date" >
            <form:input type="text" class="form-control" id="startdate" path="startdate" name="date" />
            <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
        </div>
    </div>
    <label class="col-sm-1 control-label">Fine</label>
    <div class="col-sm-2 date">
        <div class="input-group input-append date" >
            <form:input type="text" class="form-control" id="enddate"  path="enddate" name="date" />
            <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
        </div>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-1">
      <input type="button" class="btn btn-default" value="Ricerca" onclick="getNotes();"/>
    </div>
    <c:if test="${sessionScope.userSessionInfo.isAmministrator()}">
	    <div class="col-sm-1">
	      <button type="button" class="btn btn-default addRowElement" onclick="addRowTable('${gridName}');">Aggiungi nota</button>
	    </div>
    </c:if>
  </div>
</form:form> 

	
	<%@include file="modal.jsp"%>
	<table id="${gridName}"></table>
    <div id="${gridName}Pager"></div>
    
    <br/>
	<br/>
    
    
	<table id="${gridHighlightsName}"></table>
	<div id="${gridHighlightsName}Pager"></div>
    
    <script type="text/javascript" src="<c:url value="/resources/js/application/jqgridtable.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/application/note_col_model.js"/>"></script>
 	<script type="text/javascript" src="<c:url value="/resources/js/application/search.js"/>"></script>
 	

    <script type="text/javascript"> 
    	$(document).ready(function () {
    		initSearchElement("searchForm","startdate","enddate" )
    		getNotesHightlights();
    		$('#owner').autocomplete({
                id: 'AutoComplete',
                autoFocus: true,
            	source: function(request, response){
						this.xhr = $.ajax({
							url: '<c:url value="/home/user/getuser" />',
							type: 'get',
							data: request,
							dataType: "json",
							success: function( data ) {
								response( data );
							},
							error: function(model, response, options) {
								response([]);
							}
						});
				}
            
            });
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
    	
    	function getNotes(refreshHighlights,excelDownload){
    		excel= typeof excelDownload !== 'undefined' ? excelDownload : false;
    		refreshHighlights= typeof refreshHighlights !== 'undefined' ? refreshHighlights : false;
    		
    		if (validation()==true){
    		
					$.ajax({
						type: "POST",
						contentType: "application/json; charset=utf-8",
						url: '<c:url value="/home/note/get" />',
						data: JSON.stringify({
							description:$('#description').val(),
							startdate: 	$('#startdate').val(),
							enddate:	$('#enddate').val(),
							high:		false,
							archive:	$('#archive')[0].checked,
							timestamp:  new Date().toString()
						}),
						dataType: "json",
						success: function (data) {
							
							$.jgrid.gridUnload('#${gridName}'); 
							
							config={
					    			gridName:		'${gridName}',
					    			colModel:		createNoteColumnModel(),
					    			data:			data,
					    			imgSource:		'<c:url value="/resources/css/images" />',	
					    			urlGetData:		'',
				        			urlSaveData:	'<c:url value="/home/note/save" />',
				        			urlDeleteData:	'<c:url value="/home/note/del" />',
			        				editGridRow:	true,
			            			deleteGridRow:	${sessionScope.userSessionInfo.isAmministrator()},
			            			afterCompleteAdd:getNotes,
			            			onAfterSaveGridRow:	function(rowid){
		    							var row=$("#" + $.jgrid.jqID(rowid));
					    				value=row.find("td[aria-describedby$='_highlights']").html();
										if (value=="true")
											getNotesHightlights();
									},
			        				validateData: 	 function(rowid){
			        					
						    				var row=$("#" + $.jgrid.jqID(rowid))
						    				row.find("td[aria-describedby$='creationdate']").find("input").attr("disabled","");
						    				if (row.find("td[aria-describedby$='archive']").find("input").val()=="true"){
						    					row.find("td[aria-describedby$='highlights']").find("input").attr("disabled","");
						    				}
			        					
			        				},
									onBeforeEditGridRow:setControlOnEditNote
									
								}
					    		initTable(config,false)
					    		$(".addRowElement").removeAttr("disabled");
					    		$('#${gridName}').jqGrid('setCaption', 'Risultati');
					    		//$('#${gridName}').jqGrid('setLabel', 'custom', 'Comandi <button type="button" class="btn addRowElement" style="padding: 0px 6px;" onclick="addRowTable();"><i class="fa fa-plus fa-fw"></i></button>');
					    		
					    		
		
						},
						error:  function (jqXHR, timeout, message) {
							
						    var contentType = jqXHR.getResponseHeader("Content-Type");
						    if (jqXHR.status === 200 && contentType.toLowerCase().indexOf("text/html") >= 0) {
						    	setTimeout(function(){
						    		response={
											errortitle:"Sessione scaduta",
											errordescription:"Automaticamente verrai rimandato alla login!"
									}
									errorDialogShow(0, response) 
						    		
						    		var el=$(".modal-footer")
						    		if (el!=null){
						    			el.find("button").trigger("click")
						    		}
						    		location.reload();
						    	},2000);
						    	// assume that our login has expired - reload our current page
						       // window.location.reload();
						    }
						}
				});
			
    		}//if
	   }
    	
	   function getNotesHightlights(){
		    $(".addRowElement").attr("disabled","disabled");
			$.ajax({
				type: "POST",
				contentType: "application/json; charset=utf-8",
				url: '<c:url value="/home/note/get" />',
				data: JSON.stringify({
					description:$('#description').val(),
					startdate: 	$('#startdate').val(),
					enddate:	$('#enddate').val(),
					high:		true
				}),
				dataType: "json",
				success: function (data) {
					
					$.jgrid.gridUnload('#${gridHighlightsName}'); 
					config={
			    			gridName:			'${gridHighlightsName}',
			    			colModel:			createNoteColumnModel(true),
			    			data:				data,
			    			imgSource:			'<c:url value="/resources/css/images" />',	
			    			urlGetData:			'',
		        			urlSaveData:		'<c:url value="/home/note/savehighlights" />',
		        			urlDeleteData:		'',
	        				editGridRow:		true,
	            			deleteGridRow:		false,
	            			afterCompleteAdd:	getNotesHightlights,
	        				validateData: 	 	function(rowid){
						        					var row=$("#" + $.jgrid.jqID(rowid))
								    				row.find("td[aria-describedby$='creationdate']").find("input").attr("disabled","");
	        				},
	        				onAfterSaveGridRow:	function(rowid){
    							var row=$("#" + $.jgrid.jqID(rowid));
			    				value=row.find("td[aria-describedby$='_archive']").html();
								if (value=="true")
									getNotesHightlights();
							},
							onBeforeEditGridRow:setControlOnEditNote
			    		}
			    		initTable(config)
			    		$('#${gridHighlightsName}').jqGrid('setCaption', 'Highlights');
			    		//$('#${gridHighlightsName}').jqGrid('setLabel', 'custom', 'Comandi');
			    		getNotes();
			    		

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
	   }	
	   
	   
	   function setControlOnEditNote(rowid){
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
			row.find("td[aria-describedby$='_description']").find("textarea").keyup(function() {
					controlLenghtTextarea(this,1000);
			  });
				

	}

    </script>
   
   

