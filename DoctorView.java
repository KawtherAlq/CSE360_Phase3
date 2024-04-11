package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
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
        
        
        Button selectDoctorButton = new Button("Select Doctor");
    	selectDoctorButton.setLayoutX(550);
    	selectDoctorButton.setLayoutY(10);
    	selectDoctorButton.setOnAction(e -> {
    		HBox popUpWindow = new HBox(10);
    		VBox puWindow = new VBox(10);
    		Button enterButton = new Button("Enter");
    		Label doctorName = new Label("Doctor's Name or Email: ");
    		Label errorLabel = new Label("");
    		errorLabel.setTextFill(Color.RED);
    		errorLabel.setStyle("-fx-font-weight: bold;");
    		TextField doctorNameField = new TextField();
    		popUpWindow.getChildren().addAll(doctorName, doctorNameField);
    		popUpWindow.setAlignment(Pos.CENTER);
    		puWindow.getChildren().addAll(popUpWindow, enterButton, errorLabel);
    		puWindow.setAlignment(Pos.CENTER);
    	    Scene popup = new Scene(puWindow, 420, 120);
    	    Stage window = new Stage();
    	    window.setScene(popup);
    	    window.initModality(Modality.APPLICATION_MODAL);
    		window.setTitle("Select Doctor");
    		window.show();
    		
    		enterButton.setOnAction(event -> {
    			int count;
    			char at;
    	        if(doctorNameField.getText().isEmpty()) {
    	        	errorLabel.setText("No email or name was entered");
    	        }
    	        else {
    	        	try (BufferedReader reader = new BufferedReader(new FileReader("userdata.txt"))) {
    	                String line;
    	                count = 0;
    	                at = '@';
    	                while ((line = reader.readLine()) != null) {
    	                    String[] parts = line.split(",");
    	                    for(int i = 0; i < parts.length; i++) {
    	                    		if((doctorNameField.getText().equalsIgnoreCase(parts[i])) && (parts[i].contains(String.valueOf(at)))) {
    	                    			selectDoctorButton.setText("Welcome, Doctor " + parts[i-1] + "!");
    	                    			count = 1;
    	                    			window.close();
    	                    		}
    	                    		else if(doctorNameField.getText().equalsIgnoreCase(parts[i])) {
    	                    			selectDoctorButton.setText("Welcome, Doctor " + parts[i] + "!");
    	                    			count = 1;
    	                    			window.close();
    	                    		}
    	                    }
    	                }
    	                if(count == 0) {
    	                	errorLabel.setText("Name or Email do not exist");
    	                }
    	            } catch (IOException ex) {
    	                ex.printStackTrace();
    	            }
    	        }
    	    });
    		
        });
    	
    	Label nameBoxLabel = new Label("");
    	Label emailBoxLabel = new Label("");
    	Label phoneBoxLabel = new Label("");
    	Label addressBoxLabel = new Label("");
    	Label patientRecordsLabel = new Label ("");

        Button selectPatientButton = new Button("Select Patient");
        selectPatientButton.setLayoutX(10);
        selectPatientButton.setLayoutY(60);
        selectPatientButton.setOnAction(e -> {
    		HBox popUpWindow = new HBox(10);
    		VBox puWindow = new VBox(10);
    		Button enterButton = new Button("Enter");
    		Label patientName = new Label("Patient's Name or Email: ");
    		Label errorLabel = new Label("");
    		errorLabel.setTextFill(Color.RED);
    		errorLabel.setStyle("-fx-font-weight: bold;");
    		TextField patientNameField = new TextField();
    		popUpWindow.getChildren().addAll(patientName, patientNameField);
    		popUpWindow.setAlignment(Pos.CENTER);
    		puWindow.getChildren().addAll(popUpWindow, enterButton, errorLabel);
    		puWindow.setAlignment(Pos.CENTER);
    	    Scene popup = new Scene(puWindow, 420, 120);
    	    Stage window = new Stage();
    	    window.setScene(popup);
    	    window.initModality(Modality.APPLICATION_MODAL);
    		window.setTitle("Select Patient");
    		window.show();
    		
    		enterButton.setOnAction(event -> {
    			int count;
    			char at;
    	        if(patientNameField.getText().isEmpty()) {
    	        	errorLabel.setText("No email or name was entered");
    	        }
    	        else {
    	        	try (BufferedReader reader = new BufferedReader(new FileReader("userdata.txt"))) {
    	                String line;
    	                count = 0;
    	                at = '@';
    	                while ((line = reader.readLine()) != null) {
    	                    String[] parts = line.split(",");
    	                    for(int i = 0; i < parts.length; i++) {
    	                    		if((patientNameField.getText().equalsIgnoreCase(parts[i])) && (parts[i].contains(String.valueOf(at)))) {
    	                    			nameBoxLabel.setText(parts[i-1]);
    	                    			emailBoxLabel.setText(parts[i]);
    	                    			phoneBoxLabel.setText(parts[i+2]);
    	                    			addressBoxLabel.setText(parts[i+3]);
    	                    			count = 1;
    	                    			window.close();
    	                    		}
    	                    		else if(patientNameField.getText().equalsIgnoreCase(parts[i])) {
    	                    			nameBoxLabel.setText(parts[i]);
    	                    			emailBoxLabel.setText(parts[i+1]);
    	                    			phoneBoxLabel.setText(parts[i+3]);
    	                    			addressBoxLabel.setText(parts[i+4]);
    	                    			count = 1;
    	                    			window.close();
    	                    		}
    	                    }
    	                }
    	                if(count == 0) {
    	                	errorLabel.setText("Name or Email do not exist");
    	                }
    	            } catch (IOException ex) {
    	                ex.printStackTrace();
    	            }
    	        }
    	    });
    		
        });
        

        Label nameLabel = new Label("Name: ");
        nameLabel.setLayoutX(80);
        nameLabel.setLayoutY(113);

        Label emailLabel = new Label("Email: ");
        emailLabel.setLayoutX(83);
        emailLabel.setLayoutY(163);

        Label phoneLabel = new Label("Phone: ");
        phoneLabel.setLayoutX(315);
        phoneLabel.setLayoutY(113);
        
        Label addressLabel = new Label("Address: ");
        addressLabel.setLayoutX(305);
        addressLabel.setLayoutY(163);
        
        Label callLabel = new Label("Call Patient: ");
        callLabel.setLayoutX(550);
        callLabel.setLayoutY(113);
        
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
    	
    	Rectangle phone = new Rectangle(160, 25, Color.WHITE);
    	phone.setStroke(Color.LIGHTGRAY);
        email.setStrokeWidth(1.5);
    	StackPane phoneBox = new StackPane();
        phoneBox.getChildren().addAll(phone, phoneBoxLabel);
        phoneBox.setLayoutX(355);
    	phoneBox.setLayoutY(110);
    	
    	Rectangle address = new Rectangle(160, 25, Color.WHITE);
    	address.setStroke(Color.LIGHTGRAY);
        email.setStrokeWidth(1.5);
    	StackPane addressBox = new StackPane();
        addressBox.getChildren().addAll(address, addressBoxLabel);
        addressBox.setLayoutX(355);
    	addressBox.setLayoutY(160);
        
        

        Button callButton = new Button("Call");
        callButton.setLayoutX(617);
        callButton.setLayoutY(110);
        callButton.setOnAction(e -> {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Call");
			alert.setHeaderText(null);
			alert.setContentText("Patient has been called!");
			alert.showAndWait();
    });

        //Label doctorNameLabel = new Label("Doctor's Name");
        //doctorNameLabel.setLayoutX(685);
        //doctorNameLabel.setLayoutY(10);

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
        saveButton.setOnAction(e -> {
            if (!emailBoxLabel.getText().isEmpty()) {
                String emailUser = emailBoxLabel.getText();
                int at = emailUser.indexOf('@');
                String emailUserName = emailUser.substring(0, at);
                String filename = emailUserName + "_Records.txt";
                String append = physicalTestTextArea.getText() +  "\n" + prescriptionsTextArea.getText();
                if (!prescriptionsTextArea.getText().isEmpty() || !physicalTestTextArea.getText().isEmpty()) {
					String outMsg = prescriptionsTextArea.getText();
						try {
							FileWriter fw = new FileWriter(filename);
							BufferedWriter bw = new BufferedWriter(fw);
							PrintWriter outFile = new PrintWriter(bw);
			
							outFile.print(append);
			
							outFile.close();
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
	                        alert.setTitle("Save");
	                        alert.setHeaderText(null);
	                        alert.setContentText("Information has been saved successfully!");
	                        alert.showAndWait();
	                    } 
						catch (IOException ex) {
	                        ex.printStackTrace();
	                    }
                } 
                else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Save");
                    alert.setHeaderText(null);
                    alert.setContentText("No text has been entered, nothing to save");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Save");
                alert.setHeaderText(null);
                alert.setContentText("Information can't be saved if no patient has been selected");
                alert.showAndWait();
            }
        });

        ComboBox<String> patientHistoryComboBox = new ComboBox<>();
        patientHistoryComboBox.getItems().addAll("Patient History", "Previous Health Issues", "Previous Prescribed Medications", "History of Immunization", "Other");
        patientHistoryComboBox.setPromptText("Search");
        patientHistoryComboBox.setLayoutX(280);
        patientHistoryComboBox.setLayoutY(0);
        patientHistoryComboBox.setPrefWidth(240);
        
        Rectangle RecordsBox = new Rectangle(240, 225, Color.WHITE);
    	RecordsBox.setStroke(Color.LIGHTGRAY);
        RecordsBox.setStrokeWidth(1.5);
        patientRecordsLabel.setWrapText(true);
        Text patientRecordsText = new Text(patientRecordsLabel.getText());
        patientRecordsText.setWrappingWidth(230);
    	StackPane patientRecordsBox = new StackPane();
    	patientRecordsBox.getChildren().addAll(RecordsBox, patientRecordsText);
    	patientRecordsBox.setLayoutX(280);
    	patientRecordsBox.setLayoutY(25);
    	
    	
    	patientHistoryComboBox.setOnAction(e -> {
    		patientRecordsText.setText("");
    		patientRecordsText.setFill(Color.BLACK);
    		patientRecordsText.setStyle("-fx-font-weight: normal;");
    		int count = 0;
    		String mainText = "";
    		if((!emailBoxLabel.getText().equals(""))) {
    			String emailUser = emailBoxLabel.getText();
    	        int at = emailUser.indexOf('@');
    	        String emailUserName = emailUser.substring(0, at);
    	        String filename = emailUserName + "_Records.txt";
    	        File file = new File(filename);
    	        if(file.exists()) {

    			if(patientHistoryComboBox.getValue().equals("Patient History")) {
    				try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
    					String line;
    					while ((line = reader.readLine()) != null) {
    						String[] parts = line.split(":");
    						if ((parts.length == 2)) {
    							String category = parts[0].trim();
    							String option = parts[1].trim();
    							mainText = mainText + category  + ": " + option + "\n";
    						}
    					}
    					patientRecordsText.setText(mainText);
    				} 
    				catch (IOException ex) {
    					ex.printStackTrace();
    				}
    			}
    			else if(patientHistoryComboBox.getValue().equals("Previous Health Issues")) {
    				try (BufferedReader reader = new BufferedReader(new FileReader(emailUserName + "_Records.txt"))) {
    					String line;
    					while ((line = reader.readLine()) != null) {
    						String[] parts = line.split(":");
    						if (((parts.length == 2)) && (count == 0)) {
    							String category = parts[0].trim();
    							String option = parts[1].trim();
    							patientRecordsText.setText(category  + ": " + option + "\n");
    						}
    						count++;
    					}
    				} 
    				catch (IOException ex) {
    					ex.printStackTrace();
    				}
    			
    			}
    			else if(patientHistoryComboBox.getValue().equals("History of Immunization")) {
    				try (BufferedReader reader = new BufferedReader(new FileReader(emailUserName + "_Records.txt"))) {
    					String line;
    					while ((line = reader.readLine()) != null) {
    						String[] parts = line.split(":");
    						if (((parts.length == 2)) && (count == 1)) {
    							String category = parts[0].trim();
    							String option = parts[1].trim();
    							patientRecordsText.setText(category  + ": " + option + "\n");
    						}
    						count++;
    					}
    				} 
    					catch (IOException ex) {
    						ex.printStackTrace();
    					}
    			
    			}
    			else if(patientHistoryComboBox.getValue().equals("Previous Prescribed Medications")) {
    				try (BufferedReader reader = new BufferedReader(new FileReader(emailUserName + "_Records.txt"))) {
    					String line;
    					while ((line = reader.readLine()) != null) {
    						String[] parts = line.split(":");
    						if (((parts.length == 2)) && (count == 2)) {
    							String category = parts[0].trim();
    							String option = parts[1].trim();
    							patientRecordsText.setText(category  + ": " + option + "\n");
    						}
    						count++;
    					}
    				} 
    					catch (IOException ex) {
    						ex.printStackTrace();
    					}
    			
    			}
    			else {
    				try (BufferedReader reader = new BufferedReader(new FileReader(emailUserName + "_Records.txt"))) {
    					String line;
    					while ((line = reader.readLine()) != null) {
    						String[] parts = line.split(":");
    						if (((parts.length == 2)) && (count == 3)) {
    							String category = parts[0].trim();
    							String option = parts[1].trim();
    							patientRecordsText.setText(category  + ": " + option + "\n");
    						}
    						count++;
    					}
    				} 
    					catch (IOException ex) {
    						ex.printStackTrace();
    					}
    			
    			}
    		}
    	   else if(!file.exists()) {
    		   	patientRecordsText.setText("No Patient History Exists");
   				patientRecordsText.setFill(Color.RED);
   				patientRecordsText.setStyle("-fx-font-weight: bold;");
    	  }
    		}
    		else {
    			patientRecordsText.setText("No Patient has been selected");
    			patientRecordsText.setFill(Color.RED);
    			patientRecordsText.setStyle("-fx-font-weight: bold;");
    		}
    	});
        
        

        Button finishedButton = new Button("Finished");
        finishedButton.setOnAction(e -> {
            SignIn signInPage = new SignIn();
            signInPage.start(primaryStage);
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            SignIn signInPage = new SignIn();
            signInPage.showPortal(primaryStage);
        });

        HBox buttonsBox = new HBox(10);
        buttonsBox.getChildren().addAll(backButton, saveButton, finishedButton);
        buttonsBox.setLayoutX(560);
        buttonsBox.setLayoutY(260);

        Pane topPane = new Pane();
        topPane.setStyle("-fx-background-color: #F4DED6;");
        topPane.setPrefHeight(225);
        topPane.getChildren().addAll(notificationButton, selectPatientButton, nameLabel, nameBox, emailLabel, emailBox, phoneLabel, phoneBox, addressLabel, callLabel, callButton, addressBox, selectDoctorButton);

        Pane bottomPane = new Pane();
        bottomPane.setStyle("-fx-background-color: #B1D3FB;");
        bottomPane.setPrefHeight(300);
        bottomPane.getChildren().addAll(physicalTestTextArea, patientRecordsBox, prescriptionsTextArea, patientHistoryComboBox, buttonsBox);

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