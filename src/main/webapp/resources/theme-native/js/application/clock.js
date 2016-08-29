function updateClock ( )
 	{
 	var currentTime = new Date ( );
 	
 	var day=currentTime.getDate();
 	
 	var mounth=currentTime.getMonth()+1;
 	mounth=( mounth < 10 ? "0" : "" )+ mounth;
 	
 	var year=currentTime.getFullYear();
 	
  	var currentHours = currentTime.getHours ( );
  	var currentMinutes = currentTime.getMinutes ( );
  	var currentSeconds = currentTime.getSeconds ( );

  	// Pad the minutes and seconds with leading zeros, if required
  	currentMinutes = ( currentMinutes < 10 ? "0" : "" ) + currentMinutes;
  	currentSeconds = ( currentSeconds < 10 ? "0" : "" ) + currentSeconds;

  	// Choose either "AM" or "PM" as appropriate
  //	var timeOfDay = ( currentHours < 12 ) ? "AM" : "PM";
  	var timeOfDay="";

  	// Convert the hours component to 12-hour format if needed
  	//currentHours = ( currentHours > 12 ) ? currentHours - 12 : currentHours;

  	// Convert an hours component of "0" to "12"
  	//currentHours = ( currentHours == 0 ) ? 12 : currentHours;

  	// Compose the string for display
  	var currentTimeString = day+"/"+mounth+"/"+year+" "+currentHours + ":" + currentMinutes + ":" + currentSeconds + " " + timeOfDay;
  	
  	
   	$("#clock").html(currentTimeString);
   	  	
 }

$(document).ready(function()
{
   setInterval('updateClock()', 1000);
});