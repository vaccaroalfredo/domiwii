package dashboard.db.daoimpl;

import java.util.List;

import dashboard.db.dao.AbstractDao;
import dashboard.db.dao.DeviceDao;
import dashboard.db.jpa.Device;

public class DeviceDaoImpl extends AbstractDao<Device> implements DeviceDao {

	@Override
	public Long addDevice(Device device) {
		
		Long iddevice = null;
		try {
			this.openTransaction();
			
		
			
			this.save(device);
			
			iddevice = device.getId();
			
			System.out.println("Inserita device "+iddevice);
		}catch(Exception e){
			this.closeTransaction(false);
		}finally{
			this.closeTransaction(true);
		}
		return iddevice;
		
	}

	@Override
	public Device getDeviceByUid(String uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Device> getDevices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existDevice(String uid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteDevice(String uid) {
		// TODO Auto-generated method stub
		return false;
	}

}
