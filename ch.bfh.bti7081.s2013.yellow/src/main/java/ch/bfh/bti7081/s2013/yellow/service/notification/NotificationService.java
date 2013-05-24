package ch.bfh.bti7081.s2013.yellow.service.notification;

import ch.bfh.bti7081.s2013.yellow.model.notification.Notification;
import ch.bfh.bti7081.s2013.yellow.service.generic.GenericService;

/**
 * @author Andy Pollari
 * Service interface for Notification
 */
public interface NotificationService extends GenericService<Notification> {

    /**
     * sends the Notification
     * @param notification
     */
    void send(Notification notification);

}
