package dashboard.web.model;

import java.io.Serializable;

import dashboard.db.jpa.Scheduler;

public class MonitorData implements Serializable {

	//private String schedulerId;
	private String actionState;
	private String deviceAlias;
	private String command;
	private String date;
	
	
	
	
	public MonitorData(String actionState, String deviceAlias, String command, String date) {
		super();
	//	this.schedulerId = schedulerId;
		this.actionState = actionState;
		this.deviceAlias = deviceAlias;
		this.command = command;
		this.date = date;
	}
	
	
	
	
	public MonitorData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void buildObjByScheduler(Scheduler s){
		
	
		this.actionState = s.getDeviceActionId().getAction().getState().toString();
		this.deviceAlias = s.getDeviceActionId().getDevice().getAlias();
		this.command = s.getDeviceActionId().getAction().getCommand();
		this.date = s.getCreationdate().toString();
		
		
	}

	public String getActionState() {
		return actionState;
	}
	public void setActionState(String actionState) {
		this.actionState = actionState;
	}
	public String getDeviceAlias() {
		return deviceAlias;
	}
	public void setDeviceAlias(String deviceAlias) {
		this.deviceAlias = deviceAlias;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	
}
