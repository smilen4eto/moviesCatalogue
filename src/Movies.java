import java.io.Serializable;
import java.util.HashMap;

import org.bson.BSONObject;

import com.mongodb.BasicDBObject;

public class Movies extends BasicDBObject{
	int idMongo;
	String name;
	int year;
	Actor actorsCollection;
	
	public Movies(int idMongo, String name, int year, Actor actor) {
		setIdMongo(idMongo);
		setName(name);
		setYear(year);
		actorsCollection = actor;
	}

	public int getIdMongo() {
		return idMongo;
	}

	public void setIdMongo(int idMongo) {
		this.idMongo = idMongo;
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

//	public void insertActor(Actor actor){
//		actorsCollection.put(actor.getId(), actor);
//	}

	@Override
	public String toString() {
		return "Movies [idMongo=" + idMongo + ", name=" + name + ", year="
				+ year + ", actorsCollection=" + actorsCollection + "]";
	}
	
	
}
