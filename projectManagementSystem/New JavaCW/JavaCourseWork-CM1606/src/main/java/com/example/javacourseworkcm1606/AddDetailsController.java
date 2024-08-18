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

public class AddDetailsController {
    @FXML
    public TextField projectIdField;
    @FXML
    public TextField projectNameField;
    @FXML
    public TextField categoryField;
    @FXML
    public TextField teamMembersField;
    @FXML
    public TextField descriptionField;
    @FXML
    public ImageView imageView;
    @FXML
    public Button uploadTeamLogoButton;
    @FXML
    public Button addButton;
    @FXML
    public TextField countryField;
    @FXML
    public Button resetButton;

    private Stage stage; // Stage reference

    public String getTeamLogoPath() {
        return teamLogoPath;
    }

    private String teamLogoPath; // Store team logo path

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public Stage getStage() {
        return stage;
    }


    public void onUploadTeamLogoButton(ActionEvent event) {
        //Create file chooser object to select a file
        FileChooser fileChooser = new FileChooser();
        //
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

    public void addButtonClick(ActionEvent event) {
        //Get the strings from the fields
        String projectId = projectIdField.getText();
        String projectName = projectNameField.getText();
        String category = categoryField.getText();
        String teamMembers = teamMembersField.getText();
        String description = descriptionField.getText();
        String country = countryField.getText();
        //check if all the fields are filled
        if (!validateFields()){ // <-- Added: Check if logo path is empty
            return;
        }
        ProjectManager projectManager = ProjectManager.getInstance(); // <-- Added: Get instance of ProjectManager
        // Check if the project ID already exists
        if (projectManager.projectIdExists(projectId)) {
            showAlert("Validation Error", "Project ID already exists. Please use a different ID.");
            return;
        }
        //Create new project object
        Project project = new Project(projectId, projectName, category, teamMembers, description, country, teamLogoPath);
        //Get instance of project manager

        //add project to the list
        projectManager.addProject(project);//<-- Added: Add project to ProjectManager
        //save to file
        projectManager.saveToFile();

        // Optionally, reset fields after adding
        resetFields();

        // Show success message
        showAlert("Success", "Project added successfully!");
        // Load and switch to the main page
        //logic to travel back to main page controller
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
    }



    public void resetButtonClick(ActionEvent event) {
        resetFields();
    }

    private boolean validateFields() {
        if (projectIdField.getText().isEmpty() ||
                projectNameField.getText().isEmpty() ||
                categoryField.getText().isEmpty() ||
                teamMembersField.getText().isEmpty() ||
                descriptionField.getText().isEmpty() ||
                countryField.getText().isEmpty() ||
                teamLogoPath == null || teamLogoPath.isEmpty()) {

            showAlert("Validation Error", "Please fill in all fields and upload a team logo.");
            return false;
        }
        return true;
    }


    public void resetFields() {
        projectIdField.setText("");
        projectNameField.setText("");
        categoryField.setText("");
        teamMembersField.setText("");
        descriptionField.setText("");
        countryField.setText("");
        imageView.setImage(null);
        teamLogoPath = null; // <-- Added: Clear team logo path
    }

    public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void onBackToHomeButtonClick(ActionEvent event) {
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
    }
}
