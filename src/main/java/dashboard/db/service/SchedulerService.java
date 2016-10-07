package dashboard.db.service;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import dashboard.db.dao.NoteDao;
import dashboard.db.dao.SchedulerDao;
import dashboard.db.daoimpl.NoteDaoImpl;
import dashboard.db.daoimpl.SchedulerDaoImpl;
import dashboard.db.jpa.Action;
import dashboard.db.jpa.Device;
import dashboard.db.jpa.Note;
import dashboard.db.jpa.Scheduler;





@Service("schedulerService")
public class SchedulerService {
	private static final Logger logger = Logger.getLogger(SchedulerService.class);
	
	
	
	public SchedulerService() {
		super();
		
	}
	
	public Action getActionByDeviceId(String iddevice){
		logger.debug("DEBUG--SERVICE--getActionByDeviceId");
		
		SchedulerDao dao = new SchedulerDaoImpl();
		
		try{
			return dao.getActionByDeviceID(iddevice); 
		}catch(Exception e){
			logger.error("getNote",e);
		}
		
		
		return null;
	}

	public boolean storeAction(Note note){
//		logger.debug("DEBUG--SERVICE--storeNote");
//		NoteDao dao = new NoteDaoImpl();
//		try{
//			return dao.updateNote(note); 
//		}catch(Exception e){
//			logger.error("storeNote",e);
//		}
		return false;
	}
	
	public Long addScheduledAction(Action action, Device device ){
		
		logger.debug("DEBUG--SERVICE--addNote ");
		
		SchedulerDao dao = new SchedulerDaoImpl();
			
		try{
			return dao.addAction(action, device);
		}catch(Exception e){
			logger.error("addScheduledAction",e);
		}
		return new Long(0);
	}
	
	public boolean deleteScheduledAction(Long idScheduledAction){
		logger.debug("DEBUG--SERVICE--deleteScheduledAction ");
//		NoteDao dao = new NoteDaoImpl();
//		try{
//			return dao.deleteNote(idnote);
//		}catch(Exception e){
//			logger.error("deleteNote",e);
//		}
		return false;
	}
	
	
	public Action getWaitingActionByDeviceId(String idDevice){
		
		SchedulerDao dao = new SchedulerDaoImpl();
		
		Action a = dao.getWaitingActionByDeviceID(idDevice);
		
		return a;
	}
	
//	public List<Note> findNote(String description, Date startdate,Date enddate,Boolean highlights,Boolean archive){
//		logger.debug("DEBUG--SERVICE--findNote ");
//		NoteDao dao = new NoteDaoImpl();
//		try{
//			return dao.getNotes(description,startdate,enddate,highlights,archive);
//		}catch(Exception e){
//			logger.error("findNote",e);
//		}
//		return new ArrayList<Note>();
//	}
	
	public List<Scheduler> getAllScheduledAction(){
		
		SchedulerDao dao = new SchedulerDaoImpl();
		
		
		
		List<Scheduler> list = dao.getAllScheduledAction();
		
		return list;
	}
 
   
}
