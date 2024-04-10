import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DoctorView extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Doctor's View");

        Button notificationsButton = new Button("!");
        notificationsButton.setLayoutX(10);
        notificationsButton.setLayoutY(10);

        ComboBox<String> selectPatientComboBox = new ComboBox<>();
        selectPatientComboBox.getItems().addAll("Patient 1", "Patient 2", "Patient 3");
        selectPatientComboBox.setPromptText("Select Patient");
        selectPatientComboBox.setLayoutX(10);
        selectPatientComboBox.setLayoutY(50);

        Label nameLabel = new Label("Name: ");
        nameLabel.setLayoutX(80);
        nameLabel.setLayoutY(113);
        TextField nameField = new TextField();
        nameField.setLayoutX(120);
        nameField.setLayoutY(110);

        Label emailLabel = new Label("Email: ");
        emailLabel.setLayoutX(83);
        emailLabel.setLayoutY(163);
        TextField emailField = new TextField();
        emailField.setLayoutX(120);
        emailField.setLayoutY(160);

        Label phoneLabel = new Label("Phone: ");
        phoneLabel.setLayoutX(315);
        phoneLabel.setLayoutY(113);
        TextField phoneField = new TextField();
        phoneField.setLayoutX(355);
        phoneField.setLayoutY(110);
        
        Label addressLabel = new Label("Address: ");
        addressLabel.setLayoutX(305);
        addressLabel.setLayoutY(163);
        TextField addressField = new TextField();
        addressField.setLayoutX(355);
        addressField.setLayoutY(160);
        
        Label callLabel = new Label("Call Patient: ");
        callLabel.setLayoutX(550);
        callLabel.setLayoutY(113);

        Button callButton = new Button("Call");
        callButton.setLayoutX(617);
        callButton.setLayoutY(110);

        Label doctorNameLabel = new Label("Doctor's Name");
        doctorNameLabel.setLayoutX(685);
        doctorNameLabel.setLayoutY(10);

        TextArea physicalTestTextArea = new TextArea();
        physicalTestTextArea.setPromptText("Physical Test");
        physicalTestTextArea.setLayoutX(45);
        physicalTestTextArea.setLayoutY(0);
        physicalTestTextArea.setPrefSize(230, 250);

        TextArea prescriptionsTextArea = new TextArea();
        prescriptionsTextArea.setPromptText("Prescription(s)");
        prescriptionsTextArea.setLayoutX(525);
        prescriptionsTextArea.setLayoutY(0);
        prescriptionsTextArea.setPrefSize(235, 250);

        Button saveButton = new Button("Save");
        ComboBox<String> patientHistoryComboBox = new ComboBox<>();
        patientHistoryComboBox.getItems().addAll("Patient History", "Previous Health Issues", "Previous Prescribed Medications", "History of Immunization", "Other");
        patientHistoryComboBox.setPromptText("Patient History");
        patientHistoryComboBox.setLayoutX(290);
        patientHistoryComboBox.setLayoutY(15);

        Button finishedButton = new Button("Finished");

        HBox buttonsBox = new HBox(10); // Spacing between buttons
        buttonsBox.getChildren().addAll(saveButton, finishedButton);
        buttonsBox.setLayoutX(590);
        buttonsBox.setLayoutY(260);

        Pane topPane = new Pane();
        topPane.setStyle("-fx-background-color: #F4DED6;");
        topPane.setPrefHeight(225);
        topPane.getChildren().addAll(notificationsButton, selectPatientComboBox, nameLabel, nameField, emailLabel, emailField, phoneLabel, phoneField, addressLabel, callLabel, callButton, addressField, doctorNameLabel);

        Pane bottomPane = new Pane();
        bottomPane.setStyle("-fx-background-color: #B1D3FB;");
        bottomPane.setPrefHeight(300);
        bottomPane.getChildren().addAll(physicalTestTextArea, prescriptionsTextArea, patientHistoryComboBox, buttonsBox);

        VBox mainPane = new VBox();
        mainPane.getChildren().addAll(topPane, bottomPane);

        Scene scene = new Scene(mainPane, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

