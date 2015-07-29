package web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Service.ActorService;
import Service.MovieService;

@Path("/actors")
public class Actors {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String actorsByBirthDate(){
		return ActorService.printThreeActorsSortedByBirthDate();
	}
	
	@Path("/mov3act")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String allMovFrom3actString(){
		return MovieService.printAllMoviesFromThreeActors();
	}
}
