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
            Parent loginPage = FXMLLoader.load(getClass().getClassLoader().getResource("application/login_page.fxml"));

            // Create a new scene with the next page content
            Scene scene = new Scene(loginPage);

            // Get the current stage (primaryStage) from the button's scene
            Stage primaryStage = (Stage) btn_login.getScene().getWindow();

            // Set the new scene on the stage (Switch to the next page)
            primaryStage.setScene(scene);
            primaryStage.setTitle("Next Page"); // Set the title of the next page
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
	    }

	    @FXML
	public void clickSignup(ActionEvent event) {

	    }

	}


