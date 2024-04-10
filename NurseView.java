package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;

public class NurseView extends Application {
    private static final String CHAT_FILE = "chat.txt";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Nurse's View");

        StackPane root = Nurse(primaryStage);

        Scene scene = new Scene(root, 800, 510);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private StackPane Nurse(Stage primaryStage) {
        Button notificationButton;
        Button callButton;
        Button saveButton;
        Button finishButton;
        Button selectPatientButton;
        Button backButton;

        ComboBox<String> patientHistoryComboBox;

        Label nameLabel;
        Label emailLabel;
        Label phoneLabel;
        Label addressLabel;
        Label callLabel;
        Label patientRecords;
        Label patientNotes;
        Label nameBoxLabel;
        Label emailBoxLabel;
        Label patientRecordsLabel;

        TextField phoneField;
        TextField addressField;
        TextField nurseNameField;

        TextArea prescriptionsTextArea;

        notificationButton = new Button("!");
        notificationButton.setLayoutX(10);
        notificationButton.setLayoutY(10);
        callButton = new Button("Call");
        callButton.setLayoutX(617);
        callButton.setLayoutY(110);
        saveButton = new Button("Save");
        finishButton = new Button("Finish");
        selectPatientButton = new Button("Select Patient");
        selectPatientButton.setLayoutX(10);
        selectPatientButton.setLayoutY(50);
        backButton = new Button("Back");

        patientHistoryComboBox = new ComboBox<>();
        patientHistoryComboBox.getItems().addAll("All Medical History", "Previous Health Issues", "Previous Prescribed Medications", "History of Immunization", "Other");
        patientHistoryComboBox.setPromptText("Search");
        patientHistoryComboBox.setPrefWidth(300);
        patientHistoryComboBox.setLayoutX(45);
        patientHistoryComboBox.setLayoutY(32);

        nameLabel = new Label("Name: ");
        nameLabel.setLayoutX(80);
        nameLabel.setLayoutY(113);
        emailLabel = new Label("Email: ");
        emailLabel.setLayoutX(83);
        emailLabel.setLayoutY(163);
        phoneLabel = new Label("Phone: ");
        phoneLabel.setLayoutX(315);
        phoneLabel.setLayoutY(113);
        addressLabel = new Label("Address: ");
        addressLabel.setLayoutX(305);
        addressLabel.setLayoutY(163);
        callLabel = new Label("Call Patient: ");
        callLabel.setLayoutX(550);
        callLabel.setLayoutY(113);
        patientRecords = new Label("Patient Records");
        patientRecords.setStyle("-fx-font-size: 12pt; -fx-font-weight: bold;");
        patientRecords.setLayoutX(140);
        patientRecords.setLayoutY(1);
        patientNotes = new Label("Patient Notes");
        patientNotes.setStyle("-fx-font-size: 12pt; -fx-font-weight: bold;");
        patientNotes.setLayoutX(560);
        patientNotes.setLayoutY(1);
        nameBoxLabel = new Label("");
        emailBoxLabel = new Label("");
        patientRecordsLabel = new Label("");

        Rectangle box = new Rectangle(800, 25, Color.GAINSBORO);
        box.setLayoutX(0);
        box.setLayoutY(0);

        Rectangle name = new Rectangle(160, 25, Color.WHITE);
        name.setStroke(Color.LIGHTGRAY);
        name.setStrokeWidth(1.5);
        StackPane nameBox = new StackPane();
        nameBox.getChildren().addAll(name, nameBoxLabel);
        nameBox.setLayoutX(120);
        nameBox.setLayoutY(110);

        Rectangle email = new Rectangle(160, 25, Color.WHITE);
        email.setStroke(Color.LIGHTGRAY);
        email.setStrokeWidth(1.5);
        StackPane emailBox = new StackPane();
        emailBox.getChildren().addAll(email, emailBoxLabel);
        emailBox.setLayoutX(120);
        emailBox.setLayoutY(160);

        Rectangle RecordsBox = new Rectangle(300, 198, Color.WHITE);
        RecordsBox.setStroke(Color.LIGHTGRAY);
        RecordsBox.setStrokeWidth(1.5);
        patientRecordsLabel.setWrapText(true);
        Label patientRecordsText = new Label();
        patientRecordsText.setWrapText(true);
        patientRecordsText.setPrefWidth(285);
        patientRecordsText.setLayoutX(50);
        patientRecordsText.setLayoutY(60);

        phoneField = new TextField();
        phoneField.setLayoutX(355);
        phoneField.setLayoutY(110);
        addressField = new TextField();
        addressField.setLayoutX(355);
        addressField.setLayoutY(160);
        nurseNameField = new TextField("Nurse's Name");
        nurseNameField.setLayoutX(630);
        nurseNameField.setLayoutY(10);

        prescriptionsTextArea = new TextArea();
        prescriptionsTextArea.setPromptText("Type Here");
        prescriptionsTextArea.setLayoutX(455);
        prescriptionsTextArea.setLayoutY(32);
        prescriptionsTextArea.setPrefSize(300, 223);

        VBox chatBox = new VBox(10);
        chatBox.setPrefWidth(200);
        chatBox.setPrefHeight(200);
        chatBox.setStyle("-fx-background-color: white;");

        HBox buttonsBox = new HBox(10); // Spacing between buttons
        buttonsBox.getChildren().addAll(backButton, saveButton, finishButton);
        buttonsBox.setLayoutX(590);
        buttonsBox.setLayoutY(260);

        Pane topPane = new Pane();
        topPane.setStyle("-fx-background-color: #F4DED6;");
        topPane.setPrefHeight(225);
        topPane.getChildren().addAll(notificationButton, selectPatientButton, nameLabel, nameBox, emailLabel, emailBox, phoneLabel, phoneField, addressLabel, callLabel, callButton, addressField, nurseNameField);

        Pane bottomPane = new Pane();
        bottomPane.setStyle("-fx-background-color: #B1D3FB;");
        bottomPane.setPrefHeight(300);
        bottomPane.getChildren().addAll(patientRecordsText, prescriptionsTextArea, patientHistoryComboBox, buttonsBox, box, patientRecords, patientNotes);

        VBox mainPane = new VBox();
        mainPane.getChildren().addAll(topPane, bottomPane);

        StackPane root = new StackPane();
        root.getChildren().addAll(mainPane);

        // Popup window for chat
        notificationButton.setOnAction(event -> {
            Stage chatStage = new Stage();
            chatStage.setTitle("Chat with Nurse");
            VBox chatLayout = new VBox(10);
            TextArea chatHistoryTextArea = new TextArea();
            chatHistoryTextArea.setEditable(false);
            TextField chatInputField = new TextField();
            Button sendButton = new Button("Send");
            sendButton.setOnAction(e -> {
                // Handle sending message
                String message = chatInputField.getText();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(CHAT_FILE, true))) {
                    writer.write("Nurse: " + message);
                    writer.newLine();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                if (!message.isEmpty()) {
                    // Append the message to chat history
                    chatHistoryTextArea.appendText("Nurse: " + message + "\n");
                    // Save the message to chat.txt
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

        return root;
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
