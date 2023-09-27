package test.server.demo.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.Arrays;


@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "userName"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true)
    @NotEmpty(message = "Username cannot be empty")
    private String userName;
    @NotEmpty(message = "Username cannot be empty")
    private String password;
    private boolean active;
    private String roles;
    private int idxActualLearnObject = 1;
    private int life = 3;
    private int score = 0;
    private int levelscore[] = new int[6];
    private String profileImage;

    public User(int id, String userName, String password, boolean active, String roles, int idxActualLearnObject, int life, int score, int[] levelscore, String profileImage) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.idxActualLearnObject = idxActualLearnObject;
        this.life = life;
        this.score = score;
        this.levelscore = levelscore;
        this.profileImage = profileImage;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public User() {
        // Per default every user receivers normal rights
        // Make sure that you prefix with ROLE_
        // Add ",ROLE_ADMIN" for additional admin rights
        this.roles = "ROLE_USER";
        // Per default each user is active
        this.active = true;
        Arrays.fill(levelscore, 0);
    }

    public User(int id, String userName, String password, boolean active, String roles, int idxActualLearnObject, int life, int score) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.idxActualLearnObject = idxActualLearnObject;
        this.life = life;
        this.score = score;
        Arrays.fill(levelscore, 0);
    }

    public User(int id, String userName, String password, boolean active, String roles, int idxActualLearnObject, int life) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.idxActualLearnObject = idxActualLearnObject;
        this.life = life;
        Arrays.fill(levelscore, 0);
    }

    private void computeScore() {
        this.score = 0;
        for (int i = 0; i < this.idxActualLearnObject; i++) {
            this.score += levelscore[i];
        }
    }

    public void setLevelscore(int[] levelscore) {
        this.levelscore = levelscore;
        computeScore();
    }

    public void setLevelScore(int idx, int content) {
        this.levelscore[idx] = content;
        computeScore();
    }

    public void deleteLevelScore(int idx) {
        this.levelscore[idx] = 0;
        computeScore();
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIdxActualLearnObject() {
        return idxActualLearnObject;
    }

    public void setIdxActualLearnObject(int idxActualLearnObject) {
        this.idxActualLearnObject = idxActualLearnObject;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
