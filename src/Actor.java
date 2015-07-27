import java.util.Date;

public class Actor {
	String name;
	String description;
	Date dateBirth;
	
	public Actor(String name, String description, Date dateBirth) {
		setDateBirth(dateBirth);
		setDescription(description);
		setName(name);
	}

	@Override
	public String toString() {
		return "Actor [name=" + name + ", description="
				+ description + ", dateBirth=" + dateBirth + "]";
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}
	
	
}
