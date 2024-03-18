package presentation;

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

    private RandomIO randomIO;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        randomIO = new RandomIO();

        // Labels and text fields
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

        // Buttons
        Button addButton = new Button("Add");
        Button findButton = new Button("Find");

        addButton.setPrefWidth(120);
        findButton.setPrefWidth(120);

        // Pane and element positions
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

        // Style for pane
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setPadding(new Insets(20));
        mainPane.setHgap(30);
        mainPane.setVgap(15);

        // Set scene and its content
        Scene scene = new Scene(mainPane, 400, 400);

        // Set style for stage
        primaryStage.setTitle("Random File Processing");
        primaryStage.setResizable(false);
        ;
        primaryStage.setScene(scene);
        primaryStage.show();

        // Button actions
        addButton.setOnAction(e -> {
            Person person;
            try {
                int recordNum = Integer.parseInt(recordText.getText());
                String fname = fNameText.getText();
                String lname = lNameText.getText();
                String phone = phoneText.getText();
                int age = Integer.parseInt(ageText.getText());

                person = new Person(fname, lname, phone, age);
                randomIO.addPerson(person);
                popAlert("Person added successfully!");

                // Clearing the text fields
                clearTextFields(recordText, fNameText, lNameText, phoneText, ageText);
            } catch (NumberFormatException ex) {
                popAlert("Please input an integer for record# and age.");
            } catch (IllegalArgumentException ex) {
                popAlert(ex.getMessage());
            } catch (Exception ex) {
                popAlert(ex.getMessage());
            }
        });

        findButton.setOnAction(e -> {
            try {
                int recordNumber = Integer.parseInt(recordText.getText());

                // Ensure recordNumber is valid (greater than 0)
                if (recordNumber <= 0) {
                    popAlert("Record number must be greater than 0.");
                    return;
                }

                // Find the person based on the record number
                Person foundPerson = randomIO.findPerson(recordNumber);

                // Display the found person's information in the text fields
                if (foundPerson != null) {
                    fNameText.setText(foundPerson.getFirstName());
                    lNameText.setText(foundPerson.getLastName());
                    phoneText.setText(foundPerson.getPhone());
                    ageText.setText(String.valueOf(foundPerson.getAge()));

                    popAlert("Person found successfully!");
                } else {
                    popAlert("Person not found.");
                }
            } catch (NumberFormatException ex) {
                popAlert("Please input a valid integer for record number.");
            } catch (IllegalArgumentException ex) {
                popAlert(ex.getMessage());
            }
        });
    }

    private void popAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearTextFields(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.setText("");
        }
    }
}
