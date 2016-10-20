package dashboard.web.model;

public class CheckDeviceResponse extends Response {
	
	private String temperature;
	private String humidity;

	public CheckDeviceResponse(String response, ResponseCode code,String temperature, String humidity) {
		super(response, code);
		this.temperature=temperature;
		this.humidity=humidity;
		
	}
	
	

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	
	
	

}
