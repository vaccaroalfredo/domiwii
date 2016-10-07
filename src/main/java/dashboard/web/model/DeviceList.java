package dashboard.web.model;

import java.io.Serializable;
import java.util.List;

import dashboard.db.ASDataFormat;

public class DeviceList implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> devicesAlias;

	
	
	public DeviceList(List<String> devicesAlias) {
		super();
		this.devicesAlias = devicesAlias;
		
	}
	
	public DeviceList(){
		
		super();
	}
	
	
	public List<String> getAlias() {
		return devicesAlias;
	}
	public void setAlias(List<String> devicesAlias) {
		this.devicesAlias = devicesAlias;
	}
	

	
	

}
