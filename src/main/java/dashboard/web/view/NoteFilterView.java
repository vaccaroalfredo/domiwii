package dashboard.web.view;

import java.util.Date;

public class NoteFilterView {
	public String description;
	private Date startdate;
	private Date enddate;
	private Boolean high;
	private boolean archive;
	
	
	public NoteFilterView() {
		super();
		this.archive=false;
		// TODO Auto-generated constructor stub
	}
	
	

	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
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
	public Boolean getHigh() {
		return high;
	}
	public void setHigh(Boolean high) {
		this.high = high;
	}



	public boolean getArchive() {
		return archive;
	}



	public void setArchive(boolean archive) {
		this.archive = archive;
	}
	
	
	
	

}
