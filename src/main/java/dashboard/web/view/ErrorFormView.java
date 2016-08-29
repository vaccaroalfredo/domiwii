package dashboard.web.view;

public class ErrorFormView {
	public final static Long APPLICATION_ERROR=new Long(-1);
	
	private Long status;
	private String errordescription;
	
	
	
	
	public ErrorFormView() {
		super();
		this.status=new Long(0);
	}
	public ErrorFormView(Long status, String errordescription) {
		super();
		this.setErrorInfo(status, errordescription);
	}
	
	public void setErrorInfo(Long status, String errordescription){
		this.status = status;
		this.errordescription = errordescription;
	}
	
	
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getErrordescription() {
		return errordescription;
	}
	public void setErrordescription(String errordescription) {
		this.errordescription = errordescription;
	}
	
	
	
}
