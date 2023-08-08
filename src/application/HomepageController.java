package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import session.Session;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.CheckMenuItem;
import javafx.event.*;


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
