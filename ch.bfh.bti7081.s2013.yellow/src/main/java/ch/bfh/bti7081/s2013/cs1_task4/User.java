package ch.bfh.bti7081.s2013.cs1_task4;


/**
 * This class represents a user
 */
public class User extends Person {
    int id;
    String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
