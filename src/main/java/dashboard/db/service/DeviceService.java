package dashboard.db.service;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

import dashboard.db.dao.DeviceDao;
import dashboard.db.dao.UserDao;
import dashboard.db.daoimpl.DeviceDaoImpl;
import dashboard.db.daoimpl.UserDaoImpl;
import dashboard.db.jpa.Device;

@Service("deviceService")
public class DeviceService {
	private static final Logger logger = Logger.getLogger(DeviceService.class);
	
	public DeviceService(){
		super();
	}
	
	public Boolean existDevice(String uid){
		
		DeviceDao dao = new DeviceDaoImpl();		
		try{
			return dao.existDevice(uid);
		}catch(Exception e){
			logger.error("existDevice",e);
		}
		return false;	
	}
	
	public boolean addDevice(Device device){
		
		logger.debug("DEBUG--SERVICE--storeDevice");
		DeviceDao dao = new DeviceDaoImpl();
		try{
			 dao.addDevice( device ); 
			 return true;
		}catch(ConstraintViolationException e){
			logger.error("storeDevice",e);
			return false;
		}catch(Exception e){
			logger.error("storeDevice",e);
		}
		return false;
		
	}

}
