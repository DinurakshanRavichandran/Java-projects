package com.example.javacourseworkcm1606;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class ProjectSelectionManagerTest {
    private ProjectSelectionManager manager;

    @BeforeEach
    void setUp() {
        manager = ProjectSelectionManager.getInstance();
        manager.clearSelectedProjects();
    }

    @Test
    void testSingletonInstance() {
        ProjectSelectionManager anotherInstance = ProjectSelectionManager.getInstance();
        assertSame(manager, anotherInstance);
    }

    @Test
    void testSetSelectedProjects() {
        ObservableList<Project> projects = FXCollections.observableArrayList(
                new Project("P1", "Project 1", "Category 1", "Team 1", "Description 1", "Country 1", "path/to/logo1"),
                new Project("P2", "Project 2", "Category 2", "Team 2", "Description 2", "Country 2", "path/to/logo2")
        );
        manager.setSelectedProjects(projects);
        assertEquals(2, manager.getSelectedProjects().size());
    }

    @Test
    void testClearSelectedProjects() {
        ObservableList<Project> projects = FXCollections.observableArrayList(
                new Project("P1", "Project 1", "Category 1", "Team 1", "Description 1", "Country 1", "path/to/logo1"),
                new Project("P2", "Project 2", "Category 2", "Team 2", "Description 2", "Country 2", "path/to/logo2")
        );
        manager.setSelectedProjects(projects);
        manager.clearSelectedProjects();
        assertEquals(0, manager.getSelectedProjects().size());
    }

    @Test
    void testBubbleSortProjectsByTotalPoints() {
        ObservableList<Project> projects = FXCollections.observableArrayList(
                new Project("P1", "Project 1", "Category 1", "Team 1", "Description 1", "Country 1", "path/to/logo1"),
                new Project("P2", "Project 2", "Category 2", "Team 2", "Description 2", "Country 2", "path/to/logo2"),
                new Project("P3", "Project 3", "Category 3", "Team 3", "Description 3", "Country 3", "path/to/logo3")
        );

        projects.get(0).setTotalPoints(10);
        projects.get(1).setTotalPoints(20);
        projects.get(2).setTotalPoints(15);

        manager.setSelectedProjects(projects);
        manager.bubbleSortProjectsByTotalPoints();

        List<Project> sortedProjects = manager.getSelectedProjects();
        assertEquals(20, sortedProjects.get(0).getTotalPoints());
        assertEquals(15, sortedProjects.get(1).getTotalPoints());
        assertEquals(10, sortedProjects.get(2).getTotalPoints());
    }

    @Test
    void testGetTopThreeProjects() {
        ObservableList<Project> projects = FXCollections.observableArrayList(
                new Project("P1", "Project 1", "Category 1", "Team 1", "Description 1", "Country 1", "path/to/logo1"),
                new Project("P2", "Project 2", "Category 2", "Team 2", "Description 2", "Country 2", "path/to/logo2"),
                new Project("P3", "Project 3", "Category 3", "Team 3", "Description 3", "Country 3", "path/to/logo3"),
                new Project("P4", "Project 4", "Category 4", "Team 4", "Description 4", "Country 4", "path/to/logo4")
        );

        projects.get(0).setTotalPoints(10);
        projects.get(1).setTotalPoints(20);
        projects.get(2).setTotalPoints(15);
        projects.get(3).setTotalPoints(25);

        manager.setSelectedProjects(projects);
        List<Project> topThreeProjects = manager.getTopThreeProjects();

        assertEquals(3, topThreeProjects.size());
        assertEquals(25, topThreeProjects.get(0).getTotalPoints());
        assertEquals(20, topThreeProjects.get(1).getTotalPoints());
        assertEquals(15, topThreeProjects.get(2).getTotalPoints());
    }
}
