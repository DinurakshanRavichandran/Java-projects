package com.example.javacourseworkcm1606;



import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class Project {
    // declaration of instance variables
    private String projectID;
    private String projectName;
    private String category;
    private String teamMembers;
    private String briefDescription;
    private String country;
    private String teamLogoPath;
    private int totalPoints;// <--- Change: Add a new instance variable for total points

    //default constructor
    public Project()
    {

    }

    public ImageView getLogoImageView() {
        //System.out.println("getLogoImageView() called for project: " + projectName);
        Image image = new Image("file:" + teamLogoPath);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        return imageView;
    }

    //constructor with parameters
    public Project(String projectID, String projectName, String category, String teamMembers,
                   String briefDescription, String country, String teamLogoPath)
    {
        this.projectID = projectID;
        this.projectName = projectName;
        this.category = category;
        this.teamMembers = teamMembers;
        this.briefDescription = briefDescription;
        this.country = country;
        this.teamLogoPath = teamLogoPath;
        this.totalPoints = 0; // <--- Change: Initialize totalPoints to 0
    }

    // Setter for projectID
    public void setProjectID(String projectID)
    {
        this.projectID = projectID;
    }
    // Setter for totalPoints
    public void setTotalPoints(int totalPoints) { // <--- Change: Add a setter for totalPoints
        this.totalPoints = totalPoints;
    }
    // Setter for projectName
    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }
    //Setter for category
    public void setCategory(String category)

    {
        this.category = category;
    }
    //Setter for teamMembers
    public void setTeamMembers(String teamMembers)
    {
        this.teamMembers = teamMembers;
    }
    //Setter for briefDescription
    public void setBriefDescription(String briefDescription)
    {
        this.briefDescription = briefDescription;
    }
    //Setter for country
    public void setCountry(String country)
    {
        this.country = country;
    }
    //Setter for teamLogo
    public void setTeamLogoPath(String teamLogoPath)
    {
        this.teamLogoPath = teamLogoPath;
    }

    // Getter for projectID
    public String getProjectID()
    {
        return projectID;
    }
    //Getter for projectName
    public String getProjectName()
    {
        return projectName;
    }
    //Getter for category
    public String getCategory()
    {
        return category;
    }
    //Getter for teamMembers
    public String getTeamMembers()
    {
        return teamMembers;
    }
    //Getter for briefDescription
    public String getBriefDescription()
    {
        return briefDescription;
    }
    //Getter for country
    public String getCountry()
    {
        return country;
    }
    //Getter for teamLogoPath
    public String getTeamLogoPath()
    {
        return teamLogoPath;
    }
    //Method to add teamMember
    // Getter for totalPoints
    public int getTotalPoints() { // <--- Change: Add a getter for totalPoints
        return totalPoints;
    }


}

