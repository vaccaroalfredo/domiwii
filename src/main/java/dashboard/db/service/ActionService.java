package dashboard.db.service;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import dashboard.db.dao.ActionDao;
import dashboard.db.dao.NoteDao;
import dashboard.db.daoimpl.ActionDaoImpl;
import dashboard.db.daoimpl.NoteDaoImpl;
import dashboard.db.jpa.Action;
import dashboard.db.jpa.Note;


@Service("actionService")
public class ActionService {
	private static final Logger logger = Logger.getLogger(ActionService.class);

	
	public ActionService() {
		super();
		
	}
	
	public Action getActionById(Long idAction){
		logger.debug("DEBUG--BERVICE--getNote");
		//NoteDao dao = new NoteDaoImpl();
		ActionDao dao = new ActionDaoImpl();
		try{
			return dao.getActionById(idAction); 
		}catch(Exception e){
			logger.error("getAction",e);
		}
		return null;
	}
	
//	public Action getActionByDeviceId(Long idDevice){
//		logger.debug("DEBUG--BERVICE--getNote");
//		//NoteDao dao = new NoteDaoImpl();
//		ActionDao dao = new ActionDaoImpl();
//		try{
//			return dao.getActionByDeviceId(idDevice); 
//		}catch(Exception e){
//			logger.error("getAction",e);
//		}
//		return null;
//	}

//	public boolean storeAction(Action action){
//		logger.debug("DEBUG--SERVICE--storeNote");
//		//NoteDao dao = new NoteDaoImpl();
//		
//		ActionDao dao = new ActionDaoImpl();
//		try{
//			return dao.updateNote(note); 
//		}catch(Exception e){
//			logger.error("storeNote",e);
//		}
//		return false;
//	}
	
	public Long addAction(Action action){
		logger.debug("DEBUG--SERVICE--addAction ");
		//NoteDao dao = new NoteDaoImpl();
		ActionDao dao = new ActionDaoImpl();
		try{
			return dao.addAction(action);
		}catch(Exception e){
			logger.error("addAction",e);
		}
		return new Long(0);
	}
	
	public boolean deleteNote(Long idAction){
		logger.debug("DEBUG--SERVICE--deleteNote ");
		//NoteDao dao = new NoteDaoImpl();
		ActionDao dao = new ActionDaoImpl();
		try{
			return dao.deleteAction(idAction);
		}catch(Exception e){
			logger.error("deleteAction",e);
		}
		return false;
	}
	
	
	public List<Note> findNote(String description, Date startdate,Date enddate,Boolean highlights,Boolean archive){
		logger.debug("DEBUG--SERVICE--findNote ");
		NoteDao dao = new NoteDaoImpl();
		try{
			return dao.getNotes(description,startdate,enddate,highlights,archive);
		}catch(Exception e){
			logger.error("findNote",e);
		}
		return new ArrayList<Note>();
	}
	
 
   
}
