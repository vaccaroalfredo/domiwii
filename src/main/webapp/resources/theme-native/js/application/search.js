function initSearchElement(idForm,idStartDate,idEndDate){
	initDateField(idStartDate,new Date().add({day:-7}))
	initDateField(idEndDate,new Date().add({day:3}))
	
	
	
	
}
function initDateField(idDate,valueDate){
	
	$('#'+idDate).datetimepicker({
    		timeFormat:'H:i',
    		dateFormat: 'd-m-Y',
    		timepickerScrollbar:false,
    		format:'d-m-Y H:i',
    		value:valueDate
    		
		});		
//    		.on('changeDate', function(e) {
//    			// Revalidate the date field
//    			$('#'+idDate).val(valueDate);
//    	});
//	
	//$('#'+idDate).val(valueDate);


}
