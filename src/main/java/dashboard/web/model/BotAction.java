package dashboard.web.model;

import java.io.Serializable;

	
public class BotAction implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private String devId;
	private String mode = "-1";
	private String status;
	private String temperature;
	private String speed;
	private String confort;
	private String alias;
	private String uiid;
	
	
	public BotAction(String mode, String temperature, String speed, String confort,String alias, String uiid) {
		super();
//		this.devId = devId;
		this.mode = mode;
		this.temperature = temperature;
		this.speed = speed;
		this.confort = confort;
		this.alias = alias;
		this.uiid=uiid;
		
		
		
		
	}
	public String getUiid() {
		return uiid;
	}
	public void setUiid(String uiid) {
		this.uiid = uiid;
	}
	public String getConfort() {
		return confort;
	}
	public void setConfort(String confort) {
		this.confort = confort;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public BotAction() {
		super();
		
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
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getStatus() {
		if (this.mode.equalsIgnoreCase("0")) {
			this.status = "0";
		}else{
			this.status = "1";
		}
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
