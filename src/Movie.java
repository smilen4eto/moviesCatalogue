
public class Movie {
	int id;
	String name;
	int year;
	
	public Movie(int id,String name, int year) {
		setId(id);
		setName(name);
		setYear(year);
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}


	@Override
	public String toString() {
		return "Movies [name=" + name + ", year="
				+ year +  "]";
	}
}

