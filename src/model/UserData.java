package model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
public class UserData {
	private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty email;
    private SimpleIntegerProperty credits;
    
    public UserData(String id, String name, String email, int credits) {
    	this.id = new SimpleStringProperty(id);
    	this.name = new SimpleStringProperty(name);
    	this.email = new SimpleStringProperty(email);
    	this.credits = new SimpleIntegerProperty(credits);
    }
    
    public String getId() {// method name must be getId() not getID(), or it will cause error
    	return this.id.get();
    }
    
    public String getName() {
    	return this.name.get();
    }
    
    public String getEmail() {
    	return this.email.get();
    }
    
    public int getCredits() {
    	return this.credits.get();
    }
}
