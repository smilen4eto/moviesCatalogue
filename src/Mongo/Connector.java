package Mongo;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.catalina.tribes.membership.StaticMember;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


public class Connector {
//	static List<Integer> actors = new ArrayList<>();
	public static MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	public static DBCollection moviesColl = mongoClient.getDB("moviesDB").getCollection("movies");
	public static DBCollection actorsColl = mongoClient.getDB("moviesDB").getCollection("actors");
//	public static void checkForConnection(){
//		System.out.println(mongoClient);
//		if (mongoClient.equals(null)){
//			System.out.println("Mongo e null");
////			connectionOpen();
//		} else {
//			System.out.println("Sq ko praim");
//		}
//	}
		
//	public static void connectionOpen(){
//		System.out.println("connection open");
//		mongoClient = new MongoClient( "localhost" , 27017 );
//
////		indexMovAndAct(db);
//
//	}
	
	public static void closeConnection(){
		mongoClient.close();
	}
	

//	public static void insertObjectInDB(BasicDBObject obj, String collection){
//		DBCollection coll = mongoClient.getDB("moviesDB").getCollection(collection);
//				coll.insert(obj);
//			Connector.closeConnection();
//	}
		
//	Task 2 - Index movies and actors fields
	public static void indexMovAndAct(DB db){
		db.getCollection("movies").createIndex("year");
		db.getCollection("actors").createIndex("birthdate");
	}
	}

