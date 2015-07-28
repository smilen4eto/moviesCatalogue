package Model;
import java.util.ArrayList;
import java.util.Date;


public class Actor {
	int id;
	String name;
	String description;
	Date dateBirth;
	ArrayList<Movie> listOfMovies;
	
	public Actor(int id, String name, String description, Date dateBirth) {
		listOfMovies = new ArrayList<Movie>();
		setId(id);
		setDateBirth(dateBirth);
		setDescription(description);
		setName(name);
	}

	@Override
	public String toString() {
		return "Actor [name=" + name + ", description="
				+ description + ", dateBirth=" + dateBirth +" id = " + id + "]";
	}
	
	public void addMovie(Movie movie){
		listOfMovies.add(movie);
	}
	
	public void showAllMoviesForActor(){
		System.out.println(getName());
		for (Movie movie : listOfMovies) {
			System.out.println(movie.toString());
		}
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

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}
	
	
}