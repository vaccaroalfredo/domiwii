	<style type="text/css">
	#containerbody {
		background: #122341;
		color:#FFFFFF ;
		position: absolute; 
		top: 0; 
		left: 0;
		height:100%;
		width:100%;
	
	}
	

	/*Remove border on grid*/
	.ui-jqgrid tr.ui-row-ltr td { border-right-color: transparent; }
    .ui-jqgrid tr.ui-row-ltr td { border-bottom-color: transparent; }
    .ui-jqgrid { border-right-width: 0px; border-left-width: 0px; }
    th.ui-th-column { border-right-color: transparent !important }


	.monitorRowClassEven{
		background-color: #5D8EAC;
	
	}
	
    .monitorRowClassOdd{
		background-color: #D0E2F0;
		
	}
	
	.monitorRowClassEven > td{

		color: #FFFFFF !important; 
	}
	.monitorRowClassOdd > td{
		
		color: #000000 !important; 
	}
	
	/*Customize color highlight*/
	.table > tbody > tr.active > td, .table > tbody > tr.active > th, .table > tbody > tr > td.active, .table > tbody > tr > th.active, .table > tfoot > tr.active > td, .table > tfoot > tr.active > th, .table > tfoot > tr > td.active, .table > tfoot > tr > th.active, .table > thead > tr.active > td, .table > thead > tr.active > th, .table > thead > tr > td.active, .table > thead > tr > th.active {
    background-color: transparent;
	}


	
	
</style>
	<%@include file="../sky.jsp"%>
	<div class="row" style="font-size: 26px;vertical-align:middle;width:100%; margin-bottom: 10px;">
	  <div class="col-md-8" style="float:left; text-align: left;">
	  <a href="<c:url value="/home"/>" title="Indietro"><i class="fa fa-chevron-circle-left" aria-hidden="true"></i></a>
	  <strong>MONITOR</strong></div>
	  <div style="font-size: 21px;" class="col-md-2" id="clock"></div>
	  <div class="col-md-2">			
	  		<div class="pull-right" style="margin-right: 20px">
	  			<a title="Disconnetti come ${sessionScope.userSessionInfo.getUser()}" class="" href="<c:url value="logout"/>">
	  			<i aria-hidden="true" class="fa fa-user-times" style="color:white"></i>
	  			</a>
	  		</div>	
	  </div>
