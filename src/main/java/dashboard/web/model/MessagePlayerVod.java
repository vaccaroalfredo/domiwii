package dashboard.web.model;

import java.io.Serializable;

public class MessagePlayerVod extends Message implements Serializable{
	
	String programid;
	String color;
	
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public MessagePlayerVod(String m, String deviceid, String id, String color) {
		super(m,deviceid);
		this.programid = id;
		this.color=color;
	}
	public MessagePlayerVod() {
		super();
		
	}
	public String getProgramid() {
		return programid;
	}
	public void setProgramid(String id) {
		this.programid = id;
	}

}