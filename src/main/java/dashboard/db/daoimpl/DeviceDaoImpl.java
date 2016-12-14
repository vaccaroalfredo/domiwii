package dashboard.db.daoimpl;

import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import dashboard.db.dao.AbstractDao;
import dashboard.db.dao.DeviceDao;
import dashboard.db.jpa.Device;
import dashboard.db.jpa.User;

public class DeviceDaoImpl extends AbstractDao<Device> implements DeviceDao {

	@Override
	public Long addDevice(Device device) {

		Long iddevice = null;
		try {

			this.openTransaction();

			//device.setPasswordEncrypt();

			this.save(device);

			iddevice = device.getId();

			System.out.println("Inserita device " + iddevice);

		} catch (Exception e) {
			
			this.closeTransaction(false);
			
		} finally {
			
			this.closeTransaction(true);
			
		}
		return iddevice;

	}

	@Override
	public Device getDeviceByUid(String uid) {
		try{
			this.openTransaction();
			
			Criteria cr = this.getSession().createCriteria(Device.class);
			cr.add(Restrictions.eq("uid", uid));
			List results = cr.list();
			if (results.size() == 1)
			  return (Device) results.get(0);

		}catch(Exception e){
			this.closeTransaction(false);
		}finally{
			this.closeTransaction(true);
		}

		return null;
	}

	@Override
	public List<Device> getDevices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existDevice(String uid) {
		try{
			this.openTransaction();
			
			Criteria cr = this.getSession().createCriteria(User.class);
			cr.add(Restrictions.eq("uid", uid));
			List results = cr.list();
			if (results.size() == 1)
			  return true;

		}catch(Exception e){
			this.closeTransaction(false);
		}finally{
			this.closeTransaction(true);
		}

		return false;
	}

	@Override
	public boolean existDeviceByAlias(String alias) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteDevice(String uid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean authDevice(String alias, String password) {

		Device device = this.getDeviceByAlias(alias);
		//String pass=device.getPasswordEncrypt();
		if(device == null){
			
			device = this.getDeviceByUid(alias);
			
		}
		if (device != null && device.getPassword().equalsIgnoreCase( Device.getPasswordEncrypt(password))) {

			return true;

		}

		return false;
	}
	
	@Override
	public Device getDeviceByAlias(String alias) {

		List<Device> deviceList = null;
		this.openTransaction();
		Criteria cr = this.getSession().createCriteria(Device.class);
		// cr = cr.setProjection(Projections.property("alias"));
		cr = cr.add(Restrictions.eq("alias", alias.trim()));
		deviceList = cr.list();

		this.closeTransaction(true);

		if (deviceList.size() == 1) {

			return deviceList.get(0);

		}

		return null;

	}
	@Override
	public List<Device> getDevicesByAlias(List<String> alias) {

		List<Device> deviceList = null;
		this.openTransaction();
		Criteria cr = this.getSession().createCriteria(Device.class);
		// cr = cr.setProjection(Projections.property("alias"));
		cr = cr.add(Restrictions.in("alias", alias));
		deviceList = cr.list();

		this.closeTransaction(true);

		
			return deviceList;

		

	}
	@Override
	public List<Device> getDevicesByUid(List<String> uid) {

		List<Device> deviceList = null;
		this.openTransaction();
		Criteria cr = this.getSession().createCriteria(Device.class);
		// cr = cr.setProjection(Projections.property("alias"));
		cr = cr.add(Restrictions.in("uid", uid));
		deviceList = cr.list();

		this.closeTransaction(true);

		
			return deviceList;

		

	}
	@Override
	public Long updateDevice(Device device) {

		Long id = new Long(-1);
		try {
			Device dev = null;
			if (device.getUid() != null) {				
				dev = this.getDeviceByUid(device.getUid());				
			}
			
			if (dev != null) {
				
				this.openTransaction();
				dev.setAlias(device.getAlias());
				dev.setUid(device.getUid());
				dev.setPassword(device.getPassword());
				//dev.setPasswordEncrypt();				
				this.saveOrUpdate(dev);
				id = dev.getId();
			
			}else{
				
				id = this.addDevice(device);
				
			}
		} catch (Exception e) {
			this.closeTransaction(false);
		} finally {
			this.closeTransaction(true);
		}
		return id;

	}

	@Override
	public boolean updateDeviceMetadata(String iddevice, String temperature, String humidity) {
		
		
		boolean updated = false;
		try {
			Device dev = null;
			if (iddevice != null) {				
				dev = this.getDeviceByUid(iddevice);				
			}
			
			if (dev != null) {
				
				this.openTransaction();
				dev.setTemperature(temperature);
				dev.setHumidity(humidity);
							
				this.saveOrUpdate(dev);
				updated = true;
			
			}else{
				
				updated = false;
				
			}
		} catch (Exception e) {
			this.closeTransaction(false);
		} finally {
			this.closeTransaction(true);
		}
		return updated;
	}
}
