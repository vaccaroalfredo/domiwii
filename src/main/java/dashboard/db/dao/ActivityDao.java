package dashboard.db.dao;

import java.util.Date;
import java.util.List;

import dashboard.db.jpa.Activity;


public interface ActivityDao {
	public Long addActivity(Activity activity);
	public boolean updateActivity(Activity activity);
    public List<Activity> getActivities(); 
    public List<Activity> getActivities(String description, Activity.Status status,Date startdate,Date enddate);
    public List<Activity> getActivitiesNotClosed();
    public boolean deleteActivity(Long idactivity);

}
