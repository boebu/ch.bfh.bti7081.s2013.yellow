package ch.bfh.bti7081.s2013.yellow.service.notification.strategy;

import ch.bfh.bti7081.s2013.yellow.model.notification.Notification;


/**
 *
 */
public interface SendNotificationStrategy {

    /**
     * sends the notification
     * @param notification
     */
    void sendNotification(Notification notification);
}
