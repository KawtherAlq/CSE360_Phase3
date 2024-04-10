package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NurseView extends Application {
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Nurse's View");
		StackPane root = Nurse(primaryStage);
		
		Scene scene = new Scene(root, 800, 510);
		primaryStage.setTitle("Nurse's View");
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

    //ComboBox<String> selectPatientComboBox;
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
  
    //TextField nameField;
    //TextField emailField;
    TextField phoneField;
    TextField addressField;
    TextField nurseNameField;
    
    Text patientRecordsText;
    
    //Image callImage; 
    //ImageView callImageView;

    //TextArea physicalTestTextArea;
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
	patientRecordsLabel = new Label ("");
        
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
    patientRecordsText = new Text(patientRecordsLabel.getText());
    patientRecordsText.setWrappingWidth(285);
	StackPane patientRecordsBox = new StackPane();
	patientRecordsBox.getChildren().addAll(RecordsBox, patientRecordsText);
	patientRecordsBox.setLayoutX(45);
	patientRecordsBox.setLayoutY(57);
          
	//nameField = new TextField();
	//nameField.setLayoutX(120);
	//nameField.setLayoutY(110);
	//emailField = new TextField();
	//emailField.setLayoutX(120);
	//emailField.setLayoutY(160);
	phoneField = new TextField();
	phoneField.setLayoutX(355);
	phoneField.setLayoutY(110);
	addressField = new TextField();
	addressField.setLayoutX(355);
	addressField.setLayoutY(160);
	nurseNameField = new TextField("Nurse's Name");
	nurseNameField.setLayoutX(630);
	nurseNameField.setLayoutY(10);

	// Load the image
	//callImage = new Image("Images/piggy.png"); 

	// Create an image view to display the image
	//callImageView = new ImageView(callImage);
	//callImageView.setFitWidth(125);
	//callImageView.setFitHeight(125);
	//callImageView.setLayoutX(665);
	//callImageView.setLayoutY(50);

	//physicalTestTextArea = new TextArea();
	//physicalTestTextArea.setPromptText("");
	//physicalTestTextArea.setLayoutX(45);
	//physicalTestTextArea.setLayoutY(57);
	//physicalTestTextArea.setPrefSize(300, 198);
	prescriptionsTextArea = new TextArea();
	prescriptionsTextArea.setPromptText("Type Here");
	prescriptionsTextArea.setLayoutX(455);
	prescriptionsTextArea.setLayoutY(32);
	prescriptionsTextArea.setPrefSize(300, 223);

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
	bottomPane.getChildren().addAll(patientRecordsBox, prescriptionsTextArea, patientHistoryComboBox, buttonsBox, box, patientRecords, patientNotes);

	VBox mainPane = new VBox();
	mainPane.getChildren().addAll(topPane, bottomPane);
	
    notificationButton.setOnAction(e -> {
        VBox popUpWindow = new VBox(10);
        TextArea messageTextArea = new TextArea();
        messageTextArea.setPrefWidth(400);
        messageTextArea.setPrefHeight(200);
        Button sendButton = new Button("Send");
        sendButton.setOnAction(event -> {
            //String message = messageTextArea.getText();
            messageTextArea.clear();
        });
        popUpWindow.getChildren().addAll(messageTextArea, sendButton);
        Scene popup = new Scene(popUpWindow, 420, 205);
        Stage window = new Stage();
        window.setScene(popup);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Nurse's Messages");
        window.show();
    });
	
	callButton.setOnAction(e -> {
		if(phoneField.getText().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Call");
			alert.setHeaderText(null);
			alert.setContentText("No phone number has been entered!");
			alert.showAndWait();
		}
		else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Call");
			alert.setHeaderText(null);
			alert.setContentText("Patient has been called!");
			alert.showAndWait();
		}
    });
	
	saveButton.setOnAction(e -> {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Save");
        alert.setHeaderText(null);
        alert.setContentText("Information has been saved successfully!");
        alert.showAndWait();
    });
	
	finishButton.setOnAction(e -> {
       
        //NurseView main = new NurseView();
        //main.start(primaryStage);
    });
	
	backButton.setOnAction(e -> {
	       
       
    });
	
	
	selectPatientButton.setOnAction(e -> {
		HBox popUpWindow = new HBox(10);
		VBox puWindow = new VBox(10);
		Button enterButton = new Button("Enter");
		Label patientName = new Label("Patient's Name: ");
		TextField patientNameField = new TextField();
		popUpWindow.getChildren().addAll(patientName, patientNameField);
		popUpWindow.setAlignment(Pos.CENTER);
		puWindow.getChildren().addAll(popUpWindow, enterButton);
		puWindow.setAlignment(Pos.CENTER);
	    Scene popup = new Scene(puWindow, 420, 120);
	    Stage window = new Stage();
	    window.setScene(popup);
	    window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Select Patient");
		window.show();
		
		enterButton.setOnAction(this::SaveHandler);
		
    });
	
	try (BufferedReader reader = new BufferedReader(new FileReader("user_info.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if(parts.length >= 3) {
                nameBoxLabel.setText(parts[0]);
                emailBoxLabel.setText(parts[1]);
            }
            if(parts.length > 3) {
            	phoneField.setText(parts[3]);
            	addressField.setText(parts[4]);
            }
        }
    } catch (IOException ex) {
        ex.printStackTrace();
    }
	
        
	StackPane root = new StackPane();
	root.getChildren().addAll(mainPane);
    return root;
	
}

private void SaveHandler(ActionEvent e) {
    Button sourceButton = (Button) e.getSource();
    if(sourceButton.getText() == "Enter") {
    	
    }
}

	public static void main(String[] args) {
		launch(args);
	}
}