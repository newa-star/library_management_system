package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.*;

public class MainController {
	@FXML
	private Button btn_login;
	@FXML
	private Button btn_signup;
	    
	@FXML
	public void clickLogin(ActionEvent event) {
		try {
            
			
			
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
        } catch (IOException e) {
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
	            Stage primaryStage = (Stage) btn_login.getScene().getWindow();

	            // Set the new scene on the stage (Switch to the next page)
	            primaryStage.setScene(scene);
	            primaryStage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		    }

	    }

	


