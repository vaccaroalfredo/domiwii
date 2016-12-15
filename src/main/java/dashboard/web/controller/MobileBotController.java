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
import dashboard.web.model.CheckDeviceResponse;
import dashboard.web.model.Command;
import dashboard.web.model.ConditionerResponse;
import dashboard.web.model.DeviceList;
import dashboard.web.model.DeviceMetadataResponse;
import dashboard.web.model.Response;
import dashboard.web.model.UpdateDeviceModel;
import dashboard.web.model.Response.ResponseCode;

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
		String uiid = act.getUiid();
		
		if ((devAlias == null || devAlias.equalsIgnoreCase("")) && (uiid == null || uiid.equalsIgnoreCase("")) ) {

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

		
		Device dev = null;
		
		if(devAlias != null){
			dev = ds.getDeviceByAlias(devAlias);
		}
		

		if (dev == null && uiid != null && !uiid.equalsIgnoreCase("")) {
			
			dev = ds.getDeviceByUid(uiid);
			
		}
		
		
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
	public CheckDeviceResponse checkDevice(@RequestBody(required = false) BotAuthentication auth) {
		try {
			boolean isAuthenticated = false;

			DeviceService deviceService = (DeviceService) SpringApplicationContext.getServiceBean("deviceService");

			isAuthenticated = deviceService.authDevice(auth.getAlias(), auth.getPassword());
			
			if(isAuthenticated){
				
				Device dev = deviceService.getDeviceByAlias(auth.getAlias());
				if (dev== null){
					dev = deviceService.getDeviceByUid(auth.getAlias());
					
				}
				
				if(dev!= null){
					return new CheckDeviceResponse(String.valueOf(isAuthenticated), ResponseCode.OK, dev.getTemperature(), dev.getHumidity());
				}
			}

			return new CheckDeviceResponse(String.valueOf(isAuthenticated),ResponseCode.KO,"","");
		} catch (Exception e) {

			return new CheckDeviceResponse(String.valueOf(false),ResponseCode.KO,"","");
		}

	}
	@RequestMapping(value = "/checkDeviceByUid", method = RequestMethod.POST)
	public CheckDeviceResponse checkDeviceByUid(@RequestBody(required = false) BotAuthentication auth) {
		try {
			boolean isAuthenticated = false;

			DeviceService deviceService = (DeviceService) SpringApplicationContext.getServiceBean("deviceService");

			isAuthenticated = deviceService.authDevice(auth.getUid(), auth.getPassword());
			
			if(isAuthenticated){
				
				if(auth.getUid()==null){
					return new CheckDeviceResponse("uid non valorizzato",ResponseCode.KO,"","");
				}
				
				Device dev = deviceService.getDeviceByUid(auth.getUid());
				if (dev== null){
					
					return new CheckDeviceResponse(String.valueOf(isAuthenticated),ResponseCode.KO,"","");
				}
				
				
					return new CheckDeviceResponse(String.valueOf(isAuthenticated), ResponseCode.OK, dev.getTemperature(), dev.getHumidity());
			
			}

			return new CheckDeviceResponse(String.valueOf(isAuthenticated),ResponseCode.KO,"","");
		} catch (Exception e) {

			return new CheckDeviceResponse(String.valueOf(false),ResponseCode.KO,"","");
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

		Code c = new Code(ConditionerCodeMode.ZERO, param);
		Code c1 = new Code(ConditionerCodeMode.ONE, param);
		Code c2 = new Code(ConditionerCodeMode.TWO, param);
		Code c3 = new Code(ConditionerCodeMode.THREE, param);
		
		List<Code> codes = new ArrayList<Code>();
		codes.add(c);
		codes.add(c1);
		codes.add(c2);
		codes.add(c3);

		CodeList codeList = new CodeList(codes);

		Conditioner cond = new Conditioner("cond", "Model" + (Math.random() * 100), codeList);

		ConditionerService cs = (ConditionerService) SpringApplicationContext.getServiceBean("conditionerService");

		cs.addConditioner(cond);

		return new Response(String.valueOf(true), ResponseCode.OK);// +params.getId();
	}
	@RequestMapping(value = "/updateDevice", method = RequestMethod.POST)
	public Response updateDevice(@RequestBody(required = true) UpdateDeviceModel deviceUpdated) {

		if(deviceUpdated.getAlias()==null || deviceUpdated.getAlias()=="" || deviceUpdated.getAlias()==" "){
			 return new Response("Alias nullo o vuoto", ResponseCode.KO);
		}	
		
		
		//	Device device = new Device(devId, alias, password, temperature, humidity);
			DeviceService deviceService = new DeviceService();
			Device dev = deviceService.getDeviceByAlias(deviceUpdated.getAlias());
			if (dev == null) {
				
				return new Response("Alias non strovato", ResponseCode.KO);
				
			}
			
			if (deviceUpdated.getPassword()== null ||deviceUpdated.getPassword()== "" || deviceUpdated.getPassword()== " " ) {
				return new Response("Password Vuota!", ResponseCode.KO);
			}
			
			if (!dev.getPassword().equalsIgnoreCase(Device.getPasswordEncrypt(deviceUpdated.getPassword()))) {
				return new Response("Password errata, riprovare!", ResponseCode.KO);
			}
			
			
			if (!deviceUpdated.getAlias().equalsIgnoreCase(deviceUpdated.getNewAlias())) {
				if(deviceService.getDeviceByAlias(deviceUpdated.getNewAlias()) != null){
					return new Response("Alias gia presente, inserirne uno nuovo", ResponseCode.KO);
				}
			}
			
			
			
			dev.setAlias(deviceUpdated.getNewAlias());
			if(deviceUpdated.getNewPassword() != null && (!deviceUpdated.getNewPassword().equalsIgnoreCase("")) && (!deviceUpdated.getNewPassword().equalsIgnoreCase(" ") )){
				dev.setPassword(Device.getPasswordEncrypt(deviceUpdated.getNewPassword()));
			}
			
			Long idDev = deviceService.updateDevice(dev);
			
			if (idDev == null) {
				return new Response("Errore nel Salvataggio", ResponseCode.KO);
			}
			
			return new Response("Device Modificato correttamente", ResponseCode.OK);
		
		
		

	}
	@RequestMapping(value = "/resetDevice", method = RequestMethod.POST)
	public Response resetDevice(@RequestBody(required = true) UpdateDeviceModel deviceUpdated) {

		String devId = "";
		String alias = devId;
		String password = "admin";
		String temperature = "-1";
		String humidity = "-1";

		
		
		if(deviceUpdated.getAlias()==null || deviceUpdated.getAlias()=="" || deviceUpdated.getAlias()==" "){
			 return new Response("Alias nullo o vuoto", ResponseCode.KO);
		}
		
		if (deviceUpdated.getPassword()== null ||deviceUpdated.getPassword()== "" || deviceUpdated.getPassword()== " " ) {
			return new Response("Password Vuota!", ResponseCode.KO);
		}
		
		
		DeviceService deviceService = new DeviceService();
		Device dev = deviceService.getDeviceByAlias(deviceUpdated.getAlias());
		
		if(dev== null){
			dev = deviceService.getDeviceByUid(deviceUpdated.getAlias());
		}
		
		if(dev == null){
			return new Response("Device non Trovato", ResponseCode.KO);
		}
		
		String receivedPassword =  Device.getPasswordEncrypt(deviceUpdated.getPassword());
		if (!dev.getPassword().equalsIgnoreCase(Device.getPasswordEncrypt(deviceUpdated.getPassword()))) {
			return new Response("Password errata, riprovare!", ResponseCode.KO);
		}
		
		
		dev.setAlias(dev.getUid());
		dev.setPassword(Device.getPasswordEncrypt(password));
		dev.setTemperature(temperature);
		dev.setHumidity(humidity);
		
		deviceService.updateDevice(dev);
			
		
		return new Response("Device Resettato correttamente", ResponseCode.OK);

		

	}

}
