package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showSignInPage();
    }

    public void showSignInPage() {
        SignIn signInPage = new SignIn(primaryStage, this::showSignUpPage);
        primaryStage.setScene(signInPage.getScene());
        primaryStage.setTitle("HealHub - Sign In");
        primaryStage.show();
    }


    public void showSignUpPage() {
        SignUp signUpPage = new SignUp(primaryStage, this::showSignInPage);
        primaryStage.setScene(signUpPage.getScene(this));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
