package ch.bfh.bti7081.s2013.yellow.service.person;

import ch.bfh.bti7081.s2013.yellow.model.person.User;
import ch.bfh.bti7081.s2013.yellow.service.generic.GenericService;

/**
 * @author Andy Pollari
 * Service interface for UserService
 */
public interface UserService  extends GenericService<User> {
    /**
     *
     * @return logged in user. If user is not logged in, null is returned
     */
    User getLoggedInUser();

}
