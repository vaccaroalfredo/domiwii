package dashboard.web.controller;

import java.util.StringTokenizer;

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

@RestController
public class ServiceController {

	/*
	 * deviceId=2314
	 */
	@RequestMapping(value = "/addDevice", method = RequestMethod.POST)
	public String addDevice(@RequestBody(required = true) String params) {

		String devId = "";
		String alias = devId;
		String password = "admin";
		String temperature = "-1";
		String humidity = "-1";

		if (params.indexOf("deviceID=") >= 0) {

			if (params.indexOf(";") >= 0) {

				devId = params.substring(params.indexOf("deviceID=") - 1, params.indexOf(";") - 1);

			} else {

				int start = params.indexOf("deviceID=") + String.valueOf("deviceID=").length();
				devId = params.substring(start);
			}
		}
		
		if(devId != null && (!devId.equalsIgnoreCase("")) && (!devId.equalsIgnoreCase(" ")) ){
			
			alias = devId;
			Device device = new Device(devId, alias, Device.getPasswordEncrypt(password), temperature, humidity);
			DeviceService deviceService = new DeviceService();
			deviceService.updateDevice(device);
			return "true";
		}
		
		

		return "false";

	}

	

	@RequestMapping(value = "/getAction", method = RequestMethod.POST)
	public String getAction(@RequestBody(required = true) String params) {

		String iddevice = "-1";
		String temperature = "-1";
		String humidity = "-1";

		if (params.indexOf("deviceID=") >= 0) {
			int start = params.indexOf("deviceID=") + String.valueOf("deviceID=").length();

			if (params.indexOf(";temperature") >= 0) {
				int i = params.indexOf(";temperature");
				int s = params.indexOf("deviceID=");
				iddevice = params.substring(start, params.indexOf(";temperature"));

			} else {

				// int start = params.indexOf("deviceID=")+
				// String.valueOf("deviceID=").length();
				iddevice = params.substring(start);
			}
		}

		if (params.indexOf("temperature=") >= 0) {
			int start = params.indexOf("temperature=") + String.valueOf("temperature=").length();
			if (params.indexOf(";humidity") >= 0) {

				temperature = params.substring(start, params.indexOf(";humidity"));

			} else {

				// int start = params.indexOf("temperature=")+
				// String.valueOf("temperature=").length();
				temperature = params.substring(start);
			}
		}
		if (params.indexOf("humidity=") >= 0) {
			int start = params.indexOf("humidity=") + String.valueOf("humidity=").length();

			// int start = params.indexOf("humidity=")+
			// String.valueOf("humidity=").length();
			humidity = params.substring(start);
			humidity = humidity.replace(";", "");

		}

		if (iddevice.equalsIgnoreCase("-1")) {
			return "-1";
		}
		// Action a = actionService.getActionById(new Long(1));

		SchedulerService schedulerService = (SchedulerService) SpringApplicationContext
				.getServiceBean("schedulerService");

		DeviceService deviceService = (DeviceService) SpringApplicationContext.getServiceBean("deviceService");

		boolean isDeviceUpdated = deviceService.updateDeviceMetadata(iddevice, temperature, humidity);

		Action a = schedulerService.getWaitingActionByDeviceId(iddevice);

		String toReturn = "";
		if (a != null) {
			toReturn = a.getCommand() + ";" + a.getId();

			ActionService actionService = (ActionService) SpringApplicationContext.getServiceBean("actionService");
			a.setState(State.START);
			actionService.updateActionState(a);

		}

		return toReturn;

	}

	@RequestMapping(value = "/updateAction", method = RequestMethod.POST)
	public String updateAction(@RequestBody(required = true) String params) {

		boolean toReturn = false;
		String actionID = "2";
		String state = "1";// 1 or 2
		State s = null;

		switch (state) {
		case "2":
			s = State.SUCCESS;
			break;
		case "1":
			s = State.FAILED;
			break;
		default:

			break;
		}

		ActionService actionService = (ActionService) SpringApplicationContext.getServiceBean("actionService");

		Long aid = Long.valueOf(actionID);
		if (aid == null) {
			return "false";
		}
		toReturn = actionService.updateActionState(aid, s);

		return String.valueOf(toReturn);// +params.getId();

	}

	/*
	 * 
	 * ws addBotAction
	 * 
	 * @param String idDevice; mode; temperature; speed; return boolean
	 * 
	 */

}
