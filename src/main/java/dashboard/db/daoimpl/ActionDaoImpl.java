package dashboard.db.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import dashboard.db.dao.AbstractDao;
import dashboard.db.dao.ActionDao;
import dashboard.db.dao.DeviceDao;
import dashboard.db.jpa.Action;
import dashboard.db.jpa.Device;
import dashboard.db.jpa.Note;

public class ActionDaoImpl extends AbstractDao<Action> implements ActionDao  {

	@Override
	public Long addAction(Action action) {
		Long idaction = null;
		try{
			this.openTransaction();
			this.persist(action);
			idaction = action.getId();
			System.out.println("Inserita action "+idaction);
		}catch(Exception e){
			this.closeTransaction(false);
		}finally{
			this.closeTransaction(true);
		}
		return idaction;
	}

	@Override
	public Action getActionById(Long id) {
		// TODO Auto-generated method stub
		Action action=null;
		try{
			this.openTransaction();
			action = this.get(Action.class, id);
		}catch(Exception e){
			this.closeTransaction(false);
		}finally{
			this.closeTransaction(true);
		}
		return action;
		
	}

//	@Override
//	public Action getActionByDeviceId(Long idDevice) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public List<Action> getActionsByDeviceId(String idDevice) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<Action> getActions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existAction(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAction(Long id) {
		boolean ok=false;
		try{
			this.openTransaction();
			this.delete(Action.class,id);
			ok=true;
		}catch(Exception e){
			this.closeTransaction(ok);
		}finally{
			this.closeTransaction(ok);
		}
		return ok;
	}

	
}
