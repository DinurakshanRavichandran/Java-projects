package com.example.javacourseworkcm1606;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import java.io.IOException;

public class MainPageController {
    @FXML
    public ImageView staticImageView;
    private Stage stage;
    public static boolean randomSpotLightSelection = false;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    public void initialize()
    {
       String imagePath = getClass().getResource("/Images/MainPageImage.jpg").toExternalForm();
       Image image = new Image(imagePath);
       staticImageView.setImage(image);


    }


    public void addProjectsButtonClick(ActionEvent event) {
        if (randomSpotLightSelection) {
            showAlert("Warning", "Random spotlight selection already completed. You can't add projects.");
            return;
        }

        try {
            FXMLLoader addProjectLoader = new FXMLLoader(getClass().getResource("addDetails-view.fxml"));
            Parent addProjectRoot = addProjectLoader.load();
            Scene addProjectScene = new Scene(addProjectRoot, 595, 488);

            AddDetailsController addProjectController = addProjectLoader.getController();
            addProjectController.setStage(stage); // Pass the stage to the controller if needed

            stage.setScene(addProjectScene);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to open the Add Project Details page.");
        }
    }

    @FXML
    private void updateProjectsButtonClick(ActionEvent event) {
        if (randomSpotLightSelection) {
            showAlert("Warning", "Random spotlight selection already completed. You can't update projects.");
            return;
        }
        try {
            FXMLLoader updateProjectLoader = new FXMLLoader(getClass().getResource("updateDetails-view.fxml"));
            Parent updateProjectRoot = updateProjectLoader.load();
            Scene updateProjectScene = new Scene(updateProjectRoot, 650, 477);

            UpdateDetailsController updateProjectController = updateProjectLoader.getController();
            updateProjectController.setStage(stage); // Pass the stage reference if needed

            stage.setScene(updateProjectScene);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to open the Update Project Details page.");
        }
    }



    public void viewProjectButtonClick(ActionEvent event) {
        if (randomSpotLightSelection) {
            showAlert("Warning", "Random spotlight selection already completed. You can't view projects.");
            return;
        }
        try {
            FXMLLoader viewProjectLoader = new FXMLLoader(getClass().getResource("viewProjects.fxml"));
            Parent viewProjectRoot = viewProjectLoader.load();
            Scene addProjectScene = new Scene(viewProjectRoot, 944, 550);

            ViewProjectsController viewProjectController = viewProjectLoader.getController();
            viewProjectController.setStage(stage); // Pass the stage to the controller if needed

            stage.setScene(addProjectScene);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to open the View Project Details page.");
        }
    }

    public void deleteProjectButtonClick(ActionEvent event) {
        if (randomSpotLightSelection) {
            showAlert("Warning", "Random spotlight selection already completed. You can't delete projects.");
            return;
        }
        try {
            FXMLLoader deleteProjectLoader = new FXMLLoader(getClass().getResource("deleteDetails-view.fxml"));
            Parent deleteProjectRoot = deleteProjectLoader.load();
            Scene deleteProjectScene = new Scene(deleteProjectRoot, 504, 374);

            DeleteDetailsController deleteProjectController = deleteProjectLoader.getController();
            deleteProjectController.setStage(stage); // Pass the stage reference if needed

            stage.setScene(deleteProjectScene);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to open the delete Project Details page.");
        }
        // Implementation for delete button
    }

    public void saveProjectsButtonClick(ActionEvent event) {
        ProjectManager projectManager = ProjectManager.getInstance();
        projectManager.saveToFile();
        showAlert("Success", "Project Successfully saved.");
    }

    public void randomSpotlightButtonClick(ActionEvent event) {

        try {
            randomSpotLightSelection = true;
            FXMLLoader spotLightSelectionLoader = new FXMLLoader(getClass().getResource("RandomSpotLightSelection.fxml"));
            Parent randomSpotLightRoot = spotLightSelectionLoader.load();
            Scene randomSpotLightSceneScene = new Scene(randomSpotLightRoot, 864, 489);

            RandomSpotlightSelectionController randomSpotlightSelectionController = spotLightSelectionLoader.getController();
            randomSpotlightSelectionController.setStage(stage); // Pass the stage reference if needed

            stage.setScene(randomSpotLightSceneScene);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to open the delete Project Details page.");
        }
        // Implementation for random spotlight button
    }

    public void awardWinningProjectsButtonClick(ActionEvent event) {
        try {
            FXMLLoader awardWinningProjectLoader = new FXMLLoader(getClass().getResource("awardWinningProject-view.fxml"));
            Parent awardWinningProjectRoot = awardWinningProjectLoader.load();
            Scene awardWinningProjectScene = new Scene(awardWinningProjectRoot, 600, 400);

            AwardWinningProjectController awardWinningProjectController = awardWinningProjectLoader.getController();
            awardWinningProjectController.setStage(stage); // Pass the stage reference if needed

            stage.setScene(awardWinningProjectScene);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to open the Awarding Projects  page.");
        }
        // Implementation for award-winning projects button
    }

    public void visualizeButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("visualize-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            VisualizingAwardWinningProjectsController controller = loader.getController();
            controller.setStage(stage); // Pass the stage to the controller

            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the visualization page.");
        }
        // Implementation for visualize button
    }

    public void exitButtonClick(ActionEvent event) {
        System.exit(0); // Simple exit application action
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}