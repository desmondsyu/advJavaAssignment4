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

import java.io.IOException;
import java.lang.annotation.AnnotationTypeMismatchException;

public class PersonGui extends Application {

    private RandomIO randomIO;
    TextField recordText;
    TextField fNameText;
    TextField lNameText;
    TextField ageText;
    TextField phoneText;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        try {
            randomIO = new RandomIO("persons.dat");

        } catch (IOException e) {
            popAlert("Error opening the file: " + e.getMessage());
            System.exit(1);
        }

        // labels and text fields
        Label recordLabel = new Label("Record #");
        Label fNameLabel = new Label ("First Name");
        Label lNameLabel = new Label ("Last Name");
        Label ageLabel = new Label ("Age");
        Label phoneLabel = new Label ("Phone");

        recordText = new TextField();
        fNameText = new TextField();
        lNameText = new TextField();
        ageText = new TextField();
        phoneText = new TextField();

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
        Scene scene = new Scene(mainPane, 400, 400);

        // set style for stage
        primaryStage.setTitle("Random File Processing");
        primaryStage.setResizable(false);;
        primaryStage.setScene(scene);
        primaryStage.show();

        addButton.setOnAction(e -> addPerson(fNameText.getText(), lNameText.getText(), phoneText.getText(), ageText.getText()));
        findButton.setOnAction(e -> findPerson(recordText.getText()));
    }

    private void findPerson(String record) {
        try {
            Person person = randomIO.findPerson(Integer.parseInt(record));
            displayPersonInformation(person);
        } catch(NumberFormatException e) {
            popAlert("Record must be a number.");
        } catch(Exception e) {
            popAlert(e.getMessage());
        }
    }
    private void displayPersonInformation(Person person) {
        fNameText.setText(person.getFirstName());
        lNameText.setText(person.getLastName());
        phoneText.setText(person.getPhone());
        ageText.setText(String.valueOf(person.getAge()));
    }

    private void addPerson(String firstName, String lastName, String phone, String age) {
        try {
            randomIO.addPerson(new Person(firstName, lastName, phone, Integer.parseInt(age)));
            resetFields();
            popAlert("New person added successfully!", "Success!");

        } catch(NumberFormatException e) {
            popAlert("Age must be a number.");
        } catch(Exception e) {
            popAlert(e.getMessage());
        }
    }

    private void resetFields() {
        recordText.setText("");
        fNameText.setText("");
        lNameText.setText("");
        phoneText.setText("");
        ageText.setText("");
    }

    private void popAlert(String message) {
        popAlert(message, "Error");
    }
    private void popAlert(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}