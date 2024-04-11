package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class DoctorView extends Application {
	 private static final String CHAT_FILE = "chat.txt";
	 
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Doctor's View");
        Button notificationButton = new Button("!");
        notificationButton.setLayoutX(10);
        notificationButton.setLayoutY(10);

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
        
        Button backButton = new Button("back");
        //   backButton.setStyle("-fx-background-color: #B1D3FB");
             backButton.setOnAction(e -> {
             SignIn signInPage = new SignIn();
             signInPage.showPortal(primaryStage);
         });

        HBox buttonsBox = new HBox(10);
        buttonsBox.getChildren().addAll(backButton, saveButton, finishedButton);
        buttonsBox.setLayoutX(590);
        buttonsBox.setLayoutY(260);

        Pane topPane = new Pane();
        topPane.setStyle("-fx-background-color: #F4DED6;");
        topPane.setPrefHeight(225);
        topPane.getChildren().addAll(notificationButton, selectPatientComboBox, nameLabel, nameField, emailLabel, emailField, phoneLabel, phoneField, addressLabel, callLabel, callButton, addressField, doctorNameLabel);

        Pane bottomPane = new Pane();
        bottomPane.setStyle("-fx-background-color: #B1D3FB;");
        bottomPane.setPrefHeight(300);
        bottomPane.getChildren().addAll(physicalTestTextArea, prescriptionsTextArea, patientHistoryComboBox, buttonsBox);

        VBox mainPane = new VBox();
        mainPane.getChildren().addAll(topPane, bottomPane);
        
        notificationButton.setOnAction(event -> {
            Stage chatStage = new Stage();
            chatStage.setTitle("Chat with Doctor");
            VBox chatLayout = new VBox(10);
            TextArea chatHistoryTextArea = new TextArea();
            chatHistoryTextArea.setEditable(false);
            loadChatHistory(chatHistoryTextArea);
            
            TextField chatInputField = new TextField();
            Button sendButton = new Button("Send");
            sendButton.setOnAction(e -> {
                String message = chatInputField.getText();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(CHAT_FILE, true))) {
                    writer.write("Doctor: " + message);
                    writer.newLine();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                if (!message.isEmpty()) {
                    chatHistoryTextArea.appendText("Doctor: " + message + "\n");
                    chatInputField.clear();
                }
            });
            chatLayout.getChildren().addAll(chatHistoryTextArea, chatInputField, sendButton);
            chatLayout.setAlignment(Pos.CENTER);
            Scene chatScene = new Scene(chatLayout, 400, 300);
            chatStage.setScene(chatScene);
            chatStage.initModality(Modality.APPLICATION_MODAL);
            chatStage.show();
        });

        Scene scene = new Scene(mainPane, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadChatHistory(TextArea chatTextArea) {
        try (BufferedReader reader = new BufferedReader(new FileReader(CHAT_FILE))) {
            String line;
            StringBuilder chatHistory = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                chatHistory.append(line).append("\n");
            }
            chatTextArea.setText(chatHistory.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}