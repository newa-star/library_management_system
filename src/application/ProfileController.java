package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.collections.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import session.Session;
import java.sql.*;
import javafx.event.ActionEvent;

public class ProfileController implements Initializable{

    @FXML
    private Button btn_homepage;
    @FXML
    private Button btn_changeProfile;
	@FXML
    private TableColumn name_col;

    @FXML
    private TableColumn id_col;

    @FXML
    private TableColumn credits_col;

    @FXML
    private TableColumn email_col;

    @FXML
    private TableView<model.UserData> tableview;
    
    public void initialize(URL url, ResourceBundle bundle) {
    	try{
    		id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
            name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
            email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
            credits_col.setCellValueFactory(new PropertyValueFactory<>("credits"));
            //tableview.getColumns().addAll(id_col,name_col,email_col,credits_col);
            
            ObservableList<model.UserData> data = FXCollections.observableArrayList();
    		
    		String sql = "SELECT ID, name, email, credits FROM user where ID = ?";
    		PreparedStatement stmt = connectMysql.Connnector.executePreparedStatement(sql);
    		stmt.setString(1, Session.getUserID());
    		ResultSet rs = stmt.executeQuery();
    		while(rs.next()) {
    			 String id = rs.getString("id");
                 String name = rs.getString("name");
                 String email = rs.getString("email");
                 int credits = rs.getInt("credits");
                 
                 model.UserData user_data = new model.UserData(id,name,email,credits);
                 data.add(user_data);
    		}
    		//System.out.println(datalist.get(0).getID());
    		
    		tableview.setItems(data);
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    @FXML
    void clicktoChangeProfile(ActionEvent event) {
    	try {	
       	 Parent changeProfile = FXMLLoader.load(getClass().getClassLoader().getResource("view/changeProfile.fxml"));

            // Create a new scene with the next page content
            Scene scene = new Scene(changeProfile);

            // Get the current stage (primaryStage) from the button's scene
            Stage primaryStage = (Stage) btn_homepage.getScene().getWindow();

            // Set the new scene on the stage (Switch to the next page)
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Change Profile"); // Set the title of the next page
            primaryStage.show();
   			
       	}
       	catch(IOException e) {
       		e.printStackTrace();
       	}
       }
    
    
    @FXML
    public void clicktoHomepage(ActionEvent event) {
    	try {	
    	 Parent homePage = FXMLLoader.load(getClass().getClassLoader().getResource("view/homepage.fxml"));

         // Create a new scene with the next page content
         Scene scene = new Scene(homePage);

         // Get the current stage (primaryStage) from the button's scene
         Stage primaryStage = (Stage) btn_homepage.getScene().getWindow();

         // Set the new scene on the stage (Switch to the next page)
         primaryStage.setScene(scene);
         primaryStage.setResizable(false);
         primaryStage.setTitle("homePage"); // Set the title of the next page
         primaryStage.show();
			
    	}
    	catch(IOException e) {
    		e.printStackTrace();
    	}
    }

}
