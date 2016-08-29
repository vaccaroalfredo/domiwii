package dashboard.db.jpa;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Access(AccessType.FIELD)
public class Setting implements Serializable{
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;
	 private Integer numberrowstosee;
	 private Integer refreshpagemonitor;
	 private Integer minutetodefinecritic;
	 
	 
	 
	 
	public Setting() {
		super();
	}
	
	
	
	public Setting( Integer numberrowstosee, Integer refreshpagemonitor, Integer minutetodefinecritic) {
		super();
		this.numberrowstosee = numberrowstosee;
		this.refreshpagemonitor = refreshpagemonitor;
		this.minutetodefinecritic = minutetodefinecritic;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getNumberrowstosee() {
		if (this.numberrowstosee==null)
			return new Integer(0);
		return numberrowstosee;
	}

	public void setNumberrowstosee(Integer numberrowstosee) {
		this.numberrowstosee = numberrowstosee;
	}

	public Integer getRefreshpagemonitor() {
		if (this.refreshpagemonitor==null)
			return new Integer(0);
		return refreshpagemonitor;
	}
	public void setRefreshpagemonitor(Integer refreshpagemonitor) {
		this.refreshpagemonitor = refreshpagemonitor;
	}
	public Integer getMinutetodefinecritic() {
		if (this.minutetodefinecritic==null)
			return new Integer(0);
		return minutetodefinecritic;
	}
	public void setMinutetodefinecritic(Integer minutetodefinecritic) {
		this.minutetodefinecritic = minutetodefinecritic;
	}
	 
	 
}
