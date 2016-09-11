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
	
	public Boolean existDeviceByAlias(String alias){
		
		DeviceDao dao = new DeviceDaoImpl();		
		try{
			return dao.existDevice(alias);
		}catch(Exception e){
			logger.error("existDevice",e);
		}
		return false;	
	}
	
	public boolean authDevice(String alias, String password){
		
		DeviceDao dao = new DeviceDaoImpl();
		boolean toReturn = dao.authDevice( alias, password );
		
		return toReturn;
	}
	
	public boolean addDevice( Device device){
		
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
	
	public Long updateDevice( Device device){
		
		Long toreturn = new Long(-1);
		DeviceDao dao = new DeviceDaoImpl();
		try{
			 toreturn = dao.updateDevice( device ); 
			 return toreturn;
		}catch(ConstraintViolationException e){
			logger.error("storeDevice",e);
			return toreturn;
		}catch(Exception e){
			logger.error("storeDevice",e);
		}
		return toreturn;
		
		
		
	}
	public Device getDeviceByAlias(String alias){
		
		Device dev= null;
		DeviceDao dao = new DeviceDaoImpl();
		
		dev = dao.getDeviceByAlias(alias);
		
		if (dev == null) {
			logger.error("getDeviceByAlias Device"+alias+" not found");
		}
		
		return dev;
	}

	public boolean updateDeviceMetadata(String iddevice, String temperature, String humidity) {
		
		boolean toReturn=false;
		DeviceDao dao = new DeviceDaoImpl();
		try{
			toReturn = dao.updateDeviceMetadata( iddevice, temperature, humidity ); 
			 return toReturn;
		}catch(ConstraintViolationException e){
			logger.error("storeDevice",e);
			return toReturn;
		}catch(Exception e){
			logger.error("storeDevice",e);
		}
		return toReturn;
	}
	

}
