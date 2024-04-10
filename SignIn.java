import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SignIn extends Application {

    private static final String USER_DATA_FILE = "userdata.txt";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("HealHub Sign-In");

        VBox leftSide = new VBox();
        leftSide.setMinWidth(200);
        leftSide.setBackground(new Background(new BackgroundFill(Color.rgb(243, 22, 213), CornerRadii.EMPTY, Insets.EMPTY)));

        VBox rightSide = new VBox(10);
        rightSide.setAlignment(Pos.CENTER);
        rightSide.setPadding(new Insets(10, 10, 10, 10));
        rightSide.setPrefWidth(600);
        rightSide.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        Label signInLabel = new Label("Sign In");

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
            if (email.isEmpty() || password.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all the required fields.");
                alert.showAndWait();
             } else if (authenticate(email, password)) {
                showPortal(primaryStage);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Incorrect email or password.");
                alert.showAndWait();
            }
        });

        Hyperlink signupLink = new Hyperlink("Don't have an account? Sign Up");
        signupLink.setOnAction(e -> {
            SignUp signUpPage = new SignUp();
            try {
                signUpPage.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        rightSide.getChildren().addAll(signInLabel, gridPane, loginButton, signupLink);

        HBox hbox = new HBox();
        hbox.setFillHeight(true);
        hbox.getChildren().addAll(leftSide, rightSide);

        Scene scene = new Scene(hbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean authenticate(String email, String password) {
        File file = new File(USER_DATA_FILE);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[1].equals(email) && parts[2].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    void showPortal(Stage primaryStage) {
        Button doctorsPortalButton = new Button("Doctor's Portal");
        doctorsPortalButton.setOnAction(e -> {
            DoctorView doctorView = new DoctorView();
            try {
                doctorView.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button nursesPortalButton = new Button("Nurse's Portal");
        // Set action for Nurse's Portal button if needed

        Button patientsPortalButton = new Button("Patient's Portal");
        patientsPortalButton.setOnAction(e -> {
            PatientView patientView = new PatientView();
            try {
                patientView.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox portalButtons = new VBox(20, doctorsPortalButton, nursesPortalButton, patientsPortalButton);
        portalButtons.setAlignment(Pos.CENTER);

        Scene portalScene = new Scene(portalButtons, 400, 300);
        primaryStage.setScene(portalScene);
        primaryStage.setTitle("Portal Selection");
    }
}