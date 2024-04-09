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

public class SignIn {
	private Stage stage;
	private Authenticator authenticator = new Authenticator();
	private Runnable onSignUpRequested;

	public SignIn(Stage stage, Runnable onSignUpRequested) {
	    this.stage = stage;
	    this.onSignUpRequested = onSignUpRequested;
	}
    public Scene getScene() {
        VBox leftSide = new VBox();
        leftSide.setMinWidth(150);
        leftSide.setBackground(new Background(new BackgroundFill(Color.rgb(243, 222, 213), CornerRadii.EMPTY, Insets.EMPTY)));
        
        Image logoImage = new Image(getClass().getResourceAsStream("logo.png"));
        ImageView logoView = new ImageView(logoImage);

        

        logoView.setFitHeight(200); 
        logoView.setPreserveRatio(true); 
        VBox.setMargin(logoView, new Insets(180, 100, 20, 30));// Top, Right, Bottom, Left padding
        leftSide.getChildren().add(logoView);
        
        VBox rightSide = new VBox(10);
        rightSide.setAlignment(Pos.CENTER);
        rightSide.setPadding(new Insets(10, 10, 10, 10));
        rightSide.setPrefWidth(600);
        rightSide.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));


        Label signInLabel = new Label("Sign In");

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

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPromptText("Enter email");

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");

        gridPane.add(emailLabel, 0, 0);
        gridPane.add(emailField, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);

        Button signInButton = new Button("Login");
        signInButton.setStyle("-fx-background-color: #B1D3FB");

        signInButton.setOnAction(e -> {
            boolean isAuthenticated = authenticator.authenticate(emailField.getText(), passwordField.getText());
            if (isAuthenticated) {
                showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Incorrect email or password.");
            }
        });
        
        Button signUpButton = new Button("Sign up");
        signUpButton.setOnAction(e -> onSignUpRequested.run());

        rightSide.getChildren().addAll(signInLabel,roleBox);
        rightSide.getChildren().addAll( gridPane, signInButton, signUpButton);

        
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
