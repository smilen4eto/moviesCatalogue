import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		
	}

