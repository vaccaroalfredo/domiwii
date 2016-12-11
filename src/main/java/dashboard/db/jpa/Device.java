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

import dashboard.db.ASDataFormat;

@Entity(name="Device")
@Access(AccessType.FIELD)
@Table(
		 uniqueConstraints = {@UniqueConstraint(columnNames = {"idDevice"})}
)
public class Device implements Serializable {
	@Column(name = "uid", unique = true, nullable = false)
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
	@Column(name = "alias", unique = true, nullable = false)
	private String alias;
	private String password;
	private String temperature;
	private String humidity;
	
//	public Device(String uid, List<Scheduler> scheduler) {
//		
//		super();
//		this.uid = uid;
//		this.scheduler= scheduler;
//		
//	}
	public Device(String uid,String alias,String password, String temperature, String humidity) {
		
		super();
		this.uid = uid;
		this.password = password;
		this.alias = alias;
		this.temperature = temperature;
		this.humidity = humidity;
					
		
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
	
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	
	public String getPasswordEncrypt(){
		return ASDataFormat.sha256(this.getPassword());
	}
	
	public void setPasswordEncrypt(){
		this.password= ASDataFormat.sha256(this.getPassword());
	}
	
	public static String getPasswordEncrypt(String password){
		return ASDataFormat.sha256(password);
	}
	

}
