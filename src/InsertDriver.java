
import java.io.Serializable;

import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;


public class InsertDriver extends BasicDBObject implements Serializable{
	public static void main(String[] args) {
		String act1description = "With an almost unpronounceable surname and a thick Austrian accent, who would have ever believed that a brash, quick talking bodybuilder from a small European village would become one of Hollywood's biggest stars, marry into the prestigious Kennedy family, amass a fortune via shrewd investments and one day be the Governor of California!? ";
		Actor act1 = new Actor(0, "Arnold Schwarzenegger", act1description, "30.July.1947");
		Movies movie1 = new Movies(0, "The Terminator", 1984, act1);
		movie1.actorsCollection = act1;
		
		String[] nameStrings = {"name", "year"};
		JSONObject jobj = new JSONObject(movie1, nameStrings);
		DBObject dbObject = (DBObject)JSON.parse(jobj.toString());
//		 
//		collection.insert(dbObject);
		
		DB db = (new MongoClient("localhost", 27017)).getDB("moviesCatalogue");
		DBCollection movies = db.getCollection("movies");
		
//		BasicDBObject moviesDBobject = new BasicDBObject();
//			moviesDBobject.put("name", movie1.getName());
//	        moviesDBobject.put("year", "1984");
//		
//		BasicDBObject actorDBobject = new BasicDBObject();
//				actorDBobject.put("name", act1.getName());
//				actorDBobject.put("description", act1description);
//				actorDBobject.put("dateBirth", act1.dateBirth);
//		
//		moviesDBobject.put("actorsCollection", actorDBobject);
//		movies.insert(moviesDBobject);
		
		
		movies.insert(dbObject);
		
//		QueryDriver.findMovie(new BasicDBObject(0), movies);

	}
}
