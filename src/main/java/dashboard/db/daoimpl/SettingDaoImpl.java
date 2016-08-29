package dashboard.db.daoimpl;

import java.util.List;

import org.hibernate.Session;

import dashboard.db.dao.AbstractDao;
import dashboard.db.dao.SettingDao;
import dashboard.db.jpa.Activity;
import dashboard.db.jpa.Setting;
import dashboard.db.jpa.User;

public class SettingDaoImpl extends AbstractDao<Setting> implements SettingDao {


	public SettingDaoImpl() {
		super();
	}

	public SettingDaoImpl(Session session) {
		super(session);
		
	}

	@Override
	public Long addSetting(Setting setting) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateSetting(Setting setting) {
		boolean ok=false;
		try{
			this.openTransaction();
			this.saveOrUpdate(setting);
			ok=true;
		}catch(Exception e){
			this.closeTransaction(ok);
		}finally{
			this.closeTransaction(ok);
		}
		return ok;
	}

	@Override
	public List<Setting> getSetting() {
		List<Setting> activity=null;
		try{
			this.openTransaction();
			activity=this.find(Setting.class,"id",false);
		}catch(Exception e){
			this.closeTransaction(false);
		}finally{
			this.closeTransaction(true);
		}
		return activity;
	}

	@Override
	public boolean deleteSetting(Long idsetting) {
		// TODO Auto-generated method stub
		return false;
	}

}
