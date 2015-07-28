package Service;

import Model.Movie;
import Mongo.CRUDMovies;
import Mongo.Connector;

import com.mongodb.DB;

public class MovieService {
	public static boolean insertMovie(Movie movie, DB db){
		int countOfMovies = (int) db.getCollection("movies").count();
		movie.setId(countOfMovies+1);
		ActorService.addActorsInMovie(movie.listOfActors, db);
		Connector.connectToDB(CRUDMovies.insertQueryMovie(movie, movie.getId()), "movies", db);
		return true;
	}
}
