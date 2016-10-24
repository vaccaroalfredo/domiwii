package dashboard.web.model;

import java.io.Serializable;

public class ServerMessage implements Serializable{
	
	String message;
	String code;
	
	public ServerMessage(String m, String code) {
		super();
		this.message = m;
		this.code=code;
	}
	
	public ServerMessage() {
		super();
		
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String m) {
		this.message = m;
	}	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}	
}