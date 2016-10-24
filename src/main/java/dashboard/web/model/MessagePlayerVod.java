package dashboard.web.model;



public class MessagePlayerVod extends Message{
	
	String programid;
	
	
	public MessagePlayerVod(String m, String deviceid, String id) {
		super(m,deviceid);
		this.programid = id;
	}
	public String getProgramid() {
		return programid;
	}
	public void setProgramid(String id) {
		this.programid = id;
	}

}