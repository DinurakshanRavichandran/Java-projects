package com.example.javacourseworkcm1606;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class DeleteDetailsController {
    @FXML
    public TextField projectIdField;
    @FXML
    public Button deleteButton;
    @FXML
    public Button resetButton;
    @FXML
    public Button homeButton;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    public void onDeleteButtonClick(ActionEvent event) {
        String projectId = projectIdField.getText();

        if (projectId == null || projectId.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Project ID is required.");
            return;
        }

        ProjectManager projectManager = ProjectManager.getInstance();
        boolean projectRemoved = false;

        for (Project project : projectManager.getProjects()) {
            if (project.getProjectID().equals(projectId)) {
                projectManager.removeProject(project);
                projectRemoved = true;
                break;
            }
        }

        if (projectRemoved) {
            projectManager.saveToFile();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Project deleted successfully.");
            // Optionally clear the input field and update the UI
            projectIdField.clear();
            try {
                FXMLLoader mainPageLoader = new FXMLLoader(getClass().getResource("mainPage.fxml")); // <-- Added: Load the main page FXML
                Parent mainPageRoot = mainPageLoader.load(); // <-- Added: Load the main page root

                MainPageController mainPageController = mainPageLoader.getController(); // <-- Added: Get the main page controller
                mainPageController.setStage(stage); // <-- Added: Pass the stage to the main page controller

                Scene mainPageScene = new Scene(mainPageRoot); // <-- Added: Create a new scene with the main page root
                stage.setScene(mainPageScene); // <-- Added: Set the stage to the new scene
            } catch (IOException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR,"Error", "Failed to load the main page.");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Project not found.");
        }

    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    public void onResetButtonClick(ActionEvent event) {
        projectIdField.setText("");
    }
    @FXML
    public void backToHomeButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 693, 452);

            MainPageController mainPageController = loader.getController();
            mainPageController.setStage(stage); // Pass the stage to the controller

            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Can't go the main page.");
        }
    }
}
