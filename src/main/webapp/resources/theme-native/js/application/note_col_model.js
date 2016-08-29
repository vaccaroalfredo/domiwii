var createNoteColumnModel=function(highlights){
	var highlights= typeof highlights !== 'undefined' ? highlights : false;
	var col=
[
 			{ label: 'Nota n.', name: 'id', key: true, width: 75 ,sorttype: 'int'}, 
 			{ label: 'Data Creazione',name: 'creationdate',width: 150,editable: true,edittype:"text"},
 			{ label: 'Descrizione',name: 'description',width: 150,editable: true,edittype:"textarea",editrules: {required: true}},
 			{ label: 'Assegnatario',name: 'owner',width: 150,editable: true,edittype:"text"},
 			{ label: 'Attività',name: 'activity',width: 150,editable: true,edittype:"text"},
 			{ label: 'Highlights',name: 'highlights',width: 70,editable: true,edittype:'custom', 
 	    	  editoptions: {	
 	    		  				value:"1:0",
 	    		   				custom_element: function  (value, options) {
 	    		   					var el = document.createElement("input");
 	    		   					el.type="checkbox";
 	    		   					el.align="center";
 	    		   				
 	    		   	     			
 	    		   	     			if (value.indexOf("false")==-1) {
 	    		   	     				el.value="1"
 	    		   	     				el.checked="checked"	
 	    		   	     			}else{
 	    		   	     				el.value="0"
 	    		   	     				el.checked=""
 	    		   	     			}
 	    		   	     			return el;
 	    		   				},
 		    					custom_value:function (elem, operation, value) {
 		    					    if(operation === 'get') {
 		    					        return $(elem).val();
 		    					     } /*else if(operation === 'set') {
 		    					        $('input',elem).val(value);
 		    					     }*/
 		    					 },
 		    					dataEvents: [
 	         					             { type: 'change', data: {  }, fn: function(e) {                        	  		

	 			                                 	if (e.target.value=="0"){
	 			                                 		e.target.checked="checked";
	 			                                 		e.target.value="1";
	 	         					             	}else{
	 	         					             		e.target.checked="";
	 	         					             		e.target.value="0";
	 	         					             	}	
	 	         					             }
 	         					             }
 	                           
 	         					             ]	

 	         					
 	         	},
 	         	formatter: function (cellValue, options, rowObject) {
	 				 if (cellValue=="1" || cellValue=="true")
	 					 return "true";
	 				 else if (cellValue=="0")
	 					return "false";
	 				 
	 				 return "false";
	 		    }	
 			
 			},
 			{ label: 'Archiviata',name: 'archive',width: 70,editable: true,edittype:"custom",
 				 editoptions: {	
		  				value:"true:false",
		   				custom_element: function  (value, options) {
		   					var el = document.createElement("input");
		   					el.type="checkbox";
		   					el.align="center";
		   				
		   	     			
		   	     			if (value.indexOf("true")==-1) {
		   	     				el.value="false";
		   	     				el.checked="";
		   	     			}else{
		   	     				el.value="true";
		   	     				el.checked="checked";	
		   	     					
		   	     				
		   	     			}
		   	     			return el;
		   				},
  					custom_value:function (elem, operation, value) {
  					    if(operation === 'get') {
  					        return $(elem).val();
  					     } /*else if(operation === 'set') {
  					        $('input',elem).val(value);
  					     }*/
  					 },
  					dataEvents: [
   					             { type: 'change', data: {  }, fn: function(e) {                        	  		

		                                 	if (e.target.value=="false"){
		                                 		e.target.checked="checked";
		                                 		e.target.value="true";
       					             	}else{
       					             		e.target.checked="";
       					             		e.target.value="false";
       					             	}	
       					             }
   					             }
                     
   					             ]	

   					
			   	}
//			   	,formatter: function (cellValue, options, rowObject) {
//						 if (cellValue=="1")
//							 return "true";
//						 else if (cellValue=="0")
//							return "false";
//						 
//						 return "false";
//				    }	
	

			
			},
 			{
				 label : ' ',
				 name:'custom',
		         width: 120,
		         editable: true,
		         hidden:false,
		         edittype:'custom',
		         editoptions:onEditRowTable(),
		         formatter:createEditCommands(highlights)
			}
 		
 		

 	];
	if (highlights==true){
		
		 col[5].hidden=true;
		 col[6].hidden=true;
		 
	}
	return col;
	
}

