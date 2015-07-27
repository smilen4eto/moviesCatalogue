import java.util.ArrayList;
import java.util.List;


public class ListOfActors {
	static List<Integer> actors = new ArrayList<>();
	
	static boolean insertActorInList(int id){
		return actors.add(id);
	}
	
	static void deleteAllActorsFromList(){
		actors.clear();
	}
}
