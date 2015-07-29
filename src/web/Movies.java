package web;

import javax.sound.midi.Track;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import Model.Movie;
import Mongo.Connector;
import Service.MovieService;

@Path("/movies")
public class Movies {
	

	
	@GET()
	@Produces(MediaType.APPLICATION_JSON)
	public String returnAllMovies(){
//		Connector.checkForConnection();
		int count = MovieService.returnTheCountOfAllMovies();
		return MovieService.printMoviesSortedBy(count, "_id");
	}
	
	@Path("/id/{i}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String returnNMoviesSortByID(@PathParam("i")int i){
		return MovieService.printMoviesSortedBy(i, "id");
	}
	
	@Path("/year/{i}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String returnIMovies(@PathParam("i") int i){
//		Connector.checkForConnection();
		return MovieService.printMoviesSortedBy(i, "year");
	}
	
	@Path("/year")
	@GET()
	@Produces(MediaType.APPLICATION_JSON)
	public String returnAllMoviesSortByYear(){
		int count = MovieService.returnTheCountOfAllMovies();
		return MovieService.printMoviesSortedBy(count, "year");
	}
	
	@Path("/abc")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String returnMoviesAlpabetically(){
//		Connector.checkForConnection();
		int count = MovieService.returnTheCountOfAllMovies();
		return MovieService.printMoviesSortedBy(count, "name");
	}
	
	@Path("/abc/{i}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String returnIMoviesSortByABC(@PathParam("i") int i){
		return MovieService.printMoviesSortedBy(i, "year");
	}
	
	@Path("/find={name}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String returnMovieByName(@PathParam("name") String name){
		return MovieService.findMovieByName(name);
	}
	
	@Path("/find/{year}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String returnMovieByYear(@PathParam("year") int year){
		return MovieService.findMoviesByYear(year);
	}
}
