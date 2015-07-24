import java.io.Serializable;

import com.mongodb.BasicDBObject;


public class Actor extends BasicDBObject {
	int id;
	String name;
	String description;
	String dateBirth;
	
	public Actor(int id, String name, String description, String dateBirth) {
		setDateBirth(dateBirth);
		setDescription(description);
		setId(id);
		setName(name);
	}

	@Override
	public String toString() {
		return "Actor [id=" + id + ", name=" + name + ", description="
				+ description + ", dateBirth=" + dateBirth + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(String dateBirth) {
		this.dateBirth = dateBirth;
	}
	
	
	
}
