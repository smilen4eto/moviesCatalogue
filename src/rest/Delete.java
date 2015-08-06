package rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import Service.ActorService;
import Service.MovieService;

@Path("/delete")
public class Delete {

	@DELETE
	@Path("/movie/{id}")
	public void removeMovie(@PathParam("id") int id) {
		MovieService.removeMovie(id);
	}
	
	@DELETE
	@Path("/actor/{id}")
	public void removeActor(@PathParam("id") int id) {
		ActorService.removeActor(id);
	}
}
