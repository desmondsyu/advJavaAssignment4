package presentation;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonGui extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		// labels and text fields
		Label recordLabel = new Label("Record #");
		Label fNameLabel = new Label ("First Name");
		Label lNameLabel = new Label ("Last Name");
		Label ageLabel = new Label ("Age");
		Label phoneLabel = new Label ("Phone");
		
		TextField recordText = new TextField();
		TextField fNameText = new TextField();
		TextField lNameText = new TextField();
		TextField ageText = new TextField();
		TextField phoneText = new TextField();

		// buttons
		Button addButton = new Button("Add");
		Button findButton = new Button("Find");

		
		
		GridPane mainPane = new GridPane();
	}
	
}
