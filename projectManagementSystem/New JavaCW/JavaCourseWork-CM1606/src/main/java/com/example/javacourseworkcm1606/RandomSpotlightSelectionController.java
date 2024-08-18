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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomSpotlightSelectionController {
    @FXML
    public AnchorPane spotLightTable;
    @FXML
    public TableColumn<Project,String> CategoryField;
    @FXML
    public TableColumn<Project,String> projectIdField;
    @FXML
    public TableColumn<Project,String> projectNameField;
    @FXML
    public TableColumn<Project,String> teamMembersField;
    @FXML
    public TableColumn<Project,String> descriptionField;
    @FXML
    public TableColumn<Project,String> countryField;
    @FXML
    public TableColumn<Project, ImageView> logoField;
    @FXML
    public TableView<Project> randomSpotLight;
    @FXML
    public Button randomSpotlightButton;

    @FXML
    public void onHomeButtonClick(ActionEvent event) {
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
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    ProjectManager projectManager = ProjectManager.getInstance();
    @FXML
    public void initialize() {


        CategoryField.setCellValueFactory(new PropertyValueFactory<>("category"));
        projectIdField.setCellValueFactory(new PropertyValueFactory<>("projectID"));
        projectNameField.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        teamMembersField.setCellValueFactory(new PropertyValueFactory<>("teamMembers"));
        descriptionField.setCellValueFactory(new PropertyValueFactory<>("briefDescription"));
        countryField.setCellValueFactory(new PropertyValueFactory<>("country"));
        logoField.setCellValueFactory(new PropertyValueFactory<>("logoImageView"));
    }
    @FXML
    public void randomSpotlightButtonClick(ActionEvent event) {
        Random random = new Random();
        ObservableList<Project> selectedProjects = FXCollections.observableArrayList();

        Map<String, List<Project>> projectsByCategory = projectManager.getProjectsByCategory();

        for (List<Project> projects : projectsByCategory.values()) {
            if (!projects.isEmpty()) {
                Project randomProject = projects.get(random.nextInt(projects.size()));
                selectedProjects.add(randomProject);
            }

    }
        ProjectSelectionManager.getInstance().setSelectedProjects(selectedProjects); // <--- Change: Store selected projects
        randomSpotLight.setItems(selectedProjects);

}
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
