package dashboard.web.model;

import java.io.Serializable;

public class Metadata implements Serializable{
	
	private String alias;
	private String temperature;
	private String humidity;
	private String uiid;
	
	
	
	
	
	public Metadata(String uiid, String alias, String temperature, String humidity) {
		super();
		this.alias = alias;
		this.temperature = temperature;
		this.humidity = humidity;
		this.uiid= uiid;
	}
	
	public String getUiid() {
		return uiid;
	}

	public void setUiid(String uiid) {
		this.uiid = uiid;
	}

	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
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
