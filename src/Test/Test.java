package Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import sun.misc.CEStreamExhausted;
import Model.Actor;
import Model.Movie;
import Mongo.CRUDActors;
import Mongo.CRUDMovies;
import Mongo.Connector;
import Service.ActorService;
import Service.MovieService;
import Utils.ListOfActors;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;

public class Test {
	public static void main(String[] args) {

		DB db = Connector.connectionOpen();	

//		Task 1 - Collection creation - 10 movie & 8 actor objects
		
		Movie movie1 = new Movie("The Terminator", 1984);	
		Movie movie2 = new Movie("Deadpool", 2016);
		Movie movie3 = new Movie("X-men", 2000);
		Movie movie4 = new Movie("Ant-Man", 2015);
		Movie movie5 = new Movie("Green Lantern", 2011);
		Movie movie6 = new Movie("Iron Man", 2008);
		Movie movie7 = new Movie("I Am Number Four", 2011);
		Movie movie8 = new Movie("The Hunger Games", 2012);
		Movie movie9 = new Movie("Silver Linings Playbook", 2012);
		Movie movie10 = new Movie("Inception", 2010);
		
		
		String act1description = "With an almost unpronounceable surname and a thick Austrian accent, who would have ever believed that a brash, quick talking bodybuilder from a small European village would become one of Hollywood's biggest stars, marry into the prestigious Kennedy family, amass a fortune via shrewd investments and one day be the Governor of California!? ";
		Actor act1 = new Actor(2,"Arnold Schwarzenegger", act1description, new Date(47,5,6));

		String act2description = "Linda Carroll Hamilton was born on September 26, 1956 in Salisbury, Maryland, to Barbara K. (Holt) and Carroll Stanford Hamilton, a physician. Following Wicomico High School, she studied for two years at Washington College in Chestertown, Maryland, before moving on to acting studies in New York.";
		Actor act2 = new Actor(1,"Linda Hamilton", act2description, new Date(56,8,26));
				
		String act3description = "Hugh Jackman was born in Sydney, New South Wales,to Grace McNeil (Greenwood) and Christopher John Jackman, an accountant.";
		Actor act3 = new Actor(4,"Hugh Jackman", act3description, new Date(68,9,12));
		
		String act4description = "Ryan Rodney Reynolds was born on October 23, 1976 in Vancouver, British Columbia, Canada, the youngest of four children. His father, Jim, was a food wholesaler and his mother, Tammy, was a retail-store saleswoman.";
		Actor act4 = new Actor(3,"Ryan Reynolds", act4description, new Date(76,9,23));		
		
		String act5description = "Robert Downey Jr. has evolved into one of the most respected actors in Hollywood. With an amazing list of credits to his name, he has managed to stay new and fresh even after over four decades in the business.";
		Actor act5 = new Actor(5, "Robert Downey Jr.", act5description, new Date(65, 3, 4));
		
		String act6description = "Academy Award-winning actress Jennifer Lawrence is best known for playing Katniss Everdeen in The Hunger Games (2012), Tiffany Maxwell in Silver Linings Playbook (2012), and Rosalyn Rosenfeld in American Hustle (2013).";
		Actor act6 = new Actor(6, "Jennifer Lawrence", act6description, new Date(90, 7, 15));
		
		String act7description = "Few actors in the world have had a career quite as diverse as Leonardo DiCaprio's. DiCaprio has gone from relatively humble beginnings, as a supporting cast member of the sitcom Growing Pains (1985) and low budget horror movies, such as Critters 3 (1991).";
		Actor act7 = new Actor(7, "Leonardo DiCaprio", act7description, new Date(74, 10, 11));
		
		String act8description = "Alexander Richard Pettyfer was born in Hertfordshire and raised in Windsor. His mother, Lee (Robinson), is an interior designer. His father, Richard Pettyfer, is a fellow actor.";
		Actor act8 = new Actor(8, "Alex Pettyfer", act8description, new Date(90,3,10));
		
//		movie1.addActorInMovie(act1);
//		movie1.addActorInMovie(act2);
//		MovieService.insertMovie(movie1, db);
//		
//		movie2.addActorInMovie(act4);
//		MovieService.insertMovie(movie2, db);
//		
//		movie3.addActorInMovie(act3);
//		movie3.addActorInMovie(act4);
//		movie3.addActorInMovie(act6);
//		MovieService.insertMovie(movie3, db);
//		
//		movie4.addActorInMovie(act4);
//		MovieService.insertMovie(movie4, db);
//		
//		movie5.addActorInMovie(act4);
//		MovieService.insertMovie(movie5, db);
//		
//		movie6.addActorInMovie(act5);
//		MovieService.insertMovie(movie6, db);
//		
//		movie7.addActorInMovie(act8);
//		MovieService.insertMovie(movie7, db);
//		
//		movie8.addActorInMovie(act6);
//		MovieService.insertMovie(movie8, db);
//		
//		movie9.addActorInMovie(act6);
//		MovieService.insertMovie(movie9, db);
//		
//		movie10.addActorInMovie(act7);
//		MovieService.insertMovie(movie10, db);
				
// 		Task 5 - Sort 4 movies by date
		
// 		ArrayList<Movie> movies = CRUDMovies.returnFourOldestMovies(db);
//
// 		for (Movie movie : movies) {
//			System.out.println(movie.toString());
//		}
		
		

//		Task6 - Return actors sorted by birthDate from 3 movies	
		
//		ArrayList<Actor> actors = CRUDActors.returnActorsFromThreeMovies(db);	
//		for (Actor actor : actors) {
//			System.out.println(actor.toString());
//		}

//		Task 7 - Return all movies from 3 actors with name and year
		
// 		HashMap<Integer,Actor> actorsWithMovies = CRUDActors.printThreeActorsMovies(db);
//		for (Actor actor : actorsWithMovies.values()) {
//			actor.showAllMoviesForActor();
//		}

//		Task 8 - Remove 2 actors and their ID's in movies.actors
		
//		CRUDActors.removeAnActor(db, 3);
// 		CRUDActors.removeAnActor(db, 6);
		
	
//		Task 9 - Create a new actor and add him in 4 movies
		
//		String act9description = " hsdgkhfdkgj";
//		Actor actor9 = new Actor(9, "Test Test", act9description, new Date(93, 7, 6));	
//		CRUDActors.addNewActorInFourMovies(db, actor9);
		
		
//		Task 9(old) - update an actors ID in actors and movies	
		
//		CRUDActors.updateActorID(db, 8, 11);
		
		}
				
			}
		