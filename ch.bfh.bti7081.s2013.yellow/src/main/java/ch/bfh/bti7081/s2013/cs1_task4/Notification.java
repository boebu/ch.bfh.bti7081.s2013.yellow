package ch.bfh.bti7081.s2013.cs1_task4;

import ch.bfh.bti7081.s2013.yellow.model.NotificationType;

/**
 * This class represents a notification
 */
public class Notification {

    public NotificationType getNotificationType;

    public Notification(User receiver, String message) {
        this.receiver = receiver;
        this.message = message;
    }

    User receiver;
    String message;

    public NotificationType getGetNotificationType() {
        return getNotificationType;
    }

    public void setGetNotificationType(NotificationType getNotificationType) {
        this.getNotificationType = getNotificationType;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
