package com.example.javacourseworkcm1606;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class AwardWinningProjectController {
    @FXML
    public TextField projectField;
    @FXML
    public TextField categoryField;
    @FXML
    public TextField projectNameField;
    @FXML
    public TextField judge1Field;
    @FXML
    public TextField judge2Field;
    @FXML
    public TextField judge3Field;
    @FXML
    public TextField judge4Field;
    @FXML
    public Button doneButton;
    @FXML
    public Button resetButton;
    @FXML
    public Button backToHomeButton;
    @FXML
    public Button nextButton;

    private ObservableList<Project> projects = FXCollections.observableArrayList();
    private int currentProjectIndex = 0;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {
        projects = ProjectSelectionManager.getInstance().getSelectedProjects(); // <--- Change: Retrieve selected projects
        loadProjectData();
    }

    @FXML
    public void DoneButtonClick(ActionEvent event)
    {
        try {
            int judge1Points = Integer.parseInt(judge1Field.getText());
            int judge2Points = Integer.parseInt(judge2Field.getText());
            int judge3Points = Integer.parseInt(judge3Field.getText());
            int judge4Points = Integer.parseInt(judge4Field.getText());

            if (judge1Points > 5 || judge2Points > 5 || judge3Points > 5 || judge4Points > 5) {
                showAlert("Invalid input", "Points for each judge must not exceed 5.");
                return;
            }

            Project project = projects.get(currentProjectIndex);
            int totalPoints = judge1Points + judge2Points + judge3Points + judge4Points;
            project.setTotalPoints(totalPoints);
            System.out.println(totalPoints);
            System.out.println(project.getProjectName());

            if (currentProjectIndex < projects.size() - 1) {
                currentProjectIndex++;
                loadProjectData();

            }
        } catch (NumberFormatException e) {
            showAlert("Invalid input", "Please enter valid points for all judges.");
        }


    }
    @FXML
    public void ResetButtonClick(ActionEvent event)
    {
        clearFields();
    }
    @FXML
    public void HomeButtonClick(ActionEvent event)
    {
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
    @FXML
    public void nextButtonClick(ActionEvent event)
    {
        if (currentProjectIndex < projects.size() - 1) {
            currentProjectIndex++;
            loadProjectData();
        }
    }
    private void loadProjectData() {
        if (projects.size()==currentProjectIndex) {
            showAlert("No Projects", "No projects available for evaluation.");
            return;
        }

        Project project = projects.get(currentProjectIndex);
        projectField.setText(project.getProjectID());
        categoryField.setText(project.getCategory());
        projectNameField.setText(project.getProjectName());
        clearFields();
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        judge1Field.clear();
        judge2Field.clear();
        judge3Field.clear();
        judge4Field.clear();
    }

}
