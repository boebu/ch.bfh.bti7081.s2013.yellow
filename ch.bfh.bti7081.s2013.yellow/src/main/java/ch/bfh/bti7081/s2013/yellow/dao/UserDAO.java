package ch.bfh.bti7081.s2013.yellow.dao;

import ch.bfh.bti7081.s2013.yellow.dao.generic.GenericDAO;
import ch.bfh.bti7081.s2013.yellow.model.person.User;

public interface UserDAO extends GenericDAO<User> {

    /**
     *
     * @param username
     * @return user with given username. If user can't be found in the database -> return null
     */
    public User findByUsername(String username);
    
    User findByEmail(String email);
}
