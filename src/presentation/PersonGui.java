package presentation;

import java.util.InputMismatchException;

import business.Person;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
		
		addButton.setPrefWidth(120);
		findButton.setPrefWidth(120);
		
		// pane and element positions
		GridPane mainPane = new GridPane();
		
		mainPane.add(recordLabel, 0, 0);
		mainPane.add(recordText, 1, 0);
		mainPane.add(fNameLabel, 0, 1);
		mainPane.add(fNameText, 1, 1);
		mainPane.add(lNameLabel, 0, 2);
		mainPane.add(lNameText, 1, 2);
		mainPane.add(ageLabel, 0, 3);
		mainPane.add(ageText, 1, 3);
		mainPane.add(phoneLabel, 0, 4);
		mainPane.add(phoneText, 1, 4);
		mainPane.add(addButton, 0, 5);
		mainPane.add(findButton, 1, 5);
		
		// style for pane
		mainPane.setAlignment(Pos.CENTER);
		mainPane.setPadding(new Insets(20));
		mainPane.setHgap(30);
		mainPane.setVgap(15);
		
		// set scene and it's content
		Scene scene = new Scene(mainPane, 500, 500);
		
		// set style for stage
		primaryStage.setTitle("Random File Processing");
		primaryStage.setResizable(false);;
		primaryStage.setScene(scene);
		primaryStage.show();
		
/*Code below only for testing*/
		
		// button action
		addButton.setOnAction(e -> {
			Person person;
			
			try {
				int recordNum = Integer.parseInt(recordText.getText());
			}
			catch(NumberFormatException ex) {
				System.out.println("Please enter an integer for record#.");
			}
						
			try {
				
				String fname = fNameText.getText();
				String lname = lNameText.getText();
				String phone = phoneText.getText();
				int age = Integer.parseInt(ageText.getText());
				
				person = new Person(fname, lname, phone, age);
			}
			catch(NumberFormatException ex) {
				System.out.println("Please input an integer for age.");
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		});
		
		findButton.setOnAction(e -> {
			
		});
	}
	
}
