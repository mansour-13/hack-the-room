package test.server.demo.user;

public class UserDTO {
    private String username;
    private int id_learnObject;
    private int score;

    public UserDTO() {
    }

    public UserDTO(String username, int id_learnObject, int score) {
        this.username = username;
        this.id_learnObject = id_learnObject;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId_learnObject() {
        return id_learnObject;
    }

    public void setId_learnObject(int id_learnObject) {
        this.id_learnObject = id_learnObject;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
