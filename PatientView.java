import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PatientView extends Application {
    private TextField nameField;
    private TextField emailField;
    private TextField phoneField;
    private TextField addressField;
    private TextField emergencyContactField;
    private DatePicker dobPicker;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Patient's View");

        Label contactInfoLabel = new Label("Contact Information");
        contactInfoLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-alignment: center;");

        VBox contactInfoBox = new VBox(10, contactInfoLabel);
        contactInfoBox.setLayoutX(10);
        contactInfoBox.setLayoutY(10);

        Label nameLabel = new Label("Name: ");
        nameField = new TextField();
        nameField.setDisable(true);

        Label emailLabel = new Label("Email: ");
        emailField = new TextField();
        emailField.setDisable(true);

        Label phoneLabel = new Label("Phone: ");
        phoneField = new TextField();
        phoneField.setDisable(true);

        VBox personalInfoLeft = new VBox(10);
        personalInfoLeft.getChildren().addAll(nameLabel, nameField, emailLabel, emailField, phoneLabel, phoneField);

        Label addressLabel = new Label("Address: ");
        addressField = new TextField();
        addressField.setDisable(true);

        Label emergencyContactLabel = new Label("Emergency Contact: ");
        emergencyContactField = new TextField();
        emergencyContactField.setDisable(true);

        Label dobLabel = new Label("DOB: ");
        dobPicker = new DatePicker();
        dobPicker.setDisable(true);

        VBox personalInfoRight = new VBox(10);
        personalInfoRight.getChildren().addAll(addressLabel, addressField, emergencyContactLabel, emergencyContactField, dobLabel, dobPicker);

        HBox personalInfoBox = new HBox(20);
        personalInfoBox.getChildren().addAll(personalInfoLeft, personalInfoRight);
        personalInfoBox.setLayoutX(10);
        personalInfoBox.setLayoutY(50);

        Button editButton = new Button("Edit");
        editButton.setOnAction(e -> {
            // Enable text fields when the "Edit" button is clicked
            nameField.setDisable(false);
            emailField.setDisable(false);
            phoneField.setDisable(false);
            addressField.setDisable(false);
            emergencyContactField.setDisable(false);
            dobPicker.setDisable(false);
        });

        Button doneButton = new Button("Done");
        doneButton.setOnAction(e -> {
            nameField.setDisable(true);
            emailField.setDisable(true);
            phoneField.setDisable(true);
            addressField.setDisable(true);
            emergencyContactField.setDisable(true);
            dobPicker.setDisable(true);
        });

        Button billingButton = new Button("Billing"); 
        HBox bill = new HBox();
        bill.getChildren().addAll(billingButton);
        bill.setLayoutX(240);
        bill.setLayoutY(250);

        HBox buttonsBox = new HBox(30);
        buttonsBox.getChildren().addAll(editButton, doneButton);
        buttonsBox.setLayoutX(20);
        buttonsBox.setLayoutY(250);

        Label appointmentLabel = new Label("Appointment: ");
        appointmentLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-alignment: center;");
        CheckBox upcomingCheckbox = new CheckBox("Upcoming");
        CheckBox pastCheckbox = new CheckBox("Past");
        CheckBox scheduleCheckbox = new CheckBox("Schedule");
        DatePicker appointmentPicker = new DatePicker();

        VBox appointmentBox = new VBox(10, appointmentLabel, upcomingCheckbox, pastCheckbox, scheduleCheckbox, appointmentPicker);
        appointmentBox.setLayoutX(870);
        appointmentBox.setLayoutY(10);

        Label medicalHistoryLabel = new Label("Medical History: ");
        medicalHistoryLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-alignment: center;");
        Label allergiesLabel = new Label("Allergies: ");
        TextArea allergiesTextArea = new TextArea();
        allergiesTextArea.setPrefSize(500, 100); // Set preferred width and height
        Label prescriptionLabel = new Label("Prescription: ");
        TextArea prescriptionTextArea = new TextArea();
        prescriptionTextArea.setPrefSize(500, 100); // Set preferred width and height
        Label injuriesLabel = new Label("Injuries: ");
        TextArea injuriesTextArea = new TextArea();
        injuriesTextArea.setPrefSize(500, 100); // Set preferred width and height

        Button backButton = new Button("back");
   //   backButton.setStyle("-fx-background-color: #B1D3FB");
        backButton.setOnAction(e -> {
        SignIn signInPage = new SignIn();
        signInPage.showPortal(primaryStage);
    });

        VBox medicalHistoryBox = new VBox(10, medicalHistoryLabel, allergiesLabel, allergiesTextArea, prescriptionLabel,
                prescriptionTextArea, injuriesLabel, injuriesTextArea, backButton);
        medicalHistoryBox.setLayoutX(10);
        medicalHistoryBox.setLayoutY(0);

        // Create Chat VBox
        Label chatLabel = new Label("Chat");
        chatLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-alignment: center;");
        TextArea chatTextArea = new TextArea();
        chatTextArea.setPrefSize(250, 225);
        VBox chatBox = new VBox(10, chatLabel, chatTextArea);
        chatBox.setLayoutX(480);
        chatBox.setLayoutY(10);

        // Create Lab Results VBox
        Label labResultsLabel = new Label("Lab Results");
        labResultsLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-alignment: center;");
        TextArea labResultsTextArea = new TextArea();
        labResultsTextArea.setPrefSize(465, 430);
        VBox labResultsBox = new VBox(10, labResultsLabel, labResultsTextArea);
        labResultsBox.setLayoutX(580);
        labResultsBox.setLayoutY(10);

        Pane topPane = new Pane();
        topPane.setStyle("-fx-background-color: #F4DED6;");
        topPane.setPrefHeight(350);
        topPane.getChildren().addAll(contactInfoBox, personalInfoBox, appointmentBox, buttonsBox, bill, chatBox);

        Pane bottomPane = new Pane();
        bottomPane.setStyle("-fx-background-color: #B1D3FB;");
        bottomPane.setPrefHeight(550);
        bottomPane.getChildren().addAll(medicalHistoryBox, labResultsBox);

        VBox vbox = new VBox(topPane, bottomPane);

        Scene scene = new Scene(vbox, 1056, 768); // Adjusted scene width for better visibility
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}