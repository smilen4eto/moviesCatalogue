package Service;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.mongodb.DB;

import Model.Actor;
import Model.Movie;
import Mongo.CRUDActors;
import Mongo.CRUDMovies;
import Mongo.Connector;
import Utils.ListOfActors;

public class ActorService {
	public static List<Integer> addActorsInMovie(List<Actor> actors){
		ListOfActors.deleteAllActorsFromList();
		for (Actor actor : actors) {
			if (CRUDActors.findActorByID(actor.getId())== null)
				CRUDActors.insertActors(actor, actor.getId());
			
		ListOfActors.insertActorInList(actor.getId());
		}
		return ListOfActors.actors;
	}
	
	public static String printThreeActorsSortedByBirthDate(){
		ArrayList<Actor> actors = CRUDActors.returnActorsFromThreeMovies();	
		Gson gson = new Gson();
		String json = gson.toJson(actors);
		return json;
	}
	
	public static void removeActor(int actorID){
		CRUDActors.removeAnActor(actorID);
		System.out.println("Actor number " + actorID + " has been successfully removed.");
	}
	
	public static void addActorInFourMovies(Actor actor){
		CRUDActors.addNewActorInFourMovies(actor);
		System.out.println(actor.getName() + " has been added successfully.");
	}
	
	public static void changeActorID(int oldID, int newID){
		CRUDActors.updateActorID(oldID, newID);
		System.out.println("The ID has been changed");
	}
	
	public static void addActorInExistingMovie(Actor actor, Movie movie){
		movie.addActorInMovie(actor);
		ArrayList<Integer> arrayList = movie.returnArrayListWithActorIDs();
		CRUDMovies.updateMovies(movie.getId(), arrayList);
	}

}
