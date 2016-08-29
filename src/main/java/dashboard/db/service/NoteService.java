package dashboard.db.service;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import dashboard.db.dao.NoteDao;
import dashboard.db.daoimpl.NoteDaoImpl;
import dashboard.db.jpa.Note;





@Service("noteService")
public class NoteService {
	private static final Logger logger = Logger.getLogger(NoteService.class);
	
	
	
	public NoteService() {
		super();
		
	}
	
	public Note getNote(Long idnote){
		logger.debug("DEBUG--SERVICE--getNote");
		NoteDao dao = new NoteDaoImpl();
		try{
			return dao.getNoteByID(idnote); 
		}catch(Exception e){
			logger.error("getNote",e);
		}
		return null;
	}

	public boolean storeNote(Note note){
		logger.debug("DEBUG--SERVICE--storeNote");
		NoteDao dao = new NoteDaoImpl();
		try{
			return dao.updateNote(note); 
		}catch(Exception e){
			logger.error("storeNote",e);
		}
		return false;
	}
	
	public Long addNote(Note note){
		logger.debug("DEBUG--SERVICE--addNote ");
		NoteDao dao = new NoteDaoImpl();
		try{
			return dao.addNote(note);
		}catch(Exception e){
			logger.error("addNote",e);
		}
		return new Long(0);
	}
	
	public boolean deleteNote(Long idnote){
		logger.debug("DEBUG--SERVICE--deleteNote ");
		NoteDao dao = new NoteDaoImpl();
		try{
			return dao.deleteNote(idnote);
		}catch(Exception e){
			logger.error("deleteNote",e);
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
