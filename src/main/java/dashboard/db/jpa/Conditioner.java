package dashboard.db.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity(name="Conditioner")
@Access(AccessType.FIELD)
@Table(
		 uniqueConstraints = {@UniqueConstraint(columnNames = {"model"})}
)	
public class Conditioner implements Serializable {
	
	private String name;
	
	@Id
	@Column(name = "model", unique = true, nullable = false)
	private String model;
	
	@Column(length=10000)
	@Convert( converter = CodeArrayToStringConverter.class)
	private CodeList codes;
	
	
	public Conditioner(String name, String model, CodeList codes) {
		super();
		this.name = name;
		this.model = model;
		this.codes = codes;
	}
	
	
	public Conditioner() {
		super();
		
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public CodeList getCode() {
		return codes;
	}
	public void setCode(CodeList code) {
		this.codes = code;
	}
	
	

}
