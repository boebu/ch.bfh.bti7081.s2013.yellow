package ch.bfh.bti7081.s2013.yellow.model.person;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * represends a user of the system
 */
@Entity
@Table(name="User")
public class User extends Person<User> {
    public User() {
        super(User.class);
    }

    @Basic(optional = false)
    String username;

    @Basic
    String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}