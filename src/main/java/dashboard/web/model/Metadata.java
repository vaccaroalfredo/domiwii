package dashboard.web.model;

import java.io.Serializable;

public class Metadata implements Serializable{
	
	private String alias;
	private String temperature;
	private String humidity;
	
	
	
	
	
	public Metadata(String alias, String temperature, String humidity) {
		super();
		this.alias = alias;
		this.temperature = temperature;
		this.humidity = humidity;
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
	public String getHumdity() {
		return humidity;
	}
	public void setHumdity(String humdity) {
		this.humidity = humdity;
	}
	
	

}
