package test.server.demo.learnObjekt;

import jakarta.persistence.*;
import test.server.demo.Images.Image;

import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "learn_Objects", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "name"})})
public class LearnObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true)
    private String name;
    private String theorie;

    @ElementCollection
    @CollectionTable(name = "learn_object_stories", joinColumns = @JoinColumn(name = "learn_object_id"))
    @Column(name = "story")
    private List<String> story;

    private String solution;
    private String task;
    private int timeLimit;

    private String image;

    public LearnObject() {
    }

    public LearnObject(int id, String name, String theorie, List<String> story, String solution, String task, int timeLimit, String image) {
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


    public List<String> getStory() {
        return story;
    }


    public String getSolution() {
        return solution;
    }


    public String getTask() {
        return task;
    }


    public int getTimeLimit() {
        return timeLimit;
    }


    public String getImage() {
        return image;
    }


}
