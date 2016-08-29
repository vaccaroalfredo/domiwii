package dashboard.db.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import dashboard.db.dao.ActivityDao;
import dashboard.db.dao.SettingDao;
import dashboard.db.daoimpl.ActivityDaoImpl;
import dashboard.db.daoimpl.SettingDaoImpl;
import dashboard.db.jpa.Setting;

@Service("settingService")
public class SettingService {
	private static final Logger logger = Logger.getLogger(SettingService.class);

	
	
	public SettingService() {
		super();

	}


	public boolean storeSetting(Setting setting){
		logger.debug("DEBUG--SERVICE--storeSetting");
		SettingDao dao = new SettingDaoImpl();
		return dao.updateSetting(setting); 
		
	}
	
	public Long addSetting(Setting setting){
		logger.debug("DEBUG--SERVICE--addSetting ");
		SettingDao dao = new SettingDaoImpl();
		return dao.addSetting(setting);
		
	}
	
	public boolean deleteSetting(Long idsetting){
		logger.debug("DEBUG--SERVICE--deleteSetting ");
		SettingDao dao = new SettingDaoImpl();
		return dao.deleteSetting(idsetting);
		
	}
	
	
	public List<Setting> findSetting(){
		logger.debug("DEBUG--SERVICE--findSetting ");
		SettingDao dao = new SettingDaoImpl();
		List<Setting> setting= dao.getSetting();
		if ( setting.size()==0){
			
			Setting s=new Setting(50,30,10);
			setting.add(s);
		}
		return setting;
	}
	
	
 
   
}
