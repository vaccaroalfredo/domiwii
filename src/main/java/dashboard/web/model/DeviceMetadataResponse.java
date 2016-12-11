package dashboard.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dashboard.db.jpa.Conditioner;
import dashboard.db.jpa.Device;

public class DeviceMetadataResponse implements Serializable {
	
	private List<Metadata> response =  new ArrayList<>();
	
	
	

	public DeviceMetadataResponse(List<Device> response) {
		super();
		
		this.buildMetadataBuDevices(response);
		
	}

	private void buildMetadataBuDevices(List<Device> devices) {
		
		for (Device device : devices) {
			
			this.response.add(new Metadata(device.getUid(),device.getAlias(), device.getTemperature(), device.getHumidity()));
			
		}
	}

	public List<Metadata> getResponse() {
		return response;
	}

	

	
	
	
	
	
	
	
	
	
}
