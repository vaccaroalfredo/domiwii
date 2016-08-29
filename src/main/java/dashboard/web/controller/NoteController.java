package dashboard.web.controller;


import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import dashboard.db.jpa.Note;
import dashboard.db.service.NoteService;
import dashboard.web.context.SpringApplicationContext;
import dashboard.web.view.ErrorFormView;
import dashboard.web.view.NoteFilterView;



@Controller
@RequestMapping(value="/home/note")
public class NoteController extends LoggerUtils{
	private static final Logger logger = Logger.getLogger(NoteController.class);
	
	@RequestMapping(value="/save")
	public @ResponseBody  ErrorFormView save(@RequestBody Note jsonString)
	{	this.debugMessage(logger,"Url match with function save!!! ");
		
		jsonString.logInfo();
		jsonString.validation();
		ErrorFormView error=new ErrorFormView();
		NoteService noteService = (NoteService) SpringApplicationContext.getServiceBean("noteService");
//		if (jsonString.getHighlights()==null){
//			Note note=noteService.getNote(jsonString.getId());
//			if ( note.isHighlights()==true){
//				jsonString.setHighlights(1);
//			}
//		}
		if (  noteService.storeNote(jsonString) == false )   
			error.setErrorInfo(ErrorFormView.APPLICATION_ERROR,"Modifica nota n. "+jsonString.getId()+" non riuscita!");
		
		return error;
	
	}
	
	@RequestMapping(value="/savehighlights")
	public @ResponseBody  ErrorFormView savehighlights(@RequestBody Note jsonString)
	{	this.debugMessage(logger,"Url match with function savehighlights!!! ");
		
		jsonString.logInfo();
		jsonString.validation();
		jsonString.setHighlights(new Integer(1));
		ErrorFormView error=new ErrorFormView();
		NoteService noteService = (NoteService) SpringApplicationContext.getServiceBean("noteService");
//		if (jsonString.getHighlights()==null){
//			Note note=noteService.getNote(jsonString.getId());
//			if ( note.isHighlights()==true){
//				jsonString.setHighlights(1);
//			}
//		}
		if (  noteService.storeNote(jsonString) == false )   
			error.setErrorInfo(ErrorFormView.APPLICATION_ERROR,"Modifica nota n. "+jsonString.getId()+" non riuscita!");
		
		return error;
	
	}
	

	@RequestMapping(value="/get",method = RequestMethod.POST)
	public @ResponseBody  List<Note> get(@RequestBody NoteFilterView searchform)
	{	this.debugMessage(logger,"Url match with function get!!! ");
		
		
		NoteService activityService = (NoteService) SpringApplicationContext.getServiceBean("noteService");
		List<Note> notes=activityService.findNote(searchform.getDescription(), searchform.getStartdate(),searchform.getEnddate(),searchform.getHigh(),searchform.getArchive());
		
		return notes;
		
	
	}
	@RequestMapping(value="/add")
	public @ResponseBody  ErrorFormView add(@RequestBody Note jsonString)
	{	this.debugMessage(logger,"Url match with function add!!! ");
		
		jsonString.logInfo();
		//jsonString.validation();
		ErrorFormView error=new ErrorFormView();
		NoteService noteService = (NoteService) SpringApplicationContext.getServiceBean("noteService");
		Long newidnote=noteService.addNote(jsonString);
		if (newidnote < 0)   
			error.setErrorInfo(ErrorFormView.APPLICATION_ERROR,"Aggiunta nuova nota non riuscita!");
		else
			error.setStatus(newidnote);
		
		return error;
	
	}
	
	@RequestMapping(value="/del")
	public @ResponseBody  ErrorFormView del(HttpServletRequest request)
	{	this.debugMessage(logger,"Url match with function delete!!! ");
		
		String idnote=request.getParameter("id");
		System.out.println("NOTA DA CANCELLARE : "+idnote);
		ErrorFormView error=new ErrorFormView();
		NoteService activityService = (NoteService) SpringApplicationContext.getServiceBean("noteService");
	
		if (activityService.deleteNote(Long.parseLong(idnote))== false)   
			error.setErrorInfo(ErrorFormView.APPLICATION_ERROR,"Cancellazione nota non riuscita!");
		
		
		return error;
	
	}

}