var createEditCommands=function(highlights){
	return function (cellvalue, options, rowObject) {
		var img="";
		var config=activeGrid.getActiveGrid(options.gid);
		var editGridRow=config.editGridRow
		var deleteGridRow=config.deleteGridRow
		
		if (editGridRow==true){
			img= '<div align="center">'+
        			'<a id="editpicture'+options.rowId+'" title="Modifica" class="editpicture" onclick="editRowTable('+options.rowId+','+options.gid+')" ><i class="fa fa-pencil-square-o fa-fw"></i></a>'
		}
		
		
	    
	    if (rowObject.archive=="false" || rowObject.archive==false){
	    	if (rowObject.highlights==0 || rowObject.highlights=="0"){
				
		        img=img+'<a id="highpicture'+options.rowId+'" title="Sposta tra gli highlights" style="padding-left: 10px; padding-right: 5px;" class="highpicture" onclick="changeStatusRowTable(0,'+options.rowId+','+options.gid+')" ><i class="fa fa-star-o" aria-hidden="true"></i></a>'
			}else if  (highlights==true){
//				action="mhf";
//				img=img+'<a id="highpicture'+options.rowId+'" title="Rimuovi tra gli highlights" class="highpicture" onclick="changeStatusRowTable(1,'+options.rowId+','+options.gid+')" ><i class="fa fa-long-arrow-up" aria-hidden="true"></i></a>'	
		    }   
	    	
	    	action="mhf";
	        img=img+'<a id="archivepicture'+options.rowId+'" title="Archivia" style="padding-left: 10px; padding-right: 5px;" class="archivepicture" onclick="changeStatusRowTable(2,'+options.rowId+','+options.gid+')" ><i class="fa fa-archive" aria-hidden="true"></i></a>'
	    }    
         
        if(deleteGridRow==true)	{		
        	img=img+'<a id="deletepicture'+options.rowId+'" title="Cancella" class="deletepicture" onclick="deleteRowTable('+options.rowId+','+options.gid+')" ><i class="fa fa-trash-o fa-fw"></i></a>'// <img id="deletepicture'+options.rowId+'" title="Cancella" class="deletepicture" onclick="deleteRowTable('+options.rowId+')" src="'+config.imgSource+'/gtk_delete.png" />'
        }
        
        	
        if (img!=""){	
        	img = img+'</div>';
        }
        return img
	}
}
function changeStatusRowTable(action,rowId,grid){
	 
	  var config=activeGrid.getActiveGrid(grid.id);
	  config.validateData(rowId)
	  lastSelection=rowId;
	  
	  
	  var tdhighlight=null;
	  if (action==0){
		  $(grid).editRow(rowId);
		  tdhighlight=$(grid).find("tr[id="+rowId+"]").find('td[aria-describedby='+config.gridName+'_highlights]')
		  tdhighlight[0].title=1;
		  tdhighlight.find("input").val(1);
		  tdhighlight.find("input")[0].checked=true;
	  }else if (action==1){
		  tdhighlight=$(grid).find("tr[id="+rowId+"]").find('td[aria-describedby='+config.gridName+'_highlights]')
		  $(grid).editRow(rowId);
		  tdhighlight[0].title=true;
		  tdhighlight.find("input").val(true);
		  tdhighlight.find("input")[0].checked=true;
	  }else if (action==2){
		  tdhighlight=$(grid).find("tr[id="+rowId+"]").find('td[aria-describedby='+config.gridName+'_archive]')
		  $(grid).editRow(rowId);
		  tdhighlight[0].title=true;
		  tdhighlight.find("input").val(true);
		  tdhighlight.find("input")[0].checked=true;
	  }
    
    //var result=tdresult.find("select").val()
	  saveRowTable(rowId,grid)
	
}