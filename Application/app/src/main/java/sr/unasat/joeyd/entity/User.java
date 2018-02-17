package sr.unasat.joeyd.entity;

/**
 * Created by mpawirodinomo on 2/17/2018.
 */

public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;


    public User(long id, String firstName, String lastName, String userName, String password) {
        this.id = id;
        this.firstName = firstName;
        this.userName = userName;
        this.userName = userName;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
}
