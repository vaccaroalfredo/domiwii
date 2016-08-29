//Configurazione gridtable
//var config={
//		gridName:					'idDomGrid',
//		data:						"datiinjsondacaricarenellagriglia"
//		onLoadComplete:				function(data){}
//		onBeforeEditGridRow:		function(rowid){},
//		onAfterSaveGridRow:			function(rowid){},
//		urlGetData:					'',
//		urlSaveData:				'',
//		urlDeleteData:				'',
//		rowsToShow					'righedamostrare'
//		colModel					'colonne da mostrare'		
//		validateData:				function(rowid){}
//}
var activeGrid={
		configuration:[],
		initPageTables:	function(configurations){
							if ($.isArray(configurations)){
								for (var i=0;i<=configurations.size();i++){
									this.addPageTable(configurations[i]);
								}
							}else{
								this.addPageTable(configurations);
							}
		},	
		restartPageTables:function(configurations){
							this.configuration=[];
							this.initPageTables(configurations);
		},
		addPageTable:	function(configuration){
							this.configuration[configuration.gridName]=configuration;
		},
		getActiveGrid:	function(gridName){
							return this.configuration[gridName];
		}
		
}
var lastSelection;
var emptyRowID=0;

//See documentation on jqgrid at http://www.trirand.com/jqgridwiki/doku.php?id=wiki:inline_editing
$.extend($.jgrid,{
    info_dialog: function (caption, content, c_b, modalopt) {
    	 var response={
    			errordescription:content
    			
    	}
         errorDialogShow(0, response)
       
    }
});



//INIZIALIZZAZIONE GRIGLIA
function initTable(configuration,registryOnActiveGrid){
	var config=configuration;
	var registryOnActiveGrid= typeof registryOnActiveGrid !== 'undefined' ? registryOnActiveGrid : true;
	if (registryOnActiveGrid){
		activeGrid.restartPageTables(configuration);//gestire sia singolo che array
	}else{
		activeGrid.addPageTable(configuration);
	}
	
	$("#"+config.gridName).jqGrid({
		data: 			config.data,
						//Se esistono dati, la griglia si popula altrimenti va a richiederli all'url
		datatype: 		config.data==null ? "json":"local",
        url: 			config.data==null ? config.urlGetData:"",
        mtype: 			"GET",
        page: 			1,
        rowNum: 		config.rowsToShow,
        loadonce : 		true,
        viewrecords: 	true,
        sortable:		true,
        cellsubmit:		"clientArray",//clientArray o remote
        height: 		'auto',
        pager: 			"#"+config.gridName+"Pager",
        closeAfterAdd: 	true,
        ajaxRowOptions:	{
			        	 	type :"POST",
			        	    contentType :"application/json; charset=utf-8",
			        	    dataType :"json"
        },
        serializeRowData:function(postdata){
			        	 	if (postdata.id === emptyRowID)
			        	        postdata.id = null; // rest api expects int or null
			        	 	
			        	    return JSON.stringify(postdata)
         },
         ondblClickRow: function (id) {
			             // edit the row and save it on press "enter" key
			        	 var ismonitor= typeof config.isMonitor !== 'undefined' ? config.isMonitor : false;
			        	 if (ismonitor==false && id != lastSelection){
			        		 editRowTable(id,this)
			        	 }
        	 
         },
         onSelectRow:	function (rowId, status, e) {
        	 			//console.log("onSelectRow "+rowId)
         },
         loadComplete: config.onLoadComplete,
         loadError:    function (jqXHR, textStatus, errorThrown) {
			        	var response={
			        			 errordescription: "Servizio non disponibile!"	 
			        	 }
			        	 errorDialogShow(0,response)
         },
         colModel: 		config.colModel
		
    });
	
}


