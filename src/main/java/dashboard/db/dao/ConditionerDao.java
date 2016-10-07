package dashboard.db.dao;

import java.util.List;

import dashboard.db.jpa.Conditioner;


public interface ConditionerDao {
	
	public Boolean addConditioner(Conditioner cond);
	public List<Conditioner> getAllConditioner();
	public List<Conditioner> getConditionerbyModel();
	

}
