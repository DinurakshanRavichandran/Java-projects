package com.example.javacourseworkcm1606;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class VisualizingAwardWinningProjectsController {
    @FXML
    public Button backToHomeButton;
    @FXML
    public Button visualizeButton;
    @FXML
    public NumberAxis YAxis;
    @FXML
    public CategoryAxis XAxis;
    @FXML
    public BarChart<Number, String> barChart;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
        initializeChart();
    }

    private void initializeChart() {
        List<Project> topProjects = ProjectSelectionManager.getInstance().getTopThreeProjects();

        XYChart.Series<Number, String> series = new XYChart.Series<>();
        series.setName("Top 3 Project Scores");

        for (Project project : topProjects) {
            String projectLabel= project.getProjectName()+" "+project.getCountry();
            series.getData().add(new XYChart.Data<>(project.getTotalPoints(), projectLabel));
        }

        barChart.getData().clear();
        barChart.getData().add(series);
    }

    @FXML
    public void onHomeButtonClick(ActionEvent event) {
        try {
            FXMLLoader mainPageLoader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
            Parent mainPageRoot = mainPageLoader.load();

            MainPageController mainPageController = mainPageLoader.getController();
            mainPageController.setStage(stage);

            Scene mainPageScene = new Scene(mainPageRoot);
            stage.setScene(mainPageScene);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the main page.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
