
public class Movie {
	String name;
	int year;
	
	public Movie(String name, int year) {
		setName(name);
		setYear(year);
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
