package dashboard.db.jpa;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name="Device")
@Access(AccessType.FIELD)
@Table(
		 uniqueConstraints = {@UniqueConstraint(columnNames = {"uid"})}
)
public class Device implements Serializable {
	
	private String uid;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq_gen")
	@SequenceGenerator(name="seq_gen", sequenceName="user_seq",allocationSize = 1,initialValue=1)	
	private Long id;
	
	public Device(String uid) {
		
		super();
		this.uid = uid;
		
	}
	
	public Device(){
		super();		
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	

}
