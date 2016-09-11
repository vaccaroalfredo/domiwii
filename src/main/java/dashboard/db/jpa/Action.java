package dashboard.db.jpa;

import java.io.Serializable;
import java.util.Date;
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

/**
 * @author Rinaldo
 *
 */
@Entity(name="Action")
@Access(AccessType.FIELD)
@Table(
		 uniqueConstraints = {@UniqueConstraint(columnNames = {"idAction"})}
)

public class Action implements Serializable {
	
	private String name; 
	private String shortDescription;
	private State state;
	private String command;
	
	
	
	//@OneToMany(mappedBy="action")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "deviceActionId.action", cascade = CascadeType.ALL)
	private List<Scheduler> scheduler; //= new LinkedList<Scheduler>();

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq_gen")
	@SequenceGenerator(name="seq_gen", sequenceName="action_seq",allocationSize = 1,initialValue=1)	
	@Column(name = "idAction", unique = true, nullable = false)
	private Long id;
	
	public Action(String name, String description, String command, State state) {
		
		super();
		this.setName(name);
		this.setShortDescription(description);
		this.setState(state);
		this.command = command;
		
	}
//	public Action(String name, String description, State state, List<Scheduler> scheduler ) {
//		
//		super();
//		this.setName(name);
//		this.setShortDescription(description);
//		this.setState(state);
//		this.scheduler=scheduler;
//	}
//	
	public Action(){
		super();		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	
	public List<Scheduler> getScheduler() {
		return scheduler;
	}

	public void setScheduler(List<Scheduler> scheduler) {
		this.scheduler = scheduler;
	}
	
	public String getCommand() {
		return command;
	}
	
	public void setCommand(String command) {
		this.command = command;
	}
	
	
	

}
