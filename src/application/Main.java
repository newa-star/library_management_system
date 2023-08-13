package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			connectMysql.Connnector.callProcedure();// clear any obsolete reservations and corresponding book's condition 'free'
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/main.fxml"));
			Scene scene = new Scene(root,600,600);
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Library System");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
