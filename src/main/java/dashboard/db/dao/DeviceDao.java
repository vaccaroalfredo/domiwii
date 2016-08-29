package dashboard.db.dao;

import java.util.List;

import dashboard.db.jpa.Device;

public interface DeviceDao {
	
	public Long addDevice(Device device);
	public Device getDeviceByUid(String uid);
	public List<Device> getDevices();
	public boolean existDevice(String uid);
	public boolean deleteDevice(String uid);

}
