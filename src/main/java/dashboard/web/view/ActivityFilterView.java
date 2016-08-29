package dashboard.web.view;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;



import dashboard.db.ASDataFormat;
import dashboard.db.jpa.Activity;

public class ActivityFilterView implements Serializable{

	private Date startdate;
	private Date enddate;
	private String description;
	private Activity.Status status;
	
	
	
	public ActivityFilterView() {
		super();
//		this.startdate=new Date();
//		Calendar c=new GregorianCalendar();
//		c.add(Calendar.HOUR,24);
//		this.enddate=c.getTime();
	}
	
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Activity.Status getStatus() {
		return status;
	}

	public void setStatus(Activity.Status status) {
		this.status = status;
	}
	
	public void logInfo(){
		System.out.println("Descrizione :"+this.description);
		System.out.println("Data inizio :"+ASDataFormat.dateToString(this.startdate,"dd-MM-yyyy HH:mm:ss"));
		System.out.println("Data fine :"+ASDataFormat.dateToString(this.enddate,"dd-MM-yyyy HH:mm:ss"));
		if (this.status!=null)
		System.out.println("Stato :"+this.status.toString());
		
		
		
	}
	 
	
}
