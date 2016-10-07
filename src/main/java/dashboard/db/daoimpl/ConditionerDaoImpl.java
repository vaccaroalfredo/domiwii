package dashboard.db.daoimpl;

import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import dashboard.db.dao.AbstractDao;
import dashboard.db.dao.ConditionerDao;
import dashboard.db.dao.DeviceDao;
import dashboard.db.jpa.Conditioner;
import dashboard.db.jpa.Device;
import dashboard.db.jpa.User;

public class ConditionerDaoImpl extends AbstractDao<Conditioner> implements ConditionerDao {

	


	@Override
	public Boolean addConditioner(Conditioner cond) {
		
		Boolean isadded = false;
		try {

			this.openTransaction();
			this.save(cond);

			System.out.println("Inserito cond " + cond.getModel());
			isadded=true;
			
		} catch (Exception e) {
			
			this.closeTransaction(false);
			
		} finally {
			isadded=true;
			this.closeTransaction(true);
			
		}
		return isadded;
		
	}

	@Override
	public List<Conditioner> getAllConditioner() {
		
		try{
			this.openTransaction();
			
			Criteria cr = this.getSession().createCriteria(Conditioner.class);
			List results = cr.list();
			return results;

		}catch(Exception e){
			this.closeTransaction(false);
		}finally{
			this.closeTransaction(true);
		}

		return null;
		
	}

	@Override
	public List<Conditioner> getConditionerbyModel() {
		
		return null;
	}
}
