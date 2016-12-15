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
	private String uid;
	
	
	public BotAuthentication(String alias, String password, String uid) {
		super();
		this.alias = alias;
		this.password = password;
		this.uid=uid;
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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
	

}
