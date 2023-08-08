package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.*;
import java.sql.*;

public class MainController {
	@FXML
	private Button btn_login;
	@FXML
	private Button btn_signup;
	@FXML
	private TextField textID;
	@FXML
	private PasswordField password;
	    
	@FXML
	public void clickLogin(ActionEvent event) {
		try {
            
			String user_id = textID.getText();
			String pwd = password.getText();
			String sql ="select ID, password from user where ID = ?and password = ?";
			PreparedStatement stmt = connectMysql.Connnector.executePreparedStatement(sql);
			stmt.setString(1,user_id);
			stmt.setString(2, pwd);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
	            // User ID and password are correct, record user ID in session
				new session.Session().setUserID(user_id);
				// Load the next page FXML file
	            Parent homePage = FXMLLoader.load(getClass().getClassLoader().getResource("view/homepage.fxml"));

	            // Create a new scene with the next page content
	            Scene scene = new Scene(homePage);

	            // Get the current stage (primaryStage) from the button's scene
	            Stage primaryStage = (Stage) btn_login.getScene().getWindow();

	            // Set the new scene on the stage (Switch to the next page)
	            primaryStage.setScene(scene);
	            primaryStage.setResizable(false);
	            primaryStage.setTitle("homePage"); // Set the title of the next page
	            primaryStage.show();
	            System.out.println("user ID:"+ session.Session.getUserID());
	        } 
			else {
	            // User ID and/or password are incorrect
				Alert alert = new Alert(AlertType.WARNING);
	            alert.setTitle("Invalid Credentials");
	            alert.setHeaderText(null);
	            alert.setContentText("Invalid username or password. Please try again.");
	            
	            // Show the Alert dialog
	            alert.showAndWait();
	        }
			
			
			
        } catch (IOException e) {
            e.printStackTrace();
        }
		catch(SQLException e) {
			e.printStackTrace();
		}
	    }

	    @FXML
	public void clickSignup(ActionEvent event) {
	    	try {
	            // Load the next page FXML file
	            Parent signup_page = FXMLLoader.load(getClass().getClassLoader().getResource("view/signup_page.fxml"));

	            // Create a new scene with the next page content
	            Scene scene = new Scene(signup_page);

	            // Get the current stage (primaryStage) from the button's scene
	            Stage primaryStage = (Stage) btn_signup.getScene().getWindow();

	            // Set the new scene on the stage (Switch to the next page)
	            primaryStage.setScene(scene);
	            primaryStage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		    }

	    }

	


