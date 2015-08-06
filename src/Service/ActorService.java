package Service;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
				CRUDActors.insertActors(actor);
			
		ListOfActors.insertActorInList(actor.getId());
		}
		return ListOfActors.actors;
	}
	
	public static String returnAllActorsSortedByName(){
		return "";
	}
	
	public static String printActorsSortedByBirthDate(){
		ArrayList<Actor> actors = CRUDActors.returnActor();	
		Gson gson = new Gson();
		String json = gson.toJson(actors);
		return json;
	}
	
	public static void removeActor(int actorID){
		CRUDActors.removeAnActor(actorID);
		System.out.println("Actor number " + actorID + " has been successfully removed.");
	}
	
	public static void addActorInMovie(String movieString){
		Gson gson = new Gson();
		Movie movie = gson.fromJson(movieString, Movie.class);
		CRUDActors.addNewActorInFourMovies(movie.actorName, movie);
//		System.out.println(actor.getName() + " has been added successfully.");
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

	public static String findActorByName(String name){
		Gson gson = new Gson();
		String json = gson.toJson(CRUDActors.findActorByName(name));
		return json;
	}
	
	public static String findActorByID(int id){
		Gson gson = new Gson();
		String json = gson.toJson(CRUDActors.printActorMovieByID(id));
		return json;
	}
	
	public static int addAnActor(String actor){
		Gson gSon=  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Actor actor2 = gSon.fromJson(actor, Actor.class);
		System.out.println(actor2.toString());
		return CRUDActors.insertActors(actor2);
	}
}
