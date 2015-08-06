package Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import Model.Actor;
import Model.Movie;
import Mongo.CRUDActors;
import Mongo.CRUDMovies;
import Mongo.Connector;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;

public class MovieService {
	public static boolean insertMovie(String movie){
		Gson gSon = new Gson();
		Movie movie2 = gSon.fromJson(movie, Movie.class);
		int countOfMovies = (int) Connector.moviesColl.count();
		movie2.setId(100-countOfMovies);
		ActorService.addActorsInMovie(movie2.listOfActors);
		CRUDMovies.insertQueryMovie(movie2, movie2.getId());
		return true;
	}
	
	public static void removeMovie(int id){
		CRUDMovies.deleteAMovie(id);
	}
	
	public static String printMoviesSortedBy(int count, String sort){
 		ArrayList<Movie> movies = CRUDMovies.returnMovies(count, sort);
 		Gson gson = new Gson();
 		return gson.toJson(movies);
}
	public static int returnTheCountOfAllMovies(){
			return CRUDMovies.countOfMovies();
	}
	
	public static String findMovieByName(String name){
		Gson gson = new Gson();
		String json = gson.toJson(CRUDMovies.findMovie(name));
		return json;
	}
	
	public static String findMoviesByYear(int year){
		Gson gson = new Gson();
		String json = gson.toJson(CRUDMovies.findMoviesByYear(year));
		return json;
	}
	
	public static String printAllMoviesFromActors(int count){
 		HashMap<Integer,Actor> actorsWithMovies = CRUDActors.printActorsMovies(count);
 		Gson gson = new Gson();
 		String json = gson.toJson(actorsWithMovies);
 		return json;
	}
	
	public static String returnMovieByID(int id){
		Gson gson = new Gson();
		String json = gson.toJson(CRUDMovies.findMovieByID(id));
		return json;
	}
}
