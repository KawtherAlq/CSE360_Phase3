package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.*;

//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.List;

public class PatientView extends Application {
    private static final String CHAT_FILE = "chat.txt";

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
        chatTextArea.setPrefSize(200, 150); 

        TextField chatInputField = new TextField();
        chatInputField.setPromptText("Type your message here...");
        chatInputField.setLayoutY(400);

        Button sendButton = new Button("Send");
        sendButton.setLayoutY(400);
        sendButton.setOnAction(e -> {
            String message = chatInputField.getText();
            chatTextArea.appendText("Patient: " + message + "\n");
            chatInputField.clear();

            // Write the message to the chat file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CHAT_FILE, true))) {
                writer.write("Patient: " + message);
                writer.newLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // Load chat history
        loadChatHistory(chatTextArea);

        VBox chatBox = new VBox(10, chatLabel, chatTextArea, chatInputField, sendButton);
        chatBox.setLayoutX(480);
        chatBox.setLayoutY(10);

        Pane topPane = new Pane();
        topPane.setStyle("-fx-background-color: #F4DED6;");
        topPane.setPrefHeight(350);
        topPane.getChildren().addAll(contactInfoBox, personalInfoBox, appointmentBox, buttonsBox, chatBox);

        Pane bottomPane = new Pane();
        bottomPane.setStyle("-fx-background-color: #B1D3FB;");
        bottomPane.setPrefHeight(550);
        bottomPane.getChildren().addAll(medicalHistoryBox);

        VBox vbox = new VBox(topPane, bottomPane);

        Scene scene = new Scene(vbox, 1056, 768); // Adjusted scene width for better visibility
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadChatHistory(TextArea chatTextArea) {
        try (BufferedReader reader = new BufferedReader(new FileReader(CHAT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                chatTextArea.appendText(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}