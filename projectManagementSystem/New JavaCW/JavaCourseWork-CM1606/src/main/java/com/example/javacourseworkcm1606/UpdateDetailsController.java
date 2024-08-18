package com.example.javacourseworkcm1606;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UpdateDetailsController {
    @FXML
    public TextField teamMembersField;
    @FXML
    public TextField projectIdField;
    @FXML
    public TextField projectNameField;
    @FXML
    public TextField categoryField;
    @FXML
    public TextField countryField;
    @FXML
    public ImageView imageView;
    @FXML
    public Button loadButton;
    @FXML
    public TextField descriptionField;
    private String teamLogoPath; // Store team logo path

    @FXML
    private Button uploadLogoButton; // Make sure this matches the ID in your FXML file
    @FXML
    private Button updateButton; // Make sure this matches the ID in your FXML file
    @FXML
    private Button resetButton; // Make sure this matches the ID in your FXML file

    private Stage stage; // Reference to the stage

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    // Other event handler methods as needed

    @FXML
    public void uploadLogoButtonClick() {

        // Implement logic for handling logo upload
    }

    @FXML
    public void updateButtonClick() {


        String projectId = projectIdField.getText();

        if (projectId == null || projectId.isEmpty()) {
            showAlert("Error", "Project ID cannot be empty.");
            return;
        }

        ProjectManager projectManager = ProjectManager.getInstance();
        List<Project> projects = projectManager.getProjects();

        Project foundProject = null;
        for (Project project : projects) {
            if (project.getProjectID().equals(projectId)) {
                foundProject = project;
                break;
            }
        }

        if (foundProject != null) {
            // Update the project's details with the user input
            foundProject.setProjectName(projectNameField.getText());
            foundProject.setCategory(categoryField.getText());
            foundProject.setTeamMembers(teamMembersField.getText());
            foundProject.setBriefDescription(descriptionField.getText());
            foundProject.setCountry(countryField.getText());
            foundProject.setTeamLogoPath(teamLogoPath);

            // Optionally save changes to a file
            projectManager.saveToFile();

            showAlert("Success", "Project details updated successfully.");
        } else {
            showAlert("Not Found", "Project with ID " + projectId + " is not available.");
        }
        try {
            FXMLLoader mainPageLoader = new FXMLLoader(getClass().getResource("mainPage.fxml")); // <-- Added: Load the main page FXML
            Parent mainPageRoot = mainPageLoader.load(); // <-- Added: Load the main page root

            MainPageController mainPageController = mainPageLoader.getController(); // <-- Added: Get the main page controller
            mainPageController.setStage(stage); // <-- Added: Pass the stage to the main page controller

            Scene mainPageScene = new Scene(mainPageRoot); // <-- Added: Create a new scene with the main page root
            stage.setScene(mainPageScene); // <-- Added: Set the stage to the new scene
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the main page.");
        }
        // Implement logic for updating project details
    }
    @FXML
    public void onUploadTeamLogoButton(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        File selectedFile = fileChooser.showOpenDialog(stage); // Use the stage reference

        if (selectedFile != null) {
            // Store the logo path
            teamLogoPath = selectedFile.getAbsolutePath(); // <-- Changed: Store the absolute path
            imageView.setImage(new Image(selectedFile.toURI().toString())); // Display the image in ImageView
        }
    }

    @FXML
    public void resetButtonClick() {
        resetFields();
        // Implement logic for resetting fields
    }

    public void onLoadButtonClick(ActionEvent event) {
        String projectId = projectIdField.getText(); // <-- Change: Get Project ID from TextField

        if (projectId == null || projectId.isEmpty()) {
            showAlert("Error", "Project ID cannot be empty.");
            return;
        }

        ProjectManager projectManager = ProjectManager.getInstance();
        List<Project> projects = projectManager.getProjects();

        Project foundProject = null;
        for (Project project : projects) {
            if (project.getProjectID().equals(projectId)) {
                foundProject = project;
                break;
            }
        }

        if (foundProject != null) {
            // <-- Change: Populate the text fields with project details
            projectNameField.setText(foundProject.getProjectName());
            categoryField.setText(foundProject.getCategory());
            teamMembersField.setText(foundProject.getTeamMembers());
            descriptionField.setText(foundProject.getBriefDescription());
            countryField.setText(foundProject.getCountry());
            System.out.println(foundProject.getTeamMembers());

            if (foundProject.getTeamLogoPath() != null) {
               Image newImage = new Image("file:/"+foundProject.getTeamLogoPath());
               imageView.setImage(newImage);

            }
        } else {
            showAlert("Not Found", "Project with ID " + projectId + " is not available.");
        }

    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void upoadLogoButtonClick(ActionEvent event) {
    }
    public void resetFields() {
        projectIdField.setText("");
        projectNameField.setText("");
        categoryField.setText("");
        teamMembersField.setText("");
        descriptionField.setText("");
        countryField.setText("");
        imageView.setImage(null);
        //teamLogoPath = null; // <-- Added: Clear team logo path
    }




    public void onBackToHomeButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 693, 452);

            MainPageController mainPageController = loader.getController();
            mainPageController.setStage(stage); // Pass the stage to the controller

            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the main page.");
        }
    }
}
