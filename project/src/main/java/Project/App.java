package Project;



import javafx.application.Application; 
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	
	public void start(final Stage primaryStage) throws Exception{
		primaryStage.setTitle("GradeOverview");
		primaryStage.setScene(new Scene(FXMLLoader.load(App.class.getResource("App.fxml"))));
		primaryStage.show();
	}
	
	/*
	 * Start application
	 */
	public static void main(final String[]args) {
		launch();
	}
}
