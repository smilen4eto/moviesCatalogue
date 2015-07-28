package Mongo;
import Test.Test;
import Utils.ListOfActors;
import Model.*;

import java.util.ArrayList;
import java.util.List;

import Model.Movie;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


public class CRUDMovies {
	public static BasicDBObject insertQueryMovie(Movie movie,int id){
		BasicDBObject doc = new BasicDBObject("_id", id)
				.append("name", movie.getName())
		        .append("year", movie.getYear())
		        .append("actors", ListOfActors.actors);
		return doc;	
	}
	
	public static ArrayList<Movie> returnFourOldestMovies(DB db){
		ArrayList<Movie> movies = new ArrayList<Movie>();
		DBCursor cursor = db.getCollection("movies").find().limit(4).sort(new BasicDBObject("year", 1));
		for (DBObject result : cursor) {
			Movie movie = new Movie((String) result.get("name"), (Integer) result.get("year"));
			movies.add(movie);
			//System.out.println((String) result.get("name") + " " + (Integer) result.get("year"));
		}
		return movies;
	}
	
	static void updateMovies(DB db, int searchedID, ArrayList<Integer> arrayList) {
		db.getCollection("movies").update(
				new BasicDBObject("_id", searchedID),
				new BasicDBObject("$set",
						new BasicDBObject("actors", arrayList)));
	}
	
}
