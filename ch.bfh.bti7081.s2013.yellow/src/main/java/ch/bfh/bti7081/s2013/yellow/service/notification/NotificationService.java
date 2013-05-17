package ch.bfh.bti7081.s2013.yellow.service.notification;

import ch.bfh.bti7081.s2013.cs1_task4.Notification;
import ch.bfh.bti7081.s2013.yellow.service.generic.GenericService;

public interface NotificationService extends GenericService<Notification> {

    /**
     * sends the Notification
     * @param notification
     */
    void send(Notification notification);

}
