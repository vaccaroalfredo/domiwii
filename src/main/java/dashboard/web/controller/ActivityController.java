package dashboard.web.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


import dashboard.db.jpa.Activity;
import dashboard.db.jpa.Scheduler;
import dashboard.db.jpa.Setting;
import dashboard.db.service.ActivityService;
import dashboard.db.service.SchedulerService;
import dashboard.db.service.SettingService;
import dashboard.web.context.SpringApplicationContext;
import dashboard.web.model.MonitorData;
import dashboard.web.session.UserSessionInfo;
import dashboard.web.view.ErrorFormView;
import dashboard.web.view.ActivityFilterView;

@Controller
@RequestMapping(value="/home/activity")
@SessionAttributes("userSessionInfo")
public class ActivityController  extends LoggerUtils{
	private static final Logger logger = Logger.getLogger(ActivityController.class);
	
	@RequestMapping(value="/monitorsetting")
	public @ResponseBody  Setting monitorsetting(HttpServletRequest request)
	{	this.debugMessage(logger,"Url match with function monitorsetting!!! ");
		//UserSessionInfo info= (UserSessionInfo) request.getSession().getAttribute("userSessionInfo");
	
		SettingService activityService = (SettingService) SpringApplicationContext.getServiceBean("settingService");
		return activityService.findSetting().get(0);
	
	}
	
	
	@RequestMapping(value="/monitor")
	public @ResponseBody  List<MonitorData> monitor(HttpServletRequest request)
	{	this.debugMessage(logger,"Url match with function monitor!!! ");
		//UserSessionInfo info= (UserSessionInfo) request.getSession().getAttribute("userSessionInfo");
	
		int rows=(request.getParameter("rows")!=null) ? Integer.parseInt(request.getParameter("rows")):1;
		if (rows>0){
			
			SchedulerService schedulerService = (SchedulerService) SpringApplicationContext.getServiceBean("schedulerService");
			
			List<Scheduler> scheduledActions = schedulerService.getAllScheduledAction();
			
			List<MonitorData> toReturn=  new ArrayList<MonitorData>();
			
			for (Scheduler scheduler : scheduledActions) {
				
				
				MonitorData monitorData= new MonitorData();
				monitorData.buildObjByScheduler(scheduler);
				
				toReturn.add(monitorData);
				
			}
			
		//	ActivityService activityService = (ActivityService) SpringApplicationContext.getServiceBean("activityService");
		//	activityService.findActivityNotClosed();
			
			return toReturn;// activityService.findActivityNotClosed();
		}else{
			return new ArrayList<MonitorData>();
		}
	}
	
	@RequestMapping(value="/save")
	public @ResponseBody  ErrorFormView save(@RequestBody Activity jsonString)
	{	this.debugMessage(logger,"Url match with function save!!! ");
		
		jsonString.logInfo();
		jsonString.validation();
		ErrorFormView error=new ErrorFormView();
		ActivityService activityService = (ActivityService) SpringApplicationContext.getServiceBean("activityService");
		if (  activityService.storeActivity(jsonString) == false )   
			error.setErrorInfo(ErrorFormView.APPLICATION_ERROR,"Modifica attività n. "+jsonString.getId()+" non riuscita!");
		
		return error;
	
	}
	
	@RequestMapping(value="/add")
	public @ResponseBody  ErrorFormView add(@RequestBody Activity jsonString)
	{	this.debugMessage(logger,"Url match with function add!!! ");
		
		jsonString.logInfo();
		jsonString.validation();
		ErrorFormView error=new ErrorFormView();
		ActivityService activityService = (ActivityService) SpringApplicationContext.getServiceBean("activityService");
		Long newidactivity=activityService.addActivity(jsonString);
		if (newidactivity < 0)   
			error.setErrorInfo(ErrorFormView.APPLICATION_ERROR,"Aggiunta nuova attività non riuscita!");
		else
			error.setStatus(newidactivity);
		
		return error;
	
	}
	
	@RequestMapping(value="/del")
	public @ResponseBody  ErrorFormView del(HttpServletRequest request)
	{	this.debugMessage(logger,"Url match with function delete!!! ");
		
		String idactivity=request.getParameter("id");
		System.out.println("ATTIVITA DA CANCELLARE : "+idactivity);
		ErrorFormView error=new ErrorFormView();
		ActivityService activityService = (ActivityService) SpringApplicationContext.getServiceBean("activityService");
	
		if (activityService.deleteActivity(Long.parseLong(idactivity))== false)   
			error.setErrorInfo(ErrorFormView.APPLICATION_ERROR,"Cancellazione attività non riuscita!");
		
		
		return error;
	
	}
	  
	@RequestMapping(value="/get", method = RequestMethod.POST)
	public @ResponseBody  List<Activity> get(@RequestBody ActivityFilterView searchform,@ModelAttribute("userSessionInfo") UserSessionInfo infouser)
	{	this.debugMessage(logger,"Url match with function get!!! ");
		
		searchform.logInfo();
		infouser.setSearchFilter(searchform);
		ActivityService activityService = (ActivityService) SpringApplicationContext.getServiceBean("activityService");
		return activityService.findActivity(searchform.getDescription(),searchform.getStatus(), searchform.getStartdate(),searchform.getEnddate());
		
	
	}
	
	@RequestMapping(value = "/downloadexcel", method = RequestMethod.GET)
    public ModelAndView downloadActivityExcel(@ModelAttribute("userSessionInfo") UserSessionInfo infouser) {
        // create some sample data
		ActivityService activityService = (ActivityService) SpringApplicationContext.getServiceBean("activityService");
		ActivityFilterView searchform=infouser.getSearchFilter();
		List<Activity> listActivity= activityService.findActivity(searchform.getDescription(),searchform.getStatus(), searchform.getStartdate(),searchform.getEnddate());
		
//    	File dir = new File("C:\\tmp\\excel");
//     	dir.mkdir();
//    	TempFile.setTempFileCreationStrategy(new TempFile.DefaultTempFileCreationStrategy(dir));
//        // return a view which will be resolved by an excel view resolver
        return new ModelAndView("excelOfactivity", "listActivity", listActivity);
    }
	

}
