package dashboard.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dashboard.db.jpa.Conditioner;

public class ConditionerResponse implements Serializable {
	
	private List<Conditioner> response =  new ArrayList<>();
	
	
	

	public ConditionerResponse(List<Conditioner> response) {
		super();
		this.response = response;
	}

	public List<Conditioner> getResponse() {
		return response;
	}

	public void addConditioner(Conditioner cond) {
		this.response.add(cond);
	}

	
	
	
	
	
	
	
	
	
}
