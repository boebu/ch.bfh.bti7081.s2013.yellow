package ch.bfh.bti7081.s2013.yellow.dao.notification;

import ch.bfh.bti7081.s2013.cs1_task4.Notification;
import ch.bfh.bti7081.s2013.yellow.dao.generic.GenericDAOImpl;
import org.springframework.stereotype.Repository;

/**
 * Implementation of NotificationDAO
 */
@Repository
public class NotificationDAOImpl extends GenericDAOImpl<Notification> implements NotificationDAO {

    public NotificationDAOImpl() {
        super(Notification.class);
    }
}
