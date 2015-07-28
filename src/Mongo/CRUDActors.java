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
	
	public static BasicDBObject insertActors(Actor actor, int id){
		BasicDBObject doc = new BasicDBObject("_id", id)
				.append("name", actor.getName())
				.append("description", actor.getDescription())
				.append("birthdate", actor.getDateBirth());
		return doc;
	}
	
	public static DBObject findActorByID(int id, DB db){
		return db.getCollection("actors").findOne(new BasicDBObject("_id", id));
	}
	
	
	
	public static ArrayList<Actor> returnActorsFromThreeMovies(DB db) {
		ArrayList<Actor> actors = new ArrayList<Actor>();
		Set<Integer> setOfActors = new HashSet();
		DBCursor cursor = db.getCollection("movies").find().limit(3);
		for (DBObject result : cursor) {
			setOfActors.addAll((ArrayList<Integer>) (result.get("actors")));
		}
		DBCursor resultCursor = db.getCollection("actors").find()
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
	
	
	public static HashMap<Integer,Actor> printThreeActorsMovies(DB db){
		HashMap<Integer,Actor> actors = new HashMap<Integer,Actor>();
		DBCursor cursor = db.getCollection("actors").find().limit(3);
		for (DBObject result : cursor) {
			Actor actor = new Actor((Integer)result.get("_id"), (String)result.get("name"), (String)result.get("description"), (Date)result.get("birthdate"));
			actors.put(actor.getId(), actor);
		}
		DBCursor moviesCursor = db.getCollection("movies").find();
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
	
	public static void removeAnActor(DB db, int actorId){
		db.getCollection("actors").remove(new BasicDBObject("_id", actorId));
		DBCursor moviesCursor = db.getCollection("movies").find();
		for (DBObject result : moviesCursor) {
			ArrayList<Integer> arrayList = (ArrayList<Integer>) result
					.get("actors");
				if (arrayList.contains(actorId)) {
					arrayList.remove((Integer)actorId);
					CRUDMovies.updateMovies(db, (Integer)result.get("_id"), arrayList);
				}
			}
	}
	
	public static void addNewActorInFourMovies(DB db, Actor actor){
		Connector.connectToDB(CRUDActors.insertActors(actor, actor.getId()), "actors", db);
		DBCursor moviesCursor = db.getCollection("movies").find().limit(4);
		for (DBObject result : moviesCursor) {
			ArrayList<Integer> arrayList = (ArrayList<Integer>) result.get("actors");
					arrayList.add(actor.getId());
					CRUDMovies.updateMovies(db, (Integer)result.get("_id"), arrayList);
		}
	}
	
	public static void updateActorID(DB db,int oldID, int newID){
		DBObject actor = db.getCollection("actors").findOne(new BasicDBObject("_id", oldID));
		Actor actorNew = new Actor(newID, (String)actor.get("name"), (String)actor.get("description"), (Date)actor.get("birthdate"));
		db.getCollection("actors").remove(new BasicDBObject("_id", oldID));
		Connector.connectToDB(CRUDActors.insertActors(actorNew, actorNew.getId()), "actors", db);
		DBCursor moviesCursor = db.getCollection("movies").find();
		for (DBObject result : moviesCursor) {
			ArrayList<Integer> arrayList = (ArrayList<Integer>) result
					.get("actors");
				if (arrayList.contains(oldID)) {
					arrayList.remove((Integer)oldID);
					arrayList.add(newID);
					CRUDMovies.updateMovies(db, (Integer)result.get("_id"), arrayList);
				}
			}
	}
}
