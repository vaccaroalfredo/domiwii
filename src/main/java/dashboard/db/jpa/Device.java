package dashboard.db.jpa;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name="Device")
@Access(AccessType.FIELD)
@Table(
		 uniqueConstraints = {@UniqueConstraint(columnNames = {"idDevice"})}
)
public class Device implements Serializable {
	
	private String uid;
	//
	//@OneToMany(mappedBy="device")
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy ="deviceActionId.device")
	private List<Scheduler> scheduler ;//= new LinkedList<Scheduler>();// = new HashSet<Scheduler>(0);

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq_gen")
	@SequenceGenerator(name="seq_gen", sequenceName="device_seq",allocationSize = 1,initialValue=1)	
	@Column(name = "idDevice", unique = true, nullable = false)
	private Long id;
	
//	public Device(String uid, List<Scheduler> scheduler) {
//		
//		super();
//		this.uid = uid;
//		this.scheduler= scheduler;
//		
//	}
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
	
	
	public List<Scheduler> getScheduler() {
		return scheduler;
	}

	public void setScheduler(List<Scheduler> scheduler) {
		this.scheduler = scheduler;
	}
	
	

}
