import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PatientView extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Patient's View");

        Label contactInfoLabel = new Label("Contact Information");
        contactInfoLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-alignment: center;");

        VBox contactInfoBox = new VBox(10, contactInfoLabel);
        contactInfoBox.setLayoutX(10);
        contactInfoBox.setLayoutY(10);

        Label nameLabel = new Label("Name: ");
        TextField nameField = new TextField();

        Label emailLabel = new Label("Email: ");
        TextField emailField = new TextField();

        Label phoneLabel = new Label("Phone: ");
        TextField phoneField = new TextField();

        VBox personalInfoLeft = new VBox(10);
        personalInfoLeft.getChildren().addAll(nameLabel, nameField, emailLabel, emailField, phoneLabel, phoneField);

        Label addressLabel = new Label("Address: ");
        TextField addressField = new TextField();

        Label emergencyContactLabel = new Label("Emergency Contact: ");
        TextField emergencyContactField = new TextField();

        Label dobLabel = new Label("DOB: ");
        DatePicker dobPicker = new DatePicker();

        VBox personalInfoRight = new VBox(10);
        personalInfoRight.getChildren().addAll(addressLabel, addressField, emergencyContactLabel, emergencyContactField, dobLabel, dobPicker);

        HBox personalInfoBox = new HBox(20);
        personalInfoBox.getChildren().addAll(personalInfoLeft, personalInfoRight);
        personalInfoBox.setLayoutX(10);
        personalInfoBox.setLayoutY(50);

        Button editButton = new Button("Edit");
        Button billingButton = new Button("Billing");

        HBox buttonsBox = new HBox(150);
        buttonsBox.getChildren().addAll(editButton, billingButton);
        buttonsBox.setLayoutX(60);
        buttonsBox.setLayoutY(250);

        Label appointmentLabel = new Label("Appointment: ");
        appointmentLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-alignment: center;");
        CheckBox upcomingCheckbox = new CheckBox("Upcoming");
        CheckBox pastCheckbox = new CheckBox("Past");
        CheckBox scheduleCheckbox = new CheckBox("Schedule");
        DatePicker appointmentPicker = new DatePicker();

        VBox appointmentBox = new VBox(10, appointmentLabel, upcomingCheckbox, pastCheckbox, scheduleCheckbox, appointmentPicker);
        appointmentBox.setLayoutX(1350);
        appointmentBox.setLayoutY(10);

        Label medicalHistoryLabel = new Label("Medical History: ");
        medicalHistoryLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-alignment: center;");
        Label allergiesLabel = new Label("Allergies: ");
        TextArea allergiesTextArea = new TextArea();
        allergiesTextArea.setPrefSize(500, 150); // Set preferred width and height
        Label prescriptionLabel = new Label("Prescription: ");
        TextArea prescriptionTextArea = new TextArea();
        prescriptionTextArea.setPrefSize(500, 150); // Set preferred width and height
        Label injuriesLabel = new Label("Injuries: ");
        TextArea injuriesTextArea = new TextArea();
        injuriesTextArea.setPrefSize(500, 150); // Set preferred width and height

        VBox medicalHistoryBox = new VBox(10, medicalHistoryLabel, allergiesLabel, allergiesTextArea, prescriptionLabel,
                prescriptionTextArea, injuriesLabel, injuriesTextArea);
        medicalHistoryBox.setLayoutX(10);
        medicalHistoryBox.setLayoutY(0);

        // Create Chat VBox
        Label chatLabel = new Label("Chat");
        chatLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-alignment: center;");
        TextArea chatTextArea = new TextArea();
        chatTextArea.setPrefSize(250, 225);
        VBox chatBox = new VBox(10, chatLabel, chatTextArea);
        chatBox.setLayoutX(650);
        chatBox.setLayoutY(10);

        // Create Lab Results VBox
        Label labResultsLabel = new Label("Lab Results");
        labResultsLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-alignment: center;");
        TextArea labResultsTextArea = new TextArea();
        labResultsTextArea.setPrefSize(800, 525);
        VBox labResultsBox = new VBox(10, labResultsLabel, labResultsTextArea);
        labResultsBox.setLayoutX(700);
        labResultsBox.setLayoutY(10);

        Pane topPane = new Pane();
        topPane.setStyle("-fx-background-color: #F4DED6;");
        topPane.setPrefHeight(300);
        topPane.getChildren().addAll(contactInfoBox, personalInfoBox, appointmentBox, buttonsBox, chatBox);

        Pane bottomPane = new Pane();
        bottomPane.setStyle("-fx-background-color: #B1D3FB;");
        bottomPane.setPrefHeight(600);
        bottomPane.getChildren().addAll(medicalHistoryBox, labResultsBox);

        VBox vbox = new VBox(topPane, bottomPane);

        Scene scene = new Scene(vbox, 1200, 500); // Adjusted scene width for better visibility
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}