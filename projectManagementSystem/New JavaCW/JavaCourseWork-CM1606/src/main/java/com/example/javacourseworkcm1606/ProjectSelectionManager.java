package com.example.javacourseworkcm1606;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectSelectionManager {
    private static ProjectSelectionManager instance;
    private ObservableList<Project> selectedProjects;

    private ProjectSelectionManager() {
        selectedProjects = FXCollections.observableArrayList();
    }

    public static ProjectSelectionManager getInstance() {
        if (instance == null) {
            instance = new ProjectSelectionManager();
        }
        return instance;
    }

    public ObservableList<Project> getSelectedProjects() {
        return selectedProjects;
    }

    public void setSelectedProjects(ObservableList<Project> selectedProjects) {
        this.selectedProjects.setAll(selectedProjects);
    }

    public void clearSelectedProjects() {
        this.selectedProjects.clear();
    }

    public void bubbleSortProjectsByTotalPoints() {
        int n = selectedProjects.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (selectedProjects.get(j).getTotalPoints() < selectedProjects.get(j + 1).getTotalPoints()) {
                    // Swap projects[j] and projects[j+1]
                    Project temp = selectedProjects.get(j);
                    selectedProjects.set(j, selectedProjects.get(j + 1));
                    selectedProjects.set(j + 1, temp);
                }
            }
        }
    }

    public List<Project> getTopThreeProjects() {
        bubbleSortProjectsByTotalPoints();
        return selectedProjects.stream().limit(3).collect(Collectors.toList());
    }
}
