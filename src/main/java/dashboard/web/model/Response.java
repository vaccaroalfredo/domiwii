package dashboard.web.model;

import java.io.Serializable;

public class Response implements Serializable {
	
	public static enum ResponseCode{
		OK{
			@Override
			public String toString() {
				// TODO Auto-generated method stub
				return "1";
			}
		}, KO{
			@Override
			public String toString() {
				// TODO Auto-generated method stub
				return "-1";
			}
		}
	}
	
	private String response;
	private String code; 
	
	

	public Response(String response, ResponseCode code) {
		super();
		this.response = response;
		this.code= code.toString();
	}
	public Response(String response) {
		super();
		this.response = response;
		this.code= ResponseCode.OK.toString();
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

}
