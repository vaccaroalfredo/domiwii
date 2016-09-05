package dashboard.web.controller;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dashboard.db.jpa.Action;
import dashboard.db.jpa.Device;
import dashboard.db.jpa.State;
import dashboard.db.service.ActionService;
import dashboard.db.service.DeviceService;
import dashboard.db.service.SchedulerService;
import dashboard.web.context.SpringApplicationContext;
import dashboard.web.model.ArduinoMessage;
import dashboard.web.model.BotAction;

@RestController
public class BotController extends LoggerUtils{
	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	@RequestMapping(value = "/addBotAction", method = RequestMethod.POST)  
	public Boolean getAction(@RequestBody(required = false) BotAction params){
		
		this.debugMessage(logger, "addBotAction");
		
		Action action =  new Action("action","descrizione", State.WAITING);
		
		ActionService as = (ActionService) SpringApplicationContext.getServiceBean("actionService");
		
		as.addAction(action);
		
		Device device = new Device("pippoDevice");
		
		DeviceService ds = (DeviceService) SpringApplicationContext.getServiceBean("deviceService");
		
		ds.addDevice(device);
		
		SchedulerService ss = (SchedulerService) SpringApplicationContext.getServiceBean("schedulerService");
		
		ss.addScheduledAction(action, device);
		
		
		return true;//+params.getId();
	}
	
	
	
/*
 * 
 * ws addBotAction
 * @param String idDevice; mode; temperature; speed;
 * return boolean
 * {
    "devId": "fwef",
    "mode": "mode",
    "speed": "23",
    "temperature": "22"
}
 * */
	
}


