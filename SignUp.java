package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SignUp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("HealHub Sign-Up");

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
        });

        Hyperlink signInLink = new Hyperlink("Already have an account? Sign In");
        signInLink.setOnAction(e -> {
            Main signInPage = new Main();
            try {
                signInPage.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //signin logic
        });

        rightSide.getChildren().addAll(signUpLabel, roleBox, gridPane, signUpButton, signInLink);

        // Create the HBox and add the left and right sides
        HBox hbox = new HBox();
        hbox.setFillHeight(true);
        hbox.getChildren().addAll(leftSide, rightSide);

        Scene scene = new Scene(hbox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
