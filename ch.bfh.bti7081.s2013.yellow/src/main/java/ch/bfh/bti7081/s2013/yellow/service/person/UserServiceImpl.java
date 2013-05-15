package ch.bfh.bti7081.s2013.yellow.service.person;

import ch.bfh.bti7081.s2013.yellow.dao.person.UserDAO;
import ch.bfh.bti7081.s2013.yellow.model.person.User;
import ch.bfh.bti7081.s2013.yellow.service.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation for ServiceUser
 */
@Transactional
@Service("userService")
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService {

    @Autowired
    UserDAO userDAO;

    @Override
    public User getLoggedInUser() {
        System.out.println("### getLoggedInUser is not implemented yet! -> TODO###");
        return null;  // TODO
    }
}
