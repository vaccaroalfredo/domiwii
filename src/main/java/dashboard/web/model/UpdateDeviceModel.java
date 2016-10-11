package dashboard.web.model;

import java.io.Serializable;

public class UpdateDeviceModel implements Serializable{
	
	private String alias;
	private String newAlias;
	private String password;
	private String newPassword;
	
	
	
	
	
	public UpdateDeviceModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UpdateDeviceModel(String alias, String password, String newAlias, String newPassword) {
		super();
		this.alias = alias;
		this.password = password;
		this.newAlias= newAlias;
		this.newPassword=newPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
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
	public String getNewAlias() {
		return newAlias;
	}
	public void setNewAlias(String newAlias) {
		this.newAlias = newAlias;
	}
	
	

}
