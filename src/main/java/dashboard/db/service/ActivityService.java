package dashboard.db.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import dashboard.db.dao.ActivityDao;
import dashboard.db.daoimpl.ActivityDaoImpl;
import dashboard.db.jpa.Activity;

@Service("activityService")
public class ActivityService {
	private static final Logger logger = Logger.getLogger(ActivityService.class);
	
	
	public ActivityService() {
		super();
		
	}
	public boolean storeActivity(Activity activity){
		logger.debug("DEBUG--SERVICE--storeActivity");
		ActivityDao dao = new ActivityDaoImpl();
		try{
			return dao.updateActivity(activity); 
		}catch(Exception e){
			logger.error("storeActivity",e);
		}
		return false;
		
	}
	
	public Long addActivity(Activity activity){
		logger.debug("DEBUG--SERVICE--addActivity ");
		ActivityDao dao = new ActivityDaoImpl();
		try{
			return dao.addActivity(activity);
		}catch(Exception e){
			logger.error("addActivity",e);
		}
		return new Long(0);
	}
	
	public boolean deleteActivity(Long idactivity){
		logger.debug("DEBUG--SERVICE--deleteActivity ");
		ActivityDao dao = new ActivityDaoImpl();
		try{
			return dao.deleteActivity(idactivity);
		}catch(Exception e){
			logger.error("deleteActivity",e);
		}
		return false;
	}
	
	
	public List<Activity> findActivity(String description,Activity.Status status, Date startdate,Date enddate){
		logger.debug("DEBUG--SERVICE--findActivity ");
		ActivityDao dao = new ActivityDaoImpl();
		try{
			return dao.getActivities(description,status,startdate,enddate);
		}catch(Exception e){
			logger.error("findActivity",e);
		}
		return new ArrayList<Activity>();
	}
	
	public List<Activity> findActivityNotClosed(){
		logger.debug("DEBUG--SERVICE--findActivity ");
		ActivityDao dao = new ActivityDaoImpl();
		try{
			return dao.getActivitiesNotClosed();
		}catch(Exception e){
			logger.error("findActivityNotClosed",e);
		}
		return new ArrayList<Activity>();
		
	}
}
