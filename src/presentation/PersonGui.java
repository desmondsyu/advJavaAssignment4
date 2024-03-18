package presentation; // Package declaration for the presentation layer

import business.Person; 
import data.RandomIO; 
import javafx.application.Application; 
import javafx.geometry.Insets; 
import javafx.geometry.Pos; 
import javafx.scene.Scene; 
import javafx.scene.control.Alert; 
import javafx.scene.control.Button; 
import javafx.scene.control.Label;
import javafx.scene.control.TextField; 
import javafx.scene.layout.GridPane; 
import javafx.stage.Stage; 

public class PersonGui extends Application {

    private RandomIO randomIO; // Instance variable to handle file operations

    public static void main(String[] args) {
        launch(args); // Launching the JavaFX application
    }

    @Override
    public void start(Stage primaryStage) {

        randomIO = new RandomIO(); // Initializing the RandomIO instance

        // Labels and text fields for user input
        Label recordLabel = new Label("Record #");
        Label fNameLabel = new Label("First Name");
        Label lNameLabel = new Label("Last Name");
        Label ageLabel = new Label("Age");
        Label phoneLabel = new Label("Phone");

        TextField recordText = new TextField();
        TextField fNameText = new TextField();
        TextField lNameText = new TextField();
        TextField ageText = new TextField();
        TextField phoneText = new TextField();

        // Buttons for adding and finding records
        Button addButton = new Button("Add");
        Button findButton = new Button("Find");

        addButton.setPrefWidth(120); // Setting preferred width for buttons
        findButton.setPrefWidth(120);

        // Pane to arrange UI elements
        GridPane mainPane = new GridPane();

        // Adding labels and text fields to the pane
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

        // Setting style and layout for the pane
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setPadding(new Insets(20));
        mainPane.setHgap(30);
        mainPane.setVgap(15);

        // Creating the scene and setting it to the stage
        Scene scene = new Scene(mainPane, 400, 400); // Creating a scene with specified dimensions

        primaryStage.setTitle("Random File Processing"); // Setting the title of the stage
        primaryStage.setResizable(false); // Disabling window resizing
        primaryStage.setScene(scene); // Setting the scene to the stage
        primaryStage.show(); // Displaying the stage

        // Button actions for adding and finding records
        addButton.setOnAction(e -> { // Event handler for add button
            Person person; // Creating a Person object
            try {
                // Parsing user input and creating a Person object
                int recordNum = Integer.parseInt(recordText.getText());
                String fname = fNameText.getText();
                String lname = lNameText.getText();
                String phone = phoneText.getText();
                int age = Integer.parseInt(ageText.getText());

                person = new Person(fname, lname, phone, age); // Creating a new Person instance
                randomIO.addPerson(person); // Adding the Person to the file
                popAlert("Person added successfully!"); // Displaying success message

                // Clearing the text fields after successful addition
                clearTextFields(recordText, fNameText, lNameText, phoneText, ageText);
            } catch (NumberFormatException ex) {
                popAlert("Please input an integer for record# and age."); // Handling invalid input format
            } catch (IllegalArgumentException ex) {
                popAlert(ex.getMessage()); // Handling invalid input values
            } catch (Exception ex) {
                popAlert(ex.getMessage()); // Handling other exceptions
            }
        });

        findButton.setOnAction(e -> { // Event handler for find button
            try {
                int recordNumber = Integer.parseInt(recordText.getText()); // Parsing record number

                if (recordNumber <= 0) { // Checking for valid record number
                    popAlert("Record number must be greater than 0."); // Alerting user for invalid input
                    return;
                }

                // Finding person based on record number
                Person foundPerson = randomIO.findPerson(recordNumber);

                if (foundPerson != null) { // If person is found
                    // Displaying found person's information
                    fNameText.setText(foundPerson.getFirstName());
                    lNameText.setText(foundPerson.getLastName());
                    phoneText.setText(foundPerson.getPhone());
                    ageText.setText(String.valueOf(foundPerson.getAge()));

                    popAlert("Person found successfully!"); // Alerting user for success
                } else {
                    popAlert("Person not found."); // Alerting user if person not found
                }
            } catch (NumberFormatException ex) {
                popAlert("Please input a valid integer for record number."); // Handling invalid input format
            } catch (IllegalArgumentException ex) {
                popAlert(ex.getMessage()); // Handling invalid input values
            }
        });
    }

    // Method to display alert messages
    private void popAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING); // Creating a warning alert
        alert.setTitle("Error"); // Setting alert title
        alert.setHeaderText(null); // Setting header text
        alert.setContentText(message); // Setting alert message
        alert.showAndWait(); // Displaying the alert and waiting for user response
    }

    // Method to clear text fields
    private void clearTextFields(TextField... textFields) {
        for (TextField textField : textFields) { // Looping through text fields
            textField.setText(""); // Clearing each text field
        }
    }
}
