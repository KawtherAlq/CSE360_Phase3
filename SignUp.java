package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SignUp {
    private Stage stage;
    private Runnable onSignInRequested; // This Runnable will be used to switch back to the SignIn page

    public SignUp(Stage stage, Runnable onSignInRequested) {
        this.stage = stage;
        this.onSignInRequested = onSignInRequested;
    }

    public Scene getScene(Main mainApp) {
        VBox leftSide = new VBox();
        leftSide.setMinWidth(200);
        leftSide.setBackground(new Background(new BackgroundFill(Color.rgb(243, 222, 213), CornerRadii.EMPTY, Insets.EMPTY)));
        
        Image logoImage = new Image(getClass().getResourceAsStream("logo.png"));
        ImageView logoView = new ImageView(logoImage);

//        // Optionally, set the size of the logo
        logoView.setFitHeight(200); // Set height
        logoView.setPreserveRatio(true); // Preserve ratio
        VBox.setMargin(logoView, new Insets(180, 100, 20, 30));
        leftSide.getChildren().add(logoView);
        
        VBox rightSide = new VBox(10);
        rightSide.setAlignment(Pos.CENTER);
        rightSide.setPadding(new Insets(10, 10, 10, 10));
        rightSide.setPrefWidth(600);
        rightSide.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        Label signUpLabel = new Label("Sign Up");

        HBox roleBox = new HBox(10);
        roleBox.setAlignment(Pos.CENTER);
        Button patientButton = new Button("Patient");
        patientButton.setStyle("-fx-background-color: #B1D3FB");
        Button adminButton = new Button("Admin");
        adminButton.setStyle("-fx-background-color: #B1D3FB");
        roleBox.getChildren().addAll(patientButton, adminButton);

        GridPane gridPane = new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter name");

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPromptText("Enter email");

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");

        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(emailLabel, 0, 1);
        gridPane.add(emailField, 1, 1);
        gridPane.add(passwordLabel, 0, 2);
        gridPane.add(passwordField, 1, 2);

        Button signUpButton = new Button("Sign Up");
        signUpButton.setStyle("-fx-background-color: #B1D3FB"); // Baby blue
        signUpButton.setOnAction(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            
            // Check if all fields are filled
            if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                String userInfo = String.format("Name: %s, Email: %s, Password: %s%n", name, email, password);

                try {
                    String filePath = "users.txt";
                    
                    Files.write(Paths.get(filePath), userInfo.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    
                    showAlert(Alert.AlertType.INFORMATION, "Registration Successful", "Your information has been saved.");
                
                if (onSignInRequested != null) {
                    onSignInRequested.run();
                }
                }
                catch (IOException ex) {
                	
                    ex.printStackTrace();
                    // Show error alert
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to Signup");
                }
            } else {
                showAlert(Alert.AlertType.WARNING,  "Incomplete", "Please fill in all fields.");
            }
        });
        

        rightSide.getChildren().addAll(signUpLabel, roleBox, gridPane, signUpButton);
        
        
        HBox hbox = new HBox();
        hbox.setFillHeight(true);
        hbox.getChildren().addAll(leftSide, rightSide);
        return new Scene(hbox, 800, 600);
    }
        
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
  
        }
    