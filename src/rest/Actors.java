package rest;



import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import Service.ActorService;
import Service.MovieService;

@Path("/actors")
public class Actors {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String actorsByBirthDate(){
		return ActorService.printActorsSortedByBirthDate();
	}
	
	
	
	@Path("/movies/{count}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String allMovFrom3actString(@PathParam("count") int count){
		return MovieService.printAllMoviesFromActors(count);
	}
	
	@Path("/find/{name}")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String returnMovieByName(@Context UriInfo uriInfo){
		return ActorService.findActorByName((uriInfo.getPathParameters(true).getFirst("name").toString()));
	}
	
	@Path("/find={id}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String returnMovieByID(@PathParam("id") int id){
		return ActorService.findActorByID(id);
	}
	
	
}
