package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Movie {
	int id;
	String name;
	int year;
	public List<Actor> listOfActors;

	
	public Movie(String name, int year) {
		listOfActors = new ArrayList<Actor>();
		setId(0);
		setName(name);
		setYear(year);
		
		
	}
	
	public void addActorInMovie(Actor actor){
		listOfActors.add(actor);
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

