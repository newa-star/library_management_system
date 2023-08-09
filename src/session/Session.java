package session;
import java.util.ArrayList;
public class Session {
	private static String userID;
	private static ArrayList<String> selected_genre = new ArrayList();
	
	public Session() {
		
	}
	
	public static String getUserID() {
		return userID;
	}
	
	public void setUserID(String user_id) {
		userID = user_id;
	}
	
	public static ArrayList<String> getSelected_genre() {
		return selected_genre; 
	}

	public static void clearSelected_genre() {
		selected_genre.clear();
	}
	public static void  clear() {
		userID = null;
	}
}
