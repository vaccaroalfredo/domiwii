package dashboard.db.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CodeList implements Serializable{
	
	private List<Code> codes;

	
	public CodeList() {
		super();
		this.codes = new ArrayList<>();
	}
	public CodeList(List<Code> codes) {
		super();
		this.codes = codes;
	}

	public List<Code> getCodes() {
		return codes;
	}

	public void setCodes(List<Code> codes) {
		this.codes = codes;
	}
	
	

}
