package dashboard.web.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dashboard.web.model.ArduinoMessage;

@RestController
public class ServiceController {
	
	@RequestMapping(value = "/pippo", method = RequestMethod.GET)  
	public String getPippo(){
		
		return "pippo";
	}
	@RequestMapping(value = "/getAction", method = RequestMethod.POST)  
	public String getAction(@RequestBody(required = false) String params){
		
		

		
		String response[] = {"0 0 0 0 0","1 1 2 0 2","1 2 2 1 2","1 3 2 2 1","1 3 2 0 2","1 4 2 0 2","1 5 0 0 0","1 2 2 1 2","1 3 2 2 1","1 3 2 0 2","1 4 2 0 2"};
		
		int index =  (int) (Math.random()*10);
		
		return response[index];//+params.getId();
	}
	
	@RequestMapping(value = "/updateAction", method = RequestMethod.POST)  
	public String updateAction(@RequestBody(required = true) String params){
	
		
		return params;//+params.getId();
	}

}
