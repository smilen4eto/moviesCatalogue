import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


public class CRUDMovies {
	static BasicDBObject insertQueryMovie(Movie movie,int id){
		BasicDBObject doc = new BasicDBObject("_id", id)
				.append("name", movie.getName())
		        .append("year", movie.getYear())
		        .append("actors", ListOfActors.actors);
		return doc;	
	}
	
	static void returnFourOldestMovies(DB db){
		DBCursor cursor = db.getCollection("movies").find().limit(4).sort(new BasicDBObject("year", 1));
		for (DBObject result : cursor) {
			System.out.println((String) result.get("name") + " " + (Integer) result.get("year"));
		}
	}
	
	static void updateMovies(DB db, int searchedID, ArrayList<Integer> arrayList) {
		db.getCollection("movies").update(
				new BasicDBObject("_id", searchedID),
				new BasicDBObject("$set",
						new BasicDBObject("actors", arrayList)));
	}
	
}
