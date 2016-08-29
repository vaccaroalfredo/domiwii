$.jgrid.extend({
            setColWidth: function (iCol, newWidth, adjustGridWidth) {
                "use strict";
                return this.each(function () {
                    var $self = $(this), grid = this.grid, colName, colModel, i, nCol;
                    if (typeof iCol === "string") {
                        // the first parametrer is column name instead of index
                        colName = iCol;
                        colModel = $self.jqGrid("getGridParam", "colModel");
                        for (i = 0, nCol = colModel.length; i < nCol; i++) {
                            if (colModel[i].name === colName) {
                                iCol = i;
                                break;
                            }
                        }
                        if (i >= nCol) {
                            return; // error: non-existing column name specified as the first parameter
                        }
                    } else if (typeof iCol !== "number") {
                        return; // error: wrong parameters
                    }
                    grid.resizing = { idx: iCol };
                    grid.headers[iCol].newWidth = newWidth;
                    if (adjustGridWidth !== false) {
                        grid.newWidth = grid.width + newWidth - grid.headers[iCol].width;
                    }
                    grid.dragEnd();   // adjust column width
                    if (adjustGridWidth !== false) {
                        $self.jqGrid("setGridWidth", grid.newWidth, false); // adjust grid width too
                    }
                });
            }
        });
