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
	
	public static void insertActors(Actor actor, int id){
		BasicDBObject doc = new BasicDBObject("_id", id)
				.append("name", actor.getName())
				.append("description", actor.getDescription())
				.append("birthdate", actor.getDateBirth());
		Connector.actorsColl.insert(doc);
	}
	
	public static DBObject findActorByID(int id){
		return Connector.actorsColl.findOne(new BasicDBObject("_id", id));
	}
	
	
	
	public static ArrayList<Actor> returnActorsFromThreeMovies() {
		ArrayList<Actor> actors = new ArrayList<Actor>();
		Set<Integer> setOfActors = new HashSet();
		DBCursor cursor = Connector.moviesColl.find();
		//		DBCursor cursor = Connector.actorsColl.find().limit(3);
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
//		Connector.closeConnection();
		return actors;
	}
	
	
	public static HashMap<Integer,Actor> printThreeActorsMovies(){
		HashMap<Integer,Actor> actors = new HashMap<Integer,Actor>();
		DBCursor cursor = Connector.actorsColl.find().limit(3);
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
//		Connector.closeConnection();
		return actors;
	}
	
	public static void removeAnActor(int actorId){
		Connector.actorsColl.remove(new BasicDBObject("_id", actorId));
		DBCursor moviesCursor = Connector.moviesColl.getCollection("movies").find();
		for (DBObject result : moviesCursor) {
			ArrayList<Integer> arrayList = (ArrayList<Integer>) result
					.get("actors");
				if (arrayList.contains(actorId)) {
					arrayList.remove((Integer)actorId);
					CRUDMovies.updateMovies((Integer)result.get("_id"), arrayList);
				}
			}
//		Connector.closeConnection();
	}
	
	public static void addNewActorInFourMovies(Actor actor){
		CRUDActors.insertActors(actor, actor.getId());
		DBCursor moviesCursor = Connector.moviesColl.find().limit(4);
		for (DBObject result : moviesCursor) {
			ArrayList<Integer> arrayList = (ArrayList<Integer>) result.get("actors");
					arrayList.add(actor.getId());
					CRUDMovies.updateMovies((Integer)result.get("_id"), arrayList);
		}
//		Connector.closeConnection();
	}
	
	public static void updateActorID(int oldID, int newID){
		DBObject actor = Connector.actorsColl.findOne(new BasicDBObject("_id", oldID));
		Actor actorNew = new Actor(newID, (String)actor.get("name"), (String)actor.get("description"), (Date)actor.get("birthdate"));
		Connector.actorsColl.remove(new BasicDBObject("_id", oldID));
		CRUDActors.insertActors(actorNew, actorNew.getId());
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
//		Connector.closeConnection();
	}
}
