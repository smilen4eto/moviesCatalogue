package rest;

import javax.sound.midi.Track;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.json.JSONObject;

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
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String returnMovieByName(@Context UriInfo uriInfo){
		return MovieService.findMovieByName(uriInfo.getPathParameters(true).getFirst("name").toString());
	}
	
	@Path("/find/{year}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String returnMovieByYear(@PathParam("year") int year){
		return MovieService.findMoviesByYear(year);
	}
	
	@Path("/findID={id}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String returnMovieByID(@PathParam("id") int id){
		return MovieService.returnMovieByID(id);
	}
	
	@Path("update")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateMovie(){
		return "";
	}
}
