package rest;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Service.ActorService;

@Path("/update")
public class Update {

	@Path("/movie/addActor")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String insertAnActorInMovie(String movie){
		ActorService.addActorInMovie(movie);
		System.out.println(movie);
		return "";
	}
}