//EDIT ROW
function editRowTable(rowid,grid) {
	console.log("called function editRowTable on "+rowid+" sulla grid "+grid.id);
	var grid = $("#"+grid.id);//config.gridName);
	var config=activeGrid.getActiveGrid(grid[0].id)
	if (rowid && rowid !== lastSelection) {
       grid.jqGrid('restoreRow',lastSelection);//rimettere senza focus la linea precedente
    }   
    var editParameters = {
		"keys" : false,// keys: when set to true we can use [Enter] key to save the row and [Esc] to cancel editing.
		"oneditfunc" : function(rowid){ 
			  config.validateData(rowid)
			//$("#" + $.jgrid.jqID(rowid) + "_description").focus();
		},
		"successfunc" : null,
		"url" : null,
	    "extraparam" : {},
		"aftersavefunc" : null,
		"errorfunc": null,
		"afterrestorefunc" :null,
		"restoreAfterError" : true,
		"focusField": 0,
		"mtype" : "POST"
     }
     grid.jqGrid('editRow',rowid,editParameters);
     lastSelection = rowid;  // grid.setSelection(rowid);
     var selRowId = grid.jqGrid ('getGridParam', 'selrow')
     grid.jqGrid('setSelection',rowid);
     config.onBeforeEditGridRow(rowid);
    
}
//SAVE ROW
function saveRowTable(rowid,grid){
	console.log("called function saveRowTable on "+rowid+" sulla grid "+grid.id);
	//rowid=el.closest('tr').id
	var config=activeGrid.getActiveGrid(grid.id)
	lastSelection=rowid;
	var saveparameters = {
			"successfunc" : function (serverresponse) {
				
								var error=serverresponse.responseJSON;
								if (error.status<0){
									$(".addRowElement").removeAttr("disabled");
									errorDialogShow(rowid,error);
									return false;
								}
								return true;
				
		    },
			"url" : 		config.urlSaveData,
		    "extraparam" : {},
		    "afterSubmitCell": null,
			"aftersavefunc" : function(rowid, result){
								console.log("After  save "+rowid)
								config.validateData(rowid) 
								var grid=jQuery("#"+config.gridName)
								grid.jqGrid('restoreRow',rowid);
								//
								if (rowid == emptyRowID){
									//Funziona solo il reload dal server[problema con i filtri]
									$(".addRowElement").removeAttr("disabled");
									config.afterCompleteAdd();//getActivities();
									//grid.jqGrid('setGridParam',{sortname: 'id', sortorder: "desc" ,datatype:'json'}).trigger('reloadGrid');
								}else{
									grid.trigger("reloadGrid");
									//Bug #3291
									if ($(".addRowElement").size()>0 && $(".addRowElement")[0].hasAttribute("disabled")){
										 $(".addRowElement").removeAttr("disabled");
										 config.afterCompleteAdd();
									}	 
								}
								
							//	grid.setSelection(rowid);
								config.onAfterSaveGridRow(rowid)
			
			},
			"errorfunc": function(a,jqXHR,c){
								console.log("Error on save")
								
							    var contentType = jqXHR.getResponseHeader("Content-Type");
							    if (jqXHR.status === 200 && contentType.toLowerCase().indexOf("text/html") >= 0) {
							    	var response={
											errortitle:"Sessione scaduta",
											errordescription:"Automaticamente verrai rimandato alla login!"
									}
									errorDialogShow(0, response) 
							        // assume that our login has expired - reload our current page
							        window.location.reload();
							    }
			},
			"afterrestorefunc" : null,
			"restoreAfterError" : true,
			"mtype" : "POST"
			
		}
		jQuery("#"+grid.id).jqGrid('saveRow',rowid,  saveparameters);//config.gridName

}
//ADD ROW
function addRowTable(gridid){
	console.log("called function addRowTable on grid "+gridid);
	var rowid =emptyRowID //jQuery().Guid.New();
	var grid=jQuery("#"+gridid)
	grid.jqGrid('restoreRow',lastSelection);//rimettere senza focus la linea precedente
	 
	var addParameters = {	
	    rowID : rowid,//"_empty",
	    initdata : {},
	    position :"first",
	    useDefValues : false,
	    useFormatter : false,
	    addRowParams : {extraparam:{}}
	}
	grid.jqGrid('addRow',  addParameters);
	$(".addRowElement").attr("disabled","disabled");
	config.validateData(rowid)
}
//DELETE ROW
function deleteRowTable(rowid,grid){
	 	// grid.trigger("reloadGrid", [{page:grid[0].p.page}]);
		var gridid=typeof grid[0] !== 'undefined' ? grid[0].id : grid.id; 
		console.log("called function deleteRowTable on "+rowid+" sulla grid "+gridid);
		var grid = $("#"+gridid);
		var config=activeGrid.getActiveGrid(gridid)
    	var options ={
    		"url" : config.urlDeleteData,//'activity/del',
    		"mtype":"POST",
    		"closeOnEscape":true,
    		//"modal":false,
    		"reloadAfterSubmit":true,
    		"afterSubmit":function(serverresponse){
	    			jQuery("#delmod"+config.gridName).hide()
	    			var error=serverresponse.responseJSON;
					if (error.status<0){
						errorDialogShow(rowid,error);
						return false
					}
					return true
    			
    		},
    		"afterShowForm" : function ($form) {
    		    $form.closest('div.ui-jqdialog').position({
    		        my: "center",
    		        of: $("#"+config.gridName).closest('div.ui-jqgrid')
    		    });
    		},
    		"beforeShowForm": function ($form) {
    		    $("td.delmsg", $form[0]).html("Cancellare record <b>numero=" +
    		         $("#"+config.gridName).jqGrid('getGridParam','selrow') + "</b>?");
    		}
    			
    	}
    	//var grid=jQuery("#"+config.gridName)
    	grid.jqGrid('setSelection',rowid)
    	grid.jqGrid('delGridRow', rowid, options );
       // $(this).dialog('close');
	           
	
}


