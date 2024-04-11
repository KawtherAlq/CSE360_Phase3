import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SignUp extends Application {

    private static final String USER_DATA_FILE = "userdata.txt";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("HealHub Sign-Up");
        

        VBox leftSide = new VBox();
        leftSide.setMinWidth(200);
        leftSide.setBackground(new Background(new BackgroundFill(Color.rgb(243, 222, 213), CornerRadii.EMPTY, Insets.EMPTY)));

        VBox rightSide = new VBox(10);
        rightSide.setAlignment(Pos.CENTER);
        rightSide.setPadding(new Insets(10, 10, 10, 10));
        rightSide.setPrefWidth(600);
        rightSide.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        Label signUpLabel = new Label("Sign Up");

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
        
        Label phoneLabel = new Label("Phone:");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Enter phone number");

        Label addressLabel = new Label("Address:");
        TextField addressField = new TextField();
        addressField.setPromptText("Enter address");

        Label emergencyLabel = new Label("Emergency Contact");
        TextField emergencyField = new TextField();
        emergencyField.setPromptText("Enter Emergency Contact");

        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(emailLabel, 0, 1);
        gridPane.add(emailField, 1, 1);
        gridPane.add(passwordLabel, 0, 2);
        gridPane.add(passwordField, 1, 2);
        gridPane.add(phoneLabel, 0, 3);
        gridPane.add(phoneField, 1, 3);
        gridPane.add(addressLabel, 0, 4);
        gridPane.add(addressField, 1, 4);
        gridPane.add(emergencyLabel, 0, 5);
        gridPane.add(emergencyField, 1, 5);

        Button signUpButton = new Button("Sign Up");
        signUpButton.setStyle("-fx-background-color: #B1D3FB");
        signUpButton.setOnAction(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();
            String emergencyContact = emergencyField.getText();
            
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty() || address.isEmpty() || emergencyContact.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all the required fields.");
                alert.showAndWait();
            } else {
                writeUserData(name, email, password, phone, address, emergencyContact);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Congratulations, your account has been successfully created.");
                alert.showAndWait();
                System.out.println("Successfully signed up.");
            }
        });

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #B1D3FB");
        backButton.setOnAction(e -> {
            SignIn signInPage = new SignIn();
            signInPage.start(primaryStage);
        });

        Image callImage = new Image("file:Images/piggy.png");

        // Create an image view to display the image
        ImageView callImageView = new ImageView(callImage);
        callImageView.setFitWidth(125);
        callImageView.setFitHeight(125);

        // Create a StackPane to center the image
        StackPane imageContainer = new StackPane(callImageView);
        leftSide.getChildren().addAll(imageContainer);


        Hyperlink signInLink = new Hyperlink("Already have an account? Sign In");
        signInLink.setOnAction(e -> {
            SignIn signInPage = new SignIn();
            try {
                signInPage.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        rightSide.getChildren().addAll(signUpLabel, gridPane, signUpButton, signInLink, backButton);

        HBox hbox = new HBox();
        hbox.setFillHeight(true);
        hbox.getChildren().addAll(leftSide, rightSide);

        Scene scene = new Scene(hbox, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void writeUserData(String name, String email, String password, String phone, String address, String emergencyContact) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE, true))) {
            writer.write(name + "," + email + "," + password + "," + phone + "," + address + "," + emergencyContact);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
