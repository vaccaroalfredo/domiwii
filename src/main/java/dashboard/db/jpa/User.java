package dashboard.db.jpa;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import dashboard.db.ASDataFormat;

@Entity(name="Users")
@Access(AccessType.FIELD)
@Table(
		 uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})}
)
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public enum ProfileAccess {
	      Administration(0), Operational(1);
	      private int value;
	      ProfileAccess(int value) { this.value = value; }
	      public int getValue() { return value; }
	};

	 @Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="user_seq_gen")
	 @SequenceGenerator(name="user_seq_gen", sequenceName="user_seq",allocationSize = 1,initialValue=1)
	 private Long id;
	 @Column(name="nameandsurname")
	 private String user;
	 private String username;
	 private String password;
	 @Enumerated(EnumType.ORDINAL)
	 private ProfileAccess profile;
	 
	 
	 public User(Long id, String username, String password, ProfileAccess profile) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
			this.profile = profile;
	 }
	 
	 public User( String username, String password, ProfileAccess profile) {
			super();
			this.id = null;
			this.username = username;
			this.password = password;
			this.profile = profile;
	 }


	 public User() {
		super();
	 }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public ProfileAccess getProfile() {
		return profile;
	}


	public void setProfile(ProfileAccess profile) {
		this.profile = profile;
	}

	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (profile != other.profile)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


	public void logInfo(){
		System.out.println("Id :"+this.id);
		System.out.println("Username :"+this.username);
		System.out.println("Person :"+this.user);
		
		
	} 
	 
	 
	 
	 
}