//UTILITY FUNCTION
var createEditCommands=function(){
	
	return function (cellvalue, options, rowObject) {
		var img="";
		var config=activeGrid.getActiveGrid(options.gid);
		var editGridRow=config.editGridRow
		var deleteGridRow=config.deleteGridRow
		
		if (editGridRow==true){
			img= '<div align="center">'+
        			'<a id="editpicture'+options.rowId+'" title="Modifica" class="editpicture" onclick="editRowTable('+options.rowId+','+options.gid+')" ><i class="fa fa-pencil-square-o fa-fw"></i></a>'
		}
        if(deleteGridRow==true)			
        	img=img+'<a id="deletepicture'+options.rowId+'" title="Cancella" class="deletepicture" onclick="deleteRowTable('+options.rowId+','+options.gid+')" ><i class="fa fa-trash-o fa-fw"></i></a>'// <img id="deletepicture'+options.rowId+'" title="Cancella" class="deletepicture" onclick="deleteRowTable('+options.rowId+')" src="'+config.imgSource+'/gtk_delete.png" />'
                	
        if (img!=""){	
        	img = img+'</div>';
        }
        return img
	}
}
var onEditRowTable= function(){
	
	var aa= {
     	custom_element: function(value, options){ 
     			var el = document.createElement("div");
     			el.align="center"
     			el.innerHTML='<a id="savepicture'+options.rowId+'" title="Salva" class="savepicture" onclick="saveRowTable('+options.rowId+','+this.id+')"><i class="fa fa-floppy-o fa-fw"></i></a>';
     			return el;
     	}, 
     	custom_value: function(elem, operation, value){ 
     				return $(elem).val();
     	} 
     	
	
	
	}
	return aa;
}

function setAltRowscolor(){  
	$("tr.jqgrow:odd").css("background","#FAFBFC");//28E0ED
	$("tr.jqgrow:even").css("background","#FFFFFF");
}
function errorDialogShow(rowID, response) {
	var title= typeof response.errortitle !== 'undefined' ? response.errortitle : '';
	if (title!=''){
		$("#dialog-confirm").find(".modal-title").html(response.errortitle);
	}
	$("#dialog-confirm").find(".modal-body").html("<p><span class=\"ui-icon ui-icon-alert\" style=\"float:left; margin:0 7px 20px 0;\"></span>"+response.errordescription+"</p>");
	$("#dialog-confirm").modal()
}
