package dashboard.db.dao;

import java.util.List;

import dashboard.db.jpa.Activity;
import dashboard.db.jpa.Setting;
import dashboard.db.jpa.User;

public interface SettingDao {
	public Long addSetting(Setting user);
	public boolean updateSetting(Setting user);
    public List<Setting> getSetting(); 
    public boolean deleteSetting(Long idsetting);
}
