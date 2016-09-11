package dashboard.db.daoimpl;

import java.util.Date;
import java.util.List;

import javax.management.Query;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import dashboard.db.ASDataFormat;
import dashboard.db.dao.AbstractDao;
import dashboard.db.dao.NoteDao;
import dashboard.db.dao.SchedulerDao;
import dashboard.db.dao.UserDao;
import dashboard.db.jpa.Action;
import dashboard.db.jpa.Activity;
import dashboard.db.jpa.Device;
import dashboard.db.jpa.DeviceActionId;
import dashboard.db.jpa.Note;
import dashboard.db.jpa.Scheduler;
import dashboard.db.jpa.State;
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

			idScheduledAction = null;// actionSceduled.getId();

		} catch (Exception e) {

			this.closeTransaction(false);

		} finally {

			this.closeTransaction(true);
		}

		return idScheduledAction;
	}

	@Override
	public Action getActionByDeviceID(String idDevice) {

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
	public List<Scheduler> getActionsByDeviceID(String idDevice) {

		return null;
	}

	@Override
	public Action getWaitingActionByDeviceID(String idDevice) {

		List<Scheduler> list = null;
		Scheduler scheduler = null;
		Action action = null;
		try {
			this.openTransaction(); 
			//Criteria cr = this.getSession().createCriteria(Action.class);//.createCriteria("deviceActionId.action","joinSchedulerAsction");//.createAlias(, "scheduler");
			//cr.setFetchMode("scheduler.deviceActionId", FetchMode.JOIN);
			//cr.createAlias("a.scheduler", "scheduler");
			org.hibernate.Query q = this.getSession().createQuery("FROM Scheduler s join s.deviceActionId devac WHERE devac.device.uid = "+ idDevice +" AND devac.action.state = "+ State.WAITING.ordinal()+" order BY  s.creationdate ASC ");
			//cr.add(Restrictions.eq("state",State.WAITING));
			//org.hibernate.Query q = this.getSession().createQuery("FROM Scheduler");
			//cr.createCriteria("scheduler.deviceActionId.device","d");//.add(Restrictions.eq("d.id",Long.valueOf(2)));
			//cr.add(Restrictions.eq("scheduler",Long.valueOf(2)));
			//cr.createCriteria("p.idDevice", "deviceActionId", JoinType.INNER_JOIN);
			//ProjectionList columns = Projections.projectionList().add(Projections.property("device.id"));
			//cr.createAlias("scheduler.deviceActionId", "daid");
			
			//daid.add(Restrictions.eq("id",Long.valueOf(1)));
		    //cr.createAlias("", "dev");
		    //cr.setResultTransformer(Criteria.ROOT_ENTITY);
			//cr.setProjection(columns);		
		    list = q.list();
		    
		    if (list.size()>0) {
				scheduler= list.get(0); // first available action
			}
		 
		    if (scheduler != null) {
		    	action =  scheduler.getDeviceActionId().getAction();   
			}
		    
					
			//return list;
		} catch (Exception e) {
			this.closeTransaction(false);
		} finally {
			this.closeTransaction(true);
		}
		list.size();
		return action;
	}
	@Override
	public boolean deleteScheduler(Long idScheduler) {

		return false;
	}

	@Override
	public List<Action> getWaitingActionsByDeviceID(String idDevice) {

		return null;
	}

}