var createActivityColumnModel=function(monitor){
	var ismonitor = typeof monitor !== 'undefined' ? monitor : false;
	var col=[
     { label: 'Attività n.', name: 'id', key: true, width: 75 ,sorttype: 'int'},  
     { label: 'Creato',name: 'creationdate',editable: true,edittype:"text",
	        editoptions: {
	             // dataInit is the client-side event that fires upon initializing the toolbar search field for a column
	             // use it to place a third party control to customize the toolbar
	             dataInit: function (element) {
	             	 $(element).datetimepicker({
	              		//timeFormat:'H:m',
	              		dateFormat: 'd-m-Y',
	              		timepickerScrollbar:false,
	              		timepicker:false,
	              		format:'d-m-Y'
	              	});
	             }
	         }
     },
     {label: 'Stato',name: 'status',editable: true,edittype: "select",
         	editoptions: {
         		value: 		"0:Nuovo;1:In Lavorazione;2:Chiuso",
         		dataEvents: [
                          { type: 'change', data: {  }, fn: function(e) {
                       	  
                            	var myGrid = $('#'+config.gridName)
                     	  	    selRowId = myGrid.jqGrid ('getGridParam', 'selrow')
                      	  	    $td = $(this).closest("td");
	                        	rowid = $td.closest("tr.jqgrow").attr("id");
	                        	config.validateData(rowid);
                      	  	    
                          		}
                          }
                           
                          
               ]
         	},
         	formatter:function (cellvalue, options, rowObject){
    		 	   		 var value=""
    			   		 if (cellvalue == "Closed" || cellvalue=="Chiuso"){
    		        		 value = "Chiuso";
    		        		 //return  '<div class="text-center"><i class="fa fa-hourglass-end hourglass" aria-hidden="true"></i>'+value+'</div>';
    		        	 }else if (cellvalue == "InProgress"|| cellvalue=="In Lavorazione"){
    		        		 value = "In Lavorazione"; 
    		        		 //return  '<div class="text-center"><i class="fa fa-hourglass-half hourglass"  aria-hidden="true"></i>'+value+'</div>';
    		        	 }else if (cellvalue == "New" || cellvalue=="Nuovo"){
    		        		 value = "Nuovo"
    		        	     //return  '<div class="text-center"><i class="fa fa-hourglass-start hourglass" aria-hidden="true"></i>'+value+'</div>';
    		        	 }
    		        	 return value;

    	 }
     },
     {label: 'Inizio',name: 'startdate',editable:true,edittype:"text",
         	//editrules: {required: true},
         	editrules:{ required:true, custom:true, custom_func: function(value,colname){
	         		  	if (value==""){
	         		  		return [false,"Campo Inizio è obbligatorio"];
	         		  	}else if (value!=""){
	         		  		var lastSelection1= typeof lastSelection !== 'undefined' ? lastSelection : 0;
	         		  		var row=$("#" + $.jgrid.jqID(lastSelection1))
	         		  		var enddate=row.find("td[aria-describedby$='_enddate']").find("input").val();
	         		  		if (enddate!=""){
		         		  		var d1 = Date.parse(value);
		         		  		var d2 = Date.parse(enddate);
		         		  		if ((d2-d1)<=0){
		         		  			return [false,"Campo Inizio deve essere precedente al campo Fine"];
		         		  		}
	         		  		}
	         		  	}
	         		  	return [true];
	         		  	
         		} 
         	},
         	editoptions: {
         		dataInit: function (element) {
         				$(element).datetimepicker({
	                 		timeFormat:'H:i',
	                 		dateFormat: 'd-m-Y',
	                 		timepickerScrollbar:false,
	                 		format:'d-m-Y H:i'
	                 		
	                 	});
                }
         	}
     },
     {label: 'Fine',name: 'enddate',editable: true,edittype:"text",
         	editrules: {required: true},
         	editoptions: {
	             // dataInit is the client-side event that fires upon initializing the toolbar search field for a column
	             // use it to place a third party control to customize the toolbar
	             dataInit: function (element) {
		                 $(element).datetimepicker({
		                 		timeFormat:'H:i',
		                 		dateFormat: 'd-m-Y',
		                 		timepickerScrollbar:false,
		                 		format:'d-m-Y H:i'
		                 });
	             }
         	}
     },
     {label : "Descrizione",name: 'description',editable: true,edittype:"textarea",
    	 	editrules: {required: true},
         	editoptions: {
         		rows:"7"//,
         		//cols:"10"
         	},
         	formatter:function (cellvalue, options, rowObject){
         		var cellvalue= typeof cellvalue !== 'undefined' ? cellvalue : "";
         		if (typeof config.isMonitor !== 'undefined'){
					if (config.isMonitor==false)
						return cellvalue;
					else if (cellvalue.length>=35){
						return cellvalue.substring(0, 30)+'...'
					}
         		}else{
         			cellvalue="";
         		}	
				return cellvalue;
         	}
     },
     {label: 'Assegnatario ',name: 'owner',editable: true,edittype: "text",
         	editrules: {required: false},
         	editoptions: {
	             // dataInit is the client-side event that fires upon initializing the toolbar search field for a column
	             // use it to place a third party control to customize the toolbar
	             dataInit: function (element) {
	            	 $(element).autocomplete({
	                     id: 'AutoComplete',
	                     autoFocus: true,
	                 	 source: function(request, response){
	     						this.xhr = $.ajax({
	     							url: config.autocompleteUser,
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
	             }
         	}
     },
     {label: 'Esito',name: 'result',editable: true, //,edittype: "select",
    	 	sorttype: function (cell) {
    	 		if (cell==null)
    	 			return "";
             return cell;
         	},
    	    edittype:'custom', 
    	    editoptions: {	
    		   				custom_element: function myelem (value, options) {
    		   					var el = document.createElement("select");
    		   	     			el.className="form-control";
    		   					el.align="center";
    		   	     			
    		   	     			if (value.indexOf("Positivo")==-1 && value!=""){
    		   	     				el.innerHTML='<option role="option" value="true">Positivo</option><option role="option" value="false" selected="true">Negativo</option>';
    		   	     			} else{
    		   	     				el.innerHTML='<option role="option" value="true" selected="true">Positivo</option><option role="option" value="false" >Negativo</option>';
    		   	     			}
    		   	     			return el;
    		   				},
	    					custom_value:function myvalue(elem, operation, value) {
	    					    if(operation === 'get') {
	    					        return $(elem).val();
	    					     } /*else if(operation === 'set') {
	    					        $('input',elem).val(value);
	    					     }*/
	    					 },
    		   
    		   				value: "true:Positivo;false:Negativo",
         					dataEvents: [
         					             { type: 'change', data: {  }, fn: function(e) {                        	  		

		                                 	var myGrid = $('#'+config.gridName)
		                           	  	    selRowId = myGrid.jqGrid ('getGridParam', 'selrow')
		                           	  	    $td = $(this).closest("td");
		    	                        	rowid = $td.closest("tr.jqgrow").attr("id");
		    	                        	config.validateData(rowid);
			   
         					             	}	
         					             }
                           
         					             ]	
         	},
         	formatter:function (cellvalue, options, rowObject) {
			        	 var value="";
			        	 if (rowObject.status=="Chiuso" || rowObject.status=="Closed"){
				        	 if (cellvalue == "true" || cellvalue == true || cellvalue ==  "Positivo" || (typeof (cellvalue)=="string" && cellvalue.indexOf("Positivo")>=0)){
				        		 value = "Positivo";
				        		 return  '<div class="text-center labelcolored labelresult-ok">'+value+'</div>';
				        	 }else if (cellvalue == "false" || cellvalue == false || cellvalue ==  "Negativo" || (typeof (cellvalue)=="string" && cellvalue.indexOf("Negativo")>=0)){
				        		 value = "Negativo"; 
				        		 return  '<div class="text-center labelcolored labelresult-ko">'+value+'</div>';
				        	 }
			        	 } 
			        	 return value;
         	}
     },
     {label : "Errore",name: 'faildescription',editable: true,edittype:"textarea",
         	editoptions: {
         		rows:"7"
         	}
     },
     {label : ' ',name:'custom',editable: true,hidden:ismonitor,edittype:'custom',
	         editoptions:onEditRowTable(),
	         formatter:createEditCommands()
     },
     {label : 'Allarme',name:'alarm',editable: true,hidden:!ismonitor,edittype:'custom',width:60,
         editoptions:onEditRowTable(),
         formatter:function (cellvalue, options, rowObject) {
        			var img='<div id="semaphore'+options.rowId+'\" style="position: relative;text-align:center"><i class="fa fa-circle" aria-hidden="true" style="color:green"></i></div>';
        					
        			
        	        return img
        		}
         
     }
 ];
	
 if (monitor==true){
	 col[0].width=70;
	 col[1].hidden=true;
	 col[2].width=150;
	 col[3].width=140;
	 col[4].width=140;
	 col[5].width=400;
	 col[6].width=300;
	 col[7].hidden=true;
	 col[8].hidden=true;
	 col[9].hidden=true;
	 
 }
 return col;
	
}

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
        
        img=img+'<a id="okpicture'+options.rowId+'" title="Chiuso con esito positivo" style="padding-left: 10px; padding-right: 5px;" class="editpicture" onclick="closeStatusRowTable('+options.rowId+','+options.gid+',true)" ><i class="fa fa-thumbs-o-up"></i></a>'
        
        img=img+'<a id="kopicture'+options.rowId+'" title="Chiuso con esito negativo" class="editpicture" onclick="closeStatusRowTable('+options.rowId+','+options.gid+',false)" ><i class="fa fa-thumbs-o-down"></i></a>'
        	
        if (img!=""){	
        	img = img+'</div>';
        }
        return img
	}
}
function closeStatusRowTable(rowId,grid,result){
	  lastSelection=rowId;
	  var config=activeGrid.getActiveGrid(grid.id);
	  config.validateData(rowId)
	  var tdstatus=$(grid).find("tr[id="+rowId+"]").find("td[aria-describedby='jqActivityGrid_status']")
	  var tdresult=$(grid).find("tr[id="+rowId+"]").find("td[aria-describedby='jqActivityGrid_result']")
	  var status=tdstatus[0].title  
	  $(grid).editRow(rowId);
    
    //var result=tdresult.find("select").val()
    
    
	 status="Chiuso";
	 tdstatus[0].title=status;
     tdstatus.find("select").val("2");
    
    if (status=="Nuovo"){
  	  tdstatus[0].title="In Lavorazione";
  	  tdstatus.find("select").val("1");
    }else if (status=="In Lavorazione"){
  	  tdstatus[0].title="Chiuso";
  	  tdstatus.find("select").val("2");
    }else if (status=="Chiuso"){
  	  if (result==true){
	      tdresult.title="Positivo";
  		  tdresult.find("select").val("true")
  	  }else{
  		  tdresult.title="Negativo";
  		  tdresult.find("select").val("false")
  	  }
//	      if (result=="true"){
//	    	  tdresult.title="Negativo";
//  		  tdresult.find("select").val("false")
//  	  }else{
//  		  tdresult.title="Positivo";
//  		  tdresult.find("select").val("true")
//  	  }
	  }
    saveRowTable(rowId,grid)
	
}


//function MakeCellEditable(rowId, colName) {
//    var item = '#'+config.gridName;
//    var rowids = $(item).getDataIDs();
//    var colModel = $(item).getGridParam().colModel;
//
//    for (var i = 0; i < rowids.length; i++) {
//        if (rowId == rowids[i]) {
//            for (var j = 0; j < colModel.length; j++) {
//                if (colModel[j].name == colName) {
//                	
//                    var cm1 = $(item).jqGrid('getColProp',colName);
//                    cm1.editable=true;
//                   
//                    $(item).restoreCell(i, j);
//                    // Put cell in editmode.
//                    // If the edit (third param) is set to false the cell is just selected and not edited.
//                    // If set to true the cell is selected and edited.
//                    $(item).editCell(i, j, true);
//
//                    // Let the grid know that the cell has been changed without having to push enter button or click another cell.
//                    $(item).saveCell(i, j);
//                }
//            }
//        }
//    }
//}

//var customInputDate=function customInputDate(value,opt){
//	var el = document.createElement("input");
//	  el.type="text";
//	  el.className="form-control"
//	  el.value = value;
//	  return el;
//	
//}
//var customInputDateValue=function customInputDateValue(elem, operation, value){
//	 if(operation === 'get') {
//	       return $(elem).val();
//	    } else if(operation === 'set') {
//	       $('input',elem).val(value);
//	    }
//	
//}
//function customDateValidation(val, colname) {
//        if (val.trim() == "") {
//        	return [ false,"Data è richiesta " ];
//        } else {
//        	return [ true, "" ];
//        }
//
//}
