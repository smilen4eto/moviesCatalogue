package Utils;
import java.util.ArrayList;
import java.util.List;


public class ListOfActors {
	public static List<Integer> actors = new ArrayList<>();
	
	public static boolean insertActorInList(int id){
		return actors.add(id);
	}
	
	public static void deleteAllActorsFromList(){
		actors.clear();
	}
}
