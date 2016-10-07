package dashboard.db.jpa;

import java.io.Serializable;
import java.util.List;

public class Code implements Serializable {
	
	private ConditionerCodeMode name;
	private List<Integer> codes;
	
	
	
	public Code() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Code(ConditionerCodeMode name, List<Integer> codes) {
		super();
		this.name = name;
		this.codes = codes;
	}
	
	public ConditionerCodeMode getName() {
		return name;
	}
	public void setName(ConditionerCodeMode name) {
		this.name = name;
	}
	public List<Integer> getCodes() {
		return codes;
	}
	public void setCodes(List<Integer> codes) {
		this.codes = codes;
	}

	
	
	
	
}
