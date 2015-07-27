import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;

public class Test {
	public static void main(String[] args) {
		Movie movie1 = new Movie("The Terminator", 1984);
		Movie movie2 = new Movie("Deadpool", 2016);
		Movie movie3 = new Movie("X-men", 2000);
		Movie movie4 = new Movie("blaaah", 1993);
		
		
		String act1description = "With an almost unpronounceable surname and a thick Austrian accent, who would have ever believed that a brash, quick talking bodybuilder from a small European village would become one of Hollywood's biggest stars, marry into the prestigious Kennedy family, amass a fortune via shrewd investments and one day be the Governor of California!? ";
		Actor act1 = new Actor("Arnold Schwarzenegger", act1description, new Date(30/06/1947));
		

		String act2description = "Linda Carroll Hamilton was born on September 26, 1956 in Salisbury, Maryland, to Barbara K. (Holt) and Carroll Stanford Hamilton, a physician. Following Wicomico High School, she studied for two years at Washington College in Chestertown, Maryland, before moving on to acting studies in New York.";
		Actor act2 = new Actor("Linda Hamilton", act2description, new Date(26/9/1956));
		
		String act3description = "Hugh Jackman was born in Sydney, New South Wales, to Grace McNeil (Greenwood) and Christopher John Jackman, an accountant.";
		Actor act3 = new Actor("Hugh Jackman", act3description, new Date(12/10/1968));
		
		String act4description = "Ryan Rodney Reynolds was born on October 23, 1976 in Vancouver, British Columbia, Canada, the youngest of four children. His father, Jim, was a food wholesaler and his mother, Tammy, was a retail-store saleswoman.";
		Actor act4 = new Actor("Ryan Reynolds", act4description, new Date(23/10/1976));
		
		
		DB db = Connector.connectionOpen();
		Connector.connectToDB(Connector.insertActors(act1, 2), "actors", db);
		Connector.connectToDB(Connector.insertActors(act2, 1), "actors", db);
		Connector.connectToDB(Connector.insertQueryMovie(movie1, 2), "movies", db);
		Connector.deleteAllActorsFromList();
		Connector.connectToDB(Connector.insertActors(act3, 4), "actors", db);
		Connector.connectToDB(Connector.insertQueryMovie(movie3, 1), "movies", db);
		Connector.deleteAllActorsFromList();
		Connector.connectToDB(Connector.insertActors(act4, 3), "actors", db);
		Connector.connectToDB(Connector.insertQueryMovie(movie2, 3), "movies", db);
		
		
// ---		Connector.connectToDB(Connector.insertActors(act4, 5), "actors", db);
// ---		Connector.connectToDB(Connector.insertQueryMovie(movie3, 4), "movies", db);
		
		db.getCollection("movies").createIndex("year");
		db.getCollection("actors").createIndex("birthdate");
		
		Set<Integer> res = new HashSet();
		DBCursor cursor = db.getCollection("movies").find().limit(2);		
		for (DBObject result : cursor) {
			res.addAll((ArrayList<Integer>)(result.get("actors")));
			}
		
	for (Integer integer : res) {
		System.out.println(integer);
		//System.out.println((String)(db.getCollection("actors").findOne(new BasicDBObject("_id", integer)).get("name")));
	}

//		Connector.returnFourOldestMovies(db);
//		System.out.println((String) cursor.one().get("name"));
		
	}
}
