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
import dashboard.web.model.BotAuthentication;
import dashboard.web.model.Command;


@RestController
public class BotController extends LoggerUtils {
	private static final Logger logger = Logger.getLogger(LoginController.class);

	/*
	 * 
	 * ws addBotAction
	 * 
	 * @param String idDevice; mode; temperature; speed; return boolean {
	 * "devId": "fwef", "mode": "mode", "speed": "23", "temperature": "22",
	 * "confort": "1", "alias":"alias" }
	 */

	@RequestMapping(value = "/addBotAction", method = RequestMethod.POST)
	public Boolean addBotAction(@RequestBody(required = false) BotAction act) {

		this.debugMessage(logger, "addBotAction");

	//	String command = params.getMode() + "" + params.getTemperature() + "" + params.getSpeed();// +""+params.getConfort();
		String devAlias = act.getAlias();
		if (devAlias == null || devAlias.equalsIgnoreCase("")) {
			
			return false;
			
		}
		
		Command commandObj = new Command();
		
		try {
			
			commandObj.setParameters(act.getStatus(), act.getMode(),  act.getTemperature(), act.getSpeed(), act.getConfort());
			
		} catch (Exception e) {
			
			return false;
			
		}
		
		Action action = new Action("action", "BotCommand", commandObj.buildCommand(), State.WAITING);

		ActionService as = (ActionService) SpringApplicationContext.getServiceBean("actionService");

		as.addAction(action);

		DeviceService ds = (DeviceService) SpringApplicationContext.getServiceBean("deviceService");

		Device dev = ds.getDeviceByAlias(devAlias);

		if (dev == null) {

			return false;

		} else {

			SchedulerService ss = (SchedulerService) SpringApplicationContext.getServiceBean("schedulerService");

			ss.addScheduledAction(action, dev);

		}

		return true;// +params.getId();
	}

	/*
	 * 
	 * ws checkUser
	 * 
	 * @param String Alias; password; return boolean { "alias": "alias",
	 * "password": "password" }
	 */
	@RequestMapping(value = "/checkDevice", method = RequestMethod.POST)
	public Boolean checkDevice(@RequestBody(required = false) BotAuthentication auth) {
		boolean isAuthenticated = false;

		DeviceService deviceService = (DeviceService) SpringApplicationContext.getServiceBean("deviceService");

		isAuthenticated = deviceService.authDevice(auth.getAlias(), auth.getPassword());

		return isAuthenticated;

	}

}
