package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import session.Session;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.CheckMenuItem;
import javafx.event.*;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javafx.scene.control.Alert;
public class HomepageController {

    @FXML
    private RadioMenuItem password;

    @FXML
    private RadioMenuItem profile;
    
    @FXML
    private MenuItem Return;

    @FXML
    private Button btn_search_genre;
    
    @FXML
    private Menu genre_menu;
    
    @FXML
    private CheckMenuItem romance;

    @FXML
    private CheckMenuItem memoir;

    @FXML
    private CheckMenuItem fantasy;
    
    @FXML
    private CheckMenuItem fiction;

    @FXML
    private CheckMenuItem dystopian;
    
    @FXML
    private CheckMenuItem biography;

    @FXML
    private CheckMenuItem young_adult;

    @FXML
    private CheckMenuItem science_fiction;
    
    @FXML
    private CheckMenuItem mystery;

    @FXML
    private CheckMenuItem science;

    @FXML
    private CheckMenuItem historical_fiction;
    
    @FXML
    private MenuItem bookname;

    @FXML
    private MenuItem author;
    
    @FXML
    private MenuItem ISBN;

    @FXML
    private Button btn_logout;
    @FXML
    private Button btn_cancel;

    @FXML
    public void clickLogout(ActionEvent event) {
    	try{
			Session.clear();//clear user id stored in session
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/main.fxml"));
			Scene scene = new Scene(root,600,600);
			Stage primaryStage = (Stage) btn_logout.getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.setTitle("Library System");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    

    @FXML
    public void clicktoCancel(ActionEvent event) {
    	try 
    	{
    		String user_id = session.Session.getUserID();
    		String check = "select returnDate from br_record where UID=? and returnDate is null";// user has not returned the book
    		PreparedStatement stmt = connectMysql.Connnector.executePreparedStatement(check);
    		stmt.setString(1,user_id);
    		ResultSet rs = stmt.executeQuery();
    		if(rs.next()) {
    			Alert alert = new Alert(AlertType.WARNING);
    			alert.setContentText("You have not yet returned all the books you borrowed!");
    			alert.showAndWait();
    		}
    		else {
    			String deleteReservation = "delete from reservation where UID=?";
    			stmt = connectMysql.Connnector.executePreparedStatement(deleteReservation);
    			stmt.setString(1, user_id);
    			stmt.executeUpdate();// delete reservation record of the user
    			
    			String deleteBr_record = "delete from br_record where UID=?";
    			stmt = connectMysql.Connnector.executePreparedStatement(deleteBr_record);
    			stmt.setString(1, user_id);
    			stmt.executeUpdate();// delete borrow and return records of the user
    			
    			String dropKey = "ALTER TABLE `br_record` DROP FOREIGN KEY `fk_user`"; 
    			String dropConstrain = "ALTER TABLE `br_record` DROP INDEX `fk_user_idx`";
    			connectMysql.Connnector.executeUpdateStatement(dropKey);
    			connectMysql.Connnector.executeUpdateStatement(dropConstrain);// delete all the constraints first
    			
    			String deleteUser = "delete from user where ID=?";
    			stmt = connectMysql.Connnector.executePreparedStatement(deleteUser);
    			stmt.setString(1, user_id);
    			stmt.executeUpdate();// delete user profile
    			
    			String addIndex = "ALTER TABLE `br_record` ADD INDEX `fk_user_idx` (`UID` ASC) VISIBLE;";
    			String addConstrain = "ALTER TABLE `br_record` ADD CONSTRAINT `fk_user`  FOREIGN KEY (`UID`) REFERENCES `library_management_system`.`user` (`ID`)";
    			connectMysql.Connnector.executeUpdateStatement(addIndex);
    			connectMysql.Connnector.executeUpdateStatement(addConstrain);//add the constraints of original table
    			
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setContentText("You have cancelled your account successfully!");
    			alert.showAndWait();
    			
    			Session.clear();//clear user id stored in session
    			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/main.fxml"));
    			Scene scene = new Scene(root,600,600);
    			Stage primaryStage = (Stage) btn_logout.getScene().getWindow();
    			primaryStage.setScene(scene);
    			primaryStage.setTitle("Library System");
    			primaryStage.setResizable(false);
    			primaryStage.show();//return to main page
    			
    		}
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	catch(IOException e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    public void clicktoViewProfile(ActionEvent event) {
    	try{
			
    		Parent profile = FXMLLoader.load(getClass().getClassLoader().getResource("view/profile.fxml"));
			Scene scene = new Scene(profile);
			Stage primaryStage = (Stage) btn_logout.getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.setTitle("user profile");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    public void clicktoChangePassword(ActionEvent event) {
    	try{
			Parent password = FXMLLoader.load(getClass().getClassLoader().getResource("view/changePassword.fxml"));
			Scene scene = new Scene(password);
			Stage primaryStage = (Stage) btn_logout.getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.setTitle("change password");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    public void clicktoReturn(ActionEvent event) {
    	try{
			Parent return_book = FXMLLoader.load(getClass().getClassLoader().getResource("view/return_book.fxml"));
			Scene scene = new Scene(return_book);
			Stage primaryStage = (Stage) btn_logout.getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.setTitle("Return Books");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    


    @FXML
    public void clicktoSearch(ActionEvent event) {
    	try{
			
			
    		for(MenuItem menuItem: genre_menu.getItems()) {
    			 CheckMenuItem genreItem = (CheckMenuItem)menuItem;
    			if(genreItem.isSelected()) {
    				session.Session.getSelected_genre().add(genreItem.getText());
    			}//get all the genre names which are selected and store it in session
    		}
    		Parent bookinfo = FXMLLoader.load(getClass().getClassLoader().getResource("view/bookinfo.fxml"));
			Scene scene = new Scene(bookinfo);
			Stage primaryStage = (Stage) btn_logout.getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.setTitle("Books");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    public void clickISBN(ActionEvent event) {
    	try{
			Parent bookinfo = FXMLLoader.load(getClass().getClassLoader().getResource("view/bookinfo.fxml"));
			Scene scene = new Scene(bookinfo);
			Stage primaryStage = (Stage) btn_logout.getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.setTitle("Books");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }


    @FXML
    public void clickAuthor(ActionEvent event) {
    	try{
			Parent bookinfo = FXMLLoader.load(getClass().getClassLoader().getResource("view/bookinfo.fxml"));
			Scene scene = new Scene(bookinfo);
			Stage primaryStage = (Stage) btn_logout.getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.setTitle("Books");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    

    @FXML
    public void clickBookname(ActionEvent event) {
    	try{
			Parent bookinfo = FXMLLoader.load(getClass().getClassLoader().getResource("view/bookinfo.fxml"));
			Scene scene = new Scene(bookinfo);
			Stage primaryStage = (Stage) btn_logout.getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.setTitle("Books");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    
    }
}
