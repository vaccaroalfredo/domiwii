package dashboard.db.dao;

import java.util.Date;
import java.util.List;

import dashboard.db.jpa.Action;
import dashboard.db.jpa.Device;
import dashboard.db.jpa.Note;
import dashboard.db.jpa.Scheduler;


public interface SchedulerDao {
	public Long addAction(Action action, Device device);
	public Action getActionByDeviceID(String idDevice);
	public Scheduler getNextActionByDeviceID(Long idDevice);
	public boolean updateActionById(Long idAction);
    public List<Scheduler> getActionsByDeviceID(String idDevice);
    public List<Action> getWaitingActionsByDeviceID(String idDevice);
    //public List<Note> getNotes(String owner, Date startdate,Date enddate,Boolean hightlights,Boolean archive);
    public boolean deleteScheduler(Long idScheduler);
	public Action getWaitingActionByDeviceID(String idDevice);
	public List<Scheduler> getAllScheduledAction();
    

}
