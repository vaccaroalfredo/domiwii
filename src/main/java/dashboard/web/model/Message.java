package dashboard.web.model;

import java.io.Serializable;

public class Message implements Serializable{
	
	String message;
	String deviceid;
	
	public Message(String m, String deviceid) {
		super();
		this.message = m;
		this.deviceid=deviceid;
	}
	
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String m) {
		this.message = m;
	}	
	
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String id) {
		this.deviceid = id;
	}	
}