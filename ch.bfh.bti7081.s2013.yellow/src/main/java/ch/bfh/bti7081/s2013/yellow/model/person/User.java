package ch.bfh.bti7081.s2013.yellow.model.person;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Andy Pollari
 * represents a user of the system
 */
@Entity
@Table(name="User")
public class User extends Person<User> {
    public User() {
        super(User.class);
    }

    @Basic(optional = false)
    String username;

  

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