<%-- 	  <div class="col-md-3"><img alt="" style="height:40px" src="<c:url value="/resources/css/images/logonetcom.png" />" class="pull-right"></div> --%>
	</div>
	
	<c:set var="gridName" scope="page" value="jqMonitorGrid" />
	<table id="${gridName}"></table>
	<%@include file="modal.jsp"%>
	<script type="text/javascript" src="<c:url value="/resources/js/application/clock.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/application/jqgridtable.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/application/activity_col_model.js"/>"></script>
 	<script type="text/javascript"> 
    	$(document).ready(function () {
    		//$("#containerbody").css({ background: "#122341",color:"#FFFFFF" })
    		$.ajax({
				type: "GET",
				contentType: "application/json; charset=utf-8",
				url: '<c:url value="/home/activity/monitor" />',
				data: JSON.stringify({
				     timestamp:  new Date().toString()
				    }),
				dataType: "json",
				success: function (data) {
					
		    		function refreshGrid(){
		    			
		    				$.ajax({
		    				type: "GET",
		    				contentType: "application/json; charset=utf-8",
		    				url: '<c:url value="/home/activity/monitorsetting" />',
		    				data: JSON.stringify({
		    				     timestamp:  new Date().toString()
		    				    }),
		    				dataType: "json",
		    				success: function (data) {	
		    							config.minuteToCriticActivity = data.minutetodefinecritic;
		    							config.secondToRefresh=data.refreshpagemonitor;
		    							config.rowsToShow=data.numberrowstosee;
		    			
						  	           	var grid = jQuery('#'+config.gridName);  	           
						  	           	grid.jqGrid('setGridParam',{rowNum: 	config.rowsToShow ,sortname: 'id', sortorder: "desc" ,datatype:'json',url:'<c:url value="/home/activity/monitor" />'}).trigger('reloadGrid');
						  	           	$('#'+config.gridName).closest(".ui-jqgrid").find('.loading').hide();
					  	           
		    					}
		    				});
			
		  	        }
		    		config={
		    			gridName:					'${gridName}',
		    			colModel:					createActivityColumnModel(true),
		    			data:						data,
		    			onLoadComplete:				function(data){
									    				
									    				$(this).find(">tbody>tr.jqgrow:odd").addClass("monitorRowClassEven");
									                    $(this).find(">tbody>tr.jqgrow:even").addClass("monitorRowClassOdd");
									    				
									                    $('td[title="In Lavorazione"]').closest("tr").find('td[aria-describedby="${gridName}_alarm"]').find("i").css({color: "yellow"});  

	    											 //$('#${gridName}').css({ background: "#000000" });
// 	    											 $("tr.jqgrow:odd").css("background","#357CE0");
// 									    			 $("tr.jqgrow:odd").css("color","#FFFFFF");
// 									    			 $("tr.jqgrow:even").css("background","#FFFFFF");
// 									    			 $("tr.jqgrow:even").css("color","#000000");
									    			 
									    			 
									    			// $(".active").css("background","#B5244A");
//	    											 $('table#jqMonitorGrid.ui-jqgrid-btable.ui-common-table.table.table-bordered').css({ borderCollapse: "inline" });
// 	    											 $('table#jqMonitorGrid.ui-jqgrid-btable.ui-common-table.table.table-bordered').find("td:not(:first-child)").css({ border: "1px inline black" });
	    											 
// 	    											 $('table#jqMonitorGrid.ui-jqgrid-btable.ui-common-table.table.table-bordered').find("tr:not(:first-child)").css({ border: "10px solid black" });
		    	 		
// 		    										 $('tbody').find('tr').css({background: "#22AA54"});
// 								    				 $('td[title="In Lavorazione"]').closest("tr").css({background: "#C6C305"});
									           		 var element=$('td[aria-describedby="${gridName}_startdate"]').filter(function () {
									           			 if  ( $(this).prop("title")=="") return false;
									           			
									           			 if  ( $(this).parent().find('td[title="Nuovo"]').size() > 0 ){
									   	        			 var eventData=Date.parse( $(this).prop("title")).add({minute:-config.minuteToCriticActivity});
									   	        			 var today=new Date();
									   	        			 return today > eventData;
									           			 }
									           			 return false;
									           			 
									           		 });
									           		 
									           	    element.closest("tr").find('td[aria-describedby="${gridName}_alarm"]').find("i").css({color: "red"})
									           	    var elementTOmove=element.closest("tr").find('td[aria-describedby="${gridName}_alarm"]').find("div")
													function blink() {
												    	elementTOmove.fadeTo(100, 0.1).fadeTo(200, 1.0);
												    }
									           	    
									           	    setInterval(function(){blink()}, 1000);
                
              
												    
									           	    
										           	/*  function loop(value){
										           	        var move="+=50";
										           	        if (value==false){
										           	     	 move="-=50";
										           	        }
										           			elementTOmove.animate({
										           				//width: 'toggle'
// 										           	            opacity: 0.25,
 										           	            left: move,
// 										           	            height: 'toggle'
										           	        }, 500, function() {
										           	            loop(!value);
										           	        });
	
										           	}
													loop(true)	 */
								           			window.setTimeout( config.refreshGrid, config.secondToRefresh *1000)
 								           			$('#${gridName}').setGridWidth($(window).width()-80);
									           		
													},
		    			urlGetData:					'',
		    			urlSaveData:				'',
		    			urlDeleteData:				'',
		    			onBeforeEditGridRow:		function(rowid){},
						onAfterSaveGridRow:			function(rowid){},
		    			rowsToShow:					${appsettingBean.numberrowstosee},
		    			imgSource:					'<c:url value="/resources/css/images" />',	
		    			isMonitor:	 				true,
		    			refreshGrid: 				refreshGrid,
		    			secondToRefresh:			${appsettingBean.refreshpagemonitor},
		    			minuteToCriticActivity:		${appsettingBean.minutetodefinecritic},
		    			afterCompleteAdd:			function(){},
		    			validateData:				function(rowid){}
		    		}
		    		initTable(config)
		    		$('#${gridName}').jqGrid('setCaption', 'Attivit&agrave; in corso');
//  		    		$('#${gridName}').hideCol("creationdate");
// 		    		$('#${gridName}').hideCol("result");
// 		    		$('#${gridName}').hideCol("faildescription");
					$('#${gridName}').jqGrid('setGridParam',{altRows:false,pager:'',rowNum:${appsettingBean.numberrowstosee}});
					$('#${gridName}').bind("jqGridBeforeSelectRow", function (e, rowid, orgClickEvent) {
					   //Make not selectable row
					    return false;
					});
					
					//To make refresh width 
					$('#${gridName}').trigger("reloadGrid");
				}
				
    		});	
        });
    	
    </script>

