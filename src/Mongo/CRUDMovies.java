package Mongo;

import Utils.ListOfActors;
import Model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.NewCookie;

import Model.Movie;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


public class CRUDMovies {
	public static void insertQueryMovie(Movie movie,int id){
		BasicDBObject doc = new BasicDBObject("_id", id)
				.append("name", movie.getName())
		        .append("year", movie.getYear())
		        .append("actors", ListOfActors.actors);
		Connector.moviesColl.insert(doc);
	}
	
	public static int countOfMovies(){
//		Connector.checkForConnection();
		int count = 0;
		count = (int)Connector.moviesColl.count();
//		Connector.closeConnection();
		return count;
	}
	
	public static DBCursor queryToSearchMoviesBy(String sort, int count){
//		Connector.checkForConnection();
		if (sort.contains("year")) {
			return Connector.moviesColl.find().limit(count).sort(new BasicDBObject("year", 1));
		} else if(sort.contains("name")){
			return Connector.moviesColl.find().limit(count).sort(new BasicDBObject("name", 1));
		} else if(sort.contains("_id")){
			return Connector.moviesColl.find().limit(count).sort(new BasicDBObject("_id", 1));
		} 
		else return Connector.moviesColl.find().limit(count).sort(new BasicDBObject("_id", 1));
	}
	
	
	public static ArrayList<Movie> returnMovies(int count, String sort){
//		Connector.checkForConnection();
		ArrayList<Movie> movies = new ArrayList<Movie>();
		DBCursor cursor = queryToSearchMoviesBy(sort, count);
		for (DBObject resultMov : cursor) {
			Movie movie = new Movie((String) resultMov.get("name"), (Integer) resultMov.get("year"));
			movie.setId((Integer)resultMov.get("_id"));
			ArrayList<Integer> actIDsArrayList = (ArrayList<Integer>)resultMov.get("actors");
			for (Integer integer : actIDsArrayList) {
				DBObject result =CRUDActors.findActorByID(integer);
				Actor actor = new Actor((Integer)result.get("_id"), (String)result.get("name"), (String)result.get("description"), (Date)result.get("birthdate"));
				movie.addActorInMovie(actor);
			}
			movies.add(movie);
		}
		return movies;
	}
	
	
	public static Movie findMovie(String name){
		DBObject movieDbObject = Connector.moviesColl.findOne(new BasicDBObject("name", name));
		Movie movie = new Movie((String)movieDbObject.get("name"), (int)movieDbObject.get("year"));
		return movie;
	}
	
	public static ArrayList<Movie> findMoviesByYear(int year){
		ArrayList<Movie> movies = new ArrayList<Movie>();
		DBCursor moviesCursor = Connector.moviesColl.find(new BasicDBObject("year", year));
		for (DBObject movieDbObject : moviesCursor) {
			Movie movie = new Movie((String)movieDbObject.get("name"), (int)movieDbObject.get("year"));
			movies.add(movie);
		}
		return movies;
	}
	
	public static void updateMovies(int searchedID, ArrayList<Integer> arrayList) {
		Connector.moviesColl.update(
				new BasicDBObject("_id", searchedID),
				new BasicDBObject("$set",
						new BasicDBObject("actors", arrayList)));
		Connector.closeConnection();
	}
	
}
