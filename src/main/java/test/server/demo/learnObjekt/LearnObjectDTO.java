package test.server.demo.learnObjekt;

import test.server.demo.Images.Image;

import java.sql.Time;
import java.util.List;

public class LearnObjectDTO {
    private String name;
    private String theorie;
    private String story;
    private String solution;
    private String task;
    private Time timeLimit;
    private Image image;

    public LearnObjectDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTheorie() {
        return theorie;
    }

    public void setTheorie(String theorie) {
        this.theorie = theorie;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Time getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Time timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Image getImages() {
        return this.image;
    }

    public void setImages(Image images) {
        this.image = image;
    }
}
