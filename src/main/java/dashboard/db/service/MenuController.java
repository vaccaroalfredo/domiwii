package dashboard.web.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import dashboard.db.jpa.Activity;
import dashboard.db.jpa.Setting;
import dashboard.db.service.SettingService;
import dashboard.web.context.SpringApplicationContext;
import dashboard.web.session.UserSessionInfo;

import dashboard.web.view.NoteFilterView;

import dashboard.web.view.ActivityFilterView;

import dashboard.web.view.UserFilterView;


@Controller
@RequestMapping(value="/home")
public class MenuController extends LoggerUtils{
	private static final Logger logger = Logger.getLogger(MenuController.class);
	public enum MenuItem {
	      Monitor(0), Activity(1),Note(2),User(3),Setting(4);
	      private int value;
	      MenuItem(int value) { this.value = value; }
	      public int getValue() { return value; }
	};
	
	@ExceptionHandler(HttpSessionRequiredException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason="The session has expired")
	public String handleSessionExpired(){
	  return "login?message=\"Sessione scaduta\"";
	}
	
	@RequestMapping(value="/monitor",method=RequestMethod.GET)
	public ModelAndView goToMonitor(HttpServletRequest request, HttpServletResponse response)
	{
		this.debugMessage(logger,"Url match with function goToMonitor!!! ");
		UserSessionInfo infouser= (UserSessionInfo) request.getSession().getAttribute("userSessionInfo");
		infouser.setSectionMenu(MenuItem.Monitor);
		ModelAndView model=  new ModelAndView("monitor");
		SettingService activityService = (SettingService) SpringApplicationContext.getServiceBean("settingService");
		model.addObject("appsettingBean",activityService.findSetting().get(0));
		return model;
	}
	
	@RequestMapping(value="/activity",method=RequestMethod.GET)
	public ModelAndView goToActivity(HttpServletRequest request, HttpServletResponse response)
	{
		this.debugMessage(logger,"Url match with function goToActivity!!! ");
		UserSessionInfo infouser= (UserSessionInfo) request.getSession().getAttribute("userSessionInfo");
		infouser.setSectionMenu(MenuItem.Activity);
		ModelAndView model=  new ModelAndView("activity");
		model.addObject("searchBean",new ActivityFilterView());
		Map<Integer,String> country = new LinkedHashMap<Integer,String>();
		for  ( Activity.Status status : Activity.Status.values()){
			country.put(status.getValue(), status.toString());
		}
		model.addObject("statusList", country);
		
		return model;
	}
	
	
	@RequestMapping(value="/note",method=RequestMethod.GET)
	public ModelAndView goToNote(HttpServletRequest request, HttpServletResponse response)
	{
		this.debugMessage(logger,"Url match with function goToNote!!! ");
		UserSessionInfo infouser= (UserSessionInfo) request.getSession().getAttribute("userSessionInfo");
		infouser.setSectionMenu(MenuItem.Note);
		ModelAndView model= new ModelAndView("note");
		model.addObject("noteFilterBean",new NoteFilterView());
		return model;
        
	
	}
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public ModelAndView goToUser(HttpServletRequest request, HttpServletResponse response)
	{
		this.debugMessage(logger,"Url match with function goToUser!!! ");
		UserSessionInfo infouser= (UserSessionInfo) request.getSession().getAttribute("userSessionInfo");
		infouser.setSectionMenu(MenuItem.User);
		if (infouser.isAmministrator()==true){
			ModelAndView model= new ModelAndView("user");
			model.addObject("userFilterBean",new UserFilterView());
			return model;
		}else
			return  new ModelAndView("accessdenied");
		
	
	}
	
	@RequestMapping(value="/setting",method=RequestMethod.GET)
	public ModelAndView goToSetting(HttpServletRequest request, HttpServletResponse response)
	{
		this.debugMessage(logger,"Url match with function goToSetting!!! ");
		UserSessionInfo infouser= (UserSessionInfo) request.getSession().getAttribute("userSessionInfo");
		if (infouser.isAmministrator()==true){
			infouser.setSectionMenu(MenuItem.Setting);
			ModelAndView model=  new ModelAndView("setting");
			SettingService activityService = (SettingService) SpringApplicationContext.getServiceBean("settingService");
			model.addObject("appsettingBean",activityService.findSetting().get(0));
			return model;
		}else{
			return  new ModelAndView("accessdenied");
		}
	}
	
	@RequestMapping(value="/setting",method=RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("appsettingBean")Setting setting)
	{	this.debugMessage(logger,"Url match with function save!!! ");
		ModelAndView model = new ModelAndView("setting");
		SettingService settingService = (SettingService) SpringApplicationContext.getServiceBean("settingService");
		if (setting.getNumberrowstosee()<=0){
			model.addObject("message", "Impostazioni non salvate,  il numero di righe da visualizzare deve essere maggiore di zero");
		}else if (setting.getMinutetodefinecritic()<=0){
			model.addObject("message", "Impostazioni non salvate, il campo 'indicazione attività' critica deve essere maggiore di zero");
		}else if (setting.getRefreshpagemonitor()<=0){
			model.addObject("message", "Impostazioni non salvate,il campo 'refresh pagina monitor' deve essere maggiore di zero");
		}else if ( settingService.storeSetting(setting)== false ){
			model.addObject("message", "Impostazioni non salvate");
		}
		
		return model;
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ModelAndView logout(WebRequest request, SessionStatus status,ModelAndView modelmessageregister)
	{	this.debugMessage(logger,"Url match with function logout!!! ");
        
		status.setComplete();
	    request.removeAttribute("userSessionInfo", WebRequest.SCOPE_SESSION);
	    return new ModelAndView(new RedirectView("login",true));
	
	}
}
