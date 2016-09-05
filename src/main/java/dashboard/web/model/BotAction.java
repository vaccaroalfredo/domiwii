package dashboard.web.model;

import java.io.Serializable;

public class BotAction implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String devId;
	private String mode;
	private String temperature;
	private String speed;
	
	
	public BotAction(String devId, String mode, String temperature, String speed) {
		super();
		this.devId = devId;
		this.mode = mode;
		this.temperature = temperature;
		this.speed = speed;
	}
	public BotAction() {
		super();
		
	}
	
	public String getDevId() {
		return devId;
	}
	public void setDevId(String devId) {
		this.devId = devId;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	
	
	
	
}
