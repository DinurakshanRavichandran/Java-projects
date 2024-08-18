package com.example.javacourseworkcm1606;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;


public class ProjectManager {
    // the list that will store all the project objects created
    private List<Project> projects;
    //creation of a project manager object and made private
    private static ProjectManager instance;
    // assingning a list to the object
    private ProjectManager()
    {
        projects = new ArrayList<>();
    }
    // instance method to create singleton pattern across the classes
    public static ProjectManager getInstance()
    {
        if(instance == null)
        {
            instance = new ProjectManager();
            // load projects from file at the start of the program
            instance.loadFromFile();
        }
        return instance;
    }
    // method to add project to the list
    public void addProject(Project project)
    {
        projects.add(project);
    }
    // method to remove project from the list
    public void removeProject(Project project)
    {
        projects.remove(project);
    }
    // getter for the projects arraylist
    public List<Project> getProjects()
    {
        return projects;
    }
    // method to sort projects by ID
    public void bubbleSortProjectsByID() {
        int n = projects.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (projects.get(j).getProjectID().compareTo(projects.get(j + 1).getProjectID()) > 0) {
                    // Swap projects[j] and projects[j+1]
                    Project temp = projects.get(j);
                    projects.set(j, projects.get(j + 1));
                    projects.set(j + 1, temp);
                }
            }
        }
    }
    // Method to save projects to a file
    public void saveToFile() { // <-- Modified method
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("projects.txt")))) { // <-- Fixed file name
            for (Project project : projects) {
                writer.println("ProjectID: " + project.getProjectID());
                writer.println("ProjectName: " + project.getProjectName());
                writer.println("Category: " + project.getCategory());
                writer.println("TeamMembers: " + project.getTeamMembers());
                writer.println("Description: " + project.getBriefDescription());
                writer.println("Country: " + project.getCountry());
                writer.println("TeamLogoPath: " + project.getTeamLogoPath());
                writer.println("---"); // <-- Project delimiter
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load projects from a file in a custom text format
    public void loadFromFile() { // <-- Added method
        try (Scanner scanner = new Scanner(new File("projects.txt"))) { // <-- Fixed file name
            projects.clear();
            Project project = null;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("ProjectID: ")) {
                    if (project != null) {
                        projects.add(project);
                    }
                    project = new Project();
                    project.setProjectID(line.substring(11));
                } else if (line.startsWith("ProjectName: ")) {
                    project.setProjectName(line.substring(13));
                } else if (line.startsWith("Category: ")) {
                    project.setCategory(line.substring(10));
                } else if (line.startsWith("TeamMembers: ")) {
                    project.setTeamMembers(line.substring(13));
                } else if (line.startsWith("Description: ")) {
                    project.setBriefDescription(line.substring(13));
                } else if (line.startsWith("Country: ")) {
                    project.setCountry(line.substring(9));
                } else if (line.startsWith("TeamLogoPath: ")) {
                    project.setTeamLogoPath(line.substring(14));
                } else if (line.equals("---")) {
                    if (project != null) {
                        projects.add(project);
                        project = null;
                    }
                }
            }
            if (project != null) { // Add the last project if the file doesn't end with "---"
                projects.add(project);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

}
    // method to check if project id exists
    public boolean projectIdExists(String projectId) {
        for (Project project : projects) {
            if (project.getProjectID().equals(projectId)) {
                return true;
            }
        }
        return false;
    }

    public Map<String, List<Project>> getProjectsByCategory() {
        return projects.stream().collect(Collectors.groupingBy(Project::getCategory));
    }
}
