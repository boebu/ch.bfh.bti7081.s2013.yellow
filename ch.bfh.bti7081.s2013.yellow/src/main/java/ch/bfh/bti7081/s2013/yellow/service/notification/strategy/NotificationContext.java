package ch.bfh.bti7081.s2013.yellow.service.notification.strategy;

import ch.bfh.bti7081.s2013.cs1_task4.Notification;

/**
 * In The NotificationContext the sendStrategy is defined.
 * The context provides to send the notification
 */
public class NotificationContext {

    private SendNotificationStrategy sendStrategy;

    /**
     * sends the notification with the correct strategy
     * @param notification
     */
    public void send(Notification notification)
    {
        sendStrategy.sendNotification(notification);
    }

    public SendNotificationStrategy getSendStrategy() {
        return sendStrategy;
    }

    public void setSendStrategy(SendNotificationStrategy sendStrategy) {
        this.sendStrategy = sendStrategy;
    }
}
