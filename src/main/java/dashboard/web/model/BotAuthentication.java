package dashboard.web.model;

import java.io.Serializable;

import dashboard.db.ASDataFormat;

public class BotAuthentication implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String alias;
	private String password;
	
	
	public BotAuthentication(String alias, String password) {
		super();
		this.alias = alias;
		this.password = password;
	}
	
	public BotAuthentication(){
		
		super();
	}
	
	
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static String getPasswordEncrypt(String password){
		return ASDataFormat.sha256(password);
	}
	
	

}
