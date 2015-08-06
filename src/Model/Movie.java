package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Movie {
	int id;
	String name;
	int year;
	public ArrayList<Actor> listOfActors;
	public String actorName;
	
	public Movie(){
		listOfActors = new ArrayList<Actor>();
		setId(0);
		setName(null);
		setYear(0);
	}

	public Movie(int id,String name, int year, String actorName){
		setId(id);
		setName(name);
		setYear(year);
		this.actorName = actorName;
	}
	
	public Movie(String name, int year) {
		listOfActors = new ArrayList<Actor>();
		setId(0);
		setName(name);
		setYear(year);

	}

	
	public Movie(int id,String name, int year){
		listOfActors = new ArrayList<Actor>();
		setId(id);
		setName(name);
		setYear(year);
	}
	
	public void addActorInMovie(Actor actor){
		listOfActors.add(actor);
	}

	public ArrayList<Integer> returnArrayListWithActorIDs(){
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		for (Actor act : listOfActors) {
			arrayList.add(act.getId());
		}
		return arrayList;
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

