package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Model.Actor;
import Service.ActorService;
import Service.MovieService;

@Path("/insert")
public class Insert {

	@Path("/actor")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String insertAnActor(String actor){
		int id = ActorService.addAnActor(actor);
		return ""+id;
	}
	
	@Path("/movie")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void insertAMovie(String movie){
		MovieService.insertMovie(movie);

	}
}
