package ch.bfh.bti7081.s2013.yellow.dao.person;

import ch.bfh.bti7081.s2013.yellow.dao.generic.GenericDAOImpl;
import ch.bfh.bti7081.s2013.yellow.model.person.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

    public UserDAOImpl() {
        super(User.class);
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> q = getEntityManager().createQuery("SELECT u FROM User u WHERE UPPER(u.username) = UPPER(:username)", User.class);
        q.setParameter("username", username);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null; //no user found
        }
    }

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> q = getEntityManager().createQuery("SELECT u FROM User u WHERE UPPER(u.email) = UPPER(:email)", User.class);
        q.setParameter("email", email);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null; //no user found
        }
    }

}
