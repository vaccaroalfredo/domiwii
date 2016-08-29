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
import dashboard.db.dao.UserDao;
import dashboard.db.jpa.Activity;
import dashboard.db.jpa.Note;
import dashboard.db.jpa.User;


public class NoteDaoImpl extends AbstractDao<Note> implements NoteDao {
	
	@Override
	public List<Note> getNotes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Note> getNotes(String description, Date startdate, Date enddate,Boolean hightlights,Boolean archive) {
		List<Note> note=null;
		try{
			this.openTransaction();
			
			Criteria c=this.getSession().createCriteria(Note.class);
			if (hightlights==false){
				c=c.add(Restrictions.disjunction()
									.add(Restrictions.disjunction().add(Restrictions.eq("highlights",new Integer(0))).add(Restrictions.eq("archive",new Boolean(true))).add(Restrictions.isNull("archive"))   )
	                                .add(Restrictions.isNull("highlights")));
				if(startdate != null && enddate != null)
				{
					if (startdate.before(enddate)){
						c=c.add(Restrictions.between("creationdate", startdate,enddate));
					}
				}
				if (description!=null && description.trim().length()!=0)
					c=c.add(Restrictions.like("description", "%"+description.trim()+"%"));
				
				
				c=c.add(Restrictions.eq("archive", archive));
				
			}else{
				c=c.add(Restrictions.eq("highlights",new Integer(1)));
				c=c.add(Restrictions.ne("archive",true));
			}      
		
			
			c=c.addOrder(Order.desc("creationdate"));
			note=c.list();
		}catch(Exception e){
			this.closeTransaction(false);
		}finally{
			this.closeTransaction(true);
		}
		return note;
	}

	public Long addNote(Note note) {
		Long idnote=null;
		try{
			this.openTransaction();
			this.persist(note);
			idnote=note.getId();
			System.out.println("Inserita nota "+idnote);
		}catch(Exception e){
			this.closeTransaction(false);
		}finally{
			this.closeTransaction(true);
		}
		return idnote;
	}
	public boolean updateNote(Note note) {
		boolean ok=false;
		try{
			this.openTransaction();
			this.saveOrUpdate(note);
			ok=true;
		}catch(Exception e){
			this.closeTransaction(ok);
		}finally{
			this.closeTransaction(ok);
		}
		return ok;
	}
	

	
	public boolean deleteNote(Long idnote) {
		boolean ok=false;
		try{
			this.openTransaction();
			this.delete(Note.class,idnote);
			ok=true;
		}catch(Exception e){
			this.closeTransaction(ok);
		}finally{
			this.closeTransaction(ok);
		}
		return ok;
	}

	public List<String> getNoteByOwner(String owner) {
		List<String> names=null;
		boolean ok=false;
		try{
			this.openTransaction();
			Criteria cr = this.getSession().createCriteria(Note.class);
			cr=cr.setProjection(Projections.property("owner"));
			if (owner!=null && !owner.equals(""))
			cr=cr.add(Restrictions.like("owner", owner.trim()+"%"));
			
			names=cr.list();
			ok=true;
		}catch(Exception e){
			this.closeTransaction(ok);
		}finally{
			this.closeTransaction(ok);
		}
		return names;
	}
	public Note getNoteByStartdate(Date startdate) {
		Note note = null;
		try{
			this.openTransaction();
			note=this.getNoteByStartdate(startdate);
		}catch(Exception e){
			this.closeTransaction(false);
		}finally{
			this.closeTransaction(true);
		}
		return note;
	}

	public Note getNoteByID(Long id) {
		Note note=null;
		try{
			this.openTransaction();
			note=this.get(Note.class, id);
		}catch(Exception e){
			this.closeTransaction(false);
		}finally{
			this.closeTransaction(true);
		}
		return note;
	}

	

	

}
