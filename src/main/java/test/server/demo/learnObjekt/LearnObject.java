package test.server.demo.learnObjekt;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "learn_Objects", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "name"})})
public class LearnObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true)
    private String name;
    @Column(length = 2000)
    private String theory;

    @ElementCollection
    @CollectionTable(name = "learn_object_stories", joinColumns = @JoinColumn(name = "learn_object_id"))
    @Column(name = "story")
    private List<String> story;

    @Column(length = 2000)
    private String solution;
    private String ending;

    @Column(length = 2000)
    private String task;
    private int timeLimit;

    private String image;

    public LearnObject(int id, String name, String theory, List<String> story, String solution, String ending, String task, int timeLimit, String image) {
        this.id = id;
        this.name = name;
        this.theory = theory;
        this.story = story;
        this.solution = solution;
        this.ending = ending;
        this.task = task;
        this.timeLimit = timeLimit;
        this.image = image;
    }

    public LearnObject() {
    }

    public LearnObject(int id, String name, String theory, List<String> story, String solution, String task, int timeLimit, String image) {
        this.id = id;
        this.name = name;
        this.theory = theory;

        this.story = story;
        this.solution = solution;
        this.task = task;
        this.timeLimit = timeLimit;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getEnding() {
        return ending;
    }

    public void setEnding(String ending) {
        this.ending = ending;
    }

    public String getName() {
        return name;
    }


    public String getTheory() {
        return theory;
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
