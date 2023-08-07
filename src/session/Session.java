package session;

public class Session {
	private String userID;
	
	public Session(String userID) {
		this.userID = userID;
	}
	
	public String getUserID() {
		return this.userID;
	}
	
	public void clear() {
		this.userID = null;
	}
}
