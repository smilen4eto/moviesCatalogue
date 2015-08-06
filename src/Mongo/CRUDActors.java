package Mongo;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import Model.Actor;
import Model.Movie;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


public class CRUDActors {
	
	public static int insertActors(Actor actor){
		int count = (int)Connector.actorsColl.count();
		int id = 100 - count;
		BasicDBObject doc = new BasicDBObject("_id", id)
				.append("name", actor.getName())
				.append("description", actor.getDescription())
				.append("birthdate", actor.getDateBirth());
		Connector.actorsColl.insert(doc);
		return id;
	}
	
	public static DBObject findActorByID(int id){
		return Connector.actorsColl.findOne(new BasicDBObject("_id", id));
	}
	
	public static Actor findActorByName(String name){
		try {
			DBObject act = Connector.actorsColl.findOne(new BasicDBObject("name", name));
			Actor actor = new Actor((int)act.get("_id"), (String)act.get("name"), (String)act.get("description"), (Date)act.get("birthdate"));
			return actor;
		} catch (Exception e) {
			Actor actor = new Actor(0, null, null, null);
			return actor;
		}
		
	}
	
	
	public static ArrayList<Actor> returnActorsFromThreeMovies() {
		ArrayList<Actor> actors = new ArrayList<Actor>();
		Set<Integer> setOfActors = new HashSet();
		DBCursor cursor = Connector.moviesColl.find();
		for (DBObject result : cursor) {
			setOfActors.addAll((ArrayList<Integer>) (result.get("actors")));
		}
		DBCursor resultCursor = Connector.actorsColl.find()
				.sort(new BasicDBObject("birthdate", 1));
		for (DBObject result : resultCursor) {
			if (setOfActors.contains((Integer) result.get("_id"))) {
				Actor actor = new Actor((Integer) result.get("_id"),
						(String) result.get("name"),
						(String) result.get("description"),
						(Date) result.get("birthdate"));
				if (!(actors.contains(actor))) {
					actors.add(actor);
				}
			}
		}
		return actors;
	}
	
	public static ArrayList<Actor> returnActor(){
		ArrayList<Actor> actors = new ArrayList<Actor>();
		DBCursor resultCursor = Connector.actorsColl.find()
				.sort(new BasicDBObject("birthdate", 1));
		for (DBObject result : resultCursor) {
			Actor actor = new Actor((Integer) result.get("_id"),
					(String) result.get("name"),
					(String) result.get("description"),
					(Date) result.get("birthdate"));
			actors.add(actor);
		}
		
		return actors;
		
	}
	
	public static HashMap<Integer,Actor> printActorsMovies(int count){
		HashMap<Integer,Actor> actors = new HashMap<Integer,Actor>();
		DBCursor cursor = Connector.actorsColl.find().limit(count);
		for (DBObject result : cursor) {
			Actor actor = new Actor((Integer)result.get("_id"), (String)result.get("name"), (String)result.get("description"), (Date)result.get("birthdate"));
			actors.put(actor.getId(), actor);
		}
		DBCursor moviesCursor = Connector.moviesColl.find();
		for (DBObject result : moviesCursor) {
			ArrayList<Integer> arrayList = (ArrayList<Integer>) result.get("actors");
			for (Integer integer : arrayList) {
				if(actors.containsKey(integer)){
					Movie movie = new Movie((String)result.get("name"), (int)result.get("year"));
					actors.get(integer).addMovie(movie);
				}
			}
		}
		return actors;
	}
	
	public static Actor printActorMovieByID(int id){
		try {
			DBObject result = findActorByID(id);
			ArrayList<Integer> arrayList = new ArrayList<Integer>();
			Actor actor = new Actor((Integer)result.get("_id"), (String)result.get("name"), (String)result.get("description"), (Date)result.get("birthdate"));
			DBCursor moviesCursor = Connector.moviesColl.find();
			for (DBObject res : moviesCursor) {
				arrayList = (ArrayList<Integer>) res.get("actors");
				for (Integer integer : arrayList) {
					if(integer==actor.getId()){
						Movie movie = new Movie((String)res.get("name"), (int)res.get("year"));
						actor.addMovie(movie);
					}
				}
			}
			return actor;
		} catch (Exception e) {
			Actor actor = new Actor();
			return actor;
		}
	}
	
	public static void removeAnActor(int actorId){
		Connector.actorsColl.remove(new BasicDBObject("_id", actorId));
		DBCursor moviesCursor = Connector.moviesColl.getCollection("movies").find();
		for (DBObject result : moviesCursor) {
			ArrayList<Integer> arrayList = (ArrayList<Integer>) result
					.get("actors");
			System.out.println(arrayList);
				if (arrayList.contains(actorId)) {
					System.out.println(actorId);
					arrayList.remove(actorId);
					CRUDMovies.updateMovies((Integer)result.get("_id"), arrayList);
				}
			}
	}
	 
	public static void addNewActorInFourMovies(String actorName, Movie movie){
		System.out.println(movie.toString() + movie.getId());
//		CRUDActors.insertActors(actor);
		Actor actor = findActorByName(movie.actorName);
		DBCursor moviesCursor = Connector.moviesColl.find(new BasicDBObject("_id", movie.getId()));
		for (DBObject result : moviesCursor) {
			ArrayList<Integer> arrayList = (ArrayList<Integer>) result.get("actors");
			if(arrayList.contains(actor.getId())){
				continue;
			} else {
					arrayList.add(actor.getId());
					CRUDMovies.updateMovies((Integer)result.get("_id"), arrayList);
		}
		}
	}
	
	public static void updateActorID(int oldID, int newID){
		DBObject actor = Connector.actorsColl.findOne(new BasicDBObject("_id", oldID));
		Actor actorNew = new Actor(newID, (String)actor.get("name"), (String)actor.get("description"), (Date)actor.get("birthdate"));
		Connector.actorsColl.remove(new BasicDBObject("_id", oldID));
		CRUDActors.insertActors(actorNew);
		DBCursor moviesCursor = Connector.moviesColl.find();
		for (DBObject result : moviesCursor) {
			ArrayList<Integer> arrayList = (ArrayList<Integer>) result
					.get("actors");
				if (arrayList.contains(oldID)) {
					arrayList.remove((Integer)oldID);
					arrayList.add(newID);
					CRUDMovies.updateMovies((Integer)result.get("_id"), arrayList);
				}
			}
	}
}
