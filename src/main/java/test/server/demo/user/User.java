package test.server.demo.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;


@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "id", "userName" }) })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique=true)
    @NotEmpty(message = "Username cannot be empty")
    private String userName;
    @NotEmpty(message = "Username cannot be empty")
    private String password;
    private boolean active;
    private String roles;
    private int idxActualLearnObject;
    private int life;

    public User() {
        // Per default every user receivers normal rights
        // Make sure that you prefix with ROLE_
        // Add ",ROLE_ADMIN" for additional admin rights
        this.roles = "ROLE_USER";
        // Per default each user is active
        this.active = true;
        this.life = 3;
        this.idxActualLearnObject = 1;
    }

    public User(int id, String userName, String password, boolean active, String roles, int idxActualLearnObject, int life) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.idxActualLearnObject = idxActualLearnObject;
        this.life = life;
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
