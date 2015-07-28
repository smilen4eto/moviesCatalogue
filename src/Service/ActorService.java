package Service;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.DB;

import Model.Actor;
import Model.Movie;
import Mongo.CRUDActors;
import Mongo.CRUDMovies;
import Mongo.Connector;
import Utils.ListOfActors;

public class ActorService {
	public static List<Integer> addActorsInMovie(List<Actor> actors, DB db){
		ListOfActors.deleteAllActorsFromList();
		for (Actor actor : actors) {
			if (CRUDActors.findActorByID(actor.getId(), db)== null)
				Connector.connectToDB(CRUDActors.insertActors(actor, actor.getId()), "actors", db);
			
		ListOfActors.insertActorInList(actor.getId());
		}
		return ListOfActors.actors;
	}
	

}
