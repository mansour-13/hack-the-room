package test.server.demo.learnObjekt;

import jakarta.persistence.*;
import test.server.demo.Images.Image;

import java.sql.Time;

@Entity
@Table(name = "learn_Objects", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "name"})})
public class LearnObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true)
    private String name;
    private String theorie;
    private String story;
    private String solution;
    private String task;
    private Time timeLimit;
    @Lob
    private byte[] image;

    public LearnObject() {
    }

    public LearnObject(int id, String name, String theorie, String story, String solution, String task, Time timeLimit, byte[] image) {
        this.id = id;
        this.name = name;
        this.theorie = theorie;
        this.story = story;
        this.solution = solution;
        this.task = task;
        this.timeLimit = timeLimit;
        this.image = image;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getTheorie() {
        return theorie;
    }


    public String getStory() {
        return story;
    }


    public String getSolution() {
        return solution;
    }


    public String getTask() {
        return task;
    }


    public Time getTimeLimit() {
        return timeLimit;
    }


    public byte[] getImage() {
        return image;
    }


}
