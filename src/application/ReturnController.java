package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.sql.Date;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
public class ReturnController {
  
	@FXML
	private TextField userID;
	@FXML
	private TextField bookID;
	@FXML
	private Button btn_return;
	@FXML
	private Button btn_homepage;

	// Event Listener on Button[#btn_return].onAction
	@FXML
	public void clicktoReturn(ActionEvent event) {
		// TODO Autogenerated
		try {
			int br_id = 0;
			String id = userID.getText();
			String book_id = bookID.getText();
			if(! id.equals(session.Session.getUserID())) 
			{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("It is not your user ID, are you sure to proceed?");
			alert.showAndWait();
			}
			else 
			{// userid has been checked
				String sql = "select brID from br_record where UID=? and bookID=?";
				PreparedStatement stmt = connectMysql.Connnector.executePreparedStatement(sql);
				stmt.setString(1, id);
				stmt.setString(2, book_id);
				ResultSet rs = stmt.executeQuery();
					if(rs.next()) 
					{// borrowed record has been checked
					
						br_id = rs.getInt("brID");
						String updateReturnDate = "update br_record set `returnDate` = ? where brID = ?";
						stmt = connectMysql.Connnector.executePreparedStatement(updateReturnDate);
						String d = new model.CurrentDate().toString();
						Date date = Date.valueOf(d);// String convert into a sql.Date type variable
						stmt.setDate(1, date);
						stmt.setInt(2, br_id);
						stmt.executeUpdate();// update return date
						// check whether the update is successful
						String checkReturnDate = "select returnDate from br_record where brID = ?";
						stmt = connectMysql.Connnector.executePreparedStatement(checkReturnDate);
						stmt.setInt(1,br_id);
						rs = stmt.executeQuery();
						if(rs.next()) 
						{
							Date checkdate = rs.getDate("returnDate");
					
							if(checkdate != null) 
							{// confirmed the update of reuturnDate is successful
								String setCondition = "UPDATE `library_management_system`.`book` SET `condition` = 'free' WHERE (`bookID` = ?)";
								PreparedStatement state = connectMysql.Connnector.executePreparedStatement(setCondition);
								state.setString(1, book_id);
								state.executeUpdate();// set the condition of the returned book = free
						
						
								String getISBN = "select ISBN from book where bookID = ?";
								state = connectMysql.Connnector.executePreparedStatement(getISBN);
								state.setString(1, book_id);
								ResultSet res = state.executeQuery();
								String ISBN = null;
								while(res.next()) {
									ISBN = res.getString("ISBN");// get the ISBN and use it to update the table 'bookinfo'
								}
								String setStock = "update bookinfo set inStock=inStock+1 where ISBN = ?";
								state = connectMysql.Connnector.executePreparedStatement(setStock);
								state.setString(1,ISBN);
								state.executeUpdate();// update the stock of the corresponding book
						
								String calCredits = "select overdue,left(duration,2) as during,DATEDIFF(returnDate,borrowDate) as duetime from br_record where brID = ?";
								state = connectMysql.Connnector.executePreparedStatement(calCredits);
								state.setInt(1, br_id);
								res = state.executeQuery();
								int overdue = 0;
								int due_time = 0;
								int duration;
								while(res.next()) {
									overdue = Integer.valueOf(res.getString("overdue"));
									// get the overdue value and then add/minus credits based on it
									duration = Integer.valueOf( (res.getString("during")) );
									due_time = res.getInt("duetime")-duration;
								}// get how many days it has been overdue for
								if(overdue == 1) {// overdue, minus credits
									String minusCredits = "update user set credits=credits - ? where ID=?";
									PreparedStatement minus = connectMysql.Connnector.executePreparedStatement(minusCredits);
									minus.setInt(1, due_time);
									minus.setString(2, id);
									minus.executeUpdate();
								}
								else {//return on time, reward 2 credits per time
									String addCredits  = "update user set credits=credits+2 where ID=?";
									PreparedStatement add = connectMysql.Connnector.executePreparedStatement(addCredits);
									add.setString(1, id);
									add.executeUpdate();
								}
								Alert alert = new Alert(AlertType.CONFIRMATION);
								alert.setContentText("You have returned the book successfully!");
								alert.showAndWait();
								//content for reserve function...
								String s = "select * from reservation where bookID=?";
								PreparedStatement checkBookID = connectMysql.Connnector.executePreparedStatement(s);
								checkBookID.setString(1, book_id);
								ResultSet r = checkBookID.executeQuery();
								if(r.next()) {
									Date reserved_date = Date.valueOf(new model.CurrentDate().toString());
									s = "update reservation set reserved_date=? where bookID=?";
									PreparedStatement upd = connectMysql.Connnector.executePreparedStatement(s);
									upd.setDate(1, reserved_date);
									upd.setString(2, book_id);
									upd.executeUpdate();// update reserved_date if this book has been reserved
							
									s = "update book set `condition`=? where bookID=?";
									upd = connectMysql.Connnector.executePreparedStatement(s);
									upd.setString(1, "reserved");
									upd.setString(2, book_id);
									upd.executeUpdate();// update condition
								}
								else 
								{
							
								}
							}
							else 
							{
								Alert alert = new Alert(AlertType.ERROR);
								alert.setContentText("Exception!Return date is null!");
								alert.showAndWait();
							}
						}
						else 
						{
							Alert alert = new Alert(AlertType.ERROR);
							alert.setContentText("Something went wrong, can't find the return record");
							alert.showAndWait();
						}
					}// borrow record checked
					else 
					{// borrow record  wrong
						Alert alert = new Alert(AlertType.ERROR);
						alert.setContentText("You have not borrowed this book, can't find the return record");
						alert.showAndWait();
					}
				
				}//userid checked
			}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	// Event Listener on Button[#btn_homepage].onAction*/
	@FXML
	public void clicktoHomepage(ActionEvent event) {
		// TODO Autogenerated
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
