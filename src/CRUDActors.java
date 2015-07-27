import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


public class CRUDActors {
	
	static BasicDBObject insertActors(Actor actor, int id){
		BasicDBObject doc = new BasicDBObject("_id", id)
				.append("name", actor.getName())
				.append("description", actor.getDescription())
				.append("birthdate", actor.getDateBirth());
		return doc;
	}
	
	static void returnActorsFromThreeMovies(DB db){
		Set<Integer> setOfActors = new HashSet();
		DBCursor cursor = db.getCollection("movies").find().limit(3);
		for (DBObject result : cursor) {
			setOfActors.addAll((ArrayList<Integer>)(result.get("actors")));
		}
		DBCursor resultCursor = db.getCollection("actors").find()
				.sort(new BasicDBObject("birthdate", 1));
		for (DBObject result : resultCursor) {
			if (setOfActors.contains((Integer) result.get("_id"))) {
				System.out.println((String) result.get("name") + " - "
						+ (String) result.get("description"));
			}
		}
	}
	
	
	static void printThreeActorsMovies(DB db){
		DBCursor cursor = db.getCollection("actors").find().limit(3);
		ArrayList<Integer> setOfActorsIDs = new ArrayList<Integer>();
		for (DBObject result : cursor) {
			setOfActorsIDs.add((Integer) (result.get("_id")));
		}
		DBCursor moviesCursor = db.getCollection("movies").find();
		for (DBObject result : moviesCursor) {
			ArrayList<Integer> arrayList = (ArrayList<Integer>) result
					.get("actors");
			for (int i = 0; i < setOfActorsIDs.size(); i++) {
				if (arrayList.contains(setOfActorsIDs.get(i))) {
					System.out.println(db.getCollection("actors").findOne(
							new BasicDBObject("_id", setOfActorsIDs.get(i))));
					System.out.println((String) result.get("name") + " "
							+ result.get("year"));

				}
			}
		}
	}
	
	static void removeAnActor(DB db, int actorId){
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
	
	static void addNewActorInFourMovies(DB db, Actor actor){
		Connector.connectToDB(CRUDActors.insertActors(actor, actor.getId()), "actors", db);
		DBCursor moviesCursor = db.getCollection("movies").find().limit(4);
		for (DBObject result : moviesCursor) {
			ArrayList<Integer> arrayList = (ArrayList<Integer>) result
					.get("actors");
					arrayList.add(actor.getId());
					CRUDMovies.updateMovies(db, (Integer)result.get("_id"), arrayList);
		}
	}
	
	static void updateActorID(DB db,int oldID, int newID){
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
