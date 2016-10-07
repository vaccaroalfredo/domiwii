package dashboard.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dashboard.db.jpa.Action;
import dashboard.db.jpa.Code;
import dashboard.db.jpa.CodeList;
import dashboard.db.jpa.Conditioner;
import dashboard.db.jpa.ConditionerCodeMode;
import dashboard.db.jpa.Device;
import dashboard.db.jpa.State;
import dashboard.db.service.ActionService;
import dashboard.db.service.ConditionerService;
import dashboard.db.service.DeviceService;
import dashboard.db.service.SchedulerService;
import dashboard.web.context.SpringApplicationContext;
import dashboard.web.model.ArduinoMessage;
import dashboard.web.model.BotAction;
import dashboard.web.model.BotAuthentication;
import dashboard.web.model.Command;
import dashboard.web.model.ConditionerResponse;
import dashboard.web.model.DeviceList;
import dashboard.web.model.DeviceMetadataResponse;
import dashboard.web.model.Response;

@RestController
public class MobileBotController extends LoggerUtils {
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
	public Response addBotAction(@RequestBody(required = false) BotAction act) {

		this.debugMessage(logger, "addBotAction");

		// String command = params.getMode() + "" + params.getTemperature() + ""
		// + params.getSpeed();// +""+params.getConfort();
		String devAlias = act.getAlias();
		if (devAlias == null || devAlias.equalsIgnoreCase("")) {

			return new Response(String.valueOf(false));

		}

		Command commandObj = new Command();

		try {

			commandObj.setParameters(act.getStatus(), act.getMode(), act.getTemperature(), act.getSpeed(),
					act.getConfort());

		} catch (Exception e) {

			return new Response(String.valueOf(false));

		}

		Action action = new Action("action", "BotCommand", commandObj.buildCommand(), State.WAITING);

		ActionService as = (ActionService) SpringApplicationContext.getServiceBean("actionService");

		as.addAction(action);

		DeviceService ds = (DeviceService) SpringApplicationContext.getServiceBean("deviceService");

		Device dev = ds.getDeviceByAlias(devAlias);

		if (dev == null) {

			return new Response(String.valueOf(false));

		} else {

			SchedulerService ss = (SchedulerService) SpringApplicationContext.getServiceBean("schedulerService");
			ss.addScheduledAction(action, dev);

		}

		return new Response(String.valueOf(true));// +params.getId();
	}

	/*
	 * 
	 * ws checkUser
	 * 
	 * @param String Alias; password; return boolean { "alias": "alias",
	 * "password": "password" }
	 */
	@RequestMapping(value = "/checkDevice", method = RequestMethod.POST)
	public Response checkDevice(@RequestBody(required = false) BotAuthentication auth) {
		try {
			boolean isAuthenticated = false;

			DeviceService deviceService = (DeviceService) SpringApplicationContext.getServiceBean("deviceService");

			isAuthenticated = deviceService.authDevice(auth.getAlias(), auth.getPassword());

			return new Response(String.valueOf(isAuthenticated));
		} catch (Exception e) {

			return new Response(String.valueOf(false));

		}

	}

	/*
	 * 
	 * ws
	 * 
	 * @param String Alias; password; return boolean { "alias": "alias",
	 * "password": "password" }
	 */
	@RequestMapping(value = "/devicesMetadata", method = RequestMethod.POST)
	public DeviceMetadataResponse getDeviceMetadata(@RequestBody(required = true) List<String> devicelist) {

		try {
			DeviceService deviceService = (DeviceService) SpringApplicationContext.getServiceBean("deviceService");

			List<Device> devlist = deviceService.getDevicesMetadata(devicelist);

			DeviceMetadataResponse deviceMetadataResponse = new DeviceMetadataResponse(devlist);

			return deviceMetadataResponse;

		} catch (Exception e) {

			return new DeviceMetadataResponse(new ArrayList<Device>());

		}

	}

	/*
	 * 
	 * ws
	 * 
	 * @param String Alias; password; return boolean { "alias": "alias",
	 * "password": "password" }
	 */
	@RequestMapping(value = "/conditionerList", method = RequestMethod.POST)
	public ConditionerResponse getConditionerList() {
		try {
			ConditionerService cs = (ConditionerService) SpringApplicationContext.getServiceBean("conditionerService");
			List<Conditioner> condList = cs.getConditioners();
			condList.size();
			ConditionerResponse cr = new ConditionerResponse(condList);
			return cr;

		} catch (Exception e) {

			return new ConditionerResponse(new ArrayList<Conditioner>());

		}

	}

	@RequestMapping(value = "/addConditioner", method = RequestMethod.POST)
	public Response addConditioner() {

		this.debugMessage(logger, "addBotAction");

		// String command = params.getMode() + "" + params.getTemperature() + ""
		// + params.getSpeed();// +""+params.getConfort();
		List<Integer> param = new ArrayList<Integer>();
		param.add(9000);
		param.add(225);
		param.add(12);
		param.add(225);
		param.add(12);
		param.add(225);
		param.add(12);
		param.add(225);
		param.add(9000);
		param.add(225);
		param.add(12);
		param.add(225);
		param.add(12);
		param.add(225);
		param.add(12);
		param.add(225);
		param.add(9000);
		param.add(225);
		param.add(12);
		param.add(225);
		param.add(12);
		param.add(225);
		param.add(12);
		param.add(225);

		Code c = new Code(ConditionerCodeMode.COLD_VEL_1, param);
		Code c1 = new Code(ConditionerCodeMode.HOT_VEL_1, param);
		Code c2 = new Code(ConditionerCodeMode.COLD_VEL_4, param);
		Code c3 = new Code(ConditionerCodeMode.STOP, param);
		
		List<Code> codes = new ArrayList<Code>();
		codes.add(c);
		codes.add(c1);
		codes.add(c2);
		codes.add(c3);

		CodeList codeList = new CodeList(codes);

		Conditioner cond = new Conditioner("cond", "Model" + (Math.random() * 100), codeList);

		ConditionerService cs = (ConditionerService) SpringApplicationContext.getServiceBean("conditionerService");

		cs.addConditioner(cond);

		return new Response(String.valueOf(true));// +params.getId();
	}

}
