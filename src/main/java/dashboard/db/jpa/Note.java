package dashboard.db.jpa;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dashboard.db.ASDataFormat;
@Entity(name="Note")
@Table( indexes = {
        @Index(name = "highlightsIndex",  columnList="highlights") ,
        @Index(name="creationdateIndex", columnList="creationdate"),
        @Index(name="archiveIndex", columnList="archive")
    }
)
@Access(AccessType.FIELD)
public class Note implements Serializable{
	
	//@GeneratedValue(strategy = GenerationType.AUTO)// or GenerationType.SEQUENCE( da preferire perchè portabile) - alternative IDENTITY
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="note_seq_gen")
	@SequenceGenerator(name="note_seq_gen", sequenceName="note_seq",allocationSize = 1,initialValue=1)
	private Long id;
	@Column(nullable=false)
	private Date creationdate;
	@Column(length=1000)
	private String description;
	private String owner; // task's owner
	private String activity; // related activity
	private Integer highlights; // =1  note added to higlights, =0 note archived
	private Boolean archive;
	
	
	
	

	public Note() {
		super();
		
	}


	public Note(Long id, Date creationdate, String owner, String activity,Integer highlights, boolean archive){
		super();
		this.id = id;
		this.creationdate =creationdate;
		this.owner = owner;
		this.activity  = activity;
		this.highlights = highlights;
		this.archive = archive;
		
	}

	
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


	

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getOwner() {
		return owner;
	}


	public void setOwner(String owner) {
		this.owner = owner;
	}


	public String getActivity() {
		return activity;
	}


	public void setActivity(String activity) {
		this.activity = activity;
	}


	public Integer getHighlights() {
		return highlights;
	}


	public void setHighlights(Integer highlights) {
		this.highlights = highlights;
	}
	
	public boolean isHighlights(){
		return this.highlights==null ? false: this.highlights>0;
	}


	public Boolean getArchive() {
		return archive;
	}


	public void setArchive(Boolean archive) {
		this.archive = archive;
	}
	
	public void validation(){
		if (this.creationdate==null && this.id==null){
			this.setCreationdate(new Date());
		}	
	}


	public void logInfo(){
		System.out.println("Id :"+this.id);
		System.out.println("Data creazione :"+ASDataFormat.dateToString(this.creationdate,"dd-MM-yyyy HH:mm:ss"));
		System.out.println("Descrizione :"+this.description);
		System.out.println("Owner :"+this.owner);
		System.out.println("Highlight :"+this.highlights);
		
		
	}

	
}
