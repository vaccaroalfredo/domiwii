package dashboard.db.jpa;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;*/

import dashboard.db.ASDataFormat;



@Entity 
@Table( indexes = {
        @Index(name = "statusIndex",  columnList="status") ,
        @Index(name="startdateIndex", columnList="startdate")
    }
)
@Access(AccessType.FIELD)
public class Activity implements Serializable {
	public enum Status {
	      New(0), InProgress(1),Closed(2);
	      private int value;
	      Status(int value) { this.value = value; }
	      public int getValue() { return value; }
	      @Override
	      public String toString(){
	    	  switch (this.value) {
	    	  	case 0:return "Nuovo";
	    	  	case 1:return "In lavorazione";
	    	  	case 2:return "Chiuso";
				default:
					break;
			}
			return "";
	      }
	}
	
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="activity_seq_gen")
	 @SequenceGenerator(name="activity_seq_gen", sequenceName="activity_seq",allocationSize = 1,initialValue=1)
	 private Long id;
	 @Column(nullable=false)
	 private Date creationdate;
	 @Column(nullable=false)
	 private Date startdate;
	 @Column(nullable=false)
	 private Date enddate;
	 private String owner;
	 //@Field
	 @Column(length=1000,nullable=false)
	 private String description;
	 @Enumerated(EnumType.ORDINAL)
	 private Status status;
	 private Boolean result;
	 @Column(length=1000)
	 private String faildescription;
	
	 public Activity(Long id, Date creationdate, Date startdate, Date enddate, String owner, String description,
			Status status, Boolean result, String faildescription) {
		super();
		this.id = id;
		this.creationdate = creationdate;
		this.startdate = startdate;
		this.enddate = enddate;
		this.owner = owner;
		this.description = description;
		this.status = status;
		this.result = result;
		this.faildescription = faildescription;
	}

	public Activity() {
		super();
		
	}
	
	/*@PrePersist
	 * Now it seems that those interpreters are not been executed, with little search i found out that it's suitable using entityManager.
	 * Non questo caso dove usiamo session
	public void prePersist() {
	    if(this.id == null) 
	    	this.creationdate= new Date();
	}*/

//	@PrePersist
//    protected void onCreate() {
//		this.creationdate= new Date();
//    }
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;	
		
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
		//this.validation();
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String getFaildescription() {
		return faildescription;
	}

	public void setFaildescription(String faildescription) {
		this.faildescription = faildescription;
	}
	
	public void validation(){
		if (this.creationdate==null && this.id==null){
			this.setCreationdate(new Date());
		}
		if (this.getStatus()!=Activity.Status.Closed){
			this.setResult(null);
			this.setFaildescription(null);
		}
		
	}
	 
	public void logInfo(){
		System.out.println("Id :"+this.id);
		System.out.println("Data creazione :"+ASDataFormat.dateToString(this.creationdate,"dd-MM-yyyy HH:mm:ss"));
		System.out.println("Description :"+this.description);
		System.out.println("Data inizio :"+ASDataFormat.dateToString(this.startdate,"dd-MM-yyyy HH:mm:ss"));
		System.out.println("Data fine :"+ASDataFormat.dateToString(this.enddate,"dd-MM-yyyy HH:mm:ss"));
		System.out.println("Owner :"+this.owner);
		System.out.println("Status :"+this.status);
		System.out.println("Result :"+this.result);
		System.out.println("Fail description :"+this.faildescription);
		
		
	}
	 

}
