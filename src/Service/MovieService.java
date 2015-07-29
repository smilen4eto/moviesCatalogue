package Service;

import java.util.ArrayList;
import java.util.HashMap;

import Model.Actor;
import Model.Movie;
import Mongo.CRUDActors;
import Mongo.CRUDMovies;
import Mongo.Connector;

import com.google.gson.Gson;
import com.mongodb.DB;

public class MovieService {
	public static boolean insertMovie(Movie movie){
		int countOfMovies = (int) Connector.moviesColl.count();
		movie.setId(countOfMovies+1);
		ActorService.addActorsInMovie(movie.listOfActors);
		CRUDMovies.insertQueryMovie(movie, movie.getId());
		Connector.closeConnection();
		return true;
		
	}
	
	public static String printMoviesSortedBy(int count, String sort){
//		Connector.checkForConnection();
 		ArrayList<Movie> movies = CRUDMovies.returnMovies(count, sort);
 		Gson gson = new Gson();
 		String json = gson.toJson(movies); 
 		return json;
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
	
	public static String printAllMoviesFromThreeActors(){
 		HashMap<Integer,Actor> actorsWithMovies = CRUDActors.printThreeActorsMovies();
 		Gson gson = new Gson();
 		String json = gson.toJson(actorsWithMovies);
 		return json;
	}
}
