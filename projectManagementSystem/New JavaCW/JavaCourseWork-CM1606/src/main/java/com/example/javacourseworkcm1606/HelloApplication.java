package com.example.javacourseworkcm1606;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Welcome to the project manager Sarah");

        // Create the scene and add the stylesheet
        Scene mainScene = new Scene(root, 693, 452); // Adjust size as needed
        mainScene.getStylesheets().add(getClass().getResource("/css/mainPage.css").toExternalForm());

        primaryStage.setScene(mainScene);
        primaryStage.show();

        // Set the stage reference in the controller
        MainPageController controller = loader.getController();
        controller.setStage(primaryStage);
    }

    public static void main(String[] args) {
        launch();
    }
}
