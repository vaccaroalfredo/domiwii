package dashboard.db.dao;

import java.util.Date;
import java.util.List;

import dashboard.db.jpa.Note;


public interface NoteDao {
	public Long addNote(Note note);
	public Note getNoteByID(Long idnote);
	public boolean updateNote(Note note);
    public List<Note> getNotes(); 
    public List<Note> getNotes(String owner, Date startdate,Date enddate,Boolean hightlights,Boolean archive);
    public boolean deleteNote(Long idnote);

}
