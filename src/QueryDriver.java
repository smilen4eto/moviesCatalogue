import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


public class QueryDriver {

	public static void findMovie(BasicDBObject query, DBCollection coll){

	    DBCursor cursor = coll.find(query);

	    try {
	       while(cursor.hasNext()) {
	          DBObject dbobj = cursor.next();
	        //Converting BasicDBObject to a custom Class(Employee)
	          Movies mov = (new Gson()).fromJson(dbobj.toString(), Movies.class);
	          System.out.println(mov.toString());
	       }
	    } finally {
	       cursor.close();
	    }

	}
	
}
	

