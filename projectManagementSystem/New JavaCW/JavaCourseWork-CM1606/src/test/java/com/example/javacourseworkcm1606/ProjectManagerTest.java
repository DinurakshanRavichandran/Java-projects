package com.example.javacourseworkcm1606;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProjectManagerTest {

    private ProjectManager projectManager;

    @BeforeEach
    void setUp() {
        projectManager = ProjectManager.getInstance();
        projectManager.getProjects().clear();  // Clear any existing projects
    }

    @Test
    void testAddProject() {
        Project project = new Project();
        project.setProjectID("1");
        projectManager.addProject(project);

        assertEquals(1, projectManager.getProjects().size());
        assertEquals("1", projectManager.getProjects().get(0).getProjectID());
    }

    @Test
    void testRemoveProject() {
        Project project = new Project();
        project.setProjectID("1");
        projectManager.addProject(project);
        projectManager.removeProject(project);

        assertTrue(projectManager.getProjects().isEmpty());
    }

    @Test
    void testGetProjects() {
        Project project1 = new Project();
        project1.setProjectID("1");
        Project project2 = new Project();
        project2.setProjectID("2");
        projectManager.addProject(project1);
        projectManager.addProject(project2);

        List<Project> projects = projectManager.getProjects();
        assertEquals(2, projects.size());
    }

    @Test
    void testBubbleSortProjectsByID() {
        Project project1 = new Project();
        project1.setProjectID("2");
        Project project2 = new Project();
        project2.setProjectID("1");
        projectManager.addProject(project1);
        projectManager.addProject(project2);

        projectManager.bubbleSortProjectsByID();

        assertEquals("1", projectManager.getProjects().get(0).getProjectID());
        assertEquals("2", projectManager.getProjects().get(1).getProjectID());
    }

    @Test
    void testSaveToFile() throws IOException {
        Project project = new Project();
        project.setProjectID("1");
        project.setProjectName("Test Project");
        project.setCategory("Test Category");
        project.setTeamMembers("Member1, Member2");
        project.setBriefDescription("Test Description");
        project.setCountry("Test Country");
        project.setTeamLogoPath("/path/to/logo.png");
        projectManager.addProject(project);
        projectManager.saveToFile();

        File file = new File("projects.txt");
        assertTrue(file.exists());
        assertTrue(file.length() > 0);

        // Clean up
        file.delete();
    }

    @Test
    void testLoadFromFile() throws IOException {
        // Create a test file
        File file = new File("projects.txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("ProjectID: 1\n");
            writer.write("ProjectName: Test Project\n");
            writer.write("Category: Test Category\n");
            writer.write("TeamMembers: Member1, Member2\n");
            writer.write("Description: Test Description\n");
            writer.write("Country: Test Country\n");
            writer.write("TeamLogoPath: /path/to/logo.png\n");
            writer.write("---\n");
        }

        projectManager.loadFromFile();

        List<Project> projects = projectManager.getProjects();
        assertEquals(1, projects.size());
        assertEquals("1", projects.get(0).getProjectID());
        assertEquals("Test Project", projects.get(0).getProjectName());

        // Clean up
        file.delete();
    }

    @Test
    void testGetProjectsByCategory() {
        Project project1 = new Project();
        project1.setProjectID("1");
        project1.setCategory("Category1");
        Project project2 = new Project();
        project2.setProjectID("2");
        project2.setCategory("Category1");
        Project project3 = new Project();
        project3.setProjectID("3");
        project3.setCategory("Category2");
        projectManager.addProject(project1);
        projectManager.addProject(project2);
        projectManager.addProject(project3);

        Map<String, List<Project>> projectsByCategory = projectManager.getProjectsByCategory();

        assertEquals(2, projectsByCategory.get("Category1").size());
        assertEquals(1, projectsByCategory.get("Category2").size());
    }
    @Test
    void testProjectIdExists() {
        Project project1 = new Project();
        project1.setProjectID("1");
        Project project2 = new Project();
        project2.setProjectID("2");
        projectManager.addProject(project1);
        projectManager.addProject(project2);

        assertTrue(projectManager.projectIdExists("1"));
        assertTrue(projectManager.projectIdExists("2"));
        assertFalse(projectManager.projectIdExists("3"));
    }

}
