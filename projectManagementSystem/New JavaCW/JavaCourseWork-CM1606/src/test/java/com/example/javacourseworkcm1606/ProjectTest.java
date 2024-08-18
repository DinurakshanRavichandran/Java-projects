package com.example.javacourseworkcm1606;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.scene.image.ImageView;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    private Project project;

    @BeforeEach
    public void setUp() {
        project = new Project();
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(project);
    }

    @Test
    public void testParameterizedConstructor() {
        Project paramProject = new Project("1", "Project Name", "Category", "Team Members",
                "Brief Description", "Country", "logo.png");
        assertEquals("1", paramProject.getProjectID());
        assertEquals("Project Name", paramProject.getProjectName());
        assertEquals("Category", paramProject.getCategory());
        assertEquals("Team Members", paramProject.getTeamMembers());
        assertEquals("Brief Description", paramProject.getBriefDescription());
        assertEquals("Country", paramProject.getCountry());
        assertEquals("logo.png", paramProject.getTeamLogoPath());
        assertEquals(0, paramProject.getTotalPoints());
    }

    @Test
    public void testSetProjectID() {
        project.setProjectID("1");
        assertEquals("1", project.getProjectID());
    }

    @Test
    public void testSetProjectName() {
        project.setProjectName("Project Name");
        assertEquals("Project Name", project.getProjectName());
    }

    @Test
    public void testSetCategory() {
        project.setCategory("Category");
        assertEquals("Category", project.getCategory());
    }

    @Test
    public void testSetTeamMembers() {
        project.setTeamMembers("Team Members");
        assertEquals("Team Members", project.getTeamMembers());
    }

    @Test
    public void testSetBriefDescription() {
        project.setBriefDescription("Brief Description");
        assertEquals("Brief Description", project.getBriefDescription());
    }

    @Test
    public void testSetCountry() {
        project.setCountry("Country");
        assertEquals("Country", project.getCountry());
    }

    @Test
    public void testSetTeamLogoPath() {
        project.setTeamLogoPath("logo.png");
        assertEquals("logo.png", project.getTeamLogoPath());
    }

    @Test
    public void testSetTotalPoints() {
        project.setTotalPoints(100);
        assertEquals(100, project.getTotalPoints());
    }


}
