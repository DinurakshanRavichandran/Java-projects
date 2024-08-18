package com.example.javacourseworkcm1606;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class ViewProjectsController {
    @FXML
    private TableView<Project> projectsTableView;
    @FXML
    private Button backToHomeButton;
    @FXML
    private TableColumn<Project, String> projectIdColumn;
    @FXML
    private TableColumn<Project, String> projectNameColumn;
    @FXML
    private TableColumn<Project, String> categoryColumn;
    @FXML
    private TableColumn<Project, String> teamMembersColumn;
    @FXML
    private TableColumn<Project, String> descriptionColumn;
    @FXML
    private TableColumn<Project, String> countryColumn;
    @FXML
    private TableColumn<Project, String> logoColumn;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void backToHomeButtonClick(ActionEvent event) {
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
        // Implement logic to navigate back to the home page
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void initialize() {
        // Initialize the TableView columns
        projectIdColumn.setCellValueFactory(new PropertyValueFactory<>("projectID"));
        projectNameColumn.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        teamMembersColumn.setCellValueFactory(new PropertyValueFactory<>("teamMembers"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("briefDescription"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        logoColumn.setCellValueFactory(new PropertyValueFactory<>("teamLogoPath"));

        logoColumn.setCellFactory(new Callback<TableColumn<Project, String>, TableCell<Project, String>>() {
            @Override
            public TableCell<Project, String> call(TableColumn<Project, String> param) {
                return new TableCell<Project, String>() {
                    private final ImageView imageView = new ImageView();

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setGraphic(null);
                        } else {
                            Image image = new Image("file:" + item, 50, 50, true, true);
                            imageView.setImage(image);
                            setGraphic(imageView);
                        }
                    }
                };
            }
        });

        // Load project data into the TableView
        loadProjectData();
    }

    private void loadProjectData() {
        ProjectManager projectManager = ProjectManager.getInstance();
        projectManager.bubbleSortProjectsByID(); // <-- Sort projects by ID before loading data
        ObservableList<Project> projectList = FXCollections.observableArrayList(projectManager.getProjects());
        projectsTableView.setItems(projectList);
    }
}
