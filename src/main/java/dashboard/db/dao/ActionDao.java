package dashboard.db.dao;

import java.util.List;

import dashboard.db.jpa.Action;


public interface ActionDao {
	
	public Long addAction(Action action);
	public Action getActionById(Long id);
//	public Action getActionByDeviceId(Long idDevice);
//	public List<Action> getActionsByDeviceId(String idDevice);
	public List<Action> getActions();
	public boolean existAction(String id);
	public boolean deleteAction(Long id);

}
