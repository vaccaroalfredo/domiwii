var createUserColumnModel=function(){
	
	var col=
[
 			{ label: 'Utente n.', name: 'id', key: true, width: 75,sorttype: 'int' }, 
 			{ label: 'Utente',name: 'user',width: 150,editable: true,edittype:"text",editrules: {required: true}},
 			{ label: 'Username',name: 'username',width: 150,editable: true,edittype:"text",editrules: {required: true}},
 			{ label: 'Password',name: 'password',width: 150,editable: true,edittype:"password",editrules: {required: true},
 			  formatter: function(cellvalue, options, rowObject){
 				  var value = typeof cellvalue !== 'undefined' ? cellvalue : "";	
 				  if ( value.length>0)
 				  	return "*********";
 				  else
 					  return value;
 			  },
 			  hidden: false, 
 			  editrules:{ 
			 	  edithidden: false,//, // We are using a custom verification 
			 	  custom:true, // This is the function we have created 
			 	  custom_func: function (cellvalue, cellname) { 
			 		  // Get a reference to the password check input box.
			 		 // var passCheckVal = $("#tr_passwordCheck input").val() 
			 		  // If both fields are empty or the passwords match // we can submit this form. 
			 		  //if ( (cellvalue == "" && passCheckVal == "") || cellvalue == passCheckVal ) { return [true, ""]; } 
			 		  // Can you guess why we are here? 
			 		  //return [false, "Password and password check don't match."];
			 		  return [true];
			 	  }
 			  }
 			},
 			{ 	
		 		label: 'Tipo',
		 		name: 'profile',
		 		width: 100,
		 		editable: true,
		 		edittype: "select",
		 		editoptions: {
		          value: "1:Operativo;0:Amministrativo"
		 		},
		 		formatter:function (cellvalue, options, rowObject){
		 		   if (cellvalue == "Administration")
		 		       return "Amministrativo";
		 		   else if (cellvalue == "Operational")
		 			   return "Operativo";
		 		   
		 			   
		 			   return cellvalue
		
		 		}
 			},
 			{
				 label : ' ',
				 name:'custom',
		         width: 120,
		         editable: true,
		         hidden:false,
		         edittype:'custom',
		         editoptions:onEditRowTable(),
		         formatter:createEditCommands()
 			}

 	];
	return col;
	
}