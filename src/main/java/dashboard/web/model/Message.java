package dashboard.web.model;



public class Message{
	
	String message;
	String deviceid;
	
	public Message(String m, String deviceid) {
		super();
		this.message = m;
		this.deviceid=deviceid;
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