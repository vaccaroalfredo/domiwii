package dashboard.web.session;

import java.io.Serializable;



import dashboard.db.jpa.User;
import dashboard.db.jpa.User.ProfileAccess;
import dashboard.web.controller.MenuController.MenuItem;
import dashboard.web.view.ActivityFilterView;


//@Component("userSessionInfo")
//@Scope(value="session",proxyMode=ScopedProxyMode.TARGET_CLASS)
public class UserSessionInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User userData;
	private MenuItem sectionMenu;
	private ActivityFilterView searchFilter;
	//private String username;
	
	public UserSessionInfo() {
		super();
		
	}

	public UserSessionInfo(User userData) {
		super();
		this.userData = userData;
		this.setSectionMenu(MenuItem.Monitor);
	}

	public User getUserData() {
		return userData;
	}

	public void setUserData(User userData) {
		this.userData = userData;
		//this.username=userData.getUsername();
	}
	
	public boolean isAmministrator(){
		return this.userData.getProfile()== ProfileAccess.Administration;
	}
	
	public boolean canEditActivity(){
		return this.isAmministrator();
	}
	
	public boolean canAddActivity(){
		return this.isAmministrator();
	}
	
	public boolean canDeleteActivity(){
		return this.isAmministrator();
	}

	public MenuItem getSectionMenu() {
		return sectionMenu;
	}

	public void setSectionMenu(MenuItem sectionMenu) {
		this.sectionMenu = sectionMenu;
	}

	public boolean isMonitorAccess(){
		return this.sectionMenu== MenuItem.Monitor;	
	}
	
	public boolean isUserAccess(){
		return this.sectionMenu== MenuItem.User;	
	}
	
	public boolean isSettingAccess(){
		return this.sectionMenu== MenuItem.Setting;	
	}
	
	public boolean isActivityAccess(){
		return this.sectionMenu== MenuItem.Activity;	
	}
	
	public boolean isNoteAccess(){
		return this.sectionMenu== MenuItem.Note;	
	}

	public ActivityFilterView getSearchFilter() {
		return searchFilter;
	}

	public void setSearchFilter(ActivityFilterView searchFilter) {
		this.searchFilter = searchFilter;
	}
	
	public String getUser() {
		return this.userData.getUser();
	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	
	
	
}
