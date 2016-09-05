package dashboard.db.daoimpl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import dashboard.db.ASDataFormat;
import dashboard.db.dao.AbstractDao;
import dashboard.db.dao.NoteDao;
import dashboard.db.dao.SchedulerDao;
import dashboard.db.dao.UserDao;
import dashboard.db.jpa.Action;
import dashboard.db.jpa.Activity;
import dashboard.db.jpa.Device;
import dashboard.db.jpa.Note;
import dashboard.db.jpa.Scheduler;
import dashboard.db.jpa.User;


public class SchedulerDaoImpl extends AbstractDao<Scheduler> implements SchedulerDao {

	@Override
	public Long addAction(Action action, Device device) {
		
		Long idScheduledAction = null;
		
		try {
			
			Scheduler actionSceduled = new Scheduler("");
			
			actionSceduled.setAction(action);
			
			actionSceduled.setDevice(device);
			
			this.openTransaction();
			
			this.persist(actionSceduled);
			
			idScheduledAction = null;//actionSceduled.getId();
			
		} catch (Exception e) {
			
			this.closeTransaction(false);
			
		}finally {
			
			this.closeTransaction(true);
		}
		
		
		
		return idScheduledAction;
	}

	@Override
	public Action getActionByDeviceID(Long idDevice) {
		
		return null;
	}

	@Override
	public Scheduler getNextActionByDeviceID(Long idDevice) {
		
		return null;
	}

	@Override
	public boolean updateActionById(Long idAction) {
		
		return false;
	}

	@Override
	public List<Scheduler> getActionsByDeviceID(Long idDevice) {
		
		return null;
	}

	@Override
	public boolean deleteScheduler(Long idScheduler) {
	
		return false;
	}
	
	
	

	

}
