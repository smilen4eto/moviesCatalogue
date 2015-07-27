import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class Connector {
	static List<Integer> actors = new ArrayList<>();
	
	static DB connectionOpen(){
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		DB db = mongoClient.getDB( "moviesDB" );
		return db;
	}
	
	static void connectToDB(BasicDBObject doc, String collection, DB db){
		DBCollection coll = db.getCollection(collection);
		coll.insert(doc);
	}
	
	static BasicDBObject insertQueryMovie(Movie movie,int id){
		BasicDBObject doc = new BasicDBObject("_id", id)
				.append("name", movie.getName())
		        .append("year", movie.getYear())
		        .append("actors", Connector.actors);
		return doc;
		
	}

	
	static void insertActorInList(int id){
		actors.add(id);
	}
	
	static void deleteAllActorsFromList(){
		actors.clear();
	}

	static BasicDBObject insertActors(Actor actor, int id){
		BasicDBObject doc = new BasicDBObject("_id", id)
				.append("name", actor.getName())
				.append("description", actor.getDescription())
				.append("birthdate", actor.getDateBirth());
		insertActorInList(id);
		return doc;
	}
	
	static void returnFourOldestMovies(DB db){
		DBCursor cursor = db.getCollection("movies").find().limit(4).sort(new BasicDBObject("year", 1));
		for (DBObject result : cursor) {
			System.out.println((String) result.get("name") + " " + (Integer) result.get("year"));
		}
	}
}
