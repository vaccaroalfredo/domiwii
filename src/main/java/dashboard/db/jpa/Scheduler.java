package dashboard.db.jpa;

import java.beans.Transient;
import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name="Scheduler")
@Access(AccessType.FIELD)
//@Table(
//		
//		//uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})}
//		
//)

@AssociationOverrides({
	@AssociationOverride(name = "deviceActionId.action",
		joinColumns = @JoinColumn(name = "idAction")),
	@AssociationOverride(name = "deviceActionId.device",
		joinColumns = @JoinColumn(name = "idDevice")) })
public class Scheduler {
	
	private String name;	
	
	@EmbeddedId
	private DeviceActionId deviceActionId = new DeviceActionId();

	//@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq_gen")
//	@SequenceGenerator(name="seq_gen", sequenceName="scheduler_seq",allocationSize = 1,initialValue=1)	
//	private Long id;
	
//	private Action action;
//	private Device device;
	
	public Scheduler(String name) {
		
		super();
		this.setName(name);
		
	}
	
	public Scheduler(){
		super();		
	}

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public DeviceActionId getDeviceActionId() {
		return deviceActionId;
	}

	public void setDeviceActionId(DeviceActionId deviceActionId) {
		this.deviceActionId = deviceActionId;
	}
	
	public void setAction(Action action) {
		
		this.deviceActionId.setAction(action);
	}
	
	public void setDevice(Device device) {
		
		this.deviceActionId.setDevice(device);
	}



	
	
	

//	@Transient
//	public Action getAction(){
//		
//		return this.getDeviceActionId().getAction();
//	}
//	@Transient
//	public Device getDevice(){
//		
//		return this.getDeviceActionId().getDevice();
//	}
	
//	public boolean equals(Object o) {
//		if (this == o)
//			return true;
//		if (o == null || getClass() != o.getClass())
//			return false;
//
//		Scheduler that = (Scheduler) o;
//
//		if (getDeviceActionId() != null ? !getDeviceActionId().equals(that.getDeviceActionId())
//				: that.getDeviceActionId() != null)
//			return false;
//
//		return true;
//	}
//
//	public int hashCode() {
//		return (getDeviceActionId() != null ? getDeviceActionId().hashCode() : 0);
//	}
	

}
