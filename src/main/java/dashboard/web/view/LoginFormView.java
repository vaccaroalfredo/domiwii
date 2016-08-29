package dashboard.web.view;

public class LoginFormView {
	private String username;
	private String password;
	private Boolean noapplicationuser;
	
	
	public LoginFormView() {
		super();
		this.username="";
		this.password="";
	}
	public LoginFormView(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getNoapplicationuser() {
		return noapplicationuser;
	}
	public void setNoapplicationuser(Boolean noapplicationuser) {
		this.noapplicationuser = noapplicationuser;
	}
	
	

}
