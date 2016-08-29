<%@include file="../include.jsp"%>
<c:set var="gridName" scope="page" value="jqUserGrid" />
<form:form id="searchForm" class="form-horizontal" method="GET" modelAttribute="userFilterBean" >
  <div class="form-group">
    <label for="user" class="col-sm-1 control-label">Utente</label>
    <div class="col-sm-3">
      <form:input type="text" class="form-control" id="user" path="user" placeholder="Utente" />
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-1">
      <input type="button" class="btn btn-default" value="Ricerca" onclick="getUsers();"/>
    </div>
    <c:if test="${sessionScope.userSessionInfo.isAmministrator()}">
	    <div class="col-sm-1">
	      <button type="button" class="btn btn-default addRowElement" onclick="addRowTable('${gridName}');">Aggiungi utente</button>
	    </div>
    </c:if>
  </div>
</form:form> 

	
	<%@include file="modal.jsp"%>
	<table id="${gridName}"></table>
    <div id="${gridName}Pager"></div>
   
    <script type="text/javascript" src="<c:url value="/resources/js/application/jqgridtable.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/application/user_col_model.js"/>"></script>
 	
 	

    <script type="text/javascript"> 
    	$(document).ready(function () {
    		getUsers();
    		$('#user').autocomplete({
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
    	
    	function getUsers(){
    		
    		$(".addRowElement").removeAttr("disabled");
			$.ajax({
				type: "POST",
				contentType: "application/json; charset=utf-8",
				url: '<c:url value="/home/user/get" />',
				data: JSON.stringify({
					user: 	$('#user').val()
					
				}),
				dataType: "json",
				success: function (data) {
					
					$.jgrid.gridUnload('#${gridName}'); 
					config={
			    			gridName:		'${gridName}',
			    			colModel:		createUserColumnModel(),
			    			data:			data,
			    			onLoadComplete:	function(data){
			    								setAltRowscolor();
			    								var select=$("td[aria-describedby$='_profile'][title='Amministrativo']");
			    								if (select.size()==1){
			    									select.closest("tr").find("a[id^='deletepicture']").css("display","none");//attr("disabled","");
			    								}
			    								<c:if test="${sessionScope.userSessionInfo.isAmministrator()}">
			    									var select=$("td[aria-describedby$='_username'][title='${sessionScope.userSessionInfo.userData.username}']");
			    									select.closest("tr").find("a[id^='deletepicture']").css("display","none");
			    								</c:if>
			    								
			    								
			    			},
			    			imgSource:		'<c:url value="/resources/css/images" />',	
			    			urlGetData:		'',
		        			urlSaveData:	'<c:url value="/home/user/save" />',
		        			urlDeleteData:	'<c:url value="/home/user/del" />',
		        			onBeforeEditGridRow:		function(rowid){
		        				
		        				<c:if test="${sessionScope.userSessionInfo.isAmministrator()}">
								var select=$("td[aria-describedby$='_id'][title='${sessionScope.userSessionInfo.userData.id}']");
								select.closest("tr").find("a[id^='deletepicture']").css("display","none");
								</c:if>
		        				
		        			},
							onAfterSaveGridRow:			function(rowid){},
	        				editGridRow:	${sessionScope.userSessionInfo.isAmministrator()},
	            			deleteGridRow:	${sessionScope.userSessionInfo.isAmministrator()},
	            			afterCompleteAdd:getUsers,
	        				validateData: 	 function(rowid){
				        					var row=$("#" + $.jgrid.jqID(rowid));
				        					var select=row.find("td[aria-describedby$='_profile']").find("select")
				        					if (select!=null){
					        					if (select.val()=="0"){
					        						select.attr("disabled","");
					        					}
				        					}
	        				}
			    		}
			    		initTable(config)
			    		$('#${gridName}').jqGrid('setCaption', 'Risultati');
			    		//$('#${gridName}').jqGrid('setLabel', 'custom', 'Comandi <button type="button" class="btn addRowElement" style="padding: 0px 6px;" onclick="addRowTable();"><i class="fa fa-plus fa-fw"></i></button>');

				},
				error:  function (jqXHR, timeout, message) {
					
					
				    var contentType = jqXHR.getResponseHeader("Content-Type");
				    if (jqXHR.status === 200 && contentType.toLowerCase().indexOf("text/html") >= 0) {
				    	response={
								errortitle:"Sessione scaduta",
								errordescription:"Automaticamente verrai rimandato alla login!"
						}
						errorDialogShow(0, response)
				    	// assume that our login has expired - reload our current page
				       // window.setTimeout(function(){
				    	   console.log("Redirect...")
				    	   window.location.reload();
						//}, 2000);
				        
				    }
				} 
		});
	   }
    </script>
    
   

