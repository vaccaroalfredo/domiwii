package dashboard.db.dao;

import java.util.List;

import dashboard.db.jpa.Device;

public interface DeviceDao {
	
	public Long addDevice(Device device);
	public Device getDeviceByUid(String uid);
	public List<Device> getDevices();
	public boolean existDevice(String uid);
	public boolean existDeviceByAlias(String alias);
	public boolean deleteDevice(String uid);
	public boolean authDevice(String alias, String password);
	public Device getDeviceByAlias(String alias);
	public Long updateDevice(Device device);
	public boolean updateDeviceMetadata(String iddevice, String temperature, String humidity);
	List<Device> getDevicesByAlias(List<String> alias);
	

}
