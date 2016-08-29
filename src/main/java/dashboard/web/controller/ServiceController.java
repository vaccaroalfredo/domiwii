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
		
		
//		Comandi da inviare
//
//		1. SPEGNI
//
//		2. ACCESO CALDO TEMPERATURA[intero compreso tra 16 e 27] VELOCITA_VENTILATORE [intero
//
//		compreso tra 1 e 4]
//
//		3. ACCESO FREDDO TEMPERATURA [intero compreso tra 16 e 27] VELOCITA_VENTILATORE [intero
//
//		compreso tra 1 e 4]
//
//		4. ACCESO DEUMIDIFICATORE TEMPERATURA [intero compreso tra 16 e 27] VELOCITA_VENTILATORE
//
//		[intero compreso tra 1 e 4]
//
//		5. ACCESO VENTILATORE TEMPERATURA [intero compreso tra 16 e 27] VELOCITA_VENTILATORE
//
//		[intero compreso tra 1 e 4]
//
//		6. ACCESO AUTOMATICO INDICE_BENESSERE [intero compreso tra 0 e 10]
//
//		Questi comandi potremmo codificarli in questo modo:
//
//		0 0 0 0 0 : spegni
//
//		1 1 x x y: acceso caldo temperatura xx e velocità ventilatore y
//
//		1 2 x x y: acceso freddo temperatura xx e velocità ventilatore y
//
//		1 3 x x y: acceso deumidificatore temperatura xx e velocità ventilatore y
//
//		1 4 x x y: acceso ventilatore temperatura xx e velocità ventilatore y
//
//		1 5 0 0 0: acceso automatico
		
		String response[] = {"0 0 0 0 0","1 1 2 0 2","1 2 2 1 2","1 3 2 2 1","1 3 2 0 2","1 4 2 0 2","1 5 0 0 0","1 2 2 1 2","1 3 2 2 1","1 3 2 0 2","1 4 2 0 2"};
		
		int index =  (int) (Math.random()*10);
		
		return response[index];//+params.getId();
	}
	
	@RequestMapping(value = "/updateAction", method = RequestMethod.POST)  
	public String updateAction(@RequestBody(required = true) String params){
	
		
		return params;//+params.getId();
	}

}
