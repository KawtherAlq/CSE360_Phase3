import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main extends Application {

    private String signUpEmail;
    private String signUpPassword;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("HealHub Sign-In");

        // Create the left side of the split
        VBox leftSide = new VBox();
        leftSide.setMinWidth(200);
        leftSide.setBackground(new Background(new BackgroundFill(Color.rgb(243, 222, 213), CornerRadii.EMPTY, Insets.EMPTY)));

        // Create the right side of the split
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
        Button doctorButton = new Button("Doctor");
        doctorButton.setStyle("-fx-background-color: #B1D3FB");
        Button nurseButton = new Button("Nurse");
        nurseButton.setStyle("-fx-background-color: #B1D3FB");
        roleBox.getChildren().addAll(patientButton, doctorButton, nurseButton);

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

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #B1D3FB");
        loginButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();

            // Read user information from file
            boolean found = false;
            try (BufferedReader reader = new BufferedReader(new FileReader("user_info.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 3 && parts[1].equals(email) && parts[2].equals(password)) {
                        found = true;
                        break;
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            // Check if the email and password match the credentials stored during sign-up
            if (found) {
                // Successful login
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Successfully signed in!");
                alert.showAndWait();
            } else {
                // Display error message for incorrect email or password
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Incorrect email or password!");
                alert.showAndWait();
            }
        });

        Hyperlink signupLink = new Hyperlink("Don't have an account? Sign Up");
        signupLink.setOnAction(e -> {
            SignUp signUpPage = new SignUp(this); // Pass Main instance to SignUp
            try {
                signUpPage.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        rightSide.getChildren().addAll(signInLabel, roleBox, gridPane, loginButton, signupLink);

        HBox hbox = new HBox();
        hbox.setFillHeight(true);
        hbox.getChildren().addAll(leftSide, rightSide);

        Scene scene = new Scene(hbox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to set the sign-up email and password
    public void setSignUpInfo(String email, String password) {
        this.signUpEmail = email;
        this.signUpPassword = password;
    }
}