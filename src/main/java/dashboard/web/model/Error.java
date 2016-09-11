package dashboard.web.model;

import java.io.Serializable;

public class Error implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String code;
	String message;
	
		
	
	
	public Error(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	
	public Error() {
		super();
		
	}


	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
